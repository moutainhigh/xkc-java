package com.tahoecn.xkc.service.customer;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.model.vo.DicInfo;

public interface ICustomerHelp {
	
	public JSONObject OpportunityInfo(String opportunityID);
	
	public JSONObject OpportunityInfo(String ProjectID, String Mobile);
	
	public CustomerModelVo InitCustomerModeData(CSearchModelVo model,String jsonFileName, JSONObject CustomerObj,String customerModeType);
	
	public void SyncCustomer(String opportunityID, int optionType);
	
	public JSONObject GetParameters(CGWDetailModel model);
	
	public JSONObject GetParameters(CGWDetailModel model,JSONObject obj);
	
	public JSONObject CustomerOpportunityExist(String ProjectID, String Mobile);
	
	public JSONObject CustomerExist(String Mobile);
	
	public Map<String,String> GetOpportunitySource(JSONObject parameter);
    
	public void ClueUpdate(JSONObject parameter);
	
	public Boolean ClueCustomerUpdate(JSONObject param);
	
	public Boolean ClueCustomerInsert(JSONObject param);
	
	public List<Map<String,Object>> ClueCustomerList_Select(String Mobile, String ProjectID);
	
	public Boolean ClueCustomerFailureUpdate(JSONObject param);
	
	public Result IntentProjectAdd(JSONObject paramAry);
	
	public Boolean CustomerMobileSearch(String ProjectID,String Mobile,String JobCode,String UserID);
	
	public Boolean ComeOverdueClue_Update(String ProjectID, String Mobile, String UserID, String OrgID, String JobID);
	
	public int ClueExist(String ProjectID, String Mobile);
	
	public Boolean GetProjectIsNoAllotRole(String projectId);
	
	public JSONObject CustomerPotentialExist(String Mobile);
	
	public JSONObject OpportunityCustomer(String opportunityID);
	
	public Boolean CustomerIsCanOperate(String opportunityID,String userID);
	
	public CustomerModelVo InitCustomerModelByFileName(String jsonFile);
	
	public List<DicInfo> InitCustomerDicModel(String jsonFile);
}
