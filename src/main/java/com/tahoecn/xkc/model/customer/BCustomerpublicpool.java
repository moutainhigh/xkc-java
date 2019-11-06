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
 * @since 2019-07-11
 */
@TableName("B_CustomerPublicPool")
@ApiModel(value="BCustomerpublicpool对象", description="")
public class BCustomerpublicpool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ClueID")
    private String ClueID;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("CustomerPotentialID")
    private String CustomerPotentialID;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("Name")
    private String Name;

    @TableField("Gender")
    private String Gender;

    @TableField("Mobile")
    private String Mobile;

    @TableField("CustomerLevel")
    private String CustomerLevel;

    @TableField("SourceType")
    private String SourceType;

    @ApiModelProperty(value = "1 报备过期 2 跟进过期 3 到访过期 4 丢弃 5 顾问离职 ")
    @TableField("Reason")
    private String Reason;

    @TableField("OperationTime")
    private Date OperationTime;

    @TableField("TheFirstVisitDate")
    private Date TheFirstVisitDate;

    @TableField("ClueSource")
    private String ClueSource;

    @TableField("ReportUserID")
    private String ReportUserID;

    @TableField("ReportUserName")
    private String ReportUserName;

    @TableField("ReportTime")
    private Date ReportTime;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("SaleTeamID")
    private String SaleTeamID;

    @TableField("SaleTeamName")
    private String SaleTeamName;

    @TableField("ExpireTag")
    private String ExpireTag;

    @TableField("CatchTime")
    private Date CatchTime;

    @TableField("CatchWay")
    private String CatchWay;

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
    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
    }
    public String getCustomerPotentialID() {
        return CustomerPotentialID;
    }

    public void setCustomerPotentialID(String CustomerPotentialID) {
        this.CustomerPotentialID = CustomerPotentialID;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
    public String getCustomerLevel() {
        return CustomerLevel;
    }

    public void setCustomerLevel(String CustomerLevel) {
        this.CustomerLevel = CustomerLevel;
    }
    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String SourceType) {
        this.SourceType = SourceType;
    }
    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
    public Date getOperationTime() {
        return OperationTime;
    }

    public void setOperationTime(Date OperationTime) {
        this.OperationTime = OperationTime;
    }
    public Date getTheFirstVisitDate() {
        return TheFirstVisitDate;
    }

    public void setTheFirstVisitDate(Date TheFirstVisitDate) {
        this.TheFirstVisitDate = TheFirstVisitDate;
    }
    public String getClueSource() {
        return ClueSource;
    }

    public void setClueSource(String ClueSource) {
        this.ClueSource = ClueSource;
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
    public Date getReportTime() {
        return ReportTime;
    }

    public void setReportTime(Date ReportTime) {
        this.ReportTime = ReportTime;
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
    public String getSaleTeamID() {
        return SaleTeamID;
    }

    public void setSaleTeamID(String SaleTeamID) {
        this.SaleTeamID = SaleTeamID;
    }
    public String getSaleTeamName() {
        return SaleTeamName;
    }

    public void setSaleTeamName(String SaleTeamName) {
        this.SaleTeamName = SaleTeamName;
    }
    public String getExpireTag() {
        return ExpireTag;
    }

    public void setExpireTag(String ExpireTag) {
        this.ExpireTag = ExpireTag;
    }
    public Date getCatchTime() {
        return CatchTime;
    }

    public void setCatchTime(Date CatchTime) {
        this.CatchTime = CatchTime;
    }
    public String getCatchWay() {
        return CatchWay;
    }

    public void setCatchWay(String CatchWay) {
        this.CatchWay = CatchWay;
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
        return "BCustomerpublicpool{" +
        "id=" + id +
        ", ClueID=" + ClueID +
        ", OpportunityID=" + OpportunityID +
        ", CustomerPotentialID=" + CustomerPotentialID +
        ", CustomerID=" + CustomerID +
        ", Name=" + Name +
        ", Gender=" + Gender +
        ", Mobile=" + Mobile +
        ", CustomerLevel=" + CustomerLevel +
        ", SourceType=" + SourceType +
        ", Reason=" + Reason +
        ", OperationTime=" + OperationTime +
        ", TheFirstVisitDate=" + TheFirstVisitDate +
        ", ClueSource=" + ClueSource +
        ", ReportUserID=" + ReportUserID +
        ", ReportUserName=" + ReportUserName +
        ", ReportTime=" + ReportTime +
        ", SaleUserID=" + SaleUserID +
        ", SaleUserName=" + SaleUserName +
        ", SaleTeamID=" + SaleTeamID +
        ", SaleTeamName=" + SaleTeamName +
        ", ExpireTag=" + ExpireTag +
        ", CatchTime=" + CatchTime +
        ", CatchWay=" + CatchWay +
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
