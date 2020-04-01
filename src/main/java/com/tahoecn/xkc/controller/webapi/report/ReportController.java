package com.tahoecn.xkc.controller.webapi.report;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.tahoecn.xkc.common.utils.ExcelUtil;
import com.tahoecn.xkc.common.utils.SqlInjectionUtil;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.customer.CustomerBook;
import com.tahoecn.xkc.model.customer.UpdateCustinfoLog;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;
import com.tahoecn.xkc.service.customer.IUpdateCustinfoLogService;
import com.tahoecn.xkc.service.report.ICbFyService;
import com.tahoecn.xkc.service.report.ICostomerReportService;
import com.tahoecn.xkc.service.report.ICustomerBookService;
import com.tahoecn.xkc.service.report.IKfReportService;
import com.tahoecn.xkc.service.report.ReportService;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YYY
 * @since 2019-07-22
 */
@RestController
@RequestMapping("/webapi/report")
public class ReportController extends TahoeBaseController {


    @Autowired
    private ReportService reportService;
    
    @Autowired
    private ICostomerReportService costomerReportService;

    @Autowired
    ICbFyService iCbFyService;

    @Autowired
    private ICustomerBookService customerBookService;

    @Autowired
    private IUpdateCustinfoLogService iUpdateCustinfoLogService;

    @ApiOperation(value = "客储动态监测表", notes = "客储动态监测表")
    @RequestMapping(value = "/CustomerRank_Select", method = {RequestMethod.GET})
    public Result CustomerRank_Select(String orglevel, String orgID, String startDate, String endDate,String AccountID,
                                      String isSelect,String dl) {
        // 仿sql注入过滤
        orglevel = SqlInjectionUtil.filter(orglevel);
        orgID = SqlInjectionUtil.filter(orgID);
        startDate = SqlInjectionUtil.filter(startDate);
        endDate = SqlInjectionUtil.filter(endDate);
        AccountID = SqlInjectionUtil.filter(AccountID);
        isSelect = SqlInjectionUtil.filter(isSelect);
        dl = SqlInjectionUtil.filter(dl);

        startDate = startDate + " 00:00:00";
        endDate = endDate + " 23:59:59";
        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotEmpty(dl)) {
            if ("1".equals(dl)){
                sqlWhere.append("SELECT * FROM kcview ORDER BY kcview.area,kcview.city,kcview.project");
                List<Map<String,Object>> result = reportService.CustomerRankNew_Select(orglevel,AccountID,startDate,endDate,sqlWhere.toString());
                CustomerRank_DownLoad(result);
            }
            if ("2".equals(dl)){
                List<Map<String,Object>> result = reportService.CustomerRankDetail_Select(orgID,startDate,endDate);
                CustomerRankDetail_DownLoad(result);
            }

            return null;
        }

