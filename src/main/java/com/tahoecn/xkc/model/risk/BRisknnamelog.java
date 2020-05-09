package com.tahoecn.xkc.model.risk;

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
 * @since 2020-05-06
 */
@TableName("B_RiskNnameLog")
@ApiModel(value="BRisknnamelog对象", description="")
public class BRisknnamelog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("CustomerOldName")
    private String CustomerOldName;

    @TableField("CustomerNewName")
    private String CustomerNewName;

    @TableField("ClueId")
    private String ClueId;

    @TableField("OpportunityId")
    private String OpportunityId;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @TableField("AdviserGroupName")
    private String AdviserGroupName;

    @TableField("ReportUserID")
    private String ReportUserID;

    @TableField("ReportUserName")
    private String ReportUserName;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public String getCustomerOldName() {
        return CustomerOldName;
    }

    public void setCustomerOldName(String CustomerOldName) {
        this.CustomerOldName = CustomerOldName;
    }
    public String getCustomerNewName() {
        return CustomerNewName;
    }

    public void setCustomerNewName(String CustomerNewName) {
        this.CustomerNewName = CustomerNewName;
    }
    public String getClueId() {
        return ClueId;
    }

    public void setClueId(String ClueId) {
        this.ClueId = ClueId;
    }
    public String getOpportunityId() {
        return OpportunityId;
    }

    public void setOpportunityId(String OpportunityId) {
        this.OpportunityId = OpportunityId;
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
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public String getAdviserGroupName() {
        return AdviserGroupName;
    }

    public void setAdviserGroupName(String AdviserGroupName) {
        this.AdviserGroupName = AdviserGroupName;
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
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return "BRisknnamelog{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", CustomerMobile=" + CustomerMobile +
        ", CustomerOldName=" + CustomerOldName +
        ", CustomerNewName=" + CustomerNewName +
        ", ClueId=" + ClueId +
        ", OpportunityId=" + OpportunityId +
        ", SaleUserID=" + SaleUserID +
        ", SaleUserName=" + SaleUserName +
        ", AdviserGroupID=" + AdviserGroupID +
        ", AdviserGroupName=" + AdviserGroupName +
        ", ReportUserID=" + ReportUserID +
        ", ReportUserName=" + ReportUserName +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
