package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class CustomerActionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String follwUpTypeID;
    public String follwUpType;
    // 跟进方式ID
    public String follwUpWay;
    // 跟进内容
    public String followUpContent;
    // 跟进人
    public String follwUpUserID;
    // 下次跟进格时间
    public String nextFollowUpDate;
    // 跟进人角色ID
    public String follwUpUserRole;
    // 跟进人所属组织
    public String orgID;
    // 意向级别
    public String intentionLevel;
    // 机会ID
    public String opportunityID;
    // 线索ID
    public String clueID;
    // 原顾问姓名
    public String oldSaleUserName;
    // 新顾问姓名
    public String newSaleUserName;
    // 销售人员身份,1为自销，2为代理，3为自渠,4为外渠
    public int salesType;
    // 创建人
    public String creator;
    // 编辑人
    public String editor;
	public String getFollwUpTypeID() {
		return follwUpTypeID;
	}
	public void setFollwUpTypeID(String follwUpTypeID) {
		this.follwUpTypeID = follwUpTypeID;
	}
	public String getFollwUpType() {
		return follwUpType;
	}
	public void setFollwUpType(String follwUpType) {
		this.follwUpType = follwUpType;
	}
	public String getFollwUpWay() {
		return follwUpWay;
	}
	public void setFollwUpWay(String follwUpWay) {
		this.follwUpWay = follwUpWay;
	}
	public String getFollowUpContent() {
		return followUpContent;
	}
	public void setFollowUpContent(String followUpContent) {
		this.followUpContent = followUpContent;
	}
	public String getFollwUpUserID() {
		return follwUpUserID;
	}
	public void setFollwUpUserID(String follwUpUserID) {
		this.follwUpUserID = follwUpUserID;
	}
	public String getNextFollowUpDate() {
		return nextFollowUpDate;
	}
	public void setNextFollowUpDate(String nextFollowUpDate) {
		this.nextFollowUpDate = nextFollowUpDate;
	}
	public String getFollwUpUserRole() {
		return follwUpUserRole;
	}
	public void setFollwUpUserRole(String follwUpUserRole) {
		this.follwUpUserRole = follwUpUserRole;
	}
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getIntentionLevel() {
		return intentionLevel;
	}
	public void setIntentionLevel(String intentionLevel) {
		this.intentionLevel = intentionLevel;
	}
	public String getOpportunityID() {
		return opportunityID;
	}
	public void setOpportunityID(String opportunityID) {
		this.opportunityID = opportunityID;
	}
	public String getClueID() {
		return clueID;
	}
	public void setClueID(String clueID) {
		this.clueID = clueID;
	}
	public String getOldSaleUserName() {
		return oldSaleUserName;
	}
	public void setOldSaleUserName(String oldSaleUserName) {
		this.oldSaleUserName = oldSaleUserName;
	}
	public String getNewSaleUserName() {
		return newSaleUserName;
	}
	public void setNewSaleUserName(String newSaleUserName) {
		this.newSaleUserName = newSaleUserName;
	}
	public int getSalesType() {
		return salesType;
	}
	public void setSalesType(int salesType) {
		this.salesType = salesType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
    
	

}