        if ("4".equals(orglevel)){  //地产
            sqlWhere.append("SELECT '地产板块' Area,'' city,'' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,'BFD658A5-F645-468C-A11C-FB1689C1A166' haschildren,'5' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY PID");
        }else if ("5".equals(orglevel)) {    //区域
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'6' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY Area,haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'6' level FROM kcview WHERE POrgID='"+orgID+"' GROUP BY Area,haschildren");
            }
        } else if ("6".equals(orglevel)){   //城市
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT City Area,'','' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'7' level FROM kcview WHERE POrgID='"+orgID+"' GROUP BY Area,city,haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT City Area,'','' project,SUM(one) one,SUM(two) two,SUM(three) three,SUM(four) four,SUM(five) five,SUM(rg) rg,SUM(qy) qy,haschildren,'7' level FROM kcview WHERE COrgID='"+orgID+"' GROUP BY Area,city,haschildren");
            }
        } else if ("7".equals(orglevel)) {   //项目
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append( "SELECT project Area,'', '',one,two,three,four,five,rg,qy,'' haschildren,'10' level FROM kcview WHERE COrgID='"+orgID+"'");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append( "SELECT project Area,'', '',one,two,three,four,five,rg,qy,'' haschildren,'10' level FROM kcview WHERE ProjectID='"+orgID+"'");
            }
        } else if ("9".equals(orglevel)) {   //置业顾问
            sqlWhere.append( "SELECT Area,'', '',one,two,three,four,five,rg,qy,'' haschildren,'10' level FROM kcview");
            List<Map<String,Object>> result = reportService.CustomerRankSalesNew_Select(orgID,startDate,endDate,sqlWhere.toString());
            return Result.ok(result);
        }

        List<Map<String,Object>> result = reportService.CustomerRankNew_Select(orglevel,AccountID,startDate,endDate,sqlWhere.toString());

        return Result.ok(result);
    }


    private void CustomerRank_DownLoad(List<Map<String,Object>> result) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("区域", "area"));
        entity.add(new ExcelExportEntity("城市", "city"));
        entity.add(new ExcelExportEntity("项目", "project"));
        entity.add(new ExcelExportEntity("1级", "one"));
        entity.add(new ExcelExportEntity("1.5级", "two"));
        entity.add(new ExcelExportEntity("2级", "three"));
        entity.add(new ExcelExportEntity("2.5级", "four"));
        entity.add(new ExcelExportEntity("3级", "five"));
        entity.add(new ExcelExportEntity("认购", "rg"));
        entity.add(new ExcelExportEntity("签约", "qy"));

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CustomerRankDetail_DownLoad(List<Map<String,Object>> result) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("项目", "IntentProjectName"));
        entity.add(new ExcelExportEntity("置业顾问", "SaleUserName"));
        entity.add(new ExcelExportEntity("客户姓名", "CustomerName"));
        entity.add(new ExcelExportEntity("客户手机", "CustomerMobile"));
        entity.add(new ExcelExportEntity("级别升降", "CustomerRankValue"));
        entity.add(new ExcelExportEntity("升降时间", "CreateTime"));

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "渠道客户统计表", notes = "渠道客户统计表")
    @RequestMapping(value = "/ChannelCustomerReport_Select", method = {RequestMethod.GET})
    public Result ChannelCustomerReport_Select(String orglevel, String orgID, String startDate, String endDate,String AccountID,
                                      String isSelect,String IsExcel) {
        // 防sql注入过滤
        orglevel = SqlInjectionUtil.filter(orglevel);
        orgID = SqlInjectionUtil.filter(orgID);
        startDate = SqlInjectionUtil.filter(startDate);
        endDate = SqlInjectionUtil.filter(endDate);
        AccountID = SqlInjectionUtil.filter(AccountID);
        isSelect = SqlInjectionUtil.filter(isSelect);
        IsExcel = SqlInjectionUtil.filter(IsExcel);

        startDate = startDate + " 00:00:00";
        endDate = endDate + " 23:59:59";
        StringBuilder sqlWhere = new StringBuilder();
        if (StringUtils.isNotEmpty(IsExcel)) {
            List<Map<String,Object>> result = reportService.ChannelCustomerReportDL_Select(orglevel,AccountID,startDate,endDate);
            SetExcel_ChannelCustomerReport(result);
            return null;
        }

        if ("4".equals(orglevel)){  //地产
            sqlWhere.append("SELECT '地产板块' Area,'' City,'' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,'BFD658A5-F645-468C-A11C-FB1689C1A166' haschildren,'5' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY PID");
        }else if ("5".equals(orglevel)) {    //区域
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,haschildren,'6' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY Area,haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,haschildren,'6' level FROM kcview WHERE POrgID='"+orgID+"' GROUP BY Area,haschildren");
            }
        } else if ("6".equals(orglevel)){   //城市
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT City Area,'','' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,haschildren,'7' level FROM kcview WHERE POrgID='"+orgID+"' GROUP BY Area,city,haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT City Area,'','' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,haschildren,'7' level FROM kcview WHERE COrgID='"+orgID+"' GROUP BY Area,city,haschildren");
            }
        } else if ("7".equals(orglevel)) {   //项目
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT project Area,'', '',SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,haschildren,'8' level FROM kcview  WHERE COrgID='"+orgID+"' GROUP BY kcview.project,kcview.haschildren");
            } else if ("1".equals(isSelect)){   //首次加载和搜索查询
                sqlWhere.append("SELECT project Area,'', '',SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,'{0}' haschildren,'8' level FROM kcview WHERE ProjectID='"+orgID+"' GROUP BY kcview.project,kcview.haschildren");
            }
        } else if ("8".equals(orglevel)) {   ////这个表示展开项目了，项目下没东西了，直接返回空的
            return Result.ok("");
        }

        List<Map<String,Object>> result = reportService.ChannelCustomerReport_Select(orglevel,AccountID,startDate,endDate,sqlWhere.toString());

        return Result.ok(result);
    }

    private void SetExcel_ChannelCustomerReport(List<Map<String,Object>> result) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("区域", "area"));
        entity.add(new ExcelExportEntity("城市", "city"));
        entity.add(new ExcelExportEntity("项目", "project"));
        entity.add(new ExcelExportEntity("渠道来源", "DictName"));
        entity.add(new ExcelExportEntity("报备", "baobeiCount"));
        entity.add(new ExcelExportEntity("到访", "daofangCount"));
        entity.add(new ExcelExportEntity("认筹", "BookingCount"));
        entity.add(new ExcelExportEntity("认购套数", "OrderCount"));
        entity.add(new ExcelExportEntity("认购金额", "OrderAmount"));
        entity.add(new ExcelExportEntity("签约套数", "ContractCount"));
        entity.add(new ExcelExportEntity("签约金额", "ContractAmount"));
        entity.add(new ExcelExportEntity("退筹", "BookingCancelCount"));
        entity.add(new ExcelExportEntity("退房", "OrderCancelCount"));

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    @ApiOperation(value = "考勤报表查询", notes = "考勤报表查询")
    @RequestMapping(value = "/mChannelCheckReportList_Select", method = {RequestMethod.GET})
    public Result mChannelCheckReportList_Select(String ProjectID, Date StartTime,Date EndTime,String CheckDate,String Name,String Mobile,String TaskName,
    		String ReportName,int PageIndex,int PageSize) {
        IPage page=new Page(PageIndex,PageSize);
        IPage<Map<String,Object>> list=reportService.mChannelCheckReportList_Select(page,StartTime,EndTime,ProjectID,CheckDate,Name,Mobile,TaskName,ReportName);
        return Result.ok(list);
    }








    @ApiOperation(value = "考勤报表导出", notes = "考勤报表导出")
    @RequestMapping(value = "/mChannelCheckReportList_Export", method = {RequestMethod.GET})
    public Result mChannelCheckReportList_Export(String ProjectID, Date StartTime,Date EndTime,String CheckDate,String Name,String Mobile,String TaskName,
    		String ReportName) {
        List<Map<String,Object>> list=reportService.mChannelCheckReportList_Export(StartTime,EndTime,ProjectID,CheckDate,Name,Mobile,TaskName,ReportName);
        SetExcel_mChannelCheckReportList(list);
        return null;
    }

    private void SetExcel_mChannelCheckReportList(List<Map<String,Object>> result) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("姓名", "Name"));
        entity.add(new ExcelExportEntity("电话", "Mobile"));
        entity.add(new ExcelExportEntity("身份证", "CertificatesNo"));
        entity.add(new ExcelExportEntity("打卡日期", "CheckDate"));
        entity.add(new ExcelExportEntity("签到", "CheckInTime"));
        entity.add(new ExcelExportEntity("签退", "CheckOutTime"));
        entity.add(new ExcelExportEntity("工时", "WorkTime"));
        entity.add(new ExcelExportEntity("任务名称", "TaskName"));
        entity.add(new ExcelExportEntity("所属专员", "ReportName"));
        entity.add(new ExcelExportEntity("组别", "ChannelTypeName"));
        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,result,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

























    @ApiOperation(value = "客户信息明细", notes = "客户信息明细")
    @RequestMapping(value = "/costomerReportDetail", method = {RequestMethod.GET})
    public Result costomerReportDetail(int PageIndex,int PageSize,CostomerReport report,String isExcel,String isWhole,String wholeStatus) {
        IPage page=new Page(PageIndex,PageSize);
        QueryWrapper<CostomerReport> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getAreaName()), CostomerReport::getAreaName, report.getAreaName());   //区域名
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getCityName()), CostomerReport::getCityName, report.getCityName());   //城市名
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getIntentProjectName()), CostomerReport::getIntentProjectName, report.getIntentProjectName());    //项目名
        wrapper.lambda().like(StringUtils.isNotBlank(report.getCustomerName()), CostomerReport::getCustomerName, report.getCustomerName());   //客户名
        wrapper.lambda().like(StringUtils.isNotBlank(report.getCustomerMobile()), CostomerReport::getCustomerMobileWhole, report.getCustomerMobile()); //客户电话
        wrapper.lambda().like(StringUtils.isNotBlank(report.getSaleUserName()), CostomerReport::getSaleUserName, report.getSaleUserName());   //置业顾问

        //wrapper.lambda().eq(StringUtils.isNotBlank(report.getOpportunitySource()), CostomerReport::getOpportunitySource, report.getOpportunitySource());    //客户源
        wrapper.lambda().and(StringUtils.isNotBlank(report.getOpportunitySource()),rolewrapper -> rolewrapper.eq(StringUtils.isNotBlank(report.getOpportunitySource()),CostomerReport::getOpportunitySource, report.getOpportunitySource()).or().eq(StringUtils.isNotBlank(report.getOpportunitySource()),CostomerReport::getChannelName,report.getOpportunitySource()));

        wrapper.lambda().eq(StringUtils.isNotBlank(report.getCustomerStatus()), CostomerReport::getCustomerStatus, report.getCustomerStatus()); //客户状态
        if(StringUtils.isNotEmpty(wholeStatus)){
            wrapper.lambda().ne(CostomerReport::getCustomerStatus, "无效");
        }
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getCustomerRankName()), CostomerReport::getCustomerRankName, report.getCustomerRankName());   //客户级别
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getFollwUpWayTxt()), CostomerReport::getFollwUpWayTxt, report.getFollwUpWayTxt());    //跟进类型
        wrapper.lambda().eq(report.getDaofangCount() != null, CostomerReport::getDaofangCount, report.getDaofangCount());    //到访次数
        if (report.getCreateTime() != null)
            wrapper.lambda().between(report.getCreateTime() != null, CostomerReport::getCreateTime, report.getCreateTime(), new Date(report.getCreateTimeEnd().getTime() + 60 * 60 * 24 * 1000 - 1));  //创建时间
        if (report.getReportTime() != null)
            wrapper.lambda().between(report.getReportTime() != null, CostomerReport::getReportTime, report.getReportTime(), new Date(report.getReportTimeEnd().getTime() + 60 * 60 * 24 * 1000 - 1));  //宝贝时间
        if (report.getTheFirstVisitDate() != null)
            wrapper.lambda().between(report.getTheFirstVisitDate() != null, CostomerReport::getTheFirstVisitDate, report.getTheFirstVisitDate(), new Date(report.getTheFirstVisitDateEnd().getTime() + 60 * 60 * 24 * 1000 - 1));  //首访时间
        if (report.getZjdf() != null)
            wrapper.lambda().between(report.getZjdf() != null, CostomerReport::getZjdf, report.getZjdf(), new Date(report.getZjdf().getTime() + 60 * 60 * 24 * 1000 - 1));  //最近到访
        if (report.getTheLatestFollowUpDate() != null)
            wrapper.lambda().between(report.getTheLatestFollowUpDate() != null, CostomerReport::getTheLatestFollowUpDate, report.getTheLatestFollowUpDate(), new Date(report.getTheLatestFollowUpDateEnd().getTime() + 60 * 60 * 24 * 1000 - 1));  //最近跟进
        if (report.getBookingCreateTime() != null)
            wrapper.lambda().between(report.getBookingCreateTime() != null, CostomerReport::getBookingCreateTime, report.getBookingCreateTime(), new Date(report.getBookingCreateTimeEnd().getTime() + 60 * 60 * 24 * 1000 - 1));  //认筹时间
        if (report.getOrderCreateTime() != null)
            wrapper.lambda().between(report.getOrderCreateTime() != null, CostomerReport::getOrderCreateTime, report.getOrderCreateTime(), new Date(report.getOrderCreateTimeEnd().getTime() + 60 * 60 * 24 * 1000 - 1));  //认购时间
        if (report.getmYContractCreateTime() != null)
            wrapper.lambda().between(report.getmYContractCreateTime() != null, CostomerReport::getmYContractCreateTime, report.getmYContractCreateTime(),new Date(report.getmYContractCreateTimeEnd().getTime() + 60*60*24*1000 - 1));  //签约时间
        if (StringUtils.isNotEmpty(isExcel)){
            page = new Page(1,-1);
        }
        IPage<CostomerReport> list=costomerReportService.page(page,wrapper);
        if (StringUtils.isNotEmpty(isExcel)){
            SetExcel_costomerReport(list,isWhole);
            return null;
        }
        return Result.ok(list);
    }
























    private void SetExcel_costomerReport(IPage<CostomerReport> result,String isWhole) {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("区域", "areaName"));
        entity.add(new ExcelExportEntity("城市公司", "cityName"));
        entity.add(new ExcelExportEntity("项目名称", "intentProjectName"));
        entity.add(new ExcelExportEntity("客户姓名", "customerName"));
        if (StringUtils.isNotEmpty(isWhole)){
            entity.add(new ExcelExportEntity("客户电话", "customerMobileWhole"));
        }else{
            entity.add(new ExcelExportEntity("客户电话", "customerMobile"));
        }
        entity.add(new ExcelExportEntity("置业顾问", "saleUserName"));
        entity.add(new ExcelExportEntity("置业顾问所属团队", "saleTeamName"));
        entity.add(new ExcelExportEntity("渠道来源", "sourceType"));
        entity.add(new ExcelExportEntity("所属机构", "channelName"));
        entity.add(new ExcelExportEntity("渠道人员", "reportUserName"));
        entity.add(new ExcelExportEntity("渠道人员电话", "reportUserMobile"));
        entity.add(new ExcelExportEntity("报备人归属", "reportOrgName"));
        entity.add(new ExcelExportEntity("机构简称", "reportOrgShortName"));
        entity.add(new ExcelExportEntity("客户状态", "customerStatus"));
        entity.add(new ExcelExportEntity("客户级别", "customerRankName"));
        entity.add(new ExcelExportEntity("创建时间", "createTime"));
        entity.add(new ExcelExportEntity("报备时间", "reportTime"));
        entity.add(new ExcelExportEntity("首次到访", "theFirstVisitDate"));
        entity.add(new ExcelExportEntity("最近到访", "zjdf"));
        entity.add(new ExcelExportEntity("到访次数", "daofangCount"));
        entity.add(new ExcelExportEntity("最近跟进", "theLatestFollowUpDate"));
        entity.add(new ExcelExportEntity("跟进方式", "follwUpWayTxt"));
        entity.add(new ExcelExportEntity("认筹数", "projNum"));
        entity.add(new ExcelExportEntity("首次认筹", "bookingCreateTime"));
        entity.add(new ExcelExportEntity("认购套数（套）", "orderCount"));
        entity.add(new ExcelExportEntity("认购金额（元）", "myorderAccount"));
        entity.add(new ExcelExportEntity("首次认购", "orderCreateTime"));
        entity.add(new ExcelExportEntity("签约套数", "mYContractCount"));
        entity.add(new ExcelExportEntity("签约总额(元)", "contractAccount"));
        entity.add(new ExcelExportEntity("首次签约", "mYContractCreateTime"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String,Object>> resultMap = new ArrayList<>();
        for (CostomerReport costomerReport : result.getRecords()) {
            Map<String, Object> map = Maps.newHashMap();
            BeanMap beanMap = BeanMap.create(costomerReport);
            for (Object key : beanMap.keySet()) {
                if (("createTime").equals(key) || ("reportTime").equals(key) || ("theFirstVisitDate").equals(key) || ("zjdf").equals(key) ||
                ("theLatestFollowUpDate").equals(key) || ("bookingCreateTime").equals(key)){
                    if (beanMap.get(key) != null)
                        map.put(key + "", sdf.format((Date)beanMap.get(key)));

                }else if (("orderCreateTime").equals(key)|| ("mYContractCreateTime").equals(key)){
                    if (beanMap.get(key) != null)
                        map.put(key + "", sdf.format((Date)beanMap.get(key)));
                } else{
                    if(("sourceType").equals(key)){
                        if (beanMap.get(key) == null || "".equals(beanMap.get(key)))
                            map.put(key + "", "自然访客");
                        else
                            map.put(key + "", beanMap.get(key));
                    }else
                        map.put(key + "", beanMap.get(key));
                }
            }
            resultMap.add(map);
        }

        try {
            LocalDateTime time= LocalDateTime.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            String name = dtf2.format(time) + ".xls";
            ExcelUtil.exportExcel(entity,resultMap,name,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "test", notes = "test")
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public Result test(String ProjectID, Date StartTime,Date EndTime) {
        iCbFyService.save1();
        return null;
    }

    @ApiOperation(value = "客户台账", notes = "客户台账")
    @RequestMapping(value = "/costomerBook", method = {RequestMethod.GET})
    public Result costomerBook(int PageIndex,int PageSize,CustomerBook report,String isExcel) {
        IPage page=new Page(PageIndex,PageSize);
        QueryWrapper<CustomerBook> wrapper=new QueryWrapper<>();
        wrapper.lambda().like(StringUtils.isNotBlank(report.getName()), CustomerBook::getName, report.getName());
        wrapper.lambda().like(StringUtils.isNotBlank(report.getMobile()), CustomerBook::getMobile, report.getMobile());
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getCardType()), CustomerBook::getCardType, report.getCardType());
        wrapper.lambda().like(StringUtils.isNotBlank(report.getCardID()), CustomerBook::getCardID, report.getCardID());
        wrapper.lambda().eq(StringUtils.isNotBlank(report.getGender()), CustomerBook::getGender, report.getGender());
        wrapper.lambda().like(StringUtils.isNotBlank(report.getAddress()), CustomerBook::getAddress, report.getAddress());

        if (StringUtils.isNotEmpty(isExcel)){
            page = new Page(1,-1);
        }

        IPage<CustomerBook> list=customerBookService.page(page,wrapper);
        if (StringUtils.isNotEmpty(isExcel)){
            //SetExcel_costomerReport(list,isWhole);
            return null;
        }
        return Result.ok(list);
    }

    @ApiOperation(value = "客户台账详情", notes = "客户台账详情")
    @RequestMapping(value = "/costomerBookDetail", method = {RequestMethod.GET})
    public Result costomerBookDetail(String ID,String OpportunityID) {
        List<Map<String,Object>> listOpp = customerBookService.listOpp(ID);
        List<Map<String,Object>> listClue = customerBookService.listClue(ID);
        List<Map<String,Object>> payInfoList = customerBookService.customerPayInfo(ID);
        //查询客户签约信息
        List<Map<String, Object>> CustomerNEWSignInfo = customerBookService.CustomerNEWSignInfo_Select(ID);

        Map<String,List<Map<String,Object>>> result = new HashMap<>();
        
        QueryWrapper<UpdateCustinfoLog> updateCustInfoLogQuery = new QueryWrapper<>();
        updateCustInfoLogQuery.eq("CustomerID",ID);
        updateCustInfoLogQuery.orderByDesc("CreateTime");
        List updateCustInfoLogList = iUpdateCustinfoLogService.list(updateCustInfoLogQuery);
        result.put("updateCustInfoLogList",updateCustInfoLogList);
        result.put("listOpp",listOpp);
        result.put("listClue",listClue);
        result.put("CustomerNEWSignInfo",CustomerNEWSignInfo);
        result.put("payInfoList",payInfoList);
        return Result.ok(result);
    }

}
