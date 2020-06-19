package com.tahoecn.xkc.model.risk.vo;

import com.tahoecn.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/5/22 15:26
 */
public class TiskTreeFindVO implements Serializable {

    private String id;

    private String regionalId;//区域主键
    private String cityId;//城市主键
    private String projectId;//项目主键

    private Date startTime;//起始时间
    private Date endTime;//结束时间
    private Integer level;//级别

    public String getId() {
        return StringUtils.isNotEmpty(id)?id.toLowerCase():id;
    }

    public void setId(String id) {
        this.id = StringUtils.isNotEmpty(id)?id.toUpperCase():id;
    }

    public String getRegionalId() {
        return StringUtils.isNotEmpty(regionalId)?regionalId.toLowerCase():regionalId;
    }

    public void setRegionalId(String regionalId) {
        this.regionalId = StringUtils.isNotEmpty(regionalId)?regionalId.toUpperCase():regionalId;
    }

    public String getCityId() {
        return StringUtils.isNotEmpty(cityId)?cityId.toLowerCase():cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = StringUtils.isNotEmpty(cityId)?cityId.toUpperCase():cityId;
    }

    public String getProjectId() {
        return StringUtils.isNotEmpty(projectId)?projectId.toLowerCase():projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = StringUtils.isNotEmpty(projectId)?projectId.toUpperCase():projectId;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
