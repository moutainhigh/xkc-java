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
 * @since 2019-07-23
 */
@TableName("B_Clue")
@ApiModel(value="BClue对象", description="")
public class BClue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("CustomerPotentialID")
    private String CustomerPotentialID;

    @TableField("Name")
    private String Name;

    @TableField("LastName")
    private String LastName;

    @TableField("FirstName")
    private String FirstName;

    @TableField("Gender")
    private String Gender;

    @TableField("Mobile")
    private String Mobile;

    @TableField("CognitiveChannel")
    private String CognitiveChannel;

    @TableField("CognitiveChannelSub")
    private String CognitiveChannelSub;

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

    @TableField("Remark")
    private String Remark;

    @ApiModelProperty(value = "1 自拓团队 2 代理团队 3 自然拓客 4 分销中介 5案场 6 历史数据 7call客系统")
    @TableField("SourceType")
    private String SourceType;

    @TableField("ReportUserID")
    private String ReportUserID;

    @TableField("ReportUserName")
    private String ReportUserName;

    @TableField("ReportUserMobile")
    private String ReportUserMobile;

    @TableField("ReportUserOrg")
    private String ReportUserOrg;

    @TableField("RuleID")
    private String RuleID;

    @TableField("ComeOverdueTime")
    private Date ComeOverdueTime;

    @TableField("TradeOverdueTime")
    private Date TradeOverdueTime;

    @TableField("UserBehaviorID")
    private Integer UserBehaviorID;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @TableField("IsSelect")
    private Integer IsSelect;

    @TableField("ConfirmTime")
    private Date ConfirmTime;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "1 已报备 ，2 跟进中，3 已过期，4 已放弃，5 已到访，6 已认筹，7 已认购，8 已签约")
    @TableField("Status")
    private Integer Status;

    @TableField("InvalidType")
    private Integer InvalidType;

    @TableField("InvalidTime")
    private Date InvalidTime;

    @TableField("InvalidReason")
    private String InvalidReason;

    @TableField("ConfirmUserId")
    private String ConfirmUserId;

    @TableField("NextFollowUpDate")
    private Date NextFollowUpDate;

    @TableField("ChannelUserID")
    private String ChannelUserID;

    @TableField("ChannelTaskID")
    private String ChannelTaskID;

    @TableField("ChannelCollaborator")
    private String ChannelCollaborator;

    @TableField("ReportMethod")
    private Integer ReportMethod;

    @TableField("WXUserID")
    private String WXUserID;

    @TableField("IsShare")
    private Integer IsShare;

    @TableField("ShareLogDetailID")
    private String ShareLogDetailID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCustomerPotentialID() {
        return CustomerPotentialID;
    }

    public void setCustomerPotentialID(String CustomerPotentialID) {
        this.CustomerPotentialID = CustomerPotentialID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getCognitiveChannel() {
        return CognitiveChannel;
    }

    public void setCognitiveChannel(String CognitiveChannel) {
        this.CognitiveChannel = CognitiveChannel;
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
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String SourceType) {
        this.SourceType = SourceType;
    }
    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String ReportUserID) {
        this.ReportUserID = ReportUserID;
    }
    public String getReportUserName() {
        return ReportUserName;
    }

    public void setReportUserName(String ReportUserName) {
        this.ReportUserName = ReportUserName;
    }
    public String getReportUserMobile() {
        return ReportUserMobile;
    }

    public void setReportUserMobile(String ReportUserMobile) {
        this.ReportUserMobile = ReportUserMobile;
    }
    public String getReportUserOrg() {
        return ReportUserOrg;
    }

    public void setReportUserOrg(String ReportUserOrg) {
        this.ReportUserOrg = ReportUserOrg;
    }
    public String getRuleID() {
        return RuleID;
    }

    public void setRuleID(String RuleID) {
        this.RuleID = RuleID;
    }
    public Date getComeOverdueTime() {
        return ComeOverdueTime;
    }

    public void setComeOverdueTime(Date ComeOverdueTime) {
        this.ComeOverdueTime = ComeOverdueTime;
    }
    public Date getTradeOverdueTime() {
        return TradeOverdueTime;
    }

    public void setTradeOverdueTime(Date TradeOverdueTime) {
        this.TradeOverdueTime = TradeOverdueTime;
    }
    public Integer getUserBehaviorID() {
        return UserBehaviorID;
    }

    public void setUserBehaviorID(Integer UserBehaviorID) {
        this.UserBehaviorID = UserBehaviorID;
    }
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public Integer getIsSelect() {
        return IsSelect;
    }

    public void setIsSelect(Integer IsSelect) {
        this.IsSelect = IsSelect;
    }
    public Date getConfirmTime() {
        return ConfirmTime;
    }

    public void setConfirmTime(Date ConfirmTime) {
        this.ConfirmTime = ConfirmTime;
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
    public Integer getInvalidType() {
        return InvalidType;
    }

    public void setInvalidType(Integer InvalidType) {
        this.InvalidType = InvalidType;
    }
    public Date getInvalidTime() {
        return InvalidTime;
    }

    public void setInvalidTime(Date InvalidTime) {
        this.InvalidTime = InvalidTime;
    }
    public String getInvalidReason() {
        return InvalidReason;
    }

    public void setInvalidReason(String InvalidReason) {
        this.InvalidReason = InvalidReason;
    }
    public String getConfirmUserId() {
        return ConfirmUserId;
    }

    public void setConfirmUserId(String ConfirmUserId) {
        this.ConfirmUserId = ConfirmUserId;
    }
    public Date getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(Date NextFollowUpDate) {
        this.NextFollowUpDate = NextFollowUpDate;
    }
    public String getChannelUserID() {
        return ChannelUserID;
    }

    public void setChannelUserID(String ChannelUserID) {
        this.ChannelUserID = ChannelUserID;
    }
    public String getChannelTaskID() {
        return ChannelTaskID;
    }

    public void setChannelTaskID(String ChannelTaskID) {
        this.ChannelTaskID = ChannelTaskID;
    }
    public String getChannelCollaborator() {
        return ChannelCollaborator;
    }

    public void setChannelCollaborator(String ChannelCollaborator) {
        this.ChannelCollaborator = ChannelCollaborator;
    }
    public Integer getReportMethod() {
        return ReportMethod;
    }

    public void setReportMethod(Integer ReportMethod) {
        this.ReportMethod = ReportMethod;
    }
    public String getWXUserID() {
        return WXUserID;
    }

    public void setWXUserID(String WXUserID) {
        this.WXUserID = WXUserID;
    }
    public Integer getIsShare() {
        return IsShare;
    }

    public void setIsShare(Integer IsShare) {
        this.IsShare = IsShare;
    }
    public String getShareLogDetailID() {
        return ShareLogDetailID;
    }

    public void setShareLogDetailID(String ShareLogDetailID) {
        this.ShareLogDetailID = ShareLogDetailID;
    }

    @Override
    public String toString() {
        return "BClue{" +
        "id=" + id +
        ", CustomerPotentialID=" + CustomerPotentialID +
        ", Name=" + Name +
        ", LastName=" + LastName +
        ", FirstName=" + FirstName +
        ", Gender=" + Gender +
        ", Mobile=" + Mobile +
        ", CognitiveChannel=" + CognitiveChannel +
        ", CognitiveChannelSub=" + CognitiveChannelSub +
        ", TheLatestFollowUpDate=" + TheLatestFollowUpDate +
        ", TheLatestFollowUpContent=" + TheLatestFollowUpContent +
        ", TheLatestFollowUpWay=" + TheLatestFollowUpWay +
        ", VisitsCount=" + VisitsCount +
        ", FollowupCount=" + FollowupCount +
        ", IntentProjectID=" + IntentProjectID +
        ", IntentProjectName=" + IntentProjectName +
        ", CustomerLevel=" + CustomerLevel +
        ", CustomerRank=" + CustomerRank +
        ", Remark=" + Remark +
        ", SourceType=" + SourceType +
        ", ReportUserID=" + ReportUserID +
        ", ReportUserName=" + ReportUserName +
        ", ReportUserMobile=" + ReportUserMobile +
        ", ReportUserOrg=" + ReportUserOrg +
        ", RuleID=" + RuleID +
        ", ComeOverdueTime=" + ComeOverdueTime +
        ", TradeOverdueTime=" + TradeOverdueTime +
        ", UserBehaviorID=" + UserBehaviorID +
        ", AdviserGroupID=" + AdviserGroupID +
        ", IsSelect=" + IsSelect +
        ", ConfirmTime=" + ConfirmTime +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", InvalidType=" + InvalidType +
        ", InvalidTime=" + InvalidTime +
        ", InvalidReason=" + InvalidReason +
        ", ConfirmUserId=" + ConfirmUserId +
        ", NextFollowUpDate=" + NextFollowUpDate +
        ", ChannelUserID=" + ChannelUserID +
        ", ChannelTaskID=" + ChannelTaskID +
        ", ChannelCollaborator=" + ChannelCollaborator +
        ", ReportMethod=" + ReportMethod +
        ", WXUserID=" + WXUserID +
        ", IsShare=" + IsShare +
        ", ShareLogDetailID=" + ShareLogDetailID +
        "}";
    }
}
