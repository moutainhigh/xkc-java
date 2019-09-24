package com.tahoecn.xkc.service.customer.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.date.DateUtil;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomerfjlistSelectMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IPartReceiveService;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.sys.ISystemAccountService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

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
	@Value("${WXDetailPagePath}")
	private String WXDetailPagePath;
	@Value("${ImgSiteUrl}")
	private String ImgSiteUrl;
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private ISystemMessageService iSystemMessageService;
	@Resource
	private ISystemAccountService iSystemAccountService;
	@Resource
	private IVCustomergwlistSelectService iVCustomergwlistSelectService;
	
	
	@Override
	public Result CustomerList(JSONObject paramAry) {
		Result entity = new Result();
		try {
			StringBuilder whereSb = new StringBuilder();
	        StringBuilder orderSb = new StringBuilder();
	        //手机/或姓名
	        String Mobile = paramAry.getString("Mobile");
	        if(!StringUtils.isEmpty(Mobile)){
	        	whereSb.append(" and (CustomerName Like '%"+Mobile+"%' or CustomerMobile Like '%"+Mobile+"%' or SpareMobile Like '%"+Mobile+"%') ");
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
	        
	        IPage<VCustomerfjlistSelect> page =new Page<>();
	    	page.setSize(paramAry.getLongValue("PageSize"));
	    	page.setCurrent(paramAry.getLongValue("PageIndex"));
	    	
	    	IPage<VCustomerfjlistSelect> data = vCustomerfjlistSelectMapper.sCustomerFJList_Select(page,pmap);
	    	if(data!=null && data.getRecords()!=null && data.getRecords().size()>0){
	    		for(VCustomerfjlistSelect vCustomerfjlistSelect : data.getRecords()){
	    			String customerMobile = vCustomerfjlistSelect.getCustomerMobile();
	    			if(!StringUtils.isEmpty(customerMobile)){
	    				String a = customerMobile.substring(0, 3);
	    				String b = customerMobile.substring(7);
	    				vCustomerfjlistSelect.setCustomerMobile(a+"****"+b);
	    			}
	    			String adviserMobile = vCustomerfjlistSelect.getAdviserMobile();
	    			if(!StringUtils.isEmpty(adviserMobile)){
	    				String a = adviserMobile.substring(0, 3);
	    				String b = adviserMobile.substring(7);
	    				vCustomerfjlistSelect.setAdviserMobile(a+"****"+b);
	    			}
	    		}
	    	}
	    	JSONObject j_data = new JSONObject();
	    	j_data.put("List", data.getRecords());
	    	j_data.put("AllCount", data.getTotal());
	    	j_data.put("PageSize", data.getSize());
	        entity.setData(j_data);
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
	@Transactional(readOnly=false)
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
	                if (StringUtils.isEmpty(SaleUserID)){
	                    entity.setErrcode(1);
	        	        entity.setErrmsg("不能跟进公共客户！");
	                    return entity;
	                }
	            }
	            if (StringUtils.isEmpty(SaleUserID) ){//登记公共客户
	                jsonFile = "FJNewCustomer.json";
	                customerModeType = CustomerModeType.分接_老机会_老客户_公共客户.getTypeID();
	                isNew = 0;
	            }
	            if (SaleUserID!=null && SaleUserID.length() > 0 && !SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//不为无
	                jsonFile = "FJOldCustomer.json";
	                customerModeType = CustomerModeType.分接_老机会_老客户.getTypeID();
	                isNew = 0;
	            }
	            if (SaleUserID!=null && SaleUserID.length() > 0 && SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//为无
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
	                if (OldCustomerObj!=null && OldCustomerObj.size() > 0){//本项目存在历史机会,直接分配给之前的顾问
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
	        if(customerModel!=null){
	        	customerModel.setIsNew(isNew);
	        }
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
		entity.setErrcode(0);
		entity.setErrmsg("成功");
        try{
            if (!StringUtils.isEmpty(paramAry.getString("FormSessionID"))){
            	Map<String,Object> pmap =JSONObject.parseObject(paramAry.toJSONString(), Map.class);
            	Long RowCount = vCustomerfjlistSelectMapper.mSystemFormSessionStatus_Select_step1(pmap);
            	if(RowCount.intValue()==0){
            		vCustomerfjlistSelectMapper.mSystemFormSessionStatus_Select_step2(pmap);
            	}else{
            		entity.setErrcode(1);
            		entity.setErrmsg("不能重复请求！");
                    return entity;
            	}
            }

            JSONObject CustomerObj = new JSONObject();
            CGWDetailModel model =JSONObject.parseObject(paramAry.toJSONString(), CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
                //初始化参数
            	JSONObject parameter = customerTemplate.GetParameters(model);
            	parameter.put("VisitAddress", "");
            	parameter.put("IsIPad", "0");
                String OldSaleUserID = "";
                String SaleUserID = parameter.getString("SaleUserID");
                if (parameter!=null && parameter.size() > 0){
                    Boolean IsNew = true;
                    String sqlKey = "";
                    String Mobile =parameter.getString("Mobile");
                    if (!StringUtils.isEmpty(Mobile)){
                    	JSONObject re_j = customerTemplate.CustomerOpportunityExist(model.getProjectID(), Mobile);
                    	if(re_j.getBooleanValue("status")){
                    		CustomerObj = re_j.getJSONObject("CustomerObj");
                    		//存在老机会_老客户 
                    		parameter.put("CustomerID", CustomerObj.getString("CustomerID"));
                    		parameter.put("OpportunityID", CustomerObj.getString("OpportunityID"));
                            OldSaleUserID = CustomerObj.getString("SaleUserID");
                            parameter.put("LastName", CustomerObj.getString("LastName"));
                            parameter.put("FirstName", CustomerObj.getString("FirstName"));
                            if (!StringUtils.isEmpty(OldSaleUserID) && !"C4C09951-FA39-4982-AAD1-E72D9D4C3899".equals(OldSaleUserID)){
                                SaleUserID = OldSaleUserID;
                                parameter.put("SaleUserID", OldSaleUserID);
                            }
                            sqlKey = "mCustomerFJDetail_Update";
                            IsNew = false;
                            //公共客户不允许分接跟进
                            if (!"XSZC".equals(paramAry.getString("JobCode"))){
                                if (OldSaleUserID==null||OldSaleUserID.length() == 0){
                                    entity.setErrcode(1);
                            		entity.setErrmsg("不能跟进公共客户！");
                                    return entity;
                                }
                            }
                            parameter.put("IsReAlloc", "0");
                        }else{//新机会
                        	JSONObject re_j_1 = customerTemplate.CustomerExist(Mobile);
                            if (!re_j_1.getBooleanValue("status")){
                            	//新机会_新客户
                                parameter.put("CustomerID", UUID.randomUUID().toString());
                                //1.根据手机号码查询拓客客户信息 
                                JSONObject re_j_2 = customerTemplate.CustomerPotentialExist(Mobile);
                                if (re_j_2.getBooleanValue("status")){
                                	//存在拓客客户信息
                                	CustomerObj = re_j_2.getJSONObject("CustomerObj");
                                	parameter.put("CardType", CustomerObj.getString("CardType"));
                                    parameter.put("CardID",CustomerObj.getString("CardID"));
                                    parameter.put("AcceptFactor",CustomerObj.getString("AcceptFactor"));
                                    parameter.put("AgeGroup", CustomerObj.getString("AgeGroup"));
                                    parameter.put("DomicilePlace",CustomerObj.getString("DomicilePlace"));
                                    parameter.put("HomeAddress",CustomerObj.getString("HomeAddress"));
                                    parameter.put("HomeArea",CustomerObj.getString("HomeArea"));
                                    parameter.put("WorkArea",CustomerObj.getString("WorkArea"));
                                    parameter.put("Marriage",CustomerObj.getString("Marriage"));
                                    parameter.put("Family",CustomerObj.getString("Family"));
                                    parameter.put("Industry",CustomerObj.getString("Industry"));
                                    parameter.put("PropertyNum",CustomerObj.getString("PropertyNum"));
                                }
                                sqlKey = "mCustomerFJDetail_Insert";
                            }else{//新机会_老客户
                            	CustomerObj = re_j_1.getJSONObject("CustomerObj");
                            	parameter.put("CustomerID", CustomerObj.getString("CustomerID"));
                            	JSONObject OldCustomerObj = customerTemplate.OpportunityInfo(model.getProjectID(), Mobile);
                                if (OldCustomerObj!=null && OldCustomerObj.size() > 0){
                                	//本项目存在历史机会,直接分配给之前的顾问
                                    parameter.put("LastName",OldCustomerObj.getString("LastName"));
                                    parameter.put("FirstName",OldCustomerObj.getString("FirstName"));
                                }
                                sqlKey = "mOldCustomerFJDetail_Insert";
                            }
                            parameter.put("OpportunityID",UUID.randomUUID().toString());
                            IsNew = true;
                        }
                        //销售轨迹类别
                    	parameter.put("TrackType", "BC2F967F-8FFE-1F52-49F6-CBCDFE8D044A");
                        //来电/来访
                        int status ="E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType")) ? 2 : 1;
                        parameter.put("Status", status);
                        String ClueID = "";
                        if (IsNew){
                            //客户来源
                            String OpportunitySource="";
                            Map<String, String> re_map = customerTemplate.GetOpportunitySource(parameter);
                            ClueID = re_map.get("clueID")!=null?re_map.get("clueID").toString():"";
                            OpportunitySource = re_map.get("opportunitySourceID")!=null?re_map.get("opportunitySourceID").toString():"";
                            parameter.put("OpportunitySource",OpportunitySource);
                            parameter.put("ClueID", ClueID);
                        }
                        String IntentionLevel = CustomerObj.getString("CustomerLevel");
                        if (StringUtils.isEmpty(IntentionLevel)){
                            IntentionLevel = "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D";
                        }
                        String VisitType = parameter.getString("VisitType");
                        String FollwUpWay = "";
                        if ("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(VisitType)){
                        	//来访
                            FollwUpWay = "E30825AA-B894-4A5F-AF55-24CAC34C8F1F";
                        }else{//来电
                            FollwUpWay = "A79A1057-D4DC-497C-8C81-8F93E422C819";
                        }
                        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(FollwUpWay);
                        if (SaleUserID.length() > 0){
                        	parameter.put("Name", parameter.getString("LastName")+parameter.getString("FirstName"));
                            Map<String,Object> pmap =JSONObject.parseObject(parameter.toJSONString(), Map.class);
                            if(sqlKey.equals("mCustomerFJDetail_Update")){
                            	Map<String, Object> step1_map = vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step1(pmap);
                            	if(step1_map!=null && step1_map.size()>0){
                            		int Status = parameter.getIntValue("Status");
                                	int IsIPad = parameter.getIntValue("IsIPad");
                                	List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4});
                                	String tSaleUserID= step1_map.get("SaleUserID").toString();
                                	Number number = (Number)step1_map.get("Status");
                                	int tStatus = number.intValue();
                                	if(Status==2 && list.contains(tStatus) && (tSaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899") || tSaleUserID.equals(""))){
                                		vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step2(pmap);
                                	}
                                	String tSalePartnerID = null;
                                	String tFirstVisitAddress = step1_map.get("FirstVisitAddress").toString();
                                	String tReVisitAddress = step1_map.get("ReVisitAddress").toString();
                                	if(step1_map.get("SalePartnerID")!=null){
                                		tSalePartnerID = step1_map.get("SalePartnerID").toString();
                                	}
                                	if(IsIPad==1 && Status==2 && tSalePartnerID==null && "".equals(tFirstVisitAddress) && "".equals(tReVisitAddress) && !StringUtils.isEmpty(parameter.getString("VisitAddress")) && parameter.getIntValue("IsReAlloc")==0 ){
                                		vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step3(pmap);
                                	}
                                	if(IsIPad==1 && Status==2 && !tSaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899") && tSalePartnerID==null && "".equals(tReVisitAddress) && !tFirstVisitAddress.equals(parameter.getString("VisitAddress")) && parameter.getIntValue("IsReAlloc")==1){
                                		vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step4(pmap);
                                	}
                                	if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
                                		pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                                		pmap.put("UpDownStatus", 1);
                                		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                                	}
                                	vCustomerfjlistSelectMapper.mCustomerFJDetail_Update_step5(pmap);
                            	}
                            	
                            }else if(sqlKey.equals("mCustomerFJDetail_Insert")){
                            	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step1(pmap);
                            	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step2(pmap);
                            	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step3(pmap);
                            	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step4(pmap);
                            	
                            	pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                            	pmap.put("UpDownStatus", 1);
                            	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                            	if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
                            		pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                            		pmap.put("UpDownStatus", 1);
                            		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                            	}
                            	vCustomerfjlistSelectMapper.mCustomerFJDetail_Insert_step5(pmap);
                            	pmap.put("ClueID","");
                            	vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);
                            	
                            }else if(sqlKey.equals("mOldCustomerFJDetail_Insert")){
                            	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step1(pmap);
                            	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step2(pmap);
                            	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step3(pmap);
                            	pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                            	pmap.put("UpDownStatus", 1);
                            	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                            	if("E0C5FDD1-800B-39F5-1A20-C0A5A3C3B450".equals(parameter.getString("VisitType"))){
                            		pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                            		pmap.put("UpDownStatus", 1);
                            		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                            	}
                            	vCustomerfjlistSelectMapper.mOldCustomerFJDetail_Insert_step4(pmap);
                            	pmap.put("ClueID","");
                            	vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);
                            }
                            Boolean result = true;
                            if (result){
                                if (IsNew){
                                    //更新线索信息
                                	customerTemplate.ClueUpdate(parameter);
                                    if (!SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//不为无
                                    	//判断分配的置业顾问是否为无
                                        if (entity.getErrcode() == 0){
                                            String UserID = parameter.getString("UserID");
                                            String ProjectID = parameter.getString("ProjectID");
                                            String BizID = parameter.getString("OpportunityID");
                                            String BizType = "Opportunity";
                                            String Subject = MessageType.分配待跟进.getTypeID();
                                            String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" +  parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                            String Receiver = parameter.getString("SaleUserID");
                                            iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver,  MessageType.分配待跟进.getTypeID(), true);
                                        }
                                        //客户分配
                                        if(!StringUtils.isEmpty(ClueID)){
                                        	Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(ClueID);
                                        	if(re_map_step1!=null && re_map_step1.size()>0){
                                        		String protectSource = re_map_step1.get("ProtectSource")!=null?re_map_step1.get("ProtectSource").toString():"";
                                        		if(!StringUtils.isEmpty(protectSource)){
                                        			String projectID = parameter.getString("ProjectID");
                                                    Map<String,Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                                    if(re_map_step2!=null && re_map_step2.size()>0){
                                                    	int AllotRemind=0;
                                        				if(re_map_step2.get("AllotRemind")!=null){
                                        					Number number = (Number)re_map_step2.get("AllotRemind");
                                        					AllotRemind = number.intValue();
                                        				}
                                                    	String ReportUserID = "";
                                                        ClueID = "";
                                                        if(AllotRemind>0){
                                                        	if(re_map_step1.get("ReportUserID")!=null){
                                                        		ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                                        	}
                                                        	if(re_map_step1.get("ClueID")!=null){
                                                        		ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                                        	}
                                                        }
                                                        if (!"".equals(ClueID) && !"".equals(ReportUserID)){
                                                        	if (!SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){
                                                        		String UserID = parameter.getString("UserID");
                                                                String ProjectID = parameter.getString("ProjectID");
                                                                String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + "(客户分配提醒)";
                                                                iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.系统通知.getTypeID(), true);
                                                        	}
                                                        }
                                                    }
                                        		}
                                        	}
                                        }
                                        //客户到访
                                        if ("售场接待".equals(FollwUpType)){//售场接待
                                        	Map<String,Object> pmp = JSONObject.parseObject(parameter.toJSONString(), Map.class);
                            				customerTemplate.sendKHDFMsg(pmp);
                                        }
                                        String userID = parameter.getString("SaleUserID");
                                        Result Account = iSystemAccountService.SystemAccountDetail_Select(userID);
                                        String NewSaleUserName = "";
                                        if (Account.getErrcode() == 0 && Account.getData()!=null){
                                        	JSONObject AccountData = (JSONObject) Account.getData();
                                            if (AccountData.size() > 0){
                                                NewSaleUserName = AccountData.getString("EmployeeName");
                                            }
                                        }
                                        JSONObject obj1 = new JSONObject();
                                        obj1.put("FollwUpType", "分配顾问");
                                        obj1.put("FollwUpTypeID", ActionType.分配顾问.getValue());
                                        obj1.put("SalesType", 1);
                                        obj1.put("NewSaleUserName", NewSaleUserName);
                                        obj1.put("OldSaleUserName", "");
                                        obj1.put("FollwUpUserID",parameter.getString("UserID"));
                                        obj1.put("FollwUpWay", "");
                                        obj1.put("FollowUpContent", "");
                                        obj1.put("IntentionLevel", "");
                                        obj1.put("OrgID",parameter.getString("OrgID"));
                                        obj1.put("FollwUpUserRole",parameter.getString("JobID"));
                                        obj1.put("OpportunityID", parameter.getString("OpportunityID"));
                                        obj1.put("ClueID", "");
                                        obj1.put("NextFollowUpDate", "");
                                        CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                    }
                                    //增加跟进记录
                                    if (!StringUtils.isEmpty(FollwUpType)){
                                    	JSONObject obj = new JSONObject();
                                        obj.put("FollwUpType", FollwUpType);
                                        obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                                        obj.put("SalesType", 1);
                                        obj.put("NewSaleUserName", "");
                                        obj.put("OldSaleUserName", "");
                                        obj.put("FollwUpUserID",parameter.getString("UserID"));
                                        obj.put("FollwUpWay", FollwUpWay);
                                        obj.put("FollowUpContent", FollwUpType);
                                        obj.put("IntentionLevel", IntentionLevel);//默认D级
                                        obj.put("OrgID",parameter.getString("OrgID"));
                                        obj.put("FollwUpUserRole", parameter.getString("JobID"));
                                        obj.put("OpportunityID", parameter.getString("OpportunityID"));
                                        obj.put("ClueID", "");
                                        obj.put("NextFollowUpDate", "");
                                        CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                    }
                                    String opportunityID = parameter.getString("OpportunityID");
                                    String userID = parameter.getString("UserID");
                                    iVCustomergwlistSelectService.CustomerOpportunityFollowUpDetail_Update(opportunityID, userID);//客户机会跟进记录更新
                                }else{
                                    if (!SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//不为无
                                    	//判断分配的置业顾问是否为无
                                        if (OldSaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){//原顾问为无,新顾问不为无,发送分配待跟进消息和分配跟进记录
                                            if (entity.getErrcode() == 0){
                                                String UserID = parameter.getString("UserID");
                                                String ProjectID = parameter.getString("ProjectID");
                                                String BizID = parameter.getString("OpportunityID");
                                                String BizType = "Opportunity";
                                                String Subject = MessageType.分配待跟进.getTypeID();
                                                String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                                String Receiver = parameter.getString("SaleUserID");
                                                iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.分配待跟进.getTypeID(), true);
                                            }
                                            //客户分配
                                            String fClueID = parameter.getString("ClueID");
                                            if(!StringUtils.isEmpty(fClueID)){
                                                Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(fClueID);
                                                if(re_map_step1!=null && re_map_step1.size()>0){
                                                	String protectSource = re_map_step1.get("ProtectSource")!=null?re_map_step1.get("ProtectSource").toString():"";
                                                	if(!StringUtils.isEmpty(protectSource)){
                                                		String projectID = parameter.getString("ProjectID");
                                                        Map<String,Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                                        if(re_map_step2!=null && re_map_step2.size()>0){
                                                        	int AllotRemind=0;
                                            				if(re_map_step2.get("AllotRemind")!=null){
                                            					Number number = (Number)re_map_step2.get("AllotRemind");
                                            					AllotRemind = number.intValue();
                                            				}
                                                        	String ReportUserID = "";
                                                            ClueID = "";
                                                            if(AllotRemind>0){
                                                            	if(re_map_step1.get("ReportUserID")!=null){
                                                            		ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                                            	}
                                                            	if(re_map_step1.get("ClueID")!=null){
                                                            		ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                                            	}
                                                            }
                                                            if (!"".equals(ClueID) && !"".equals(ReportUserID)){
                                                            	if (!SaleUserID.equals("C4C09951-FA39-4982-AAD1-E72D9D4C3899")){
                                                            		String UserID = parameter.getString("UserID");
                                                                    String ProjectID = parameter.getString("ProjectID");
                                                                    String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + "(客户分配提醒)";
                                                                    iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.系统通知.getTypeID(), true);
                                                            	}
                                                            }
                                                        }
                                                	}
                                                }
                                            }
                                            String userID = parameter.getString("SaleUserID");
                                            Result Account = iSystemAccountService.SystemAccountDetail_Select(userID);
                                            String NewSaleUserName = "";
                                            if (Account.getErrcode() == 0 && Account.getData()!=null){
                                            	JSONObject AccountData = (JSONObject) Account.getData();
                                                if (AccountData.size() > 0){
                                                    NewSaleUserName =AccountData.getString("EmployeeName");
                                                }
                                            }
                                            JSONObject obj1 = new JSONObject();
                                            obj1.put("FollwUpType", "分配顾问");
                                            obj1.put("FollwUpTypeID", ActionType.分配顾问.getValue());
                                            obj1.put("SalesType", 1);
                                            obj1.put("NewSaleUserName", NewSaleUserName);
                                            obj1.put("OldSaleUserName", "");
                                            obj1.put("FollwUpUserID",parameter.getString("UserID"));
                                            obj1.put("FollwUpWay", "");
                                            obj1.put("FollowUpContent", "");
                                            obj1.put("IntentionLevel", "");
                                            obj1.put("OrgID", parameter.getString("OrgID"));
                                            obj1.put("FollwUpUserRole",parameter.getString("JobID"));
                                            obj1.put("OpportunityID", parameter.getString("OpportunityID"));
                                            obj1.put("ClueID", "");
                                            obj1.put("NextFollowUpDate", "");
                                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                                            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                        }else{
                                            if (FollwUpWay.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){
                                                if (entity.getErrcode() == 0 && model.getIsSend().equals("1")){
                                                    String UserID = parameter.getString("UserID");
                                                    String ProjectID = parameter.getString("ProjectID");
                                                    String BizID = parameter.getString("OpportunityID");
                                                    String BizType = "Opportunity";
                                                    String Subject = MessageType.到访提醒.getTypeID();
                                                    String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                                    String Receiver = parameter.getString("SaleUserID");
                                                    iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.到访提醒.getTypeID(), true);
                                                }
                                                //客户到访
                                                String fClueID = parameter.getString("ClueID");
                                                if(!StringUtils.isEmpty(fClueID)){
                                                    Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(fClueID);
                                                    if(re_map_step1!=null && re_map_step1.size()>0){
                                                    	String protectSource = String.valueOf(re_map_step1.get("ProtectSource"));
                                                        String projectID = parameter.getString("ProjectID");
                                                        Map<String,Object> re_map_step2 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step2(projectID, protectSource);
                                                        String ReportUserID = "";
                                                        ClueID = "";
                                                        Number AllotRemind = (Number)re_map_step2.get("AllotRemind");
                                                        if(AllotRemind.intValue()>0){
                                                        	if(re_map_step1.get("ReportUserID")!=null){
                                                        		ReportUserID = String.valueOf(re_map_step1.get("ReportUserID"));
                                                        	}
                                                        	if(re_map_step1.get("ClueID")!=null){
                                                        		ClueID = String.valueOf(re_map_step1.get("ClueID"));
                                                        	}
                                                        }
                                                        if (!"".equals(ClueID) && !"".equals(ReportUserID)){
                                                        	String UserID = parameter.getString("UserID");
                                                            String ProjectID = parameter.getString("ProjectID");
                                                            String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + MessageType.到访提醒.getTypeID();
                                                            iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.带看通知.getTypeID(), true);
                                                        }
                                                    }
                                                }
                                            }
                                            //增加跟进记录
                                            if (!StringUtils.isEmpty(FollwUpType)){
                                            	JSONObject obj = new JSONObject();
                                                obj.put("FollwUpType", FollwUpType);
                                                obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                                                obj.put("SalesType", 1);
                                                obj.put("NewSaleUserName", "");
                                                obj.put("OldSaleUserName", "");
                                                obj.put("FollwUpUserID", parameter.getString("UserID"));
                                                obj.put("FollwUpWay", FollwUpWay);
                                                obj.put("FollowUpContent", FollwUpType);
                                                obj.put("IntentionLevel", IntentionLevel);//默认D级
                                                obj.put("OrgID", parameter.getString("OrgID"));
                                                obj.put("FollwUpUserRole", parameter.getString("JobID"));
                                                obj.put("OpportunityID", parameter.getString("OpportunityID"));
                                                obj.put("ClueID", "");
                                                obj.put("NextFollowUpDate", "");
                                                CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                                                iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                                            }
                                            String opportunityID = parameter.getString("OpportunityID");
                                            String userID = parameter.getString("UserID");
                                            iVCustomergwlistSelectService.CustomerOpportunityFollowUpDetail_Update(opportunityID, userID);//客户机会跟进记录更新
                                        }
                                    }
                                }

                            }
                            else{
                                entity.setErrcode(1);
                                entity.setErrmsg("失败");
                            }
                        }else{
                        	entity.setErrcode(1);
                            entity.setErrmsg("参数缺失！");
                        }
                    }else{
                    	entity.setErrcode(1);
                        entity.setErrmsg("手机号码为空！");
                    }
                }else{
                    entity.setErrcode(1);
                    entity.setErrmsg("参数格式错误！");
                }
            }else{
                entity.setErrcode(1);
                entity.setErrmsg("参数不完整！");
            }
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            throw e;
        }
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
            
            IPage<Map<String,Object>> page =new Page<>();
	    	page.setSize(paramAry.getLongValue("PageSize"));
	    	page.setCurrent(paramAry.getLongValue("PageIndex"));
            
            Map<String,Object> pmap =JSONObject.parseObject(paramAry.toJSONString(), Map.class);
            String StatusDate = DateUtil.format(new Date(), "yyyy-MM-dd");
            pmap.put("StatusDate", StatusDate);
            IPage<Map<String, Object>> data = vCustomerfjlistSelectMapper.sCustomerFJAdviserList_Select_part(page,pmap);
            JSONObject j_data = new JSONObject();
	    	j_data.put("List", data.getRecords());
	    	j_data.put("AllCount", data.getTotal());
	    	j_data.put("PageSize", data.getSize());
            entity.setData(j_data);
            entity.setErrcode(0);
	        entity.setErrmsg("成功");
        }catch (Exception e){
        	entity.setErrcode(1);
	        entity.setErrmsg("系统异常");
			e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mUserAuthorDetail_Select(JSONObject Parameter) {
		Result re = new Result();
        try{
        	Map<String,Object> pmap =JSONObject.parseObject(Parameter.toJSONString(), Map.class);
        	
        	//String UnionID="";
        	String UserID="";
        	String AdviserGroupID ="";
        	String ShareProjectID="";
        	String NewID = "";
        	String ID = "";
        	Long Count = 0l;
        	
        	Map<String,Object> get1 = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get1(pmap);
        	Map<String,Object> get2 = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get2(pmap);
        	if(get1!=null && get1.size()>0){
        		ShareProjectID = get1.get("ShareProjectID").toString();
        	}
        	if(get2!=null && get2.size()>0){
        		if(get1.get("UserID")!=null){
        			UserID = get1.get("UserID").toString();
        		}
        		if(get1.get("ID")!=null){
        			ID = get1.get("ID").toString();
        		}
        	}
        	AdviserGroupID = Parameter.getString("JobID");
        	Map<String,Object> data = null;
        	if("".equals(ID)){
        		Count = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_getCount(pmap);
        		if(Count.intValue()>0){
        			data = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get3(pmap);
        		}else{
        			pmap.put("ShareProjectID", ShareProjectID);
        			pmap.put("AdviserGroupID", AdviserGroupID);
        			NewID = UUID.randomUUID().toString();
        			pmap.put("NewID", NewID);
        			vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_Insert(pmap);
        			data = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get4(pmap);
        			
        		}
        	}else{
        		if(!"".equals(UserID)){
        			if(UserID.equals(Parameter.getString("UserID"))){
        				pmap.put("TID", ID);
        				data = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get5(pmap);
        			}else{
        				data = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get6(pmap);
        			}
        		}else{
        			Count = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_getCount(pmap);
        			if(Count.intValue()>0){
        				data = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get3(pmap);
        			}else{
        				pmap.put("AdviserGroupID", AdviserGroupID);
        				pmap.put("TID", ID);
        				vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_update(pmap);
        				data = vCustomerfjlistSelectMapper.mUserAuthorDetail_Select_get5(pmap);
        			}
        		}
        	}
        	
           /* String WXDetailPagePath = WXDetailPagePath;//项目详情路径
            String imgSiteUrl = ImgSiteUrl;//图片路径
*/            if(data==null || data.size()==0){
            	re.setErrcode(1);
            	re.setErrmsg("失败");
            	return re;
            }
         /*   if ("0".equals(data.get("Msg").ToString())){
                re.setErrmsg("绑定成功");
                JSONObject dat = new JSONObject();
                dat.put("WXUserID", data.get("WXUserID"));
                Parameter.put("WXUserID", data.get("WXUserID"));
                //生成小程序二维码
                string codeId = Utils.CreateGUID();
                string QRCodeUrl = string.Empty;
                JObject jObj = new JObject();
                jObj.Add("ID", codeId);
                jObj.Add("ProjectID", Parameter["ProjectID"].ToString());
                jObj.Add("IdentityID", Parameter["JobID"].ToString());
                jObj.Add("CodeParameter", Parameter["UserID"].ToString());
                jObj.Add("Category", Parameter["JobCode"].ToString() == "GW" ? 2 : 3);
                jObj.Add("Creator", Parameter["UserID"].ToString());
                JObject flg = (JObject)JsonDataHelper.GetNormalData("mShareSaleUserQRCode_Insert", jObj, out errmsg);
                string HeadImg = string.Empty;
                string VisitingCardPic = string.Empty;
                StringBuilder Upd = new StringBuilder();
                if (flg["Msg"] != null && flg["Msg"].ToString() == "0")
                {
                    if (flg["ShareProjectID"] != null && flg["ShareProjectID"].ToString() != "")
                    {
                        QRCodeUrl = Utils.CreateWXQRCode(codeId, WXDetailPagePath);
                    }
                    else
                    {
                        QRCodeUrl = Utils.CreateWXQRCode(codeId);
                    }
                    if (flg["HeadImg"] != null && flg["HeadImg"].ToString() != "")
                    {
                        HeadImg = flg["HeadImg"].ToString();
                    }
                    if (!string.IsNullOrEmpty(QRCodeUrl))
                    {
                        JObject channerPara = new JObject();
                        //如果有用户头像 则把小程序二维码和用户头像合成一张图片 如果没有用户头像 则用小程序二维码就可以
                        if (!string.IsNullOrEmpty(HeadImg))
                        {
                            //检测当前头像是否可用 ，不可用则用小程序二维码
                            string url = context.Server.MapPath(HeadImg);
                            //检测当前头像是否可用 ，不可用则用小程序二维码
                            if (System.IO.File.Exists(url))
                            {
                                VisitingCardPic = Utils.CreateWXCardImg(QRCodeUrl, HeadImg);
                            }
                            else
                            {
                                VisitingCardPic = QRCodeUrl;
                            }
                        }
                        else
                        {
                            VisitingCardPic = QRCodeUrl;
                        }
                    }
                }
                if (!string.IsNullOrEmpty(VisitingCardPic))
                {
                    Upd.AppendFormat(" QRCodeUrl='{0}',", VisitingCardPic);
                }
                Parameter.Add("Upd", Upd.ToString());
                JObject bean = (JObject)JsonDataHelper.GetNormalData("mUserH5QRCodeUrlDetail_Select", Parameter, out errmsg);
                Task.Run(() => {
                    TaskBindUser(Parameter);
                });
                
                re.Data = dat;
                re.ErrCode = 0;
            }
            else if (data["Msg"].ToString() == "1")
            {
                re.ErrMsg = "该用户已绑定过微信";
                re.ErrCode = 1;
            }
            else if (data["Msg"].ToString() == "2")
            {
                re.ErrMsg = "该微信已绑定用户";
                re.ErrCode = 2;
            }
        }
        catch (Exception ex)
        {
            re.ErrMsg = ex.Message;
            re.ErrCode = 99;
        }*/
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return re;
	}

	public static void main(String[] args) {
		String customerMobile = "15812345678";
		
		String a = customerMobile.substring(0, 3);
		String b = customerMobile.substring(7);
		System.out.println(a+"****"+b);
	}
}
