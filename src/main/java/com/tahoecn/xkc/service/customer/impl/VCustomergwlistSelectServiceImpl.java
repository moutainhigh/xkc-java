package com.tahoecn.xkc.service.customer.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.MessageHandleType;
import com.tahoecn.xkc.common.enums.MessageType;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto.FilterItem;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.GWCustomerPageVo;
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
@Service
public class VCustomergwlistSelectServiceImpl extends ServiceImpl<VCustomergwlistSelectMapper, VCustomergwlistSelect> implements IVCustomergwlistSelectService {
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private ISystemMessageService iSystemMessageService;
	
	@Override
	public Result customerList(GWCustomerPageDto model) {
		Result entity = new Result();
        try {
        	StringBuilder whereSb = new StringBuilder();
        	StringBuilder orderSb = new StringBuilder();
        	if(!StringUtils.isEmpty(model.getKeyWord())){
        		whereSb.append(" and (CustomerName Like'"+model.getKeyWord()+"' or CustomerMobile Like'"+model.getKeyWord()+"') ");
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
										filterItem.getChild().add(i, "1");
										break;
									case "6A91A1AB-FC05-2BC0-8C08-0C3FCB07D865":
										filterItem.getChild().add(i, "2");
										break;
									case "3962D6C3-1141-F62F-2181-9F6AA82E0A6C":
										filterItem.getChild().add(i, "3");
										break;
									case "3089CC46-2736-2B3C-3B9C-6D44D059ACCD":
										filterItem.getChild().add(i, "4");
										break;
									case "E9C2D9B3-4538-CBDD-73DE-AEA7444DB20E":
										filterItem.getChild().add(i, "5");
										break;
									case "43A67B86-E775-158F-F897-30E78F5C8D4C":
										filterItem.getChild().add(i, "7");
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
										filterItem.getChild().add(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA");
										break;
									case "ABC6231B-225A-1EC6-8BD6-5165D911BD4E":
										filterItem.getChild().add(i, "DF2057E2-303B-1F14-4075-069668D3A3BE");
										break;
									case "55EE9A36-4A34-36C6-5B77-8BC1227A156C":
										filterItem.getChild().add(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D");
										break;
									case "B8922623-E5E4-04BF-1A0B-DEE35CBC9A08":
										filterItem.getChild().add(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E");
										break;
									case "AE005A89-BB77-2E81-A8BA-05CB619654B5":
										filterItem.getChild().add(i, "84640879-F4A7-CB87-E39B-F18070BCA568");
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
        		if (model.getSort() == "F5F8788E-EB27-E49D-86D6-BCFAFC181493"){//最新跟进
                    orderSb.append(" ORDER BY  TheLatestFollowUpDate desc ");
                }
                if (model.getSort() == "7A5EDE88-5EBB-D5EB-C3F3-45959B855B14"){//最早跟进
                    orderSb.append(" ORDER BY  TheLatestFollowUpDate  asc ");
                }
                if (model.getSort() == "CC0DE53C-A4A7-FB72-36EE-6BFAA4820B5C"){//最新创建
                    orderSb.append(" ORDER BY  CreateTime desc ");
                }
                if (model.getSort() == "47C99D99-FBB2-F7DC-2384-5DE728E9D657"){//最早创建
                    orderSb.append(" ORDER BY CreateTime asc ");
                }
        	}else{
        		orderSb.append(" ORDER BY  TheLatestFollowUpDate Desc ");
        	}
        	model.setWhere(whereSb.toString());
        	model.setOrder(orderSb.toString());
        	//设置分页
        	IPage<GWCustomerPageVo> page =new Page<GWCustomerPageVo>();
        	page.setSize(model.getPageSize());
        	page.setCurrent(model.getPageIndex());
        	List<GWCustomerPageVo> data = vCustomergwlistSelectMapper.sCustomerGWListNew_Select(model);
        	Long allCount = vCustomergwlistSelectMapper.sCustomerGWListNew_Select_count(model);
        	JSONObject re = new JSONObject();
        	re.put("List", data);
        	re.put("AllCount", allCount);
        	re.put("PageSize", model.getPageSize());
        	entity.setData(re);
        	entity.setErrmsg("成功");
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
            //获取顾问和协作人信息
          /*  Hashtable SiteConfig = Utils.GetSiteConfig("SiteConfig");
            string SiteUrl = SiteConfig["SiteUrl"].ToString();*/
            String siteUrl="";
            paramAry.put("SiteUrl", "");
            List<HashMap<String,Object>> gwInfo = vCustomergwlistSelectMapper.sCustomerGWBaseSalerInfo_Select(opportunityID, projectID,siteUrl);
            objData.put("AdviserList", gwInfo);
            String dataStr = new JSONObject(objData).toJSONString();
            JSONObject data = JSONObject.parseObject(dataStr);
            entity.setData(data);
            entity.setErrmsg("成功");
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
        IPage<Map<String,Object>> page =new Page<Map<String,Object>>();
    	page.setSize(paramAry.getLongValue("PageSize"));
    	page.setCurrent(paramAry.getLongValue("PageIndex"));
    	String opportunityID = paramAry.getString("OpportunityID");
    	String projectID = paramAry.getString("ProjectID");
    	IPage<Map<String,Object>> data = vCustomergwlistSelectMapper.mCustomerFollowUpList_Select(page,opportunityID,projectID);
    	JSONObject j_data = new JSONObject();
    	j_data.put("List", data.getRecords());
    	j_data.put("AllCount", data.getTotal());
    	j_data.put("PageSize", data.getSize());
    	re.setData(j_data);
        re.setErrmsg("成功");
        re.setErrcode(99);
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
	public Result mCustomerFollowUpDetail_Insert(JSONObject paramAry) {
		Result re = new Result();
		String opportunityID = paramAry.getString("OpportunityID");
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
			customerActionVo.setOpportunityID(paramAry.getString("OpportunityID"));
			customerActionVo.setClueID("");
			customerActionVo.setNextFollowUpDate(paramAry.getString("NextTime"));
			
			re = this.CustomerFollowUp_Insert(customerActionVo);
			if (follwUpType.equals("售场接待")){
                //客户到访
                String clueID = paramAry.getString("ClueID");
                String projectID = paramAry.getString("ProjectID");
                Map<String,Object> res = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select(opportunityID, clueID, projectID);
                
                String tclueID = "";
                String reportUserID = "";
                String protectSource = "";
                if(res.get("clueID")!=null){
                	tclueID = res.get("clueID").toString();
                }
                if(res.get("reportUserID")!=null){
                	reportUserID = res.get("reportUserID").toString();
                }
                if(res.get("protectSource")!=null){
                	protectSource = res.get("protectSource").toString();
                }
                if (!StringUtils.isEmpty(tclueID) &&  !StringUtils.isEmpty(reportUserID)){
                	String LastName = "";
                	String FirstName = "";
                	String Mobile = "";
                	if(!"".equals(protectSource)){
                    	Map<String,Object> resf =vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_f(projectID, protectSource);
                    	int customerVisitsRemind = 0;
                    	if(resf.get("customerVisitsRemind")!=null){
                    		customerVisitsRemind = (int) resf.get("customerVisitsRemind");
                    	}
                    	if(customerVisitsRemind>0){
                    		Map<String,Object> ress = vCustomergwlistSelectMapper.RemindRuleArriveDetail_Select_s(tclueID);
                    		if(ress.get("LastName")!=null){
                    			LastName = ress.get("LastName").toString();
                    		}
                    		if(ress.get("FirstName")!=null){
                    			FirstName = ress.get("FirstName").toString();
                    		}
                    		if(ress.get("Mobile")!=null){
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
            switch (customerActionVo.getFollwUpType())
            {
                case "顾问报备":
                    message = "报备成功";
                    break;
                case "渠道报备":
                    message = getChannelReportContent(customerActionVo.getClueID());
                    break;
                case "确客有效":
                    message = getChannelReportEnableContent(customerActionVo.getClueID());
                    break;
                case "报备无效":
                    message = getChannelReportDisableContent(customerActionVo.getClueID());
                    break;
                case "分配顾问":
                    message = getSaleUserAllotContent(customerActionVo.getNewSaleUserName());
                    break;
                case "系统回收":
                    message = getCustomerReleaseContent(customerActionVo.getSalesType(),customerActionVo.getOpportunityID(),customerActionVo.getClueID());
                    break;
                case "重新分配顾问":
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
	
}
