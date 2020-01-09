package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-07 16:30
 * @Description: //TODO 极光推送对外接口参数VO
 **/
public class APPSendJPushVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 当前登录人的id */
    private String userId;

    /* 当前登录人所属项目id */
    private String projectId;

    /* 当前登录人的jobId */
    private String jobId;

    /* 通知内容标题*/
    private String notificationTitle;

    /* 消息内容标题*/
    private String msgTitle;

    /* 消息内容*/
    private String msgContent;

    /* 扩展字段*/
    private String extras;

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

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
