package com.tahoecn.xkc.model.dto;

import java.io.Serializable;

public class ChannelInsertDto implements Serializable {
    /**
     * ProjectID":"cd69e423-63b5-e711-80c700505686c900",
     * "OrgID":"",
     * "OrgName":"qqq",
     * "OrgShortName":"",
     * "Bizlicense":"/Uploads/image/20190627/9486178f28b0352fae139bafc18d370c.png",
     * "LeaderID":"",
     * "UserName":"qqq",
     * "Name":"qqq",
     * "Mobile":"11111111222",
     * "Status":"1",
     * "ProjectIDs":"CD69E423-63B5-E711-80C7-00505686C900",
     * "RuleIDs":["CD69E423-63B5-E711-80C7-00505686C900,"],
     * "UserID":"6C883173-489C-47A4-97D2-3601CB7CEDFD"}
     */
    private String projectID;
    private String orgID;
    private String orgName;
    private String orgShortName;
    private String bizlicense;
    private String leaderID;
    private String userName;
    private String name;
    private String mobile;
    private String status;
    private String projectIDs;
    private String ruleIDs;
    private String userID;
    private String orgCode;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getBizlicense() {
        return bizlicense;
    }

    public void setBizlicense(String bizlicense) {
        this.bizlicense = bizlicense;
    }

    public String getLeaderID() {
        return leaderID;
    }

    public void setLeaderID(String leaderID) {
        this.leaderID = leaderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectIDs() {
        return projectIDs;
    }

    public void setProjectIDs(String projectIDs) {
        this.projectIDs = projectIDs;
    }

    public String getRuleIDs() {
        return ruleIDs;
    }

    public void setRuleIDs(String ruleIDs) {
        this.ruleIDs = ruleIDs;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
