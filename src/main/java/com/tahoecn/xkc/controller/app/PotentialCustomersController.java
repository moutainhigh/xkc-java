package com.tahoecn.xkc.controller.app;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.customer.IPotentialCustomerService;

@RestController
@Api(tags = "APP-潜在客户接口", value = "APP-潜在客户接口")
@RequestMapping("/app/potenCust")
public class PotentialCustomersController extends TahoeBaseController{
	
	@Resource
	private IPotentialCustomerService iPotentialCustomerService;
	
	@ResponseBody
    @ApiOperation(value = "标签新增", notes = "标签新增")
    @RequestMapping(value = "/mCustomerPotentialTagDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialTagDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialTagDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "关注客户列表", notes = "关注客户列表")
    @RequestMapping(value = "/mUserFollowPotentialList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserFollowPotentialList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mUserFollowPotentialList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户关注新增", notes = "客户关注新增")
    @RequestMapping(value = "/mUserFollowPotentialDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserFollowPotentialDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mUserFollowPotentialDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户关注删除", notes = "客户关注删除")
    @RequestMapping(value = "/mUserFollowPotentialDetail_Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mUserFollowPotentialDetail_Delete(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mUserFollowPotentialDetail_Delete(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "我的客户", notes = "我的客户")
    @RequestMapping(value = "/mCustomerPotentialCopyList_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialCopyList_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialCopyList_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户基本信息", notes = "客户基本信息")
    @RequestMapping(value = "/mCustomerPotentialZQBaseDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialZQBaseDetail_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialZQBaseDetail_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户详细信息", notes = "客户详细信息")
    @RequestMapping(value = "/mCustomerPotentialZQDetail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialZQDetail_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialZQDetail_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户跟进列表", notes = "客户跟进列表")
    @RequestMapping(value = "/mCustomerPotentialFollowUpList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialFollowUpList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialFollowUpList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "完善资料", notes = "完善资料")
    @RequestMapping(value = "/mCustomerPotentialZQDetail_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialZQDetail_Update(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialZQDetail_Update(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户跟进记录新增", notes = "客户跟进记录新增")
    @RequestMapping(value = "/mCustomerPotentialFollowUpDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialFollowUpDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialFollowUpDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户等级", notes = "客户登记")
    @RequestMapping(value = "/mCustomerPotentialZQDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialZQDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialZQDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户查询", notes = "客户查询")
    @RequestMapping(value = "/mCustomerPotentialZQSearch_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialZQSearch_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialZQSearch_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户列表", notes = "客户列表")
    @RequestMapping(value = "/mCustomerPotentialZQList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerPotentialZQList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iPotentialCustomerService.mCustomerPotentialZQList_Select(json);
    }
	
}
