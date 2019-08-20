package com.tahoecn.xkc.service.channel.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.enums.ActionType;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.rule.BCluerule;
import com.tahoecn.xkc.model.vo.ChannelRegisterModel;
import com.tahoecn.xkc.model.vo.CustomerActionVo;
import com.tahoecn.xkc.model.vo.ImmissionRule;
import com.tahoecn.xkc.model.vo.ProtectRule;
import com.tahoecn.xkc.model.vo.RegisterRuleBaseModel;
import com.tahoecn.xkc.service.channel.IBChannelService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.impl.VCustomergwlistSelectServiceImpl;
import com.tahoecn.xkc.service.rule.IBClueruleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class BChannelServiceImpl extends ServiceImpl<BClueMapper,BClue> implements IBChannelService {
	@Value("${SiteUrl}")
    private String SiteUrl;
	@Resource
	private BChanneluserMapper bChanneluserMapper;
	@Autowired
    private IBClueruleService clueruleService;
	@Autowired
	private BClueMapper bClueMapper;
	@Autowired
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	@Autowired
	private BCustomerMapper bCustomerMapper;
	@Autowired
    private IBClueService iBClueService;
	@Autowired
    private IBClueruleService iBClueruleService;
	@Autowired
    private IBClueService clueService;
    @Autowired
    private VCustomergwlistSelectServiceImpl iVCustomergwlistSelectService;
	
	
	/**
	 * 扫码确认
	 */
	@Override
	public Result mCaseFieCustomerDetail_Select(JSONObject jsonParam) {
		Result re = new Result();
		@SuppressWarnings("rawtypes")
		Map paramMap = (HashMap)jsonParam.get("_param");
        //参数验证
        if (StringUtils.isEmpty(paramMap.get("TypeID"))){
            re.setErrcode(1);
            re.setErrmsg("参数 TypeID 缺失");
        }
        if (!StringUtils.isEmpty(paramMap.get("TypeID")) && paramMap.get("TypeID").equals("1")){
        	if(StringUtils.isEmpty(paramMap.get("TypeID"))
        			|| StringUtils.isEmpty(paramMap.get("TokerUserID"))
        			|| StringUtils.isEmpty(paramMap.get("SourceType"))
        			|| StringUtils.isEmpty(paramMap.get("ProjectID"))
        			|| StringUtils.isEmpty(paramMap.get("ClueID"))){
        		re.setErrcode(1);
                re.setErrmsg("参数缺失");
        	}
        }
        //判断当前项目和意向项目是否一致 不一致则返回错误
        paramMap.put("SiteUrl", SiteUrl);
        List<Map<String,Object>> ob = iBClueService.CaseFieCustomerDetail_Select(paramMap);
        if (!ob.get(0).get("IntentProjectID").toString().toLowerCase().equals(paramMap.get("ProjectID").toString().toLowerCase())){
            return Result.errormsg(9,"报备项目和案场项目不一致,请确认带客项目");
        }
        //验证规则 TypeID 表示是否验证规则 1 表示验证 其他表示不验证
        //TypeID 表示是否验证规则 1 表示验证 其他表示不验证
        if (paramMap.get("TypeID").equals("1")){
            re =  clueConfirm(paramMap);
        }
        //查询
        //获取客户信息
        ob = iBClueService.CaseFieCustomerDetail_Select(paramMap);
        //获取线索信息
        int invalidType = ob.get(0).get("InvalidType")== null ? -1 : (short)ob.get(0).get("InvalidType");//无效类型
        String InvalidReason = (String) ob.get(0).get("InvalidReason");//无效说明
        //获取无效信息
        Map<String,Object> RuleP = new HashMap<String,Object>();
        RuleP.put("Mobile", ob.get(0).get("Mobile"));
        List<Map<String,Object>> inval = iBClueService.CaseFieInvalDetail_Select(RuleP);
        //获取规则信息
        RuleP = new HashMap<String,Object>();
        RuleP.put("ID", ob.get(0).get("RuleID"));
        BCluerule rule = iBClueruleService.CaseFieRuleDetail_Select(RuleP);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        //组建规则说明 根据规则id获取规则
        List<String> ruleList = new ArrayList<String>();
        if(rule != null){
        	if ("1".equals(rule.getIsOnlyAllowNew())){
        		ruleList.add(rule.getRuleName() + "，仅允许报备项目新客户");
        	}
        	if (rule.getIsOnlyAllowNew() != null && rule.getIsOnlyAllowNew() == 0){
        		if (rule.getOldOwnerLimit() != null && rule.getOldOwnerLimit() == 0){
        			ruleList.add(rule.getRuleName() + "，允许报备新客户或者不在保护期的老客户，且不允许报备集团老业主");
        		}
        		if (rule.getOldOwnerLimit() != null && rule.getOldOwnerLimit() == 1){
        			ruleList.add(rule.getRuleName() + "，允许报备新客户或者不在保护期的老客户，且仅允许报备其他项目的老业主");
        		}
        	}
        	if (rule.getFollowUpOverdueDays() != null && rule.getFollowUpOverdueDays() > 0){
        		ruleList.add("保护期：距离上一次跟进时间超过" + rule.getFollowUpOverdueDays() + "天未跟进自动失效");
        	}
        	if (rule.getIsProtect() != null && rule.getIsProtect() == 1){
        		String leixing = "";
        		if ("1".equals(rule.getUserBehaviorID())){
        			leixing = ("签约");
        		}
        		if ("2".equals(rule.getUserBehaviorID())){
        			leixing = ("认购");
        		}
        		if ("3".equals(rule.getUserBehaviorID())){
        			leixing = ("认筹");
        		}
        		if (rule.getIsSelect() != null && rule.getIsSelect() == 1){
        			ruleList.add("保护期模式：两段式 报备-到访-" + leixing);
        			ruleList.add("到访保护期" + rule.getProtectVisitTime() + "天，提前" + rule.getProtectVisitRemindTime() + "天提醒");
        		}else{
        			ruleList.add("启用保护期模式：一段式 报备-" + leixing);
        		}
        		ruleList.add(leixing + "保护期" + rule.getProtectTime() + "天，提前" + rule.getProtectRemindTime() + "天提醒");
        		
        	}
        	if (rule.getIsPreIntercept() != null && rule.getIsPreIntercept() == 1){
        		ruleList.add("启用防截客时间" + rule.getPreInterceptTime() + "分钟");
        	}
        	ruleList.add("规则在：" + (rule.getTakeEffectTime() == null ? "" :sdf.format(rule.getTakeEffectTime())) + "生效");
        }
        //无效信息 根据手机号获取有效的线索
        List<Map<String,Object>> invalidList = new ArrayList<Map<String,Object>>();
        Map<String,Object> jos = null;
        //老客户
        if (invalidType == 2){
            jos = new HashMap<String,Object>();
            jos.put("Name", "无效说明");
            jos.put("Value", InvalidReason);
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "职业顾问名称");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClSaleUserName"));
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备时间");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreateTime"));
            invalidList.add(jos);
        }else if(invalidType == 4){
            //被其他渠道报备
            jos = new HashMap<String,Object>();
            jos.put("Name", "无效说明");
            jos.put("Value", InvalidReason);
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备人");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreator"));
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备时间");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreateTime"));
            invalidList.add(jos);
        }else if (invalidType == 5){
            //被其他渠道带看
            jos = new HashMap<String,Object>();
            jos.put("Name", "无效说明");
            jos.put("Value", InvalidReason);
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备人");
            jos.put("Value",(inval.size()==0)?"": inval.get(0).get("ClCreator"));
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备时间");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreateTime"));
            invalidList.add(jos);
        }else if (invalidType == 1){
            //报备的客户为集团老业主
            jos = new HashMap<String,Object>();
            jos.put("Name", "无效说明");
            jos.put("Value", InvalidReason);
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "职业顾问名称");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClSaleUserName"));
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备时间");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreateTime"));
            invalidList.add(jos);
        }else if (invalidType == 3 || invalidType == 7 || invalidType == 8){
            //防截客时间内到访
            jos = new HashMap<String,Object>();
            jos.put("Name", "无效说明");
            jos.put("Value", InvalidReason);
            invalidList.add(jos);
        }else if (invalidType == 0){
            //默认
            jos = new HashMap<String,Object>();
            jos.put("Name", "无效说明");
            jos.put("Value", InvalidReason);
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备人");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreator"));
            invalidList.add(jos);
            jos = new HashMap<String,Object>();
            jos.put("Name", "报备时间");
            jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreateTime"));
            invalidList.add(jos);
		}else{
		    jos = new HashMap<String,Object>();
		    jos.put("Name", "无效说明");
		    jos.put("Value", InvalidReason);
		    invalidList.add(jos);
		    jos = new HashMap<String,Object>();
		    jos.put("Name", "报备人");
		    jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreator"));
		    invalidList.add(jos);
		    jos = new HashMap<String,Object>();
		    jos.put("Name", "报备时间");
		    jos.put("Value", (inval.size()==0)?"":inval.get(0).get("ClCreateTime"));
		    invalidList.add(jos);
		}
        //组建json
        Map<String,Object> jo = new HashMap<String,Object>();
        jo.put("ClueList", JSON.parseObject(JSON.toJSONString(ob.get(0))));
        jo.put("CluruleListeList", ruleList);
        jo.put("InvalidList", invalidList);
        return Result.ok(jo);
	}
	
	private Result clueConfirm(Map<String,Object> Parameter) {
		Result re = new Result();
        String channelTypeID = getChannelTypeID(Parameter.get("ClueID").toString());
        if (StringUtils.isEmpty(channelTypeID)){
        	return Result.errormsg(1,"未找到渠道身份");
        }
        Parameter.put("AdviserGroupID",channelTypeID);
        ChannelRegisterModel channel = this.newChannelRegisterModel(Parameter.get("TokerUserID").toString(), channelTypeID, Parameter.get("ProjectID").toString());
        Map<String,Object> ruleValidate = this.ValidateForConfirmation(Parameter.get("ClueID").toString(),channel);
        String invalidReason = clueService.getMessage((int)ruleValidate.get("InvalidType"),JSONUtil.parseObj(channel.getUserRule()));
        DateTime visitTime = DateTime.now();
        Parameter.put("VisitTime", visitTime);
        Parameter.put("ConfirmUserId", Parameter.get("UserID"));

        //通过有效验证
        if ((boolean) ruleValidate.get("Tag")){
            String tradeOverTime = "";
            Parameter.put("TradeOverdueTime", tradeOverTime);
            re.setErrcode(0);
            re.setErrmsg("带看确认成功！");
            re.setData(iBClueService.ClueConfirm_Update(Parameter));
        }else{//验证不通过
            if ((int)ruleValidate.get("InvalidType") == -1){
                return Result.errormsg(1, ruleValidate.get("Message").toString());
            }
            re.setErrcode(1);
            re.setErrmsg(clueService.GetMessageForReturn((int)ruleValidate.get("InvalidType"),JSONUtil.parseObj(channel.getUserRule())));
            Parameter.put("InvalidType", ruleValidate.get("InvalidType"));
            Parameter.put("InvalidReason", invalidReason);
            //更新当前验证的线索的状态为3（无效）、确认时间、确认人、无效类型和无效原因
            iBClueService.ClueConfirmInvalid_Update(Parameter);
        }
        String follwUpType = (boolean)ruleValidate.get("Tag") == true ? ActionType.确客有效.toString() : ActionType.报备无效.toString();
        CustomerActionVo customerActionVo = new CustomerActionVo();
		customerActionVo.setFollwUpType(follwUpType);
		customerActionVo.setFollwUpTypeID(ActionType.valueOf(follwUpType).getValue());
		customerActionVo.setSalesType(4);
		customerActionVo.setNewSaleUserName("");
		customerActionVo.setOldSaleUserName("");
		customerActionVo.setFollwUpUserID(Parameter.get("UserID").toString());
		customerActionVo.setFollwUpWay("");
		customerActionVo.setFollowUpContent("");
		customerActionVo.setIntentionLevel("");
		customerActionVo.setOrgID(Parameter.get("OrgID").toString());
		customerActionVo.setFollwUpUserRole(Parameter.get("JobID").toString());
		customerActionVo.setOpportunityID("");
		customerActionVo.setClueID(Parameter.get("ClueID").toString());
		customerActionVo.setNextFollowUpDate("");
        iVCustomergwlistSelectService.CustomerFollowUp_Insert(customerActionVo);
        return Result.ok(re);
	}
	
	@Override
	public ChannelRegisterModel newChannelRegisterModel(String userId, String adviserGroupID, String projecId){
        String channelOrgId = GetChannelOrgID(userId);
        //如果渠道是分销中介，则把渠道身份赋值成机构ID
        adviserGroupID = StringUtils.isEmpty(channelOrgId) ? adviserGroupID : channelOrgId;
        //获取当前渠道的准入规则
        Map<String,Object> UserRule1 = clueruleService.getRegisterRule(projecId,adviserGroupID);
        RegisterRuleBaseModel UserRule = loadModel(UserRule1);//JSON.parseObject(JSON.toJSONString(UserRule1), RegisterRuleBaseModel.class);
//        this.UserRule = new RegisterRuleBaseModel(adviserGroupID, projecId);
        ChannelRegisterModel model = new ChannelRegisterModel();
        model.setChannelUserId(userId);
        model.setChannelOrgId(channelOrgId);
        model.setUserRule(UserRule);
        return model;
    }
	/**
     * 获取渠道人员所属组织ID
     */
    private String GetChannelOrgID(String userId){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("UserID", userId);
        List<Map<String,Object>> data = bChanneluserMapper.GetChannelOrgID_Select(obj);
        return (data != null && data.size() > 0) ? data.get(0).get("ChannelOrgID").toString() : "";
    }
	private RegisterRuleBaseModel loadModel(Map<String, Object> obj) {
		ImmissionRule imm = new ImmissionRule();
		if(obj.get("IsOnlyAllowNew")!=null){
			Number IsOnlyAllowNew = (Number) obj.get("IsOnlyAllowNew");
			imm.setIsOnlyAllowNew(IsOnlyAllowNew.intValue());
		}
		if(obj.get("OverdueTime")!=null){
			Number OverdueTime = (Number) obj.get("OverdueTime");
	        imm.setOverdueTime(OverdueTime.intValue());
		}
		if(obj.get("ProtectTypeID")!=null){
			Number ProtectTypeID = (Number) obj.get("ProtectTypeID");
	        imm.setProtectTypeID(ProtectTypeID.intValue());
		}
		if(obj.get("ValidationMode")!=null){
			Number ValidationMode = (Number) obj.get("ValidationMode");
	        imm.setValidationMode(ValidationMode.intValue());
		}
		if(obj.get("OldOwnerLimit")!=null){
			Number OldOwnerLimit = (Number) obj.get("OldOwnerLimit");
	        imm.setOldOwnerLimit(OldOwnerLimit.intValue());
		}

        ProtectRule pro = new ProtectRule();
        
        if(obj.get("IsPreIntercept")!=null){
        	Number IsPreIntercept = (Number) obj.get("IsPreIntercept");
            pro.setIsPreIntercept(IsPreIntercept.intValue());
        }
        if(obj.get("PreInterceptTime")!=null){
        	Number PreInterceptTime = (Number) obj.get("PreInterceptTime");
            pro.setPreInterceptTime(PreInterceptTime.intValue());
        }
        if(obj.get("IsProtect")!=null){
        	Number IsProtect = (Number) obj.get("IsProtect");
            pro.setIsProtect(IsProtect.intValue());
        }
        if(obj.get("IsProtectVisit")!=null){
        	Number IsProtectVisit = (Number) obj.get("IsProtectVisit");
            pro.setIsProtectVisit(IsProtectVisit.intValue());
        }
        if(obj.get("IsSelect")!=null){
        	Number IsSelect = (Number) obj.get("IsSelect");
            pro.setIsSelect(IsSelect.intValue());
        }
        if(obj.get("ProtectTime")!=null){
        	Number ProtectTime = (Number) obj.get("ProtectTime");
            pro.setProtectTime(ProtectTime.intValue());
        }
        if(obj.get("ProtectVisitTime")!=null){
        	 Number ProtectVisitTime = (Number) obj.get("ProtectVisitTime");
             pro.setProtectVisitTime(ProtectVisitTime.intValue());	
        }
        pro.setUserBehaviorID(obj.get("UserBehaviorID")==null|| "".equals(obj.get("UserBehaviorID")) ?0:Integer.parseInt(obj.get("UserBehaviorID").toString()));
        if(obj.get("IsPermanent")!=null){
        	Number IsPermanent = (Number) obj.get("IsPermanent");
            pro.setIsPermanent(IsPermanent.intValue());
        }
        RegisterRuleBaseModel UserRule = new RegisterRuleBaseModel();
        UserRule.setRuleID(obj.get("ID").toString());
        if(obj.get("CalMode")!=null){
        	Number CalMode = (Number) obj.get("CalMode");
            UserRule.setCalMode(CalMode.intValue());
        }
        if(obj.get("RuleType")!=null){
        	Number RuleType = (Number) obj.get("RuleType");
            UserRule.setRuleType(RuleType.intValue());
        }
        if(obj.get("ProtectSource")!=null){
        	Number ProtectSource = (Number) obj.get("ProtectSource");
            UserRule.setProtectSource(ProtectSource.intValue());
        }
        UserRule.setImmissionRule(imm);
        UserRule.setProtectRule(pro);
		return UserRule;
	}
	
	/**
	 * 现场确认验证
	 */
	private Map<String,Object> ValidateForConfirmation(String ClueID,ChannelRegisterModel channel) {
		Map<String,Object> msg = new HashMap<String,Object>();
		QueryWrapper<BClue> wrapper = new QueryWrapper<BClue>();
        wrapper.eq("ID", ClueID);
        wrapper.eq("IsDel", 0);
        BClue obj = iBClueService.getOne(wrapper);
        String phone = obj.getMobile();
        String projectId = obj.getIntentProjectID();
        //该线索已经被确认过
        if (obj.getStatus() == 2){
            msg.put("Tag", false);
            msg.put("InvalidType", -1);
            msg.put("Message", "该线索已被案场确认过！");
            return msg;
        }
        //判断待验证线索是否已经失效
        if (obj.getStatus() == 3){
        	msg.put("Tag", false);
            msg.put("InvalidType", obj.getInvalidType());
            msg.put("Message", obj.getInvalidReason());
            return msg;
        }
        //到访超过保护期
        if (IsOverdueCome(obj)){
        	msg.put("Tag", false);
            msg.put("InvalidType", 7);
            return msg;
        }
        //是否在防截客时间到访
        if (IsPreIntercept(obj.getCreateTime(),channel)){
        	msg.put("Tag", false);
            msg.put("InvalidType", 3);
            return msg;
        }
        msg = InstantConfirmation(phone, projectId,channel);
        return msg;
	}
	private String getChannelTypeID(String ClueID) {
        QueryWrapper<BClue> wrapper = new QueryWrapper<BClue>();
        wrapper.eq("id", ClueID);
        BClue result = iBClueService.getOne(wrapper);
        return result != null ?result.getAdviserGroupID():"";
	}
	
    /**
     * 验证报备客户是否有效
     */
	@Override
	public Map<String, Object> ValidateForReport(String mobile, String projectId,
			ChannelRegisterModel channelRegisterModel) {
		Map<String, Object> msg = new HashMap<String, Object>();
		//默认值false
		msg.put("IsExsitOpp",false);
        if (clueruleService.IsRepeatedReg(mobile, projectId, channelRegisterModel.getChannelUserId())){
            msg.put("Tag", false);
            msg.put("InvalidType", 9);
            return msg;
        }
        //竞争带看并且是现场确认模式
        if (channelRegisterModel.getUserRule().getRuleType() == 1 
        		&& channelRegisterModel.getUserRule().getImmissionRule().getValidationMode() == 1){
            //判断是否存在报备保护模式的有效线索，由于两种模式互斥，所以需要单独判断
            if (clueruleService.isExistReportProtectClue(mobile, projectId)){
            	msg.put("Tag", false);
                msg.put("InvalidType", 4);
                return msg;
            }else{
                //仅提示报备成功
            	msg.put("Tag", true);
                msg.put("InvalidType", 0);
                return msg;
            }
        }else{//实时确认
            return InstantConfirmation(mobile, projectId,channelRegisterModel);
        }
	}
	/**
	 * 实时确认
	 */
	private Map<String, Object> InstantConfirmation(String phone, String projectId, 
			ChannelRegisterModel channelRegisterModel) {
		Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("IsExsitOpp", false);
        if (channelRegisterModel.getUserRule().getImmissionRule().getIsOnlyAllowNew() == 1){//仅允许报备新客户
            //验证该项目是否已存在销售机会
            boolean flag = clueruleService.IsExistOpportunity(phone, projectId);
            if (flag){//存在销售机会
            	msg.put("Tag", false);
                msg.put("InvalidType", 2);
            }else{//不存在销售机会
                msg = validateClue(phone, projectId,channelRegisterModel);
            }
        } else{//允许报备满足条件的老客户（新客户+不在渠道保护期和案场保护期的老客户）
            //老业主限制，0.不允许报备老业主 1.仅允许报备其它项目老业主
            if (channelRegisterModel.getUserRule().getImmissionRule().getOldOwnerLimit() == 0){
                //验证是否集团老业主
                if (IsOwner(phone)){
                	msg.put("Tag", false);
                    msg.put("InvalidType", 1);
                    return msg;
                }
            }else{
                //验证是否是本项目老业主
            	List<Map<String, Object>> a1 = bCustomerMapper.IsProjectOwner_Select(projectId, phone);
                if (a1 == null || a1.size() == 0 ){
                	msg.put("Tag", false);
                    msg.put("InvalidType", 11);
                    return msg;
                }
            }
            msg = validateClue(phone, projectId, channelRegisterModel);
            if ((boolean)msg.get("Tag") == false)
                return msg;
            msg = validateOpp(phone, projectId);
        }
        return msg;
	}
	/**
	 * 验证是否满足不存在机会或机会没有置业顾问
	 */
	private Map<String, Object> validateOpp(String phone, String projectId) {
		Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("IsExsitOpp", false);
		Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("ProjectID", projectId);
        obj.put("CustomerMobile", phone);
		Map<String,Object> opp = bCustomerpotentialMapper.ValidOpp_Select(obj);
        //不存在销售机会
        if(opp==null||opp.size()==0){
        	msg.put("Tag", true);
            msg.put("InvalidType", 0);
        }else{//存在销售机会
            if(StringUtils.isEmpty(opp.get("SaleUserID").toString()))
            {
                msg.put("Tag", true);
                msg.put("InvalidType", 0);
                msg.put("IsExsitOpp", true);
                msg.put("OppID", opp.get("ID").toString());
            }else{
            	msg.put("Tag", false);
                msg.put("InvalidType", 6);
            }
        }
        return msg;
	}
	/**
	 * 判断客户是否是集团老业主
	 */
	private boolean IsOwner(String phone) {
		Map<String,Object> temp = new HashMap<String,Object>();
    	temp.put("Mobile", phone);
        Map<String,Object> map = bCustomerpotentialMapper.IsOwner_Select(temp);
        if(map == null || map.size() == 0){
        	return false;
        }else{
        	return true;
        }
	}
	/**
	 * 其他条件满足的情况下，验证报备的线索是否有效
	 */
	private Map<String, Object> validateClue(String phone, String projectId,ChannelRegisterModel channelRegisterModel) {
		Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("IsExsitOpp", false);
        //查询项目下存在该手机号的有效线索
        List<Map<String,Object>> clues = bClueMapper.RuleClueList_Select(phone, projectId,channelRegisterModel.getChannelUserId());
        //该项目已经存在已确认线索
        for(Map<String,Object> c : clues){
        	if((int)c.get("Status") == 2){
        		//报备保护
        		if (channelRegisterModel.getUserRule().getRuleType() == 0){
        			msg.put("InvalidType", 4);
        		}else{
        			msg.put("InvalidType", 5);
        		}
        		msg.put("Tag", false);
        		return msg;
        	}
        }
        if (clues.size() == 0){//该手机号为新客户（在允许报备老客户时，此段无意义，仅存在于报备新客户）
        	msg.put("Tag", true);
        	msg.put("InvalidType", 0);
            return msg;
        }
        if (clues.size() == 1){//该手机号线索已存在，此条件下需要进一步证明规则类型
            int ruleType = (int) clues.get(0).get("RuleType");
            if (channelRegisterModel.getUserRule().getRuleType() == ruleType && ruleType == 1){//线索为竞争带看，并且当前渠道用户规则也为竞争带看
            	msg.put("Tag", true);
            	msg.put("InvalidType", 0);
            }else{//线索为报备保护
            	msg.put("Tag", false);
            	msg.put("InvalidType", 4);
            }
        }else{//该手机号存在多条线索，说明报备模式为竞争带看
            int ruleType = (int) clues.get(0).get("RuleType");
            if (channelRegisterModel.getUserRule().getRuleType() == ruleType){//当前渠道的报备规则也为竞争带看
            	msg.put("Tag", true);
            	msg.put("InvalidType", 0);
            }else{//当前渠道的报备规则为报备保护，报备规则模式不同则互斥
            	msg.put("Tag", false);
            	msg.put("InvalidType", 4);
            }
        }
        return msg;
	}
	/**
	 * 错误提示信息，用于返回到页面展示
	 */
	@Override
	public String GetMessageForReturn(int invalidType, RegisterRuleBaseModel userRule) {
		String msg = "";
        switch (invalidType){
            case 0:
                msg = userRule.getRuleType() == 1 ? "报备成功，为避免其他渠道抢先带看，请尽快到案场进行带客确认！" : "报备成功，您可以在“我的客户”中查看客户的最新状态！";
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
	
	private boolean IsPreIntercept(Date createTime,ChannelRegisterModel channel) {
		if (channel.getUserRule().getProtectRule().getIsPreIntercept() == 1){
            //报备时间+防截客周期小于等于当前时间
            if ((createTime.getMinutes()+channel.getUserRule().getProtectRule().getPreInterceptTime()) <= DateTime.now().getMinutes()){
                return false;
            }else{
                return true;
            }
        }
        else//没有防截客机制
            return false;
	}
	private boolean IsOverdueCome(BClue clue) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("ClueID", clue.getId());
        Map<String,Object> data = iBClueService.IsOverdueCome_Select(obj);
        return data == null || Integer.parseInt(data.get("IsOverdueCome").toString()) == 0 ? false : true;
	}
	
}
