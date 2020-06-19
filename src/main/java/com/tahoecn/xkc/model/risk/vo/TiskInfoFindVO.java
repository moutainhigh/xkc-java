package com.tahoecn.xkc.model.risk.vo;

import com.tahoecn.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/5/21 16:28
 */
public class TiskInfoFindVO implements Serializable {

   /* private String id ;//主键
    private Integer level ;//级别*/
    private String userName ;//用户名
    private Date startTime ;//统计时间
    private Date endTime ;//统计时间
    private String saleUserName ;//置业顾问
    private String customerMobile ;//手机号
    private String adviserGroupID ;//相关渠道
    private Integer riskType ;//风险类别
    private String reportUserName ;//经纪人
    private Integer customerStatus ;//客户状态

    private String regionalId;//区域主键
    private String cityId;//城市主键
    private String projectId;//项目主键

    @NotNull(message = "起始页不能为空")
    private Integer pageNum;
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(String regionalId) {
        this.regionalId = regionalId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

   /* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getStartTime() {
        if (null != startTime) return DateUtil.parseDateTime(DateUtil.formatDate(startTime)+" 00:00:00");
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        if (null != endTime) return DateUtil.parseDateTime(DateUtil.formatDate(endTime)+" 23:59:59");
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getAdviserGroupID() {
        return adviserGroupID;
    }

    public void setAdviserGroupID(String adviserGroupID) {
        this.adviserGroupID = adviserGroupID;
    }

    public Integer getRiskType() {
        return riskType;
    }

    public void setRiskType(Integer riskType) {
        this.riskType = riskType;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }
}
