package com.tahoecn.xkc.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2019-06-25
 */
@RestController
@Api(tags = "APP-职业顾问接口", value = "APP-职业顾问接口")
@RequestMapping("/app/careerCons")
public class CareerConsCustController extends TahoeBaseController {

    @Autowired
    private IVCustomergwlistSelectService iVCustomergwlistSelectService;

    /**
     * 职业顾问客户列表
     * @param gWCustomerPageDto
     * @return
     */
	@ResponseBody
    @ApiOperation(value = "置业顾问客户列表", notes = "职业顾问客户列表")
    @RequestMapping(value = "/mCustomerGWList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mCustomerGWList_Select(@RequestBody JSONObject paramAry) {
		GWCustomerPageDto gWCustomerPageDto = new GWCustomerPageDto();
		JSONObject json = paramAry.getJSONObject("_param");
		gWCustomerPageDto = JSON.parseObject(json.toJSONString(), GWCustomerPageDto.class);
		return iVCustomergwlistSelectService.customerList(gWCustomerPageDto);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问客户基本信息", notes = "职业顾问客户基本信息")
    @RequestMapping(value = "/mCustomerGWBase_Select", method = RequestMethod.POST)
    public Result mCustomerGWBase_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.customerBase(json);
    }
	
	
	@ResponseBody
    @ApiOperation(value = "置业顾问跟进记录", notes = "置业顾问跟进记录")
    @RequestMapping(value = "/mCustomerFollowUpList_Select", method = RequestMethod.POST)
    public Result mCustomerFollowUpList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerFollowUpList_Select(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问销售轨迹", notes = "置业顾问销售轨迹")
    @RequestMapping(value = "/mCustomerTrackList_Select", method = RequestMethod.POST)
    public Result mCustomerTrackList_Select(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.TrackList(json);
    }
	
	@ResponseBody
    @ApiOperation(value = "置业顾问新增跟进记录", notes = "置业顾问新增跟进记录")
    @RequestMapping(value = "/mCustomerFollowUpDetail_Insert", method = RequestMethod.POST)
    public Result mCustomerFollowUpDetail_Insert(@RequestBody JSONObject paramAry) {
		JSONObject json = paramAry.getJSONObject("_param");
		return iVCustomergwlistSelectService.mCustomerFollowUpDetail_Insert(json);
    }
	
}
