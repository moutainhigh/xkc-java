package com.tahoecn.xkc.service.customer.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.common.enums.CustomerPotentialModeType;
import com.tahoecn.xkc.common.enums.MessageHandleType;
import com.tahoecn.xkc.converter.CareerConsCustConverter;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.model.vo.CGWDetailModel;
import com.tahoecn.xkc.model.vo.CSearchModelVo;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.CustomerModelVo;
import com.tahoecn.xkc.model.vo.FilterItem;
import com.tahoecn.xkc.model.vo.GWCustomerPageModel;
import com.tahoecn.xkc.model.vo.RegisterRuleBaseModel;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.customer.ICustomerPotentialTemplate;
import com.tahoecn.xkc.service.customer.IPotentialCustomerService;
import com.tahoecn.xkc.service.customer.IVCustomergwlistSelectService;
import com.tahoecn.xkc.service.sys.ISystemMessageService;

@SuppressWarnings("unchecked")
@Service
@Transactional(readOnly=true)
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {
	
	@Resource
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	@Resource
	private ICustomerPotentialTemplate iCustomerPotentialTemplate;
	@Resource
	private IVCustomergwlistSelectService iVCustomergwlistSelectService;
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	@Resource
	private ISystemMessageService iSystemMessageService;
	@Resource
	private IBChannelService iBChannelService;

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
        	String dataStr = new JSONObject(re).toJSONString();
            JSONObject data_re = JSONObject.parseObject(dataStr);
        	entity.setData(data_re);
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
	        	if(job!=null && job.size()>0){
	        		data.put("TractSort", job.get("Sort"));
		        	data.put("TractName", job.get("Name"));
		        	data.put("TractTime", job.get("TractTime"));
	        	}
	            
	        	JSONObject token = new JSONObject();
	            token.put("TokerUserID",data.get("ReportUserID"));
	            token.put("ClueMobile",data.get("CustomerMobile"));
	            token.put("ClueID", data.get("ClueID"));
	            token.put("SourceType",data.get("SourceType"));
	            data.put("Token", token);
	        }
	        String dataStr = new JSONObject(data).toJSONString();
            JSONObject data_re = JSONObject.parseObject(dataStr);
            try {
            	String CreateTime = data_re.getString("CreateTime");
                String TractTime = data_re.getString("TractTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                if(CreateTime!=null && !CreateTime.equals("")){
                	 Date d_CreateTime = new Date(Long.valueOf(CreateTime));
                	 data_re.put("CreateTime",sdf.format(d_CreateTime));
                }
                if(TractTime!=null && !TractTime.equals("")){
                	 Date d_TractTime = new Date(Long.valueOf(TractTime));
                	 data_re.put("TractTime", sdf.format(d_TractTime));
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
	        re.setData(data_re);
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
        try{
            CSearchModelVo model =JSONObject.parseObject(paramAry.toJSONString(), CSearchModelVo.class);
            JSONObject CustomerObj = CustomerPotentialClueDetail_Select(model.getClueID());
            if (CustomerObj!=null){
                String customerPotentialModeType = CustomerPotentialModeType.自渠_客户_详情.getTypeID();
                CustomerModelVo customerModel = iCustomerPotentialTemplate.InitCustomerPotentialModeData(model, "ZQDetailCustomerPotential.json", CustomerObj, customerPotentialModeType);
                entity.setData(customerModel);
                entity.setErrcode(0);
                entity.setErrmsg("成功！");
            }else{
                entity.setErrcode(11);
                entity.setErrmsg("不存在客户信息！");
            }
        }catch (Exception e){
            entity.setErrcode(110);
            entity.setErrmsg("获取数据异常！");
            e.printStackTrace();
        }
        return entity;
	}
	
	@Override
	public JSONObject CustomerPotentialClueDetail_Select(String ClueID){
        //1.根据手机号项目ID码判断是否为机会客户
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ClueID", ClueID);
        Map<String, Object> data = bCustomerpotentialMapper.CustomerPotentialClueDetail_Select(param);
        if(data!=null && data.size()>0){
        	return new JSONObject(data);
        }
        return null;
    }

	@Override
	public Result mCustomerPotentialFollowUpList_Select(JSONObject paramAry) {
		Result entity = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
			List<Map<String, Object>> data = bCustomerpotentialMapper.mCustomerPotentialFollowUpList_Select(pmap);
			Long AllCount = bCustomerpotentialMapper.mCustomerPotentialFollowUpList_Select_Count(pmap);
			JSONObject j_data = new JSONObject();
	    	j_data.put("List", data);
	    	j_data.put("AllCount", AllCount);
	    	j_data.put("PageSize", paramAry.getInteger("PageSize"));
			entity.setData(j_data);
            entity.setErrcode(0);
            entity.setErrmsg("成功！");
		} catch (Exception e) {
			entity.setErrcode(1);
	        entity.setErrmsg("服务器出现异常！");
			e.printStackTrace();
		}
        return entity;
	}

	@Override
	public Result mCustomerPotentialZQDetail_Update(JSONObject paramAry) {
		Result entity = new Result();
		entity.setData("成功");
        try{
            CGWDetailModel model = JSONObject.parseObject(paramAry.toJSONString(),CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
            	JSONObject CustomerObj = CustomerPotentialClueDetail_Select(model.getClueID());
            	JSONObject Parameter = iCustomerPotentialTemplate.GetParameters(model, CustomerObj);
                if (Parameter.size() > 0){
                	Map<String,Object> pmap = JSONObject.parseObject(Parameter.toJSONString(), Map.class);
                	pmap.put("Name", Parameter.getString("LastName")+Parameter.getString("FirstName"));
                	//设置变更记录
                	iVCustomergwlistSelectService.addCustomerChangeInfo(pmap);
                	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_step1(pmap);
                	List<Map<String,Object>> valid1 = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_valid_1(pmap);
                	if(valid1!=null && valid1.size()>0){
                		bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_step3(pmap);
                	}else{
                		bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_step2(pmap);
                	}
                	
                	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_step4(pmap);
                	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_step5(pmap);
                	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Update_step6(pmap);
                	pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                	pmap.put("UpDownStatus", 1);
                	bCustomerpotentialMapper.P_ClueCustomerRank(pmap);
                	Boolean res = true;
                    if (res){
                        //增加跟进记录
                        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(Parameter.getString("FollwUpWay"));
                        if (!StringUtils.isEmpty(FollwUpType)){
                        	JSONObject obj = new JSONObject();
                            obj.put("FollwUpType", FollwUpType);
                            obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                            obj.put("SalesType", 3);
                            obj.put("NewSaleUserName", "");
                            obj.put("OldSaleUserName", "");
                            obj.put("FollwUpUserID",Parameter.getString("UserID"));
                            obj.put("FollwUpWay",Parameter.getString("FollwUpWay"));
                            obj.put("FollowUpContent", Parameter.getString("FollowUpContent"));
                            obj.put("IntentionLevel", Parameter.getString("CustomerLevel"));
                            obj.put("OrgID", Parameter.getString("OrgID"));
                            obj.put("FollwUpUserRole", Parameter.getString("JobID"));
                            obj.put("OpportunityID", "");
                            obj.put("ClueID", Parameter.getString("ClueID"));
                            obj.put("NextFollowUpDate",Parameter.getString("NextFollowUpDate"));
                            CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(),CustomerActionVo.class);
                            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                        }
                        CustomerPotentialClueFollowUpDetail_Update(Parameter);//潜在客户线索跟进记录更新
                    }
                    entity.setErrcode(0);
                    entity.setErrmsg("成功！");
                }else{
                    entity.setErrcode(1);
        	        entity.setErrmsg("参数格式错误！");
                }
            }
        }catch (Exception e){
        	entity.setErrcode(1);
	        entity.setErrmsg("服务器异常！");
        }
        return entity;
	}
	
	public Result CustomerPotentialClueFollowUpDetail_Update(JSONObject paramAry){
		Result entity = new Result();
		try {
			Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
			bCustomerpotentialMapper.CustomerPotentialClueFollowUpDetail_Update(pmap);
			entity.setErrcode(0);
            entity.setErrmsg("成功！");
		} catch (Exception e) {
			entity.setErrcode(1);
	        entity.setErrmsg("服务器出现异常！");
			e.printStackTrace();
		}
        return entity;
    }

	@Override
	public Result mCustomerPotentialZQDetail_Insert(JSONObject paramAry) {
		Result entity = new Result();
		entity.setErrmsg("成功");
        try{
            if (!StringUtils.isEmpty(paramAry.getString("FormSessionID"))){
                String formSessionID = paramAry.getString("FormSessionID");
                Long RowCount = vCustomergwlistSelectMapper.mSystemFormSessionStatus_Select_count(formSessionID);
                if (RowCount > 0){
                    entity.setErrcode(1);
        	        entity.setErrmsg("不能重复请求！");
                    return entity;
                }
            }
            CGWDetailModel model =JSONObject.parseObject(paramAry.toJSONString(), CGWDetailModel.class);
            if (model != null && model.getItemList() != null && model.getItemList().size() > 0){
            	JSONObject Parameter = iCustomerPotentialTemplate.GetParameters(model);
                if (Parameter.size() > 0){
                    String Mobile = Parameter.getString("Mobile");
                    String ChannelTaskID = Parameter.getString("ChannelTaskID");
                    if (!StringUtils.isEmpty(ChannelTaskID)){//渠道任务ID
                    	Map<String,Object> channelTaskParam = new HashMap<String, Object>();
                        channelTaskParam.put("ChannelTaskID",ChannelTaskID);
                        Result channelTaskEntiry = mChannelTaskDetail_Select(channelTaskParam);
                        if (channelTaskEntiry.getErrcode() == 0 && channelTaskEntiry.getData()!=null){
                        	JSONObject channelTask = (JSONObject)channelTaskEntiry.getData();
                            if (channelTask.size() > 0){
                                String TaskType = channelTask.getString("TaskType");
                                String Creator = channelTask.getString("Creator");
                                if (TaskType.length() > 0){
                                    model.setChannelTypeID(TaskType);
                                    Parameter.put("ChannelUserID", model.getUserID());
                                    Parameter.put("UserID",Creator);
                                }
                            }
                        }
                    }
                    if (StringUtils.isEmpty(model.getChannelTypeID())){//没有渠道身份ID
                        entity.setErrcode(20);
                        entity.setErrmsg("您当前角色不是渠道身份，不能报备");
                        return entity;
                    }
                    ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(model.getUserID(), model.getChannelTypeID(), model.getProjectID());
                    if (StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
                        entity.setErrcode(21);
                        entity.setErrmsg("未找到该渠道的报备规则");
                        return entity;
                    }
                    Result CustomerValidate = ValidateForReport(Mobile, model.getProjectID(),channelRegisterModel.getUserRule(),channelRegisterModel.getChannelUserId());
                    if (CustomerValidate.getErrcode() != 0){
                        entity.setErrcode(CustomerValidate.getErrcode());
                        entity.setErrmsg(GetMessageForReturn(CustomerValidate.getErrcode(), channelRegisterModel.getUserRule()));
                        return entity;
                    }
                    Parameter.put("AdviserGroupID",model.getChannelTypeID());
                    Parameter.put("ClueID", UUID.randomUUID().toString());
                    Parameter.put("CustomerID",StringUtils.isEmpty(Parameter.getString("CustomerID"))? UUID.randomUUID().toString(): Parameter.getString("CustomerID"));
                    Parameter.put("RuleID",channelRegisterModel.getUserRule().getRuleID());
                    Parameter.put("InvalidType",CustomerValidate.getErrcode());
                    Parameter.put("InvalidReason","");
                    Parameter.put("InvalidTime","");
                    Parameter.put("ComeOverdueTime",channelRegisterModel.getUserRule().getComeOverdueTime());
                    Parameter.put("TradeOverdueTime",channelRegisterModel.getUserRule().getTradeOverdueTime());
                    Parameter.put("IsSelect",channelRegisterModel.getUserRule().getProtectRule().getIsSelect());
                    Parameter.put("ConfirmUserId","99");
                    Map<String,Object> td = (Map<String, Object>) CustomerValidate.getData();
                    if(td!=null && td.size()>0){
                    	 Parameter.put("OppID",td.get("OppID"));
                    }else{
                    	 Parameter.put("OppID","");
                    }
                    
                    Map<String,Object> pmap = JSONObject.parseObject(Parameter.toJSONString(), Map.class);
                    pmap.put("Name", Parameter.getString("LastName")+Parameter.getString("FirstName"));
                    List<Map<String,Object>> valid_1_map = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_valid_1(pmap);
                    if(valid_1_map!=null && valid_1_map.size()>0){
                    	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step2(pmap);
                    }else{
                    	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step1(pmap);
                    }
                    List<Map<String,Object>> valid_2_map = bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_valid_2(pmap);
                    if(valid_2_map!=null && valid_2_map.size()>0){
                    	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step4(pmap);
                    }else{
                    	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step3(pmap);
                    }
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step5(pmap);
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step6(pmap);
                    bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step7(pmap);
                    if(!StringUtils.isEmpty(Parameter.getString("OppID"))){
                    	bCustomerpotentialMapper.mCustomerPotentialZQDetail_Insert_step8(pmap);
                    }
                    pmap.put("CustomerRank", "41FA0234-F8AE-434F-8BCD-6E9BE1D059DA");
                	pmap.put("UpDownStatus", 1);
                	bCustomerpotentialMapper.P_ClueCustomerRank(pmap);
                    Boolean res = true;
                    if (res){
                        JSONObject obj1 = new JSONObject();
                        obj1.put("FollwUpType", "渠道报备");
                        obj1.put("FollwUpTypeID", ActionType.渠道报备.getValue());
                        obj1.put("SalesType", 3);
                        obj1.put("NewSaleUserName", "");
                        obj1.put("OldSaleUserName", "");
                        obj1.put("FollwUpUserID", Parameter.getString("UserID"));
                        obj1.put("FollwUpWay", "");
                        obj1.put("FollowUpContent", "");
                        obj1.put("IntentionLevel", "");
                        obj1.put("OrgID", Parameter.getString("OrgID"));
                        obj1.put("FollwUpUserRole", Parameter.getString("JobID"));
                        obj1.put("OpportunityID", "");
                        obj1.put("ClueID", Parameter.getString("ClueID"));
                        obj1.put("NextFollowUpDate", "");
                        CustomerActionVo customerActionVo = JSONObject.parseObject(obj1.toJSONString(),CustomerActionVo.class);
                        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
                        //增加跟进记录
                        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(Parameter.getString("FollwUpWay"));
                        if (!StringUtils.isEmpty(FollwUpType)){
                        	JSONObject obj = new JSONObject();
                            obj.put("FollwUpType", FollwUpType);
                            obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
                            obj.put("SalesType", 3);
                            obj.put("NewSaleUserName", "");
                            obj.put("OldSaleUserName", "");
                            obj.put("FollwUpUserID", Parameter.getString("UserID"));
                            obj.put("FollwUpWay", Parameter.getString("FollwUpWay"));
                            obj.put("FollowUpContent", Parameter.getString("FollowUpContent"));
                            obj.put("IntentionLevel", Parameter.getString("CustomerLevel"));
                            obj.put("OrgID", Parameter.getString("OrgID"));
                            obj.put("FollwUpUserRole", Parameter.getString("JobID"));
                            obj.put("OpportunityID", "");
                            obj.put("ClueID", Parameter.getString("ClueID"));
                            obj.put("NextFollowUpDate", Parameter.getString("NextFollowUpDate"));
                            CustomerActionVo customerActionVo1 = JSONObject.parseObject(obj.toJSONString(),CustomerActionVo.class);
                            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo1);
                        }
                        CustomerPotentialClueFollowUpDetail_Update(Parameter);//潜在客户线索跟进记录更新
                    }
                    entity.setErrcode(0);
                    entity.setErrmsg("成功");
                }else{
                    entity.setErrcode(1);
                    entity.setErrmsg("参数格式错误！");
                }
            }
        }catch (Exception e){
            entity.setErrcode(1);
            entity.setErrmsg("服务器异常！");
            throw e;
        }
        return entity;
	}
	
	
	public Result mChannelTaskDetail_Select(Map<String,Object> Parameter){
		Result entity = new Result();
		try {
			Map<String, Object> data = bCustomerpotentialMapper.mChannelTaskDetail_Select(Parameter);
			if(data!=null && data.size()>0){
				entity.setData(new JSONObject(data));
				entity.setErrcode(0);
				entity.setErrmsg("成功");
			}else{
				entity.setErrcode(1);
				entity.setErrmsg("失败");
			}
		} catch (Exception e) {
			entity.setErrcode(1);
			entity.setErrmsg("服务器异常！");
			e.printStackTrace();
		}
        return entity;
    }
	
	public Result ValidateForReport(String phone, String projectId,RegisterRuleBaseModel UserRule,String ChannelUserId){
		Result msg = new Result();
        if (IsRepeatedReg(phone, projectId,ChannelUserId)){
            msg.setErrcode(9);
            return msg;
        }
        //竞争带看并且是现场确认模式
        if (UserRule.getRuleType() == 1 && UserRule.getImmissionRule().getValidationMode() == 1){
            //判断是否存在报备保护模式的有效线索，由于两种模式互斥，所以需要单独判断
            if (isExistReportProtectClue(phone, projectId)){
                msg.setErrcode(4);
                return msg;
            }else{
                //仅提示报备成功
                msg.setErrcode(0);
                return msg;
            }
        }else{//实时确认
            return InstantConfirmation(phone, projectId,UserRule,ChannelUserId);
        }

    }
	
	private Boolean IsRepeatedReg(String phone, String projectId, String useId){
		Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("Mobile", phone);
        obj.put("IntentProjectID", projectId);
        obj.put("ReportUserID", useId);
        Map<String, Object> data = bCustomerpotentialMapper.IsRepeatedReg_Select(obj);
        if(data!=null && data.size()>0){
        	return true;
        }
        return false;
    }
	
	private Boolean isExistReportProtectClue(String phone, String projectId){
		Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("Mobile", phone);
        obj.put("IntentProjectID", projectId);
        Map<String, Object> data = bCustomerpotentialMapper.IsExistReportProtectClue_Select(obj);
        if(data!=null && data.size()>0){
        	return true;
        }
        return false;
    }
	
	public Result ValidateForConfirmation(String clueId,ChannelRegisterModel channelRegisterModel){
		Result msg = new Result();
        msg = SceneConfirmation(clueId,channelRegisterModel.getUserRule(),channelRegisterModel.getChannelUserId());
        return msg;
    }
	
	public Result SceneConfirmation(String clueId,RegisterRuleBaseModel UserRule,String ChannelUserId){
		Result msg = new Result();
		JSONObject obj = getClueById(clueId);
        String phone =  obj.getString("Mobile");
        String projectId = obj.getString("IntentProjectID");
        //该线索已经被确认过
        if (obj.getIntValue("Status")== 2){
            msg.setErrcode(-1);
            msg.setErrmsg("该线索已被案场确认过！");
            return msg;
        }
        //判断待验证线索是否已经失效
        if (obj.getIntValue("Status") == 3){
        	msg.setErrcode(obj.getIntValue("InvalidType"));
            msg.setErrmsg(obj.getString("InvalidReason"));
            return msg;
        }
        //到访超过保护期
        if (IsOverdueCome(obj)){
            msg.setErrcode(7);
            return msg;
        }
        //是否在防截客时间到访
        Date CreateTime = obj.getDate("CreateTime");
        if (IsPreIntercept(CreateTime, UserRule)){
            msg.setErrcode(3);
            return msg;
        }
        msg = InstantConfirmation(phone, projectId, UserRule,ChannelUserId);
        return msg;
    }
	
	private JSONObject getClueById(String clueId){
		Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("ClueId", clueId);
        Map<String, Object> data = bCustomerpotentialMapper.UnconfirmedClueByClueId_Select(obj);
        if(data!=null && data.size()>0){
        	return new JSONObject(data);
        }
        return new JSONObject();
    }
	
	public Boolean IsOverdueCome(JSONObject clue){
        Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("ClueID", clue.get("ID"));
        Map<String,Object> data = bCustomerpotentialMapper.IsOverdueCome_Select(obj);
        if(data!=null && data.size()>0){
        	if(data.get("IsOverdueCome")!=null){
        		Number number = (Number)data.get("IsOverdueCome");
        		int IsOverdueCome = number.intValue();
        		if(IsOverdueCome==0){
        			return false;
        		}
        	}
        }
        return true;
    }

	public Boolean IsPreIntercept(Date createTime, RegisterRuleBaseModel UserRule){
        if (UserRule.getProtectRule().getIsPreIntercept() == 1){
            //报备时间+防截客周期小于等于当前时间
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTime(createTime);
        	calendar.set(Calendar.MINUTE, UserRule.getProtectRule().getPreInterceptTime());
            if (calendar.getTime().getTime() <= new Date().getTime()){
                return false;
            }else{
                return true;
            }
        }else{//没有防截客机制
            return false;
        }
    }
	
	private Boolean IsExistOpportunity(String phone, String projectId){
        Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("CustomerMobile", phone);
        obj.put("ProjectID", projectId);
        Map<String, Object> data = bCustomerpotentialMapper.IsExistOpportunity_Select(obj);
        if(data!=null && data.size()>0){
        	return true;
        }
        return false;
    }
	
	public Result InstantConfirmation(String phone,String projectId,RegisterRuleBaseModel UserRule,String ChannelUserId){
		Result msg = new Result();
        if (UserRule.getImmissionRule().getIsOnlyAllowNew()==1){//仅允许报备新客户
            //验证该项目是否已存在销售机会
            Boolean flag = IsExistOpportunity(phone, projectId);
            if (flag){//存在销售机会
                msg.setErrcode(2);
            }else{//不存在销售机会
                msg = validateClue(phone, projectId,UserRule,ChannelUserId);
            }

        }else{//允许报备满足条件的老客户（新客户+不在渠道保护期和案场保护期的老客户）
            //老业主限制，0.不允许报备老业主 1.仅允许报备其它项目老业主
            if (UserRule.getImmissionRule().getOldOwnerLimit() == 0){
                //验证是否集团老业主
                if (IsOwner(phone)){
                    msg.setErrcode(1);
                    return msg;
                }
            }else{
                //验证是否是本项目老业主
                if (IsProjectOwner(projectId, phone)){
                    msg.setErrcode(11);
                    return msg;
                }
            }
            msg = validateClue(phone, projectId,UserRule,ChannelUserId);
            if (msg.getErrcode()!=0)
                return msg;
            msg = validateOpp(phone, projectId);
        }
        return msg;
    }
	
	private Result validateOpp(String phone,String projectId){
		Result msg = new Result();
		JSONObject opp = getOpp(phone, projectId);
        //不存在销售机会
        if(opp==null || opp.size()==0){
            msg.setErrcode(0);
        }else{//存在销售机会
            if(StringUtils.isEmpty(opp.getString("SaleUserID"))){
                msg.setErrcode(0);
                Map<String,Object> msgData = new HashMap<String, Object>();
                msgData.put("OppID", opp.getString("ID"));
                msgData.put("IsExsitOpp", true);
                msg.setData(msgData);
            }else{
                msg.setErrcode(6);
            }
        }
        return msg;
    }
	
	private JSONObject getOpp(String phone,String projectId){
		Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("ProjectID", projectId);
        obj.put("CustomerMobile", phone);
        Map<String,Object> data = bCustomerpotentialMapper.ValidOpp_Select(obj);
        if(data!=null && data.size()>0){
        	return new JSONObject(data);
        }
        return new JSONObject();

    }
	
	private Boolean IsProjectOwner(String projectId, String phone){
		Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("ProjectID", projectId);
        obj.put("Mobile", phone);
        Map<String,Object> data = bCustomerpotentialMapper.IsProjectOwner_Select(obj);
        if(data!=null && data.size()>0){
        	return true;
        }
        return false;
    }
	
	private Boolean IsOwner(String phone){
        Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("Mobile", phone);
        Map<String,Object> data = bCustomerpotentialMapper.IsOwner_Select(obj);
        if(data!=null && data.size()>0){
        	return true;
        }
        return false;

    }
	
	private List<Map<String,Object>> getClueList(String phone,String projectId,String ChannelUserId){
        Map<String,Object> obj = new HashMap<String, Object>();
        obj.put("IntentProjectID", projectId);
        obj.put("Mobile", phone);
        obj.put("ReportUserID", ChannelUserId);
        List<Map<String,Object>> data = bCustomerpotentialMapper.RuleClueList_Select(obj);
        return data;

    }
	
	private Result validateClue(String phone, String projectId,RegisterRuleBaseModel UserRule,String ChannelUserId){
		Result msg = new Result();
        //查询项目下存在该手机号的有效线索
		List<Map<String,Object>> clues = getClueList(phone, projectId, ChannelUserId);
        //该项目已经存在已确认线索
		Boolean tc = false;
		for(Map<String,Object> clue : clues){
			int status = 0;
			if(clue.get("Status")!=null){
				Number number = (Number) clue.get("Status");
				status = number.intValue();
			}
			if(status==2){
				tc=true;
				break;
			}
		}
        if (tc){
            //报备保护
            if (UserRule.getRuleType() == 0) {
                msg.setErrcode(4);
            }else{
                msg.setErrcode(5);
            }
            return msg;
        }
        if (clues==null || clues.size() == 0){//该手机号为新客户（在允许报备老客户时，此段无意义，仅存在于报备新客户）
            msg.setErrcode(0);
            return msg;
        }
        if (clues!=null && clues.size() == 1){//该手机号线索已存在，此条件下需要进一步证明规则类型
        	Number number = (Number) clues.get(0).get("RuleType");
            int ruleType = number.intValue();
            if (UserRule.getRuleType() == ruleType && ruleType == 1){//线索为竞争带看，并且当前渠道用户规则也为竞争带看
                msg.setErrcode(0);
            }else{//线索为报备保护
                msg.setErrcode(4);
            }
        }else{//该手机号存在多条线索，说明报备模式为竞争带看
        	Number number = (Number)clues.get(0).get("RuleType");
            int ruleType = number.intValue();
            if (UserRule.getRuleType() == ruleType){//当前渠道的报备规则也为竞争带看
                msg.setErrcode(0);
            }else{//当前渠道的报备规则为报备保护，报备规则模式不同则互斥
                msg.setErrcode(4);
            }
        }
        return msg;
    }
	
	public String GetMessageForReturn(int invalidType, RegisterRuleBaseModel userRule){
        String msg = "";
        switch (invalidType){
            case 0:
                msg = userRule.getRuleType() == 1 ? "报备成功，为避免其他渠道抢先带看，请尽快到案场进行带客确认！" : "报备成功，您可以在“我的客户”中查看客户的最新状态！";
                //msg = userRule.RuleType == 1 && userRule.ImmissionRule.ValidationMode == 1 ? "报备成功，为避免其他渠道抢先带看，请尽快到案场进行带客确认！" : "报备成功，您可以在“我的客户”中查看客户的最新状态！";
                break;
            case 1:
                msg = "报备无效，该客户为集团老业主！";
                break;
            case 2:
                msg = "报备无效，该客户为项目老客户！";
                break;
            case 3:
                msg = "报备无效，不满足防截客条件！";
                break;
            case 4:
                msg = "报备无效，该客户已被其他渠道报备！";
                break;
            case 5:
                msg = "报备无效，该客户已被其他渠道带看！";
                break;
            case 6:
                msg = "报备无效，该客户处于保护期内！";
                break;
            case 7:
                msg = "报备无效，该客户到访逾期！";
                break;
            case 8:
                msg = "报备无效，该客户成交逾期！";
                break;
            case 9:
                msg = "报备无效，该客户已被您报备！";
                break;
            case 10:
                msg = "该客户在案场跟进过程中丢失！";
                break;
            case 11:
                msg = "报备无效，该客户为本项目老业主！";
                break;
        }
        return msg;
    }

	@Override
	@Transactional(readOnly=false)
	public Result mCustomerPotentialZQSearch_Select(JSONObject paramAry) {
		Result entity = new Result();
		try {
			CSearchModelVo model = JSONObject.parseObject(paramAry.toJSONString(),CSearchModelVo.class);
	        String jsonFile = "";
	        int isNew = 1;
	        String customerPotentialModeType = CustomerPotentialModeType.无.getTypeID();
	        JSONObject CustomerObj = new JSONObject();
	        if (!StringUtils.isEmpty(model.getMobile())){
	            CustomerPotentialMobileSearchDetail_Insert(model.getProjectID(), model.getMobile(), model.getJobCode(), model.getUserID());
	        }
	        if (StringUtils.isEmpty(model.getClueID())){//新线索
	            if (!StringUtils.isEmpty(model.getChannelTaskID())){//渠道任务ID
	                JSONObject channelTaskParam = new JSONObject();
	                channelTaskParam.put("ChannelTaskID",model.getChannelTaskID());
	                Result channelTaskEntiry = mChannelTaskDetail_Select(channelTaskParam);
	                if (channelTaskEntiry.getErrcode() == 0 && channelTaskEntiry.getData()!=null){
	                	JSONObject channelTask = (JSONObject)channelTaskEntiry.getData();
	                    if (channelTask.size() > 0){
	                        String TaskType = channelTask.getString("TaskType");
	                        String ProjectID = channelTask.getString("ProjectID");
	                        if (TaskType.length() > 0){
	                            model.setChannelTypeID(TaskType);
	                        }
	                        if (ProjectID.length() > 0){
	                            model.setProjectID(ProjectID);
	                        }
	                    }
	                }
	            }
	            if (StringUtils.isEmpty(model.getProjectID())){//没有渠道身份ID
	                entity.setErrcode(19);
	                entity.setErrmsg("您没有报备项目归属，不能报备");
	                return entity;
	            }
	            if (StringUtils.isEmpty(model.getChannelTypeID())){//没有渠道身份ID
	            	entity.setErrcode(20);
	                entity.setErrmsg("您当前角色不是渠道身份，不能报备");
	                return entity;
	            }
	            ChannelRegisterModel channelRegisterModel = iBChannelService.newChannelRegisterModel(model.getUserID(), model.getChannelTypeID(), model.getProjectID());
	            if (StringUtils.isEmpty(channelRegisterModel.getUserRule().getRuleID())){
	                entity.setErrcode(21);
	                entity.setErrmsg("未找到该渠道的报备规则");
	                return entity;
	            }
	            Result CustomerValidate = ValidateForReport(model.getMobile(), model.getProjectID(),channelRegisterModel.getUserRule(),channelRegisterModel.getChannelUserId());
	            if (CustomerValidate.getErrcode() != 0){
	                entity.setErrcode(CustomerValidate.getErrcode());
	                entity.setErrmsg(GetMessageForReturn(CustomerValidate.getErrcode(), channelRegisterModel.getUserRule()));
	                return entity;
	            }
	            CustomerObj = CustomerPotentialDetail_Select(model.getMobile());
	            if (CustomerObj!=null && CustomerObj.size() > 0){//存在潜在客户
	                jsonFile = "ZQNewCustomerPotential.json";
	                customerPotentialModeType = CustomerPotentialModeType.自渠_新线索_老潜在客户.getTypeID();
	            }else{
	                jsonFile = "ZQNewCustomerPotential.json";
	                customerPotentialModeType = CustomerPotentialModeType.自渠_新线索_新潜在客户.getTypeID();
	            }
	        }
	        else{//老线索
	            CustomerObj = CustomerPotentialClueDetail_Select(model.getClueID());
	            jsonFile = "ZQNewCustomerPotential.json";
	            customerPotentialModeType = CustomerPotentialModeType.自渠_客户_更新.getTypeID();
	            isNew = 0;
	        }
	        CustomerModelVo customerModel = iCustomerPotentialTemplate.InitCustomerPotentialModeData(model, jsonFile, CustomerObj, customerPotentialModeType);
	        customerModel.setIsNew(isNew);
	        entity.setData(customerModel);
	        entity.setErrcode(0);
	        entity.setErrmsg("成功");
		} catch (Exception e) {
			entity.setErrcode(1);
		    entity.setErrmsg("服务器异常！");
			throw e;
		}
        return entity;
	}
	
	public Boolean CustomerPotentialMobileSearchDetail_Insert(String ProjectID, String Mobile, String JobCode, String UserID){
        try{
            Map<String,Object> pram = new HashMap<String, Object>();
            pram.put("ProjectID",ProjectID);
            pram.put("Mobile",Mobile);
            pram.put("JobCode",JobCode);
            pram.put("UserID",UserID);
            Map<String, Object> select_data = bCustomerpotentialMapper.CustomerPotentialMobileSearchDetail_Insert_select(pram);
            if(select_data!=null && select_data.size()>0){
            	pram.put("ClueID",select_data.get("ClueID"));
            	bCustomerpotentialMapper.CustomerPotentialMobileSearchDetail_Insert(pram);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
	
	public JSONObject CustomerPotentialDetail_Select(String Mobile){
        //1.根据手机号项目ID码判断是否为机会客户
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("Mobile",Mobile);
        List<Map<String,Object>> data = bCustomerpotentialMapper.CustomerPotentialDetail_Select(param);
        if(data!=null && data.size()>0){
        	return new JSONObject(data.get(0));
        }
        return new JSONObject();

    }

	@Override
	public Result mCustomerPotentialZQList_Select(JSONObject paramAry) {
		Result re = new Result();
		try {
			GWCustomerPageModel model = JSONObject.parseObject(paramAry.toJSONString(),GWCustomerPageModel.class);
	        StringBuilder whereSb = new StringBuilder();
	        StringBuilder whereinnerSb = new StringBuilder();
	        StringBuilder orderSb = new StringBuilder();
	        //项目
	        if (!"JZ".equals(model.getJobCode())){
	            if (!StringUtils.isEmpty(model.getProjectID())){
	                whereinnerSb.append(" AND t.IntentProjectID = '"+model.getProjectID()+"' ");
	            }
	        }
	        //手机或姓名
	        if (!StringUtils.isEmpty(model.getKeyWord())){
	            whereinnerSb.append(" and (t.Name Like '%"+model.getKeyWord()+"%' or t.Mobile Like '%"+model.getKeyWord()+"%'  or t.SpareMobile Like '%"+model.getKeyWord()+"%') ");
	        }
	        if (model.getFilter() != null && model.getFilter().size() > 0){
	            for (FilterItem filterItem : model.getFilter()){
	                if (filterItem != null && filterItem.getTagID() != null){
	                    //我的关注
	                    if ("9526CC1F-47CE-4479-8EB8-AFE73ADAF1F4".equals(filterItem.getTagID())){
	                        if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
	                            whereSb.append(" and  IsCare ='1'");
	                        }
	                    }
	                    //客户状态
	                    if ("2418AA00-59DA-4501-8E23-035810B9BDA0".equals(filterItem.getTagID())){
	                        if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
	                            for (int i = 0; i < filterItem.getChild().size(); i++){
	                                switch (filterItem.getChild().get(i)){
	                                    case "3CE19EDC-29D8-499A-B577-E919BB9B6505": filterItem.getChild().set(i, "1"); break;//报备
	                                    case "071A80D9-A331-4826-A910-1C00A92AAF2E": filterItem.getChild().set(i, "2"); break;//确认
	                                    case "F4B322C7-3B97-4117-9AE3-3270266FF548": filterItem.getChild().set(i, "12"); break;//看房
	                                    case "BE092A1A-FA55-432B-B4AC-82DD48422B61": filterItem.getChild().set(i, "14"); break;//认购
	                                    case "875C15B4-671E-446D-AC6F-F882956287D3": filterItem.getChild().set(i, "15"); break;//签约
	                                    case "1B76AC9B-4BA0-4C18-92E0-DF08C867CC99": filterItem.getChild().set(i, "17"); break;//退房
	                                    case "7CC675D2-A61B-4325-BE01-FAA8E597EBCF": filterItem.getChild().set(i, "3"); break;//无效
	                                }
	                            }
	                            whereSb.append(" and Status in('" + String.join("','", filterItem.getChild()) + "')");
	                        }
	                    }
	                    //意向级别
	                    if ("F0772E70-708F-49F3-83ED-C7E4B982FFC2".equals(filterItem.getTagID())){
	                        if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
	                            for (int i = 0; i < filterItem.getChild().size(); i++){
	                                switch (filterItem.getChild().get(i)){
	                                    case "4E69B87D-CF3E-41BE-9D20-F6235E6EC079": filterItem.getChild().set(i, "2A357E4A-90D7-5D69-C209-E26CFA5839FA"); break;//A级
	                                    case "24ACB315-C82A-42B7-AD53-8F6E5CF2F8DD": filterItem.getChild().set(i, "DF2057E2-303B-1F14-4075-069668D3A3BE"); break;//B级
	                                    case "1319E9DF-C8CC-4413-91FC-7FABB1F8BF29": filterItem.getChild().set(i, "9CEA46E8-A3ED-409E-646C-F38A5EAC383E"); break;//C级
	                                    case "B999A9DE-CA95-402A-9A29-FAA6A1567AC4": filterItem.getChild().set(i, "FA35879A-CCE4-D332-0FAB-ADB57EBCAC9D"); break;//D级
	                                }
	                            }
	                            whereinnerSb.append(" and t.CustomerLevel in('" + String.join("','", filterItem.getChild()) + "')");
	                        }
	                    }
	                    //标签
	                    if ("885AC55E-7FC5-467A-9809-82AC9867363D".equals(filterItem.getTagID())){
	                        if (filterItem.getChild() != null && filterItem.getChild().size() > 0){
	                            whereinnerSb.append(" and t.CustomerPotentialID in(select  CustomerPotentialID  from  B_CustomerPotentialTag   where TagName in('" + String.join("','", filterItem.getChild()) + "'))");
	                        }
	                    }
	                }
	            }

	        }
	        if (!StringUtils.isEmpty(model.getSort())){
	            if ("559312B1-E6E5-41EC-80F8-98425F8768EB".equals(model.getSort())){//最新跟进
	                orderSb.append(" ORDER BY  TheLatestFollowUpDate desc ");
	            }
	            if ("EF309A49-DEAE-4D0A-BDB2-4AA26960484B".equals(model.getSort())){//最早跟进
	                orderSb.append(" ORDER BY  TheLatestFollowUpDate  asc ");
	            }
	            if ("2CBC649B-FAA8-4DB8-9F55-3F891ECEA28C".equals(model.getSort())){//最新创建
	                orderSb.append(" ORDER BY  CreateTime desc ");
	            }
	            if ("0D9D3B7C-EF8D-492B-ABFF-A438DDBB2F09".equals(model.getSort())){//最早创建
	                orderSb.append(" ORDER BY  CreateTime asc ");
	            }
	        }
	        if (orderSb.length() == 0){
	            orderSb.append(" ORDER BY  TheLatestFollowUpDate Desc ");
	        }
	        //判断是否为兼职
	        if ("JZ".equals(model.getJobCode())){
	        	String UserID = model.getUserID()!=null?model.getUserID():"";
	            whereinnerSb.append(" AND t.ChannelUserID = '"+UserID+"' ");
	        }else if ("ZQFZR".equals(model.getJobCode())){
	        	String ReportUserID = model.getReportUserID()!=null?model.getReportUserID():"";
	        	whereinnerSb.append(" AND t.ReportUserID = '"+ReportUserID+"' ");
	        }else{
	        	String UserID = model.getUserID()!=null?model.getUserID():"";
	            whereinnerSb.append(" AND t.ReportUserID = '"+UserID+"' ");
	        }
	        //判断是否有渠道任务ID
	        if (!StringUtils.isEmpty(model.getChannelTaskID())){
	        	String ChannelTaskID=model.getChannelTaskID()!=null?model.getChannelTaskID():"";
	            whereinnerSb.append(" AND t.ChannelTaskID='"+ChannelTaskID+"' ");
	        }
	        paramAry.put("WHERE", whereSb.toString());
	        paramAry.put("WHEREINNER", whereinnerSb.toString());
	        paramAry.put("ORDER", orderSb.toString());
	        
	        Map<String,Object> pmap = JSONObject.parseObject(paramAry.toJSONString(), Map.class);
	        List<Map<String, Object>> data = bCustomerpotentialMapper.mCustomerPotentialZQList_Select(pmap);
	        Long recordCount = bCustomerpotentialMapper.mCustomerPotentialZQList_Select_count(pmap);
	        Map<String,Object> re_map = new HashMap<String, Object>();
	        re_map.put("List", data);
	        re_map.put("AllCount", recordCount);
	        re_map.put("PageSize", paramAry.get("PageSize"));
	        
	        String dataStr = new JSONObject(re_map).toJSONString();
            JSONObject data_re = JSONObject.parseObject(dataStr);
            
	        re.setData(data_re);
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
	public Result mCustomerPotentialFollowUpDetail_Insert(JSONObject Parameter) {
		Result re = new Result();
		try {
			Parameter.put("FollwUpUserID",Parameter.getString("UserID"));
	        String ChannelTaskID = Parameter.getString("ChannelTaskID");
	        if (!StringUtils.isEmpty(ChannelTaskID)){//渠道任务ID
	            Map<String,Object> channelTaskParam = new HashMap<String, Object>();
	            channelTaskParam.put("ChannelTaskID",ChannelTaskID);
	            Result channelTaskEntiry = mChannelTaskDetail_Select(channelTaskParam);
	            if (channelTaskEntiry.getErrcode() == 0){
	                JSONObject channelTaskList = (JSONObject)channelTaskEntiry.getData();
	                if (channelTaskList!=null && channelTaskList.size() > 0){
	                    String Creator = channelTaskList.getString("Creator");
	                    if (Creator.length() > 0){
	                        Parameter.put("FollwUpUserID",Creator);
	                    }
	                }
	            }
	        }
	        //Frame.DBhelper.JsonDataHelper.SetNormalData("mCustomerPotentialFollowUpDetail_Insert", Parameter, out errmsg, debug);
	        //增加跟进记录
	        String FollwUpType = CareerConsCustConverter.GetCustomerActionByFollowUpWay(Parameter.getString("Mode"));
	        if (!StringUtils.isEmpty(FollwUpType)){
	        	JSONObject obj = new JSONObject();
	            obj.put("FollwUpType", FollwUpType);
	            obj.put("FollwUpTypeID", ActionType.valueOf(FollwUpType).getValue());
	            obj.put("SalesType", 3);
	            obj.put("NewSaleUserName", "");
	            obj.put("OldSaleUserName", "");
	            obj.put("FollwUpUserID",Parameter.getString("FollwUpUserID"));
	            obj.put("FollwUpWay",Parameter.getString("Mode"));
	            obj.put("FollowUpContent",Parameter.getString("Content"));
	            obj.put("IntentionLevel",Parameter.getString("Level"));
	            obj.put("OrgID",Parameter.getString("OrgID"));
	            obj.put("FollwUpUserRole", Parameter.getString("JobID"));
	            obj.put("OpportunityID", "");
	            obj.put("ClueID", Parameter.getString("ClueID"));
	            obj.put("NextFollowUpDate", Parameter.getString("NextTime"));
	            obj.put("Creator", Parameter.getString("UserID"));
	            obj.put("Editor", Parameter.getString("UserID"));
	            
	            CustomerActionVo customerActionVo = JSONObject.parseObject(obj.toJSONString(),CustomerActionVo.class);
	            iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
	        }
	        CustomerPotentialClueFollowUpDetail_Update(Parameter);//潜在客户线索跟进记录更新
	        //处理跟进类待办为已办
	        iSystemMessageService.DetailByHandle_Update(new String[]{Parameter.getString("ClueID")}, "Clue", MessageHandleType.新增跟进.getValue());
	        re.setErrcode(0);
	        re.setErrmsg("成功");
		} catch (Exception e) {
			re.setErrcode(1);
	        re.setErrmsg("服务器异常！");
			e.printStackTrace();
		}
        return re;
	}
}
