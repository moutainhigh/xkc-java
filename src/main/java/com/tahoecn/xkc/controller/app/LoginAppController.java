package com.tahoecn.xkc.controller.app;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.JwtTokenUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SAppdevice;
import com.tahoecn.xkc.model.sys.SLogs;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.IASharelogService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISAppdeviceService;
import com.tahoecn.xkc.service.sys.ISLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-登录接口", value = "APP-登录接口")
@RequestMapping("/app/login")
public class LoginAppController extends TahoeBaseController {

    /** redis 统计用户输错密码次数用 */
    private static final String LOGIN_COUNT_KEY = "LOGIN_COUNT_KEY";

    @Autowired
    private ISAppdeviceService iSAppdeviceService;
    @Autowired
    private ISLogsService iSLogsService;
    @Autowired
    private IBVerificationcodeService iBVerificationcodeService;
    @Autowired
    private IBChanneluserService iBChanneluserService;

    @Autowired
    private ISAccountService accountService;
    @Autowired
    private IASharelogService iASharelogService;

    @Autowired
    private IBSalesuserService salesuserService;

    @Autowired
    private IBVerificationcodeService verificationcodeService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("MobileSiteUrl")
    private String MobileSiteUrl;

    @Value("CaseLinkageUrl")
    private String CaseLinkageUrl;

    @Value("${uc_api_url}")
    private String baseUrl;

    @Value("${uc_sysId}")
    private String sysId;

    @Value("${uc_priv_key}")
    private String privKey;

    @Value("${ImgSiteUrl}")
    private String imgSiteUrl;

    @ResponseBody
    @ApiOperation(value = "登陆", notes = "登陆")
    @RequestMapping(value = "/mLoginAC_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLoginAC_Select(@RequestBody JSONObject jsonParam) {

        Map paramMap = (HashMap) jsonParam.get("_param");
        String userName = (String) paramMap.get("UserName");
        String password = (String) paramMap.get("Password");
        String MobileNum = (String) paramMap.get("MobileNum");
        String Code = (String) paramMap.get("Code");

        if (StringUtils.isNotEmpty(MobileNum)) {
            if (StringUtils.isEmpty(Code)) {
                return Result.errormsg(1, "请输入验证码");
            }

            if(!checkErrorTime(MobileNum)){
                return Result.errormsg(1, "验证码输错次数超限，请等待五分钟后再试");
            }

            BVerificationcode vc = verificationcodeService.checkAuthCode(MobileNum);
            if (vc == null || !StringUtils.equals(Code, vc.getVerificationCode())) {
                inputErrorCodeOrPassword(MobileNum);
                return Result.errormsg(1, "验证码验证失败");
            }else{
                // 说明验证码输入正确重置错误计数次数
                resetLoginCount(MobileNum);
            }
        }else {
            if (!checkErrorTime(userName)) {
                return Result.errormsg(1, "密码输错次数超限，请等待五分钟后再试");
            }
        }

        QueryWrapper<SAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SAccount::getStatus, 1);
        wrapper.lambda().eq(SAccount::getIsDel, 0);
        wrapper.lambda().and(rolewrapper -> rolewrapper.eq(SAccount::getUserName, userName).or().eq(SAccount::getMobile,MobileNum));
        //wrapper.lambda().eq(SAccount::getPassword, SecureUtil.md5(pwd));
        SAccount account = accountService.getOne(wrapper);
        HashMap<String, Object> map;
        if (account != null) {
            map = accountService.mLoginSelectByAccount(userName,MobileNum);
        } else {
            map = accountService.mLoginSelectByChannelUser(userName,MobileNum);
        }
        if (map == null) {
            return Result.errormsg(10, "用户名或密码不正确");
        }

