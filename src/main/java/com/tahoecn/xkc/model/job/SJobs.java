package com.tahoecn.xkc.model.job;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("S_Jobs")
@ApiModel(value="SJobs对象", description="")
public class SJobs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Status")
    private Integer Status;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("AuthCompanyID")
    private String AuthCompanyID;

    @TableField("Editor")
    private String Editor;

    @TableField("ProductID")
    private String ProductID;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CommonJobID")
    private String CommonJobID;

    @TableField("JobName")
    private String JobName;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("Creator")
    private String Creator;

    @TableField("JobOrgID")
    private String JobOrgID;

    @TableField("JobDesc")
    private String JobDesc;

    @TableField("JobPID")
    private String JobPID;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("JobCode")
    private String JobCode;

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getAuthCompanyID() {
        return AuthCompanyID;
    }

    public void setAuthCompanyID(String AuthCompanyID) {
        this.AuthCompanyID = AuthCompanyID;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getCommonJobID() {
        return CommonJobID;
    }

    public void setCommonJobID(String CommonJobID) {
        this.CommonJobID = CommonJobID;
    }
    public String getJobName() {
        return JobName;
    }

    public void setJobName(String JobName) {
        this.JobName = JobName;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getJobOrgID() {
        return JobOrgID;
    }

    public void setJobOrgID(String JobOrgID) {
        this.JobOrgID = JobOrgID;
    }
    public String getJobDesc() {
        return JobDesc;
    }

    public void setJobDesc(String JobDesc) {
        this.JobDesc = JobDesc;
    }
    public String getJobPID() {
        return JobPID;
    }

    public void setJobPID(String JobPID) {
        this.JobPID = JobPID;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getJobCode() {
        return JobCode;
    }

    public void setJobCode(String JobCode) {
        this.JobCode = JobCode;
    }

    @Override
    public String toString() {
        return "SJobs{" +
        "Status=" + Status +
        ", CreateTime=" + CreateTime +
        ", AuthCompanyID=" + AuthCompanyID +
        ", Editor=" + Editor +
        ", ProductID=" + ProductID +
        ", IsDel=" + IsDel +
        ", CommonJobID=" + CommonJobID +
        ", JobName=" + JobName +
        ", id=" + id +
        ", Creator=" + Creator +
        ", JobOrgID=" + JobOrgID +
        ", JobDesc=" + JobDesc +
        ", JobPID=" + JobPID +
        ", EditTime=" + EditTime +
        ", JobCode=" + JobCode +
        "}";
    }
}
