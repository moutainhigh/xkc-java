package com.tahoecn.xkc.controller.ipad;

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
}
