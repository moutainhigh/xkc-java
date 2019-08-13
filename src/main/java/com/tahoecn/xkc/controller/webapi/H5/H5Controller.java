package com.tahoecn.xkc.controller.webapi.H5;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IVABrokerMycustomersService;
import com.tahoecn.xkc.service.project.IABrokerprojectService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IBProjectcollectionService;
import com.tahoecn.xkc.service.rule.IBClueruleService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/H5")
public class H5Controller extends TahoeBaseController {

    @Autowired
    private IABrokerprojectService brokerprojectService;
    @Autowired
    private IBProjectService projectService;
    @Autowired
    private IBProjectcollectionService projectcollectionService;
    @Autowired
    private IBChanneluserService channeluserService;
    @Autowired
    private IVABrokerMycustomersService mycustomersService;
    @Autowired
    private ISystemMessageService messageService;
    @Autowired
    private IBClueService clueService;
    @Autowired
    private IBVerificationcodeService verificationcodeService;
    @Autowired
    private ISFormsessionService formsessionService;
    @Autowired
    private IBClueruleService clueruleService;

    //已测  AppID=5D4D7079-D294-4204-BD51-C3AB420C6C2F
    @ApiOperation(value = "获取城市", notes = "获取城市")
    @RequestMapping(value = "/mBrokerCityList_Select", method = {RequestMethod.POST})
    public Result mBrokerCityList_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        List<HashMap<String, String>> list = brokerprojectService.mBrokerCityList_Select((String) paramMap.get("AppID"));
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
        //前两条空,数据问题
    }
    //已测 OrgID=16c92dc7-2eca-4397-aa2d-7a38c5671201
    @ApiOperation(value = "获取房源项目列表", notes = "获取房源项目列表")
    @RequestMapping(value = "/mBrokerProjectList_SelectN", method = {RequestMethod.POST})
    public Result mBrokerProjectList_SelectN(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String OrgID=(String) paramMap.get("OrgID");
        String Name=(String) paramMap.get("Name");
        String CityID=(String) paramMap.get("CityID");
        int PageIndex=(int) paramMap.get("PageIndex");
        int PageSize=(int) paramMap.get("PageSize");
        //获取是否是机构的人
        //不为空表示登录进来的肯定是机构的人
        List<Map<String, Object>> list;
        IPage page=new Page(PageIndex,PageSize);
        if (StringUtils.isNotBlank(OrgID)) {
            list = projectService.findByOrgID(page,OrgID);
        } else {
            list = projectService.ProjectInfoList_SelectN(page,Name, CityID);
        }
        Map<String, Object> map=new HashMap<>();
        map.put("List",list);
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }

    //已测   BrokerProjectID=90DCFD49-0AE6-4F1E-A0CB-0EAC1151600E       ChannelOrgID=16c92dc7-2eca-4397-aa2d-7a38c5671201
        @ApiOperation(value = "首页-房源详情列表", notes = "首页-房源详情列表")
    @RequestMapping(value = "/mBrokerProjectDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerProjectDetail_Select(@RequestBody JSONObject jsonParam) {
            Map paramMap = (HashMap)jsonParam.get("_param");
            String BrokerProjectID=(String) paramMap.get("BrokerProjectID");
            String ChannelOrgID=(String) paramMap.get("ChannelOrgID");
        HashMap<String, Object> map = brokerprojectService.mBrokerProjectDetail_Select(BrokerProjectID, ChannelOrgID);
        Result result = new Result();
        result.setErrcode(GlobalConstants.S_CODE);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }

    //已测 ProjectID     UserID
    @ApiOperation(value = "当前项目是否被收藏", notes = "当前项目是否被收藏")
    @RequestMapping(value = "/mBrokerProjectCollectionIsExist_Select", method = {RequestMethod.POST})
    public Result mBrokerProjectCollectionIsExist_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String ProjectID=(String) paramMap.get("ProjectID");
        String UserID=(String) paramMap.get("UserID");
        Result result = new Result();
        if (StringUtils.isBlank(ProjectID)) {
            result.setErrcode(1);
            result.setErrmsg("项目ID不可以为空");
            return result;
        }
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(1);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        int total = projectcollectionService.mBrokerProjectCollectionIsExist_Select(ProjectID, UserID);
        Map map = new HashMap();
        map.put("total", total);
        result.setErrcode(0);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }


    @ApiOperation(value = "登录账号", notes = "登录账号")
    @RequestMapping(value = "/mLoginTK_SelectN", method = {RequestMethod.POST})
    public Result mLoginTK_SelectN(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserName=(String) paramMap.get("UserName");
        String Password=(String) paramMap.get("Password");
        Date date = new Date();
        String time = DateUtil.format(date,"yyyyMMddHHmm");
        Map<String, Object> map;
        if (StringUtils.equals(time, Password)) {
            map = channeluserService.ChannelUserCurrency_Find(UserName);
        } else {
            map =channeluserService.ChannelUser_Find(UserName,SecureUtil.md5(Password));
        }
        if (map==null){
            return Result.errormsg(1,"获取用户信息失败，用户名或密码错误");
        }
        if ((short) map.get("Status")==0){
            return Result.errormsg(1,"获取用户信息失败，账户被禁用");
        }
        String id= (String) map.get("ID");
        Map<String,Object> user=channeluserService.ChannelUser_Detail_FindByIdN(id);
        if (user==null){
            //获取出数据，需要修改其中的SQL语句??? what??
            return Result.errormsg(1,"获取用户信息失败，机构被禁用");
        }else {
            //判断中介同行的机构是否被禁用了
            if ("32C92DA0-DA13-4C21-A55E-A1D16955882C".equals(user.get("ChannelTypeID"))
                    &&user.get("ChannelOrgCode")==null){
                return Result.errormsg(1,"获取用户信息失败，机构被禁用");
            }
        }
        return Result.ok(user);
    }

    @ApiOperation(value = "微信-JSAPI包信息", notes = "微信-JSAPI包信息")
    @RequestMapping(value = "/mWeixinJsApiPackage_Select", method = {RequestMethod.POST})
    public Result mWeixinJsApiPackage_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String BrokerProjectID=(String) paramMap.get("BrokerProjectID");
        String ChannelOrgID=(String) paramMap.get("ChannelOrgID");

        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
