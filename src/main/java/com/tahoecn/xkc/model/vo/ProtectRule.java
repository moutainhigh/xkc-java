package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProtectRule implements Serializable {

    private static final long serialVersionUID = 1L;
    /// 是否启用到访保护期
    private int IsProtectVisit;
    /// 到访保护期保护时长
    private int ProtectVisitTime;
    /// 是否启用成交保护
    private int IsProtect;
    /// 成交保护期保护时长
    private int ProtectTime;
    /// 是否启用两段式保护期模式
    private int IsSelect;
    /// 成交保护的动作节点。1为签约，2为认购，3为认筹
    private int UserBehaviorID;
    /// 是否防截客
    private int IsPreIntercept;
    /// 防截客周期
    private int PreInterceptTime;
    /// 是否永久有效，0表示否，1表示是
    private int IsPermanent;
	public int getIsProtectVisit() {
		return IsProtectVisit;
	}
	public void setIsProtectVisit(int isProtectVisit) {
		IsProtectVisit = isProtectVisit;
	}
	public int getProtectVisitTime() {
		return ProtectVisitTime;
	}
	public void setProtectVisitTime(int protectVisitTime) {
		ProtectVisitTime = protectVisitTime;
	}
	public int getIsProtect() {
		return IsProtect;
	}
	public void setIsProtect(int isProtect) {
		IsProtect = isProtect;
	}
	public int getProtectTime() {
		return ProtectTime;
	}
	public void setProtectTime(int protectTime) {
		ProtectTime = protectTime;
	}
	public int getIsSelect() {
		return IsSelect;
	}
	public void setIsSelect(int isSelect) {
		IsSelect = isSelect;
	}
	public int getUserBehaviorID() {
		return UserBehaviorID;
	}
	public void setUserBehaviorID(int userBehaviorID) {
		UserBehaviorID = userBehaviorID;
	}
	public int getIsPreIntercept() {
		return IsPreIntercept;
	}
	public void setIsPreIntercept(int isPreIntercept) {
		IsPreIntercept = isPreIntercept;
	}
	public int getPreInterceptTime() {
		return PreInterceptTime;
	}
	public void setPreInterceptTime(int preInterceptTime) {
		PreInterceptTime = preInterceptTime;
	}
	public int getIsPermanent() {
		return IsPermanent;
	}
	public void setIsPermanent(int isPermanent) {
		IsPermanent = isPermanent;
	}
}
