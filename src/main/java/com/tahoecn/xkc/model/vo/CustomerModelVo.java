package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerModelVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ClueID;
	
	private String OpportunityID;

	private String CustomerID;

	private String OrgID;
	
	private String SpareMobile;
	
	private String UseMobile;
	
	private String CustomerMobile;
	
	private int IsNew;

	private List<PanelItem> Panel;
    
	@JsonProperty("ClueID")
    public String getClueID() {
		return ClueID;
	}

	public void setClueID(String clueID) {
		ClueID = clueID;
	}

	@JsonProperty("OpportunityID")
	public String getOpportunityID() {
		return OpportunityID;
	}

	public void setOpportunityID(String opportunityID) {
		OpportunityID = opportunityID;
	}

	@JsonProperty("CustomerID")
	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	@JsonProperty("OrgID")
	public String getOrgID() {
		return OrgID;
	}

	public void setOrgID(String orgID) {
		OrgID = orgID;
	}

	@JsonProperty("IsNew")
	public int getIsNew() {
		return IsNew;
	}

	public void setIsNew(int isNew) {
		IsNew = isNew;
	}

	@JsonProperty("Panel")
	public List<PanelItem> getPanel() {
		return Panel;
	}

	public void setPanel(List<PanelItem> panel) {
		Panel = panel;
	}

	public String getSpareMobile() {
		return SpareMobile;
	}

	public void setSpareMobile(String spareMobile) {
		SpareMobile = spareMobile;
	}

	public String getUseMobile() {
		return UseMobile;
	}

	public void setUseMobile(String useMobile) {
		UseMobile = useMobile;
	}

	public String getCustomerMobile() {
		return CustomerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		CustomerMobile = customerMobile;
	}

}
