package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

import com.tahoecn.xkc.model.dto.GWCustomerPageDto.FilterItem;

public class GWCustomerPageModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String UserID;
	
	private String ProjectID;
    
	private String JobCode;

	private String ChannelTypeID;

	private String ChannelTaskID;

	private String ReportUserID;

	private String KeyWord;

	private String Sort;

	private List<FilterItem> Filter;

	private int PageIndex;

	private int PageSize;

    public String Type;

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

	public String getChannelTypeID() {
		return ChannelTypeID;
	}

	public void setChannelTypeID(String channelTypeID) {
		ChannelTypeID = channelTypeID;
	}

	public String getChannelTaskID() {
		return ChannelTaskID;
	}

	public void setChannelTaskID(String channelTaskID) {
		ChannelTaskID = channelTaskID;
	}

	public String getReportUserID() {
		return ReportUserID;
	}

	public void setReportUserID(String reportUserID) {
		ReportUserID = reportUserID;
	}

	public String getKeyWord() {
		return KeyWord;
	}

	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}

	public String getSort() {
		return Sort;
	}

	public void setSort(String sort) {
		Sort = sort;
	}

	public List<FilterItem> getFilter() {
		return Filter;
	}

	public void setFilter(List<FilterItem> filter) {
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
    
}
