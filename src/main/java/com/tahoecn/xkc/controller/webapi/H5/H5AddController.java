package com.tahoecn.xkc.controller.webapi.H5;


import cn.hutool.core.collection.CollectionUtil;
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

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@RestController
@RequestMapping("/webapp")
public class H5AddController extends TahoeBaseController {

    @Autowired
    private IBProjectService projectService;
    @Autowired
    private IBChanneluserService channeluserService;
    @Autowired
    private IVABrokerMycustomersService mycustomersService;
    @Autowired
    private ISystemMessageService messageService;
    @Autowired
    private IBClueService clueService;
    @Autowired
    private ISFormsessionService iSFormsessionService;

    @Autowired
    private IBChannelService iBChannelService;

    @Autowired
    private ISAccountService accountService;

    @Autowired
    private BCustomerpotentialMapper bCustomerpotentialMapper;

    @Autowired
    private ICustomerHelp customerTemplate;

    @Value("${tahoe.application.physicalPath}")
    private  String physicalPath;

    @Value("${registerUrl}")
    private  String registerUrl;


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

            list =projectService.findAllProject(Name,PageIndex,PageSize);
        }

        return Result.ok(list);
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

//  如果要修改逻辑,请将H5Controller里的推荐提交一起看,逻辑相同,确认是否需要更改
    //如果要修改逻辑,请将下面 /kfMBrokerReport_Insert 接口逻辑一起修改
    @ApiOperation(value = "渠道报备客户--推荐提交", notes = "渠道报备客户--提交")
    @RequestMapping(value = "/mBrokerReport_Insert", method = {RequestMethod.POST})
    public Result mBrokerReport_Insert(@RequestBody JSONObject jsonParam) {
        Result re=new Result();
        Map paramMap = (HashMap)jsonParam.get("_param");
        String userID=(String) paramMap.get("UserID");
        String userName=(String) paramMap.get("UserName");
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
            return  Result.errormsg(1,"不能重复请求！");
        }
        //判断身份 是否有自渠和置业顾问 ,如果有,不可报备,返回错误信息
        Result jobByUsername=accountService.getJobByUserName(userID);
        if (jobByUsername.getErrcode()!=0){
            return jobByUsername;
        }

        if (StringUtils.isBlank(adviserGroupID)){
            return  Result.errormsg(1,"未能识别报备人的身份");
        }
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

        ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(userID, adviserGroupID, projectId);
        if (org.springframework.util.StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
            return Result.errormsg(21, "报备失败！所选项目未配置报备规则，请联系管理员！");
        }

        //验证报备客户是否有效
        Map<String, Object> CustomerValidate = iBChannelService.ValidateForReport(mobile, projectId,channelRegisterModel);

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


    @ApiOperation(value = "kf渠道报备客户--推荐提交", notes = "kf渠道报备客户--提交")
    @RequestMapping(value = "/kfMBrokerReport_Insert", method = {RequestMethod.POST})
    public Result kfMBrokerReport_Insert(@RequestBody JSONObject jsonParam) {
        Result re=new Result();
        Map paramMap = (HashMap)jsonParam.get("_param");
        String userID=(String) paramMap.get("UserID");
        String userName=(String) paramMap.get("UserName");
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
            return  Result.errormsg(1,"不能重复请求！");
        }
        //判断身份 是否有自渠和置业顾问 ,如果有,不可报备,返回错误信息
        Result jobByUsername=accountService.getJobByUserName(userID);
        if (jobByUsername.getErrcode()!=0){
            return jobByUsername;
        }

        if (StringUtils.isBlank(adviserGroupID)){
            return  Result.errormsg(1,"未能识别报备人的身份");
        }
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

        ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(userID, adviserGroupID, projectId);
        if (org.springframework.util.StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
            return Result.errormsg(21, "报备失败！所选项目未配置报备规则，请联系管理员！");
        }

        //验证报备客户是否有效
        Map<String, Object> CustomerValidate = iBChannelService.ValidateForReport(mobile, projectId,channelRegisterModel);

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
            boolean b = false;
			try {
				b = clueService.kfCreateClue(channelOrgId,CustomerValidate,channelRegisterModel.getUserRule(),status,paramMap,1);
			} catch (Exception e) {
				e.printStackTrace();
				re.setErrcode(1);
                re.setErrmsg(e.getMessage());
                return re;
			}
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

    @ApiOperation(value = "获取用户信息或创建", notes = "获取用户信息或创建")
    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.POST})
    public Result getUserInfo(@RequestBody JSONObject jsonParam) {
        Map<String, Object> paramMap = (HashMap<String, Object>)jsonParam.get("_param");
        Result user=channeluserService.getUserInfo(paramMap,request,response);
        return user;
    }



}
