package com.tahoecn.xkc.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.model.vo.Customer;
import com.tahoecn.xkc.model.vo.Customers;
import com.tahoecn.xkc.service.customer.IBClueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "客户专项报备接口", value = "客户专项报备接口")
@RestController
@RequestMapping("/app/clue")
public class AppBClueController extends TahoeBaseController {
	
	@Autowired
	private IBClueService bClueService;

	@ApiOperation(value = "报备", notes = "报备新客户")
	@RequestMapping(value = "/report", method = { RequestMethod.POST })
	public JSONResult report(
			@ApiParam(name = "reportId", value = "报告人ID", required = true) @RequestParam String reportId,
			@ApiParam(name = "name", value = "客户姓名", required = true) @RequestParam String name,
			@ApiParam(name = "sex", value = "客户性别", required = true) @RequestParam String sex,
			@ApiParam(name = "projectId", value = "项目ID") @RequestParam(required = false) String projectId,
			@ApiParam(name = "mobile", value = "客户手机号码", required = true) @RequestParam String mobile,
			@ApiParam(name = "remark", value = "备注") @RequestParam(required = false) String remark) {

		return bClueService.report(reportId, projectId, name, mobile, sex, remark);
	}

	@ApiOperation(value = "我的客户列表", notes = "我的客户列表")
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public JSONResult<Customers> list(
			@ApiParam(name = "reportId", value = "报告人ID", required = true) @RequestParam String reportId,
			@ApiParam(name = "nameOrMobile", value = "客户姓名或者手机号") @RequestParam(required = false) String nameOrMobile,
			@ApiParam(name = "ascOrDesc", value = "排序") @RequestParam(required = false) String ascOrDesc,
			@ApiParam(name = "status", value = "客户状态") @RequestParam(required = false) String status) {

		JSONResult jsonResult = new JSONResult();

		List<Customer> customers = bClueService.listMyCustomers(reportId, "", ascOrDesc, nameOrMobile, status);
		if (customers == null) {
			jsonResult.setCode(-1);
			jsonResult.setMsg("未查询到客户");
		} else {
			Customers cs = new Customers();
			cs.setCustomerList(customers);
			cs.setTotal(customers.size());
			jsonResult.setCode(0);
			jsonResult.setMsg("SUCCESS");
			jsonResult.setData(cs);
		}
		return jsonResult;
	}

	@ApiOperation(value = "客户详情", notes = "客户详情")
	@RequestMapping(value = "/detail", method = { RequestMethod.POST })
	public JSONResult<Customer> detail(
			@ApiParam(name = "clueId", value = "报备线索id", required = true) @RequestParam String clueId) {

		JSONResult jsonResult = new JSONResult();
		Customer customer = bClueService.detail(clueId);
		if (customer != null) {
			jsonResult.setCode(0);
			jsonResult.setMsg("SUCCESS");
			jsonResult.setData(customer);
		} else {
			jsonResult.setCode(-1);
			jsonResult.setMsg("未查询到该客户");
		}
		return jsonResult;
	}
}