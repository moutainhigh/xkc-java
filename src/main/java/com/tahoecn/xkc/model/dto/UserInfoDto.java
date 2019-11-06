package com.tahoecn.xkc.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * Created by zhanghw on 2018/10/10.
 */
public class UserInfoDto implements Serializable {
    private String fdSid;
    private String fdName;
    /**
     * 登录用户名
     */
    private String fdUsername;
    /**
     * 登录密码
     */
    private String fdPassword;
    /**
     * 同步系统
     */
    @TableField("fd_syncSys")
    private String fdSyncsys;
    /**
     * 显示序列
     */
    private Integer fdOrder;
    /**
     * 用户类型
     */
    @TableField("fd_userType")
    private Integer fdUsertype;
    /**
     * 岗位
     */
    private String fdPosition;
    /**
     * 职等
     */
    private String fdPositionLevel;
    /**
     * 用户手机号
     */
    private String fdTel;
    /**
     * 用户工作电话
     */
    private String fdWorkPhone;
    /**
     * 邮箱
     */
    private String fdEmail;
    /**
     * 是否有效1可用
     */
    private Integer fdAvailable;
    /**
     * 数据是否锁定 -1不锁定
     */
    private Integer fdLock;
    /**
     * 所属部门id全路径
     */
    private String fdOrgIdTree;
    /**
     * 所属部门名称全路径
     */
    private String fdOrgNameTree;
    /**
     * 部门id
     */
    private String fdOrgId;
    /**
     * 部门名称
     */
    private String fdOrgName;
    /**
     * 入职时间
     */
    private Date fdEntryTime;
    /**
     * 离职时间
     */
    private Date fdQuitTime;
    /**
     * 账号自动停用时间
     */
    private Date fdAutoStopTime;
    /**
     * 是否删除 -1不删除
     */
    private Integer fdIsdelete;
    /**
     * 性别 1男-1女
     */
    private Integer fdGender;
    /**
     * 修改标记1user修改,2org修改
     */
    private Integer fdUpdateSign;
    /**
     * 泰信id
     */
    private String fdTahoeMessageSid;
    /**
     * 头图url
     */
    private String fdTopPhotoUrl;
    /**
     * 头图缩略图url
     */
    private String fdSmallPhotoUrl;
    /**
     * 工作职责
     */
    private String fdJobDuty;
    /**
     * 地址及邮编
     */
    private String fdAddressPost;
    /**
     * 证件类型 1为身份证，2军官证，3护照,4台胞证
     */
    private Integer fdIdcardType;
    /**
     * 证件号码
     */
    private String fdIdcard;
    /**
     * 微信号
     */
    private String fdWechatSn;
    /**
     * 微信号是否显示 1显示
     */
    private Integer fdIfWechatShow;

    public String getFdSid() {
        return fdSid;
    }

