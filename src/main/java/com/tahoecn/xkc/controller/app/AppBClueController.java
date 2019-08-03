package com.tahoecn.xkc.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.vo.Customer;
import com.tahoecn.xkc.model.vo.Customers;
import com.tahoecn.xkc.service.customer.IBClueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "客户专项报备接口", value = "客户专项报备接口")
@RestController
@RequestMapping("/app/clue")
public class AppBClueController extends TahoeBaseController {
	
	@Autowired
	private IBClueService bClueService;

	@ApiOperation(value = "报备", notes = "报备新客户")
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result report(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
		try {
			System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String reportId = (String)paramMap.get("reportId").toString();//报告人ID
            String name = (String)paramMap.get("name").toString();//客户姓名
            String sex = (String)paramMap.get("sex").toString();//客户性别
            String projectId = (String)paramMap.get("projectId").toString();//项目ID
            String mobile = (String)paramMap.get("mobile").toString();//客户手机号码
            String remark = (String)paramMap.get("remark").toString();//备注
			return bClueService.report(reportId, projectId, name, mobile, sex, remark);
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}

	@ApiOperation(value = "我的客户列表", notes = "我的客户列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result list(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
		try {
			System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String reportId = (String)paramMap.get("reportId").toString();//报告人ID
            String nameOrMobile = (String)paramMap.get("nameOrMobile").toString();//客户姓名或者手机号
            String ascOrDesc = (String)paramMap.get("ascOrDesc").toString();//排序
            String status = (String)paramMap.get("status").toString();//客户状态
            List<Customer> customers = bClueService.listMyCustomers(reportId, "", ascOrDesc, nameOrMobile, status);
    		if (customers == null) {
    			return Result.errormsg(-1,"未查询到该客户");
    		} else {
    			Customers cs = new Customers();
    			cs.setCustomerList(customers);
    			cs.setTotal(customers.size());
    			return Result.ok(cs);
    		}
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}

	@ApiOperation(value = "客户详情", notes = "客户详情")
	@RequestMapping(value = "/detail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result detail(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
		try {
			System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            String clueId = (String)paramMap.get("clueId").toString();//报备线索id
            Customer customer = bClueService.detail(clueId);
            if (customer != null) {
            	return Result.ok(customer);
    		} else {
    			return Result.errormsg(-1,"未查询到该客户");
    		}
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}