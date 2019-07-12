package com.tahoecn.xkc.service.customer;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;

public interface IPartReceiveService {
	
	public Result CustomerList(JSONObject paramAry);
	
	public Result mCustomerFJSearch_Select(JSONObject paramAry);
	
	public Result mCustomerFJDetail_Insert(JSONObject paramAry);
	
	public Result CounselorList(JSONObject paramAry);
}
