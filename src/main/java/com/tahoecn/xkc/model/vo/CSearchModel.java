package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
/**
 * 案场分接客户
 * @author WH
 *
 */
public class CSearchModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String UserID;
    private String ProjectID;
    private String JobCode;
    private String ChannelTaskID;
    private String ClueID;// 线索ID
    private String OpportunityID;// 机会ID
    private String LostID;// 丢失ID
    private String CustomerID;// 客户ID
    private String OrgID;// 组织
    private String JobID;// 角色
    private String Mobile;// 手机号
    private String IsEquity;// 是否为关联权益人
    private String IsCustomerFirstEdit;// 是否首次访问
    private String CustomerPotentialID;// 潜在客户ID
    private String ChannelTypeID;// 渠道身份ID
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getProjectID() {
		return ProjectID;
	}
	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}
	public String getJobCode() {
		return JobCode;
	}
	public void setJobCode(String jobCode) {
		JobCode = jobCode;
	}
	public String getChannelTaskID() {
		return ChannelTaskID;
	}
	public void setChannelTaskID(String channelTaskID) {
		ChannelTaskID = channelTaskID;
	}
	public String getClueID() {
		return ClueID;
	}
	public void setClueID(String clueID) {
		ClueID = clueID;
	}
	public String getOpportunityID() {
		return OpportunityID;
	}
	public void setOpportunityID(String opportunityID) {
		OpportunityID = opportunityID;
	}
	public String getLostID() {
		return LostID;
	}
	public void setLostID(String lostID) {
		LostID = lostID;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getOrgID() {
		return OrgID;
	}
	public void setOrgID(String orgID) {
		OrgID = orgID;
	}
	public String getJobID() {
		return JobID;
	}
	public void setJobID(String jobID) {
		JobID = jobID;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getIsEquity() {
		return IsEquity;
	}
	public void setIsEquity(String isEquity) {
		IsEquity = isEquity;
	}
	public String getIsCustomerFirstEdit() {
		return IsCustomerFirstEdit;
	}
	public void setIsCustomerFirstEdit(String isCustomerFirstEdit) {
		IsCustomerFirstEdit = isCustomerFirstEdit;
	}
	public String getCustomerPotentialID() {
		return CustomerPotentialID;
	}
	public void setCustomerPotentialID(String customerPotentialID) {
		CustomerPotentialID = customerPotentialID;
	}
	public String getChannelTypeID() {
		return ChannelTypeID;
	}
	public void setChannelTypeID(String channelTypeID) {
		ChannelTypeID = channelTypeID;
	}
    
}
