package com.tahoecn.xkc.model.miniprogram.vo.allocation;


import java.util.List;

/**
 * <p>
 *  思为小程序案场分配参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
public class CourtCaseVO {

    private String adviserId;

    private List<String> adviserIdList;

    private String code;

    private List<String> lostIdList;

    private List<String> opportunityIdList;

    private List<String> publicIdList;

    private String jobCode;

    private String jobId;

    private String orgId;

    private String projectId;

    private String userId;

    private String userTrueName;

    public String getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public List<String> getAdviserIdList() {
        return adviserIdList;
    }

    public void setAdviserIdList(List<String> adviserIdList) {
        this.adviserIdList = adviserIdList;
    }

    public List<String> getLostIdList() {
        return lostIdList;
    }

    public void setLostIdList(List<String> lostIdList) {
        this.lostIdList = lostIdList;
    }

    public List<String> getOpportunityIdList() {
        return opportunityIdList;
    }

    public void setOpportunityIdList(List<String> opportunityIdList) {
        this.opportunityIdList = opportunityIdList;
    }

    public List<String> getPublicIdList() {
        return publicIdList;
    }

    public void setPublicIdList(List<String> publicIdList) {
        this.publicIdList = publicIdList;
    }
}
