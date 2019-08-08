package com.tahoecn.xkc.controller.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.controller.TahoeBaseController;
import com.tahoecn.xkc.converter.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "app置业计划接口", value = "app置业计划接口")
@RestController
@RequestMapping("/app/Property")
public class AppPropertyController extends TahoeBaseController {
	@ApiOperation(value = "查询折扣", notes = "查询折扣")
	@RequestMapping(value = "/mPropertyDiscountList_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mPropertyDiscountList_Select(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
		try {
			System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            
            
			return re.ok("");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}

	@ApiOperation(value = "查询房间信息", notes = "查询房间信息")
	@RequestMapping(value = "/mHouseOpportunity_Detail_Select", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result mHouseOpportunity_Detail_Select(@RequestBody JSONObject jsonParam) {
		Result re=new Result();
		try {
			System.out.println(jsonParam.toJSONString());
            Map paramMap = (HashMap)jsonParam.get("_param");
            
            
			return re.ok("");
		}catch (Exception e) {
			e.printStackTrace();
			return Result.errormsg(1,"系统异常，请联系管理员");
		}
	}
}
