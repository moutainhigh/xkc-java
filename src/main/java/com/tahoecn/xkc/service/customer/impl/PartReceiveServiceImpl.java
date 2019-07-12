package com.tahoecn.xkc.service.customer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomerfjlistSelectMapper;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IPartReceiveService;

@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly=true)
public class PartReceiveServiceImpl implements IPartReceiveService {

	@Resource
	private VCustomerfjlistSelectMapper vCustomerfjlistSelectMapper;
	@Resource
	private ICustomerHelp customerTemplate;
	@Value("${SiteUrl}")
    private String SiteUrl;
	
	
	@Override
	public Result CustomerList(JSONObject paramAry) {
		Result entity = new Result();
		try {
			StringBuilder whereSb = new StringBuilder();
	        StringBuilder orderSb = new StringBuilder();
	        //手机/或姓名
	        String Mobile = paramAry.getString("Mobile");
	        if(!StringUtils.isEmpty(Mobile)){
	        	whereSb.append(" and (CustomerName Like'%"+Mobile+"%' or CustomerMobile Like'"+Mobile+"') ");
	        }
	        //今日来访
	        String Filter = paramAry.getString("Filter");
	        if (!StringUtils.isEmpty(Filter)){
	            if (Filter.equals("42464192-8347-2067-CDA9-A5D45CA0EBE7")){
	                whereSb.append(" and  isday =0 ");
	            }
	            if (Filter.equals("40FEA720-8014-6232-2C66-220F37B68E42")){
	                whereSb.append(" and  isday =1 ");
	            }
	        }else{
	            whereSb.append(" and  isday =0 ");
	        }
	        //排序
	        String Sort = paramAry.getString("Sort"); 
	        if (!StringUtils.isEmpty(Sort)){
	            if (Sort.equals("7D3688E3-0857-B27D-360D-07C53D20DAD8")){  
	            	//最新来访
	                orderSb.append(" Order By visitingTime desc ");
	            }else{//最早来访
	                orderSb.append(" Order By visitingTime asc");
	            }
	        }else{
	            orderSb.append(" Order By visitingTime desc ");
	        }
	        paramAry.put("WHERE", whereSb.toString());
	        paramAry.put("ORDER", orderSb.toString());

	        Map<String,Object> pmap =JSONObject.parseObject(paramAry.toJSONString(), Map.class);
	        
	        List<VCustomerfjlistSelect> data = vCustomerfjlistSelectMapper.sCustomerFJList_Select(pmap);
	        entity.setData(data);
	        entity.setErrcode(0);
	        entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrcode(1);
	        entity.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return entity;
	}

	@Override
	public Result mCustomerFJSearch_Select(JSONObject paramAry) {
		Result entity = new Result();
		try {
			CSearchModelVo model =JSONObject.parseObject(paramAry.toJSONString(), CSearchModelVo.class);
	        String jsonFile = "";
	        String customerModeType = CustomerModeType.无.getTypeID();
	        int isNew = 1;
	        JSONObject CustomerObj = new JSONObject();
	        if (!StringUtils.isEmpty(model.getMobile())){
	        	customerTemplate.CustomerMobileSearch(model.getProjectID(), model.getMobile(), model.getJobCode(), model.getUserID());
	        	customerTemplate.ComeOverdueClue_Update(model.getProjectID(), model.getMobile(), model.getUserID(), model.getOrgID(), model.getJobID());
	        }
	        //验证是否为老机会老客户
	        JSONObject j_re = customerTemplate.CustomerOpportunityExist(model.getProjectID(), model.getMobile());
	        
	        if (j_re.getBooleanValue("status")){//是老客户
	        	CustomerObj = j_re.getJSONObject("CustomerObj");
	            String SaleUserID = CustomerObj.getString("SaleUserID");
	            String JobCode = paramAry.getString("JobCode");
	            if (!"XSZC".equals(JobCode)){
	                if (SaleUserID.length() == 0){
	                    entity.setErrcode(1);
	        	        entity.setErrmsg("不能跟进公共客户！");
	                    return entity;
	                }
	            }
	            if (SaleUserID.length() == 0 ){//登记公共客户
	                jsonFile = "FJNewCustomer.json";
	                customerModeType = CustomerModeType.分接_老机会_老客户_公共客户.getTypeID();
	                isNew = 0;
	            }
	            if (SaleUserID.length() > 0 && !SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//不为无
	                jsonFile = "FJOldCustomer.json";
	                customerModeType = CustomerModeType.分接_老机会_老客户.getTypeID();
	                isNew = 0;
	            }
	            if (SaleUserID.length() > 0 && SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//为无
	                jsonFile = "FJNewCustomer.json";
	                customerModeType = CustomerModeType.分接_老机会_老客户_未分配.getTypeID();
	                isNew = 1;
	            }
	        }else{//新客户
	        	JSONObject j_re_1 = customerTemplate.CustomerExist(model.getMobile());
	            if (!j_re_1.getBooleanValue("status")){//不存在客户信息
	                //1.根据手机号码查询拓客客户信息 
	            	JSONObject j_re_2 = customerTemplate.CustomerPotentialExist(model.getMobile());
	                if (j_re_2.getBooleanValue("status")){//存在拓客客户信息
	                	CustomerObj = j_re_2.getJSONObject("CustomerObj");
	                    jsonFile = "FJNewCustomer.json";
	                    customerModeType = CustomerModeType.分接_新机会_新客户_老潜在客户.getTypeID();
	                }else{//不存在拓客客户信息
	                    jsonFile = "FJNewCustomer.json";
	                    customerModeType = CustomerModeType.分接_新机会_新客户_新潜在客户.getTypeID();
	                }
	            }else{//存在客户信息
	            	CustomerObj = j_re_1.getJSONObject("CustomerObj");
	            	JSONObject OldCustomerObj = customerTemplate.OpportunityInfo(model.getProjectID(), model.getMobile());
	                if (OldCustomerObj.size() > 0){//本项目存在历史机会,直接分配给之前的顾问
	                    //jsonFile = "FJOldCustomer.json";
	                    jsonFile = "FJNewCustomer.json";
	                    customerModeType = CustomerModeType.分接_新机会_老客户.getTypeID();
	                    isNew = 0;
	                    CustomerObj = OldCustomerObj;
	                    CustomerObj.put("CustomerTag", "");//把客户标签重置
	                }else{
	                    jsonFile = "FJNewCustomer.json";
	                    customerModeType = CustomerModeType.分接_新机会_老客户.getTypeID();
	                }
	            }
	        }
	        CustomerModelVo customerModel = customerTemplate.InitCustomerModeData(model, jsonFile, CustomerObj, customerModeType);
	        customerModel.setIsNew(isNew);
	        entity.setData(customerModel);
	        entity.setErrcode(0);
	        entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrcode(1);
	        entity.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return entity;
	}

	@Override
	public Result mCustomerFJDetail_Insert(JSONObject paramAry) {
		Result entity = new Result();
        /*try{
            if (!StringUtils.isEmpty(paramAry.getString("FormSessionID"))){
                JObject sessionRes = (JObject)JsonDataHelper.GetNormalData("mSystemFormSessionStatus_Select", paramAry, out errmsg);
                if (ConvertHelper.ToInt(sessionRes["RowCount"]) > 0)
                {
                    entity.ErrCode = 1;
                    entity.ErrMsg = "不能重复请求！";
                    return entity;
                }
            }

            JObject CustomerObj = new JObject();
            CGWDetailModel model = paramAry.ToObject<CGWDetailModel>();
            if (model != null && model.ItemList != null && model.ItemList.Count > 0)
            {
                //初始化参数
                JObject parameter = new CustomerHelp().GetParameters(model);
                parameter["VisitAddress"] = "";
                parameter["IsIPad"] = "0";
                string OldSaleUserID = "";
                string SaleUserID = ConvertHelper.ToString(parameter["SaleUserID"]); 
                if (parameter.Count > 0)
                {
                    bool IsNew = true;
                    string sqlKey = "";
                    string Mobile = Convert.ToString(parameter["Mobile"]);
                    if (!string.IsNullOrWhiteSpace(Mobile))
                    {
                        if (customerHelp.CustomerOpportunityExist(model.ProjectID, Mobile, out CustomerObj, debug))
                        {//存在老机会_老客户 
                            parameter["CustomerID"] = ConvertHelper.ToString(CustomerObj["CustomerID"]);
                            parameter["OpportunityID"] = ConvertHelper.ToString(CustomerObj["OpportunityID"]);
                            OldSaleUserID = ConvertHelper.ToString(CustomerObj["SaleUserID"]);
                            parameter["LastName"] = ConvertHelper.ToString(CustomerObj["LastName"]);
                            parameter["FirstName"] = ConvertHelper.ToString(CustomerObj["FirstName"]);
                            if (!string.IsNullOrEmpty(OldSaleUserID) && OldSaleUserID != "C4C09951-FA39-4982-AAD1-E72D9D4C3899")
                            {
                                SaleUserID = OldSaleUserID;
                                parameter["SaleUserID"] = OldSaleUserID;
                            }
                            sqlKey = "mCustomerFJDetail_Update";
                            IsNew = false;
                            //公共客户不允许分接跟进
                            if (ConvertHelper.ToString(paramAry["JobCode"]) != "XSZC")
                            {
                                if (OldSaleUserID.Length == 0)
                                {
                                    entity.ErrCode = 1;
                                    entity.ErrMsg = "不能跟进公共客户！";
                                    return entity;
                                }
                            }
                            parameter["IsReAlloc"] = "0";
                        }
                        else
                        {//新机会
                            if (!customerHelp.CustomerExist(Mobile, out CustomerObj, debug))
                            {//新机会_新客户
                                parameter["CustomerID"] = Guid.NewGuid().ToString();
                                //1.根据手机号码查询拓客客户信息 
                                if (customerHelp.CustomerPotentialExist(Mobile, out CustomerObj, debug))
                                {//存在拓客客户信息
                                    parameter["CardType"] = ConvertHelper.ToString(CustomerObj["CardType"]);
                                    parameter["CardID"] = ConvertHelper.ToString(CustomerObj["CardID"]);
                                    parameter["AcceptFactor"] = ConvertHelper.ToString(CustomerObj["AcceptFactor"]);
                                    parameter["AgeGroup"] = ConvertHelper.ToString(CustomerObj["AgeGroup"]);
                                    parameter["DomicilePlace"] = ConvertHelper.ToString(CustomerObj["DomicilePlace"]);
                                    parameter["HomeAddress"] = ConvertHelper.ToString(CustomerObj["HomeAddress"]);
                                    parameter["HomeArea"] = ConvertHelper.ToString(CustomerObj["HomeArea"]);
                                    parameter["WorkArea"] = ConvertHelper.ToString(CustomerObj["WorkArea"]);
                                    parameter["Marriage"] = ConvertHelper.ToString(CustomerObj["Marriage"]);
                                    parameter["Family"] = ConvertHelper.ToString(CustomerObj["Family"]);
                                    parameter["Industry"] = ConvertHelper.ToString(CustomerObj["Industry"]);
                                    parameter["PropertyNum"] = ConvertHelper.ToString(CustomerObj["PropertyNum"]);
                                }
                                sqlKey = "mCustomerFJDetail_Insert";
                            }
                            else
                            {//新机会_老客户
                                parameter["CustomerID"] = ConvertHelper.ToString(CustomerObj["CustomerID"]);
                                var OldCustomerObj = customerHelp.OpportunityInfo(model.ProjectID, Mobile, debug);
                                if (OldCustomerObj.Count > 0)
                                {//本项目存在历史机会,直接分配给之前的顾问
                                    //OldSaleUserID = ConvertHelper.ToString(OldCustomerObj["SaleUserID"]);
                                    parameter["LastName"] = ConvertHelper.ToString(OldCustomerObj["LastName"]);
                                    parameter["FirstName"] = ConvertHelper.ToString(OldCustomerObj["FirstName"]);
                                    //if (OldSaleUserID.Length > 0 && OldSaleUserID != "C4C09951-FA39-4982-AAD1-E72D9D4C3899")//不为无
                                    //{
                                    //    parameter["SaleUserID"] = OldSaleUserID;
                                    //    SaleUserID = OldSaleUserID;
                                    //}
                                }
                                sqlKey = "mOldCustomerFJDetail_Insert";
                            }
                            parameter["OpportunityID"] = Guid.NewGuid().ToString();
                            IsNew = true;
                        }
                        //销售轨迹类别
                        parameter["TrackType"] = "BC2F967F-8FFE-1F52-49F6-CBCDFE8D044A";
                        //来电/来访
                        int status = (Convert.ToString(parameter["VisitType"]) == "E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450") ? 2 : 1;
                        parameter["Status"] = status;
                        if (IsNew)
                        {
                            //客户来源
                            string ClueID = "";
                            parameter["OpportunitySource"] = customerHelp.GetOpportunitySource(parameter, out ClueID, debug);
                            if (!string.IsNullOrEmpty(ClueID))
                            {
                                parameter["ClueID"] = ClueID;
                            }
                            else
                            {
                                parameter["ClueID"] = "";
                            }
                        }
                        string IntentionLevel = ConvertHelper.ToString(CustomerObj["CustomerLevel"]);
                        if (string.IsNullOrEmpty(IntentionLevel))
                        {
                            IntentionLevel = "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D";
                        }
                        string VisitType = ConvertHelper.ToString(parameter["VisitType"]);
                        string FollwUpWay = "";
                        if (VisitType == "E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450")
                        {//来访
                            FollwUpWay = "E30825AA-B894-4A5F-AF55-24CAC34C8F1F";
                        }
                        else
                        {//来电
                            FollwUpWay = "A79A1057-D4DC-497C-8C81-8F93E422C819";
                        }
                        string FollwUpType = Common.Utils.GetCustomerActionByFollowUpWay(FollwUpWay);
                        if (SaleUserID.Length > 0)
                        {
                            bool result = JsonDataHelper.SetNormalData(sqlKey, parameter, out errmsg, debug);
                            if (result)
                            {
                                if (IsNew)
                                {
                                    //更新线索信息
                                    customerHelp.ClueUpdate(parameter, debug);
                                    if (SaleUserID != "C4C09951-FA39-4982-AAD1-E72D9D4C3899")//不为无
                                    {//判断分配的置业顾问是否为无
                                        if (entity.ErrCode == 0)
                                        {
                                            string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                            string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                            string BizID = ConvertHelper.ToString(parameter["OpportunityID"]);
                                            string BizType = "Opportunity";
                                            string Subject = MessageType.分配待跟进.ToString();
                                            string Content = "客户" + Convert.ToString(parameter["LastName"]) + Convert.ToString(parameter["FirstName"]) + "、" + Convert.ToString(parameter["Mobile"]) + MessageType.到访提醒.ToString();
                                            string Receiver = ConvertHelper.ToString(parameter["SaleUserID"]);
                                            new SystemMessageService().Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.分配待跟进, true, debug);
                                        }
                                        //客户分配
                                        string msg = string.Empty;
                                        
                                        JObject re = (JObject)JsonDataHelper.GetNormalData("RemindRuleAllotDetail_Select", parameter, out msg, debug);
                                        if (re["ClueID"] != null && !string.IsNullOrEmpty(re["ClueID"].ToString()) && re["ReportUserID"] != null && !string.IsNullOrEmpty(re["ReportUserID"].ToString()))
                                        {
                                            if (SaleUserID != "C4C09951-FA39-4982-AAD1-E72D9D4C3899")//不为无
                                            {//判断分配的置业顾问是否为无
                                                string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                                string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                                string Content = "客户" + Convert.ToString(parameter["LastName"]) + Convert.ToString(parameter["FirstName"]) + "、" + Convert.ToString(parameter["Mobile"]) +"(客户分配提醒)";
                                                new SystemMessageService().Detail_Insert(UserID, ProjectID, re["ClueID"].ToString(), "Clue", "客户分配提醒", Content, re["ReportUserID"].ToString(), MessageType.系统通知, true, debug);
                                            }
                                        }
                                        //客户到访
                                        if (FollwUpType == "售场接待")
                                        {
                                            string msgs = string.Empty;
                                            JObject res = (JObject)JsonDataHelper.GetNormalData("RemindRuleArriveDetail_Select", parameter, out msgs, debug);
                                            if (res["ClueID"] != null && !string.IsNullOrEmpty(res["ClueID"].ToString()) && res["ReportUserID"] != null && !string.IsNullOrEmpty(res["ReportUserID"].ToString()))
                                            {
                                                string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                                string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                                string Content = "客户" + Convert.ToString(res["LastName"]) + Convert.ToString(res["FirstName"]) + "、" + Convert.ToString(res["Mobile"]) + "(" + MessageType.到访提醒 + ")";
                                                new SystemMessageService().Detail_Insert(UserID, ProjectID, res["ClueID"].ToString(), "Clue", "客户到访提醒", Content, res["ReportUserID"].ToString(), MessageType.带看通知, true, debug);
                                            }
                                        }
                                        #endregion
                                        var Account = new SystemAccountService().SystemAccountDetail_Select(new JObject() { new JProperty("UserID", Convert.ToString(parameter["SaleUserID"])) }, debug);
                                        string NewSaleUserName = "";
                                        if (Account.ErrCode == 0)
                                        {
                                            if (((JObject)Account.Data).Count > 0)
                                            {
                                                NewSaleUserName = ConvertHelper.ToString(((JObject)Account.Data)["EmployeeName"]);
                                            }
                                        }
                                        JObject obj1 = new JObject();
                                        obj1.Add("FollwUpType", ActionType.分配顾问.ToString());
                                        obj1.Add("SalesType", 1);
                                        obj1.Add("NewSaleUserName", NewSaleUserName);
                                        obj1.Add("OldSaleUserName", "");
                                        obj1.Add("FollwUpUserID", ConvertHelper.ToString(parameter["UserID"]));
                                        obj1.Add("FollwUpWay", "");
                                        obj1.Add("FollowUpContent", "");
                                        obj1.Add("IntentionLevel", "");
                                        obj1.Add("OrgID", ConvertHelper.ToString(parameter["OrgID"]));
                                        obj1.Add("FollwUpUserRole", ConvertHelper.ToString(parameter["JobID"]));
                                        obj1.Add("OpportunityID", ConvertHelper.ToString(parameter["OpportunityID"]));
                                        obj1.Add("ClueID", "");
                                        obj1.Add("NextFollowUpDate", "");
                                        new CustomerActionRepository(obj1, debug).CustomerFollowUp_Insert();
                                    }
                                    //增加跟进记录
                                    if (!string.IsNullOrEmpty(FollwUpType))
                                    {
                                        JObject obj = new JObject();
                                        obj.Add("FollwUpType", FollwUpType);
                                        obj.Add("SalesType", 1);
                                        obj.Add("NewSaleUserName", "");
                                        obj.Add("OldSaleUserName", "");
                                        obj.Add("FollwUpUserID", ConvertHelper.ToString(parameter["UserID"]));
                                        obj.Add("FollwUpWay", FollwUpWay);
                                        obj.Add("FollowUpContent", FollwUpType);
                                        obj.Add("IntentionLevel", IntentionLevel);//默认D级
                                        obj.Add("OrgID", ConvertHelper.ToString(parameter["OrgID"]));
                                        obj.Add("FollwUpUserRole", ConvertHelper.ToString(parameter["JobID"]));
                                        obj.Add("OpportunityID", ConvertHelper.ToString(parameter["OpportunityID"]));
                                        obj.Add("ClueID", "");
                                        obj.Add("NextFollowUpDate", "");
                                        new CustomerActionRepository(obj, debug).CustomerFollowUp_Insert();
                                    }
                                    CustomerOpportunityFollowUpDetail_Update(parameter, debug);//客户机会跟进记录更新
                                }
                                else
                                {
                                    if (SaleUserID != "C4C09951-FA39-4982-AAD1-E72D9D4C3899")//不为无
                                    {//判断分配的置业顾问是否为无
                                        if (OldSaleUserID == "C4C09951-FA39-4982-AAD1-E72D9D4C3899")//原顾问为无,新顾问不为无,发送分配待跟进消息和分配跟进记录
                                        {
                                            if (entity.ErrCode == 0)
                                            {
                                                string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                                string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                                string BizID = ConvertHelper.ToString(parameter["OpportunityID"]);
                                                string BizType = "Opportunity";
                                                string Subject = MessageType.分配待跟进.ToString();
                                                string Content = "客户" + Convert.ToString(parameter["LastName"]) + Convert.ToString(parameter["FirstName"]) + "、" + Convert.ToString(parameter["Mobile"]) + MessageType.到访提醒.ToString();
                                                string Receiver = ConvertHelper.ToString(parameter["SaleUserID"]);
                                                new SystemMessageService().Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.分配待跟进, true, debug);
                                            }
                                            //客户分配
                                            string msg = string.Empty;
                                            JObject re = (JObject)JsonDataHelper.GetNormalData("RemindRuleAllotDetail_Select", parameter, out msg, debug);
                                            if (re["ClueID"] != null && !string.IsNullOrEmpty(re["ClueID"].ToString()) && re["ReportUserID"] != null && !string.IsNullOrEmpty(re["ReportUserID"].ToString()))
                                            {
                                                if (SaleUserID != "C4C09951-FA39-4982-AAD1-E72D9D4C3899")//不为无
                                                {//判断分配的置业顾问是否为无
                                                    string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                                    string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                                    string Content = "客户" + Convert.ToString(parameter["LastName"]) + Convert.ToString(parameter["FirstName"]) + "、" + Convert.ToString(parameter["Mobile"]) + "(客户分配提醒)";
                                                    new SystemMessageService().Detail_Insert(UserID, ProjectID, re["ClueID"].ToString(), "Clue", "客户分配提醒", Content, re["ReportUserID"].ToString(), MessageType.系统通知, true, debug);
                                                }
                                            }
                                            #endregion
                                            var Account = new SystemAccountService().SystemAccountDetail_Select(new JObject() { new JProperty("UserID", Convert.ToString(parameter["SaleUserID"])) }, debug);
                                            string NewSaleUserName = "";
                                            if (Account.ErrCode == 0)
                                            {
                                                if (((JObject)Account.Data).Count > 0)
                                                {
                                                    NewSaleUserName = ConvertHelper.ToString(((JObject)Account.Data)["EmployeeName"]);
                                                }
                                            }
                                            JObject obj1 = new JObject();
                                            obj1.Add("FollwUpType", ActionType.分配顾问.ToString());
                                            obj1.Add("SalesType", 1);
                                            obj1.Add("NewSaleUserName", NewSaleUserName);
                                            obj1.Add("OldSaleUserName", "");
                                            obj1.Add("FollwUpUserID", ConvertHelper.ToString(parameter["UserID"]));
                                            obj1.Add("FollwUpWay", "");
                                            obj1.Add("FollowUpContent", "");
                                            obj1.Add("IntentionLevel", "");
                                            obj1.Add("OrgID", ConvertHelper.ToString(parameter["OrgID"]));
                                            obj1.Add("FollwUpUserRole", ConvertHelper.ToString(parameter["JobID"]));
                                            obj1.Add("OpportunityID", ConvertHelper.ToString(parameter["OpportunityID"]));
                                            obj1.Add("ClueID", "");
                                            obj1.Add("NextFollowUpDate", "");
                                            new CustomerActionRepository(obj1, debug).CustomerFollowUp_Insert();
                                        }
                                        else
                                        {
                                            if (FollwUpWay == "E30825AA-B894-4A5F-AF55-24CAC34C8F1F")
                                            {
                                                if (entity.ErrCode == 0 && model.IsSend == "1")
                                                {
                                                    string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                                    string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                                    string BizID = ConvertHelper.ToString(parameter["OpportunityID"]);
                                                    string BizType = "Opportunity";
                                                    string Subject = MessageType.到访提醒.ToString();
                                                    string Content = "客户" + Convert.ToString(parameter["LastName"]) + Convert.ToString(parameter["FirstName"]) + "、" + Convert.ToString(parameter["Mobile"]) + MessageType.到访提醒.ToString();
                                                    string Receiver = ConvertHelper.ToString(parameter["SaleUserID"]);
                                                    new SystemMessageService().Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.到访提醒, true, debug);
                                                }
                                                //客户到访
                                                string msgs = string.Empty;
                                                JObject res = (JObject)JsonDataHelper.GetNormalData("RemindRuleArriveDetail_Select", parameter, out msgs, debug);
                                                if (res["ClueID"] != null && !string.IsNullOrEmpty(res["ClueID"].ToString()) && res["ReportUserID"] != null && !string.IsNullOrEmpty(res["ReportUserID"].ToString()))
                                                {
                                                    string UserID = ConvertHelper.ToString(parameter["UserID"]);
                                                    string ProjectID = ConvertHelper.ToString(parameter["ProjectID"]);
                                                    string Content = "客户" + Convert.ToString(res["LastName"]) + Convert.ToString(res["FirstName"]) + "、" + Convert.ToString(res["Mobile"]) + "(" + MessageType.到访提醒 + ")";
                                                    new SystemMessageService().Detail_Insert(UserID, ProjectID, res["ClueID"].ToString(), "Clue", "客户到访提醒", Content, res["ReportUserID"].ToString(), MessageType.带看通知, true, debug);
                                                }
                                            }
                                            //增加跟进记录
                                            if (!string.IsNullOrEmpty(FollwUpType))
                                            {
                                                JObject obj = new JObject();
                                                obj.Add("FollwUpType", FollwUpType);
                                                obj.Add("SalesType", 1);
                                                obj.Add("NewSaleUserName", "");
                                                obj.Add("OldSaleUserName", "");
                                                obj.Add("FollwUpUserID", ConvertHelper.ToString(parameter["UserID"]));
                                                obj.Add("FollwUpWay", FollwUpWay);
                                                obj.Add("FollowUpContent", FollwUpType);
                                                obj.Add("IntentionLevel", IntentionLevel);//默认D级
                                                obj.Add("OrgID", ConvertHelper.ToString(parameter["OrgID"]));
                                                obj.Add("FollwUpUserRole", ConvertHelper.ToString(parameter["JobID"]));
                                                obj.Add("OpportunityID", ConvertHelper.ToString(parameter["OpportunityID"]));
                                                obj.Add("ClueID", "");
                                                obj.Add("NextFollowUpDate", "");
                                                new CustomerActionRepository(obj, debug).CustomerFollowUp_Insert();
                                            }
                                            CustomerOpportunityFollowUpDetail_Update(parameter, debug);//客户机会跟进记录更新
                                        }
                                    }
                                }

                            }
                            else
                            {
                                entity.ErrCode = 1;
                                entity.ErrMsg = "失败";
                            }
                        }
                        else
                        {
                            entity.ErrCode = 1;
                            entity.ErrMsg = "参数缺失！";
                        }
                    }
                    else
                    {
                        entity.ErrCode = 1;
                        entity.ErrMsg = "手机号码为空！";
                    }
                }
                else
                {
                    entity.ErrCode = 1;
                    entity.ErrMsg = "参数格式错误！";

                }

            }
            else
            {
                entity.ErrCode = 1;
                entity.ErrMsg = "参数不完整！";
            }
        }
        catch (Exception ex)
        {
            entity.ErrCode = 1;
            entity.ErrMsg = "服务器异常！";

            throw ex;
        }*/

        return entity;
	}

	@Override
	public Result CounselorList(JSONObject paramAry) {
        Result entity = new Result();
        try{
            StringBuilder whereSb = new StringBuilder();
            StringBuilder OrderSb = new StringBuilder();
            String Filter = paramAry.getString("Filter");
            if (!StringUtils.isEmpty(Filter)){
                whereSb.append(" and GroupID in('" + Filter + "')");
            }
            String Sort = paramAry.getString("Sort");
            if (!StringUtils.isEmpty(Sort)){
                if (Sort.equals("362B95DC-9712-D990-8C71-A980754E1FD2")){
                    whereSb.append(" ORDER BY  DayTotalCount desc");
                }
                if (Sort.equals("5C50B541-83FE-EFA1-53B5-C1D8192CB8EF")){
                    whereSb.append(" ORDER BY  DayTotalCount asc ");
                }
            }
            paramAry.put("WHERE", whereSb.toString());
            paramAry.put("ORDER", OrderSb.toString());

            paramAry.put("SiteUrl", SiteUrl);
            Map<String,Object> pmap =JSONObject.parseObject(paramAry.toJSONString(), Map.class);
            List<Map<String, Object>> data = vCustomerfjlistSelectMapper.sCustomerFJAdviserList_Select(pmap);
            entity.setData(data);
            entity.setErrcode(0);
	        entity.setErrmsg("成功");
        }catch (Exception e){
        	entity.setErrcode(1);
	        entity.setErrmsg("系统异常");
			e.printStackTrace();
        }
        return entity;
	}

}