    public void setFdSid(String fdSid) {
        this.fdSid = fdSid;
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    public String getFdUsername() {
        return fdUsername;
    }

    public void setFdUsername(String fdUsername) {
        this.fdUsername = fdUsername;
    }

    public String getFdPassword() {
        return fdPassword;
    }

    public void setFdPassword(String fdPassword) {
        this.fdPassword = fdPassword;
    }

    public String getFdSyncsys() {
        return fdSyncsys;
    }

    public void setFdSyncsys(String fdSyncsys) {
        this.fdSyncsys = fdSyncsys;
    }

    public Integer getFdOrder() {
        return fdOrder;
    }

    public void setFdOrder(Integer fdOrder) {
        this.fdOrder = fdOrder;
    }

    public Integer getFdUsertype() {
        return fdUsertype;
    }

    public void setFdUsertype(Integer fdUsertype) {
        this.fdUsertype = fdUsertype;
    }

    public String getFdPosition() {
        return fdPosition;
    }

    public void setFdPosition(String fdPosition) {
        this.fdPosition = fdPosition;
    }

    public String getFdPositionLevel() {
        return fdPositionLevel;
    }

    public void setFdPositionLevel(String fdPositionLevel) {
        this.fdPositionLevel = fdPositionLevel;
    }

    public String getFdTel() {
        return fdTel;
    }

    public void setFdTel(String fdTel) {
        this.fdTel = fdTel;
    }

    public String getFdWorkPhone() {
        return fdWorkPhone;
    }

    public void setFdWorkPhone(String fdWorkPhone) {
        this.fdWorkPhone = fdWorkPhone;
    }

    public String getFdEmail() {
        return fdEmail;
    }

    public void setFdEmail(String fdEmail) {
        this.fdEmail = fdEmail;
    }

    public Integer getFdAvailable() {
        return fdAvailable;
    }

    public void setFdAvailable(Integer fdAvailable) {
        this.fdAvailable = fdAvailable;
    }

    public Integer getFdLock() {
        return fdLock;
    }

    public void setFdLock(Integer fdLock) {
        this.fdLock = fdLock;
    }

    public String getFdOrgIdTree() {
        return fdOrgIdTree;
    }

    public void setFdOrgIdTree(String fdOrgIdTree) {
        this.fdOrgIdTree = fdOrgIdTree;
    }

    public String getFdOrgNameTree() {
        return fdOrgNameTree;
    }

    public void setFdOrgNameTree(String fdOrgNameTree) {
        this.fdOrgNameTree = fdOrgNameTree;
    }

    public String getFdOrgId() {
        return fdOrgId;
    }

    public void setFdOrgId(String fdOrgId) {
        this.fdOrgId = fdOrgId;
    }

    public String getFdOrgName() {
        return fdOrgName;
    }

    public void setFdOrgName(String fdOrgName) {
        this.fdOrgName = fdOrgName;
    }

    public Date getFdEntryTime() {
        return fdEntryTime;
    }

    public void setFdEntryTime(Date fdEntryTime) {
        this.fdEntryTime = fdEntryTime;
    }

    public Date getFdQuitTime() {
        return fdQuitTime;
    }

    public void setFdQuitTime(Date fdQuitTime) {
        this.fdQuitTime = fdQuitTime;
    }

    public Date getFdAutoStopTime() {
        return fdAutoStopTime;
    }

    public void setFdAutoStopTime(Date fdAutoStopTime) {
        this.fdAutoStopTime = fdAutoStopTime;
    }

    public Integer getFdIsdelete() {
        return fdIsdelete;
    }

    public void setFdIsdelete(Integer fdIsdelete) {
        this.fdIsdelete = fdIsdelete;
    }

    public Integer getFdGender() {
        return fdGender;
    }

    public void setFdGender(Integer fdGender) {
        this.fdGender = fdGender;
    }

    public Integer getFdUpdateSign() {
        return fdUpdateSign;
    }

    public void setFdUpdateSign(Integer fdUpdateSign) {
        this.fdUpdateSign = fdUpdateSign;
    }

    public String getFdTahoeMessageSid() {
        return fdTahoeMessageSid;
    }

    public void setFdTahoeMessageSid(String fdTahoeMessageSid) {
        this.fdTahoeMessageSid = fdTahoeMessageSid;
    }

    public String getFdTopPhotoUrl() {
        return fdTopPhotoUrl;
    }

    public void setFdTopPhotoUrl(String fdTopPhotoUrl) {
        this.fdTopPhotoUrl = fdTopPhotoUrl;
    }

    public String getFdSmallPhotoUrl() {
        return fdSmallPhotoUrl;
    }

    public void setFdSmallPhotoUrl(String fdSmallPhotoUrl) {
        this.fdSmallPhotoUrl = fdSmallPhotoUrl;
    }

    public String getFdJobDuty() {
        return fdJobDuty;
    }

    public void setFdJobDuty(String fdJobDuty) {
        this.fdJobDuty = fdJobDuty;
    }

    public String getFdAddressPost() {
        return fdAddressPost;
    }

    public void setFdAddressPost(String fdAddressPost) {
        this.fdAddressPost = fdAddressPost;
    }

    public Integer getFdIdcardType() {
        return fdIdcardType;
    }

    public void setFdIdcardType(Integer fdIdcardType) {
        this.fdIdcardType = fdIdcardType;
    }

    public String getFdIdcard() {
        return fdIdcard;
    }

    public void setFdIdcard(String fdIdcard) {
        this.fdIdcard = fdIdcard;
    }

    public String getFdWechatSn() {
        return fdWechatSn;
    }

    public void setFdWechatSn(String fdWechatSn) {
        this.fdWechatSn = fdWechatSn;
    }

    public Integer getFdIfWechatShow() {
        return fdIfWechatShow;
    }

    public void setFdIfWechatShow(Integer fdIfWechatShow) {
        this.fdIfWechatShow = fdIfWechatShow;
    }
}
