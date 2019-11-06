package com.tahoecn.xkc.service.customer;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;

public interface ICustomerLockRoomService {
	
	public Result CustomerLockRoomDetail_Insert(JSONObject Parameter);
}
