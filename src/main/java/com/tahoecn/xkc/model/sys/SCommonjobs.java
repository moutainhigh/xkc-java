package com.tahoecn.xkc.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@TableName("S_CommonJobs")
@ApiModel(value="SCommonjobs对象", description="")
public class SCommonjobs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Creator")
    private String Creator;

    @TableField("JobCode")
    private String JobCode;

    @TableField("Status")
    private Integer Status;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("AuthCompanyID")
    private String AuthCompanyID;

    @TableField("ProductID")
    private String ProductID;

    @TableField("JobName")
    private String JobName;

    @TableField("Editor")
    private String Editor;

    @TableField("JobDesc")
    private String JobDesc;

    @TableField("IsDel")
    private Integer IsDel;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getJobCode() {
        return JobCode;
    }

    public void setJobCode(String JobCode) {
        this.JobCode = JobCode;
    }
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
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    public String getJobName() {
        return JobName;
    }

    public void setJobName(String JobName) {
        this.JobName = JobName;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getJobDesc() {
        return JobDesc;
    }

    public void setJobDesc(String JobDesc) {
        this.JobDesc = JobDesc;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SCommonjobs{" +
        "EditTime=" + EditTime +
        ", Creator=" + Creator +
        ", JobCode=" + JobCode +
        ", Status=" + Status +
        ", CreateTime=" + CreateTime +
        ", AuthCompanyID=" + AuthCompanyID +
        ", ProductID=" + ProductID +
        ", JobName=" + JobName +
        ", Editor=" + Editor +
        ", JobDesc=" + JobDesc +
        ", IsDel=" + IsDel +
        ", id=" + id +
        "}";
    }
}
