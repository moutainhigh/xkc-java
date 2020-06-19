package com.tahoecn.xkc.model.risk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/5/21 16:50
 */
public class TiskInfoVO implements Serializable, Comparable<TiskInfoVO> {

    private String id;//主键
    private String regionalName;//区域
    private String projectName;//    项目
    private String customerName;//    客户名称
    private String customerMobile;//    手机号
    private String customerStatusName;//    客户状态
    private String saleUserName;//    置业顾问
    private String reportUserName;//    经纪人
    private String orgName;//    渠道名称
    private String adviserGroupName;//    渠道名称
    private Integer riskType;//    风险类别
    private Date createTime;//    统计时间

    private String yAdviserGroupName;//    原渠道类型
    private String yOrgName;//    原渠道名称
    private String ySaleUserName;//原置业顾问姓名
    private String yReportUserName;//    原经纪人

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getyOrgName() {
        return yOrgName;
    }

    public void setyOrgName(String yOrgName) {
        this.yOrgName = yOrgName;
    }

    public String getyAdviserGroupName() {
        return yAdviserGroupName;
    }

    public void setyAdviserGroupName(String yAdviserGroupName) {
        this.yAdviserGroupName = yAdviserGroupName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionalName() {
        return regionalName;
    }

    public void setRegionalName(String regionalName) {
        this.regionalName = regionalName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerStatusName() {
        return customerStatusName;
    }

    public void setCustomerStatusName(String customerStatusName) {
        this.customerStatusName = customerStatusName;
    }

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public String getAdviserGroupName() {
        return adviserGroupName;
    }

    public void setAdviserGroupName(String adviserGroupName) {
        this.adviserGroupName = adviserGroupName;
    }

    public Integer getRiskType() {
        return riskType;
    }

    public void setRiskType(Integer riskType) {
        this.riskType = riskType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getySaleUserName() {
        return ySaleUserName;
    }

    public void setySaleUserName(String ySaleUserName) {
        this.ySaleUserName = ySaleUserName;
    }

    public String getyReportUserName() {
        return yReportUserName;
    }

    public void setyReportUserName(String yReportUserName) {
        this.yReportUserName = yReportUserName;
    }

    @Override
    public int compareTo(TiskInfoVO o) {
        return this.getCreateTime().compareTo(o.getCreateTime());
    }
}
