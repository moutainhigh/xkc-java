package com.tahoecn.xkc.service.customer.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
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
	        	String CreatedByGUID = info.get("CreatedByGUID")!=null?info.get("CreatedByGUID").toString():"";
	        	String ModifyByGUID = info.get("ModifyByGUID")!=null?info.get("ModifyByGUID").toString():"";
	        	if(!"".equals(CreatedByGUID) && "99".equals(CreatedByGUID)){
	        		List<Map<String,Object>> vaild_1_map = vCustomergwlistSelectMapper.MYUserDetail_Insert_valid_1(info);
	        		if(vaild_1_map==null || vaild_1_map.size()==0){
	        			vCustomergwlistSelectMapper.MYUserDetail_Insert_step1(info);
	        		}
	        	}
	        	if(!"".equals(ModifyByGUID) && "99".equals(ModifyByGUID)){
	        		List<Map<String,Object>> vaild_2_map = vCustomergwlistSelectMapper.MYUserDetail_Insert_valid_2(info);
	        		if(vaild_2_map==null || vaild_2_map.size()==0){
	        			vCustomergwlistSelectMapper.MYUserDetail_Insert_step2(info);
	        		}
	        	}
	        	
	        	List<Map<String,Object>> vaild_1_map = vCustomergwlistSelectMapper.MYCustomerDetail_Insert_vaild_1(info);
	        	List<Map<String,Object>> vaild_2_map = vCustomergwlistSelectMapper.MYCustomerDetail_Insert_vaild_2(info);
	        	if((vaild_1_map==null || vaild_1_map.size()==0) && (vaild_2_map==null || vaild_2_map.size()==0)){
	        		vCustomergwlistSelectMapper.MYCustomerDetail_Insert_insert_1(info);
	        		vCustomergwlistSelectMapper.MYCustomerDetail_Insert_insert_2(info);
	        	}else{
	        		vCustomergwlistSelectMapper.MYCustomerDetail_Insert_update(info);
	        	}
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
	            	List<Map<String,Object>> vaild_1_map = vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_valid_1(item);
	            	if(vaild_1_map==null || vaild_1_map.size()==0){
	            		vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_insert_1(item);
	            	}else{
	            		List<Map<String,Object>> vaild_2_map = vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_valid_2(item);
	            		List<Map<String,Object>> vaild_3_map = vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_valid_3(item);
	            		if((vaild_2_map==null || vaild_2_map.size()==0) && (vaild_3_map==null || vaild_3_map.size()==0)){
	            			vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_update(item);
	            		}
	            	}
	            	List<Map<String,Object>> vaild_4_map = vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_valid_4(item);
	            	if(vaild_4_map==null || vaild_4_map.size()==0){
	            		vCustomergwlistSelectMapper.MYOpportunityDetail_Insert_insert_2(item);
	            	}
	            	
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
