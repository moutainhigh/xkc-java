package com.tahoecn.xkc.model.vo;

import com.tahoecn.xkc.model.rule.BCluerule;
import java.io.Serializable;

public class BClueruleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private BCluerule bCluerule;

    private String IsUpdateGroup;

    private String grouplist;

    private String projectName;

    private Integer isSelect;

    private String oriProtectDays;

    private String changeProtectDays;

    private String userId;

    public BCluerule getbCluerule() {
        return bCluerule;
    }

    public void setbCluerule(BCluerule bCluerule) {
        this.bCluerule = bCluerule;
    }

    public String getIsUpdateGroup() {
        return IsUpdateGroup;
    }

    public void setIsUpdateGroup(String isUpdateGroup) {
        IsUpdateGroup = isUpdateGroup;
    }

    public String getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(String grouplist) {
        this.grouplist = grouplist;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }

    public String getOriProtectDays() {
        return oriProtectDays;
    }

    public void setOriProtectDays(String oriProtectDays) {
        this.oriProtectDays = oriProtectDays;
    }

    public String getChangeProtectDays() {
        return changeProtectDays;
    }

    public void setChangeProtectDays(String changeProtectDays) {
        this.changeProtectDays = changeProtectDays;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
