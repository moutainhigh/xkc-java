package com.tahoecn.xkc.service.customer.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.service.customer.ICustomerLockRoomService;

@Service
@Transactional(readOnly=true)
public class CustomerLockRoomServiceImpl implements ICustomerLockRoomService {

	@Override
	public Result CustomerLockRoomDetail_Insert(JSONObject Parameter) {
		Result re = new Result();
        Parameter.put("ImgUrl", UUID.randomUUID().toString()+".png");
        //JObject data = (JObject)Frame.DBhelper.JsonDataHelper.GetNormalData("CustomerLockRoomDetail_Select", Parameter, out errmsg, debug);
        //省去原系统图片处理
        return re;
	}

}
