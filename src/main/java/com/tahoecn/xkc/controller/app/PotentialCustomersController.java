package com.tahoecn.xkc.controller.app;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tahoecn.xkc.controller.TahoeBaseController;

@RestController
@Api(tags = "APP-潜在客户接口", value = "APP-潜在客户接口")
@RequestMapping("/app/potenCust")
public class PotentialCustomersController extends TahoeBaseController{
	
}
