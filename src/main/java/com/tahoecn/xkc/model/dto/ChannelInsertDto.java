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
    private String ProjectID;
    private String OrgID;
    private String OrgName;
    private String OrgShortName;
    private String Bizlicense;
    private String LeaderID;
    private String UserName;
    private String Name;
    private String Mobile;
    private String Status;
    private String ProjectIDs;
    private String RuleIDs;
    private String UserID;
    private String OrgCode;

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

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getOrgShortName() {
        return OrgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        OrgShortName = orgShortName;
    }

    public String getBizlicense() {
        return Bizlicense;
    }

    public void setBizlicense(String bizlicense) {
        Bizlicense = bizlicense;
    }

    public String getLeaderID() {
        return LeaderID;
    }

    public void setLeaderID(String leaderID) {
        LeaderID = leaderID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getProjectIDs() {
        return ProjectIDs;
    }

    public void setProjectIDs(String projectIDs) {
        ProjectIDs = projectIDs;
    }

    public String getRuleIDs() {
        return RuleIDs;
    }

    public void setRuleIDs(String ruleIDs) {
        RuleIDs = ruleIDs;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }

    @Override
    public String toString() {
        return "ChannelInsertDto{" +
                "ProjectID='" + ProjectID + '\'' +
                ", OrgID='" + OrgID + '\'' +
                ", OrgName='" + OrgName + '\'' +
                ", OrgShortName='" + OrgShortName + '\'' +
                ", Bizlicense='" + Bizlicense + '\'' +
                ", LeaderID='" + LeaderID + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Name='" + Name + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Status='" + Status + '\'' +
                ", ProjectIDs='" + ProjectIDs + '\'' +
                ", RuleIDs='" + RuleIDs + '\'' +
                ", UserID='" + UserID + '\'' +
                ", OrgCode='" + OrgCode + '\'' +
                '}';
    }
}
