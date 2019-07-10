package com.tahoecn.xkc.model.job;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@TableName("B_ProjectJobRel")
@ApiModel(value="BProjectjobrel对象", description="")
public class BProjectjobrel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("Editor")
    private String Editor;

    @TableId("ID")
    private String id;

    @TableField("Status")
    private Integer Status;

    @TableField("Creator")
    private String Creator;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("JobID")
    private String JobID;

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getJobID() {
        return JobID;
    }

    public void setJobID(String JobID) {
        this.JobID = JobID;
    }

    @Override
    public String toString() {
        return "BProjectjobrel{" +
        "ProjectID=" + ProjectID +
        ", Editor=" + Editor +
        ", id=" + id +
        ", Status=" + Status +
        ", Creator=" + Creator +
        ", EditTime=" + EditTime +
        ", CreateTime=" + CreateTime +
        ", IsDel=" + IsDel +
        ", JobID=" + JobID +
        "}";
    }
}
