package com.tahoecn.xkc.model.risk.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/6 11:26
 */
public class RiskConfigProjectVO implements Serializable {

    private String projectId;

    private String projectName;

    //防截客开关 0禁用 1启用
    private Integer isProtectCustomer;
    //人脸识别开关 0禁用 1启用
    private Integer isFace;
    //搜电未报备开关 0禁用 1启用
    private Integer isSearchMobile;
    //修改客户名称开关 0禁用 1启用
    private Integer isEditName;
    //联名购房开关 0禁用 1启用
    private Integer isJointName;
    //短期成交开关 0禁用 1启用
    private Integer isShortDeal;
    //认筹未刷证 0禁用 1启用
    private Integer isUnverified;

    //类型0集团1项目
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIsProtectCustomer() {
        return isProtectCustomer;
    }

    public void setIsProtectCustomer(Integer isProtectCustomer) {
        this.isProtectCustomer = isProtectCustomer;
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

    public Integer getIsJointName() {
        return isJointName;
    }

    public void setIsJointName(Integer isJointName) {
        this.isJointName = isJointName;
    }

    public Integer getIsShortDeal() {
        return isShortDeal;
    }

    public void setIsShortDeal(Integer isShortDeal) {
        this.isShortDeal = isShortDeal;
    }

    public Integer getIsUnverified() {
        return isUnverified;
    }

    public void setIsUnverified(Integer isUnverified) {
        this.isUnverified = isUnverified;
    }
}