        Integer IsNoAllotRole = map.get("IsNoAllotRole") == null ? -1111111 : Integer.parseInt(map.get("IsNoAllotRole").toString());//是否开启分接/销支
        Integer AllowDeviceType = map.get("AllowDeviceType") == null ? -1111111 : Integer.parseInt(map.get("AllowDeviceType").toString()); //允许登录设备类型 0.都不允许 1.只允许APP登录 2.只允许ipad登录 3.允许所有设备登录
        String JobCode = (String) map.get("JobCode");
        Integer AccountType = map.get("AccountType") == null ? -1111111 : Integer.parseInt(map.get("AccountType").toString());
        String tempPwd = (String) map.get("Password");
        String ProjectID = (String) map.get("ProjectID");
        String UserName = (String) map.get("UserName");
        Integer OutUserAllowModifyPwd = map.get("OutUserAllowModifyPwd") == null ? -1111111 : Integer.parseInt(map.get("OutUserAllowModifyPwd").toString());//外部人员是否允许修改密码0.不允许 1.允许
        Integer OutUserIsShowHouseStyle = map.get("OutUserIsShowHouseStyle") == null ? -1111111 : Integer.parseInt(map.get("OutUserIsShowHouseStyle").toString());//外部人员展示房源页 0.不展示 1.展示
        Integer HouseStyle = map.get("HouseStyle") == null ? -1111111 : Integer.parseInt(map.get("HouseStyle").toString());//1.小格子 2.大格子 3.列表
        Integer AccountStatus = map.get("AccountStatus") == null ? -1111111 : Integer.parseInt(map.get("AccountStatus").toString());  // 0.禁用 1.开启
        String ChannelType = (String) map.get("ChannelType");
        String UserID = (String) map.get("UserID");
        Integer SelfSoldTeamIsShowHouseStyle = map.get("SelfSoldTeamIsShowHouseStyle") == null ? -1111111 : Integer.parseInt(map.get("SelfSoldTeamIsShowHouseStyle").toString()); //自销团队是否展示房源页 0.不展示 1.展示
        Integer AgentTeamIsShowHouseStyle = map.get("AgentTeamIsShowHouseStyle") == null ? -1111111 : Integer.parseInt(map.get("AgentTeamIsShowHouseStyle").toString()); //代理团队是否展示房源页 0.不展示 1.展示
        if (1 == IsNoAllotRole && JobCode.equals("FJ")) {
            return Result.errormsg(10, "无法登陆,此账号所属项目没有开通分接角色");
        }
        if (1 == IsNoAllotRole && JobCode.equals("XSZC")) {
            return Result.errormsg(10, "无法登陆,此账号所属项目没有开通销管角色");
        }
        if (JobCode.equals("XSZC") && 0 == IsNoAllotRole && !Objects.equals(ProjectID, "") && 1 != AllowDeviceType && 3 != AllowDeviceType) {
            return Result.errormsg(10, "无法登陆,此账号所属项目没有开通APP登录权限");
        }
        if (0 == AccountStatus) {
            return Result.errormsg(10, "无法登陆,该账号已被禁用");
        }

        if (1 == AccountType) {
            if (StringUtils.isEmpty(Code)){
                String smap = accountService.checkUCUser(userName, password);
                JSONObject ucResult = JSONObject.parseObject(smap);
                if (0 != ucResult.getInteger("code")) {
                    inputErrorCodeOrPassword(userName);
                    return Result.errormsg(11, "登录异常" + ucResult.getString("msg"));
                }
            }
        } else {
            if (StringUtils.isEmpty(Code)) {
                password = SecureUtil.md5(password);
                if (!tempPwd.equalsIgnoreCase(password)) {
                    inputErrorCodeOrPassword(userName);
                    return Result.errormsg(10, "用户名或密码不正确");
                }
            }
        }

        // 说明密码输入正确 重置错误计数次数
        if (StringUtils.isNotBlank(userName)) {
            resetLoginCount(userName);
        }

