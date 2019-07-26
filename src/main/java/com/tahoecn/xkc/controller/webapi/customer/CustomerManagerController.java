package com.tahoecn.xkc.controller.webapi.customer;


import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerManagerMapper;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.service.customer.IBCustomerManagerService;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/customermanager")
public class CustomerManagerController extends TahoeBaseController {
    private static final Log log = LogFactory.get();

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

    @ApiOperation(value = "客户管理列表")
    @RequestMapping(value = "/CustomerManagePageList_Select", method = {RequestMethod.GET})
    public Result CustomerChangePageList_Select(String ProjectID,
                                                    String IsExcel,
                                                    Long PageSize,
                                                    Long PageIndex,
                                                    String UserID) {

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("ProjectID",ProjectID);
        map.put("IsExcel",IsExcel);
        map.put("PageSize",PageSize);
        map.put("PageIndex",PageIndex);
        map.put("UserID",UserID);
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
        entity.add(new ExcelExportEntity("客户姓名","CustomerName"));
        entity.add(new ExcelExportEntity("手机号","CustomerMobile"));
        entity.add(new ExcelExportEntity("客储等级","CustomerRank"));
        entity.add(new ExcelExportEntity("客户状态","Status"));
        entity.add(new ExcelExportEntity("原因","InvalidReason"));
        entity.add(new ExcelExportEntity("项目名称","ProjectName"));
        entity.add(new ExcelExportEntity("到访逾期时间","ComeOverdueTime"));
        entity.add(new ExcelExportEntity("成交逾期时间","TradeOverdueTime"));
        entity.add(new ExcelExportEntity("确认人","ConfirmUserName"));
        entity.add(new ExcelExportEntity("确认时间","ConfirmTime"));
        entity.add(new ExcelExportEntity("置业顾问","SaleUserName"));
        entity.add(new ExcelExportEntity("渠道类型","SourceType"));
        entity.add(new ExcelExportEntity("报备人","ReportUserName"));
        entity.add(new ExcelExportEntity("报备人手机号","ReportUserMobile"));
        entity.add(new ExcelExportEntity("报备时间","ReportTime"));
        entity.add(new ExcelExportEntity("所属机构","ChannelName"));
        entity.add(new ExcelExportEntity("首访时间","TheFirstVisitDate"));
        entity.add(new ExcelExportEntity("最近到访","ZJDF"));
        entity.add(new ExcelExportEntity("认筹时间","RCTime"));
        entity.add(new ExcelExportEntity("认购时间","RGTime"));
        entity.add(new ExcelExportEntity("签约时间","QYTime"));
        IPage page = new Page(1,99999);
        IPage<Map<String,Object>> result = customerManagerService.CustomerManagePageList_Select(page,map);

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xlsx";
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
        //查询客户签约信息
        List signInfoList = bCustomerManagerMapper.CustomerNEWSignInfo_Select(map);
        result.put("signInfoList",signInfoList);
        //查询客户付款信息
        List payInfoList = bCustomerManagerMapper.CustomerNEWPayInfo_Select(map);
        result.put("payInfoList",payInfoList);

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
}
