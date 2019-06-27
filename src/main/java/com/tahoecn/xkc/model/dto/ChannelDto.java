package com.tahoecn.xkc.model.dto;


import java.io.Serializable;
import java.util.Date;


public class ChannelDto implements Serializable {

    private String ProjectIDs;

    private String ID1516;

    private String ID;

    private String OrgCode;

    private String OrgName;

    private String OrgShortName;

    private String BizLicense;

    private String Status;

    private String StatuName;

    private String ProjectID;

    private String LeaderID;

    private String OrgLeaderName;

    private String OrgLeaderMobile;

    private String UserName;

    private String Password;

    private String OrgLeaderMobileSt;

    private String OrgLeaderStatu;

    private String OrgLeaderStatuName;

    private String OrgStatu;

    private String OrgStatuName;

    private String ChannelTypeID;

    private String ChannelType;


    private Date CreateTime;

    private String CreatorName;

    public String getProjectIDs() {
        return ProjectIDs;
    }

    public void setProjectIDs(String projectIDs) {
        ProjectIDs = projectIDs;
    }

    public String getID1516() {
        return ID1516;
    }

    public void setID1516(String ID1516) {
        this.ID1516 = ID1516;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
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

    public String getBizLicense() {
        return BizLicense;
    }

    public void setBizLicense(String bizLicense) {
        BizLicense = bizLicense;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatuName() {
        return StatuName;
    }

    public void setStatuName(String statuName) {
        StatuName = statuName;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getLeaderID() {
        return LeaderID;
    }

    public void setLeaderID(String leaderID) {
        LeaderID = leaderID;
    }

    public String getOrgLeaderName() {
        return OrgLeaderName;
    }

    public void setOrgLeaderName(String orgLeaderName) {
        OrgLeaderName = orgLeaderName;
    }

    public String getOrgLeaderMobile() {
        return OrgLeaderMobile;
    }

    public void setOrgLeaderMobile(String orgLeaderMobile) {
        OrgLeaderMobile = orgLeaderMobile;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOrgLeaderMobileSt() {
        return OrgLeaderMobileSt;
    }

    public void setOrgLeaderMobileSt(String orgLeaderMobileSt) {
        OrgLeaderMobileSt = orgLeaderMobileSt;
    }

    public String getOrgLeaderStatu() {
        return OrgLeaderStatu;
    }

    public void setOrgLeaderStatu(String orgLeaderStatu) {
        OrgLeaderStatu = orgLeaderStatu;
    }

    public String getOrgLeaderStatuName() {
        return OrgLeaderStatuName;
    }

    public void setOrgLeaderStatuName(String orgLeaderStatuName) {
        OrgLeaderStatuName = orgLeaderStatuName;
    }

    public String getOrgStatu() {
        return OrgStatu;
    }

    public void setOrgStatu(String orgStatu) {
        OrgStatu = orgStatu;
    }

    public String getOrgStatuName() {
        return OrgStatuName;
    }

    public void setOrgStatuName(String orgStatuName) {
        OrgStatuName = orgStatuName;
    }

    public String getChannelTypeID() {
        return ChannelTypeID;
    }

    public void setChannelTypeID(String channelTypeID) {
        ChannelTypeID = channelTypeID;
    }

    public String getChannelType() {
        return ChannelType;
    }

    public void setChannelType(String channelType) {
        ChannelType = channelType;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    @Override
    public String toString() {
        return "ChannelDto{" +
                "ProjectIDs='" + ProjectIDs + '\'' +
                ", ID1516='" + ID1516 + '\'' +
                ", ID='" + ID + '\'' +
                ", OrgCode='" + OrgCode + '\'' +
                ", OrgName='" + OrgName + '\'' +
                ", OrgShortName='" + OrgShortName + '\'' +
                ", BizLicense='" + BizLicense + '\'' +
                ", Status='" + Status + '\'' +
                ", StatuName='" + StatuName + '\'' +
                ", ProjectID='" + ProjectID + '\'' +
                ", LeaderID='" + LeaderID + '\'' +
                ", OrgLeaderName='" + OrgLeaderName + '\'' +
                ", OrgLeaderMobile='" + OrgLeaderMobile + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", OrgLeaderMobileSt='" + OrgLeaderMobileSt + '\'' +
                ", OrgLeaderStatu='" + OrgLeaderStatu + '\'' +
                ", OrgLeaderStatuName='" + OrgLeaderStatuName + '\'' +
                ", OrgStatu='" + OrgStatu + '\'' +
                ", OrgStatuName='" + OrgStatuName + '\'' +
                ", ChannelTypeID='" + ChannelTypeID + '\'' +
                ", ChannelType='" + ChannelType + '\'' +
                ", CreateTime=" + CreateTime +
                ", CreatorName='" + CreatorName + '\'' +
                '}';
    }
}
