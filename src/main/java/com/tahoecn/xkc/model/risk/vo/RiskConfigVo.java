package com.tahoecn.xkc.model.risk.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 风控配置替代VO
 * @author: 张晓东
 * @time: 2020/5/6 18:39
 */
public class RiskConfigVo implements Serializable {

    //主键
    @NotBlank(message = "id不能为空")
    private String id;
    //类型0 集团 1 项目
    @NotNull(message = "Type不能为空")
    private Integer type;
    //项目id
    private String projectID;
    //防截客开关 0禁用 1启用
    private Integer isProtectCustomer;
    //防截客时间
    private Integer protectTime;
    //人脸识别开关 0禁用 1启用
    private Integer isFace;
    //搜电未报备开关 0禁用 1启用
    private Integer isSearchMobile;
    //修改客户名称开关 0禁用 1启用
    private Integer isEditName;
    //修改客户名称类型 0修改姓氏 1修改名称
    private Integer editNameType;
    //修改客户名称次数
    private Integer editNameCount;
    //联名购房开关 0禁用 1启用
    private Integer isJointName;
    //0渠道不唯一 1案场先渠道后
    private Integer jointNameType;
    //短期成交开关 0禁用 1启用
    private Integer isShortDeal;
    //短期成交周期/小时
    private Integer shortDealTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public Integer getIsProtectCustomer() {
        return isProtectCustomer;
    }

    public void setIsProtectCustomer(Integer isProtectCustomer) {
        this.isProtectCustomer = isProtectCustomer;
    }

    public Integer getProtectTime() {
        return protectTime;
    }

    public void setProtectTime(Integer protectTime) {
        this.protectTime = protectTime;
    }

    public Integer getIsFace() {
        return isFace;
    }

    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    public Integer getIsSearchMobile() {
        return isSearchMobile;
    }

    public void setIsSearchMobile(Integer isSearchMobile) {
        this.isSearchMobile = isSearchMobile;
    }

    public Integer getIsEditName() {
        return isEditName;
    }

    public void setIsEditName(Integer isEditName) {
        this.isEditName = isEditName;
    }

    public Integer getEditNameType() {
        return editNameType;
    }

    public void setEditNameType(Integer editNameType) {
        this.editNameType = editNameType;
    }

    public Integer getEditNameCount() {
        return editNameCount;
    }

    public void setEditNameCount(Integer editNameCount) {
        this.editNameCount = editNameCount;
    }

    public Integer getIsJointName() {
        return isJointName;
    }

    public void setIsJointName(Integer isJointName) {
        this.isJointName = isJointName;
    }

    public Integer getJointNameType() {
        return jointNameType;
    }

    public void setJointNameType(Integer jointNameType) {
        this.jointNameType = jointNameType;
    }

    public Integer getIsShortDeal() {
        return isShortDeal;
    }

    public void setIsShortDeal(Integer isShortDeal) {
        this.isShortDeal = isShortDeal;
    }

    public Integer getShortDealTime() {
        return shortDealTime;
    }

    public void setShortDealTime(Integer shortDealTime) {
        this.shortDealTime = shortDealTime;
    }
}
