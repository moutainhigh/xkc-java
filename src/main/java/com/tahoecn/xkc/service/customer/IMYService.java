package com.tahoecn.xkc.service.customer;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;

public interface IMYService {
	
	public Result CustomerDetail_Insert(JSONObject Parameter);
	
	public Result OpportunityDetail_Insert(JSONObject Parameter);
}
