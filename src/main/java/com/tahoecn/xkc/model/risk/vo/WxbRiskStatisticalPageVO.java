package com.tahoecn.xkc.model.risk.vo;

import com.tahoecn.core.date.DateUtil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/17 16:04
 */
public class WxbRiskStatisticalPageVO implements Serializable {

    private String regionalId;//区域主键
    private String cityId;//城市主键
    private String projectId;//项目主键

    private String type;
    private String sourceType;

    private List<String> dictId;
    private String channelCompanyId;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        if (null != startTime) {
            this.startTime = startDate(startTime);
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        if (null != endTime) {
            this.endTime = endDate(endTime);
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

    public List<String> getDictId() {
        return dictId;
    }

    public void setDictId(List<String> dictId) {
        this.dictId = dictId;
    }

    public String getChannelCompanyId() {
        return channelCompanyId;
    }

    public void setChannelCompanyId(String channelCompanyId) {
        this.channelCompanyId = channelCompanyId;
    }

    public static Date startDate(Date startDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.HOUR, 0);
        cal.add(Calendar.MINUTE, 0);
        cal.add(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date endDate(Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.HOUR, 23);
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.SECOND, 59);
        return cal.getTime();
    }
}
