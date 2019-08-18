package com.tahoecn.xkc.model.rule;

import com.baomidou.mybatisplus.annotation.TableName;

import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@TableName("B_ClueRule")
@ApiModel(value="BCluerule对象", description="")
public class BCluerule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1.仅允许报备新客户 0.允许报备新客户或不在保护期的老客户")
    @TableField("IsOnlyAllowNew")
    private Integer IsOnlyAllowNew;

    @ApiModelProperty(value = "有效性验证1.带访后现场确认 2.报备后实时验证")
    @TableField("ValidationMode")
    private Integer ValidationMode;

    @ApiModelProperty(value = "0.推荐 1.自有 2.分销中介")
    @TableField("ProtectSource")
    private Integer ProtectSource;

    @TableField("Editor")
    private String Editor;

    @TableField("IsPreIntercept")
    private Integer IsPreIntercept;

    @TableField("FollowUpOverdueDays")
    private Integer FollowUpOverdueDays;

    @TableField("ID")
    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField("ProtectVisitTime")
    private Integer ProtectVisitTime;

    @TableField("RuleName")
    private String RuleName;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("VersionStartTime")
    private Date VersionStartTime;

    @TableField("Status")
    private Integer Status;

    @TableField("TakeEffectTime")
    private Date TakeEffectTime;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("RuleType")
    private Integer RuleType;

    @TableField("ProtectTime")
    private Integer ProtectTime;

    @TableField("OldOwnerLimit")
    private Integer OldOwnerLimit;

    @TableField("PreInterceptTime")
    private Integer PreInterceptTime;

    @TableField("ProtectVisitRemindTime")
    private Integer ProtectVisitRemindTime;

    @TableField("ProtectRemindTime")
    private Integer ProtectRemindTime;

    @TableField("VersionEndTime")
    private Date VersionEndTime;

    @TableField("OverdueTime")
    private Integer OverdueTime;

    @TableField("Creator")
    private String Creator;

    @TableField("CalMode")
    private Integer CalMode;

    @TableField("IsPermanent")
    private Integer IsPermanent;

    @TableField("IsProtect")
    private Integer IsProtect;

    @TableField("ProtectTypeID")
    private Integer ProtectTypeID;

    @TableField("IsSelect")
    private Integer IsSelect;

    @TableField("UserBehaviorID")
    private String UserBehaviorID;

    @TableField("IsProtectVisit")
    private Integer IsProtectVisit;

    @TableField("extendProDays")
    private Integer extendProDays;

    @TableField("extendProEndDate")
    private Date extendProEndDate;



    @TableField("extendFollowupProDays")
    private Integer extendFollowupProDays;

    @TableField("extendFollowupProEndDate")
    private Date extendFollowupProEndDate;


    @TableField("extendArriveProDays")
    private Integer extendArriveProDays;

    @TableField("extendArriveProEndDate")
    private Date extendArriveProEndDate;


    @TableField("extendSigningProDays")
    private Integer extendSigningProDays;

    @TableField("extendSigningProEndDate")
    private Date extendSigningProEndDate;

    @TableField("extendProOpt")
    private Integer extendProOpt;

    @TableField("extendArriSignProOpt")
    private Integer extendArriSignProOpt;

    @TableField(exist = false)
    private List<BClueruleGourpVo> clueruleGourpVoList;

    @TableField(exist = false)
    private String UserID;

    public Integer getIsOnlyAllowNew() {
        return IsOnlyAllowNew;
    }

    public void setIsOnlyAllowNew(Integer IsOnlyAllowNew) {
        this.IsOnlyAllowNew = IsOnlyAllowNew;
    }
    public Integer getValidationMode() {
        return ValidationMode;
    }

    public void setValidationMode(Integer ValidationMode) {
        this.ValidationMode = ValidationMode;
    }
    public Integer getProtectSource() {
        return ProtectSource;
    }

    public void setProtectSource(Integer ProtectSource) {
        this.ProtectSource = ProtectSource;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getIsPreIntercept() {
        return IsPreIntercept;
    }

    public void setIsPreIntercept(Integer IsPreIntercept) {
        this.IsPreIntercept = IsPreIntercept;
    }
    public Integer getFollowUpOverdueDays() {
        return FollowUpOverdueDays;
    }

    public void setFollowUpOverdueDays(Integer FollowUpOverdueDays) {
        this.FollowUpOverdueDays = FollowUpOverdueDays;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getProtectVisitTime() {
        return ProtectVisitTime;
    }

    public void setProtectVisitTime(Integer ProtectVisitTime) {
        this.ProtectVisitTime = ProtectVisitTime;
    }
    public String getRuleName() {
        return RuleName;
    }

    public void setRuleName(String RuleName) {
        this.RuleName = RuleName;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Date getVersionStartTime() {
        return VersionStartTime;
    }

    public void setVersionStartTime(Date VersionStartTime) {
        this.VersionStartTime = VersionStartTime;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Date getTakeEffectTime() {
        return TakeEffectTime;
    }

    public void setTakeEffectTime(Date TakeEffectTime) {
        this.TakeEffectTime = TakeEffectTime;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public Integer getRuleType() {
        return RuleType;
    }

    public void setRuleType(Integer RuleType) {
        this.RuleType = RuleType;
    }
    public Integer getProtectTime() {
        return ProtectTime;
    }

    public void setProtectTime(Integer ProtectTime) {
        this.ProtectTime = ProtectTime;
    }
    public Integer getOldOwnerLimit() {
        return OldOwnerLimit;
    }

    public void setOldOwnerLimit(Integer OldOwnerLimit) {
        this.OldOwnerLimit = OldOwnerLimit;
    }
    public Integer getPreInterceptTime() {
        return PreInterceptTime;
    }

    public void setPreInterceptTime(Integer PreInterceptTime) {
        this.PreInterceptTime = PreInterceptTime;
    }
    public Integer getProtectVisitRemindTime() {
        return ProtectVisitRemindTime;
    }

    public void setProtectVisitRemindTime(Integer ProtectVisitRemindTime) {
        this.ProtectVisitRemindTime = ProtectVisitRemindTime;
    }
    public Integer getProtectRemindTime() {
        return ProtectRemindTime;
    }

    public void setProtectRemindTime(Integer ProtectRemindTime) {
        this.ProtectRemindTime = ProtectRemindTime;
    }
    public Date getVersionEndTime() {
        return VersionEndTime;
    }

    public void setVersionEndTime(Date VersionEndTime) {
        this.VersionEndTime = VersionEndTime;
    }
    public Integer getOverdueTime() {
        return OverdueTime;
    }

    public void setOverdueTime(Integer OverdueTime) {
        this.OverdueTime = OverdueTime;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Integer getCalMode() {
        return CalMode;
    }

    public void setCalMode(Integer CalMode) {
        this.CalMode = CalMode;
    }
    public Integer getIsPermanent() {
        return IsPermanent;
    }

    public void setIsPermanent(Integer IsPermanent) {
        this.IsPermanent = IsPermanent;
    }
    public Integer getIsProtect() {
        return IsProtect;
    }

    public void setIsProtect(Integer IsProtect) {
        this.IsProtect = IsProtect;
    }
    public Integer getProtectTypeID() {
        return ProtectTypeID;
    }

    public void setProtectTypeID(Integer ProtectTypeID) {
        this.ProtectTypeID = ProtectTypeID;
    }
    public Integer getIsSelect() {
        return IsSelect;
    }

    public void setIsSelect(Integer IsSelect) {
        this.IsSelect = IsSelect;
    }
    public String getUserBehaviorID() {
        return UserBehaviorID;
    }

    public void setUserBehaviorID(String UserBehaviorID) {
        this.UserBehaviorID = UserBehaviorID;
    }
    public Integer getIsProtectVisit() {
        return IsProtectVisit;
    }

    public void setIsProtectVisit(Integer IsProtectVisit) {
        this.IsProtectVisit = IsProtectVisit;
    }

    public List<BClueruleGourpVo> getClueruleGourpVoList() {
        return clueruleGourpVoList;
    }

    public void setClueruleGourpVoList(List<BClueruleGourpVo> clueruleGourpVoList) {
        this.clueruleGourpVoList = clueruleGourpVoList;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Integer getExtendProDays() {
        return extendProDays;
    }

    public void setExtendProDays(Integer extendProDays) {
        this.extendProDays = extendProDays;
    }

    public Date getExtendProEndDate() {
        return extendProEndDate;
    }

    public void setExtendProEndDate(Date extendProEndDate) {
        this.extendProEndDate = extendProEndDate;
    }

    public Integer getExtendFollowupProDays() {
        return extendFollowupProDays;
    }

    public void setExtendFollowupProDays(Integer extendFollowupProDays) {
        this.extendFollowupProDays = extendFollowupProDays;
    }

    public Date getExtendFollowupProEndDate() {
        return extendFollowupProEndDate;
    }

    public void setExtendFollowupProEndDate(Date extendFollowupProEndDate) {
        this.extendFollowupProEndDate = extendFollowupProEndDate;
    }

    public Integer getExtendArriveProDays() {
        return extendArriveProDays;
    }

    public void setExtendArriveProDays(Integer extendArriveProDays) {
        this.extendArriveProDays = extendArriveProDays;
    }

    public Date getExtendArriveProEndDate() {
        return extendArriveProEndDate;
    }

    public void setExtendArriveProEndDate(Date extendArriveProEndDate) {
        this.extendArriveProEndDate = extendArriveProEndDate;
    }

    public Integer getExtendSigningProDays() {
        return extendSigningProDays;
    }

    public void setExtendSigningProDays(Integer extendSigningProDays) {
        this.extendSigningProDays = extendSigningProDays;
    }

    public Date getExtendSigningProEndDate() {
        return extendSigningProEndDate;
    }

    public void setExtendSigningProEndDate(Date extendSigningProEndDate) {
        this.extendSigningProEndDate = extendSigningProEndDate;
    }

    public Integer getExtendProOpt() {
        return extendProOpt;
    }

    public void setExtendProOpt(Integer extendProOpt) {
        this.extendProOpt = extendProOpt;
    }

    public Integer getExtendArriSignProOpt() {
        return extendArriSignProOpt;
    }

    public void setExtendArriSignProOpt(Integer extendArriSignProOpt) {
        this.extendArriSignProOpt = extendArriSignProOpt;
    }

    @Override
    public String toString() {
        return "BCluerule{" +
        "IsOnlyAllowNew=" + IsOnlyAllowNew +
        ", ValidationMode=" + ValidationMode +
        ", ProtectSource=" + ProtectSource +
        ", Editor=" + Editor +
        ", IsPreIntercept=" + IsPreIntercept +
        ", FollowUpOverdueDays=" + FollowUpOverdueDays +
        ", id=" + id +
        ", ProtectVisitTime=" + ProtectVisitTime +
        ", RuleName=" + RuleName +
        ", IsDel=" + IsDel +
        ", VersionStartTime=" + VersionStartTime +
        ", Status=" + Status +
        ", TakeEffectTime=" + TakeEffectTime +
        ", ProjectID=" + ProjectID +
        ", RuleType=" + RuleType +
        ", ProtectTime=" + ProtectTime +
        ", OldOwnerLimit=" + OldOwnerLimit +
        ", PreInterceptTime=" + PreInterceptTime +
        ", ProtectVisitRemindTime=" + ProtectVisitRemindTime +
        ", ProtectRemindTime=" + ProtectRemindTime +
        ", VersionEndTime=" + VersionEndTime +
        ", OverdueTime=" + OverdueTime +
        ", Creator=" + Creator +
        ", CalMode=" + CalMode +
        ", IsPermanent=" + IsPermanent +
        ", IsProtect=" + IsProtect +
        ", ProtectTypeID=" + ProtectTypeID +
        ", IsSelect=" + IsSelect +
        ", UserBehaviorID=" + UserBehaviorID +
        ", IsProtectVisit=" + IsProtectVisit +
        "}";
    }
}
