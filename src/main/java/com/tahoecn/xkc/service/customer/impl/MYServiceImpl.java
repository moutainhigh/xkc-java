package com.tahoecn.xkc.service.customer.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.service.customer.IMYService;

@Service
@Transactional(readOnly=true)
public class MYServiceImpl implements IMYService {
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	
	@Override
	@Transactional(readOnly=false)
	public Result CustomerDetail_Insert(JSONObject Parameter) {
		Result re = new Result();
		try {
			String customerID = Parameter.getString("CustomerID");
			Map<String, Object> info = vCustomergwlistSelectMapper.LocalCustomerDetail_Select(customerID);
	        if (info.size() > 0){
	        	vCustomergwlistSelectMapper.MYUserDetail_Insert(info);
	            vCustomergwlistSelectMapper.MYCustomerDetail_Insert(info);
	            re.setErrcode(0);
	            re.setErrmsg("成功");
	        }else{
	            re.setErrcode(1);
	            re.setErrmsg("客户信息不正确");
	        }
		} catch (Exception e) {
			re.setErrcode(1);
            re.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return re;
		
	}

	@Override
	@Transactional(readOnly=false)
	public Result OpportunityDetail_Insert(JSONObject Parameter) {
		Result re = new Result();
		try {
			String opportunityID = Parameter.getString("opportunityID");
	        List<Map<String,Object>> info = vCustomergwlistSelectMapper.LocalOpportunityDetail_Select(opportunityID);
	        List<Map<String,Object>> infoCustomer = vCustomergwlistSelectMapper.LocalOpportunityCustomerList_Select(opportunityID);
	        if (info.size() > 0){
	            for(Map<String,Object> item : info){
	            	vCustomergwlistSelectMapper.MYOpportunityDetail_Insert(item);
	            	vCustomergwlistSelectMapper.MyOpportunityCustomerDetail_Delete(item);
	            }
	            for(Map<String,Object> item : infoCustomer){
	            	vCustomergwlistSelectMapper.MyOpportunityCustomerDetail_Insert(item);
	            }
	            re.setErrcode(0);
	            re.setErrmsg("成功");
	        }else{
	            re.setErrcode(1);
	            re.setErrmsg("机会信息不正确");
	        }
		} catch (Exception e) {
			re.setErrcode(1);
            re.setErrmsg("系统异常");
			e.printStackTrace();
		}
        
        return re;
	}

}