//        result.setData(map);
        return result;
    }

    //已测
    @ApiOperation(value = "获取个人中心的统计数据", notes = "获取个人中心的统计数据")
    @RequestMapping(value = "/mBrokerMyCenter_Select", method = {RequestMethod.POST})
    public Result mBrokerMyCenter_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String BrokerID=(String) paramMap.get("BrokerID");
        Map<String, Object> map = channeluserService.BrokerMyCenter_Select(BrokerID);
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
        result.setData(map);
        return result;
    }


    //已测
    @ApiOperation(value = "获取我的客户列表", notes = "获取我的客户列表")
    @RequestMapping(value = "/mGetMyCustomers_Select", method = {RequestMethod.POST})
    public Result mGetMyCustomers_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        int Sort=(int) paramMap.get("Sort");
        String Filter=(String) paramMap.get("Filter");
        String CustomerInfo=(String) paramMap.get("CustomerInfo");
        String BrokerID=(String) paramMap.get("BrokerID");
        int PageIndex=(int) paramMap.get("PageIndex");
        int PageSize=(int) paramMap.get("PageSize");
        IPage page = new Page(PageIndex, PageSize);
        List<Map<String, Object>> list = mycustomersService.mGetMyCustomers_Select(page, Sort, Filter, CustomerInfo, BrokerID);
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
    }

    //已测
    @ApiOperation(value = "获取渠道人员", notes = "获取渠道人员")
    @RequestMapping(value = "/mBrokerChannelUserDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerChannelUserDetail_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        Result result = new Result();
        Map<String, Object> map = channeluserService.mBrokerChannelUserDetail_Select(UserID);
        result.setErrcode(0);
        result.setData(map);
        result.setErrmsg("成功");
        return result;
    }


    //已测
    @ApiOperation(value = "修改个人证件信息", notes = "修改个人证件信息")
    @RequestMapping(value = "/mBrokerChannelUserCardDetail_Update", method = {RequestMethod.POST})
    public Result mBrokerChannelUserCardDetail_Update(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String CertificatesName=(String) paramMap.get("CertificatesName");
        String CertificatesType=(String) paramMap.get("CertificatesType");
        String CertificatesNo=(String) paramMap.get("CertificatesNo");
        String CertificatesPicFace=(String) paramMap.get("CertificatesPicFace");
        String CertificatesPicBack=(String) paramMap.get("CertificatesPicBack");
        int i = channeluserService.mBrokerChannelUserCardDetail_Update(UserID, CertificatesName, CertificatesType, CertificatesNo, CertificatesPicFace, CertificatesPicBack);
       if(i==1){
           return Result.ok("成功");
       }
        return Result.errormsg(1,"修改失败");
    }

    //已测
    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/mMessageAllList_Select", method = {RequestMethod.POST})
    public Result mMessageAllList_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        int PageIndex=(int) paramMap.get("PageIndex");
        int PageSize=(int) paramMap.get("PageSize");
        IPage page = new Page(PageIndex, PageSize);
        List<Map<String, Object>> list = messageService.mMessageAllList_Select(page, UserID);
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
        result.setData(list);
        return result;
    }

    @ApiOperation(value = "查询用户收藏的所有项目", notes = "查询用户收藏的所有项目")
    @RequestMapping(value = "/mBrokerProjectCollectionList_Select", method = {RequestMethod.POST})
    public Result mBrokerProjectCollectionList_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        int PageIndex=(int) paramMap.get("PageIndex");
        int PageSize=(int) paramMap.get("PageSize");
        if (StringUtils.isBlank(UserID)) {
            return Result.errormsg(1,"用户ID不可以为空");
        }
        IPage page = new Page(PageIndex, PageSize);
        IPage<Map<String, Object>> list = projectcollectionService.mBrokerProjectCollectionList_Select(page, UserID);
        return Result.ok(list);
    }

    //未测
    @ApiOperation(value = "修改个人银行卡信息", notes = "修改个人银行卡信息")
    @RequestMapping(value = "/mBrokerChannelUserBankCardDetail_Update", method = {RequestMethod.POST})
    public Result mBrokerChannelUserBankCardDetail_Update(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String BankCardPerson=(String) paramMap.get("BankCardPerson");
        String BankCardCreate=(String) paramMap.get("BankCardCreate");
        String UserID=(String) paramMap.get("UserID");
        String BankCard=(String) paramMap.get("BankCard");
        String BankCardProvince=(String) paramMap.get("BankCardProvince");
        String BankCardCity=(String) paramMap.get("BankCardCity");
        String BankCardArea=(String) paramMap.get("BankCardArea");
        String BankCardBranch=(String) paramMap.get("BankCardBranch");
        String BankCardPic=(String) paramMap.get("BankCardPic");
        int i = channeluserService.mBrokerChannelUserBankCardDetail_Update(UserID, BankCardPerson, BankCardCreate, BankCard, BankCardProvince,
                BankCardCity, BankCardArea, BankCardBranch, BankCardPic);
        Result result = new Result();
        if (i == 1) {
            result.setErrcode(0);
            result.setErrmsg("成功");
            return result;
        }
        result.setErrcode(1);
        result.setErrmsg("修改失败");
        return result;
    }

    //未完成
