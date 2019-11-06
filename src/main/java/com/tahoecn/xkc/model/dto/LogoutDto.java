package com.tahoecn.xkc.model.dto;

import java.io.Serializable;

/**
 * Created by zhanghw on 2018/10/10.
 */
public class LogoutDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String UserID;
	private String UserName;
	private String JobID;
	private String DeviceCode;
	private String AppVersion;
	private String ProjectID;
	private String OrgID;
	private String ChannelTypeID;
	private String AppName;
	private String Model;
	private String Platform;
	private String JobCode;
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getJobID() {
		return JobID;
	}
	public void setJobID(String jobID) {
		JobID = jobID;
	}
	public String getDeviceCode() {
		return DeviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		DeviceCode = deviceCode;
	}
	public String getAppVersion() {
		return AppVersion;
	}
	public void setAppVersion(String appVersion) {
		AppVersion = appVersion;
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
	public String getChannelTypeID() {
		return ChannelTypeID;
	}
	public void setChannelTypeID(String channelTypeID) {
		ChannelTypeID = channelTypeID;
	}
	public String getAppName() {
		return AppName;
	}
	public void setAppName(String appName) {
		AppName = appName;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getPlatform() {
		return Platform;
	}
	public void setPlatform(String platform) {
		Platform = platform;
	}
	public String getJobCode() {
		return JobCode;
	}
	public void setJobCode(String jobCode) {
		JobCode = jobCode;
	}

}
