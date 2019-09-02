package com.tahoecn.xkc.service.customer.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.date.DateUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.CustomerModeType;
import com.tahoecn.xkc.common.enums.MessageHandleType;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.customer.UpdateCustinfoLog;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.model.vo.FilterItem;
import com.tahoecn.xkc.model.vo.PanelItem;
import com.tahoecn.xkc.service.customer.ICustomerHelp;
import com.tahoecn.xkc.service.customer.IProjectService;
import com.tahoecn.xkc.service.customer.IUpdateCustinfoLogService;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

/**
 * <p>
 * 1 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly=true)
public class VCustomergwlistSelectServiceImpl implements IVCustomergwlistSelectService {
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	@Resource
	private ISystemMessageService iSystemMessageService;
	@Resource
	private ICustomerHelp customerTemplate;
	@Resource
	private IProjectService iProjectService;
	@Value("${SiteUrl}")
    private String SiteUrl;
	@Autowired
    private IUpdateCustinfoLogService iUpdateCustinfoLogService;
	
	@Override
	public Result customerList(GWCustomerPageDto model) {
		Result entity = new Result();
        try {
        	setParamForCustomerList(model);
        	//设置分页
        	List<Map<String,Object>> data = vCustomergwlistSelectMapper.sCustomerGWListNew_Select(model);
        	List<String> OpportunityID_list = new ArrayList<String>();
        	if(data!=null && data.size()>0){
        		for(Map<String,Object> data_map : data){
        			OpportunityID_list.add(data_map.get("OpportunityID").toString());
        		}
        	}
        	Map<String,List<Map<String,Object>>> childs = new HashMap<>();
        	try {
        		List<Map<String,Object>> data_child = vCustomergwlistSelectMapper.SelectOpportunityByParentID(OpportunityID_list);
            	if(data_child!=null && data_child.size()>0){
            		for(Map<String,Object> data_child_map : data_child){
            			String tkey = data_child_map.get("ParentID").toString();
            			if(!childs.containsKey(tkey)){
            				List<Map<String,Object>> list = new ArrayList<>();
            				list.add(data_child_map);
            				childs.put(tkey, list);
            			}else{
            				childs.get(tkey).add(data_child_map);
            			}
            		}
            	}
            	if(childs!=null && childs.size()>0){
            		for(Map<String,Object> data_map : data){
            			String tkey = data_map.get("OpportunityID").toString();
            			if(childs.containsKey(tkey)){
            				data_map.put("childItem", childs.get(tkey));
            			}
            		}
            	}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	Long allCount = vCustomergwlistSelectMapper.sCustomerGWListNew_Select_count(model);
        	Map<String,Object> re = new HashMap<String, Object>();
        	re.put("List", data);
        	re.put("AllCount", allCount);
        	re.put("PageSize", model.getPageSize());
        	
        	String dataStr = new JSONObject(re).toJSONString();
            JSONObject data_re = JSONObject.parseObject(dataStr);
        	entity.setData(data_re);
        	entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrmsg("服务器异常");
			entity.setErrcode(1);
			e.printStackTrace();
		}
		return entity;
	}
	
	@Override
	public Result customerListForSetChild(GWCustomerPageDto model) {
		Result entity = new Result();
        try {
        	if(model.getOpportunityID()==null || "".equals(model.getOpportunityID())){
        		entity.setErrmsg("父ID不能为空");
    			entity.setErrcode(1);
    			return entity;
        	}
        	List<String> OpportunityID_list = new ArrayList<String>();
        	OpportunityID_list.add(model.getOpportunityID());
        	try {
        		List<Map<String,Object>> data_child = vCustomergwlistSelectMapper.SelectOpportunityByParentID(OpportunityID_list);
            	if(data_child!=null && data_child.size()>0){
            		for(Map<String,Object> data_child_map : data_child){
            			OpportunityID_list.add(data_child_map.get("OpportunityID").toString());
            		}
            	}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	model.setOpportunityIDList(OpportunityID_list);
        	setParamForCustomerList(model);
        	List<Map<String,Object>> data = vCustomergwlistSelectMapper.sCustomerGWListNew_Select_forUpdateChild(model);
        	Long allCount = vCustomergwlistSelectMapper.sCustomerGWListNew_Select_forUpdateChild_count(model);
        	Map<String,Object> re = new HashMap<String, Object>();
        	re.put("List", data);
        	re.put("AllCount", allCount);
        	re.put("PageSize", model.getPageSize());
        	
        	String dataStr = new JSONObject(re).toJSONString();
            JSONObject data_re = JSONObject.parseObject(dataStr);
        	entity.setData(data_re);
        	entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrmsg("服务器异常");
			entity.setErrcode(1);
			e.printStackTrace();
		}
		return entity;
	}
	
	private void setParamForCustomerList(GWCustomerPageDto model){
		try {
			StringBuilder whereSb = new StringBuilder();
        	StringBuilder orderSb = new StringBuilder();
        	if(!StringUtils.isEmpty(model.getKeyWord())){
        		whereSb.append(" and (CustomerName Like '%"+model.getKeyWord()+"%' or CustomerMobile Like '%"+model.getKeyWord()+"%' or SpareMobile Like '%"+model.getKeyWord()+"%') ");
        	}
        	if(model.getFilter() != null && model.getFilter().size()>0){
        		for(FilterItem filterItem:model.getFilter()){
        			if(filterItem!=null && filterItem.getTagID()!=null){
        				if(filterItem.getTagID().equals("3775DF2B-5BF4-F5A3-9D07-6B3B2549CE47")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						whereSb.append(" and  IsCare ='1'");
        					}
        				}
        				//客户状态
        				if(filterItem.getTagID().equals("DB251A8A-23BB-73B4-E586-FA67379EE732")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						for(int i = 0; i < filterItem.getChild().size(); i++){
        							switch (filterItem.getChild().get(i)) {
									case "17018427-C01B-0E2A-E62D-900B50EF2581":
										filterItem.getChild().set(i, "1");
										break;
									case "6A91A1AB-FC05-2BC0-8C08-0C3FCB07D865":
										filterItem.getChild().set(i, "2','3");
										break;
									case "3962D6C3-1141-F62F-2181-9F6AA82E0A6C":
										filterItem.getChild().set(i, "3");
										break;
									case "3089CC46-2736-2B3C-3B9C-6D44D059ACCD":
										filterItem.getChild().set(i, "4");
										break;
									case "E9C2D9B3-4538-CBDD-73DE-AEA7444DB20E":
										filterItem.getChild().set(i, "5");
										break;
									case "43A67B86-E775-158F-F897-30E78F5C8D4C":
										filterItem.getChild().set(i, "7");
										break;
									}
        						}
        						whereSb.append(" and OpportunityStatusValue in('" + String.join("','", filterItem.getChild()) + "')");
        					}
        				}
        				//意向级别
        				if(filterItem.getTagID().equals("9BB9797E-8A64-08D5-4754-4189D185D3A9")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						for(int i = 0; i < filterItem.getChild().size(); i++){
        							switch (filterItem.getChild().get(i)) {
									case "CB61E620-7589-C99B-F834-7EA3203D75AB":
										filterItem.getChild().set(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA");
										break;
									case "ABC6231B-225A-1EC6-8BD6-5165D911BD4E":
										filterItem.getChild().set(i, "DF2057E2-303B-1F14-4075-069668D3A3BE");
										break;
									case "55EE9A36-4A34-36C6-5B77-8BC1227A156C":
										filterItem.getChild().set(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D");
										break;
									case "B8922623-E5E4-04BF-1A0B-DEE35CBC9A08":
										filterItem.getChild().set(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E");
										break;
									case "AE005A89-BB77-2E81-A8BA-05CB619654B5":
										filterItem.getChild().set(i, "84640879-F4A7-CB87-E39B-F18070BCA568");
										break;
									}
        						}
        						whereSb.append(" and CustomerLevel in('" + String.join("','", filterItem.getChild()) + "')");
        					}
        				}
        				//标签
        				if(filterItem.getTagID().equals("432680C6-9872-0A1F-4BEA-3F1D0EE73095")){
        					if(filterItem.getChild()!=null && filterItem.getChild().size()>0){
        						whereSb.append(" and CustomerID in(select  CustomerID  from  B_CustomerTag  t where t.TagName in('" + String.join("','", filterItem.getChild()) + "'))");
        					}
        				}
        			}
        		}
        	}
        	
        	if(!StringUtils.isEmpty(model.getSort())){
        		if (model.getSort().equals("F5F8788E-EB27-E49D-86D6-BCFAFC181493")){//最新跟进
                    orderSb.append(" ORDER BY  TheLatestFollowUpDate desc ");
                }
                if (model.getSort().equals("7A5EDE88-5EBB-D5EB-C3F3-45959B855B14")){//最早跟进
                    orderSb.append(" ORDER BY  TheLatestFollowUpDate  asc ");
                }
                if (model.getSort().equals("CC0DE53C-A4A7-FB72-36EE-6BFAA4820B5C")){//最新创建
                    orderSb.append(" ORDER BY  CreateTime desc ");
                }
                if (model.getSort().equals("47C99D99-FBB2-F7DC-2384-5DE728E9D657")){//最早创建
                    orderSb.append(" ORDER BY CreateTime asc ");
                }
        	}else{
        		orderSb.append(" ORDER BY  TheLatestFollowUpDate Desc ");
        	}
        	if(orderSb.length()==0){
        		orderSb.append(" ORDER BY  TheLatestFollowUpDate Desc ");
        	}
        	model.setWhere(whereSb.toString());
        	model.setOrder(orderSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Result updateParentID(JSONObject paramAry) {
		Result entity = new Result();
		try {
			if(!paramAry.containsKey("OpportunityID") || "".equals(paramAry.getString("OpportunityID"))){
				entity.setErrmsg("父ID不能为空");
				entity.setErrcode(1);
				return entity;
			}
			if(!paramAry.containsKey("Child")){
				entity.setErrmsg("子集ID不能为空");
				entity.setErrcode(1);
				return entity;
			}
			String OpportunityID = paramAry.getString("OpportunityID");
			JSONArray childs = paramAry.getJSONArray("Child");
			for(int i=0;i<childs.size();i++){
				JSONObject child = childs.getJSONObject(i);
				vCustomergwlistSelectMapper.UpdateOpportunityParentID(OpportunityID, child.getString("Relation"),child.getString("ChildID"));
			}
			entity.setErrmsg("成功");
			entity.setErrcode(0);
		} catch (Exception e) {
			entity.setErrmsg("服务器异常");
			entity.setErrcode(1);
			e.printStackTrace();
		}
		return entity;
	}
	
	@Override
	public Result deleteParentID(JSONObject paramAry) {
		Result entity = new Result();
		try {
			String childID = paramAry.getString("ChildID");
			vCustomergwlistSelectMapper.DeleteOpportunityParentID(childID);
			entity.setErrmsg("成功");
			entity.setErrcode(0);
		} catch (Exception e) {
			entity.setErrmsg("服务器异常");
			entity.setErrcode(1);
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Result customerBase(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String opportunityID = paramAry.getString("OpportunityID");
        	String projectID = paramAry.getString("ProjectID");
        	HashMap<String,Object> objData =vCustomergwlistSelectMapper.sCustomerGWBase_Select(opportunityID,projectID);
        	if(objData!=null && objData.size()>0){
        		 //获取顾问和协作人信息
                paramAry.put("SiteUrl", SiteUrl);
                List<HashMap<String,Object>> gwInfo = vCustomergwlistSelectMapper.sCustomerGWBaseSalerInfo_Select(opportunityID, projectID,SiteUrl);
                objData.put("AdviserList", gwInfo);
                String dataStr = new JSONObject(objData).toJSONString();
                JSONObject data = JSONObject.parseObject(dataStr);
                
                Map<String,Object> clueData =vCustomergwlistSelectMapper.sCustomerGWBase_Select_GetClue(opportunityID);
                JSONObject token = new JSONObject();
                if(clueData!=null && clueData.size()>0){
                	 token.put("TokerUserID",clueData.get("ReportUserID"));
                     token.put("ClueMobile",clueData.get("CustomerMobile"));
                     token.put("ClueID", clueData.get("ClueID"));
                     token.put("SourceType",clueData.get("SourceType"));
                     
                     Map<String,Object> pmap = new HashMap<String, Object>();
                     pmap.put("ClueID", clueData.get("ClueID"));
                     Map<String,Object> job = bCustomerpotentialMapper.mCustomerPotentialZQTractDetail_Select(pmap);
     	        	 if(job!=null && job.size()>0){
     	        		 data.put("TractSort", job.get("Sort"));
     		        	 data.put("TractName", job.get("Name"));
     		        	 data.put("TractTime", job.get("TractTime"));
     	        	 }
                     try {
                     	String CreateTime = clueData.get("CreateTime").toString();
                        String TractTime = data.getString("TractTime");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        
                        if(CreateTime!=null && !CreateTime.equals("")){
                         	data.put("CreateTime",sdf.format(DateUtil.parse(CreateTime)));
                        }
                        if(TractTime!=null && !TractTime.equals("")){
                         	data.put("TractTime", sdf.format(DateUtil.parse(TractTime)));
                       }
         			} catch (Exception e) {
         				e.printStackTrace();
         			}
                }
                data.put("Token", token);
                entity.setData(data);
                entity.setErrmsg("成功");
        	}else{
        		 entity.setErrcode(1);
                 entity.setErrmsg("失败");
        	}
        }catch (Exception e){	
            entity.setErrcode(110);
            entity.setErrmsg("获取数据异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mCustomerFollowUpList_Select(JSONObject paramAry) {
		Result re = new Result();
		try {
			Map<String,Object> pmp = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
			if(paramAry.getString("OpportunityID")==null){
				pmp.put("OpportunityID", paramAry.getString("ClueID"));
			}
	    	List<Map<String,Object>> data = vCustomergwlistSelectMapper.mCustomerFollowUpList_Select(pmp);
	    	Long AllCount = vCustomergwlistSelectMapper.mCustomerFollowUpList_Select_Count(pmp);
	    	JSONObject j_data = new JSONObject();
	    	j_data.put("List", data);
	    	j_data.put("AllCount", AllCount);
	    	j_data.put("PageSize", paramAry.getInteger("PageSize"));
	    	re.setData(j_data);
	        re.setErrmsg("成功");
	        re.setErrcode(0);
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("系统异常！");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result TrackList(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	IPage<Map<String,Object>> page =new Page<Map<String,Object>>();
         	page.setSize(paramAry.getLongValue("PageSize"));
         	page.setCurrent(paramAry.getLongValue("PageIndex"));
         	String opportunityID = paramAry.getString("OpportunityID");
         	IPage<Map<String,Object>> data = vCustomergwlistSelectMapper.sCustomerTrackList_Select(page, opportunityID);
         	JSONObject j_data = new JSONObject();
        	j_data.put("List", data.getRecords());
        	j_data.put("AllCount", data.getTotal());
        	j_data.put("PageSize", data.getSize());
         	entity.setData(j_data);
         	entity.setErrmsg("成功");
         	entity.setErrcode(0);
        }
        catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	@Transactional(readOnly=false)
	public Result mCustomerFollowUpDetail_Insert(JSONObject paramAry) {
		Result re = new Result();
		String opportunityID = paramAry.getString("OpportunityID")!=null?paramAry.getString("OpportunityID"):"";
		String userID = paramAry.getString("UserID");
		String customerID = paramAry.getString("CustomerID");
		String mode = paramAry.getString("Mode");
		List<Map<String,Object>> list = vCustomergwlistSelectMapper.mCustomerSalePartnerIsCanOperate_Select(opportunityID,userID);
		if(list!=null && list.size()>0){
			re.setErrcode(1);
            re.setErrmsg("开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作");
            return re;
		}
		//客户跟进记录新增
		if(!StringUtils.isEmpty(mode)){
			if(mode.equals("EEB32C04-5B7C-4676-A5DC-5F95E56370EB") || mode.equals("44775694-7C97-455C-B48E-154C6BFE2D94") || mode.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){
				vCustomergwlistSelectMapper.mCustomerFollowUpDetail_Insert(opportunityID, customerID, userID, mode);
			}
		}
		String follwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(mode);
		
		if(!StringUtils.isEmpty(follwUpType)){
			String OpportunityID = paramAry.getString("OpportunityID");
			if(OpportunityID!=null && !"".equals(OpportunityID)){
				//获取子集信息
				List<String> opportunityIDlist = new ArrayList<String>();
				opportunityIDlist.add(OpportunityID);
				
				try {
					List<Map<String, Object>> childs = vCustomergwlistSelectMapper.SelectOpportunityByParentID(opportunityIDlist);
					if(childs!=null && childs.size()>0){
						for(Map<String, Object> child : childs){
							opportunityIDlist.add(child.get("OpportunityID").toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				for(String oppoID : opportunityIDlist){
					CustomerActionVo customerActionVo = new CustomerActionVo();
					customerActionVo.setFollwUpType(follwUpType);
					customerActionVo.setFollwUpTypeID(ActionType.valueOf(follwUpType).getValue());
					customerActionVo.setSalesType(1);
					customerActionVo.setNewSaleUserName("");
					customerActionVo.setOldSaleUserName("");
					customerActionVo.setFollwUpUserID(paramAry.getString("UserID"));
					customerActionVo.setFollwUpWay(paramAry.getString("Mode"));
					customerActionVo.setFollowUpContent(paramAry.getString("Content"));
					customerActionVo.setIntentionLevel(paramAry.getString("Level"));
					customerActionVo.setOrgID(paramAry.getString("OrgID"));
					customerActionVo.setFollwUpUserRole(paramAry.getString("JobID"));
					customerActionVo.setOpportunityID(oppoID);
					customerActionVo.setClueID("");
					customerActionVo.setNextFollowUpDate(paramAry.getString("NextTime"));
					re = this.CustomerFollowUp_Insert(customerActionVo);
				}
			}
			if (follwUpType.equals("售场接待")){
                //客户到访
                String clueID = paramAry.getString("ClueID");
                String projectID = paramAry.getString("ProjectID");
                Map<String,Object> res = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select(opportunityID, clueID);
                
                String tclueID = "";
                String reportUserID = "";
                String protectSource = "";
                if(res!=null && res.get("clueID")!=null){
                	tclueID = res.get("clueID").toString();
                }
                if(res!=null && res.get("reportUserID")!=null){
                	reportUserID = res.get("reportUserID").toString();
                }
                if(res!=null && res.get("protectSource")!=null){
                	protectSource = res.get("protectSource").toString();
                }
                if (!StringUtils.isEmpty(tclueID) &&  !StringUtils.isEmpty(reportUserID)){
                	String LastName = "";
                	String FirstName = "";
                	String Mobile = "";
                	if(!"".equals(protectSource)){
                    	Map<String,Object> resf =vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_f(projectID, protectSource);
                    	int customerVisitsRemind = 0;
                    	if(resf!=null && resf.get("customerVisitsRemind")!=null){
                    		Number number = (Number)resf.get("customerVisitsRemind");
                    		customerVisitsRemind = number.intValue();
                    	}
                    	if(customerVisitsRemind>0){
                    		Map<String,Object> ress = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_s(tclueID);
                    		if(ress!=null && ress.get("LastName")!=null){
                    			LastName = ress.get("LastName").toString();
                    		}
                    		if(ress!=null && ress.get("FirstName")!=null){
                    			FirstName = ress.get("FirstName").toString();
                    		}
                    		if(ress!=null && ress.get("Mobile")!=null){
                    			Mobile = ress.get("Mobile").toString();
                    		}
                    	}
                    }
                    String UserID =paramAry.getString("UserID");
                    String ProjectID = paramAry.getString("ProjectID");
                    String Content = "客户" +LastName +FirstName + "、" + Mobile + "(" + MessageType.到访提醒.getTypeID()+ ")";
                    Map<String,Object> parameter = new HashMap<String,Object>();
                    parameter.put("ProjectID", ProjectID);
                    parameter.put("BizID", res.get("ClueID").toString());
                    parameter.put("BizType", "Clue");
                    parameter.put("Subject", "客户到访提醒");
                    parameter.put("Content", Content);
                    parameter.put("Receiver",res.get("ReportUserID").toString());
                    parameter.put("MessageType",MessageType.带看通知.getTypeID());
                    parameter.put("Sender", UserID);
                    parameter.put("Creator", UserID);
                    parameter.put("IsNeedPush", true);
                    iSystemMessageService.SystemMessageDetail_Insert(parameter);
                }
            }
		}
		CustomerOpportunityFollowUpDetail_Update(opportunityID,userID);//客户机会跟进记录更新
        //处理跟进类待办为已办
		String[] BizIDs = new String[]{opportunityID};
		iSystemMessageService.DetailByHandle_Update(BizIDs, "Opportunity", MessageHandleType.新增跟进.getValue());
		re.setErrcode(0);
        re.setErrmsg("成功");
        return re;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Result CustomerOpportunityFollowUpDetail_Update(String opportunityID,String userID){
		Result re = new Result();
		try {
	        vCustomergwlistSelectMapper.CustomerOpportunityFollowUpDetail_Update(opportunityID, userID);
	        if (!StringUtils.isEmpty(opportunityID)){
	            vCustomergwlistSelectMapper.mClueTradeOverdueTime_Update(opportunityID);
	        }
	        re.setErrmsg("成功");
	        re.setErrcode(0);
		} catch (Exception e) {
			re.setErrmsg("失败");
	        re.setErrcode(1);
			e.printStackTrace();
		}
        return re;
    }
	
	@Override
	@Transactional(readOnly=false)
	public Result CustomerFollowUp_Insert(CustomerActionVo customerActionVo) {
		Result re = new Result();
		try {
			//FollowUpContent
			customerActionVo.setFollowUpContent(GetFollowUpContent(customerActionVo));
			//Creator
			if(StringUtils.isEmpty(customerActionVo.getCreator())){
				customerActionVo.setCreator(customerActionVo.getFollwUpUserID());
			}
			//Editor
			if(StringUtils.isEmpty(customerActionVo.getEditor())){
				customerActionVo.setEditor(customerActionVo.getFollwUpUserID());
			}
			if (!StringUtils.isEmpty(customerActionVo.getClueID())){
				vCustomergwlistSelectMapper.CFCreateClueFollowUp_Insert(customerActionVo);
	        }
	        if (!StringUtils.isEmpty(customerActionVo.getOpportunityID())){
	        	vCustomergwlistSelectMapper.CFCreateOppFollowUp_Insert(customerActionVo);
	        }
	        re.setErrmsg("添加成功");
	        re.setErrcode(0);
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("添加失败");
			e.printStackTrace();
		}
        return re;
		
	}
	
	public String GetFollowUpContent(CustomerActionVo customerActionVo){
        String message = null;
        try
        {
            switch (customerActionVo.getFollwUpTypeID())
            {
                case "69331990-DBF4-0A2F-80CD-7BC424AA8901":
                    message = "报备成功";
                    break;
                case "69331990-DBF4-0A2F-80CD-7BC424AA8902":
                    message = getChannelReportContent(customerActionVo.getClueID());
                    break;
                case "69331990-DBF4-0A2F-80CD-7BC424AA8903":
                    message = getChannelReportEnableContent(customerActionVo.getClueID());
                    break;
                case "69331990-DBF4-0A2F-80CD-7BC424AA8904":
                    message = getChannelReportDisableContent(customerActionVo.getClueID());
                    break;
                case "69331990-DBF4-0A2F-80CD-7BC424AA8905":
                    message = getSaleUserAllotContent(customerActionVo.getNewSaleUserName());
                    break;
                case "69331990-DBF4-0A2F-80CD-7BC424AA8911":
                    message = getCustomerReleaseContent(customerActionVo.getSalesType(),customerActionVo.getOpportunityID(),customerActionVo.getClueID());
                    break;
                case "69331990-DBF4-0A2F-80CD-7BC424AA8912":
                    message = getSaleUserAllotReContent(customerActionVo.getOldSaleUserName(), customerActionVo.getNewSaleUserName());
                    break;
                default:
                    message = customerActionVo.getFollowUpContent();
                    break;
            }
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return message;
    }
	
	public JSONObject getClue(String clueID){
		Map<String,Object> data = vCustomergwlistSelectMapper.CFGetClueRule_Select(clueID);
		JSONObject json = new JSONObject(data);
		return json;
	}
	
	private JSONObject getOppRule(String oppID){
		Map<String,Object> data = vCustomergwlistSelectMapper.CFGetOppRule_Select(oppID);
		JSONObject json = new JSONObject(data);
		return json;
    }
	
	private Boolean isOwnerChannel(String channelTypeId){
		Map<String,Object> data = vCustomergwlistSelectMapper.CFIsOwnerChannel_Select(channelTypeId);
		if(data!=null && data.size()>0){
			return true;
		}else{
			return false;
		}
    }
	
	private String getChannelReportContent(String clueID){
		String message = null;
        //获取线索对应的规则
		JSONObject rule = getClue(clueID);
        //如果渠道规则为报备保护，返回规则类型+到访保护逾期截止时间
        if (rule.getIntValue("RuleType")== 0){
            String channelTypeId =rule.getString("AdviserGroupID");
            //如果是自渠
            if (isOwnerChannel(channelTypeId)){
                message = "报备成功";
            }
            else{
                message = String.format("报备保护模式，到访保护期截止到%s",rule.getString("ComeOverdueTime"));
            }
        }
        else{
            if (rule.getIntValue("IsSelect")== 1){
                message = String.format("竞争带看模式，未确客,到访保护期截止到%s",rule.getString("ComeOverdueTime"));
            }
            else{
                message = String.format("竞争带看模式，未确客,成交保护期截止到%s",rule.getString("TradeOverdueTime"));
            }
        }
        //如果渠道为竞争带看，返回
        return message;
    }
	
	private String getChannelReportEnableContent(String clueID){
        String message = null;
        //获取线索
        JSONObject rule = getClue(clueID);
        message = String.format("有效带看(%s)，成交保护期截止到(%s)",rule.getString("TradeOverdueTime"),rule.getString("ReportUserName"));
        return message;
    }
	
	private String getChannelReportDisableContent(String clueID){
		String message =null;
        //获取线索
		JSONObject rule = getClue(clueID);
        message = String.format("报备无效，无效原因(%s)",rule.getString("InvalidReason"));
        return message;
    }
	
	private String getSaleUserAllotContent(String name){
		String message = null;
        message = String.format("分配给顾问(%s)", name);
        return message;
    }
	
	private String getSaleUserAllotReContent(String oldName, String newName){
		String message = null;
        message = String.format("从原顾问(%s)分配给新顾问(%s)", oldName, newName);
        return message;
    }
	
	private String getCustomerReleaseContent(int salesType,String opportunityID,String clueID){
		String message = null;
		JSONObject rule = null;
        //获取对应的规则
        switch (salesType){
            //自销
            case 1:
                rule = getOppRule(opportunityID);
                message = String.format("超过(%s)天未跟进，系统回收",rule.getString("OwnerReleasePeriod"));
                break;
            //代理
            case 2:
                rule = getOppRule(opportunityID);
                message = String.format("超过(%s)天未跟进，系统回收",rule.getString("AgentReleasePeriod"));
                break;
            //自渠
            case 3:
                rule = getClue(clueID);
                message = String.format("超过(%s)天未跟进，系统回收",rule.getString("FollowUpOverdueDays"));
                break;
        }
        return message;
    }

	@Override
	@Transactional(readOnly=false)
	public Result UserFollowDetail_Insert(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String opportunityID = paramAry.getString("OpportunityID");
        	String userID = paramAry.getString("UserID");
        	vCustomergwlistSelectMapper.sUserFollowDetail_Insert_delete(opportunityID, userID);
        	vCustomergwlistSelectMapper.sUserFollowDetail_Insert(opportunityID, userID);
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(110);
            entity.setErrmsg("关注客户异常！");
            throw new RuntimeException();
        }
        return entity;
	}

	@Override
	public Result UserFollowDetail_Delete(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String opportunityID = paramAry.getString("OpportunityID");
        	String userID = paramAry.getString("UserID");
        	vCustomergwlistSelectMapper.sUserFollowDetail_Delete(opportunityID, userID);;
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
        	entity.setErrcode(110);
            entity.setErrmsg("取消关注客户异常！");
            throw new RuntimeException();
        }
        return entity;
	}

	@Override
	public Result CustomerTagAdd(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String tagName = paramAry.getString("TagName");
        	if(!StringUtils.isEmpty(tagName)){
        		List<Map<String,Object>> valid = vCustomergwlistSelectMapper.CustomerTagDetail_InsertValid(tagName);
                if (valid!=null && valid.size() > 0){
                    entity.setErrcode(1);
                    entity.setErrmsg("不能添加重复标签");
                    return entity;
                }
                String customerID = paramAry.getString("CustomerID");
            	String userID = paramAry.getString("UserID");
            	vCustomergwlistSelectMapper.CustomerTagDetail_Insert_delete(customerID, userID);
            	vCustomergwlistSelectMapper.CustomerTagDetail_Insert(customerID, userID, tagName);
            	entity.setErrcode(0);
                entity.setErrmsg("成功");
        	}else{
        		entity.setErrcode(1);
                entity.setErrmsg("失败，数据异常");
        	}
        }catch (Exception e){
            entity.setErrcode(110);
            entity.setErrmsg("添加客户标签异常！");
            throw new RuntimeException();
        }
        return entity;
	}

	@Override
	public Result TagAdd(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String tagName = paramAry.getString("TagName");
        	String userID = paramAry.getString("UserID");
        	if(StringUtils.isEmpty(tagName) || StringUtils.isEmpty(userID)){
        		entity.setErrcode(1);
                entity.setErrmsg("参数异常");
                return entity;
        	}
        	List<Map<String,Object>> valid = vCustomergwlistSelectMapper.CustomerTagDetail_InsertValid(tagName);
            if (valid!=null && valid.size() > 0){
            	entity.setErrcode(111);
                entity.setErrmsg("重复添加！");
                return entity;
            }
            List<Map<String,Object>> obj = vCustomergwlistSelectMapper.TagDetail_Select(userID, tagName);
            if (obj==null || obj.size()== 0){
                vCustomergwlistSelectMapper.TagDetail_Insert(userID, tagName);
                entity.setErrcode(0);
                entity.setErrmsg("成功");
            }else{
                entity.setErrcode(111);
                entity.setErrmsg("重复添加！");
            }
        }catch (Exception e){
            entity.setErrcode(110);
            entity.setErrmsg("添加客户标签异常！");
            throw new RuntimeException();
        }
        return entity;
	}

	@Override
	public Result TagList(JSONObject paramAry) {
		Result re = new Result();
		String userID = paramAry.getString("UserID");
		List<Map<String, Object>> data = vCustomergwlistSelectMapper.TagList_Select(userID);
		re.setData(data);
        re.setErrmsg("成功");
        re.setErrcode(0);
        return re;
	}

	@Override
	public Result IntentProjectList(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String projectID = paramAry.getString("ProjectID");
        	String customerID = paramAry.getString("CustomerID");
        	String opportunityID = paramAry.getString("OpportunityID");
        	List<Map<String, Object>> result = vCustomergwlistSelectMapper.sCustomerAttachList_Select(projectID, customerID, opportunityID);
        	if(result!=null && result.size()>0){
        		for(Map<String, Object> map : result){
        			String ID = map.get("ID").toString();
        			List<Map<String, Object>> childResult= vCustomergwlistSelectMapper.sSubscribeInfoDetails_Select(ID);
        			if(childResult!=null && childResult.size()>0){
        				map.put("CustomerAttachList", childResult);
        			}else{
        				map.put("CustomerAttachList", null);
        			}
        		}
        	}else{
        		result = null;
        	}
    		entity.setData(result);
            entity.setErrmsg("成功");
            entity.setErrcode(0);
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            throw new RuntimeException();
        }
        return entity;
	}

	@Override
	public Result ProjectList(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String projectID = paramAry.getString("ProjectID");
        	String opportunityID = paramAry.getString("OpportunityID");
            String whereSb = String.format(" and PID='%s' and level='%s' and ID not in(SELECT IntentProjectID FROM B_CustomerAttach WHERE   OpportunityID ='%s') ", projectID,2,opportunityID);
            List<Map<String, Object>> result = vCustomergwlistSelectMapper.sProject_Select(whereSb);
    		entity.setData(result);
            entity.setErrmsg("成功");
            entity.setErrcode(0);
        }catch (Exception ex){
        	 entity.setErrcode(1);
             entity.setErrmsg("服务器异常！");
             throw new RuntimeException();
        }
        return entity;
	}

	@Override
	public Result Detail(JSONObject paramAry) {
		Result entity = new Result();
        try{
            CSearchModelVo model = JSONObject.parseObject(paramAry.toJSONString(), CSearchModelVo.class);
            CustomerModelVo customerModel=null;
            String opportunityID = model.getOpportunityID();
            JSONObject CustomerObj = customerTemplate.OpportunityInfo(opportunityID);
            if (!CustomerObj.isEmpty()){
                customerModel = customerTemplate.InitCustomerModeData(model, "GWDetailCustomer.json", CustomerObj, CustomerModeType.顾问_客户_详情.getTypeID());
                List<PanelItem> PanelItems = customerModel.getPanel();
                for(PanelItem PanelItem : PanelItems){
                	if("成员信息".equals(PanelItem.getName())){
                		List<String> parentID = new ArrayList<>();
                		parentID.add(opportunityID);
                		try {
                			List<Map<String, Object>> childs = vCustomergwlistSelectMapper.SelectOpportunityByParentID(parentID);
                    		if(childs!=null && childs.size()>0){
                    			JSONArray J_DATA = new JSONArray();
                    			for(Map<String, Object> map : childs){
                    				String ChildID = map.get("OpportunityID").toString();
                    				String ParentRelation ="";
                    				if(map.get("ParentRelation")!=null){
                    					ParentRelation = map.get("ParentRelation").toString();
                    				}
                    				String CustomerName = map.get("CustomerName").toString();
                    				String CustomerMobile = map.get("CustomerMobile").toString();
                    				String OpportunityStatusValue = map.get("OpportunityStatusValue").toString();
                    				JSONObject json = new JSONObject();
                    				json.put("ChildID", ChildID);
                    				json.put("ParentRelation", ParentRelation);
                    				json.put("CustomerName", CustomerName);
                    				json.put("CustomerMobile", CustomerMobile);
                    				json.put("OpportunityStatusValue", OpportunityStatusValue);
                    				J_DATA.add(json);
                    			}
                    			PanelItem.getChild().get(0).setChild(J_DATA);
                    		}
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
                	}
                }
                entity.setData(customerModel);
                entity.setErrmsg("成功");
                entity.setErrcode(0);
            }else{
            	entity.setErrmsg("不存在客户信息！");
                entity.setErrcode(11);
            }
            
        }catch (Exception e){
            entity.setErrmsg("获取数据异常！");
            entity.setErrcode(110);
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result IntentProjectAdd(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String customerID = paramAry.getString("CustomerID");
        	String projectID = paramAry.getString("ProjectID");
        	String intentProjectID = paramAry.getString("IntentProjectID");
        	String opportunityID = paramAry.getString("OpportunityID");
        	String userID = paramAry.getString("UserID");
        	Map<String,Object> obj = vCustomergwlistSelectMapper.CustomerAttachDetail_Select(customerID, projectID, intentProjectID, opportunityID);
            if (obj==null || obj.size() == 0){
            	vCustomergwlistSelectMapper.CustomerAttachDetail_Insert(customerID, projectID, intentProjectID, opportunityID, userID);
                //同步明源客户数据
            	customerTemplate.SyncCustomer(opportunityID, 0);
            }
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrmsg("服务器异常！");
            entity.setErrcode(1);
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result GiveUpDetail(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	CSearchModelVo model = JSONObject.parseObject(paramAry.toJSONString(), CSearchModelVo.class);
            CustomerModelVo customerModel = new CustomerModelVo();
            String opportunityID = model.getOpportunityID();
            JSONObject CustomerObj = customerTemplate.OpportunityInfo(opportunityID);
            if (CustomerObj!=null && CustomerObj.size() > 0){
                String customerModeType = CustomerModeType.顾问_客户_详情.getTypeID();
                customerModel = customerTemplate.InitCustomerModeData(model, "GWDetailCustomer.json", CustomerObj,customerModeType);
                entity.setData(customerModel);
                entity.setErrmsg("成功");
                entity.setErrcode(0);
            }else{
                entity.setErrmsg("不存在客户信息！");
                entity.setErrcode(11);
            }
        }catch (Exception e){
        	 entity.setErrmsg("服务器异常！");
             entity.setErrcode(1);
             e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result GiveUpApplyAdd(JSONObject paramAry) {
		Result entity = new Result();
		try {
	        String opportunityID = paramAry.getString("OpportunityID");
	        Map<String,Object> obj = vCustomergwlistSelectMapper.sOpportunityGiveUpDetail_Exists(opportunityID);
	        if (obj!=null && obj.size() == 0){
	            String LostID =UUID.randomUUID().toString();
				Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
	            pmap.put("LostID", LostID);
	            vCustomergwlistSelectMapper.sOpportunityGiveUpDetail_Insert(pmap);
	            String UserID = paramAry.getString("UserID");
                String ProjectID = paramAry.getString("ProjectID");
                String BizID = LostID;
                String BizType = "OpportunityGiveUp";
                String Subject = MessageType.客户丢失通知.getTypeID();
                String Content = MessageType.客户丢失通知.getTypeID();
                
                List<Map<String,Object>> userArray = vCustomergwlistSelectMapper.SystemMessageSaleUserYXJL_Select(ProjectID);
                for (Map<String,Object> item : userArray){
                    String Receiver = String.valueOf(item.get("SaleUserID"));
                    iSystemMessageService.Detail_Insert(UserID, ProjectID, BizID, BizType, Subject, Content, Receiver, MessageType.客户丢失通知.getTypeID(), true);
                }
	        }
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
	public Result GiveUpStatusFind(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String opportunityID = paramAry.getString("OpportunityID");
        	Map<String,Object> result = vCustomergwlistSelectMapper.sOpportunityGiveUpDetail_Exists(opportunityID);
        	JSONObject obj = new JSONObject();
            if (result!=null && result.size() > 0){//有申请
                String approvalStatus = String.valueOf(result.get("ApprovalStatus"));
                if (approvalStatus == "0"){//申请中
                    obj.put("Status", "1");
                }
                if (approvalStatus == "1"){//已驳回
                    obj.put("Status", "2");
                }
                obj.put("Reason",String.valueOf(result.get("Reason")));
                obj.put("Status", "");
            }else{//没有申请
                obj.put("Status", "0");
                obj.put("Reason", "");
            }
            entity.setData(obj);
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result DealDetail(JSONObject paramAry) {
		Result entity = new Result();
        try{
        	String signID = paramAry.getString("SignID");
        	Map<String,Object> result = vCustomergwlistSelectMapper.sSignInfoDetail_Select(signID);
            if (result!=null && result.size() > 0){
            	List<Map<String,Object>> childResult = vCustomergwlistSelectMapper.sPaymentNodeList_Select(signID);
                if (childResult.size() > 0){
                    result.put("MoneyList", childResult);
                }else{
                    result.put("MoneyList", null);
                }
                List<Map<String,Object>> NoMoneyList = new ArrayList<Map<String,Object>>();
                
                for (Map<String,Object> item : childResult){
                	Number number = (Number)item.get("IsPay");
                    if (number.intValue() == 0){
                        NoMoneyList.add(item);
                    }
                }
                result.put("NoMoneyList", NoMoneyList);
            }
            entity.setData(result);
            entity.setErrmsg("成功");
            entity.setErrcode(0);
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result mUserDetail_Update(JSONObject Parameter) {
		Result re = new Result();
        String JobCode = Parameter.getString("JobCode");
        switch (JobCode.toUpperCase()){
            case "FJ":
                re = this.UserDetail_Update(Parameter);
                break;
            case "GW":
                re = this.UserDetail_Update(Parameter);
                break;
            case "XSJL":
                re = this.UserDetail_Update(Parameter);
                break;
            case "YXJL":
                re = this.UserDetail_Update(Parameter);
                break;
            case "JZ":
                re = this.JZDetail_Update(Parameter);
                break;
            case "ZQ":
                re = this.UserDetail_Update(Parameter);
                break;
            case "ZQFZR":
                re = this.UserDetail_Update(Parameter);
                break;
            default:
                break;
        }
        return re;
	}
	
	@Override
	public Result UserDetail_Update(JSONObject Parameter){
		Result re = new Result();
		try {
			String Update = "";
	        String HeadImg =Parameter.getString("HeadImg");
	        if (!StringUtils.isEmpty(HeadImg)){
	            Update += String.format(" HeadImg='%s',", HeadImg);
	        }
	        String Name = Parameter.getString("Name");
	        if (!StringUtils.isEmpty(Name)){
	            Update += String.format(" Name='%s',", Name);
	        }
	        if (StringUtils.isEmpty(Update)){
	        	re.setErrcode(90);
	        	re.setErrmsg("无效请求");
	            return re;
	        }
	        Parameter.put("Update", Update);
	        
			Map<String,Object> pmap = JSONObject.parseObject(Parameter.toJSONString(), Map.class);
			vCustomergwlistSelectMapper.SalesUserDetail_Update(pmap);
	        re.setErrcode(0);
	    	re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("服务器异常！");
            e.printStackTrace();
		}
        return re;
    }
	
	@Override
	public Result JZDetail_Update(JSONObject Parameter){
		Result re = new Result();
		try {
			String Update = "";
	        String HeadImg =Parameter.getString("HeadImg");
	        if (!StringUtils.isEmpty(HeadImg)){
	            Update += String.format(" HeadImg='%s',", HeadImg);
	        }
	        if (StringUtils.isEmpty(Update)){
	        	re.setErrcode(90);
		    	re.setErrmsg("无效请求");
	            return re;
	        }
	        Parameter.put("Update", Update);
	        Map<String,Object> pmap = JSONObject.parseObject(Parameter.toJSONString(), Map.class);
	        vCustomergwlistSelectMapper.JZDetail_Update(pmap);
	        re.setErrcode(0);
	    	re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
			re.setErrmsg("服务器异常！");
            e.printStackTrace();
		}
        return re;
    }

	@Override
	public Result mCustomerGWDetail_Insert(JSONObject paramAry) {
        Result entity = new Result();
        try{
            if (!StringUtils.isEmpty(paramAry.getString("FormSessionID"))){
            	String formSessionID = paramAry.getString("FormSessionID");
            	Long RowCount = vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_count(formSessionID);
            	if(RowCount.intValue()==0){
            		vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_update(formSessionID);
            	}
                if (RowCount.intValue()> 0){
                    entity.setErrcode(1);
                    entity.setErrmsg("不能重复请求！");
                    return entity;
                }
            }
            JSONObject CustomerObj = new JSONObject();
            CGWDetailModel model = JSONObject.parseObject(paramAry.toJSONString(), CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
                //初始化参数
                JSONObject parameter = customerTemplate.GetParameters(model);
                if (!parameter.isEmpty()){
                    String sqlKey = "";
                    String Mobile =parameter.getString("Mobile");
                    if (!StringUtils.isEmpty(Mobile)){//验证手机号码
                    	JSONObject j_re = customerTemplate.CustomerOpportunityExist(model.getProjectID(), Mobile);
                        if (j_re.getBoolean("status")){//存在老机会_老客户 
                        	CustomerObj = j_re.getJSONObject("CustomerObj");
                            entity.setErrcode(1);
                            entity.setErrmsg("此手机号客户已存在,销售顾问:" +CustomerObj.getString("SaleUserName"));
                        }else{//新机会
                        	JSONObject j_re_1 = customerTemplate.CustomerExist(Mobile);
                            if (!j_re_1.getBoolean("status")){//新机会_新客户
                                parameter.put("CustomerID", UUID.randomUUID().toString());
                                sqlKey = "mNewCustomerGWDetail_Insert";
                            }else{//新机会_老客户
                            	CustomerObj = j_re_1.getJSONObject("CustomerObj");
                            	parameter.put("CustomerID", CustomerObj.getString("CustomerID"));
                                sqlKey = "mOldCustomerGWDetail_Insert";
                            }
                            entity.setErrcode(0);
                        }
                        if (entity.getErrcode() == 0){
                        	parameter.put("TrackType", "BC2F967F-8FFE-1F52-49F6-CBCDFE8D044A");
                        	parameter.put("OpportunityID", UUID.randomUUID().toString());
                        	parameter.put("SaleUserID", model.getUserID());
                        	parameter.put("Status", 1);
                        	parameter.put("IsCustomerFirstEdit", 0);
                        	parameter.put("CustomerFirstEditTime", "");
                            //客户来源
                            String ClueID = "";
                            Map<String,String> re_map = customerTemplate.GetOpportunitySource(parameter);
                            ClueID = String.valueOf(re_map.get("clueID"));
                            
                            parameter.put("ClueID", ClueID);
                            parameter.put("OpportunitySource", String.valueOf(re_map.get("opportunitySourceID")));
                            //客户分配
                            String projectID = parameter.getString("ProjectID");
                            if(!"".equals(ClueID)){
                            	Map<String,Object> re_map_step1 = vCustomergwlistSelectMapper.RemindRuleAllotDetail_Select_step1(ClueID);
                            	if(re_map_step1!=null && re_map_step1.size()>0){
                            		String protectSource = re_map_step1.get("ProtectSource")!=null?re_map_step1.get("ProtectSource").toString():"";
                            		if(!StringUtils.isEmpty(protectSource)){
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
                                                String UserID = parameter.getString("UserID");
                                                String ProjectID = parameter.getString("ProjectID");
                                                String Content = "客户" + parameter.getString("LastName") + parameter.getString("FirstName") + "、" + parameter.getString("Mobile") + "(客户分配提醒)";
                                                iSystemMessageService.Detail_Insert(UserID, ProjectID, ClueID, "Clue", "客户分配提醒", Content, ReportUserID, MessageType.系统通知.getTypeID(), true);
                                            }
                            			}
                            		}
                            	}
                            }
                            if (parameter.getString("FollwUpWay").equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){
                            	//跟进方式为到访时 机会状态设置为到访(2),其他情况为问询(1)
                            	parameter.put("Status", 2);
                            	parameter.put("IsCustomerFirstEdit", 1);
                            	parameter.put("CustomerFirstEditTime", DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
                            }
                            parameter.put("Name", parameter.getString("LastName")+parameter.getString("FirstName"));
                        	Map<String,Object> parameterMap = JSONObject.parseObject(parameter.toJSONString(), Map.class);
                            if(sqlKey.equals("mNewCustomerGWDetail_Insert")){//添加新客户
                            	vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step1(parameterMap);
                            	vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step2(parameterMap);
                            	vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step3(parameterMap);
                            	vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step4(parameterMap);
                            	vCustomergwlistSelectMapper.mNewCustomerGWDetail_Insert_step5(parameterMap);
                            	
                            }else if(sqlKey.equals("mOldCustomerGWDetail_Insert")){//顾问新增已有客户机会信息
                            	vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step1(parameterMap);
                            	vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step2(parameterMap);
                            	
                            	List<Map<String,Object>> step3_map = vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step3_vaild(parameterMap);
                            	if(step3_map!=null && step3_map.size()>0){
                            		vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step3_update(parameterMap);
                            	}else{
                            		vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step3_insert(parameterMap);
                            	}
                            	vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step4(parameterMap);
                            	vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step5(parameterMap);
                            	vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step6(parameterMap);
                            	vCustomergwlistSelectMapper.mOldCustomerGWDetail_Insert_step7(parameterMap);
                            }
                            
                            parameterMap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                        	parameterMap.put("UpDownStatus", 1);
                        	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        	String FollwUpWay = parameter.getString("FollwUpWay");
                        	if(FollwUpWay.equals("EEB32C04-5B7C-4676-A5DC-5F95E56370EB") || FollwUpWay.equals("44775694-7C97-455C-B48E-154C6BFE2D94")){
                        		parameterMap.put("CustomerRank", "FC420696-6F6C-40B1-BE17-96FCEC75B0F2");
                        		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        	}else if(FollwUpWay.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){
                        		parameterMap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                        		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        	}
                        	String IsLittleBooking = parameter.getString("IsLittleBooking");
                        	if(IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")){
                        		parameterMap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
                        		vCustomergwlistSelectMapper.P_OpportunityCustomerRank(parameterMap);
                        	}
                        	parameterMap.put("ClueID", "");
                        	vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(parameterMap);
                        	
                        	 //添加线索
                        	customerTemplate.ClueUpdate(parameter);
                            //增加意向项目
                        	customerTemplate.IntentProjectAdd(parameter);
                            if (StringUtils.isEmpty(ClueID)){
                            	JSONObject obj1 = new JSONObject();
                                obj1.put("FollwUpType", "顾问报备");
                                obj1.put("FollwUpTypeID", ActionType.顾问报备.getValue());
                                obj1.put("SalesType", 1);
                                obj1.put("NewSaleUserName", "");
                                obj1.put("OldSaleUserName", "");
                                obj1.put("FollwUpUserID", parameter.getString("UserID"));
                                obj1.put("FollwUpWay", "");
                                obj1.put("FollowUpContent", "");
                                obj1.put("IntentionLevel", "");
                                obj1.put("OrgID",parameter.getString("OrgID"));
                                obj1.put("FollwUpUserRole", parameter.getString("JobID"));
                                obj1.put("OpportunityID", parameter.getString("OpportunityID"));
                                obj1.put("ClueID", "");
                                obj1.put("NextFollowUpDate", "");
                                CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(), CustomerActionVo.class);
                                this.CustomerFollowUp_Insert(customerActionVo);
                                
                            }
                            //增加跟进记录
                            String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(parameter.getString("FollwUpWay"));
                            if (!StringUtils.isEmpty(FollwUpType)){
                            	JSONObject obj = new JSONObject();
                                obj.put("FollwUpType", FollwUpType);
                                obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                                obj.put("SalesType", 1);
                                obj.put("NewSaleUserName", "");
                                obj.put("OldSaleUserName", "");
                                obj.put("FollwUpUserID",parameter.getString("UserID"));
                                obj.put("FollwUpWay",parameter.getString("FollwUpWay"));
                                obj.put("FollowUpContent", parameter.getString("FollowUpContent"));
                                obj.put("IntentionLevel",parameter.getString("CustomerLevel"));
                                obj.put("OrgID", parameter.getString("OrgID"));
                                obj.put("FollwUpUserRole",parameter.getString("JobID"));
                                obj.put("OpportunityID",parameter.getString("OpportunityID"));
                                obj.put("ClueID", "");
                                obj.put("NextFollowUpDate",parameter.getString("NextFollowUpDate"));
                                CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                                this.CustomerFollowUp_Insert(customerActionVo);
                                //客户到访
                                if ( "售场接待".equals(FollwUpType)){//售场接待
                                	String tClueID = parameter.getString("OpportunitySource");
                                    Map<String,Object> res = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select("", tClueID);
                                    String reportUserID = "";
                                    String tprotectSource = "";
                                    if(res!=null && res.get("clueID")!=null){
                                    	tClueID = res.get("clueID").toString();
                                    }
                                    if(res!=null && res.get("reportUserID")!=null){
                                    	reportUserID = res.get("reportUserID").toString();
                                    }
                                    if(res!=null && res.get("protectSource")!=null){
                                    	tprotectSource = res.get("protectSource").toString();
                                    }
                                    if (!StringUtils.isEmpty(tClueID) &&  !StringUtils.isEmpty(reportUserID)){
                                    	String LastName = "";
                                    	String FirstName = "";
                                    	String tMobile = "";
                                    	if(!"".equals(tprotectSource)){
                                        	Map<String,Object> resf =vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_f(projectID, tprotectSource);
                                        	int customerVisitsRemind = 0;
                                        	if(resf!=null && resf.get("customerVisitsRemind")!=null){
                                        		Number number = (Number)resf.get("customerVisitsRemind");
                                        		customerVisitsRemind = number.intValue();
                                        	}
                                        	if(customerVisitsRemind>0){
                                        		Map<String,Object> ress = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_s(tClueID);
                                        		if(ress!=null && ress.get("LastName")!=null){
                                        			LastName = ress.get("LastName").toString();
                                        		}
                                        		if(ress!=null && ress.get("FirstName")!=null){
                                        			FirstName = ress.get("FirstName").toString();
                                        		}
                                        		if(ress!=null && ress.get("Mobile")!=null){
                                        			tMobile = ress.get("Mobile").toString();
                                        		}
                                        	}
                                        }
                                        String UserID =paramAry.getString("UserID");
                                        String ProjectID = paramAry.getString("ProjectID");
                                        String Content = "客户" +LastName +FirstName + "、" + tMobile + "(" + MessageType.到访提醒.getTypeID()+ ")";
                                        Map<String,Object> parameter_1 = new HashMap<String,Object>();
                                        parameter_1.put("ProjectID", ProjectID);
                                        parameter_1.put("BizID", res.get("ClueID").toString());
                                        parameter_1.put("BizType", "Clue");
                                        parameter_1.put("Subject", "客户到访提醒");
                                        parameter_1.put("Content", Content);
                                        parameter_1.put("Receiver",res.get("ReportUserID").toString());
                                        parameter_1.put("MessageType",MessageType.带看通知.getTypeID());
                                        parameter_1.put("Sender", UserID);
                                        parameter_1.put("Creator", UserID);
                                        parameter_1.put("IsNeedPush", true);
                                        iSystemMessageService.SystemMessageDetail_Insert(parameter_1);
                                    }
                                }

                            }
                            String tIsLittleBooking = parameter.getString("IsLittleBooking");
                            if (tIsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")){//小筹
                            	JSONObject obj2 = new JSONObject();
                                obj2.put("FollwUpType", "小筹");
                                obj2.put("FollwUpTypeID", ActionType.小筹.getValue());
                                obj2.put("SalesType", 1);
                                obj2.put("NewSaleUserName", "");
                                obj2.put("OldSaleUserName", "");
                                obj2.put("FollwUpUserID", "99");
                                obj2.put("FollwUpWay", "");
                                obj2.put("FollowUpContent", "小筹");
                                obj2.put("IntentionLevel", "");
                                obj2.put("OrgID", "");
                                obj2.put("FollwUpUserRole", "");
                                obj2.put("OpportunityID",parameter.getString("OpportunityID"));
                                obj2.put("ClueID", "");
                                obj2.put("NextFollowUpDate", "");
                                CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                                this.CustomerFollowUp_Insert(customerActionVo);
                            }
                            String opportunityID = parameter.getString("OpportunityID");
                            String userID = parameter.getString("UserID");
                            CustomerOpportunityFollowUpDetail_Update(opportunityID,userID);//客户机会跟进记录更新
                            //处理跟进类待办为已办
                            String[] BizIDs = new String[]{opportunityID};
                    		iSystemMessageService.DetailByHandle_Update(BizIDs, "Opportunity", MessageHandleType.新增跟进.getValue());
                            //同步明源客户数据
                    		customerTemplate.SyncCustomer(opportunityID, 0);
                        }
                    }else{
                        entity.setErrcode(1);
                        entity.setErrmsg("手机号码必填！");
                    }
                }else{
                    entity.setErrcode(1);
                    entity.setErrmsg("误参数格式错！");
                }
            }else{
                entity.setErrcode(1);
                entity.setErrmsg("参数不完整！");
            }
        }
        catch (Exception ex){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            throw ex;
        }
        return entity;
	}

	@Override
	public Result CustomerFind(JSONObject paramAry) {
		Result entity = new Result();
        CSearchModelVo model = JSONObject.parseObject(paramAry.toJSONString(),CSearchModelVo.class);
        String jsonFile = "";
        String customerModeType = CustomerModeType.无.getTypeID();
        int isNew = 1;
        JSONObject CustomerObj = null;
        //记录用户搜索客户手机号信息
        if (!StringUtils.isEmpty(model.getMobile())){
        	customerTemplate.CustomerMobileSearch(model.getProjectID(), model.getMobile(), model.getJobCode(), model.getUserID());
        	customerTemplate.ComeOverdueClue_Update(model.getProjectID(), model.getMobile(), model.getUserID(), model.getOrgID(), model.getJobID());
        }
        if (StringUtils.isEmpty(model.getOpportunityID())){
            //验证是否为老机会老客户
        	JSONObject j_re = customerTemplate.CustomerOpportunityExist(model.getProjectID(), model.getMobile());
        	if (j_re.getBoolean("status")){//存在老机会_老客户 
        		//是老客户
            	CustomerObj = j_re.getJSONObject("CustomerObj");
                jsonFile = "GWOldCustomer.json";
                customerModeType = CustomerModeType.顾问_老机会_老客户.getTypeID();
                isNew = 0;

                //若是公共客户，并更新渠道
                Map<String,Object> objCus = vCustomergwlistSelectMapper.mCustomerGGUpdateChannelSource_Select(paramAry.getString("Mobile"), paramAry.getString("ProjectID"));
                if (objCus!=null && objCus.size() > 0){
                	entity.setErrcode(20);
                	entity.setErrmsg("该客户已更新渠道，需案场销管完成分配");
                    return entity;
                }
            }else{//新客户
                //验证是否存在线索
                int IsClueExist = customerTemplate.ClueExist(model.getProjectID(), model.getMobile());
                Boolean IsNoAllotRole = customerTemplate.GetProjectIsNoAllotRole(model.getProjectID());
                if (IsClueExist>=0&&IsNoAllotRole){
                	//如果开启分接 则不允许置业顾问操作渠道报备客户
                	entity.setErrcode(18);
                	entity.setErrmsg("该客户为渠道客户，不能登记");
                    return entity;
                }else if (IsClueExist == 1){
                	//竞争带看,不允许置业顾问报备
                    entity.setErrcode(19);
                	entity.setErrmsg("该客户为渠道客户，不能登记");
                    return entity;
                }else{
                	JSONObject j_re_1 = customerTemplate.CustomerExist(model.getMobile());
                	if (!j_re_1.getBoolean("status")){
                		//1.根据手机号码查询拓客客户信息 
                		JSONObject j_re_2 = customerTemplate.CustomerPotentialExist(model.getMobile());
                		if (j_re_2.getBoolean("status")){
                			//存在拓客客户信息
                			CustomerObj = j_re_2.getJSONObject("CustomerObj");
                            jsonFile = "GWNewCustomer.json";
                            customerModeType = CustomerModeType.顾问_新机会_新客户_老潜在客户.getTypeID();
                		}else{//不存在拓客客户信息
                			 jsonFile = "GWNewCustomer.json";
                             customerModeType = CustomerModeType.顾问_新机会_新客户_新潜在客户.getTypeID();
                		}
                	}else{//存在客户信息
                		 CustomerObj = j_re_1.getJSONObject("CustomerObj");
                		 jsonFile = "GWNewCustomer.json";
                         customerModeType = CustomerModeType.顾问_新机会_老客户.getTypeID();
                	}
                }
            }
        }else{
            CustomerObj = customerTemplate.OpportunityCustomer(model.getOpportunityID());
            jsonFile = "GWNewCustomer.json";
            customerModeType = CustomerModeType.顾问_客户_更新.getTypeID();
        }
        CustomerModelVo customerModel = customerTemplate.InitCustomerModeData(model, jsonFile, CustomerObj, customerModeType);
        
        List<PanelItem> PanelItems = customerModel.getPanel();
        for(PanelItem PanelItem : PanelItems){
        	if("成员信息".equals(PanelItem.getName())){
        		List<String> parentID = new ArrayList<>();
        		parentID.add(model.getOpportunityID());
        		try {
        			List<Map<String, Object>> childs = vCustomergwlistSelectMapper.SelectOpportunityByParentID(parentID);
            		if(childs!=null && childs.size()>0){
            			JSONArray J_DATA = new JSONArray();
            			for(Map<String, Object> map : childs){
            				String ChildID = map.get("OpportunityID").toString();
            				String ParentRelation ="";
            				if(map.get("ParentRelation")!=null){
            					ParentRelation = map.get("ParentRelation").toString();
            				}
            				String CustomerName = map.get("CustomerName").toString();
            				String CustomerMobile = map.get("CustomerMobile").toString();
            				JSONObject json = new JSONObject();
            				json.put("ChildID", ChildID);
            				json.put("ParentRelation", ParentRelation);
            				json.put("CustomerName", CustomerName);
            				json.put("CustomerMobile", CustomerMobile);
            				J_DATA.add(json);
            			}
            			PanelItem.getChild().get(0).setChild(J_DATA);
            		}
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        customerModel.setIsNew(isNew);
        entity.setData(customerModel);
        entity.setErrcode(0);
        entity.setErrmsg("");
        return entity;
	}

	@Override
	public Result mCustomerGWDetail_Update(JSONObject paramAry) {
		Result entity = new Result();
        try{
            CGWDetailModel model = JSONObject.parseObject(paramAry.toJSONString(), CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
            	String opportunityID = paramAry.getString("OpportunityID");
            	String userID = paramAry.getString("UserID");
                if (!customerTemplate.CustomerIsCanOperate(opportunityID, userID)){
                    entity.setErrcode(1);
                    entity.setErrmsg("开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作");
                    return entity;
                }
                JSONObject opportunityObj = OpportunityCustomer(model.getOpportunityID());
                JSONObject parameter = customerTemplate.GetParameters(model, opportunityObj);
                if (parameter!=null && parameter.size() > 0){
                	Map<String,Object> pmap = JSONObject.parseObject(parameter.toJSONString(), Map.class);
                	pmap.put("Name", parameter.getString("LastName")+parameter.getString("FirstName"));
                	//设置变更记录
                	addCustomerChangeInfo(pmap);
                	vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step1(pmap);
                	List<Map<String,Object>> step2_map = vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step2_valid(pmap);
                	if(step2_map!=null && step2_map.size()>0){
                		vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step2_update(pmap);
                	}else{
                		vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step2_insert(pmap);
                	}
                	vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step3(pmap);
                	vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step4(pmap);
                	vCustomergwlistSelectMapper.mCustomerGWDetail_Update_step5(pmap);
                	
                	String IsLittleBooking = parameter.getString("IsLittleBooking");
                	String FollwUpWay = parameter.getString("FollwUpWay");
                	if(IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")){
                		if(FollwUpWay.equals("EEB32C04-5B7C-4676-A5DC-5F95E56370EB") || FollwUpWay.equals("44775694-7C97-455C-B48E-154C6BFE2D94")){
                			pmap.put("CustomerRank", "FC420696-6F6C-40B1-BE17-96FCEC75B0F2");
                			pmap.put("UpDownStatus", 1);
                			vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                		}else if(FollwUpWay.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){
                			pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                			pmap.put("UpDownStatus", 1);
                			vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                		}
                		pmap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
            			pmap.put("UpDownStatus", 1);
            			vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                	}else if(IsLittleBooking.equals("DFE6406C-120B-45E5-9293-DD093E416C68")){
                		pmap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
            			pmap.put("UpDownStatus", 2);
            			vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
            			if(FollwUpWay.equals("EEB32C04-5B7C-4676-A5DC-5F95E56370EB") || FollwUpWay.equals("44775694-7C97-455C-B48E-154C6BFE2D94")){
                			pmap.put("CustomerRank", "FC420696-6F6C-40B1-BE17-96FCEC75B0F2");
                			pmap.put("UpDownStatus", 1);
                			vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                		}else if(FollwUpWay.equals("E30825AA-B894-4A5F-AF55-24CAC34C8F1F")){
                			pmap.put("CustomerRank", "ED0AD9E6-AF72-424C-9EE0-9884FF31FA42");
                			pmap.put("UpDownStatus", 1);
                			vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                		}
                	}
                	pmap.put("ClueID", "");
                	vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);
                	entity.setErrcode(0);
                    entity.setErrmsg("成功");
                    if (entity.getErrcode() == 0){
                        //增加意向项目
                    	customerTemplate.IntentProjectAdd(parameter);
                        //增加跟进记录
                        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(parameter.getString("FollwUpWay"));
                        if (!StringUtils.isEmpty(FollwUpType)){
                            JSONObject obj = new JSONObject();
                            obj.put("FollwUpType", FollwUpType);
                            obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                            obj.put("SalesType", 1);
                            obj.put("NewSaleUserName", "");
                            obj.put("OldSaleUserName", "");
                            obj.put("FollwUpUserID", parameter.getString("UserID"));
                            obj.put("FollwUpWay",parameter.getString("FollwUpWay"));
                            obj.put("FollowUpContent", parameter.getString("FollowUpContent"));
                            obj.put("IntentionLevel", parameter.getString("CustomerLevel"));
                            obj.put("OrgID",parameter.getString("OrgID"));
                            obj.put("FollwUpUserRole", parameter.getString("JobID"));
                            obj.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj.put("ClueID", "");
                            obj.put("NextFollowUpDate", parameter.getString("NextFollowUpDate"));
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(), CustomerActionVo.class);
                            this.CustomerFollowUp_Insert(customerActionVo);

                            if (FollwUpType.equals("售场接待")){//售场接待
                                //客户到访
                            	String clueID = parameter.getString("ClueID");
                                String projectID = parameter.getString("ProjectID");
                                Map<String,Object> res = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select(opportunityID, clueID);
                                
                                String tclueID = "";
                                String reportUserID = "";
                                String protectSource = "";
                                if(res!=null && res.get("clueID")!=null){
                                	tclueID = res.get("clueID").toString();
                                }
                                if(res!=null && res.get("reportUserID")!=null){
                                	reportUserID = res.get("reportUserID").toString();
                                }
                                if(res!=null && res.get("protectSource")!=null){
                                	protectSource = res.get("protectSource").toString();
                                }
                                
                                if (!StringUtils.isEmpty(tclueID) && !StringUtils.isEmpty(reportUserID)){
                                	
                                	String LastName = "";
                                	String FirstName = "";
                                	String Mobile = "";
                                	if(!"".equals(protectSource)){
                                    	Map<String,Object> resf =vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_f(projectID, protectSource);
                                    	int customerVisitsRemind = 0;
                                    	if(resf!=null && resf.get("customerVisitsRemind")!=null){
                                    		Number number = (Number)resf.get("customerVisitsRemind");
                                    		customerVisitsRemind = number.intValue();
                                    	}
                                    	if(customerVisitsRemind>0){
                                    		Map<String,Object> ress = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_s(tclueID);
                                    		if(ress!=null && ress.get("LastName")!=null){
                                    			LastName = ress.get("LastName").toString();
                                    		}
                                    		if(ress!=null && ress.get("FirstName")!=null){
                                    			FirstName = ress.get("FirstName").toString();
                                    		}
                                    		if(ress!=null && ress.get("Mobile")!=null){
                                    			Mobile = ress.get("Mobile").toString();
                                    		}
                                    	}
                                    }
                                	
                                    String UserID = parameter.getString("UserID");
                                    String ProjectID = parameter.getString("ProjectID");
                                    String Content = "客户" +LastName + FirstName+ "、" + Mobile + "(" + MessageType.到访提醒.getTypeID() + ")";
                                    iSystemMessageService.Detail_Insert(UserID, ProjectID, tclueID, "Clue", "客户到访提醒", Content, reportUserID, MessageType.带看通知.getTypeID(),true);
                                }
                            }

                        }
                        String IsLittleBookingOld = opportunityObj.getString("IsLittleBooking");
                        if (StringUtils.isEmpty(IsLittleBookingOld)){
                            IsLittleBookingOld = "DFE6406C-120B-45E5-9293-DD093E416C68";
                        }
                        if (IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C") && !IsLittleBookingOld.equals(IsLittleBooking)){//小筹
                            JSONObject obj2 = new JSONObject();
                            obj2.put("FollwUpType", "小筹");
                            obj2.put("FollwUpTypeID", ActionType.小筹.getValue());
                            obj2.put("SalesType", 1);
                            obj2.put("NewSaleUserName", "");
                            obj2.put("OldSaleUserName", "");
                            obj2.put("FollwUpUserID", "99");
                            obj2.put("FollwUpWay","");
                            obj2.put("FollowUpContent","小筹");
                            obj2.put("IntentionLevel", "");
                            obj2.put("OrgID","");
                            obj2.put("FollwUpUserRole", "");
                            obj2.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj2.put("ClueID", "");
                            obj2.put("NextFollowUpDate", "");
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                            this.CustomerFollowUp_Insert(customerActionVo);
                        }
                        if (IsLittleBooking.equals("DFE6406C-120B-45E5-9293-DD093E416C68") && !IsLittleBookingOld.equals(IsLittleBooking)){//退小筹
                            JSONObject obj2 = new JSONObject();
                            obj2.put("FollwUpType", "退小筹");
                            obj2.put("FollwUpTypeID", ActionType.退小筹.getValue());
                            obj2.put("SalesType", 1);
                            obj2.put("NewSaleUserName", "");
                            obj2.put("OldSaleUserName", "");
                            obj2.put("FollwUpUserID", "99");
                            obj2.put("FollwUpWay","");
                            obj2.put("FollowUpContent","退小筹");
                            obj2.put("IntentionLevel", "");
                            obj2.put("OrgID","");
                            obj2.put("FollwUpUserRole", "");
                            obj2.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj2.put("ClueID", "");
                            obj2.put("NextFollowUpDate", "");
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                            this.CustomerFollowUp_Insert(customerActionVo);
                        }
                        CustomerOpportunityFollowUpDetail_Update(opportunityID,userID);//客户机会跟进记录更新
                        //处理跟进类待办为已办
                        String[] BizIDs = new String[]{opportunityID};
                		iSystemMessageService.DetailByHandle_Update(BizIDs, "Opportunity", MessageHandleType.新增跟进.getValue());
                        //同步明源客户数据
                        customerTemplate.SyncCustomer(opportunityID, 0);
                    }
                }else{
                	entity.setErrcode(1);
                	entity.setErrmsg("参数格式错误！");
                }
            }
        }catch (Exception e){
            entity.setErrcode(1);
        	entity.setErrmsg("服务器异常！");
        	e.printStackTrace();
        }
        return entity;
	}
	
	public JSONObject OpportunityCustomer(String opportunityID){
        //1.根据机会判断是否为机会客户
        try{
        	Map<String, Object> result = vCustomergwlistSelectMapper.sCustomerGWSearch_Select(opportunityID);
            return new JSONObject(result);
        }catch (Exception e){
        	e.printStackTrace();
            return new JSONObject();
        }

    }
	
	@Override
	public Result SubscribeFind(JSONObject paramAry){
		Result entity = new Result();
        try{
            CSearchModelVo model = JSONObject.parseObject(paramAry.toJSONString(), CSearchModelVo.class);
            String jsonFile = "";
            String customerModeType = CustomerModeType.无.getTypeID();
            JSONObject CustomerObj = new JSONObject();
            if (!StringUtils.isEmpty(model.getOpportunityID())){
                //如果开启2级以上客储允许提交认购，验证客储等级
            	Map<String, Object> objRank = vCustomergwlistSelectMapper.mCustomerTwoRankAllowSubscribe_Select(model.getOpportunityID());
                if (objRank!=null && objRank.size() > 0){
                    entity.setErrcode(1);
                    entity.setErrmsg("未到访客户不能提交认购意向！");
                    return entity;
                }
                CustomerObj = customerTemplate.OpportunityCustomer(model.getOpportunityID());
                jsonFile = "GWBuyCustomer.json";
                customerModeType = CustomerModeType.顾问_客户_更新.getTypeID();
                CustomerModelVo customerModel = customerTemplate.InitCustomerModeData(model, jsonFile, CustomerObj, customerModeType);
                entity.setData(customerModel);
            }else{
                entity.setErrcode(1);
                entity.setErrmsg("参数缺失！");
            }
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常");
            e.printStackTrace();
        }
        entity.setErrmsg("成功");
        entity.setErrcode(0);
        return entity;
    }

	@Override
	public Result mCustomerSubscribeDetail_Insert(JSONObject paramAry) {
		Result entity = new Result();
        try{
            CGWDetailModel model =JSONObject.parseObject(paramAry.toJSONString(), CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
                String opportunityID = paramAry.getString("OpportunityID");
                String userID = paramAry.getString("UserID");
                if (!customerTemplate.CustomerIsCanOperate(opportunityID, userID)) {
                	entity.setErrcode(1);
                    entity.setErrmsg("开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作");
                    return entity;
                }
                JSONObject opportunityObj = OpportunityCustomer(model.getOpportunityID());
                JSONObject parameter = customerTemplate.GetParameters(model, opportunityObj);
                String Mobile = parameter.getString("Mobile");
                if (!StringUtils.isEmpty(Mobile)){//验证手机号码
                    //手机号码+项目ID验证是否存在机会客户
                	//JSONObject CustomerObj = new JSONObject();
                	JSONObject re = customerTemplate.CustomerOpportunityExist(model.getProjectID(), Mobile);
                    if (re.getBoolean("status")){//客户信息已存在
                    	//CustomerObj = re.getJSONObject("CustomerObj");
                        Map<String,Object> pmap = JSONObject.parseObject(parameter.toJSONString(),Map.class);
                        pmap.put("Name", parameter.getString("LastName")+parameter.getString("FirstName"));
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step1(pmap);
                        List<Map<String,Object>> step2_map = vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step2_valid(pmap);
                        if(step2_map!=null && step2_map.size()>0){
                        	vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step2_update(pmap);
                        }else{
                        	vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step2_insert(pmap);
                        }
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step3(pmap);
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step4(pmap);
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step5(pmap);
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step6(pmap);
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step7(pmap);
                        vCustomergwlistSelectMapper.mCustomerSubscribeDetail_Insert_step8(pmap);
                        
                        String IsLittleBooking = parameter.getString("IsLittleBooking");
                        if(IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C")){
                        	pmap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
                        	pmap.put("UpDownStatus", 1);
                        	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                        }else if(IsLittleBooking.equals("DFE6406C-120B-45E5-9293-DD093E416C68")){
                        	pmap.put("CustomerRank", "D47878CE-D560-44C0-A6F8-4C92CCAC9EE0");
                        	pmap.put("UpDownStatus", 2);
                        	vCustomergwlistSelectMapper.P_OpportunityCustomerRank(pmap);
                        }
                        pmap.put("ClueID", "");
                        vCustomergwlistSelectMapper.P_SyncClueOpportunity_Update(pmap);
                        
                        //增加意向项目
                        customerTemplate.IntentProjectAdd(parameter);
                        String IsLittleBookingOld =opportunityObj.getString("IsLittleBooking");
                        if (StringUtils.isEmpty(IsLittleBookingOld)){
                            IsLittleBookingOld = "DFE6406C-120B-45E5-9293-DD093E416C68";
                        }
                        if (IsLittleBooking.equals("1DCCBDB8-AD44-44D4-B23A-571A38337D5C") &&  !IsLittleBookingOld.equals(IsLittleBooking)){//小筹
                            JSONObject obj2 = new JSONObject();
                            obj2.put("FollwUpType", "小筹");
                            obj2.put("FollwUpTypeID", ActionType.小筹.getValue());
                            obj2.put("SalesType", 1);
                            obj2.put("NewSaleUserName", "");
                            obj2.put("OldSaleUserName", "");
                            obj2.put("FollwUpUserID", "99");
                            obj2.put("FollwUpWay","");
                            obj2.put("FollowUpContent","小筹");
                            obj2.put("IntentionLevel", "");
                            obj2.put("OrgID","");
                            obj2.put("FollwUpUserRole", "");
                            obj2.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj2.put("ClueID", "");
                            obj2.put("NextFollowUpDate", "");
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                            this.CustomerFollowUp_Insert(customerActionVo);
                        }
                        if (IsLittleBooking.equals("DFE6406C-120B-45E5-9293-DD093E416C68") && !IsLittleBookingOld.equals(IsLittleBooking)){//退小筹
                            JSONObject obj2 = new JSONObject();
                            obj2.put("FollwUpType", "退小筹");
                            obj2.put("FollwUpTypeID", ActionType.退小筹.getValue());
                            obj2.put("SalesType", 1);
                            obj2.put("NewSaleUserName", "");
                            obj2.put("OldSaleUserName", "");
                            obj2.put("FollwUpUserID", "99");
                            obj2.put("FollwUpWay","");
                            obj2.put("FollowUpContent","退小筹");
                            obj2.put("IntentionLevel", "");
                            obj2.put("OrgID","");
                            obj2.put("FollwUpUserRole", "");
                            obj2.put("OpportunityID", parameter.getString("OpportunityID"));
                            obj2.put("ClueID", "");
                            obj2.put("NextFollowUpDate", "");
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj2.toJSONString(), CustomerActionVo.class);
                            this.CustomerFollowUp_Insert(customerActionVo);
                        }
                        entity.setErrcode(0);
                        entity.setErrmsg("成功");
                    }
                    //省去图片生成处理
                    if (!StringUtils.isEmpty(paramAry.getString("RoomID"))){
                        Map<String,Object> objParam = new HashMap<String, Object>();
                        objParam.put("ProjectID",paramAry.getString("ProjectID"));
                        objParam.put("OpportunityID", paramAry.getString("OpportunityID"));
                        objParam.put("CustomerID",paramAry.getString("CustomerID"));
                        objParam.put("RoomID",paramAry.getString("RoomID"));
                        objParam.put("UserID", paramAry.getString("UserID"));
                        vCustomergwlistSelectMapper.CustomerLockRoomDetail_Insert(objParam);
                    }
                }
            }
        }catch (Exception e){
            entity.setErrmsg("服务器异常！");
            entity.setErrcode(1);
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result RelPeopleList(JSONObject paramAry) {
		Result entity = new Result();
        try{
            String KeyWord = paramAry.getString("KeyWord");
            String where = "";
            if(!StringUtils.isEmpty(KeyWord)){
                where = " and (CustomerName ='"+KeyWord+"' OR CustomerMobile ='"+KeyWord+"')";
            }
            paramAry.put("WHERE", where);
            Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
            List<Map<String, Object>> result = vCustomergwlistSelectMapper.sCustomerRelPeople_Select(pmap);
            if (result!=null && result.size() <= 0){
                result = new ArrayList<Map<String,Object>>();
            }
            entity.setData(result);
            entity.setErrcode(0);
            entity.setErrmsg("成功");
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            e.printStackTrace();
        }
        return entity;
	}

	@Override
	public Result UserFollowList_Select(JSONObject paramAry) {
		Result entity = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(),Map.class);
			List<Map<String, Object>> result = vCustomergwlistSelectMapper.sUserFollowList_Select(pmap);
			Long recordCount = vCustomergwlistSelectMapper.sUserFollowList_Select_count(pmap);
			JSONObject re = new JSONObject();
        	re.put("List", result);
        	re.put("AllCount", recordCount);
        	re.put("PageSize", paramAry.get("PageSize"));
        	String dataStr = new JSONObject(re).toJSONString();
            JSONObject data = JSONObject.parseObject(dataStr);
        	entity.setData(data);
			entity.setErrcode(0);
            entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
			e.printStackTrace();
		}
     
        return entity;
	}

	@Override
	public void addCustomerChangeInfo(Map<String,Object> pmap){
		try {
			String OpportunityID = pmap.get("OpportunityID").toString();
			String CustomerID = pmap.get("CustomerID").toString();
			String userId =  pmap.get("UserID")!=null?pmap.get("UserID").toString():"";
			String UserTrueName = pmap.get("UserTrueName")!=null?pmap.get("UserTrueName").toString():"";
			Map<String, Object> customerData = vCustomergwlistSelectMapper.selectCustomerByID(CustomerID);
			Map<String, Object> OpportunityData = vCustomergwlistSelectMapper.selectOpportunityByID(OpportunityID);
			if(customerData!=null && customerData.size()>0 && OpportunityData!=null && OpportunityData.size()>0){
				//姓名  副手机号 证件类型  证件号码  性别 
				OpportunityData.get("CustomerName").toString();
				String CustomerName = OpportunityData.get("CustomerName")!=null && !"".equals(OpportunityData.get("CustomerName").toString())?OpportunityData.get("CustomerName").toString():"空值";
				String SpareMobile =OpportunityData.get("SpareMobile")!=null && !"".equals(OpportunityData.get("SpareMobile").toString())?OpportunityData.get("SpareMobile").toString():"空值";
				String CardType = customerData.get("CardType")!=null && !"".equals(customerData.get("CardType").toString())?customerData.get("CardType").toString():"空值";
				String CardID = customerData.get("CardID")!=null && !"".equals(customerData.get("CardID").toString())?customerData.get("CardID").toString():"空值";
				String Gender = customerData.get("Gender")!=null && !"".equals(customerData.get("Gender").toString())?customerData.get("Gender").toString():"空值";
				
				List<Map<String, Object>>  jarry = vCustomergwlistSelectMapper.DictionaryList_Select("E72C340D-4092-467A-9B8F-5138DBDCA43B");
				
				List<Map<String, Object>>  cardTypejarry = vCustomergwlistSelectMapper.DictionaryList_Select("848EBE45-2C03-40C5-AE91-EC72533539BD");
				
				
				UpdateCustinfoLog log = new UpdateCustinfoLog();
				
		        log.setOpportunityID(OpportunityID);
		        log.setCustomerID(CustomerID);
		        log.setEditorId(userId);
		        log.setEditorName(UserTrueName);
		        Boolean change = false;
				String newCustomerName = pmap.get("Name")!=null?pmap.get("Name").toString():"";
				if(!"".equals(newCustomerName)){
					if(!CustomerName.equals(newCustomerName)){
						log.setCustomerName(CustomerName + "->" + newCustomerName);
						change=true;
					}
				}
				String newSpareMobile =pmap.get("SpareMobile")!=null?pmap.get("SpareMobile").toString():"";
				if(!"".equals(newSpareMobile)){
					if(!SpareMobile.equals(newSpareMobile)){
						log.setAuxiliaryMobile(SpareMobile + "->" + newSpareMobile);
						change=true;
					}
				}
				String newCardType = pmap.get("CardType")!=null?pmap.get("CardType").toString():"";
				if(!"".equals(newCardType)){
					if(!CardType.equals(newCardType)){
						String newCardTypeStr="";
						String oldCardTypeStr="空值";
						for(Map<String, Object> map : cardTypejarry){
							if(CardType.equals(map.get("ID").toString())){
								oldCardTypeStr = map.get("DictName").toString();
							}
							if(newCardType.equals(map.get("ID").toString())){
								newCardTypeStr = map.get("DictName").toString();
							}
						}
						log.setCardType(oldCardTypeStr + "->" + newCardTypeStr);
						change=true;
					}
				}
				String newCardID = pmap.get("CardID")!=null?pmap.get("CardID").toString():"";
				if(!"".equals(newCardID)){
					if(!CardID.equals(newCardID)){
						log.setCardID(CardID + "->" + newCardID);
						change=true;
					}
				}
				String newGender = pmap.get("Gender")!=null?pmap.get("Gender").toString():"";
				if(!"".equals(newGender)){
					if(!Gender.equals(newGender)){
						String newGenderStr = "";
						String oldGenderStr = "空值";
						for(Map<String, Object> map : jarry){
							if(Gender.equals(map.get("ID").toString())){
								oldGenderStr = map.get("DictName").toString();
							}
							if(newGender.equals(map.get("ID").toString())){
								newGenderStr = map.get("DictName").toString();
							}
						}
						log.setGender(oldGenderStr + "->" + newGenderStr);
						change=true;
					}
				}
				if(change){
					 log.setCreateTime(new Date());
				     iUpdateCustinfoLogService.save(log);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Result getCustomerChangeList(String OpportunityID){
		Result entity = new Result();
		try {
			if(OpportunityID==null || "".equals(OpportunityID)){
				entity.setErrcode(1);
				entity.setErrmsg("机会ID不能为空");
				return entity;
			}
			QueryWrapper<UpdateCustinfoLog> updateCustInfoLogQuery = new QueryWrapper<>();
		    updateCustInfoLogQuery.eq("OpportunityID",OpportunityID);
		    List updateCustInfoLogList = iUpdateCustinfoLogService.list(updateCustInfoLogQuery);
		    entity.setErrcode(0);
			entity.setErrmsg("成功");
			entity.setData(updateCustInfoLogList);
		} catch (Exception e) {
			entity.setErrcode(1);
			entity.setErrmsg("系统异常");
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Result verifySpareMobile(String SpareMobile) {
		Result entity = new Result();
		try {
			List<Map<String, Object>> data = vCustomergwlistSelectMapper.verifyOpportunityMobile(SpareMobile);
			if(data!=null && data.size()>0){
				entity.setErrcode(1);
				entity.setErrmsg("手机号不可使用");
			}else{
				entity.setErrcode(0);
				entity.setErrmsg("副手机号可以使用");
			}
		} catch (Exception e) {
			entity.setErrcode(1);
			entity.setErrmsg("系统异常");
			e.printStackTrace();
		}
		return entity;
	}

}
