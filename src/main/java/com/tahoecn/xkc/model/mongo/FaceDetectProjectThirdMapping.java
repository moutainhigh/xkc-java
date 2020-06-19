package com.tahoecn.xkc.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/16 11:25
 */

@Document(collection = "faceDetectProjectThirdMapping")
public class FaceDetectProjectThirdMapping {

    @Id
    private String id;

    private String projectName;

    private String projectId;

    private String groupName;

    private String outerProjectId;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getOuterProjectId() {
        return outerProjectId;
    }

    public void setOuterProjectId(String outerProjectId) {
        this.outerProjectId = outerProjectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
