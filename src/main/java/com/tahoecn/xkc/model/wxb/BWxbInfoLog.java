package com.tahoecn.xkc.model.wxb;

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
 * @since 2020-06-29
 */
@TableName("B_WxbInfoLog")
@ApiModel(value = "BWxbInfoLog对象", description = "")
public class BWxbInfoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("OrderGUID")
    private String OrderGUID;

    @TableField("ContractGUID")
    private String ContractGUID;

    @TableField("ProjectId")
    private String ProjectId;

    @TableField("Name")
    private String Name;

    @TableField("Mobile")
    private String Mobile;

    @TableField("IdNumber")
    private String IdNumber;

    @TableField("SalerName")
    private String SalerName;

    @TableField("FirstReadTime")
    private String FirstReadTime;

    @TableField("ReportTime")
    private String ReportTime;

    @TableField("ChannelCompany")
    private String ChannelCompany;

    @TableField("Agent")
    private String Agent;

    @TableField("SubscriptionTime")
    private String SubscriptionTime;

    @TableField("FinishTime")
    private String FinishTime;

    @TableField("FinishNo")
    private String FinishNo;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("ErrorMsg")
    private String ErrorMsg;

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderGUID() {
        return OrderGUID;
    }

    public void setOrderGUID(String orderId) {
        OrderGUID = orderId;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getSalerName() {
        return SalerName;
    }

    public void setSalerName(String salerName) {
        SalerName = salerName;
    }

    public String getFirstReadTime() {
        return FirstReadTime;
    }

    public void setFirstReadTime(String firstReadTime) {
        FirstReadTime = firstReadTime;
    }

    public String getReportTime() {
        return ReportTime;
    }

    public void setReportTime(String reportTime) {
        ReportTime = reportTime;
    }

    public String getChannelCompany() {
        return ChannelCompany;
    }

    public void setChannelCompany(String channelCompany) {
        ChannelCompany = channelCompany;
    }

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String agent) {
        Agent = agent;
    }

    public String getSubscriptionTime() {
        return SubscriptionTime;
    }

    public void setSubscriptionTime(String subscriptionTime) {
        SubscriptionTime = subscriptionTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getFinishNo() {
        return FinishNo;
    }

    public void setFinishNo(String finishNo) {
        FinishNo = finishNo;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getContractGUID() {
        return ContractGUID;
    }

    public void setContractGUID(String contractGUID) {
        ContractGUID = contractGUID;
    }

    @Override
    public String toString() {
        return "BWxbInfoLog{" +
                "id=" + id +
                ", OrderGUID=" + OrderGUID +
                ", ContractGUID=" + ContractGUID +
                ", ProjectId=" + ProjectId +
                ", Name=" + Name +
                ", Mobile=" + Mobile +
                ", IdNumber=" + IdNumber +
                ", SalerName=" + SalerName +
                ", FirstReadTime=" + FirstReadTime +
                ", ReportTime=" + ReportTime +
                ", ChannelCompany=" + ChannelCompany +
                ", CreateTime=" + CreateTime +
                ", Agent=" + Agent +
                ", SubscriptionTime=" + SubscriptionTime +
                ", FinishTime=" + FinishTime +
                ", FinishNo=" + FinishNo +
                ", CreateTime=" + CreateTime +
                ", ErrorMsg=" + ErrorMsg +
                ", Status=" + Status +

                "}";
    }
}