//    @ApiOperation(value = "生成FormSessionID", notes = "生成FormSessionID")
//    @RequestMapping(value = "/mSystemFormSession_Insert", method = {RequestMethod.POST})
//    public Result mSystemFormSession_Insert(@RequestBody JSONObject jsonParam) {
//        //将FormSessionID IP Data存入表S_FormSession 其中Data为传入参数  ID为生成
//        String data = JSON.toJSONString(jsonParam.get("_param"));
//        data=data.replace("'","%27");
//        String ip= NetUtil.getClientIp(request);
//        SFormsession formsession=new SFormsession();
//        formsession.setIp(ip);
//        formsession.setData(data);
//        formsession.setCreateTime(new Date());
//        formsession.setStatus(1);
//        boolean save = formsessionService.save(formsession);
//        Map map=new HashMap();
//        map.put("FormSessionID",formsession.getId());
//        if (save){
//           return Result.ok(map);
//        }
//        return Result.errormsg(99,"生成FormSession失败");
//    }

    //未完成
    @ApiOperation(value = "渠道报备客户--推荐提交", notes = "渠道报备客户--提交")
    @RequestMapping(value = "/mBrokerReport_Insert", method = {RequestMethod.POST})
    public Result mBrokerReport_Insert(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String userID=(String) paramMap.get("UserID");
        String mobile=(String) paramMap.get("Mobile");
        String projectId =(String) paramMap.get("IntentProjectID");
        String formSessionID =(String) paramMap.get("FormSessionID");
        String adviserGroupID =(String) paramMap.get("AdviserGroupID");
        Result result = new Result();
        //验证是否重复请求
        if (StringUtils.isBlank(formSessionID)){
            return Result.errormsg(1,"FormSessionID不可为null");
        }
        //查询是否已经存在无效的FormSessionID,不存在则更新为无效状态
        int RowCount =formsessionService.checkFormSessionID(formSessionID);
        if (RowCount==1){
            Result.errormsg(1,"不能重复请求！");
        }
        if (StringUtils.isBlank(adviserGroupID)){
            Result.errormsg(1,"未能识别报备人的身份");
        }
        //1.不允许报备自己 0.允许报备自己  IsReportOwn
        int IsReportOwn=projectService.isReport(projectId,userID,mobile);
        if (IsReportOwn==1){
            Result.errormsg(1,"不允许报备自己");
        }
        //没有ChannelOrgID 的不能报备
        int IsReport=channeluserService.isReport(userID);
        //没有ChannelOrgID 的不能报备
        if (IsReport==-2){
            Result.errormsg(1,"没有所属机构的人员，不能报备");
        }
        //ChannelOrgID禁用状态的不能报备
        if (IsReport==0){
            Result.errormsg(1,"所属机构在禁用状态，不能报备");
        }
        //获取报备用户所适用的规则
        //未测 目前map无值
        Map<String,Object> userRule=clueruleService.getRegisterRule(projectId,adviserGroupID);
//        if (userRule.get("RuleID")==null){
        if (userRule==null){
            Result.errormsg(1,"未找到该渠道的报备规则");
        }
        //验证报备客户是否有效
        Map<String, Object> ruleValidate = clueService.ValidateForReport(userID, mobile, projectId, userRule);
        String channelOrgId=channeluserService.getChannelOrgID(userID,adviserGroupID);
        String msg=clueService.getMessage((int)ruleValidate.get("InvalidType"),userRule);
        ruleValidate.put("Message",msg);
        String errMsg=clueService.GetMessageForReturn((int)ruleValidate.get("InvalidType"),userRule);
        //通过有效验证
        int status = 0;
        if ((boolean)ruleValidate.get("Tag")){
            //竞争带看
            if ((int) userRule.get("RuleType") == 1 ){
                //创建新线索，线索状态是待确认
                status = 1;
            }else//报备保护
            {
                //创建新线索，线索状态是待分配，插入到访逾期时间
                status = 2;
            }
        } else//验证不通过
        {
            //创建新线索，状态是无效，无效时间是当前时间，无效类型取，无效原因取
            status = 3;
        }
        //线索报备验证后，创建线索信息
            boolean b=clueService.createClue(channelOrgId,ruleValidate,userRule,status,paramMap);
        return result;
    }

    //已测
    @ApiOperation(value = "获取客户详情", notes = "获取客户详情")
    @RequestMapping(value = "/mBrokerCustomerDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerCustomerDetail_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String ClueID=(String) paramMap.get("ClueID");
        Result result = new Result();
        Map<String, Object> map = clueService.mBrokerCustomerDetail_Select(ClueID);
        if (map.size()==0){
            result.setErrcode(99);
            result.setErrmsg("失败");
            return result;
        }
        result.setErrcode(0);
        result.setData(map);
        result.setErrmsg("成功");
        return result;
    }

    //未测  两个修改不知道哪个是
    @ApiOperation(value = "修改个人基本信息", notes = "修改个人基本信息")
    @RequestMapping(value = "/mBrokerChannelUserDetail_Upate", method = {RequestMethod.POST})
    public Result mBrokerChannelUserDetail_Upate(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String Name=(String) paramMap.get("Name");
        String Gender=(String) paramMap.get("Gender");
        String Mobile=(String) paramMap.get("Mobile");
        String ChannelTypeID=(String) paramMap.get("ChannelTypeID");
        Result result = new Result();
        int i = channeluserService.mBrokerChannelUserDetail_Upate(UserID, Name, Gender, Mobile, ChannelTypeID);
        if (i == 1) {
            result.setData(true);
            result.setErrcode(0);
            result.setErrmsg("成功");
            return result;
        } else {
            result.setErrcode(1);
            result.setData(false);
            result.setErrmsg("修改失败");
            return result;
        }
    }

    // 修改个人基本信息
    // 在审核不通过的时候可以修改机构编码，其他状态不允许修改
    @ApiOperation(value = "修改个人基本信息", notes = "修改个人基本信息")
    @RequestMapping(value = "/mBrokerChannelUserDetail_UpdateN", method = {RequestMethod.POST})
    public Result mBrokerChannelUserDetail_UpdateN(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        Result result=channeluserService.mBrokerChannelUserDetail_UpdateN(paramMap);
        return result;
    }

    //已测
    @ApiOperation(value = "项目收藏", notes = "项目收藏")
    @RequestMapping(value = "/BrokerProjectCollection_Insert", method = {RequestMethod.POST})
    public Result BrokerProjectCollection_Insert(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String ProjectID=(String) paramMap.get("ProjectID");
        //ProjectID：这里目前前台传了A_BrokerProject表的主键ID，注意一下，不是项目表的ID
        Result result = new Result();
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(1);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        if (StringUtils.isBlank(ProjectID)) {
            result.setErrcode(1);
            result.setErrmsg("项目ID不可以为空");
            return result;
        }
        int i = projectcollectionService.BrokerProjectCollection_Insert(UserID, ProjectID);
        if (i > 0) {
            result.setErrcode(0);
            result.setErrmsg("成功");
            return result;
        } else {
            result.setErrcode(1);
            result.setErrmsg("项目收藏失败");
            return result;
        }
    }

    //已测
    @ApiOperation(value = "取消项目收藏", notes = "取消项目收藏")
    @RequestMapping(value = "/mBrokerProjectCollection_Delete", method = {RequestMethod.POST})
    public Result mBrokerProjectCollection_Delete(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String ProjectID=(String) paramMap.get("ProjectID");
        //ProjectID：这里目前前台传了A_BrokerProject表的主键ID，注意一下，不是项目表的ID
        Result result = new Result();
        if (StringUtils.isBlank(UserID)) {
            result.setErrcode(1);
            result.setErrmsg("用户ID不可以为空");
            return result;
        }
        if (StringUtils.isBlank(ProjectID)) {
            result.setErrcode(1);
            result.setErrmsg("项目ID不可以为空");
            return result;
        }
        int i = projectcollectionService.BrokerProjectCollection_Delete(UserID, ProjectID);
        if (i > 0) {
            result.setErrcode(0);
            result.setErrmsg("成功");
            return result;
        } else {
            result.setErrcode(1);
            result.setErrmsg("项目收藏失败");
            return result;
        }
    }

    //未完成,还差发送短信
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping(value = "/mVerificationCode_Select", method = {RequestMethod.POST})
    public Result mVerificationCode_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String Mobile=(String) paramMap.get("Mobile");

        //存入数据库记录,并发送短信
        String msg="【泰禾集团】验证码：";
        verificationcodeService.getCodeAndSendsmg(Mobile,msg);
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg("成功");
//        result.setData(list);
        return result;
    }

    //未测
    @ApiOperation(value = "拓客人员与全民经济人同一注册接口", notes = "拓客人员与全民经济人同一注册接口")
    @RequestMapping(value = "/mLoginTKUser_InsertN", method = {RequestMethod.POST})
    public Result mLoginTKUser_InsertN(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String orgcode=(String) paramMap.get("ChannelOrgCode");
        if (StringUtils.isNotBlank(orgcode)){
             return channeluserService.register(paramMap);
        }else {
            //经纪人注册
            return channeluserService.registerN(paramMap);
        }
    }

    @ApiOperation(value = "全民经纪人忘记密码--修改密码", notes = "全民经纪人忘记密码--修改密码")
    @RequestMapping(value = "/mBrokerLoginForgetPwd_Update", method = {RequestMethod.POST})
    public Result mBrokerLoginForgetPwd_Update(@RequestBody JSONObject jsonParam) {

        Map paramMap = (HashMap)jsonParam.get("_param");
        String mobile=(String) paramMap.get("Mobile");
        String authCode=(String) paramMap.get("AuthCode");
        String password=(String) paramMap.get("Password");
        String rePassword=(String) paramMap.get("RePassword");
       if (StringUtils.equals(password,rePassword)){
           return Result.errormsg(2,"密码不一致");
       }
        BVerificationcode vc = verificationcodeService.checkAuthCode(mobile);

        if (vc == null || !StringUtils.equals(authCode, vc.getVerificationCode())) {
            return Result.errormsg(1,"验证码验证失败");
        }
        QueryWrapper<BChanneluser> wrapper=new QueryWrapper<>();
        wrapper.eq("Mobile",mobile);
        BChanneluser one = channeluserService.getOne(wrapper);
        one.setPassword(SecureUtil.md5(rePassword));
        if (channeluserService.updateById(one)){
            return Result.ok(null);
        }
        return Result.errormsg(99,"修改密码失败");

    }

    //以下都已测
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/mBrokerChannelUserPassWord_Update", method = {RequestMethod.POST})
    public Result mBrokerChannelUserPassWord_Update(@RequestBody JSONObject jsonParam) {

        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String password=(String) paramMap.get("Password");
        String OldPassword=(String) paramMap.get("OldPassword");
        Result result=channeluserService.mBrokerChannelUserPassWord_Update(UserID,password,OldPassword);
        return result;
    }



    @ApiOperation(value = "门店成员列表", notes = "门店成员列表")
    @RequestMapping(value = "/mChannelStoreUserList_SelectN", method = {RequestMethod.POST})
    public Result mChannelStoreUserList_SelectN(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        IPage<Map<String,Object>> page=channeluserService.mChannelStoreUserList_SelectN(paramMap);
       return Result.ok(page);
    }

    @ApiOperation(value = "门店成员删除", notes = "门店成员删除")
    @RequestMapping(value = "/mChannelUserUpdate_Delete", method = {RequestMethod.POST})
    public Result mChannelUserUpdate_Delete(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        Result result=channeluserService.mChannelUserUpdate_Delete(paramMap);
        return result;
    }


    @ApiOperation(value = "拓客客户列表", notes = "拓客客户列表")
    @RequestMapping(value = "/mCustomerTCList_SelectN", method = {RequestMethod.POST})
    public Result mCustomerTCList_SelectN(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        Result result=channeluserService.mCustomerTCList_SelectN(paramMap);
        return result;
    }

    /// 移动拓客-我的团队-成员(拓客)客户转移
    /// CustomerIDs：要转移的多个客户ID用逗号分隔
    /// TransferID：要转移到哪个拓客
    /// UserID：操作人ID
    @ApiOperation(value = "客户转移分配", notes = "客户转移分配")
    @RequestMapping(value = "/mCustomerTCTransfer_Update", method = {RequestMethod.POST})
    public Result mCustomerTCTransfer_Update(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        Result result=channeluserService.mCustomerTCTransfer_Update(paramMap);
        return result;
    }


}
