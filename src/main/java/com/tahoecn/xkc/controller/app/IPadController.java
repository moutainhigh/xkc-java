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
import com.tahoecn.xkc.service.ipad.IIpadService;

@RestController
@Api(tags = "ipad-业务接口", value = "ipad-业务接口")
@RequestMapping("/ipad/business")
public class IPadController extends TahoeBaseController{
	
	@Resource
	private IIpadService iIpadService;
	
	@ResponseBody
    @ApiOperation(value = "项目列表", notes = "项目列表")
    @RequestMapping(value = "/mLFProjectList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFProjectList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFProjectList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "切换项目", notes = "切换项目")
    @RequestMapping(value = "/mLFUSerProjectChange_Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFUSerProjectChange_Update(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.UserProjectChange_Update(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问列表", notes = "置业顾问列表")
    @RequestMapping(value = "/mLFCustomerGWList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerGWList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerGWList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户详情", notes = "客户详情")
    @RequestMapping(value = "/mLFCustomerDetailByMobile_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerDetailByMobile_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerDetailByMobile_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "客户登记(报备)", notes = "客户登记(报备)")
    @RequestMapping(value = "/mLFCustomerDetail_Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerDetail_Insert(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "待分配列表", notes = "待分配列表")
    @RequestMapping(value = "/mLFCustomerNeedFPList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFCustomerNeedFPList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFCustomerNeedFPList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "接待记录列表", notes = "接待记录列表")
    @RequestMapping(value = "/mLFReceptRecordList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFReceptRecordList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFReceptRecordList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "接待记录客户列表（客户列表）", notes = "接待记录客户列表（客户列表）")
    @RequestMapping(value = "/mLFReceptRecordCustomerList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mLFReceptRecordCustomerList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iIpadService.mLFReceptRecordCustomerList_Select(json);
    }
	
}
