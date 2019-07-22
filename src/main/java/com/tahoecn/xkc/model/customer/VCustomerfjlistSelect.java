package com.tahoecn.xkc.model.customer;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * 2
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@TableName("V_CustomerFJList_Select")
@ApiModel(value="VCustomerfjlistSelect对象", description="2")
public class VCustomerfjlistSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("Assignor")
    private String Assignor;

    @TableField("ReportID")
    private String ReportID;

    @TableField("ReportName")
    private String ReportName;

    @TableField("ReportTime")
    private String ReportTime;

    @TableField("VisitingTime")
    private String VisitingTime;

    @TableField("AttendNum")
    private String AttendNum;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("CustomerSource")
    private String CustomerSource;

    @TableField("CustomerType")
    private String CustomerType;

    @TableField("CustomerMode")
    private String CustomerMode;

    @TableField("AdviserID")
    private String AdviserID;

    @TableField("AdviserName")
    private String AdviserName;

    @TableField("AdviserMobile")
    private String AdviserMobile;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("IsDay")
    private Integer IsDay;

    private Long num;

    @JsonProperty("ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @JsonProperty("CustomerID")
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    
    @JsonProperty("Assignor")
    public String getAssignor() {
        return Assignor;
    }

    public void setAssignor(String Assignor) {
        this.Assignor = Assignor;
    }
    
    @JsonProperty("ReportID")
    public String getReportID() {
        return ReportID;
    }

    public void setReportID(String ReportID) {
        this.ReportID = ReportID;
    }
    
    @JsonProperty("ReportName")
    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String ReportName) {
        this.ReportName = ReportName;
    }
    
    @JsonProperty("ReportTime")
    public String getReportTime() {
        return ReportTime;
    }

    public void setReportTime(String ReportTime) {
        this.ReportTime = ReportTime;
    }
    
    @JsonProperty("VisitingTime")
    public String getVisitingTime() {
        return VisitingTime;
    }

    public void setVisitingTime(String VisitingTime) {
        this.VisitingTime = VisitingTime;
    }
    
    @JsonProperty("AttendNum")
    public String getAttendNum() {
        return AttendNum;
    }

    public void setAttendNum(String AttendNum) {
        this.AttendNum = AttendNum;
    }
    
    @JsonProperty("ProjectID")
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    
    @JsonProperty("CustomerName")
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    
    @JsonProperty("CustomerMobile")
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    
    @JsonProperty("CustomerSource")
    public String getCustomerSource() {
        return CustomerSource;
    }

    public void setCustomerSource(String CustomerSource) {
        this.CustomerSource = CustomerSource;
    }
    
    @JsonProperty("CustomerType")
    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String CustomerType) {
        this.CustomerType = CustomerType;
    }
    
    @JsonProperty("CustomerMode")
    public String getCustomerMode() {
        return CustomerMode;
    }

    public void setCustomerMode(String CustomerMode) {
        this.CustomerMode = CustomerMode;
    }
    
    @JsonProperty("AdviserID")
    public String getAdviserID() {
        return AdviserID;
    }

    public void setAdviserID(String AdviserID) {
        this.AdviserID = AdviserID;
    }
    
    @JsonProperty("AdviserName")
    public String getAdviserName() {
        return AdviserName;
    }

    public void setAdviserName(String AdviserName) {
        this.AdviserName = AdviserName;
    }
    
    @JsonProperty("AdviserMobile")
    public String getAdviserMobile() {
        return AdviserMobile;
    }

    public void setAdviserMobile(String AdviserMobile) {
        this.AdviserMobile = AdviserMobile;
    }
    
    @JsonProperty("IsDel")
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    
    @JsonProperty("Status")
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    
    @JsonProperty("IsDay")
    public Integer getIsDay() {
        return IsDay;
    }

    public void setIsDay(Integer IsDay) {
        this.IsDay = IsDay;
    }
    
    @JsonProperty("Num")
    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "VCustomerfjlistSelect{" +
        "id=" + id +
        ", CustomerID=" + CustomerID +
        ", Assignor=" + Assignor +
        ", ReportID=" + ReportID +
        ", ReportName=" + ReportName +
        ", ReportTime=" + ReportTime +
        ", VisitingTime=" + VisitingTime +
        ", AttendNum=" + AttendNum +
        ", ProjectID=" + ProjectID +
        ", CustomerName=" + CustomerName +
        ", CustomerMobile=" + CustomerMobile +
        ", CustomerSource=" + CustomerSource +
        ", CustomerType=" + CustomerType +
        ", CustomerMode=" + CustomerMode +
        ", AdviserID=" + AdviserID +
        ", AdviserName=" + AdviserName +
        ", AdviserMobile=" + AdviserMobile +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", IsDay=" + IsDay +
        ", num=" + num +
        "}";
    }
}
