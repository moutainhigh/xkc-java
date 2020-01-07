package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-07 10:35
 * @Description: //TODO app指定分配假报备客户新增VO
 **/
public class APPAssignmentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 当前登录人的id */
    private String userId;

    /* 当前登录人所属项目id */
    private String projectId;

    /* 当前登录人的jobId */
    private String jobId;

    /* 所指定的置业顾问ID*/
    private String adviserId;

    /* 待新增的假报备*/
    private List<AppAssignmentPrameterVO> sharePoolVOList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }

    public List<AppAssignmentPrameterVO> getSharePoolVOList() {
        return sharePoolVOList;
    }

    public void setSharePoolVOList(List<AppAssignmentPrameterVO> sharePoolVOList) {
        this.sharePoolVOList = sharePoolVOList;
    }
}
