package com.tahoecn.xkc.model.miniprogram.vo.allocation;


/**
 * <p>
 *  思为小程序自渠分配参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
public class ChannelVO {

    private String newUserId;

    private String oldUserId;

    private String userDisName;

    private String jobCode;

    private String jobId;

    private String orgId;

    private String projectId;

    private String userId;

    private String userTrueName;

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    public String getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(String oldUserId) {
        this.oldUserId = oldUserId;
    }

    public String getUserDisName() {
        return userDisName;
    }

    public void setUserDisName(String userDisName) {
        this.userDisName = userDisName;
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
}
