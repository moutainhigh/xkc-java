package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BClueruleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer IsOnlyAllowNew;

    private Integer ValidationMode;

    private Integer ProtectSource;

    private String Editor;

    private Integer IsPreIntercept;

    private Integer FollowUpOverdueDays;

    private String id;

    private Integer ProtectVisitTime;

    private String RuleName;

    private Integer IsDel;

    private Date VersionStartTime;

    private Integer Status;

    private Date TakeEffectTime;

    private String ProjectID;

    private Integer RuleType;

    private Integer ProtectTime;

    private Integer OldOwnerLimit;

    private Integer PreInterceptTime;

    private Integer ProtectVisitRemindTime;

    private Integer ProtectRemindTime;

    private Date VersionEndTime;

    private Integer OverdueTime;

    private String Creator;

    private Integer CalMode;

    private Integer IsPermanent;

    private Integer IsProtect;

    private Integer ProtectTypeID;

    private Integer IsSelect;

    private String UserBehaviorID;

    private Integer IsProtectVisit;

    private List<BClueruleGourpVo> clueruleGourpVoList;

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
