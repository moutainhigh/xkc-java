package com.tahoecn.xkc.service.customer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.service.customer.IPotentialCustomerService;

@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly=true)
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {
	
	@Resource
	private BCustomerpotentialMapper bCustomerpotentialMapper;

	@Override
	public Result mCustomerPotentialTagDetail_Insert(JSONObject paramAry) {
		Result re = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
			bCustomerpotentialMapper.mCustomerPotentialTagDetail_Insert_delete(pmap);
			bCustomerpotentialMapper.mCustomerPotentialTagDetail_Insert(pmap);
			re.setErrcode(0);
			re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("服务器出现异常！");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result mUserFollowPotentialList_Select(JSONObject paramAry) {
		Result entity = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
			List<Map<String,Object>> data = bCustomerpotentialMapper.mUserFollowPotentialList_Select(pmap);
			Long recordCount = bCustomerpotentialMapper.mUserFollowPotentialList_Select_count(pmap);
			Map<String,Object> re = new HashMap<String, Object>();
        	re.put("List", data);
        	re.put("AllCount", recordCount);
        	re.put("PageSize", paramAry.get("PageSize"));
        	entity.setData(re);
			entity.setErrcode(0);
			entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrcode(1);
			entity.setErrmsg("服务器出现异常！");
			e.printStackTrace();
		}
        return entity;
	}

	@Override
	public Result mUserFollowPotentialDetail_Insert(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
        	bCustomerpotentialMapper.mUserFollowPotentialDetail_Insert_delete(pmap);
        	bCustomerpotentialMapper.mUserFollowPotentialDetail_Insert(pmap);
        	entity.setErrcode(0);
			entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(110);
			entity.setErrmsg("关注客户异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mUserFollowPotentialDetail_Delete(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
        	bCustomerpotentialMapper.mUserFollowPotentialDetail_Delete(pmap);
            entity.setErrcode(0);
			entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(110);
			entity.setErrmsg("删除客户异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mCustomerPotentialCopyList_Insert(JSONObject paramAry) {
		Result re = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
			Map<String,Object> data = bCustomerpotentialMapper.mCustomerPotentialCopyList_Insert(pmap);
		    re.setErrcode(0);
			re.setErrmsg("成功");
			re.setData(data);
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("服务器出现异常！");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result mCustomerPotentialZQBaseDetail_Select(JSONObject paramAry) {
		Result re = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
			Map<String,Object> data = bCustomerpotentialMapper.mCustomerPotentialZQBaseDetail_Select(pmap);
	        if (data!=null && data.size()>0){
	        	Map<String,Object> job = bCustomerpotentialMapper.mCustomerPotentialZQTractDetail_Select(pmap);
	        	data.put("TractSort", job.get("Sort"));
	        	data.put("TractName", job.get("Name"));
	        	data.put("TractTime", job.get("TractTime"));
	            
	        	JSONObject token = new JSONObject();
	            token.put("TokerUserID",data.get("ReportUserID"));
	            token.put("ClueMobile",data.get("CustomerMobile"));
	            token.put("ClueID", data.get("ClueID"));
	            token.put("SourceType",data.get("SourceType"));
	            data.put("Token", token);
	        }
	        re.setData(data);
	        re.setErrcode(0);
	        re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("服务器出现异常！");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result mCustomerPotentialZQDetail_Select(JSONObject paramAry) {
		Result entity = new Result();
        /*try{
            CSearchModel model = paramAry.ToObject<CSearchModel>();
            CustomerPotentialTemplate customerPotentialTemplate = new CustomerPotentialTemplate();
            var CustomerObj = CustomerPotentialClueDetail_Select(model.ClueID);
            if (CustomerObj.Count > 0)
            {
                CustomerPotentialModeType customerPotentialModeType = CustomerPotentialModeType.自渠_客户_详情;
                CustomerModel customerModel = customerPotentialTemplate.InitCustomerPotentialModeData(model, "ZQDetailCustomerPotential.json", CustomerObj, customerPotentialModeType, debug);
                entity.Data = JObject.FromObject(customerModel);
                entity.ErrMsg = "成功！";
            }
            else
            {
                entity.ErrCode = 11;
                entity.ErrMsg = "不存在客户信息！";
            }
        }catch (Exception e){
            entity.ErrCode = 110;
            entity.ErrMsg = "获取数据异常！";
        }*/
        return entity;
	}
	

}
