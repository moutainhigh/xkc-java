package com.tahoecn.xkc.model.customer;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */
@ApiModel(value="CostomerReport对象", description="")
public class CostomerReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long num;

    @TableField("OpportunityID")
    private String opportunityID;

    @TableField("CustomerID")
    private String customerID;

    @TableField("CustomerName")
    private String customerName;

    @TableField("CustomerMobile")
    private String customerMobile;
    @TableField("CustomerMobileWhole")
    private String customerMobileWhole;

    @TableField("CustomerRankName")
    private String customerRankName;

    @TableField("CustomerStatus")
    private String customerStatus;

    @TableField("IntentProjectName")
    private String intentProjectName;

    @TableField("cityName")
    private String cityName;

    @TableField("areaName")
    private String areaName;

    @TableField("ProjNum")
    private Integer projNum;

    @TableField("bookingCreateTime")
    private Date bookingCreateTime;
    @TableField(exist = false)
    private Date bookingCreateTimeEnd;

    @TableField("orderCount")
    private Integer orderCount;

    @TableField("MYContractCount")
    private Integer mYContractCount;

    @TableField("orderCreateTime")
    private Date orderCreateTime;
    @TableField(exist = false)
    private Date orderCreateTimeEnd;

    @TableField("MYContractCreateTime")
    private Date mYContractCreateTime;
    @TableField(exist = false)
    private Date mYContractCreateTimeEnd;

    @TableField("myorderAccount")
    private BigDecimal myorderAccount;

    @TableField("ContractAccount")
    private BigDecimal contractAccount;

    @TableField("daofangCount")
    private Integer daofangCount;

    @TableField("OpportunitySource")
    private String opportunitySource;

    @TableField("CognitiveChannel")
    private String cognitiveChannel;

    @TableField("CognitiveChannelSub")
    private String cognitiveChannelSub;

    @TableField("SaleUserID")
    private String saleUserID;

    @TableField("SaleUserName")
    private String saleUserName;

    @TableField("TheFirstVisitDate")
    private Date theFirstVisitDate;
    @TableField(exist = false)
    private Date theFirstVisitDateEnd;

    @TableField("ZJDF")
    private Date zjdf;
    @TableField(exist = false)
    private Date zjdfEnd;

    @TableField("FollwUpWayTxt")
    private String follwUpWayTxt;

    @TableField("TheLatestFollowUpDate")
    private Date theLatestFollowUpDate;
    @TableField(exist = false)
    private Date theLatestFollowUpDateEnd;

    @TableField("CreateTime")
    private Date createTime;
    @TableField(exist = false)
    private Date createTimeEnd;

    @TableField("Gender")
    private String gender;

    @TableField("SourceType")
    private String sourceType;

    @TableField("ReportUserID")
    private String reportUserID;

    @TableField("ReportUserName")
    private String reportUserName;

    @TableField("ReportUserMobile")
    private String reportUserMobile;

    @TableField("ChannelName")
    private String channelName;

    @TableField("ReportTime")
    private Date reportTime;
    @TableField(exist = false)
    private Date reportTimeEnd;

    @TableField("GenderTxt")
    private String genderTxt;

    @TableField("SaleTeamName")
    private String saleTeamName;


    public String getCustomerMobileWhole() {
        return customerMobileWhole;
    }

    public void setCustomerMobileWhole(String customerMobileWhole) {
        this.customerMobileWhole = customerMobileWhole;
    }

    public Date getBookingCreateTimeEnd() {
        return bookingCreateTimeEnd;
    }

    public void setBookingCreateTimeEnd(Date bookingCreateTimeEnd) {
        this.bookingCreateTimeEnd = bookingCreateTimeEnd;
    }

    public Date getOrderCreateTimeEnd() {
        return orderCreateTimeEnd;
    }

    public void setOrderCreateTimeEnd(Date orderCreateTimeEnd) {
        this.orderCreateTimeEnd = orderCreateTimeEnd;
    }

    public Date getmYContractCreateTimeEnd() {
        return mYContractCreateTimeEnd;
    }

    public void setmYContractCreateTimeEnd(Date mYContractCreateTimeEnd) {
        this.mYContractCreateTimeEnd = mYContractCreateTimeEnd;
    }

    public Date getTheFirstVisitDateEnd() {
        return theFirstVisitDateEnd;
    }

    public void setTheFirstVisitDateEnd(Date theFirstVisitDateEnd) {
        this.theFirstVisitDateEnd = theFirstVisitDateEnd;
    }

    public Date getZjdfEnd() {
        return zjdfEnd;
    }

    public void setZjdfEnd(Date zjdfEnd) {
        this.zjdfEnd = zjdfEnd;
    }

    public Date getTheLatestFollowUpDateEnd() {
        return theLatestFollowUpDateEnd;
    }

    public void setTheLatestFollowUpDateEnd(Date theLatestFollowUpDateEnd) {
        this.theLatestFollowUpDateEnd = theLatestFollowUpDateEnd;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getReportTimeEnd() {
        return reportTimeEnd;
    }

    public void setReportTimeEnd(Date reportTimeEnd) {
        this.reportTimeEnd = reportTimeEnd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(String opportunityID) {
        this.opportunityID = opportunityID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerRankName() {
        return customerRankName;
    }

    public void setCustomerRankName(String customerRankName) {
        this.customerRankName = customerRankName;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getIntentProjectName() {
        return intentProjectName;
    }

    public void setIntentProjectName(String intentProjectName) {
        this.intentProjectName = intentProjectName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getProjNum() {
        return projNum;
    }

    public void setProjNum(Integer projNum) {
        this.projNum = projNum;
    }

    public Date getBookingCreateTime() {
        return bookingCreateTime;
    }

    public void setBookingCreateTime(Date bookingCreateTime) {
        this.bookingCreateTime = bookingCreateTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getmYContractCount() {
        return mYContractCount;
    }

    public void setmYContractCount(Integer mYContractCount) {
        this.mYContractCount = mYContractCount;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getmYContractCreateTime() {
        return mYContractCreateTime;
    }

    public void setmYContractCreateTime(Date mYContractCreateTime) {
        this.mYContractCreateTime = mYContractCreateTime;
    }

    public BigDecimal getMyorderAccount() {
        return myorderAccount;
    }

    public void setMyorderAccount(BigDecimal myorderAccount) {
        this.myorderAccount = myorderAccount;
    }

    public BigDecimal getContractAccount() {
        return contractAccount;
    }

    public void setContractAccount(BigDecimal contractAccount) {
        this.contractAccount = contractAccount;
    }

    public Integer getDaofangCount() {
        return daofangCount;
    }

    public void setDaofangCount(Integer daofangCount) {
        this.daofangCount = daofangCount;
    }

    public String getOpportunitySource() {
        return opportunitySource;
    }

    public void setOpportunitySource(String opportunitySource) {
        this.opportunitySource = opportunitySource;
    }

    public String getCognitiveChannel() {
        return cognitiveChannel;
    }

    public void setCognitiveChannel(String cognitiveChannel) {
        this.cognitiveChannel = cognitiveChannel;
    }

    public String getCognitiveChannelSub() {
        return cognitiveChannelSub;
    }

    public void setCognitiveChannelSub(String cognitiveChannelSub) {
        this.cognitiveChannelSub = cognitiveChannelSub;
    }

    public String getSaleUserID() {
        return saleUserID;
    }

    public void setSaleUserID(String saleUserID) {
        this.saleUserID = saleUserID;
    }

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public Date getTheFirstVisitDate() {
        return theFirstVisitDate;
    }

    public void setTheFirstVisitDate(Date theFirstVisitDate) {
        this.theFirstVisitDate = theFirstVisitDate;
    }

    public Date getZjdf() {
        return zjdf;
    }

    public void setZjdf(Date zjdf) {
        this.zjdf = zjdf;
    }

    public String getFollwUpWayTxt() {
        return follwUpWayTxt;
    }

    public void setFollwUpWayTxt(String follwUpWayTxt) {
        this.follwUpWayTxt = follwUpWayTxt;
    }

    public Date getTheLatestFollowUpDate() {
        return theLatestFollowUpDate;
    }

    public void setTheLatestFollowUpDate(Date theLatestFollowUpDate) {
        this.theLatestFollowUpDate = theLatestFollowUpDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getReportUserID() {
        return reportUserID;
    }

    public void setReportUserID(String reportUserID) {
        this.reportUserID = reportUserID;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public String getReportUserMobile() {
        return reportUserMobile;
    }

    public void setReportUserMobile(String reportUserMobile) {
        this.reportUserMobile = reportUserMobile;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getGenderTxt() {
        return genderTxt;
    }

    public void setGenderTxt(String genderTxt) {
        this.genderTxt = genderTxt;
    }

    public String getSaleTeamName() {
        return saleTeamName;
    }

    public void setSaleTeamName(String saleTeamName) {
        this.saleTeamName = saleTeamName;
    }
}
