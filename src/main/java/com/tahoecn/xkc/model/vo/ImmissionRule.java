package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ImmissionRule implements Serializable {

    private static final long serialVersionUID = 1L;
    /// 是否仅允许报备老客户
    private int IsOnlyAllowNew;
    /// 0表示超过N天未跟进，1表示超过N天未到访
    private int ProtectTypeID;
    /// 允许报备老客户且超过N天
    private int OverdueTime;
    /// 有效性验证模式，1表示带访后现场确认，2表示报备后实时确认
    private int ValidationMode;
    /// 老业主限制，0表示不允许报备集团老业主，1表示允许报备非本项目老业主
    private int OldOwnerLimit;
	public int getIsOnlyAllowNew() {
		return IsOnlyAllowNew;
	}
	public void setIsOnlyAllowNew(int isOnlyAllowNew) {
		IsOnlyAllowNew = isOnlyAllowNew;
	}
	public int getProtectTypeID() {
		return ProtectTypeID;
	}
	public void setProtectTypeID(int protectTypeID) {
		ProtectTypeID = protectTypeID;
	}
	public int getOverdueTime() {
		return OverdueTime;
	}
	public void setOverdueTime(int overdueTime) {
		OverdueTime = overdueTime;
	}
	public int getValidationMode() {
		return ValidationMode;
	}
	public void setValidationMode(int validationMode) {
		ValidationMode = validationMode;
	}
	public int getOldOwnerLimit() {
		return OldOwnerLimit;
	}
	public void setOldOwnerLimit(int oldOwnerLimit) {
		OldOwnerLimit = oldOwnerLimit;
	}
}
