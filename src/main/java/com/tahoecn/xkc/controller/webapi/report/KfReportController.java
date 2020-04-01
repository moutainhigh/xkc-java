package com.tahoecn.xkc.controller.webapi.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;
import com.tahoecn.xkc.service.report.IKfReportService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webapp/KfReport")
public class KfReportController {
	
	@Autowired
	private IKfReportService kfReportService;

	@ApiOperation(value = "kf客户信息明细", notes = "kf客户信息明细")
    @RequestMapping(value = "/kfCostomerReportDetail", method = {RequestMethod.POST})
    public Result kfCostomerReportDetail(@RequestBody CostomerReportVO report) {
        Page<KfCostomerReportDetailVO> page=new Page(report.getPageIndex(),report.getPageSize());
        Page<KfCostomerReportDetailVO> list = kfReportService.kfCostomerReportDetail(page,report);
        return Result.ok(list);
    }

}
