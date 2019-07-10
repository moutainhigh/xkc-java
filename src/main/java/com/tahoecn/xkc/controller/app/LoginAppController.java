package com.tahoecn.xkc.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.JwtTokenUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.sys.SAppdevice;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISAppdeviceService;
import com.tahoecn.xkc.service.sys.ISLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Value("MobileSiteUrl")
    private String MobileSiteUrl;

    @ResponseBody
    @ApiOperation(value = "登陆", notes = "登陆")
    @RequestMapping(value = "/mLoginAC_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLoginAC_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap) jsonParam.get("_param");
        String userName = (String) paramMap.get("UserName");
        String password = (String) paramMap.get("Password");
        QueryWrapper<SAccount> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SAccount::getStatus, 1);
        wrapper.lambda().eq(SAccount::getIsDel, 0);
        wrapper.lambda().eq(SAccount::getUserName, userName);
        //wrapper.lambda().eq(SAccount::getPassword, SecureUtil.md5(pwd));
        SAccount account = accountService.getOne(wrapper);
        HashMap<String, Object> map;
        if (account != null) {
            map = accountService.mLoginSelectByAccount(userName);
        } else {
            map = accountService.mLoginSelectByChannelUser(userName);
        }
        if (map == null) {
            return Result.errormsg(10, "用户不存在");
        }

        Integer IsNoAllotRole = (Integer) map.get("IsNoAllotRole");//是否开启分接/销支
        Integer AllowDeviceType = (Integer) map.get("AllowDeviceType"); //允许登录设备类型 0.都不允许 1.只允许APP登录 2.只允许ipad登录 3.允许所有设备登录
        String JobCode = (String) map.get("JobCode");
        Integer AccountType = (Integer) map.get("AccountType");
        String tempPwd = (String) map.get("Password");
        String ProjectID = (String) map.get("ProjectID");
        String UserName = (String) map.get("UserName");
        Integer OutUserAllowModifyPwd = (Integer) map.get("OutUserAllowModifyPwd");//外部人员是否允许修改密码0.不允许 1.允许
        Integer OutUserIsShowHouseStyle = (Integer) map.get("OutUserIsShowHouseStyle");//外部人员展示房源页 0.不展示 1.展示
        Integer HouseStyle = (Integer) map.get("HouseStyle");//1.小格子 2.大格子 3.列表
        Integer AccountStatus = (Integer) map.get("AccountStatus");  // 0.禁用 1.开启
        String ChannelType = (String) map.get("ChannelType");
        String UserID = (String) map.get("UserID");
        Integer SelfSoldTeamIsShowHouseStyle = (Integer) map.get("SelfSoldTeamIsShowHouseStyle"); //自销团队是否展示房源页 0.不展示 1.展示
        Integer AgentTeamIsShowHouseStyle = (Integer) map.get("AgentTeamIsShowHouseStyle"); //代理团队是否展示房源页 0.不展示 1.展示
        if (1 == IsNoAllotRole && JobCode.equals("FJ")) {
            return Result.errormsg(10, "无法登陆,此账号所属项目没有开通分接角色");
        }
        if (1 == IsNoAllotRole && JobCode.equals("XSZC")) {
            return Result.errormsg(10, "无法登陆,此账号所属项目没有开通销支角色");
        }
        if (JobCode.equals("XSZC") && 0 == IsNoAllotRole && !Objects.equals(ProjectID, "") && 1 != AllowDeviceType && 3 != AllowDeviceType) {
            return Result.errormsg(10, "无法登陆,此账号所属项目没有开通APP登录权限");
        }
        if (0 == AccountStatus) {
            return Result.errormsg(10, "无法登陆,该账号已被禁用");
        }

        if (1 == AccountType) {
            String smap = accountService.checkUCUser(userName, password);
            JSONObject ucResult = JSONObject.parseObject(smap);
            if (0 != ucResult.getInteger("code")) {
                return Result.errormsg(11, "登录异常" + ucResult.getString("msg"));
            }
        } else {
            password = SecureUtil.md5(password);
            if (!tempPwd.equalsIgnoreCase(password)) {
                return Result.errormsg(10, "用户名密码不正确");
            }
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
    public String getRoleList(String ProjectID, String JobCode, String AccountType, int HouseStyle, String MobileSiteUrl, int OutUserAllowModifyPwd, int OutUserIsShowHouseStyle, String ChannelType, int SelfSoldTeamIsShowHouseStyle, int AgentTeamIsShowHouseStyle) {
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
            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("MessageStyle"))){

                String categroy =  MenuAndFunList.stream().filter(j -> j.get("Url").contains("MessageStyle")).findFirst().get().get("Url");
                sb.append("{'Name':'消息','Categroy':'" + categroy + "','NormalImage':'','SelectedImage':'','ChildRole':{");
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
            if(MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("WeiBook")) ||
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
                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("Helper"))) {
                    sb.append(",'Helper':1");
                } else {
                    sb.append(",'Helper':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("RobbingCustomerPool"))) {
                    sb.append(",'RobbingCustomerPool':1");
                } else {
                    sb.append(",'RobbingCustomerPool':0");
                }

                if (MenuAndFunList.stream().anyMatch(a -> a.get("Url").contains("IsDynamic"))) {    //动态
                    sb.append(",'IsDynamic':1");
                } else {
                    sb.append(",'IsDynamic':0");
                }
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
                    MenuAndFunList.stream().anyMatch(a -> a.get("Url").equals("IsPosterShare"))){
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
}
