package com.tahoecn.xkc.controller.webapi.report;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.utils.SqlInjectionUtil;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;
import com.tahoecn.xkc.service.report.IKfReportService;
import com.tahoecn.xkc.service.report.ReportService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webapp/KfReport")
public class KfReportController {
	
	@Autowired
    private ReportService reportService;
	
	@Autowired
	private IKfReportService kfReportService;

	@ApiOperation(value = "kf客户信息明细", notes = "kf客户信息明细")
    @RequestMapping(value = "/kfCostomerReportDetail", method = {RequestMethod.GET})
    public Result kfCostomerReportDetail(CostomerReportVO report) {
        Page<KfCostomerReportDetailVO> page=new Page(report.getPageIndex(),report.getPageSize());
        Page<KfCostomerReportDetailVO> list = kfReportService.kfCostomerReportDetail(page,report);
        return Result.ok(list);
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
            List<Map<String,Object>> result = reportService.kfChannelCustomerReportDL_Select(orglevel,AccountID,startDate,endDate);
            return Result.ok(result);
        }
        
        if ("4".equals(orglevel)){  //地产
            sqlWhere.append("SELECT '地产板块' Area,'' City,'' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,'BFD658A5-F645-468C-A11C-FB1689C1A166' haschildren,'5' level FROM kcview WHERE PID='BFD658A5-F645-468C-A11C-FB1689C1A166' GROUP BY PID");
        }else if ("5".equals(orglevel)) {    //区域
            if ("0".equals(isSelect)){  //展开查询
                sqlWhere.append("SELECT  Area,'' City,'' project,SUM(baobeiCount) baobeiCount,SUM(daofangCount) daofangCount,SUM(BookingCount) BookingCount,SUM(BookingCancelCount) BookingCancelCount,SUM(OrderCount) OrderCount,Convert(decimal(18,2),SUM(OrderAmount)) OrderAmount,SUM(OrderCancelCount) OrderCancelCount,SUM(ContractCount) ContractCount,Convert(decimal(18,2),SUM(ContractAmount)) ContractAmount,haschildren,'6' level FROM kcview WHERE PID='"+orgID+"' GROUP BY Area,haschildren");
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

        List<Map<String,Object>> result = reportService.KfChannelCustomerReport_Select(orglevel,AccountID,startDate,endDate,sqlWhere.toString());

        return Result.ok(result);
    }
	
	@ApiOperation(value = "获取组织机构表", notes = "获取组织机构表")
    @RequestMapping(value = "/getOrgList", method = {RequestMethod.GET})
	public Result getOrgList(String userName, Integer level,String orgID) {
		 List<Map<String,Object>> result = kfReportService.getOrgList(userName,level,orgID);
		 return Result.ok(result);
	}
	
	

}
