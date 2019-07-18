package com.tahoecn.xkc.model.dto;

import java.io.Serializable;
import java.util.List;

public class GWCustomerPageDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userID;
	public String projectID;
	public String jobCode;
	public String channelTypeID;
	public String channelTaskID;
	public String reportUserID;
	public String keyWord;
	public String sort;
	public List<FilterItem> filter;
	public int pageIndex;
	public int pageSize;
	public String type;
	private String orgID;
	private String appVersion;
	private String jobId;
	private String deviceCode;
	private String platform;
	private String appName;
	
	public String where;
	public String order;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getChannelTypeID() {
		return channelTypeID;
	}

	public void setChannelTypeID(String channelTypeID) {
		this.channelTypeID = channelTypeID;
	}

	public String getChannelTaskID() {
		return channelTaskID;
	}

	public void setChannelTaskID(String channelTaskID) {
		this.channelTaskID = channelTaskID;
	}

	public String getReportUserID() {
		return reportUserID;
	}

	public void setReportUserID(String reportUserID) {
		this.reportUserID = reportUserID;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<FilterItem> getFilter() {
		return filter;
	}

	public void setFilter(List<FilterItem> filter) {
		this.filter = filter;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public class FilterItem {
		public String TagID;
		public List<String> Child;

		public String getTagID() {
			return TagID;
		}

		public void setTagID(String TagID) {
			this.TagID = TagID;
		}

		public List<String> getChild() {
			return Child;
		}

		public void setChild(List<String> Child) {
			this.Child = Child;
		}

	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
