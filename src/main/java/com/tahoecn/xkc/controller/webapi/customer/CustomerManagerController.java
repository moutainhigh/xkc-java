package com.tahoecn.xkc.controller.webapi.customer;


import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.landray.sso.client.oracle.StringUtil;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.common.utils.SqlInjectionUtil;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerManagerMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.customer.BCustomer;
import com.tahoecn.xkc.model.customer.BCustomerattribute;
import com.tahoecn.xkc.model.customer.UpdateCustinfoLog;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.service.customer.IBCustomerManagerService;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import com.tahoecn.xkc.service.customer.IBCustomerattributeService;
import com.tahoecn.xkc.service.customer.IUpdateCustinfoLogService;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
@Api(tags = "客户")
@RestController
@RequestMapping("/webapi/customermanager")
public class CustomerManagerController extends TahoeBaseController {
    private static final Log log = LogFactory.get();

    @Resource
    private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;

    @Autowired
    private IBCustomerService customerService;

    @Autowired
    private IBCustomerManagerService customerManagerService;

    @Autowired
    private IBSalesgroupService iBSalesgroupService;

    @Autowired
    private IBOpportunityService iBOpportunityService;

    @Autowired
    private BCustomerManagerMapper bCustomerManagerMapper;

    @Autowired
    private IUpdateCustinfoLogService iUpdateCustinfoLogService;

    @Autowired
    private IBCustomerattributeService iBCustomerattributeService;


