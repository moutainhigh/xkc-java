package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class RegisterRuleBaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /// 规则ID
	private String RuleID;
    /// 0按天计算，1按24小时计算
	private int CalMode;
    /// 规则类型，0为报备保护规则，1为竞争带看规则.
	private int RuleType;
    /// 渠道类型，0表示推荐，1表示自渠，2表示分销中介
	private int ProtectSource;
    /// 准入规则
	private ImmissionRule ImmissionRule;
    /// 保护期规则
	private ProtectRule ProtectRule;
	public String getRuleID() {
		return RuleID;
	}
	public void setRuleID(String ruleID) {
		RuleID = ruleID;
	}
	public int getCalMode() {
		return CalMode;
	}
	public void setCalMode(int calMode) {
		CalMode = calMode;
	}
	public int getRuleType() {
		return RuleType;
	}
	public void setRuleType(int ruleType) {
		RuleType = ruleType;
	}
	public int getProtectSource() {
		return ProtectSource;
	}
	public void setProtectSource(int protectSource) {
		ProtectSource = protectSource;
	}
	public ImmissionRule getImmissionRule() {
		return ImmissionRule;
	}
	public void setImmissionRule(ImmissionRule immissionRule) {
		ImmissionRule = immissionRule;
	}
	public ProtectRule getProtectRule() {
		return ProtectRule;
	}
	public void setProtectRule(ProtectRule protectRule) {
		ProtectRule = protectRule;
	}
}
