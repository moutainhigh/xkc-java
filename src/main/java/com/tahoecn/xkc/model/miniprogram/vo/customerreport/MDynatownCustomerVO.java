package com.tahoecn.xkc.model.miniprogram.vo.customerreport;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  思为小程序置业顾问客户报备参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-03
 */
public class MDynatownCustomerVO {

/*    private String deviceCode;

    private String platform;

    private String appName;

    private String model;

    private String appVersion;*/

    private String userId;

    private String userTrueName;

    private String projectId;

    private String orgId;

    private String jobCode;

    private String jobId;

    private String clueId;

    private String useMobile;

    private String opportunityId;

    private String customerId;

    private List<Map<String,String>> itemList;

    private String formSessionId;

    private String roomId;

   /* public String getDeviceCode() {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }*/

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId;
    }

    public String getUseMobile() {
        return useMobile;
    }

    public void setUseMobile(String useMobile) {
        this.useMobile = useMobile;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Map<String, String>> getItemList() {
        return itemList;
    }

    public void setItemList(List<Map<String, String>> itemList) {
        this.itemList = itemList;
    }

    public String getFormSessionId() {
        return formSessionId;
    }

    public void setFormSessionId(String formSessionId) {
        this.formSessionId = formSessionId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
