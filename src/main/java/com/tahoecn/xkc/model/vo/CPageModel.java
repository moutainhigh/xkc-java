package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class CPageModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String UserID;

	private String ProjectID;
    
	private String OrgID;
    
	private String Mobile;

	private String UserName;

	private String Sort;

	private String Filter;

	private int PageIndex;

	private int PageSize;

	private String Type;
    
	private String KeyWord;
    
	private String ClueID;
    
	private String Ctype;

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

	public String getOrgID() {
		return OrgID;
	}

	public void setOrgID(String orgID) {
		OrgID = orgID;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getSort() {
		return Sort;
	}

	public void setSort(String sort) {
		Sort = sort;
	}

	public String getFilter() {
		return Filter;
	}

	public void setFilter(String filter) {
		Filter = filter;
	}

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getKeyWord() {
		return KeyWord;
	}

	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}

	public String getClueID() {
		return ClueID;
	}

	public void setClueID(String clueID) {
		ClueID = clueID;
	}

	public String getCtype() {
		return Ctype;
	}

	public void setCtype(String ctype) {
		Ctype = ctype;
	}

}
