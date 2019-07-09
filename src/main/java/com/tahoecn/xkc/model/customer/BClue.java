package com.tahoecn.xkc.model.customer;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-07-03
 */
@TableName("B_Clue")
@ApiModel(value="BClue对象", description="")
public class BClue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1 自拓团队 2 代理团队 3 自然拓客 4 分销中介 5案场 6 历史数据 7call客系统")
    @TableField("SourceType")
    private String SourceType;

    @ApiModelProperty(value = "1 已报备 ，2 跟进中，3 已过期，4 已放弃，5 已到访，6 已认筹，7 已认购，8 已签约")
    @TableField("Status")
    private Integer Status;

    @TableField("TradeOverdueTime")
    private Date TradeOverdueTime;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("Name")
    private String Name;

    @TableField("CustomerRank")
    private String CustomerRank;

    @TableField("ConfirmUserId")
    private String ConfirmUserId;

    @TableField("ReportUserID")
    private String ReportUserID;

    @TableField("ChannelTaskID")
    private String ChannelTaskID;

    @TableField("PropertyIntention")
    private String PropertyIntention;

    @TableField("ReportUserOrg")
    private String ReportUserOrg;

    @TableField("CognitiveChannelSub")
    private String CognitiveChannelSub;

    @TableField("InvalidTime")
    private Date InvalidTime;

    @TableField("AreaIntention")
    private String AreaIntention;

    @TableField("Gender")
    private String Gender;

    @TableField("NextFollowUpDate")
    private Date NextFollowUpDate;

    @TableField("ReportUserName")
    private String ReportUserName;

    @TableField("ChannelCollaborator")
    private String ChannelCollaborator;

    @TableField("WXUserID")
    private String WXUserID;

    @TableField("IsSelect")
    private Integer IsSelect;

    @TableField("RuleID")
    private String RuleID;

    @TableField("LastName")
    private String LastName;

    @TableField("TheLatestFollowUpWay")
    private String TheLatestFollowUpWay;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("IntentProjectName")
    private String IntentProjectName;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Remark")
    private String Remark;

    @TableField("ConfirmTime")
    private Date ConfirmTime;

    @TableField("Mobile")
    private String Mobile;

    @TableField("Editor")
    private String Editor;

    @TableField("Commercial")
    private String Commercial;

    @TableField("CustomerLevel")
    private String CustomerLevel;

    @TableField("InvalidReason")
    private String InvalidReason;

    @TableField("FeeIntention")
    private String FeeIntention;

    @TableField("FollowupCount")
    private Integer FollowupCount;

    @TableField("UserBehaviorID")
    private Integer UserBehaviorID;

    @TableField("IsShare")
    private Integer IsShare;

    @TableField("TheLatestFollowUpDate")
    private Date TheLatestFollowUpDate;

    @TableField("IntentProjectID")
    private String IntentProjectID;

    @TableField("HouseIntention")
    private String HouseIntention;

    @TableField("InvalidType")
    private Integer InvalidType;

    @TableField("TheLatestFollowUpContent")
    private String TheLatestFollowUpContent;

    @TableField("ComeOverdueTime")
    private Date ComeOverdueTime;

    @TableField("ReportMethod")
    private Integer ReportMethod;

    @TableField("CognitiveChannel")
    private String CognitiveChannel;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @TableField("ShareLogDetailID")
    private String ShareLogDetailID;

    @TableField("FirstName")
    private String FirstName;

    @TableField("ChannelUserID")
    private String ChannelUserID;

    @TableField("Creator")
    private String Creator;

    @TableField("CustomerPotentialID")
    private String CustomerPotentialID;

    @TableField("VisitIntention")
    private String VisitIntention;

    @TableField("ReportUserMobile")
    private String ReportUserMobile;

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String SourceType) {
        this.SourceType = SourceType;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Date getTradeOverdueTime() {
        return TradeOverdueTime;
    }

    public void setTradeOverdueTime(Date TradeOverdueTime) {
        this.TradeOverdueTime = TradeOverdueTime;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getCustomerRank() {
        return CustomerRank;
    }

    public void setCustomerRank(String CustomerRank) {
        this.CustomerRank = CustomerRank;
    }
    public String getConfirmUserId() {
        return ConfirmUserId;
    }

    public void setConfirmUserId(String ConfirmUserId) {
        this.ConfirmUserId = ConfirmUserId;
    }
    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String ReportUserID) {
        this.ReportUserID = ReportUserID;
    }
    public String getChannelTaskID() {
        return ChannelTaskID;
    }

    public void setChannelTaskID(String ChannelTaskID) {
        this.ChannelTaskID = ChannelTaskID;
    }
    public String getPropertyIntention() {
        return PropertyIntention;
    }

    public void setPropertyIntention(String PropertyIntention) {
        this.PropertyIntention = PropertyIntention;
    }
    public String getReportUserOrg() {
        return ReportUserOrg;
    }

    public void setReportUserOrg(String ReportUserOrg) {
        this.ReportUserOrg = ReportUserOrg;
    }
    public String getCognitiveChannelSub() {
        return CognitiveChannelSub;
    }

    public void setCognitiveChannelSub(String CognitiveChannelSub) {
        this.CognitiveChannelSub = CognitiveChannelSub;
    }
    public Date getInvalidTime() {
        return InvalidTime;
    }

    public void setInvalidTime(Date InvalidTime) {
        this.InvalidTime = InvalidTime;
    }
    public String getAreaIntention() {
        return AreaIntention;
    }

    public void setAreaIntention(String AreaIntention) {
        this.AreaIntention = AreaIntention;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public Date getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(Date NextFollowUpDate) {
        this.NextFollowUpDate = NextFollowUpDate;
    }
    public String getReportUserName() {
        return ReportUserName;
    }

    public void setReportUserName(String ReportUserName) {
        this.ReportUserName = ReportUserName;
    }
    public String getChannelCollaborator() {
        return ChannelCollaborator;
    }

    public void setChannelCollaborator(String ChannelCollaborator) {
        this.ChannelCollaborator = ChannelCollaborator;
    }
    public String getWXUserID() {
        return WXUserID;
    }

    public void setWXUserID(String WXUserID) {
        this.WXUserID = WXUserID;
    }
    public Integer getIsSelect() {
        return IsSelect;
    }

    public void setIsSelect(Integer IsSelect) {
        this.IsSelect = IsSelect;
    }
    public String getRuleID() {
        return RuleID;
    }

    public void setRuleID(String RuleID) {
        this.RuleID = RuleID;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getTheLatestFollowUpWay() {
        return TheLatestFollowUpWay;
    }

    public void setTheLatestFollowUpWay(String TheLatestFollowUpWay) {
        this.TheLatestFollowUpWay = TheLatestFollowUpWay;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getIntentProjectName() {
        return IntentProjectName;
    }

    public void setIntentProjectName(String IntentProjectName) {
        this.IntentProjectName = IntentProjectName;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public Date getConfirmTime() {
        return ConfirmTime;
    }

    public void setConfirmTime(Date ConfirmTime) {
        this.ConfirmTime = ConfirmTime;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getCommercial() {
        return Commercial;
    }

    public void setCommercial(String Commercial) {
        this.Commercial = Commercial;
    }
    public String getCustomerLevel() {
        return CustomerLevel;
    }

    public void setCustomerLevel(String CustomerLevel) {
        this.CustomerLevel = CustomerLevel;
    }
    public String getInvalidReason() {
        return InvalidReason;
    }

    public void setInvalidReason(String InvalidReason) {
        this.InvalidReason = InvalidReason;
    }
    public String getFeeIntention() {
        return FeeIntention;
    }

    public void setFeeIntention(String FeeIntention) {
        this.FeeIntention = FeeIntention;
    }
    public Integer getFollowupCount() {
        return FollowupCount;
    }

    public void setFollowupCount(Integer FollowupCount) {
        this.FollowupCount = FollowupCount;
    }
    public Integer getUserBehaviorID() {
        return UserBehaviorID;
    }

    public void setUserBehaviorID(Integer UserBehaviorID) {
        this.UserBehaviorID = UserBehaviorID;
    }
    public Integer getIsShare() {
        return IsShare;
    }

    public void setIsShare(Integer IsShare) {
        this.IsShare = IsShare;
    }
    public Date getTheLatestFollowUpDate() {
        return TheLatestFollowUpDate;
    }

    public void setTheLatestFollowUpDate(Date TheLatestFollowUpDate) {
        this.TheLatestFollowUpDate = TheLatestFollowUpDate;
    }
    public String getIntentProjectID() {
        return IntentProjectID;
    }

    public void setIntentProjectID(String IntentProjectID) {
        this.IntentProjectID = IntentProjectID;
    }
    public String getHouseIntention() {
        return HouseIntention;
    }

    public void setHouseIntention(String HouseIntention) {
        this.HouseIntention = HouseIntention;
    }
    public Integer getInvalidType() {
        return InvalidType;
    }

    public void setInvalidType(Integer InvalidType) {
        this.InvalidType = InvalidType;
    }
    public String getTheLatestFollowUpContent() {
        return TheLatestFollowUpContent;
    }

    public void setTheLatestFollowUpContent(String TheLatestFollowUpContent) {
        this.TheLatestFollowUpContent = TheLatestFollowUpContent;
    }
    public Date getComeOverdueTime() {
        return ComeOverdueTime;
    }

    public void setComeOverdueTime(Date ComeOverdueTime) {
        this.ComeOverdueTime = ComeOverdueTime;
    }
    public Integer getReportMethod() {
        return ReportMethod;
    }

    public void setReportMethod(Integer ReportMethod) {
        this.ReportMethod = ReportMethod;
    }
    public String getCognitiveChannel() {
        return CognitiveChannel;
    }

    public void setCognitiveChannel(String CognitiveChannel) {
        this.CognitiveChannel = CognitiveChannel;
    }
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public String getShareLogDetailID() {
        return ShareLogDetailID;
    }

    public void setShareLogDetailID(String ShareLogDetailID) {
        this.ShareLogDetailID = ShareLogDetailID;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getChannelUserID() {
        return ChannelUserID;
    }

    public void setChannelUserID(String ChannelUserID) {
        this.ChannelUserID = ChannelUserID;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getCustomerPotentialID() {
        return CustomerPotentialID;
    }

    public void setCustomerPotentialID(String CustomerPotentialID) {
        this.CustomerPotentialID = CustomerPotentialID;
    }
    public String getVisitIntention() {
        return VisitIntention;
    }

    public void setVisitIntention(String VisitIntention) {
        this.VisitIntention = VisitIntention;
    }
    public String getReportUserMobile() {
        return ReportUserMobile;
    }

    public void setReportUserMobile(String ReportUserMobile) {
        this.ReportUserMobile = ReportUserMobile;
    }

    @Override
    public String toString() {
        return "BClue{" +
        "SourceType=" + SourceType +
        ", Status=" + Status +
        ", TradeOverdueTime=" + TradeOverdueTime +
        ", EditeTime=" + EditeTime +
        ", Name=" + Name +
        ", CustomerRank=" + CustomerRank +
        ", ConfirmUserId=" + ConfirmUserId +
        ", ReportUserID=" + ReportUserID +
        ", ChannelTaskID=" + ChannelTaskID +
        ", PropertyIntention=" + PropertyIntention +
        ", ReportUserOrg=" + ReportUserOrg +
        ", CognitiveChannelSub=" + CognitiveChannelSub +
        ", InvalidTime=" + InvalidTime +
        ", AreaIntention=" + AreaIntention +
        ", Gender=" + Gender +
        ", NextFollowUpDate=" + NextFollowUpDate +
        ", ReportUserName=" + ReportUserName +
        ", ChannelCollaborator=" + ChannelCollaborator +
        ", WXUserID=" + WXUserID +
        ", IsSelect=" + IsSelect +
        ", RuleID=" + RuleID +
        ", LastName=" + LastName +
        ", TheLatestFollowUpWay=" + TheLatestFollowUpWay +
        ", CreateTime=" + CreateTime +
        ", id=" + id +
        ", IntentProjectName=" + IntentProjectName +
        ", IsDel=" + IsDel +
        ", Remark=" + Remark +
        ", ConfirmTime=" + ConfirmTime +
        ", Mobile=" + Mobile +
        ", Editor=" + Editor +
        ", Commercial=" + Commercial +
        ", CustomerLevel=" + CustomerLevel +
        ", InvalidReason=" + InvalidReason +
        ", FeeIntention=" + FeeIntention +
        ", FollowupCount=" + FollowupCount +
        ", UserBehaviorID=" + UserBehaviorID +
        ", IsShare=" + IsShare +
        ", TheLatestFollowUpDate=" + TheLatestFollowUpDate +
        ", IntentProjectID=" + IntentProjectID +
        ", HouseIntention=" + HouseIntention +
        ", InvalidType=" + InvalidType +
        ", TheLatestFollowUpContent=" + TheLatestFollowUpContent +
        ", ComeOverdueTime=" + ComeOverdueTime +
        ", ReportMethod=" + ReportMethod +
        ", CognitiveChannel=" + CognitiveChannel +
        ", AdviserGroupID=" + AdviserGroupID +
        ", ShareLogDetailID=" + ShareLogDetailID +
        ", FirstName=" + FirstName +
        ", ChannelUserID=" + ChannelUserID +
        ", Creator=" + Creator +
        ", CustomerPotentialID=" + CustomerPotentialID +
        ", VisitIntention=" + VisitIntention +
        ", ReportUserMobile=" + ReportUserMobile +
        "}";
    }
}
