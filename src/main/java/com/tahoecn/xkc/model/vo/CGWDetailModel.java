package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class CGWDetailModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String UserID;
	
	private String UserTrueName;

	private String ProjectID;

	private String ClueID;

	private String OrgID;
	
	private String JobID;
    
	private String OpportunityID;

	private String CustomerID;
    /// 是否发送通知
	private String IsSend;
    /// 是否首次访问 
	private String IsCustomerFirstEdit;

	private List<Item> ItemList;
    /// 渠道身份ID
	private String ChannelTypeID;
	
	private String JobCode;
	
    private String ChannelTaskID;
    
    /// 来访地址
    private String VisitAddress;
    
    private String UseMobile;
    
    
    public String getUseMobile() {
		return UseMobile;
	}


	public void setUseMobile(String useMobile) {
		UseMobile = useMobile;
	}


	public String getUserID() {
		return UserID;
	}


	public void setUserID(String userID) {
		UserID = userID;
	}


	public String getUserTrueName() {
		return UserTrueName;
	}


	public void setUserTrueName(String userTrueName) {
		UserTrueName = userTrueName;
	}


	public String getProjectID() {
		return ProjectID;
	}


	public void setProjectID(String projectID) {
		ProjectID = projectID;
	}


	public String getClueID() {
		return ClueID;
	}


	public void setClueID(String clueID) {
		ClueID = clueID;
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


	public String getIsSend() {
		return IsSend;
	}


	public void setIsSend(String isSend) {
		IsSend = isSend;
	}


	public String getIsCustomerFirstEdit() {
		return IsCustomerFirstEdit;
	}


	public void setIsCustomerFirstEdit(String isCustomerFirstEdit) {
		IsCustomerFirstEdit = isCustomerFirstEdit;
	}


	public List<Item> getItemList() {
		return ItemList;
	}


	public void setItemList(List<Item> itemList) {
		ItemList = itemList;
	}


	public String getChannelTypeID() {
		return ChannelTypeID;
	}


	public void setChannelTypeID(String channelTypeID) {
		ChannelTypeID = channelTypeID;
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


	public String getVisitAddress() {
		return VisitAddress;
	}


	public void setVisitAddress(String visitAddress) {
		VisitAddress = visitAddress;
	}


	public static class Item{
        
    	private String ID;
        /// 性别
    	private String Name;

    	private String Value;

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getValue() {
			return Value;
		}

		public void setValue(String value) {
			Value = value;
		}
    	

    }

}
