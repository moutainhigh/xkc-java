package com.tahoecn.xkc.model.risk.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/5/21 10:32
 */
public class TiskInfoTreeVO implements Serializable, Comparable<TiskInfoTreeVO> {
    private String id;//主键
    private String titel;//标题
    private Integer protectCustomer = 0;//防截客
    private Integer face = 0;//人脸识别
    private Integer searchMobile = 0;//搜电未报备
    private Integer editName = 0;//修改客户名称
    private Integer jointName = 0;//联名购房
    private Integer shortDeal = 0;//短期成交
    private Integer unverified = 0;//短期成交
    private Integer level;//级别
    private Boolean isChild;//是否有子集

    private String regionalId;//区域主键
    private String cityId;//城市主键
    private String projectId;//项目主键

    public Integer getUnverified() {
        return unverified;
    }

    public void setUnverified(Integer unverified) {
        this.unverified = unverified;
    }

    public String getRegionalId() {
        return StringUtils.isNotEmpty(regionalId) ? regionalId.toLowerCase() : regionalId;
    }

    public void setRegionalId(String regionalId) {
        this.regionalId = StringUtils.isNotEmpty(regionalId) ? regionalId.toUpperCase() : regionalId;
    }

    public String getCityId() {
        return StringUtils.isNotEmpty(cityId) ? cityId.toLowerCase() : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = StringUtils.isNotEmpty(cityId) ? cityId.toUpperCase() : cityId;
    }

    public String getProjectId() {
        return StringUtils.isNotEmpty(projectId) ? projectId.toLowerCase() : projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = StringUtils.isNotEmpty(projectId) ? projectId.toUpperCase() : projectId;
    }

    public boolean isChild() {
        return (this.protectCustomer + this.face + this.searchMobile + this.editName + this.jointName + this.shortDeal) > 0;
    }

    public Boolean getChild() {
        return isChild;
    }

    public void setChild(Boolean child) {
        isChild = child;
    }

    public String getId() {
        return StringUtils.isNotEmpty(id) ? id.toLowerCase() : id;
    }

    public void setId(String id) {
        this.id = StringUtils.isNotEmpty(id) ? id.toUpperCase() : id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Integer getProtectCustomer() {
        return protectCustomer;
    }

    public void setProtectCustomer(Integer protectCustomer) {
        this.protectCustomer = protectCustomer;
    }

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }

    public Integer getSearchMobile() {
        return searchMobile;
    }

    public void setSearchMobile(Integer searchMobile) {
        this.searchMobile = searchMobile;
    }

    public Integer getEditName() {
        return editName;
    }

    public void setEditName(Integer editName) {
        this.editName = editName;
    }

    public Integer getJointName() {
        return jointName;
    }

    public void setJointName(Integer jointName) {
        this.jointName = jointName;
    }

    public Integer getShortDeal() {
        return shortDeal;
    }

    public void setShortDeal(Integer shortDeal) {
        this.shortDeal = shortDeal;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    @Override
    public int compareTo(TiskInfoTreeVO o) {
        return this.getId().compareTo(o.getId());
    }
}
