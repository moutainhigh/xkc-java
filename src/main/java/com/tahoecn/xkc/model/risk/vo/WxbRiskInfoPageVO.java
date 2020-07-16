package com.tahoecn.xkc.model.risk.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/19 9:16
 */
public class WxbRiskInfoPageVO implements Serializable {

    private String regionalId;//区域主键
    private String cityId;//城市主键
    private String projectId;//项目主键
    private String homeName;//房间
    private String type;
    private String sourceType;
    private String agent;//经纪人
    private String salerName;//置业顾问
    private Integer riskStatus;//风险类型0疑似风险1无风险2确认风险3未知客户
    private Date reportStartTime;//报备起始时间
    private Date reportEndTime;//报备结束时间
    private Date firstPhotoStartTime;//抓拍起始时间
    private Date firstPhotoEndTime;//抓拍结束时间
    private Date subscribeStartTime;//认购起始时间
    private Date subscribeEndTime;//认购结束时间
    private Date finishStartTime;//签约起始时间
    private Date finishEndTime;//签约结束时间
    private Date freshCardStartTime;//统计(刷证)起始时间
    private Date freshCardEndTime;//统计(刷证)结束时间
    private Integer style;//1全部客户2刷证客户3认证失败4报备客户5成交客户6未知客户7疑似风险8疑似待确认9确认风险10确认无风险11未刷证

    @NotNull(message = "起始页不能为空")
    private Integer pageNum;
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

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

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
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

    public Integer getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(Integer riskStatus) {
        this.riskStatus = riskStatus;
    }

    public Date getReportStartTime() {
        return reportStartTime;
    }

    public void setReportStartTime(Date reportStartTime) throws Exception{
        this.reportStartTime = reportStartTime;
        if (null != reportStartTime) {
            this.reportStartTime = WxbRiskStatisticalPageVO.startDate(reportStartTime);
        }
    }

    public Date getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(Date reportEndTime)throws Exception {
        this.reportEndTime = reportEndTime;
        if (null != reportEndTime) {
            this.reportEndTime = WxbRiskStatisticalPageVO.endDate(reportEndTime);
        }
    }

    public Date getFirstPhotoStartTime() {
        return firstPhotoStartTime;
    }

    public void setFirstPhotoStartTime(Date firstPhotoStartTime) throws Exception{
        this.firstPhotoStartTime = firstPhotoStartTime;
        if (null != firstPhotoStartTime) {
            this.firstPhotoStartTime = WxbRiskStatisticalPageVO.startDate(firstPhotoStartTime);
        }
    }

    public Date getFirstPhotoEndTime() {
        return firstPhotoEndTime;
    }

    public void setFirstPhotoEndTime(Date firstPhotoEndTime) throws Exception{
        this.firstPhotoEndTime = firstPhotoEndTime;
        if (null != firstPhotoEndTime) {
            this.firstPhotoEndTime = WxbRiskStatisticalPageVO.endDate(firstPhotoEndTime);
        }
    }

    public Date getSubscribeStartTime() {
        return subscribeStartTime;
    }

    public void setSubscribeStartTime(Date subscribeStartTime) throws Exception{
        this.subscribeStartTime = subscribeStartTime;
        if (null != subscribeStartTime) {
            this.subscribeStartTime = WxbRiskStatisticalPageVO.startDate(subscribeStartTime);
        }
    }

    public Date getSubscribeEndTime() {
        return subscribeEndTime;
    }

    public void setSubscribeEndTime(Date subscribeEndTime) throws Exception{
        this.subscribeEndTime = subscribeEndTime;
        if (null != subscribeEndTime) {
            this.subscribeEndTime = WxbRiskStatisticalPageVO.endDate(subscribeEndTime);
        }
    }

    public Date getFinishStartTime() {
        return finishStartTime;
    }

    public void setFinishStartTime(Date finishStartTime) throws Exception{
        this.finishStartTime = finishStartTime;
        if (null != finishStartTime) {
            this.finishStartTime = WxbRiskStatisticalPageVO.startDate(finishStartTime);
        }
    }

    public Date getFinishEndTime() {
        return finishEndTime;
    }

    public void setFinishEndTime(Date finishEndTime) throws Exception{
        this.finishEndTime = finishEndTime;
        if (null != finishEndTime) {
            this.finishEndTime = WxbRiskStatisticalPageVO.endDate(finishEndTime);
        }
    }

    public Date getFreshCardStartTime() {
        return freshCardStartTime;
    }

    public void setFreshCardStartTime(Date freshCardStartTime) throws Exception{
        this.freshCardStartTime = freshCardStartTime;
        if (null != freshCardStartTime) {
            this.freshCardStartTime = WxbRiskStatisticalPageVO.startDate(freshCardStartTime);
        }
    }

    public Date getFreshCardEndTime() {
        return freshCardEndTime;
    }

    public void setFreshCardEndTime(Date freshCardEndTime) throws Exception{
        this.freshCardEndTime = freshCardEndTime;
        if (null != freshCardEndTime) {
            this.freshCardEndTime = WxbRiskStatisticalPageVO.endDate(freshCardEndTime);
        }
    }

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
}
