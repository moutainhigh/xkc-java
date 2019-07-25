package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.tahoecn.core.convert.Convert;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.mapper.customer.BCustomerpotentialMapper;
import com.tahoecn.xkc.service.rule.IBClueruleService;

public class ChannelRegisterModel2 implements Serializable {
	@Autowired
	private BChanneluserMapper bChanneluserMapper;
	@Autowired
    private IBClueruleService clueruleService;
	@Autowired
	private BClueMapper bClueMapper;
	@Autowired
	private BCustomerpotentialMapper bCustomerpotentialMapper;
	@Autowired
	private BCustomerMapper bCustomerMapper;
	
	
	
	private static final long serialVersionUID = 1L;
    // 渠道用户ID
	private String ChannelUserId;
    // 渠道用户对应的报备规则
	private RegisterRuleBaseModel UserRule;
	private String ChannelOrgId;

    public String getChannelUserId() {
		return ChannelUserId;
	}
	public void setChannelUserId(String channelUserId) {
		ChannelUserId = channelUserId;
	}
	public RegisterRuleBaseModel getUserRule() {
		return UserRule;
	}
	public void setUserRule(RegisterRuleBaseModel userRule) {
		UserRule = userRule;
	}
	public String getChannelOrgId() {
		return ChannelOrgId;
	}
	public void setChannelOrgId(String channelOrgId) {
		ChannelOrgId = channelOrgId;
	}
    public ChannelRegisterModel2(String userId, String adviserGroupID, String projecId){
        this.ChannelUserId = userId;
        String channelOrgId = GetChannelOrgID(userId);
        //如果渠道是分销中介，则把渠道身份赋值成机构ID
        adviserGroupID = StringUtils.isEmpty(channelOrgId) ? adviserGroupID : channelOrgId;
        this.ChannelOrgId = channelOrgId;
        //获取当前渠道的准入规则
        Map<String,Object> UserRule1 = clueruleService.getRegisterRule(projecId,adviserGroupID);
        this.UserRule = JSON.parseObject(JSON.toJSONString(UserRule1), RegisterRuleBaseModel.class);
//        this.UserRule = new RegisterRuleBaseModel(adviserGroupID, projecId);
    }
    /**
     * 获取渠道人员所属组织ID
     */
    public String GetChannelOrgID(String userId){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("UserID", userId);
        List<Map<String,Object>> data = bChanneluserMapper.GetChannelOrgID_Select(obj);
        return (data != null && data.size() > 0) ? data.get(0).get("ChannelOrgID").toString() : "";
    }
    /**
     * 验证报备客户是否有效
     */
	public Map<String, Object> ValidateForReport(String mobile, String projectId) {
		Map<String, Object> msg = new HashMap<String, Object>();
        if (clueruleService.IsRepeatedReg(mobile, projectId, this.ChannelUserId)){
            msg.put("Tag", false);
            msg.put("InvalidType", 9);
            return msg;
        }
        //竞争带看并且是现场确认模式
        if (this.UserRule.getRuleType() == 1 && this.UserRule.getImmissionRule().getValidationMode() == 1){
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
            return InstantConfirmation(mobile, projectId);
        }
	}
	/**
	 * 实时确认
	 */
	private Map<String, Object> InstantConfirmation(String phone, String projectId) {
		Map<String, Object> msg = new HashMap<String, Object>();
        if (this.UserRule.getImmissionRule().getIsOnlyAllowNew() == 1){//仅允许报备新客户
            //验证该项目是否已存在销售机会
            boolean flag = clueruleService.IsExistOpportunity(phone, projectId);
            if (flag){//存在销售机会
            	msg.put("Tag", false);
                msg.put("InvalidType", 2);
            }else{//不存在销售机会
                msg = validateClue(phone, projectId);
            }
        } else{//允许报备满足条件的老客户（新客户+不在渠道保护期和案场保护期的老客户）
            //老业主限制，0.不允许报备老业主 1.仅允许报备其它项目老业主
            if (this.UserRule.getImmissionRule().getOldOwnerLimit() == 0){
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
            msg = validateClue(phone, projectId);
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
	private Map<String, Object> validateClue(String phone, String projectId) {
		Map<String, Object> msg = new HashMap<String, Object>();
        //查询项目下存在该手机号的有效线索
        List<Map<String,Object>> clues = bClueMapper.RuleClueList_Select(phone, projectId,this.ChannelUserId);
        //该项目已经存在已确认线索
        for(Map<String,Object> c : clues){
        	if((int)c.get("Status") == 2){
        		//报备保护
        		if (this.UserRule.getRuleType() == 0){
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
            if (this.UserRule.getRuleType() == ruleType && ruleType == 1){//线索为竞争带看，并且当前渠道用户规则也为竞争带看
            	msg.put("Tag", true);
            	msg.put("InvalidType", 0);
            }else{//线索为报备保护
            	msg.put("Tag", false);
            	msg.put("InvalidType", 4);
            }
        }else{//该手机号存在多条线索，说明报备模式为竞争带看
            int ruleType = (int) clues.get(0).get("RuleType");
            if (this.UserRule.getRuleType() == ruleType){//当前渠道的报备规则也为竞争带看
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
}
