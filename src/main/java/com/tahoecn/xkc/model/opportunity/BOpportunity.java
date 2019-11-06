package com.tahoecn.xkc.model.opportunity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-07-04
 */
@TableName("B_Opportunity")
@ApiModel(value="BOpportunity对象", description="")
public class BOpportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "0 否 1是")
    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "  1 已报备   2 跟进中 3 已过期 4 已放弃 5 已到访 6 已认筹 7 已认购 8 已签约")
    @TableField("Status")
    private Integer Status;

    @TableField("Commercial")
    private String Commercial;

    @TableField("CustomerRank")
    private String CustomerRank;

    @TableField("IsEquity")
    private Integer IsEquity;

    @TableField("TheFirstVisitDate")
    private Date TheFirstVisitDate;

    @TableField("ClueID")
    private String ClueID;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("AllCustomer")
    private String AllCustomer;

    @TableField("TheLatestFollowUpWay")
    private String TheLatestFollowUpWay;

    @TableField("IntentProjectID")
    private String IntentProjectID;

    @TableField("CognitiveChannelSub")
    private String CognitiveChannelSub;

    @TableField("TheLatestFollowUpDate")
    private Date TheLatestFollowUpDate;

    @TableField("SalePartnerID")
    private String SalePartnerID;

    @TableField("Editor")
    private String Editor;

    @TableField("VisitsCount")
    private Integer VisitsCount;

    @TableField("SalePartnerAllotTime")
    private Date SalePartnerAllotTime;

    @TableField("IsLittleBooking")
    private String IsLittleBooking;

    @TableField("CognitiveChannel")
    private String CognitiveChannel;

    @TableField("LastName")
    private String LastName;

    @TableField("AllotUserID")
    private String AllotUserID;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("OrgID")
    private String OrgID;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("FirstName")
    private String FirstName;

    @TableField("IsCustomerFirstEdit")
    private Integer IsCustomerFirstEdit;

    @TableField("TheLatestVisitDate")
    private Date TheLatestVisitDate;

    @TableField("OpportunitySource")
    private String OpportunitySource;

    @TableField("AllotTime")
    private Date AllotTime;

    @TableField("IntentProjectName")
    private String IntentProjectName;

    @TableField("FirstVisitAddress")
    private String FirstVisitAddress;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("Creator")
    private String Creator;

    @TableId("ID")
    private String id;

    @TableField("SalePartnerName")
    private String SalePartnerName;

    @TableField("PropertyIntention")
    private String PropertyIntention;

    @TableField("FollowupCount")
    private Integer FollowupCount;

    @TableField("NextFollowUpDate")
    private Date NextFollowUpDate;

    @TableField("CustomerLevel")
    private String CustomerLevel;

    @TableField("ReVisitAddress")
    private String ReVisitAddress;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("SalePartnerAllotUserID")
    private String SalePartnerAllotUserID;

    @TableField("Remark")
    private String Remark;

    @TableField("TheLatestFollowUpContent")
    private String TheLatestFollowUpContent;

    @TableField("VisitingIntention")
    private String VisitingIntention;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("CustomerFirstEditTime")
    private Date CustomerFirstEditTime;

    @TableField("SpareMobile")
    private String SpareMobile;

    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getCommercial() {
        return Commercial;
    }

    public void setCommercial(String Commercial) {
        this.Commercial = Commercial;
    }
    public String getCustomerRank() {
        return CustomerRank;
    }

    public void setCustomerRank(String CustomerRank) {
        this.CustomerRank = CustomerRank;
    }
    public Integer getIsEquity() {
        return IsEquity;
    }

    public void setIsEquity(Integer IsEquity) {
        this.IsEquity = IsEquity;
    }
    public Date getTheFirstVisitDate() {
        return TheFirstVisitDate;
    }

    public void setTheFirstVisitDate(Date TheFirstVisitDate) {
        this.TheFirstVisitDate = TheFirstVisitDate;
    }
    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getAllCustomer() {
        return AllCustomer;
    }

    public void setAllCustomer(String AllCustomer) {
        this.AllCustomer = AllCustomer;
    }
    public String getTheLatestFollowUpWay() {
        return TheLatestFollowUpWay;
    }

    public void setTheLatestFollowUpWay(String TheLatestFollowUpWay) {
        this.TheLatestFollowUpWay = TheLatestFollowUpWay;
    }
    public String getIntentProjectID() {
        return IntentProjectID;
    }

    public void setIntentProjectID(String IntentProjectID) {
        this.IntentProjectID = IntentProjectID;
    }
    public String getCognitiveChannelSub() {
        return CognitiveChannelSub;
    }

    public void setCognitiveChannelSub(String CognitiveChannelSub) {
        this.CognitiveChannelSub = CognitiveChannelSub;
    }
    public Date getTheLatestFollowUpDate() {
        return TheLatestFollowUpDate;
    }

    public void setTheLatestFollowUpDate(Date TheLatestFollowUpDate) {
        this.TheLatestFollowUpDate = TheLatestFollowUpDate;
    }
    public String getSalePartnerID() {
        return SalePartnerID;
    }

    public void setSalePartnerID(String SalePartnerID) {
        this.SalePartnerID = SalePartnerID;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getVisitsCount() {
        return VisitsCount;
    }

    public void setVisitsCount(Integer VisitsCount) {
        this.VisitsCount = VisitsCount;
    }
    public Date getSalePartnerAllotTime() {
        return SalePartnerAllotTime;
    }

    public void setSalePartnerAllotTime(Date SalePartnerAllotTime) {
        this.SalePartnerAllotTime = SalePartnerAllotTime;
    }
    public String getIsLittleBooking() {
        return IsLittleBooking;
    }

    public void setIsLittleBooking(String IsLittleBooking) {
        this.IsLittleBooking = IsLittleBooking;
    }
    public String getCognitiveChannel() {
        return CognitiveChannel;
    }

    public void setCognitiveChannel(String CognitiveChannel) {
        this.CognitiveChannel = CognitiveChannel;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getAllotUserID() {
        return AllotUserID;
    }

    public void setAllotUserID(String AllotUserID) {
        this.AllotUserID = AllotUserID;
    }
    public String getSaleUserID() {
        return SaleUserID;
    }

    public void setSaleUserID(String SaleUserID) {
        this.SaleUserID = SaleUserID;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public Integer getIsCustomerFirstEdit() {
        return IsCustomerFirstEdit;
    }

    public void setIsCustomerFirstEdit(Integer IsCustomerFirstEdit) {
        this.IsCustomerFirstEdit = IsCustomerFirstEdit;
    }
    public Date getTheLatestVisitDate() {
        return TheLatestVisitDate;
    }

    public void setTheLatestVisitDate(Date TheLatestVisitDate) {
        this.TheLatestVisitDate = TheLatestVisitDate;
    }
    public String getOpportunitySource() {
        return OpportunitySource;
    }

    public void setOpportunitySource(String OpportunitySource) {
        this.OpportunitySource = OpportunitySource;
    }
    public Date getAllotTime() {
        return AllotTime;
    }

    public void setAllotTime(Date AllotTime) {
        this.AllotTime = AllotTime;
    }
    public String getIntentProjectName() {
        return IntentProjectName;
    }

    public void setIntentProjectName(String IntentProjectName) {
        this.IntentProjectName = IntentProjectName;
    }
    public String getFirstVisitAddress() {
        return FirstVisitAddress;
    }

    public void setFirstVisitAddress(String FirstVisitAddress) {
        this.FirstVisitAddress = FirstVisitAddress;
    }
    public String getSaleUserName() {
        return SaleUserName;
    }

    public void setSaleUserName(String SaleUserName) {
        this.SaleUserName = SaleUserName;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getSalePartnerName() {
        return SalePartnerName;
    }

    public void setSalePartnerName(String SalePartnerName) {
        this.SalePartnerName = SalePartnerName;
    }
    public String getPropertyIntention() {
        return PropertyIntention;
    }

    public void setPropertyIntention(String PropertyIntention) {
        this.PropertyIntention = PropertyIntention;
    }
    public Integer getFollowupCount() {
        return FollowupCount;
    }

    public void setFollowupCount(Integer FollowupCount) {
        this.FollowupCount = FollowupCount;
    }
    public Date getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(Date NextFollowUpDate) {
        this.NextFollowUpDate = NextFollowUpDate;
    }
    public String getCustomerLevel() {
        return CustomerLevel;
    }

    public void setCustomerLevel(String CustomerLevel) {
        this.CustomerLevel = CustomerLevel;
    }
    public String getReVisitAddress() {
        return ReVisitAddress;
    }

    public void setReVisitAddress(String ReVisitAddress) {
        this.ReVisitAddress = ReVisitAddress;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getSalePartnerAllotUserID() {
        return SalePartnerAllotUserID;
    }

    public void setSalePartnerAllotUserID(String SalePartnerAllotUserID) {
        this.SalePartnerAllotUserID = SalePartnerAllotUserID;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getTheLatestFollowUpContent() {
        return TheLatestFollowUpContent;
    }

    public void setTheLatestFollowUpContent(String TheLatestFollowUpContent) {
        this.TheLatestFollowUpContent = TheLatestFollowUpContent;
    }
    public String getVisitingIntention() {
        return VisitingIntention;
    }

    public void setVisitingIntention(String VisitingIntention) {
        this.VisitingIntention = VisitingIntention;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public Date getCustomerFirstEditTime() {
        return CustomerFirstEditTime;
    }

    public void setCustomerFirstEditTime(Date CustomerFirstEditTime) {
        this.CustomerFirstEditTime = CustomerFirstEditTime;
    }

    public String getSpareMobile() {
        return SpareMobile;
    }

    public void setSpareMobile(String spareMobile) {
        SpareMobile = spareMobile;
    }

    @Override
    public String toString() {
        return "BOpportunity{" +
        "IsDel=" + IsDel +
        ", Status=" + Status +
        ", Commercial=" + Commercial +
        ", CustomerRank=" + CustomerRank +
        ", IsEquity=" + IsEquity +
        ", TheFirstVisitDate=" + TheFirstVisitDate +
        ", ClueID=" + ClueID +
        ", CreateTime=" + CreateTime +
        ", AllCustomer=" + AllCustomer +
        ", TheLatestFollowUpWay=" + TheLatestFollowUpWay +
        ", IntentProjectID=" + IntentProjectID +
        ", CognitiveChannelSub=" + CognitiveChannelSub +
        ", TheLatestFollowUpDate=" + TheLatestFollowUpDate +
        ", SalePartnerID=" + SalePartnerID +
        ", Editor=" + Editor +
        ", VisitsCount=" + VisitsCount +
        ", SalePartnerAllotTime=" + SalePartnerAllotTime +
        ", IsLittleBooking=" + IsLittleBooking +
        ", CognitiveChannel=" + CognitiveChannel +
        ", LastName=" + LastName +
        ", AllotUserID=" + AllotUserID +
        ", SaleUserID=" + SaleUserID +
        ", OrgID=" + OrgID +
        ", CustomerID=" + CustomerID +
        ", FirstName=" + FirstName +
        ", IsCustomerFirstEdit=" + IsCustomerFirstEdit +
        ", TheLatestVisitDate=" + TheLatestVisitDate +
        ", OpportunitySource=" + OpportunitySource +
        ", AllotTime=" + AllotTime +
        ", IntentProjectName=" + IntentProjectName +
        ", FirstVisitAddress=" + FirstVisitAddress +
        ", SaleUserName=" + SaleUserName +
        ", Creator=" + Creator +
        ", id=" + id +
        ", SalePartnerName=" + SalePartnerName +
        ", PropertyIntention=" + PropertyIntention +
        ", FollowupCount=" + FollowupCount +
        ", NextFollowUpDate=" + NextFollowUpDate +
        ", CustomerLevel=" + CustomerLevel +
        ", ReVisitAddress=" + ReVisitAddress +
        ", ProjectID=" + ProjectID +
        ", SalePartnerAllotUserID=" + SalePartnerAllotUserID +
        ", Remark=" + Remark +
        ", TheLatestFollowUpContent=" + TheLatestFollowUpContent +
        ", VisitingIntention=" + VisitingIntention +
        ", CustomerName=" + CustomerName +
        ", CustomerMobile=" + CustomerMobile +
        ", SpareMobile=" + SpareMobile +
        ", EditeTime=" + EditeTime +
        ", CustomerFirstEditTime=" + CustomerFirstEditTime +
        "}";
    }
}