    @ApiOperation(value = "客户管理列表")
    @RequestMapping(value = "/CustomerManagePageList_Select", method = {RequestMethod.GET})
    public Result CustomerManagePageList_Select(String ProjectID,
                                                    String IsExcel,
                                                    Long PageSize,
                                                    Long PageIndex,
                                                    String UserID,
                                                    String CustomerRank,
                                                    String Status,
                                                    String SourceType,
                                                    String SaleTeamID,
                                                    String CustomerName,
                                                    String CustomerMobile,
                                                    String ReportUserName,
                                                    String ReportUserMobile,
                                                    String ChannelName,
                                                    String SaleUserName,
                                                    String CreateTime_Start,
                                                    String CreateTime_End,
                                                    String TheFirstVisitDate_Start,
                                                    String TheFirstVisitDate_End,
                                                    String ZJDF_Start,
                                                    String ZJDF_End,
                                                    String TheLatestFollowUpDate_Start,
                                                    String TheLatestFollowUpDate_End,
                                                    String type,String uType) {

        // 防sql注入过滤
        ProjectID = SqlInjectionUtil.filter(ProjectID);
        IsExcel = SqlInjectionUtil.filter(IsExcel);
        UserID = SqlInjectionUtil.filter(UserID);
        CustomerRank = SqlInjectionUtil.filter(CustomerRank);
        Status = SqlInjectionUtil.filter(Status);
        SourceType = SqlInjectionUtil.filter(SourceType);
        SaleTeamID = SqlInjectionUtil.filter(SaleTeamID);
        CustomerName = SqlInjectionUtil.filter(CustomerName);
        CustomerMobile = SqlInjectionUtil.filter(CustomerMobile);
        ReportUserName = SqlInjectionUtil.filter(ReportUserName);
        ReportUserMobile = SqlInjectionUtil.filter(ReportUserMobile);
        ChannelName = SqlInjectionUtil.filter(ChannelName);
        SaleUserName = SqlInjectionUtil.filter(SaleUserName);
        CreateTime_Start = SqlInjectionUtil.filter(CreateTime_Start);
        CreateTime_End = SqlInjectionUtil.filter(CreateTime_End);
        TheFirstVisitDate_Start = SqlInjectionUtil.filter(TheFirstVisitDate_Start);
        TheFirstVisitDate_End = SqlInjectionUtil.filter(TheFirstVisitDate_End);
        ZJDF_Start = SqlInjectionUtil.filter(ZJDF_Start);
        ZJDF_End = SqlInjectionUtil.filter(ZJDF_End);
        TheLatestFollowUpDate_Start = SqlInjectionUtil.filter(TheLatestFollowUpDate_Start);
        TheLatestFollowUpDate_End = SqlInjectionUtil.filter(TheLatestFollowUpDate_End);
        type = SqlInjectionUtil.filter(type);
        uType = SqlInjectionUtil.filter(uType);

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("ProjectID",ProjectID);
        map.put("IsExcel",IsExcel);
        map.put("PageSize",PageSize);
        map.put("PageIndex",PageIndex);
        map.put("UserID",UserID);

        map.put("CustomerRank",CustomerRank);
        map.put("Status",Status);
        map.put("SourceType",SourceType);
        map.put("SaleTeamID",SaleTeamID);
        map.put("CustomerName",CustomerName);
        map.put("CustomerMobile",CustomerMobile);
        map.put("ReportUserName",ReportUserName);
        map.put("ReportUserMobile",ReportUserMobile);
        map.put("ChannelName",ChannelName);
        map.put("SaleUserName",SaleUserName);
        map.put("CreateTime_Start",CreateTime_Start);
        map.put("CreateTime_End",CreateTime_End);
        map.put("TheFirstVisitDate_Start",TheFirstVisitDate_Start);
        map.put("TheFirstVisitDate_End",TheFirstVisitDate_End);
        map.put("ZJDF_Start",ZJDF_Start);
        map.put("ZJDF_End",ZJDF_End);
        map.put("TheLatestFollowUpDate_Start",TheLatestFollowUpDate_Start);
        map.put("TheLatestFollowUpDate_End",TheLatestFollowUpDate_End);
        map.put("type",type);
        map.put("uType",uType);

        //获取团队
        QueryWrapper<BSalesgroup> salesgroupWrapper = new QueryWrapper<>();
        salesgroupWrapper.eq("ProjectID", ProjectID);
        salesgroupWrapper.eq("IsDel", 0);
        salesgroupWrapper.eq("Status", 1);
        salesgroupWrapper.eq("ProjectID", ProjectID);
        salesgroupWrapper.ne("Name", "");

        /*
        {"_datatype":"text","_param":{"ProjectID":"adc0d952-63b5-e711-80c7-00505686c900","IsExcel":"1","PageSize":"6","PageIndex":1,"UserID":"6C883173-489C-47A4-97D2-3601CB7CEDFD"}}:
         */

        if (StringUtils.isNotEmpty(IsExcel)){
            SetExcelToCustomerChange_BakList(map);
            return null;
        }

        List<BSalesgroup> salesgroupList = iBSalesgroupService.list(salesgroupWrapper);
        result.put("salesgroupList", salesgroupList);

        List<Map<String, Object>> distributionList = customerService.GetDistributionList_Select(ProjectID);

        result.put("distributionList", distributionList);

        IPage page = new Page(PageIndex,PageSize);

        IPage<Map<String, Object>> customerManagerList = customerManagerService.CustomerManagePageList_Select(page,map);

        result.put("customerManagerList", customerManagerList);

        return Result.ok(result);
    }

