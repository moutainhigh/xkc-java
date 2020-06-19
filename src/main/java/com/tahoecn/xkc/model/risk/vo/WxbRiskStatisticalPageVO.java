package com.tahoecn.xkc.model.risk.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/17 16:04
 */
public class WxbRiskStatisticalPageVO implements Serializable {

    private String regionalId;//区域主键
    private String cityId;//城市主键
    private String projectId;//项目主键

    private String channelSource;//渠道来源
    private String channelOrg;//渠道机构

    private Date startTime;//统计时间
    private Date endTime;//统计时间

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

    public String getChannelSource() {
        return channelSource;
    }

    public void setChannelSource(String channelSource) {
        this.channelSource = channelSource;
    }

    public String getChannelOrg() {
        return channelOrg;
    }

    public void setChannelOrg(String channelOrg) {
        this.channelOrg = channelOrg;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
