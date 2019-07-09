package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.service.rule.IBClueruleService;

public class ChannelRegisterModel implements Serializable {
	@Autowired
	private BChanneluserMapper bChanneluserMapper;
	@Autowired
    private IBClueruleService clueruleService;
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
    public ChannelRegisterModel(String userId, String adviserGroupID, String projecId){
        this.ChannelUserId = userId;
        String channelOrgId = GetChannelOrgID(userId);
        //如果渠道是分销中介，则把渠道身份赋值成机构ID
        adviserGroupID = StringUtils.isEmpty(channelOrgId) ? adviserGroupID : channelOrgId;
        this.ChannelOrgId = channelOrgId;
        //获取当前渠道的准入规则
        Map<String,Object> UserRule1 = clueruleService.getRegisterRule(projecId,adviserGroupID);
        this.UserRule = JSON.parseObject(JSON.toJSONString(UserRule1), RegisterRuleBaseModel.class);
        //new RegisterRuleBaseModel(adviserGroupID, projecId, debug);
    }
    /// 获取渠道人员所属组织ID
    public String GetChannelOrgID(String userId){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("UserID", userId);
        List<Map<String,Object>> data = bChanneluserMapper.GetChannelOrgID_Select(obj);

        return (data != null && data.size() > 0) ? data.get(0).get("ChannelOrgID").toString() : "";
    }
}