    private void SetExcelToCustomerChange_BakList(Map<String, Object> map){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("客户信息","CustomerName"));
        entity.add(new ExcelExportEntity("手机号","CustomerMobile"));
        entity.add(new ExcelExportEntity("客储等级","CustomerRankName"));
        entity.add(new ExcelExportEntity("意向等级","CustomerLevel"));
        entity.add(new ExcelExportEntity("客户状态","CustomerStatus"));
        entity.add(new ExcelExportEntity("渠道类型","SourceType"));
        entity.add(new ExcelExportEntity("所属机构","ChannelName"));
        entity.add(new ExcelExportEntity("报备人","ReportUserName"));
        entity.add(new ExcelExportEntity("销售团队","SaleTeamName"));
        entity.add(new ExcelExportEntity("置业顾问","SaleUserName"));
        entity.add(new ExcelExportEntity("媒体大类","CognitiveChannel"));
        entity.add(new ExcelExportEntity("媒体子类","CognitiveChannelSub"));
        entity.add(new ExcelExportEntity("跟进方式","Follwupway"));
        entity.add(new ExcelExportEntity("成交房源","Room"));
        entity.add(new ExcelExportEntity("报备时间","ReportTime"));
        entity.add(new ExcelExportEntity("首访时间","TheFirstVisitDate"));
        entity.add(new ExcelExportEntity("最近到访","ZJDF"));
        entity.add(new ExcelExportEntity("最近跟进","TheLatestFollowUpDate"));
//        entity.add(new ExcelExportEntity("原因","InvalidReason"));
//        entity.add(new ExcelExportEntity("成交逾期时间","TradeOverdueTime"));
//        entity.add(new ExcelExportEntity("确认人","ConfirmUserName"));
//        entity.add(new ExcelExportEntity("确认时间","ConfirmTime"));
//        entity.add(new ExcelExportEntity("报备人手机号","ReportUserMobile"));
//        entity.add(new ExcelExportEntity("认筹时间","RCTime"));
//        entity.add(new ExcelExportEntity("认购时间","RGTime"));
//        entity.add(new ExcelExportEntity("签约时间","QYTime"));
        IPage page = new Page(1,999999999);
        IPage<Map<String,Object>> result = customerManagerService.CustomerManagePageList_Select(page,map);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result.getRecords(),name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "客户详情")
    @RequestMapping(value = "/CustomerNEWDetailAll_Select", method = {RequestMethod.GET})
    public Result CustomerNEWDetailAll_Select(String ProjectID,
                                                String CustomerID,
                                                String ReportUserID,
                                                String OpportunityID) {

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("ProjectID",ProjectID);
        map.put("CustomerID",CustomerID);
        map.put("ReportUserID",ReportUserID);
        map.put("OpportunityID",OpportunityID);

        //客户基本信息
        QueryWrapper<BOpportunity> opportunityWrapper = new QueryWrapper<>();
        opportunityWrapper.eq("ID", OpportunityID);
        int num = iBOpportunityService.count(opportunityWrapper);

        Map customerMap = new HashMap();

        if(num>0){
            customerMap = bCustomerManagerMapper.CustomerNEWDetail_SelectGT(map);
        }else{
            customerMap = bCustomerManagerMapper.CustomerNEWDetail_SelectLT(map);
        }

        result.put("customerMap",customerMap);

        //查询客户跟进记录
        List customerFollowList = bCustomerManagerMapper.CustomerNEWFollowUp_Select(map);
        result.put("customerFollowList",customerFollowList);
        //查询客户签约信息 成交记录
        List signInfoList = bCustomerManagerMapper.CustomerNEWSignInfo_Select(map);
        result.put("signInfoList",signInfoList);
        //查询客户付款信息
        List payInfoList = bCustomerManagerMapper.CustomerNEWPayInfo_Select(map);
        result.put("payInfoList",payInfoList);

        QueryWrapper<UpdateCustinfoLog> updateCustInfoLogQuery = new QueryWrapper<>();
        updateCustInfoLogQuery.eq("OpportunityID",OpportunityID);
        updateCustInfoLogQuery.orderByDesc("CreateTime");
        List updateCustInfoLogList = iUpdateCustinfoLogService.list(updateCustInfoLogQuery);
        result.put("updateCustInfoLogList",updateCustInfoLogList);

        return Result.ok(result);
    }


    @ApiOperation(value = "回款明细")
    @RequestMapping(value = "/CustomerPayMentsList_Select", method = {RequestMethod.GET})
    public Result CustomerPayMentsList_Select(String TradeGUID) {
        Map<String, Object> result = new HashMap<String, Object>();

        List paymentList = bCustomerManagerMapper.CustomerPayMentsList_Select(TradeGUID);
        result.put("paymentList",paymentList);
        return Result.ok(result);
    }


    @ApiOperation(value = "修改客户基本信息")
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/UpdateCustBaseInfo", method = {RequestMethod.POST})
    public Result UpdateCustBaseInfo(String oppoId,String custId,String userId,String customerName,String auxiliaryMobile, String cardType,String cardId,String gender,
        String customerNameOrg,String auxiliaryMobileOrg, String cardTypeOrg,String cardIdOrg,String genderOrg,String AddressOrg,String Address,String attrId) {
        //新增重复号码校验 包含主辅
    	if(StringUtils.isNotBlank(auxiliaryMobile)) {
    		QueryWrapper<BOpportunity> queryWrapper = new QueryWrapper<>();
    		queryWrapper.select("count(1) as num");
    		queryWrapper.eq("CustomerMobile", auxiliaryMobile);
    		queryWrapper.or(wrapper -> wrapper.eq("SpareMobile", auxiliaryMobile).ne("ID",oppoId));
    		Map<String, Object> i = iBOpportunityService.getMap(queryWrapper);
    		if (0 != (Integer) i.get("num")) {
    			return Result.errormsg(500, "编辑失败，手机号已存在！");
    		}
    	}
    	
    	UpdateWrapper<BOpportunity> oppoUpWarapper = new UpdateWrapper<>();
        BOpportunity oppo = new BOpportunity();
        oppo.setCustomerName(customerName);
        oppo.setSpareMobile(auxiliaryMobile);
        oppoUpWarapper.eq("ID",oppoId);
        iBOpportunityService.update(oppo,oppoUpWarapper);

        BCustomer cust = new BCustomer();
        UpdateWrapper<BCustomer> custUpWarapper = new UpdateWrapper<>();
        cust.setName(customerName);
        cust.setCardType(cardType);
        cust.setCardID(cardId);
        cust.setGender(gender);
        custUpWarapper.eq("ID",custId);
        customerService.update(cust,custUpWarapper);

        //更新客户地址
        BCustomerattribute custAttr = new BCustomerattribute();
        if(StringUtil.isNotNull(attrId))
            custAttr.setId(attrId);
        custAttr.setCustomerID(custId);
        custAttr.setAddress(Address);
        iBCustomerattributeService.saveOrUpdate(custAttr);

        List<Map<String, Object>>  jarry = vCustomergwlistSelectMapper.DictionaryList_Select("E72C340D-4092-467A-9B8F-5138DBDCA43B");

        List<Map<String, Object>>  cardTypejarry = vCustomergwlistSelectMapper.DictionaryList_Select("848EBE45-2C03-40C5-AE91-EC72533539BD");

        //变更记录
        UpdateCustinfoLog log = new UpdateCustinfoLog();

        log.setOpportunityID(oppoId);
        log.setCustomerID(custId);
        log.setEditorId(userId);
        log.setEditorName(ThreadLocalUtils.getRealName());
        if(StringUtil.isNotNull(gender)) {

            for(Map<String, Object> map : jarry){
                if(map.get("ID").toString().equals(genderOrg)){
                    genderOrg = map.get("DictName").toString();
                }
                if(map.get("ID").toString().equals(gender)){
                    gender = map.get("DictName").toString();
                }
            }
            log.setGender(genderOrg + "->" + gender);
        }
        if(StringUtil.isNotNull(cardId))
            log.setCardID(cardIdOrg + "->" + cardId);
        if(StringUtil.isNotNull(cardType)) {
            for(Map<String, Object> map : cardTypejarry){
                if(map.get("ID").toString().equals(cardTypeOrg)){
                    cardTypeOrg = map.get("DictName").toString();
                }
                if(map.get("ID").toString().equals(cardType)){
                    cardType = map.get("DictName").toString();
                }
            }
            log.setCardType(cardTypeOrg + "->" + cardType);
        }
        if(StringUtil.isNotNull(auxiliaryMobile))
            log.setAuxiliaryMobile(auxiliaryMobileOrg + "->" + auxiliaryMobile);
        if(StringUtil.isNotNull(customerName))
            log.setCustomerName(customerNameOrg + "->" + customerName);
        if(StringUtil.isNotNull(Address))
            log.setHomeAddress(AddressOrg + "->" + Address);
        log.setCreateTime(new Date());
        iUpdateCustinfoLogService.save(log);
        return Result.ok("修改成功");
    }


}
