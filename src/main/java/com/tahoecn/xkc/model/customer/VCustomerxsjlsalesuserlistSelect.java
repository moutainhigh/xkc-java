package com.tahoecn.xkc.model.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 1
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
@TableName("V_CustomerXSJLSalesUserList_Select")
@ApiModel(value="VCustomerxsjlsalesuserlistSelect对象", description="1")
public class VCustomerxsjlsalesuserlistSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("SaleUserMobil")
    private String SaleUserMobil;

    @TableField("HeadImg")
    private String HeadImg;

    @TableField("SaleGroupID")
    private String SaleGroupID;

    @TableField("SaleGroupName")
    private String SaleGroupName;

    @TableField("TotalCount")
    private Integer TotalCount;

    @TableField("UnsettledCount")
    private Integer UnsettledCount;

    @TableField("OverdueCount")
    private Integer OverdueCount;

    @TableField("RecycleCount")
    private Integer RecycleCount;

    @TableField("CloseRate")
    private Integer CloseRate;

    @TableField("IntegrityRungs")
    private Integer IntegrityRungs;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
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
    public String getSaleUserMobil() {
        return SaleUserMobil;
    }

    public void setSaleUserMobil(String SaleUserMobil) {
        this.SaleUserMobil = SaleUserMobil;
    }
    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String HeadImg) {
        this.HeadImg = HeadImg;
    }
    public String getSaleGroupID() {
        return SaleGroupID;
    }

    public void setSaleGroupID(String SaleGroupID) {
        this.SaleGroupID = SaleGroupID;
    }
    public String getSaleGroupName() {
        return SaleGroupName;
    }

    public void setSaleGroupName(String SaleGroupName) {
        this.SaleGroupName = SaleGroupName;
    }
    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer TotalCount) {
        this.TotalCount = TotalCount;
    }
    public Integer getUnsettledCount() {
        return UnsettledCount;
    }

    public void setUnsettledCount(Integer UnsettledCount) {
        this.UnsettledCount = UnsettledCount;
    }
    public Integer getOverdueCount() {
        return OverdueCount;
    }

    public void setOverdueCount(Integer OverdueCount) {
        this.OverdueCount = OverdueCount;
    }
    public Integer getRecycleCount() {
        return RecycleCount;
    }

    public void setRecycleCount(Integer RecycleCount) {
        this.RecycleCount = RecycleCount;
    }
    public Integer getCloseRate() {
        return CloseRate;
    }

    public void setCloseRate(Integer CloseRate) {
        this.CloseRate = CloseRate;
    }
    public Integer getIntegrityRungs() {
        return IntegrityRungs;
    }

    public void setIntegrityRungs(Integer IntegrityRungs) {
        this.IntegrityRungs = IntegrityRungs;
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
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return "VCustomerxsjlsalesuserlistSelect{" +
        "ProjectID=" + ProjectID +
        ", SaleUserID=" + SaleUserID +
        ", SaleUserName=" + SaleUserName +
        ", SaleUserMobil=" + SaleUserMobil +
        ", HeadImg=" + HeadImg +
        ", SaleGroupID=" + SaleGroupID +
        ", SaleGroupName=" + SaleGroupName +
        ", TotalCount=" + TotalCount +
        ", UnsettledCount=" + UnsettledCount +
        ", OverdueCount=" + OverdueCount +
        ", RecycleCount=" + RecycleCount +
        ", CloseRate=" + CloseRate +
        ", IntegrityRungs=" + IntegrityRungs +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
