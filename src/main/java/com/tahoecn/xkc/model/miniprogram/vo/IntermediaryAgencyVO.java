package com.tahoecn.xkc.model.miniprogram.vo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  中介机构参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-20
 */
public class IntermediaryAgencyVO {

    private String orgId;

    private String orgCode;

    private String orgName;

    List<Map> projectList;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<Map> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Map> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "IntermediaryAgencyVO{" +
                "orgId='" + orgId + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", projectList=" + projectList +
                '}';
    }
}
