package com.tahoecn.xkc.service.customer;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;

public interface IMYService {
	
	public Result CustomerDetail_Insert(Map<String, Object> info);
	
	public Result OpportunityDetail_Insert(List<Map<String,Object>> info,List<Map<String,Object>> infoCustomer);
}
