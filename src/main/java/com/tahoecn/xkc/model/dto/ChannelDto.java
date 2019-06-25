package com.tahoecn.xkc.model.dto;


import java.io.Serializable;
import java.util.Date;


public class ChannelDto implements Serializable {

    private String id;

    private String OrgCode;

    private String OrgName;

    private String OrgShortName;

    private String BizLicense;

    private String StatuName;

    private String OrgLeaderName;

    private String OrgLeaderMobile;

    private String UserName;

    private String OrgLeaderStatuName;

    private Date CreateTime;

    private String CreatorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatuName() {
        return StatuName;
    }

    public void setStatuName(String statuName) {
        StatuName = statuName;
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

    public String getOrgLeaderStatuName() {
        return OrgLeaderStatuName;
    }

    public void setOrgLeaderStatuName(String orgLeaderStatuName) {
        OrgLeaderStatuName = orgLeaderStatuName;
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
                "id='" + id + '\'' +
                ", OrgCode='" + OrgCode + '\'' +
                ", OrgName='" + OrgName + '\'' +
                ", OrgShortName='" + OrgShortName + '\'' +
                ", BizLicense='" + BizLicense + '\'' +
                ", StatuName='" + StatuName + '\'' +
                ", OrgLeaderName='" + OrgLeaderName + '\'' +
                ", OrgLeaderMobile='" + OrgLeaderMobile + '\'' +
                ", UserName='" + UserName + '\'' +
                ", OrgLeaderStatuName='" + OrgLeaderStatuName + '\'' +
                ", CreateTime=" + CreateTime +
                ", CreatorName='" + CreatorName + '\'' +
                '}';
    }
}
