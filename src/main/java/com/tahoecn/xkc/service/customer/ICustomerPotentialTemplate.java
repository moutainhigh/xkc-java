package com.tahoecn.xkc.service.customer;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;

public interface ICustomerPotentialTemplate {
	
	public CustomerModelVo InitCustomerPotentialModeData(CSearchModelVo model, String jsonFileName, JSONObject CustomerObj, String customerPotentialModeType);
	
	public JSONObject GetParameters(CGWDetailModel model);
	
	public JSONObject GetParameters(CGWDetailModel model,JSONObject obj);
}
