package com.tahoecn.xkc.model.customer;

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
 * @since 2019-07-06
 */
@TableName("B_Opportunity")
@ApiModel(value="BOpportunity对象", description="")
public class BOpportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("ClueID")
    private String ClueID;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("LastName")
    private String LastName;

    @TableField("FirstName")
    private String FirstName;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("CognitiveChannel")
    private String CognitiveChannel;

    @TableField("OpportunitySource")
    private String OpportunitySource;

    @TableField("Commercial")
    private String Commercial;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("TheFirstVisitDate")
    private Date TheFirstVisitDate;

    @TableField("TheLatestFollowUpDate")
    private Date TheLatestFollowUpDate;

    @TableField("TheLatestFollowUpContent")
    private String TheLatestFollowUpContent;

    @TableField("TheLatestFollowUpWay")
    private String TheLatestFollowUpWay;

    @TableField("VisitsCount")
    private Integer VisitsCount;

    @TableField("FollowupCount")
    private Integer FollowupCount;

    @TableField("IntentProjectID")
    private String IntentProjectID;

    @TableField("IntentProjectName")
    private String IntentProjectName;

    @TableField("CustomerLevel")
    private String CustomerLevel;

    @TableField("CustomerRank")
    private String CustomerRank;

    @TableField("PropertyIntention")
    private String PropertyIntention;

    @TableField("Remark")
    private String Remark;

    @TableField("IsLittleBooking")
    private String IsLittleBooking;

    @TableField("IsCustomerFirstEdit")
    private Integer IsCustomerFirstEdit;

    @TableField("CustomerFirstEditTime")
    private Date CustomerFirstEditTime;

    @TableField("AllotUserID")
    private String AllotUserID;

    @TableField("AllotTime")
    private Date AllotTime;

    @TableField("IsEquity")
    private Integer IsEquity;

    @TableField("OrgID")
    private String OrgID;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @ApiModelProperty(value = "0 否 1是")
    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "  1 已报备 2 跟进中 3 已过期 4 已放弃 5 已到访 6 已认筹 7 已认购 8 已签约")
    @TableField("Status")
    private Integer Status;

    @TableField("VisitingIntention")
    private String VisitingIntention;

    @TableField("AllCustomer")
    private String AllCustomer;

    @TableField("CognitiveChannelSub")
    private String CognitiveChannelSub;

    @TableField("TheLatestVisitDate")
    private Date TheLatestVisitDate;

    @TableField("NextFollowUpDate")
    private Date NextFollowUpDate;

    @TableField("FirstVisitAddress")
    private String FirstVisitAddress;

    @TableField("ReVisitAddress")
    private String ReVisitAddress;

    @TableField("SalePartnerID")
    private String SalePartnerID;

    @TableField("SalePartnerName")
    private String SalePartnerName;

    @TableField("SalePartnerAllotUserID")
    private String SalePartnerAllotUserID;

    @TableField("SalePartnerAllotTime")
    private Date SalePartnerAllotTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public String getCognitiveChannel() {
        return CognitiveChannel;
    }

    public void setCognitiveChannel(String CognitiveChannel) {
        this.CognitiveChannel = CognitiveChannel;
    }
    public String getOpportunitySource() {
        return OpportunitySource;
    }

    public void setOpportunitySource(String OpportunitySource) {
        this.OpportunitySource = OpportunitySource;
    }
    public String getCommercial() {
        return Commercial;
    }

    public void setCommercial(String Commercial) {
        this.Commercial = Commercial;
    }
    public String getSaleUserID() {
        return SaleUserID;
    }

    public void setSaleUserID(String SaleUserID) {
        this.SaleUserID = SaleUserID;
    }
    public String getSaleUserName() {
        return SaleUserName;
    }

    public void setSaleUserName(String SaleUserName) {
        this.SaleUserName = SaleUserName;
    }
    public Date getTheFirstVisitDate() {
        return TheFirstVisitDate;
    }

    public void setTheFirstVisitDate(Date TheFirstVisitDate) {
        this.TheFirstVisitDate = TheFirstVisitDate;
    }
    public Date getTheLatestFollowUpDate() {
        return TheLatestFollowUpDate;
    }

    public void setTheLatestFollowUpDate(Date TheLatestFollowUpDate) {
        this.TheLatestFollowUpDate = TheLatestFollowUpDate;
    }
    public String getTheLatestFollowUpContent() {
        return TheLatestFollowUpContent;
    }

    public void setTheLatestFollowUpContent(String TheLatestFollowUpContent) {
        this.TheLatestFollowUpContent = TheLatestFollowUpContent;
    }
    public String getTheLatestFollowUpWay() {
        return TheLatestFollowUpWay;
    }

    public void setTheLatestFollowUpWay(String TheLatestFollowUpWay) {
        this.TheLatestFollowUpWay = TheLatestFollowUpWay;
    }
    public Integer getVisitsCount() {
        return VisitsCount;
    }

    public void setVisitsCount(Integer VisitsCount) {
        this.VisitsCount = VisitsCount;
    }
    public Integer getFollowupCount() {
        return FollowupCount;
    }

    public void setFollowupCount(Integer FollowupCount) {
        this.FollowupCount = FollowupCount;
    }
    public String getIntentProjectID() {
        return IntentProjectID;
    }

    public void setIntentProjectID(String IntentProjectID) {
        this.IntentProjectID = IntentProjectID;
    }
    public String getIntentProjectName() {
        return IntentProjectName;
    }

    public void setIntentProjectName(String IntentProjectName) {
        this.IntentProjectName = IntentProjectName;
    }
    public String getCustomerLevel() {
        return CustomerLevel;
    }

    public void setCustomerLevel(String CustomerLevel) {
        this.CustomerLevel = CustomerLevel;
    }
    public String getCustomerRank() {
        return CustomerRank;
    }

    public void setCustomerRank(String CustomerRank) {
        this.CustomerRank = CustomerRank;
    }
    public String getPropertyIntention() {
        return PropertyIntention;
    }

    public void setPropertyIntention(String PropertyIntention) {
        this.PropertyIntention = PropertyIntention;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getIsLittleBooking() {
        return IsLittleBooking;
    }

    public void setIsLittleBooking(String IsLittleBooking) {
        this.IsLittleBooking = IsLittleBooking;
    }
    public Integer getIsCustomerFirstEdit() {
        return IsCustomerFirstEdit;
    }

    public void setIsCustomerFirstEdit(Integer IsCustomerFirstEdit) {
        this.IsCustomerFirstEdit = IsCustomerFirstEdit;
    }
    public Date getCustomerFirstEditTime() {
        return CustomerFirstEditTime;
    }

    public void setCustomerFirstEditTime(Date CustomerFirstEditTime) {
        this.CustomerFirstEditTime = CustomerFirstEditTime;
    }
    public String getAllotUserID() {
        return AllotUserID;
    }

    public void setAllotUserID(String AllotUserID) {
        this.AllotUserID = AllotUserID;
    }
    public Date getAllotTime() {
        return AllotTime;
    }

    public void setAllotTime(Date AllotTime) {
        this.AllotTime = AllotTime;
    }
    public Integer getIsEquity() {
        return IsEquity;
    }

    public void setIsEquity(Integer IsEquity) {
        this.IsEquity = IsEquity;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
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
    public String getVisitingIntention() {
        return VisitingIntention;
    }

    public void setVisitingIntention(String VisitingIntention) {
        this.VisitingIntention = VisitingIntention;
    }
    public String getAllCustomer() {
        return AllCustomer;
    }

    public void setAllCustomer(String AllCustomer) {
        this.AllCustomer = AllCustomer;
    }
    public String getCognitiveChannelSub() {
        return CognitiveChannelSub;
    }

    public void setCognitiveChannelSub(String CognitiveChannelSub) {
        this.CognitiveChannelSub = CognitiveChannelSub;
    }
    public Date getTheLatestVisitDate() {
        return TheLatestVisitDate;
    }

    public void setTheLatestVisitDate(Date TheLatestVisitDate) {
        this.TheLatestVisitDate = TheLatestVisitDate;
    }
    public Date getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(Date NextFollowUpDate) {
        this.NextFollowUpDate = NextFollowUpDate;
    }
    public String getFirstVisitAddress() {
        return FirstVisitAddress;
    }

    public void setFirstVisitAddress(String FirstVisitAddress) {
        this.FirstVisitAddress = FirstVisitAddress;
    }
    public String getReVisitAddress() {
        return ReVisitAddress;
    }

    public void setReVisitAddress(String ReVisitAddress) {
        this.ReVisitAddress = ReVisitAddress;
    }
    public String getSalePartnerID() {
        return SalePartnerID;
    }

    public void setSalePartnerID(String SalePartnerID) {
        this.SalePartnerID = SalePartnerID;
    }
    public String getSalePartnerName() {
        return SalePartnerName;
    }

    public void setSalePartnerName(String SalePartnerName) {
        this.SalePartnerName = SalePartnerName;
    }
    public String getSalePartnerAllotUserID() {
        return SalePartnerAllotUserID;
    }

    public void setSalePartnerAllotUserID(String SalePartnerAllotUserID) {
        this.SalePartnerAllotUserID = SalePartnerAllotUserID;
    }
    public Date getSalePartnerAllotTime() {
        return SalePartnerAllotTime;
    }

    public void setSalePartnerAllotTime(Date SalePartnerAllotTime) {
        this.SalePartnerAllotTime = SalePartnerAllotTime;
    }

    @Override
    public String toString() {
        return "BOpportunity{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", ClueID=" + ClueID +
        ", CustomerID=" + CustomerID +
        ", CustomerName=" + CustomerName +
        ", LastName=" + LastName +
        ", FirstName=" + FirstName +
        ", CustomerMobile=" + CustomerMobile +
        ", CognitiveChannel=" + CognitiveChannel +
        ", OpportunitySource=" + OpportunitySource +
        ", Commercial=" + Commercial +
        ", SaleUserID=" + SaleUserID +
        ", SaleUserName=" + SaleUserName +
        ", TheFirstVisitDate=" + TheFirstVisitDate +
        ", TheLatestFollowUpDate=" + TheLatestFollowUpDate +
        ", TheLatestFollowUpContent=" + TheLatestFollowUpContent +
        ", TheLatestFollowUpWay=" + TheLatestFollowUpWay +
        ", VisitsCount=" + VisitsCount +
        ", FollowupCount=" + FollowupCount +
        ", IntentProjectID=" + IntentProjectID +
        ", IntentProjectName=" + IntentProjectName +
        ", CustomerLevel=" + CustomerLevel +
        ", CustomerRank=" + CustomerRank +
        ", PropertyIntention=" + PropertyIntention +
        ", Remark=" + Remark +
        ", IsLittleBooking=" + IsLittleBooking +
        ", IsCustomerFirstEdit=" + IsCustomerFirstEdit +
        ", CustomerFirstEditTime=" + CustomerFirstEditTime +
        ", AllotUserID=" + AllotUserID +
        ", AllotTime=" + AllotTime +
        ", IsEquity=" + IsEquity +
        ", OrgID=" + OrgID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", VisitingIntention=" + VisitingIntention +
        ", AllCustomer=" + AllCustomer +
        ", CognitiveChannelSub=" + CognitiveChannelSub +
        ", TheLatestVisitDate=" + TheLatestVisitDate +
        ", NextFollowUpDate=" + NextFollowUpDate +
        ", FirstVisitAddress=" + FirstVisitAddress +
        ", ReVisitAddress=" + ReVisitAddress +
        ", SalePartnerID=" + SalePartnerID +
        ", SalePartnerName=" + SalePartnerName +
        ", SalePartnerAllotUserID=" + SalePartnerAllotUserID +
        ", SalePartnerAllotTime=" + SalePartnerAllotTime +
        "}";
    }
}
