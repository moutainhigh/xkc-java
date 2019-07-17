package com.tahoecn.xkc.service.sys.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomerfjlistSelectMapper;
import com.tahoecn.xkc.service.sys.ISystemAccountService;

@Service
public class SystemAccountService implements ISystemAccountService {

	@Resource
	private VCustomerfjlistSelectMapper vCustomerfjlistSelectMapper;
	
	@Override
	public Result SystemAccountDetail_Select(String userID) {
		Result re = new Result();
		try {
			Map<String,Object> data = vCustomerfjlistSelectMapper.SystemAccountDetail_Select(userID);
			if(data!=null && data.size()>0){
				re.setData(new JSONObject(data));
				re.setErrcode(0); 
				re.setErrmsg("成功");
			}else{
				re.setErrcode(1); 
				re.setErrmsg("失败");
			}
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return re;
	}
	
}
