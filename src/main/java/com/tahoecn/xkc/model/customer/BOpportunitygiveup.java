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
 * @since 2019-07-15
 */
@TableName("B_OpportunityGiveUp")
@ApiModel(value="BOpportunitygiveup对象", description="")
public class BOpportunitygiveup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("Reason")
    private String Reason;

    @TableField("Approver")
    private String Approver;

    @ApiModelProperty(value = "0 未审批 1 通过 2 未通过")
    @TableField("ApprovalStatus")
    private Integer ApprovalStatus;

    @TableField("ApprovalDate")
    private Date ApprovalDate;

    @TableField("OrgID")
    private String OrgID;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @ApiModelProperty(value = "0 否 1是")
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
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
    public String getApprover() {
        return Approver;
    }

    public void setApprover(String Approver) {
        this.Approver = Approver;
    }
    public Integer getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(Integer ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }
    public Date getApprovalDate() {
        return ApprovalDate;
    }

    public void setApprovalDate(Date ApprovalDate) {
        this.ApprovalDate = ApprovalDate;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
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

    @Override
    public String toString() {
        return "BOpportunitygiveup{" +
        "id=" + id +
        ", OpportunityID=" + OpportunityID +
        ", CustomerName=" + CustomerName +
        ", CustomerMobile=" + CustomerMobile +
        ", Reason=" + Reason +
        ", Approver=" + Approver +
        ", ApprovalStatus=" + ApprovalStatus +
        ", ApprovalDate=" + ApprovalDate +
        ", OrgID=" + OrgID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
