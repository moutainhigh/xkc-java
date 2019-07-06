package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class CustomerModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String ClueID;
    public String OpportunityID;
    public String CustomerID;
    public String OrgID;
    public int IsNew;
    public List<PanelItem> Panel;
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
	public int getIsNew() {
		return IsNew;
	}
	public void setIsNew(int isNew) {
		IsNew = isNew;
	}
	public List<PanelItem> getPanel() {
		return Panel;
	}
	public void setPanel(List<PanelItem> panel) {
		Panel = panel;
	}
    
}
