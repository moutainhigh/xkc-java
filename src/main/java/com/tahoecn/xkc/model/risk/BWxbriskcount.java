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
 * @since 2020-06-17
 */
@TableName("B_WxbRiskCount")
@ApiModel(value="BWxbriskcount对象", description="")
public class BWxbriskcount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

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

    @TableField("House")
    private String House;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("CustomerCardId")
    private String CustomerCardId;

    @TableField("DictId")
    private String DictId;

    @TableField("DictName")
    private String DictName;

    @TableField("ChannelCompany")
    private String ChannelCompany;

    @TableField("Agent")
    private String Agent;

    @TableField("SalerName")
    private String SalerName;

    @TableField("OpportunityId")
    private String OpportunityId;

    @TableField("SubscribeMoney")
    private Float SubscribeMoney;

    @TableField("ContractMoney")
    private Float ContractMoney;

    @TableField("RiskStatus")
    private Integer RiskStatus;

    @TableField("HasPass")
    private Integer HasPass;

    @TableField("ReportTime")
    private Date ReportTime;

    @TableField("FirstPhotoTime")
    private Date FirstPhotoTime;

    @TableField("FreshCardTime")
    private Date FreshCardTime;

    @TableField("SubscribeTime")
    private Date SubscribeTime;

    @TableField("FinishTime")
    private Date FinishTime;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getHouse() {
        return House;
    }

    public void setHouse(String House) {
        this.House = House;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getCustomerCardId() {
        return CustomerCardId;
    }

    public void setCustomerCardId(String CustomerCardId) {
        this.CustomerCardId = CustomerCardId;
    }
    public String getDictName() {
        return DictName;
    }

    public void setDictName(String DictName) {
        this.DictName = DictName;
    }
    public String getChannelCompany() {
        return ChannelCompany;
    }

    public void setChannelCompany(String ChannelCompany) {
        this.ChannelCompany = ChannelCompany;
    }
    public String getAgent() {
        return Agent;
    }

    public void setAgent(String Agent) {
        this.Agent = Agent;
    }
    public String getSalerName() {
        return SalerName;
    }

    public void setSalerName(String SalerName) {
        this.SalerName = SalerName;
    }
    public String getOpportunityId() {
        return OpportunityId;
    }

    public void setOpportunityId(String OpportunityId) {
        this.OpportunityId = OpportunityId;
    }
    public Float getSubscribeMoney() {
        return SubscribeMoney;
    }

    public void setSubscribeMoney(Float SubscribeMoney) {
        this.SubscribeMoney = SubscribeMoney;
    }
    public Float getContractMoney() {
        return ContractMoney;
    }

    public void setContractMoney(Float ContractMoney) {
        this.ContractMoney = ContractMoney;
    }
    public Integer getRiskStatus() {
        return RiskStatus;
    }

    public void setRiskStatus(Integer RiskStatus) {
        this.RiskStatus = RiskStatus;
    }
    public Date getReportTime() {
        return ReportTime;
    }

    public void setReportTime(Date ReportTime) {
        this.ReportTime = ReportTime;
    }
    public Date getFirstPhotoTime() {
        return FirstPhotoTime;
    }

    public void setFirstPhotoTime(Date FirstPhotoTime) {
        this.FirstPhotoTime = FirstPhotoTime;
    }
    public Date getFreshCardTime() {
        return FreshCardTime;
    }

    public void setFreshCardTime(Date FreshCardTime) {
        this.FreshCardTime = FreshCardTime;
    }
    public Date getSubscribeTime() {
        return SubscribeTime;
    }

    public void setSubscribeTime(Date SubscribeTime) {
        this.SubscribeTime = SubscribeTime;
    }
    public Date getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(Date FinishTime) {
        this.FinishTime = FinishTime;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Integer getHasPass() {
        return HasPass;
    }

    public void setHasPass(Integer hasPass) {
        HasPass = hasPass;
    }

    public String getDictId() {
        return DictId;
    }

    public void setDictId(String dictId) {
        DictId = dictId;
    }

    @Override
    public String toString() {
        return "BWxbriskcount{" +
        "id=" + id +
        ", RegionalId=" + RegionalId +
        ", RegionalName=" + RegionalName +
        ", CityId=" + CityId +
        ", CityName=" + CityName +
        ", ProjectId=" + ProjectId +
        ", ProjectName=" + ProjectName +
        ", House=" + House +
        ", CustomerName=" + CustomerName +
        ", CustomerCardId=" + CustomerCardId +
        ", DictName=" + DictName +
        ", ChannelCompany=" + ChannelCompany +
        ", Agent=" + Agent +
        ", SalerName=" + SalerName +
        ", OpportunityId=" + OpportunityId +
        ", SubscribeMoney=" + SubscribeMoney +
        ", ContractMoney=" + ContractMoney +
        ", RiskStatus=" + RiskStatus +
        ", ReportTime=" + ReportTime +
        ", FirstPhotoTime=" + FirstPhotoTime +
        ", FreshCardTime=" + FreshCardTime +
        ", SubscribeTime=" + SubscribeTime +
        ", FinishTime=" + FinishTime +
        ", HasPass=" + HasPass +
        ", DictId=" + DictId +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
