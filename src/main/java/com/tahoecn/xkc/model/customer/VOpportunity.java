package com.tahoecn.xkc.model.customer;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-04
 */
@TableName("V_Opportunity")
@ApiModel(value="VOpportunity对象", description="")
public class VOpportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("AllCustomer")
    private String AllCustomer;

    @TableField("ID")
    private String id;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("ClueID")
    private String ClueID;

    @TableField("ChannelName")
    private String ChannelName;

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

    @TableField("Mobile")
    private String Mobile;

    @TableField("CustomerTag")
    private String CustomerTag;

    @TableField("CognitiveChannel")
    private String CognitiveChannel;

    @TableField("CognitiveChannelName")
    private String CognitiveChannelName;

    @TableField("CognitiveChannelSub")
    private String CognitiveChannelSub;

    @TableField("CognitiveChannelSubName")
    private String CognitiveChannelSubName;

    @TableField("OpportunitySource")
    private String OpportunitySource;

    @TableField("OpportunitySourceName")
    private String OpportunitySourceName;

    @TableField("Commercial")
    private String Commercial;

    @TableField("CommercialName")
    private String CommercialName;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("SaleGroupName")
    private String SaleGroupName;

    @TableField("FirstVisitDate")
    private String FirstVisitDate;

    @TableField("TheLatestFollowUpDate")
    private String TheLatestFollowUpDate;

    @TableField("VisitsCount")
    private Integer VisitsCount;

    @TableField("FollowupCount")
    private Integer FollowupCount;

    @TableField("IntentProjectID")
    private String IntentProjectID;

    @TableField("IntentProjectIDName")
    private String IntentProjectIDName;

    @TableField("CustomerLevel")
    private String CustomerLevel;

    @TableField("CustomerRank")
    private String CustomerRank;

    @TableField("CustomerRankName")
    private String CustomerRankName;

    @TableField("ORemark")
    private String ORemark;

    @TableField("OrgID")
    private String OrgID;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private String CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("StatusName")
    private String StatusName;

    @TableField("Gender")
    private String Gender;

    @TableField("GenderName")
    private String GenderName;

    @TableField("IsOwner")
    private Integer IsOwner;

    @TableField("CardType")
    private String CardType;

    @TableField("CardTypeName")
    private String CardTypeName;

    @TableField("CardID")
    private String CardID;

    @TableField("Remark")
    private String Remark;

    @TableField("COrgID")
    private String COrgID;

    @TableField("DomicilePlace")
    private String DomicilePlace;

    @TableField("DomicilePlaceName")
    private String DomicilePlaceName;

    @TableField("HomeAddress")
    private String HomeAddress;

    @TableField("HomeAddressName")
    private String HomeAddressName;

    @TableField("HomeArea")
    private String HomeArea;

    @TableField("HomeAreaName")
    private String HomeAreaName;

    @TableField("WorkArea")
    private String WorkArea;

    @TableField("WorkAreaName")
    private String WorkAreaName;

    @TableField("Marriage")
    private String Marriage;

    @TableField("MarriageName")
    private String MarriageName;

    @TableField("Family")
    private String Family;

    @TableField("FamilyName")
    private String FamilyName;

    @TableField("Industry")
    private String Industry;

    @TableField("IndustryName")
    private String IndustryName;

    @TableField("AgeGroup")
    private String AgeGroup;

    @TableField("AgeGroupName")
    private String AgeGroupName;

    @TableField("FollwUpWay")
    private String FollwUpWay;

    @TableField("FollwUpWayName")
    private String FollwUpWayName;

    @TableField("PropertyNum")
    private String PropertyNum;

    @TableField("PropertyNumName")
    private String PropertyNumName;

    @TableField("FollowUpContent")
    private String FollowUpContent;

    @TableField("AcceptFactor")
    private String AcceptFactor;

    @TableField("AcceptFactorName")
    private String AcceptFactorName;

    @TableField("PropertyIntention")
    private String PropertyIntention;

    @TableField("PropertyIntentionName")
    private String PropertyIntentionName;

    @TableField("IsEmployee")
    private String IsEmployee;

    @TableField("IsLittleBooking")
    private String IsLittleBooking;

    @TableField("IsLittleBookingName")
    private String IsLittleBookingName;

    @TableField("IsEmployeeName")
    private String IsEmployeeName;

    @TableField("Birthday")
    private String Birthday;

    @TableField("Address")
    private String Address;

    @TableField("IsCustomerFirstEdit")
    private Integer IsCustomerFirstEdit;

    @TableField("CustomerFirstEditTime")
    private Date CustomerFirstEditTime;

    @TableField("FirstVisitAddress")
    private String FirstVisitAddress;

    @TableField("SalePartnerID")
    private String SalePartnerID;

    public String getAllCustomer() {
        return AllCustomer;
    }

    public void setAllCustomer(String AllCustomer) {
        this.AllCustomer = AllCustomer;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
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
    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String ChannelName) {
        this.ChannelName = ChannelName;
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
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getCustomerTag() {
        return CustomerTag;
    }

    public void setCustomerTag(String CustomerTag) {
        this.CustomerTag = CustomerTag;
    }
    public String getCognitiveChannel() {
        return CognitiveChannel;
    }

    public void setCognitiveChannel(String CognitiveChannel) {
        this.CognitiveChannel = CognitiveChannel;
    }
    public String getCognitiveChannelName() {
        return CognitiveChannelName;
    }

    public void setCognitiveChannelName(String CognitiveChannelName) {
        this.CognitiveChannelName = CognitiveChannelName;
    }
    public String getCognitiveChannelSub() {
        return CognitiveChannelSub;
    }

    public void setCognitiveChannelSub(String CognitiveChannelSub) {
        this.CognitiveChannelSub = CognitiveChannelSub;
    }
    public String getCognitiveChannelSubName() {
        return CognitiveChannelSubName;
    }

    public void setCognitiveChannelSubName(String CognitiveChannelSubName) {
        this.CognitiveChannelSubName = CognitiveChannelSubName;
    }
    public String getOpportunitySource() {
        return OpportunitySource;
    }

    public void setOpportunitySource(String OpportunitySource) {
        this.OpportunitySource = OpportunitySource;
    }
    public String getOpportunitySourceName() {
        return OpportunitySourceName;
    }

    public void setOpportunitySourceName(String OpportunitySourceName) {
        this.OpportunitySourceName = OpportunitySourceName;
    }
    public String getCommercial() {
        return Commercial;
    }

    public void setCommercial(String Commercial) {
        this.Commercial = Commercial;
    }
    public String getCommercialName() {
        return CommercialName;
    }

    public void setCommercialName(String CommercialName) {
        this.CommercialName = CommercialName;
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
    public String getSaleGroupName() {
        return SaleGroupName;
    }

    public void setSaleGroupName(String SaleGroupName) {
        this.SaleGroupName = SaleGroupName;
    }
    public String getFirstVisitDate() {
        return FirstVisitDate;
    }

    public void setFirstVisitDate(String FirstVisitDate) {
        this.FirstVisitDate = FirstVisitDate;
    }
    public String getTheLatestFollowUpDate() {
        return TheLatestFollowUpDate;
    }

    public void setTheLatestFollowUpDate(String TheLatestFollowUpDate) {
        this.TheLatestFollowUpDate = TheLatestFollowUpDate;
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
    public String getIntentProjectIDName() {
        return IntentProjectIDName;
    }

    public void setIntentProjectIDName(String IntentProjectIDName) {
        this.IntentProjectIDName = IntentProjectIDName;
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
    public String getCustomerRankName() {
        return CustomerRankName;
    }

    public void setCustomerRankName(String CustomerRankName) {
        this.CustomerRankName = CustomerRankName;
    }
    public String getORemark() {
        return ORemark;
    }

    public void setORemark(String ORemark) {
        this.ORemark = ORemark;
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
    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
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
    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public String getGenderName() {
        return GenderName;
    }

    public void setGenderName(String GenderName) {
        this.GenderName = GenderName;
    }
    public Integer getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(Integer IsOwner) {
        this.IsOwner = IsOwner;
    }
    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }
    public String getCardTypeName() {
        return CardTypeName;
    }

    public void setCardTypeName(String CardTypeName) {
        this.CardTypeName = CardTypeName;
    }
    public String getCardID() {
        return CardID;
    }

    public void setCardID(String CardID) {
        this.CardID = CardID;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getCOrgID() {
        return COrgID;
    }

    public void setCOrgID(String COrgID) {
        this.COrgID = COrgID;
    }
    public String getDomicilePlace() {
        return DomicilePlace;
    }

    public void setDomicilePlace(String DomicilePlace) {
        this.DomicilePlace = DomicilePlace;
    }
    public String getDomicilePlaceName() {
        return DomicilePlaceName;
    }

    public void setDomicilePlaceName(String DomicilePlaceName) {
        this.DomicilePlaceName = DomicilePlaceName;
    }
    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String HomeAddress) {
        this.HomeAddress = HomeAddress;
    }
    public String getHomeAddressName() {
        return HomeAddressName;
    }

    public void setHomeAddressName(String HomeAddressName) {
        this.HomeAddressName = HomeAddressName;
    }
    public String getHomeArea() {
        return HomeArea;
    }

    public void setHomeArea(String HomeArea) {
        this.HomeArea = HomeArea;
    }
    public String getHomeAreaName() {
        return HomeAreaName;
    }

    public void setHomeAreaName(String HomeAreaName) {
        this.HomeAreaName = HomeAreaName;
    }
    public String getWorkArea() {
        return WorkArea;
    }

    public void setWorkArea(String WorkArea) {
        this.WorkArea = WorkArea;
    }
    public String getWorkAreaName() {
        return WorkAreaName;
    }

    public void setWorkAreaName(String WorkAreaName) {
        this.WorkAreaName = WorkAreaName;
    }
    public String getMarriage() {
        return Marriage;
    }

    public void setMarriage(String Marriage) {
        this.Marriage = Marriage;
    }
    public String getMarriageName() {
        return MarriageName;
    }

    public void setMarriageName(String MarriageName) {
        this.MarriageName = MarriageName;
    }
    public String getFamily() {
        return Family;
    }

    public void setFamily(String Family) {
        this.Family = Family;
    }
    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String FamilyName) {
        this.FamilyName = FamilyName;
    }
    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String Industry) {
        this.Industry = Industry;
    }
    public String getIndustryName() {
        return IndustryName;
    }

    public void setIndustryName(String IndustryName) {
        this.IndustryName = IndustryName;
    }
    public String getAgeGroup() {
        return AgeGroup;
    }

    public void setAgeGroup(String AgeGroup) {
        this.AgeGroup = AgeGroup;
    }
    public String getAgeGroupName() {
        return AgeGroupName;
    }

    public void setAgeGroupName(String AgeGroupName) {
        this.AgeGroupName = AgeGroupName;
    }
    public String getFollwUpWay() {
        return FollwUpWay;
    }

    public void setFollwUpWay(String FollwUpWay) {
        this.FollwUpWay = FollwUpWay;
    }
    public String getFollwUpWayName() {
        return FollwUpWayName;
    }

    public void setFollwUpWayName(String FollwUpWayName) {
        this.FollwUpWayName = FollwUpWayName;
    }
    public String getPropertyNum() {
        return PropertyNum;
    }

    public void setPropertyNum(String PropertyNum) {
        this.PropertyNum = PropertyNum;
    }
    public String getPropertyNumName() {
        return PropertyNumName;
    }

    public void setPropertyNumName(String PropertyNumName) {
        this.PropertyNumName = PropertyNumName;
    }
    public String getFollowUpContent() {
        return FollowUpContent;
    }

    public void setFollowUpContent(String FollowUpContent) {
        this.FollowUpContent = FollowUpContent;
    }
    public String getAcceptFactor() {
        return AcceptFactor;
    }

    public void setAcceptFactor(String AcceptFactor) {
        this.AcceptFactor = AcceptFactor;
    }
    public String getAcceptFactorName() {
        return AcceptFactorName;
    }

    public void setAcceptFactorName(String AcceptFactorName) {
        this.AcceptFactorName = AcceptFactorName;
    }
    public String getPropertyIntention() {
        return PropertyIntention;
    }

    public void setPropertyIntention(String PropertyIntention) {
        this.PropertyIntention = PropertyIntention;
    }
    public String getPropertyIntentionName() {
        return PropertyIntentionName;
    }

    public void setPropertyIntentionName(String PropertyIntentionName) {
        this.PropertyIntentionName = PropertyIntentionName;
    }
    public String getIsEmployee() {
        return IsEmployee;
    }

    public void setIsEmployee(String IsEmployee) {
        this.IsEmployee = IsEmployee;
    }
    public String getIsLittleBooking() {
        return IsLittleBooking;
    }

    public void setIsLittleBooking(String IsLittleBooking) {
        this.IsLittleBooking = IsLittleBooking;
    }
    public String getIsLittleBookingName() {
        return IsLittleBookingName;
    }

    public void setIsLittleBookingName(String IsLittleBookingName) {
        this.IsLittleBookingName = IsLittleBookingName;
    }
    public String getIsEmployeeName() {
        return IsEmployeeName;
    }

    public void setIsEmployeeName(String IsEmployeeName) {
        this.IsEmployeeName = IsEmployeeName;
    }
    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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
    public String getFirstVisitAddress() {
        return FirstVisitAddress;
    }

    public void setFirstVisitAddress(String FirstVisitAddress) {
        this.FirstVisitAddress = FirstVisitAddress;
    }
    public String getSalePartnerID() {
        return SalePartnerID;
    }

    public void setSalePartnerID(String SalePartnerID) {
        this.SalePartnerID = SalePartnerID;
    }

    @Override
    public String toString() {
        return "VOpportunity{" +
        "AllCustomer=" + AllCustomer +
        ", id=" + id +
        ", OpportunityID=" + OpportunityID +
        ", ProjectID=" + ProjectID +
        ", ClueID=" + ClueID +
        ", ChannelName=" + ChannelName +
        ", CustomerID=" + CustomerID +
        ", CustomerName=" + CustomerName +
        ", LastName=" + LastName +
        ", FirstName=" + FirstName +
        ", CustomerMobile=" + CustomerMobile +
        ", Mobile=" + Mobile +
        ", CustomerTag=" + CustomerTag +
        ", CognitiveChannel=" + CognitiveChannel +
        ", CognitiveChannelName=" + CognitiveChannelName +
        ", CognitiveChannelSub=" + CognitiveChannelSub +
        ", CognitiveChannelSubName=" + CognitiveChannelSubName +
        ", OpportunitySource=" + OpportunitySource +
        ", OpportunitySourceName=" + OpportunitySourceName +
        ", Commercial=" + Commercial +
        ", CommercialName=" + CommercialName +
        ", SaleUserID=" + SaleUserID +
        ", SaleUserName=" + SaleUserName +
        ", SaleGroupName=" + SaleGroupName +
        ", FirstVisitDate=" + FirstVisitDate +
        ", TheLatestFollowUpDate=" + TheLatestFollowUpDate +
        ", VisitsCount=" + VisitsCount +
        ", FollowupCount=" + FollowupCount +
        ", IntentProjectID=" + IntentProjectID +
        ", IntentProjectIDName=" + IntentProjectIDName +
        ", CustomerLevel=" + CustomerLevel +
        ", CustomerRank=" + CustomerRank +
        ", CustomerRankName=" + CustomerRankName +
        ", ORemark=" + ORemark +
        ", OrgID=" + OrgID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", StatusName=" + StatusName +
        ", Gender=" + Gender +
        ", GenderName=" + GenderName +
        ", IsOwner=" + IsOwner +
        ", CardType=" + CardType +
        ", CardTypeName=" + CardTypeName +
        ", CardID=" + CardID +
        ", Remark=" + Remark +
        ", COrgID=" + COrgID +
        ", DomicilePlace=" + DomicilePlace +
        ", DomicilePlaceName=" + DomicilePlaceName +
        ", HomeAddress=" + HomeAddress +
        ", HomeAddressName=" + HomeAddressName +
        ", HomeArea=" + HomeArea +
        ", HomeAreaName=" + HomeAreaName +
        ", WorkArea=" + WorkArea +
        ", WorkAreaName=" + WorkAreaName +
        ", Marriage=" + Marriage +
        ", MarriageName=" + MarriageName +
        ", Family=" + Family +
        ", FamilyName=" + FamilyName +
        ", Industry=" + Industry +
        ", IndustryName=" + IndustryName +
        ", AgeGroup=" + AgeGroup +
        ", AgeGroupName=" + AgeGroupName +
        ", FollwUpWay=" + FollwUpWay +
        ", FollwUpWayName=" + FollwUpWayName +
        ", PropertyNum=" + PropertyNum +
        ", PropertyNumName=" + PropertyNumName +
        ", FollowUpContent=" + FollowUpContent +
        ", AcceptFactor=" + AcceptFactor +
        ", AcceptFactorName=" + AcceptFactorName +
        ", PropertyIntention=" + PropertyIntention +
        ", PropertyIntentionName=" + PropertyIntentionName +
        ", IsEmployee=" + IsEmployee +
        ", IsLittleBooking=" + IsLittleBooking +
        ", IsLittleBookingName=" + IsLittleBookingName +
        ", IsEmployeeName=" + IsEmployeeName +
        ", Birthday=" + Birthday +
        ", Address=" + Address +
        ", IsCustomerFirstEdit=" + IsCustomerFirstEdit +
        ", CustomerFirstEditTime=" + CustomerFirstEditTime +
        ", FirstVisitAddress=" + FirstVisitAddress +
        ", SalePartnerID=" + SalePartnerID +
        "}";
    }
}
