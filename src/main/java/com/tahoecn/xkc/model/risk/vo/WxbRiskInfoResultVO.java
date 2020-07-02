package com.tahoecn.xkc.model.risk.vo;

import com.tahoecn.core.date.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/19 14:25
 */
public class WxbRiskInfoResultVO implements Serializable {

    private String regionalName;//区域
    private String cityName;//城市
    private String projectName;//项目
    private String projectId;//项目id
    private String opportunityId;
    private String house;//房间编号
    private String customerName;//客户姓名
    private String customerId;//客户id
    private String dictName; //渠道来源名称
    private String channelCompany;// 渠道机构
    private String agent;//经纪人
    private String salerName;//置业顾问
    private Float subscribeMoney;//认购金额
    private Float contractMoney;//签约金额
    private Date reportTime;//报备时间
    private Date firstPhotoTime;//首次抓拍时间
    private Date freshCardTime; //刷证时间
    private Date subscribeTime;//认购时间
    private Date finishTime;//签约时间
    private String riskStatus;//风险类别:0疑似风险1无风险2确认风险3未知客户

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getRegionalName() {
        return regionalName;
    }

    public void setRegionalName(String regionalName) {
        this.regionalName = regionalName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getChannelCompany() {
        return channelCompany;
    }

    public void setChannelCompany(String channelCompany) {
        this.channelCompany = channelCompany;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public Float getSubscribeMoney() {
        return subscribeMoney;
    }

    public void setSubscribeMoney(Float subscribeMoney) {
        this.subscribeMoney = subscribeMoney;
    }

    public Float getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Float contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getReportTime() {
        return DateUtil.formatDate(reportTime);
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getFirstPhotoTime() {
        return DateUtil.formatDate(firstPhotoTime);
    }

    public void setFirstPhotoTime(Date firstPhotoTime) {
        this.firstPhotoTime = firstPhotoTime;
    }

    public String getFreshCardTime() {
        return DateUtil.formatDate(freshCardTime);
    }

    public void setFreshCardTime(Date freshCardTime) {
        this.freshCardTime = freshCardTime;
    }

    public String getSubscribeTime() {
        return DateUtil.formatDate(subscribeTime);
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getFinishTime() {
        return DateUtil.formatDate(finishTime);
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }
}
