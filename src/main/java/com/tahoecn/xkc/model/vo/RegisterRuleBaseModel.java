package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import cn.hutool.core.date.DateUtil;

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
	
	public String ComeOverdueTime;
	public String TradeOverdueTime;

	public String getComeOverdueTime() {
        if (this.ProtectRule.getIsSelect() == 0 || this.ProtectRule == null || this.ProtectRule.getProtectVisitTime() == 0 || this.ProtectRule.getIsPermanent() == 1)
            return "";
        //如果是按天计算，则在当前日期增加天数作为到访逾期时间，否则在当前时间增加天数作为到访逾期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, this.ProtectRule.getProtectVisitTime()+1);
        Date time = calendar.getTime();
        return DateUtil.format(time, "yyyy/MM/dd");
	}
	public void setComeOverdueTime(String comeOverdueTime) {
		ComeOverdueTime = comeOverdueTime;
	}
	public String getTradeOverdueTime() {
		if (this.ProtectRule == null || this.ProtectRule.getIsSelect() == 0 || this.ProtectRule.getProtectTime() == 0 || this.ProtectRule.getIsPermanent() == 1)
            return "";
        //如果是按天计算，则在当前日期增加天数作为成交逾期时间，否则在当前时间增加天数作为成交逾期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, this.ProtectRule.getProtectTime()+1);
        Date time = calendar.getTime();
        return DateUtil.format(time, "yyyy/MM/dd");
	}
	public void setTradeOverdueTime(String tradeOverdueTime) {
		TradeOverdueTime = tradeOverdueTime;
	}
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
