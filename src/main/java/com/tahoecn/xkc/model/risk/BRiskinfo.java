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
@TableName("B_RiskInfo")
@ApiModel(value="BRiskinfo对象", description="")
public class BRiskinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("RiskConfigId")
    private String RiskConfigId;

    @TableField("RegionalId")
    private String RegionalId;

    @TableField("RegionalName")
    private String RegionalName;

    @TableField("CityId")
    private String CityId;

    @TableField("CityName")
    private String CityName;

    @TableField("ProjectId")
    private String ProjectId;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("RiskType")
    private Integer RiskType;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("ClueId")
    private String ClueId;

    @TableField("OpportunityId")
    private String OpportunityId;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("CustomerStatus")
    private Integer CustomerStatus;

    @TableField("CustomerStatusName")
    private String CustomerStatusName;

    @TableField("ReportUserID")
    private String ReportUserID;

    @TableField("ReportUserName")
    private String ReportUserName;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @TableField("AdviserGroupName")
    private String AdviserGroupName;

    @TableField("ReportTime")
    private Date ReportTime;

    @TableField("TheFirstVisitDate")
    private Date TheFirstVisitDate;

    @TableField("SubscribeTime")
    private Date SubscribeTime;

    @TableField("ContractTime")
    private Date ContractTime;

    @TableField("SaleUserID")
    private String SaleUserID;

    @TableField("SaleUserName")
    private String SaleUserName;

    @TableField("FirstFaceTime")
    private Date FirstFaceTime;

    @TableField("RiskDesc")
    private String RiskDesc;

    @TableField("SearchUserId")
    private String SearchUserId;

    @TableField("SearchUserName")
    private String SearchUserName;

    @TableField("SearchUserRoleID")
    private String SearchUserRoleID;

    @TableField("SearchUserRoleName")
    private String SearchUserRoleName;

    @TableField("OrgId")
    private String OrgId;

    @TableField("OpportunitySource")
    private String OpportunitySource;

    @TableField("ProjectProtectCustomerTime")
    private Date ProjectProtectCustomerTime;

    @TableField("RiskProtectCustomerTime")
    private Date RiskProtectCustomerTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRiskConfigId() {
        return RiskConfigId;
    }

    public void setRiskConfigId(String RiskConfigId) {
        this.RiskConfigId = RiskConfigId;
    }
    public String getRegionalId() {
        return RegionalId;
    }

    public void setRegionalId(String RegionalId) {
        this.RegionalId = RegionalId;
    }
    public String getRegionalName() {
        return RegionalName;
    }

    public void setRegionalName(String RegionalName) {
        this.RegionalName = RegionalName;
    }
    public String getCityId() {
        return CityId;
    }

    public void setCityId(String CityId) {
        this.CityId = CityId;
    }
    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }
    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }
    public Integer getRiskType() {
        return RiskType;
    }

    public void setRiskType(Integer RiskType) {
        this.RiskType = RiskType;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
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
    public Integer getCustomerStatus() {
        return CustomerStatus;
    }

    public void setCustomerStatus(Integer CustomerStatus) {
        this.CustomerStatus = CustomerStatus;
    }
    public String getCustomerStatusName() {
        return CustomerStatusName;
    }

    public void setCustomerStatusName(String CustomerStatusName) {
        this.CustomerStatusName = CustomerStatusName;
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
    public Date getReportTime() {
        return ReportTime;
    }

    public void setReportTime(Date ReportTime) {
        this.ReportTime = ReportTime;
    }
    public Date getTheFirstVisitDate() {
        return TheFirstVisitDate;
    }

    public void setTheFirstVisitDate(Date TheFirstVisitDate) {
        this.TheFirstVisitDate = TheFirstVisitDate;
    }
    public Date getSubscribeTime() {
        return SubscribeTime;
    }

    public void setSubscribeTime(Date SubscribeTime) {
        this.SubscribeTime = SubscribeTime;
    }
    public Date getContractTime() {
        return ContractTime;
    }

    public void setContractTime(Date ContractTime) {
        this.ContractTime = ContractTime;
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
    public Date getFirstFaceTime() {
        return FirstFaceTime;
    }

    public void setFirstFaceTime(Date FirstFaceTime) {
        this.FirstFaceTime = FirstFaceTime;
    }
    public String getRiskDesc() {
        return RiskDesc;
    }

    public void setRiskDesc(String RiskDesc) {
        this.RiskDesc = RiskDesc;
    }

    public String getSearchUserId() {
        return SearchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        SearchUserId = searchUserId;
    }

    public String getSearchUserName() {
        return SearchUserName;
    }

    public void setSearchUserName(String searchUserName) {
        SearchUserName = searchUserName;
    }

    public String getSearchUserRoleID() {
        return SearchUserRoleID;
    }

    public void setSearchUserRoleID(String searchUserRoleID) {
        SearchUserRoleID = searchUserRoleID;
    }

    public String getSearchUserRoleName() {
        return SearchUserRoleName;
    }

    public void setSearchUserRoleName(String searchUserRoleName) {
        SearchUserRoleName = searchUserRoleName;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public String getOpportunitySource() {
        return OpportunitySource;
    }

    public void setOpportunitySource(String opportunitySource) {
        OpportunitySource = opportunitySource;
    }

    public Date getProjectProtectCustomerTime() {
        return ProjectProtectCustomerTime;
    }

    public void setProjectProtectCustomerTime(Date projectProtectCustomerTime) {
        ProjectProtectCustomerTime = projectProtectCustomerTime;
    }

    public Date getRiskProtectCustomerTime() {
        return RiskProtectCustomerTime;
    }

    public void setRiskProtectCustomerTime(Date riskProtectCustomerTime) {
        RiskProtectCustomerTime = riskProtectCustomerTime;
    }

    @Override
    public String toString() {
        return "BRiskinfo{" +
                "id=" + id +
                ", RiskConfigId=" + RiskConfigId +
                ", RegionalId=" + RegionalId +
                ", RegionalName=" + RegionalName +
                ", CityId=" + CityId +
                ", CityName=" + CityName +
                ", ProjectId=" + ProjectId +
                ", ProjectName=" + ProjectName +
                ", RiskType=" + RiskType +
                ", CreateTime=" + CreateTime +
                ", ClueId=" + ClueId +
                ", OpportunityId=" + OpportunityId +
                ", CustomerName=" + CustomerName +
                ", CustomerMobile=" + CustomerMobile +
                ", CustomerStatus=" + CustomerStatus +
                ", CustomerStatusName=" + CustomerStatusName +
                ", ReportUserID=" + ReportUserID +
                ", ReportUserName=" + ReportUserName +
                ", AdviserGroupID=" + AdviserGroupID +
                ", AdviserGroupName=" + AdviserGroupName +
                ", ReportTime=" + ReportTime +
                ", TheFirstVisitDate=" + TheFirstVisitDate +
                ", SubscribeTime=" + SubscribeTime +
                ", ContractTime=" + ContractTime +
                ", SaleUserID=" + SaleUserID +
                ", SaleUserName=" + SaleUserName +
                ", FirstFaceTime=" + FirstFaceTime +
                ", RiskDesc=" + RiskDesc +
                ", SearchUserId=" + SearchUserId +
                ", SearchUserName=" + SearchUserName +
                ", SearchUserRoleID=" + SearchUserRoleID +
                ", SearchUserRoleName=" + SearchUserRoleName +
                ", OrgId=" + OrgId +
                ", OpportunitySource=" + OpportunitySource +
                ", ProjectProtectCustomerTime=" + ProjectProtectCustomerTime +
                ", RiskProtectCustomerTime=" + RiskProtectCustomerTime +
                "}";
    }
}
