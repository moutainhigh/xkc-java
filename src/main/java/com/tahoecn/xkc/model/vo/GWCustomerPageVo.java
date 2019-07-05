package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class GWCustomerPageVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String OpportunityID;
	private String CustomerID;
	private String CustomerName;
	private String CustomerMobile; 
	private String FollwUpWay;
	private String CustomerTag;
	private String TheLatestFollowUpDate;
	private String TheLatestFollowUpContent;
	private String IntentionLevel;
	private String CustomerRank;
	private String IsCare;
	private String OpportunityStatus;
	private String OpportunityStatusValue; 
	private String IsCustomerFirstEdit;
	private String SaleUserName;
	private String IsCooperat;
	private Long recordCount;

	public String getOpportunityID() {
		return OpportunityID;
	}
	public void setOpportunityID(String opportunityID) {
		OpportunityID = opportunityID;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getCustomerMobile() {
		return CustomerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		CustomerMobile = customerMobile;
	}
	public String getFollwUpWay() {
		return FollwUpWay;
	}
	public void setFollwUpWay(String follwUpWay) {
		FollwUpWay = follwUpWay;
	}
	public String getCustomerTag() {
		return CustomerTag;
	}
	public void setCustomerTag(String customerTag) {
		CustomerTag = customerTag;
	}
	public String getTheLatestFollowUpDate() {
		return TheLatestFollowUpDate;
	}
	public void setTheLatestFollowUpDate(String theLatestFollowUpDate) {
		TheLatestFollowUpDate = theLatestFollowUpDate;
	}
	public String getTheLatestFollowUpContent() {
		return TheLatestFollowUpContent;
	}
	public void setTheLatestFollowUpContent(String theLatestFollowUpContent) {
		TheLatestFollowUpContent = theLatestFollowUpContent;
	}
	public String getIntentionLevel() {
		return IntentionLevel;
	}
	public void setIntentionLevel(String intentionLevel) {
		IntentionLevel = intentionLevel;
	}
	public String getIsCare() {
		return IsCare;
	}
	public void setIsCare(String isCare) {
		IsCare = isCare;
	}
	public String getOpportunityStatus() {
		return OpportunityStatus;
	}
	public void setOpportunityStatus(String opportunityStatus) {
		OpportunityStatus = opportunityStatus;
	}
	public String getOpportunityStatusValue() {
		return OpportunityStatusValue;
	}
	public void setOpportunityStatusValue(String opportunityStatusValue) {
		OpportunityStatusValue = opportunityStatusValue;
	}
	public String getIsCustomerFirstEdit() {
		return IsCustomerFirstEdit;
	}
	public void setIsCustomerFirstEdit(String isCustomerFirstEdit) {
		IsCustomerFirstEdit = isCustomerFirstEdit;
	}
	public String getSaleUserName() {
		return SaleUserName;
	}
	public void setSaleUserName(String saleUserName) {
		SaleUserName = saleUserName;
	}
	public String getCustomerRank() {
		return CustomerRank;
	}
	public void setCustomerRank(String customerRank) {
		CustomerRank = customerRank;
	}
	public String getIsCooperat() {
		return IsCooperat;
	}
	public void setIsCooperat(String isCooperat) {
		IsCooperat = isCooperat;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	
}