        String JPushAlias = UserID.replace('-', '_');
        List<HashMap<String, String>> saleUserProject = accountService.salesUserProjectList(UserID);
        List<HashMap<String, String>> projectJobList = accountService.userProjectJobList(UserID, ProjectID);
        map.put("JPushAlias", JPushAlias);
        map.put("ProjectList", saleUserProject);
        map.put("ProjectJobList", projectJobList);

        //权限处理
        String jsonStr = getRoleList(ProjectID, JobCode, AccountType.toString(), HouseStyle, MobileSiteUrl, OutUserAllowModifyPwd, OutUserIsShowHouseStyle, ChannelType, SelfSoldTeamIsShowHouseStyle, AgentTeamIsShowHouseStyle);
        JSONArray RoleList = JSONObject.parseArray(jsonStr);

        //绑定设备信息
        SAppdevice sAppdevice = new SAppdevice();
        sAppdevice.setDeviceCode((String) map.get("DeviceCode"));
        sAppdevice.setPlatform((String) map.get("Platform"));
        sAppdevice.setAppName((String) map.get("AppName"));
        sAppdevice.setModel((String) map.get("Model"));
        sAppdevice.setJPushID((String) map.get("JPushID"));
        sAppdevice.setJPushAlias(JPushAlias);
        sAppdevice.setLastUserID(UserID);
        sAppdevice.setLastUserName(UserName);
        iSAppdeviceService.saveOrUpdate(sAppdevice);

        //登出日志记录
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("BizType", "LogoutACSuccess");
        logMap.put("BizDesc", "案场登录成功,账号:" + paramMap.get("UserName"));
        logMap.put("Ext1", paramMap.get("Model"));
        logMap.put("Ext2", paramMap.get("DeviceCode"));
        logMap.put("Ext3", paramMap.get("UserName"));
        logMap.put("Ext4", paramMap.get("AppName"));
        logMap.put("Data", jsonParam.toJSONString());
        iSLogsService.SystemLogsDetail_Insert(logMap, request);

        //输出数据处理
        map.put("RoleList",RoleList);
        map.remove("Password");
        map.remove("OutUserAllowModifyPwd");
        map.remove("OutUserIsShowHouseStyle");
        map.remove("AccountStatus");

        String token = JwtTokenUtil.createToken(UserID, UserName, false);
        //放到响应头部
        response.setHeader(JwtTokenUtil.TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);

