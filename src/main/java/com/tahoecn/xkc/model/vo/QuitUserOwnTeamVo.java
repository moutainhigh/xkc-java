package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class QuitUserOwnTeamVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ID;

    private String UserID;

    private String ProjectID;

    private String TeamID;

    private String TeamName;

    private String Name;

    private String TelPhone;

    private String SMobile;

    private String UserName;

    private String Status;

    private String Mobile;

    private String CustomerCount;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

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

    public String getTeamID() {
        return TeamID;
    }

    public void setTeamID(String teamID) {
        TeamID = teamID;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTelPhone() {
        return TelPhone;
    }

    public void setTelPhone(String telPhone) {
        TelPhone = telPhone;
    }

    public String getSMobile() {
        return SMobile;
    }

    public void setSMobile(String SMobile) {
        this.SMobile = SMobile;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCustomerCount() {
        return CustomerCount;
    }

    public void setCustomerCount(String customerCount) {
        CustomerCount = customerCount;
    }
}
