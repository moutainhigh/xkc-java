package com.tahoecn.xkc.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.customer.IPartReceiveService;

@RestController
@Api(tags = "APP-分接接口", value = "APP-分接接口")
@RequestMapping("/app/partReceive")
public class PartReceiveController extends TahoeBaseController{
	
	@Autowired
	private IPartReceiveService iPartReceiveService;
	
	@ResponseBody
    @ApiOperation(value = "分接客户列表", notes = "分接客户列表")
    @RequestMapping(value = "/mCustomerFJList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerFJList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPartReceiveService.CustomerList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "分接顾问查询", notes = "分接顾问查询")
    @RequestMapping(value = "/mCustomerFJSearch_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerFJSearch_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPartReceiveService.mCustomerFJSearch_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "案场分接客户登记", notes = "案场分接客户登记")
    @RequestMapping(value = "/mCustomerFJDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerFJDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPartReceiveService.mCustomerFJDetail_Insert(json);
    }
	
	
	@ResponseBody
    @ApiOperation(value = "分接顾问查询", notes = "分接顾问查询")
    @RequestMapping(value = "/mCustomerFJAdviserList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerFJAdviserList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPartReceiveService.CounselorList(json);
    }
	
	
}
