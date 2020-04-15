package com.tahoecn.xkc.controller.webapi.H5;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import com.tahoecn.xkc.common.utils.JwtTokenUtil;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.common.utils.PhoneUtil;
import com.tahoecn.xkc.common.utils.QRCodeUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.model.sys.SFormsession;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IVABrokerMycustomersService;
import com.tahoecn.xkc.service.customer.impl.PotentialCustomerServiceImpl;
import com.tahoecn.xkc.service.project.IABrokerprojectService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IBProjectcollectionService;
import com.tahoecn.xkc.service.rule.IBClueruleService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISFormsessionService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import com.tahoecn.xkc.service.uc.CsSendSmsLogService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
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
    private ISFormsessionService iSFormsessionService;
    @Autowired
    private IBClueruleService clueruleService;

    @Autowired
    private IBVerificationcodeService iBVerificationcodeService;
    @Autowired
    private CsSendSmsLogService csSendSmsLogService;

    @Autowired
    private IBChannelService iBChannelService;

    @Autowired
    private IBChannelorgService channelorgService;

    @Autowired
    private BCustomerpotentialMapper bCustomerpotentialMapper;

    @Autowired
    private ICustomerHelp customerTemplate;

    @Autowired
    private ISAccountService accountService;

    @Value("${tahoe.application.physicalPath}")
    private  String physicalPath;

    @Value("${registerUrl}")
    private  String registerUrl;

    @Autowired
    private PotentialCustomerServiceImpl potentialCustomerService;

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
    @ApiOperation(value = "获取房源项目列表", notes = "获取房源项目列表")
    @RequestMapping(value = "/mBrokerProjectList_SelectN", method = {RequestMethod.POST})
    public Result mBrokerProjectList_SelectN(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String OrgID=(String) paramMap.get("OrgID");
        String Name=(String) paramMap.get("Name");
//        String CityID=(String) paramMap.get("CityID");
        int PageIndex=(int) paramMap.get("PageIndex");
        int PageSize=(int) paramMap.get("PageSize");
        //获取是否是机构的人
        //不为空表示登录进来的肯定是机构的人
        Map<String,Object> list;

        if (StringUtils.isNotBlank(OrgID)) {
            list = projectService.findByOrgID(OrgID,Name,PageIndex,PageSize);
        } else {
//            list = projectService.ProjectInfoList_SelectN(page,Name, CityID);
//            list = projectService.findByOrgID(OrgID,Name,PageIndex,PageSize);
            list =projectService.findAllProject(Name,PageIndex,PageSize);
        }
//        List<Map<String, Object>> resultList=projectService.addName(list);
//        Map<String, Object> map=new HashMap<>();
//        map.put("List",resultList);
//        Result result = new Result();
//        result.setErrcode(0);
//        result.setErrmsg("成功");
//        result.setData(map);
        return Result.ok(list);
    }

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

    @CrossOrigin
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


    @CrossOrigin
    @ApiOperation(value = "登录账号", notes = "登录账号")
    @RequestMapping(value = "/mLoginTK_SelectN", method = {RequestMethod.POST})
    public Result mLoginTK_SelectN(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserName=(String) paramMap.get("UserName");
        String Password=(String) paramMap.get("Password");
        String MobileNum = (String) paramMap.get("MobileNum");
        String Code = (String) paramMap.get("Code");
        //手机验证码登录
        if (StringUtils.isNotEmpty(MobileNum)) {
            if (StringUtils.isEmpty(Code)){
                return Result.errormsg(1, "请输入验证码");
            }
            BVerificationcode vc = verificationcodeService.checkAuthCode(MobileNum);
            if (vc == null || !StringUtils.equals(Code, vc.getVerificationCode())) {
                return Result.errormsg(1, "验证码验证失败");
            }
            paramMap.put("Mobile",MobileNum);
        }
        Object mobile = paramMap.get("Mobile");

        Map<String, Object> map;
        if (mobile!=null) {
            map = channeluserService.ChannelUserCurrency_Find((String) mobile);
        } else {
            //channelTypeID是写死的
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
        Object ChannelOrgID=user.get("ChannelOrgID");
        if (ChannelOrgID==null||ChannelOrgID.equals("")){
            //非分销中介 自由经纪正常登陆
            //是老业主
            if (user.get("ChannelTypeID")!=null&&StringUtils.equals((String)user.get("ChannelTypeID"),"EB4AD331-F4AD-46D6-889A-D45575ECEE66")){
                return Result.errormsg(1,"认证失败!老业主请前往泰禾客户服务中心微信公众号推荐客户");
            }
            //是泰禾员工   为泰禾员工的 有ChannelOrgID
            if (user.get("ChannelTypeID")!=null&&StringUtils.equals((String)user.get("ChannelTypeID"),"725FA5F6-EC92-4DC6-8D47-A8E74B7829AD")){
                return Result.errormsg(1,"认证失败!泰禾员工请前往泰信员工推荐中推荐客户");
            }
        }


        //获取机构及其下属所有机构名和机构id
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
        List<Map<String,Object>> list= channelorgService.getChildOrg(id);
        if (list!=null){
            user.put("ChannelorgList",list);
        }
        BChannelorg channelOrg = channelorgService.getById((String) user.get("ChannelOrgID"));
        if(channelOrg != null){
            Short job=(Short)user.get("Job");
            if (StringUtils.equals("0",job==null?"":job.toString())){
                user.put("position","负责人");
            }else {
                user.put("position","经纪人");
            }
        }else{
            user.put("position","经纪人");
        }
        Object channelType = user.get("ChannelType");
        if (channelType!=null){
            if (StringUtils.equals((String)channelType,"中介同行")){
                user.put("ChannelType","分销中介");
            }
        }
        String token = JwtTokenUtil.createToken((String) user.get("UserID"), (String) user.get("UserName"), false);
        //放到响应头部
//        response.setHeader(JwtTokenUtil.TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);
        user.put("token",JwtTokenUtil.TOKEN_PREFIX+token);
        return Result.ok(user);
    }


    @Deprecated
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

    @CrossOrigin(methods = RequestMethod.POST,allowCredentials = "true")
    @ApiOperation(value = "获取个人中心的统计数据2", notes = "获取个人中心的统计数据")
    @RequestMapping(value = "/mBrokerMyCenter_SelectNew", method = {RequestMethod.POST})
    public Result mBrokerMyCenter_SelectNew(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String BrokerID=(String) paramMap.get("BrokerID");
        Map<String, Object> map = channeluserService.BrokerMyCenter_Select(BrokerID);
        return Result.ok(map);
    }


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
        if (StringUtils.equals(Filter,"到访")){
            Filter="来访";
        }
        IPage<Map<String, Object>> list = mycustomersService.mGetMyCustomers_Select(page, Sort, Filter, CustomerInfo, BrokerID);

        return Result.ok(list);
    }

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

    @ApiOperation(value = "查询个人证件信息", notes = "查询个人证件信息")
    @RequestMapping(value = "/mBrokerChannelUserCardDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerChannelUserCardDetail_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        QueryWrapper<BChanneluser> wrapper=new QueryWrapper<>();
        wrapper.eq("ID",UserID).eq("IsDel",0).eq("Status",1);
        BChanneluser one = channeluserService.getOne(wrapper);
        if (one!=null){
            return Result.ok(one);
        }
        return Result.errormsg(1,"加载用户个人信息失败");
    }

    @ApiOperation(value = "修改个人证件信息", notes = "修改个人证件信息")
    @RequestMapping(value = "/mBrokerChannelUserCardDetail_Update", method = {RequestMethod.POST})
    public Result mBrokerChannelUserCardDetail_Update(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        String CertificatesName=(String) paramMap.get("CertificatesName");
        String CertificatesType=(String) paramMap.get("CertificatesType");
        String CertificatesNo=(String) paramMap.get("CertificatesNo");
        String CertificatesPicFace=(String) paramMap.get("CertificatesPicFace");
        if (!StringUtils.equals(CertificatesPicFace.substring(0,17),"/ncs/uploadfiles/")){
            CertificatesPicFace="/ncs/uploadfiles"+CertificatesPicFace;
        }
        String CertificatesPicBack=(String) paramMap.get("CertificatesPicBack");
        if (!StringUtils.equals(CertificatesPicBack.substring(0,17),"/ncs/uploadfiles/")){
            CertificatesPicBack="/ncs/uploadfiles"+CertificatesPicBack;
        }
        int i = channeluserService.mBrokerChannelUserCardDetail_Update(UserID, CertificatesName, CertificatesType, CertificatesNo, CertificatesPicFace, CertificatesPicBack);
       if(i==1){
           return Result.ok("成功");
       }
        return Result.errormsg(1,"修改失败");
    }

    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/mMessageAllList_Select", method = {RequestMethod.POST})
    public Result mMessageAllList_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String UserID=(String) paramMap.get("UserID");
        int PageIndex=(int) paramMap.get("PageIndex");
        int PageSize=(int) paramMap.get("PageSize");
        IPage page = new Page(PageIndex, PageSize);
        IPage<Map<String, Object>> list = messageService.mMessageAllList_Select(page, UserID);
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

    @ApiOperation(value = "生成FormSessionID", notes = "生成FormSessionID")
    @RequestMapping(value = "/mSystemFormSession_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mSystemFormSession_Insert(@RequestBody JSONObject paramAry) {
        try{
            Map paramMap = (HashMap)paramAry.get("_param");
            SFormsession sess = new SFormsession();
            sess.setId(UUID.randomUUID().toString().toUpperCase());
            sess.setIp(NetUtil.getRemoteAddr(request));
            sess.setData(JSONObject.toJSONString(paramMap));
            sess.setCreateTime(new Date());
            sess.setStatus(1);
            iSFormsessionService.save(sess);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("FormSessionID", sess.getId());
            return Result.ok(map);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.errormsg(1,"系统异常，请联系管理员");
        }
    }


    @ApiOperation(value = "渠道报备客户--推荐提交", notes = "渠道报备客户--提交")
    @RequestMapping(value = "/mBrokerReport_Insert", method = {RequestMethod.POST})
    public Result mBrokerReport_Insert(@RequestBody JSONObject jsonParam) {
        Result re=new Result();
        Map paramMap = (HashMap)jsonParam.get("_param");
        String userID=(String) paramMap.get("UserID");
        String mobile=(String) paramMap.get("Mobile");
        String projectId =(String) paramMap.get("IntentProjectID");
        String formSessionID =(String) paramMap.get("FormSessionID");
        String adviserGroupID =(String) paramMap.get("AdviserGroupID");
        //验证是否重复请求
        if (StringUtils.isBlank(formSessionID)){
            return Result.errormsg(1,"FormSessionID不可为null");
        }
        //验证手机号格式
        if (PhoneUtil.isNotValidChinesePhone(mobile)){
            return Result.errormsg(1,"手机号格式错误");
        }
        //查询是否已经存在无效的FormSessionID,不存在则更新为无效状态
        int RowCount =iSFormsessionService.checkFormSessionID(formSessionID);
        if (RowCount==1){
            return Result.errormsg(1,"不能重复请求！");
        }
        if (StringUtils.isBlank(adviserGroupID)){
            return Result.errormsg(1,"未能识别报备人的身份");
        }

//        //判断身份 是否有自渠和置业顾问 ,如果有,不可报备,返回错误信息
//        Result jobByUsername=accountService.getJobByUserName(userID);
//        if (jobByUsername.getErrcode()!=0){
//            return jobByUsername;
//        }

        //1.不允许报备自己 0.允许报备自己  IsReportOwn
        int IsReportOwn=projectService.isReport(projectId,userID,mobile);
        if (IsReportOwn==1){
            return  Result.errormsg(1,"不允许报备自己");
        }
        //没有ChannelOrgID 的不能报备
        int IsReport=channeluserService.isReport(userID);
        //没有ChannelOrgID 的不能报备
        if (IsReport==-2){
            return  Result.errormsg(1,"没有所属机构的人员，不能报备");
        }
        //ChannelOrgID禁用状态的不能报备
        if (IsReport==0){
            return  Result.errormsg(1,"所属机构在禁用状态，不能报备");
        }
        //获取报备用户所适用的规则

//        Map<String,Object> userRule=clueruleService.getRegisterRule(projectId,adviserGroupID);
        ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(userID, adviserGroupID, projectId);
        if (org.springframework.util.StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
            return Result.errormsg(21, "报备失败！所选项目未配置报备规则，请联系管理员！");
        }

//        if (userRule.get("RuleID")==null){
        //验证报备客户是否有效
        Map<String, Object> CustomerValidate = iBChannelService.ValidateForReport(mobile, projectId,channelRegisterModel);

//        Map<String, Object> ruleValidate = clueService.ValidateForReport(userID, mobile, projectId, userRule);
        String channelOrgId=channeluserService.getChannelOrgID(userID,adviserGroupID);
        Map<String, Object> userRule=new HashMap<>();
        userRule.put("RuleType",channelRegisterModel.getUserRule().getRuleType());
        userRule.put("ValidationMode",channelRegisterModel.getUserRule().getImmissionRule().getValidationMode());
        String msg=clueService.getMessage((int)CustomerValidate.get("InvalidType"),userRule);
        CustomerValidate.put("Message",msg);
        String reMsg = iBChannelService.GetMessageForReturn((int)CustomerValidate.get("InvalidType"), channelRegisterModel.getUserRule());
        re.setErrmsg(reMsg);
        //允许报备
        if ((int)CustomerValidate.get("InvalidType")==0){
//        ruleValidate.put("Message",msg);
//        String errMsg=clueService.GetMessageForReturn((int)ruleValidate.get("InvalidType"),userRule);
        //通过有效验证
        int status = 0;

        if ((boolean)CustomerValidate.get("Tag")){
            //竞争带看
            if (channelRegisterModel.getUserRule().getRuleType() == 1 ){
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
            boolean b=clueService.createClue(channelOrgId,CustomerValidate,channelRegisterModel.getUserRule(),status,paramMap);
        if (reMsg!=null){
            re.setErrmsg(reMsg);
        }
        if (b){
            re.setErrcode(0);
            return re;
        }else {
            re.setErrcode(1);
            re.setErrmsg("存储错误,请联系管理员");
            return re;
        }

        }else {//不允许报备

                //存在销售机会且SaleUserID不为空 发送报备失败消息
                BChanneluser byId = channeluserService.getById(userID);
                Map<String, Object> obj = new HashMap<String, Object>();
                obj.put("ProjectID", projectId);
                obj.put("CustomerMobile", mobile);
                Map<String,Object> opp = bCustomerpotentialMapper.ValidOpp_Select(obj);
                if (CollectionUtil.isNotEmpty(opp)){
                    customerTemplate.sendBBSBMsg((String) opp.get("ID"),byId.getName(), userID);
                }

            re.setErrcode(1);
            return re;
        }
    }

    @ApiOperation(value = "获取客户详情", notes = "获取客户详情")
    @RequestMapping(value = "/mBrokerCustomerDetail_Select", method = {RequestMethod.POST})
    public Result mBrokerCustomerDetail_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String ClueID=(String) paramMap.get("ClueID");
        Result result = new Result();
        Map<String, Object> map = clueService.mBrokerCustomerDetail_Select(ClueID);
        if (map==null||map.size()==0){
            result.setErrcode(99);
            result.setErrmsg("失败");
            return result;
        }
        result.setErrcode(0);
        result.setData(map);
        result.setErrmsg("成功");
        return result;
    }

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

    @ApiOperation(value = "上传")
    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST},headers="content-type=multipart/form-data")
    public Result uploadFile(@RequestParam("file") MultipartFile[] file){
        String path = uploadFiles(file);
        return Result.ok(path);
    }

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

    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping(value = "/mVerificationCode_Select", method = {RequestMethod.POST})
    public Result mVerificationCode_Select(@RequestBody JSONObject jsonParam) {
        try{
            Map paramMap = (HashMap)jsonParam.get("_param");
            String Mobile = (String)paramMap.get("Mobile").toString();
            if (PhoneUtil.isNotValidChinesePhone(Mobile)){
                return Result.errormsg(1,"手机号格式错误");
            }
            Random ran = new Random();
            int verificationCode = ran.nextInt(899999)+100000;
            //写入数据,并调取短信接口
            Map<String,Object> parameter = new HashMap<String,Object>();
            parameter.put("Mobile", Mobile);
            parameter.put("VerificationCode", String.valueOf(verificationCode));
            iBVerificationcodeService.Detail_Add(parameter);
            //redis缓存验证码
//            redisTemplate.opsForValue().set(key, value , 10 , TimeUnit.MINUTES);
            csSendSmsLogService.sendSms(Mobile,"【泰禾集团】验证码："+String.valueOf(verificationCode)+"，5分钟内有效","");
            return Result.ok(String.valueOf(verificationCode));
        }catch (Exception e) {
            e.printStackTrace();
            return Result.errormsg(1,"系统异常，请联系管理员");
        }
    }

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
       if (!StringUtils.equals(password,rePassword)){
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
            return Result.okm("成功");
        }
        return Result.errormsg(99,"修改密码失败");

    }

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

    @ApiOperation(value = "审核", notes = "审核")
    @RequestMapping(value = "/mChannelStoreUserApproval_Update", method = {RequestMethod.POST})
    public Result mChannelStoreUserApproval_Update(@RequestBody JSONObject jsonParam) {
        Map<String,Object> paramMap = (HashMap)jsonParam.get("_param");
        Result result=channeluserService.mChannelStoreUserApproval_Update(paramMap);
        return result;
    }

    @ApiOperation(value = "获取小程序码", notes = "获取小程序码")
    @RequestMapping(value = "/getRqCode", method = {RequestMethod.POST})
    public Result getRqCode() {
        Result result=channeluserService.getRqCode();
        return result;
    }

    @ApiOperation(value = "获取小程序码路径", notes = "获取小程序码路径")
    @RequestMapping(value = "/getRqCodeUrl", method = {RequestMethod.POST})
    public Result getRqCodeUrl() {
        StringBuilder sb=new StringBuilder();
        sb.append("rqcodeImg").append("/twoCode.png");
        return Result.okm(sb.toString());
    }



    @ApiOperation(value = "生成二维码", notes = "生成二维码")
    @RequestMapping(value = "/getTwoCode", method = {RequestMethod.POST})
    public Result getTwoCode(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String Code = (String) paramMap.get("Code");
        String ChannelTypeID = (String) paramMap.get("ChannelTypeID");
        String OrgID = registerUrl+ChannelTypeID+"&Code="+Code;
        String url = QRCodeUtil.zxingCodeCreate(OrgID,physicalPath,"twoCode/", 500, null);
        if (url==null){
            return Result.errormsg(1,"生成失败");
        }
        return Result.ok(url);
    }


    @ApiOperation(value = "客户信息二维码", notes = "客户信息二维码")
    @RequestMapping(value = "/mCustomerQRCode_Select", method = {RequestMethod.POST})
    public Result mCustomerQRCode_Select(@RequestBody JSONObject jsonParam) {
        Map paramMap = (HashMap)jsonParam.get("_param");
        String TokerUserID = (String) paramMap.get("TokerUserID");
        String ClueMobile = (String) paramMap.get("ClueMobile");
        String ClueID = (String) paramMap.get("ClueID");
        String SourceType = (String) paramMap.get("SourceType");
        String name = "{\"TokerUserID\":\"" + TokerUserID + "\",\"ClueMobile\":\"" + ClueMobile
                + "\",\"ClueID\":\"" + ClueID + "\",\"SourceType\":\"" + SourceType + "\"}";
        String url = QRCodeUtil.zxingCodeCreate(name,physicalPath,"CustomerQRCode/", 500, null);
        if (url==null){
            return Result.errormsg(1,"生成失败");
        }
        return Result.ok(url);
    }

    @ApiOperation(value = "区域项目列表", notes = "区域项目列表")
    @RequestMapping(value = "/ProjectList_Select", method = {RequestMethod.POST})
    public Result ProjectList_Select(@RequestBody JSONObject jsonParam) {
        Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
        Object Name = paramMap.get("Name");
        List<Map<String,Object>> list;
        if (Name!=null){
            String key= (String) Name;
            list=projectService.ProjectList_Select(key,null);
        }else {
            list=projectService.ProjectList_Select(null,null);
        }
        return Result.ok(list);
    }

//    @ApiOperation(value = "获取用户信息或创建", notes = "获取用户信息或创建")
//    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.POST})
//    public Result getUserInfo(@RequestBody JSONObject jsonParam) {
//        Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
//        Result user=channeluserService.getUserInfo(paramMap);
//        return user;
//    }


}