        return Result.ok(map);
    }

    /// <summary>
    /// 获取权限列表
    /// </summary>
    /// <param name="JobCode">角色编码</param>
    /// <param name="AccountType">账号类型,1内部账号,2普通账号,3代理</param>
    /// <param name="HouseStyle">房源展示类型,1.小格子 2.大格子 3.列表</param>
    /// <param name="MobileSiteUrl">移动端站点路径</param>
    /// <param name="OutUserAllowModifyPwd">外部人员是否允许修改密码0.不允许 1.允许</param>
    /// <param name="OutUserIsShowHouseStyle">外部人员展示房源页 0.不展示 1.展示</param>
    /// <param name="ChannelType">渠道类型</param>
    /// <param name="SelfSoldTeamIsShowHouseStyle">自销团队是否展示房源页 0.不展示 1.展示</param>
    /// <param name="AgentTeamIsShowHouseStyle">代理团队是否展示房源页 0.不展示 1.展示</param>
    /// <param name="debug"></param>
    /// <returns></returns>
    public String getRoleList(String ProjectID, String JobCode, String AccountType, Integer HouseStyle, String MobileSiteUrl, Integer OutUserAllowModifyPwd, Integer OutUserIsShowHouseStyle, String ChannelType, Integer SelfSoldTeamIsShowHouseStyle, Integer AgentTeamIsShowHouseStyle) {
        String jsonStr = "";
		List<HashMap<String,String>> MenuAndFunList = accountService.GetMenuAndFunList_Select(JobCode,ProjectID);
		if (MenuAndFunList.size() != 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("DataYXFZR"))){
                sb.append("{'Name': '数据','Categroy': 'Data','NormalImage': '','SelectedImage': '','ChildRole': { 'Data_H5URL':'").append(MobileSiteUrl).append("View/rCustomer/YXJLUserCustomerRank.html'}},");
            }
            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("DataZQFZR"))){
                sb.append("{'Name': '数据','Categroy': 'Data','NormalImage': '','SelectedImage': '','ChildRole': { 'Data_H5URL':'").append(MobileSiteUrl).append("View/rCustomer/YXJLUserCustomerRank.html'}},");
            }
            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("MessageStyle"))){

                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("MessageStyle")).findFirst().get().get("Url");
                sb.append("{'Name':'首页','Categroy':'" + categroy + "','NormalImage':'','SelectedImage':'','ChildRole':{");
                sb.append("'CustomerMobileHide':0");

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("ToDoListPage_Phone"))){
                    sb.append(",'ToDoListPage_Phone':1");
                } else {
                    sb.append(",'ToDoListPage_Phone':0");
                }

                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerListPage_PushInfo"))){
                    sb.append(",'CustomerListPage_PushInfo':1");
                } else {
                    sb.append(",'CustomerListPage_PushInfo':0");
                }
                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerInfo_NeedEdit"))){  //消息进入客户详情
                    sb.append(",'CustomerInfo_NeedEdit':1");
                } else {
                    sb.append(",'CustomerInfo_NeedEdit':0");
                }
                sb.append("}},");
            }

            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("ChannelTaskList"))){
                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("ChannelTaskList")).findFirst().get().get("Url");
				sb.append("{'Name':'任务','Categroy':'" + categroy + "','NormalImage':'','SelectedImage':'','ChildRole':{");
				sb.append("}},");
			}

            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("ChannelTaskMap"))){
                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("ChannelTaskMap")).findFirst().get().get("Url");
				sb.append("{'Name':'作战图','Categroy':'" + categroy + "','NormalImage':'','SelectedImage':'','ChildRole':{");
				sb.append("}},");
			}

            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("Attendance"))){
                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("Attendance")).findFirst().get().get("Url");
				sb.append("{'Name':'考勤','Categroy':'" + categroy + "','NormalImage':'','SelectedImage':'','ChildRole':{");
				sb.append("}},");
			}

            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("ChannelUserList"))){
                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("ChannelUserList")).findFirst().get().get("Url");
				sb.append("{'Name':'团队','Categroy':'" + categroy + "','NormalImage':'','SelectedImage':'','ChildRole':{");
				sb.append("}},");
			}

            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerStyle"))){
                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("CustomerStyle")).findFirst().get().get("Url");
				sb.append("{'Name': '客户','Categroy': '" + categroy + "','NormalImage': '','SelectedImage': '','ChildRole': {'CustomerMobileHide': 0");

                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerListPage_PushInfo"))){  //进入客户详情
					sb.append(",'CustomerListPage_PushInfo':1");
				} else {
					sb.append(",'CustomerListPage_PushInfo':0");
				}
                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerInfo_NeedEdit"))){  //客户编辑
					sb.append(",'CustomerInfo_NeedEdit':1");
				} else {
					sb.append(",'CustomerInfo_NeedEdit':0");
				}
                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerListPage_AddCustomer"))){   //是否可登记客户
					sb.append(",'CustomerListPage_AddCustomer':1");
				} else {
					sb.append(",'CustomerListPage_AddCustomer':0");
				}
                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerListPage_CustomerLabel"))){ //是否显示客户标签
					sb.append(",'CustomerLabel':1");
				} else {
					sb.append(",'CustomerLabel':0");
				}

                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerListPage_CustomerFollow"))){    //是否显示关注
					sb.append(",'CustomerFollow':1");
				} else {
					sb.append(",'CustomerFollow':0");
				}

                if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CustomerListPage_IsShowMobile"))){    //是否显示手机号
					sb.append(",'IsShowMobile':1");
				} else {
					sb.append(",'IsShowMobile':0");
				}
				sb.append("}},");
			}

            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("HouseStyleOne"))){
				Boolean isHouse = true;
				if (Objects.equals(ChannelType, "自销")) {
					isHouse = SelfSoldTeamIsShowHouseStyle == 1;
				}
				if (Objects.equals(ChannelType, "代理")) {
					isHouse = AgentTeamIsShowHouseStyle == 1;
				}
				if (isHouse) {
					//泉州华大的顾问角色不显示房源
					if (!Objects.equals(ProjectID.toUpperCase(), "D4F3C7ED-56B2-E711-80C7-00505686C900") || !Objects.equals(JobCode, "GW")) {
						sb.append("{'Name': '房源','Categroy': 'HouseStyleOne','NormalImage': '','SelectedImage': '','ChildRole': {'CustomerMobileHide': 0,'HouseStyle': " + HouseStyle);

                        if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsManager"))){    //是否管理
							sb.append(",'IsManager':1");
						} else {
							sb.append(",'IsManager':0");
						}
						sb.append("}},");
					}
				}
			}
            /**
             * 不要了
             * if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("WeiBook")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("AR")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("Helper")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("RobbingCustomerPool")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("PerformanceBoard")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsDynamic"))){
				sb.append("{'Name': '锦囊','Categroy': 'Bag','NormalImage': '','SelectedImage': '','ChildRole': {'CustomerMobileHide': 0");

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("WeiBook"))) {    //微楼书
                    sb.append(",'WeiBook':1");
                } else {
                    sb.append(",'WeiBook':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("AR"))) { //AR
                    sb.append(",'AR':1");
                } else {
                    sb.append(",'AR':0");
                }
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("Helper"))) {   //营销助手
                    sb.append(",'Helper':1");
                } else {
                    sb.append(",'Helper':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("RobbingCustomerPool"))) {
                    sb.append(",'RobbingCustomerPool':1");
                } else {
                    sb.append(",'RobbingCustomerPool':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("PerformanceBoard"))) {  //业绩看板
                    sb.append(",'PerformanceBoard':1");
                } else {
                    sb.append(",'PerformanceBoard':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("IsDynamic"))) {    //动态
                    sb.append(",'IsDynamic':1");
                } else {
                    sb.append(",'IsDynamic':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CaseLinkage")) && !Objects.equals(ProjectID.toUpperCase(), "252B3699-51B2-E711-80C7-00505686C900")) {    //案场联动
                    sb.append(",'IsCaseLinkage':1");
                } else {
                    sb.append(",'IsCaseLinkage':0");
                }
                sb.append(",'CaseLinkageUrl':'").append(CaseLinkageUrl).append("'");
                sb.append("}},");
            }*/


            if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("Helper"))) {   //营销助手
                sb.append("{'Name': '营销助手','Categroy': 'yxzs','NormalImage': '','SelectedImage': '','ChildRole': {'CustomerMobileHide':0");
                sb.append("}},");
            }




            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MineCare")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("ChangePassword")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MinePerformance")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("ChangeProject")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsManager")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MineCustomer")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsAppointmentCustomer")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsMineCustomer")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsMineEvaluate")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsShareSmallProgram")) ||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsPosterShare"))||
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("AllOverdue"))){
				sb.append("{'Name': '我的','Categroy': 'Mine','NormalImage': '','SelectedImage': '','ChildRole': {'CustomerMobileHide':0");

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MineCare"))) {
                    sb.append(",'MineCare': 1");
                } else {
                    sb.append(",'MineCare': 0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("ChangePassword"))) {
                    if (Objects.equals(AccountType, "3")) {//外部代理用户
                        sb.append(",'ChangePassword': ").append(OutUserAllowModifyPwd);
                    } else {
                        sb.append(",'ChangePassword': 1");
                    }
                } else {
                    if (Objects.equals(AccountType, "3")) {//外部代理用户
                        sb.append(",'ChangePassword': ").append(OutUserAllowModifyPwd);
                    } else {
                        sb.append(",'ChangePassword': 0");
                    }
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MinePerformance"))) {
                    sb.append(",'MinePerformance': 1");
                } else {
                    sb.append(",'MinePerformance': 0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MineJob"))) {
                    sb.append(",'MineJob': 1");
                } else {
                    sb.append(",'MineJob': 0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("ChangeProject"))) {
                    sb.append(",'ChangeProject': 1");
                } else {
                    sb.append(",'ChangeProject': 0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsManager"))) {
                    sb.append(",'IsManager': 1");
                } else {
                    sb.append(",'IsManager': 0");
                }
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MineCustomer"))) {
                    sb.append(",'MineCustomer': 1");
                } else {
                    sb.append(",'MineCustomer': 0");
                }
                //新增客户预约
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsAppointmentCustomer"))) {
                    sb.append(",'IsAppointmentCustomer': 1");
                } else {
                    sb.append(",'IsAppointmentCustomer': 0");
                }

				//海报分享
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsPosterShare"))) {
                    sb.append(",'IsPosterShare': 1");
                } else {
                    sb.append(",'IsPosterShare': 0");
                }
                //分享小程序
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsShareSmallProgram"))) {
                    sb.append(",'IsShareSmallProgram': 1");
                } else {
                    sb.append(",'IsShareSmallProgram': 0");
                }
                //我分享的好友
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsMineCustomer"))) {
                    sb.append(",'IsMineCustomer': 1");
                } else {
                    sb.append(",'IsMineCustomer': 0");
                }
                //我的评价

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsMineEvaluate"))) {
                    sb.append(",'IsMineEvaluate': 1");
                } else {
                    sb.append(",'IsMineEvaluate': 0");
                }
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("IsDynamic"))) {    //动态
                    sb.append(",'IsDynamic':1");
                } else {
                    sb.append(",'IsDynamic':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("CaseLinkage"))) {    //案场联动
                    sb.append(",'IsCaseLinkage':1");
                } else {
                    sb.append(",'IsCaseLinkage':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("AllOverdue"))) {    //全部逾期
                    sb.append(",'IsAllOverdue':1");
                } else {
                    sb.append(",'IsAllOverdue':0");
                }
                sb.append(",'CaseLinkageUrl':'").append(CaseLinkageUrl).append("'");


                sb.append("}},");
			}
			sb.deleteCharAt(sb.length()-1);
			jsonStr = sb.toString();
			jsonStr += "]";
		}
		if (StringUtils.isEmpty(jsonStr)) {
			jsonStr = "[{'Name':'我的','Categroy':'Mine','NormalImage':'','SelectedImage':'','ChildRole':{'CustomerMobileHide':0,'CustomerInfo_NeedEdit':1,'ChangeProject':1,'MineJob':1,'MineCare':1,'MinePerformance':0,'CustomerListPage_PushInfo':1,'ChangePassword':0,'MineCustomer':0}}]";
		}
		return jsonStr;
	}


    @ResponseBody
    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "/mUserLogout_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserLogout_Insert(@RequestBody JSONObject jsonParam) {
    	try{
    		// 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String jobCode = (String)paramMap.get("JobCode");
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("UserID", (String)paramMap.get("UserID"));
    		map.put("DeviceCode", (String)paramMap.get("DeviceCode"));
    		map.put("Platform", (String)paramMap.get("Platform"));
    		map.put("AppName", (String)paramMap.get("AppName"));
    		//1.解绑设备信息
    		iSAppdeviceService.SystemAppDeviceDetail_Update(map);
    		//2.登出日志记录
    		Map<String,Object> logMap = new HashMap<String,Object>();
            logMap.put("BizType", "LogoutACSuccess");
            logMap.put("BizDesc", "案场登出成功,账号:" + (String)paramMap.get("UserName"));
            logMap.put("Ext1", (String)paramMap.get("Model"));
            logMap.put("Ext2", (String)paramMap.get("DeviceCode"));
            logMap.put("Ext3", (String)paramMap.get("UserName"));
            logMap.put("Ext4", (String)paramMap.get("AppName"));
            logMap.put("Data", jsonParam.toJSONString());
            iSLogsService.SystemLogsDetail_Insert(logMap, request);
            
    		return Result.ok("案场登出成功,账号:" + (String)paramMap.get("UserName"));
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
    @ResponseBody
    @ApiOperation(value = "注册", notes = "行销拓客兼职注册")
    @RequestMapping(value = "/mChannelRegistJZ_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mChannelRegistJZ_Insert(@RequestBody JSONObject jsonParam) {
    	try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            String Mobile = (String)paramMap.get("Mobile");
            String AuthCode = (String)paramMap.get("CheckCode");
            //1.验证验证码
            if(!iBVerificationcodeService.Verification(Mobile,AuthCode)){
            	return Result.errormsg(1,"验证码验证失败");
            }
            //2.手机重名验证
            if(iBChanneluserService.ChannelUserCheckMobile_Select(paramMap) > 0){
            	return Result.errormsg(1,"手机号重复");
            }
            //3.行销拓客兼职注册
            iBChanneluserService.mChannelRegistJZ_Insert(paramMap);
            return Result.ok("注册成功");
    	}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
    }
    @ResponseBody
    @ApiOperation(value = "分享埋点", notes = "分享记录")
    @RequestMapping(value = "/mShareAppLog_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mShareAppLog_Insert(@RequestBody JSONObject jsonParam) {
    	try{
    		Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
    		String Category = (String)paramMap.get("Category");
    		Map<String,Object> data = new HashMap<String,Object>();
            String ID = UUID.randomUUID().toString();
            String pagepath = "";
            paramMap.put("ID", ID);
            if (Category != null && !"".equals(Category)){
                switch (Category){
                    case "1":
                        pagepath = "/pages/detail/index";
                        break;
                    case "4":
                        pagepath = "/pages/detail/index";
                        break;
                    default:
                        pagepath = "";
                        break;
                }
            }
            paramMap.put("PagePath", pagepath);
            iASharelogService.mShareAppLog_Insert(paramMap);
            data.put("ShareLogID", ID);
    		return Result.ok(data);
    	}catch (Exception e) {
    		e.printStackTrace();
    		return Result.errormsg(1,"系统异常，请联系管理员");
    	}
    }


    /**
     * 检查登录时输错密码或验证码的次数
     * @param userName
     *          用户名或手机号
     * @return
     */
    private boolean checkErrorTime(String userName) {
        //根据用户名设置key
        String keys = LOGIN_COUNT_KEY + "_" + userName;
        Integer a = (Integer) redisTemplate.opsForValue().get(keys);

        if (a != null && a.intValue() >= 5) {
            // 说明用户已输错五次及以上密码，须等待最后一次输入密码错误的时间加上五分钟后再试
            return false;
        }

        return true;
    }

    /**
     * 输错密码或验证码
     * @param userName
     *          用户名或手机号
     */
    private void inputErrorCodeOrPassword(String userName) {
        String keys = LOGIN_COUNT_KEY + "_" + userName;
        Integer a = (Integer) redisTemplate.opsForValue().get(keys);

        // 代表第一次输错密码
        if (a == null) {
            a = 1;
            redisTemplate.opsForValue().set(keys, a);
        }else {
            // 不止第一次输错密码
            a++;
            // 设置keys值以及过期时间
            redisTemplate.opsForValue().set(keys, a, 300, TimeUnit.SECONDS);
        }
    }

    /**
     * 重置登录计数次数
     * @param userName
     *          用户名或手机号
     */
    private void resetLoginCount(String userName) {
        String keys = LOGIN_COUNT_KEY + "_" + userName;
        redisTemplate.delete(keys);
    }
}
