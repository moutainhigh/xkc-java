package com.tahoecn.xkc.model.risk;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
@TableName("B_RiskConfig")
@ApiModel(value = "BRiskconfig对象", description = "")
public class BRiskconfig implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("Type")
    private Integer Type;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("IsProtectCustomer")
    private Integer IsProtectCustomer;

    @TableField("ProtectTime")
    private Integer ProtectTime;

    @TableField("IsFace")
    private Integer IsFace;

    @TableField("IsSearchMobile")
    private Integer IsSearchMobile;

    @TableField("IsEditName")
    private Integer IsEditName;

    @TableField("EditNameType")
    private Integer EditNameType;

    @TableField("EditNameCount")
    private Integer EditNameCount;

    @TableField("IsJointName")
    private Integer IsJointName;

    @TableField("JointNameType")
    private Integer JointNameType;

    @TableField("IsShortDeal")
    private Integer IsShortDeal;

    @TableField("ShortDealTime")
    private Integer ShortDealTime;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }

    public Integer getIsProtectCustomer() {
        return IsProtectCustomer;
    }

    public void setIsProtectCustomer(Integer IsProtectCustomer) {
        this.IsProtectCustomer = IsProtectCustomer;
    }

    public Integer getProtectTime() {
        return ProtectTime;
    }

    public void setProtectTime(Integer ProtectTime) {
        this.ProtectTime = ProtectTime;
    }

    public Integer getIsFace() {
        return IsFace;
    }

    public void setIsFace(Integer IsFace) {
        this.IsFace = IsFace;
    }

    public Integer getIsSearchMobile() {
        return IsSearchMobile;
    }

    public void setIsSearchMobile(Integer IsSearchMobile) {
        this.IsSearchMobile = IsSearchMobile;
    }

    public Integer getIsEditName() {
        return IsEditName;
    }

    public void setIsEditName(Integer IsEditName) {
        this.IsEditName = IsEditName;
    }

    public Integer getEditNameType() {
        return EditNameType;
    }

    public void setEditNameType(Integer EditNameType) {
        this.EditNameType = EditNameType;
    }

    public Integer getEditNameCount() {
        return EditNameCount;
    }

    public void setEditNameCount(Integer EditNameCount) {
        this.EditNameCount = EditNameCount;
    }

    public Integer getIsJointName() {
        return IsJointName;
    }

    public void setIsJointName(Integer IsJointName) {
        this.IsJointName = IsJointName;
    }

    public Integer getJointNameType() {
        return JointNameType;
    }

    public void setJointNameType(Integer JointNameType) {
        this.JointNameType = JointNameType;
    }

    public Integer getIsShortDeal() {
        return IsShortDeal;
    }

    public void setIsShortDeal(Integer IsShortDeal) {
        this.IsShortDeal = IsShortDeal;
    }

    public Integer getShortDealTime() {
        return ShortDealTime;
    }

    public void setShortDealTime(Integer ShortDealTime) {
        this.ShortDealTime = ShortDealTime;
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

    @Override
    public String toString() {
        return "BRiskconfig{" +
                "id=" + id +
                ", Type=" + Type +
                ", ProjectID=" + ProjectID +
                ", IsProtectCustomer=" + IsProtectCustomer +
                ", ProtectTime=" + ProtectTime +
                ", IsFace=" + IsFace +
                ", IsSearchMobile=" + IsSearchMobile +
                ", IsEditName=" + IsEditName +
                ", EditNameType=" + EditNameType +
                ", EditNameCount=" + EditNameCount +
                ", IsJointName=" + IsJointName +
                ", JointNameType=" + JointNameType +
                ", IsShortDeal=" + IsShortDeal +
                ", ShortDealTime=" + ShortDealTime +
                ", Creator=" + Creator +
                ", CreateTime=" + CreateTime +
                ", IsDel=" + IsDel +
                ", Status=" + Status +
                "}";
    }

    @Override
    public BRiskconfig clone() throws CloneNotSupportedException {
        return (BRiskconfig) super.clone();
    }

}
