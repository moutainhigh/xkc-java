package com.tahoecn.xkc.model.customer;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 1
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@TableName("V_CustomerGWList_Select")
@ApiModel(value="VCustomergwlistSelect对象", description="1")
public class VCustomergwlistSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("IsCare")
    private Integer IsCare;

    @TableField("TheLatestFollowUpDate")
    private String TheLatestFollowUpDate;

    @TableField("TIME")
    private String time;

    @TableField("IsEquity")
    private Integer IsEquity;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("CustomerFirstEditTime")
    private Date CustomerFirstEditTime;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("StatusSort")
    private Integer StatusSort;

    @TableField("ID")
    private String id;

    @TableField("TheLastFollowUpDate")
    private Date TheLastFollowUpDate;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("IsSubscription")
    private Integer IsSubscription;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("OpportunityStatus")
    private String OpportunityStatus;

    @TableField("TrackType")
    private String TrackType;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("OpportunityStatusValue")
    private Integer OpportunityStatusValue;

    @TableField("FollwUpWay")
    private String FollwUpWay;

    @TableField("FollowUpDate")
    private Date FollowUpDate;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("CustomerLevel")
    private String CustomerLevel;

    @TableField("AllotUserID")
    private String AllotUserID;

    @TableField("SubDate")
    private Date SubDate;

    @TableField("AllotTime")
    private Date AllotTime;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("IsCustomerFirstEdit")
    private Integer IsCustomerFirstEdit;

    @TableField("IntentionLevel")
    private String IntentionLevel;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("IsLose")
    private String IsLose;

    @TableField("TheLatestFollowUpContent")
    private String TheLatestFollowUpContent;

    @TableField("CustomerTag")
    private String CustomerTag;

    public Integer getIsCare() {
        return IsCare;
    }

    public void setIsCare(Integer IsCare) {
        this.IsCare = IsCare;
    }
    public String getTheLatestFollowUpDate() {
        return TheLatestFollowUpDate;
    }

    public void setTheLatestFollowUpDate(String TheLatestFollowUpDate) {
        this.TheLatestFollowUpDate = TheLatestFollowUpDate;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public Integer getIsEquity() {
        return IsEquity;
    }

    public void setIsEquity(Integer IsEquity) {
        this.IsEquity = IsEquity;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public Date getCustomerFirstEditTime() {
        return CustomerFirstEditTime;
    }

    public void setCustomerFirstEditTime(Date CustomerFirstEditTime) {
        this.CustomerFirstEditTime = CustomerFirstEditTime;
    }
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
    }
    public Integer getStatusSort() {
        return StatusSort;
    }

    public void setStatusSort(Integer StatusSort) {
        this.StatusSort = StatusSort;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getTheLastFollowUpDate() {
        return TheLastFollowUpDate;
    }

    public void setTheLastFollowUpDate(Date TheLastFollowUpDate) {
        this.TheLastFollowUpDate = TheLastFollowUpDate;
    }
    public String getSaleUserID() {
        return SaleUserID;
    }

    public void setSaleUserID(String SaleUserID) {
        this.SaleUserID = SaleUserID;
    }
    public Integer getIsSubscription() {
        return IsSubscription;
    }

    public void setIsSubscription(Integer IsSubscription) {
        this.IsSubscription = IsSubscription;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getOpportunityStatus() {
        return OpportunityStatus;
    }

    public void setOpportunityStatus(String OpportunityStatus) {
        this.OpportunityStatus = OpportunityStatus;
    }
    public String getTrackType() {
        return TrackType;
    }

    public void setTrackType(String TrackType) {
        this.TrackType = TrackType;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public Integer getOpportunityStatusValue() {
        return OpportunityStatusValue;
    }

    public void setOpportunityStatusValue(Integer OpportunityStatusValue) {
        this.OpportunityStatusValue = OpportunityStatusValue;
    }
    public String getFollwUpWay() {
        return FollwUpWay;
    }

    public void setFollwUpWay(String FollwUpWay) {
        this.FollwUpWay = FollwUpWay;
    }
    public Date getFollowUpDate() {
        return FollowUpDate;
    }

    public void setFollowUpDate(Date FollowUpDate) {
        this.FollowUpDate = FollowUpDate;
    }
    public String getSaleUserName() {
        return SaleUserName;
    }

    public void setSaleUserName(String SaleUserName) {
        this.SaleUserName = SaleUserName;
    }
    public String getCustomerLevel() {
        return CustomerLevel;
    }

    public void setCustomerLevel(String CustomerLevel) {
        this.CustomerLevel = CustomerLevel;
    }
    public String getAllotUserID() {
        return AllotUserID;
    }

    public void setAllotUserID(String AllotUserID) {
        this.AllotUserID = AllotUserID;
    }
    public Date getSubDate() {
        return SubDate;
    }

    public void setSubDate(Date SubDate) {
        this.SubDate = SubDate;
    }
    public Date getAllotTime() {
        return AllotTime;
    }

    public void setAllotTime(Date AllotTime) {
        this.AllotTime = AllotTime;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public Integer getIsCustomerFirstEdit() {
        return IsCustomerFirstEdit;
    }

    public void setIsCustomerFirstEdit(Integer IsCustomerFirstEdit) {
        this.IsCustomerFirstEdit = IsCustomerFirstEdit;
    }
    public String getIntentionLevel() {
        return IntentionLevel;
    }

    public void setIntentionLevel(String IntentionLevel) {
        this.IntentionLevel = IntentionLevel;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getIsLose() {
        return IsLose;
    }

    public void setIsLose(String IsLose) {
        this.IsLose = IsLose;
    }
    public String getTheLatestFollowUpContent() {
        return TheLatestFollowUpContent;
    }

    public void setTheLatestFollowUpContent(String TheLatestFollowUpContent) {
        this.TheLatestFollowUpContent = TheLatestFollowUpContent;
    }
    public String getCustomerTag() {
        return CustomerTag;
    }

    public void setCustomerTag(String CustomerTag) {
        this.CustomerTag = CustomerTag;
    }

    @Override
    public String toString() {
        return "VCustomergwlistSelect{" +
        "IsCare=" + IsCare +
        ", TheLatestFollowUpDate=" + TheLatestFollowUpDate +
        ", time=" + time +
        ", IsEquity=" + IsEquity +
        ", CustomerMobile=" + CustomerMobile +
        ", CustomerFirstEditTime=" + CustomerFirstEditTime +
        ", OpportunityID=" + OpportunityID +
        ", StatusSort=" + StatusSort +
        ", id=" + id +
        ", TheLastFollowUpDate=" + TheLastFollowUpDate +
        ", SaleUserID=" + SaleUserID +
        ", IsSubscription=" + IsSubscription +
        ", CreateTime=" + CreateTime +
        ", OpportunityStatus=" + OpportunityStatus +
        ", TrackType=" + TrackType +
        ", CustomerID=" + CustomerID +
        ", OpportunityStatusValue=" + OpportunityStatusValue +
        ", FollwUpWay=" + FollwUpWay +
        ", FollowUpDate=" + FollowUpDate +
        ", SaleUserName=" + SaleUserName +
        ", CustomerLevel=" + CustomerLevel +
        ", AllotUserID=" + AllotUserID +
        ", SubDate=" + SubDate +
        ", AllotTime=" + AllotTime +
        ", CustomerName=" + CustomerName +
        ", IsCustomerFirstEdit=" + IsCustomerFirstEdit +
        ", IntentionLevel=" + IntentionLevel +
        ", ProjectID=" + ProjectID +
        ", IsLose=" + IsLose +
        ", TheLatestFollowUpContent=" + TheLatestFollowUpContent +
        ", CustomerTag=" + CustomerTag +
        "}";
    }
}
