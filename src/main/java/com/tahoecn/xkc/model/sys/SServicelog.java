package com.tahoecn.xkc.model.sys;

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
 * @since 2019-07-25
 */
@TableName("S_ServiceLog")
@ApiModel(value="SServicelog对象", description="")
public class SServicelog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ServiceID")
    private String ServiceID;

    @TableField("StartRunTime")
    private Date StartRunTime;

    @TableField("EndRunTime")
    private Date EndRunTime;

    @TableField("FailureReasons")
    private String FailureReasons;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "1.成功 2.失败")
    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String ServiceID) {
        this.ServiceID = ServiceID;
    }
    public Date getStartRunTime() {
        return StartRunTime;
    }

    public void setStartRunTime(Date StartRunTime) {
        this.StartRunTime = StartRunTime;
    }
    public Date getEndRunTime() {
        return EndRunTime;
    }

    public void setEndRunTime(Date EndRunTime) {
        this.EndRunTime = EndRunTime;
    }
    public String getFailureReasons() {
        return FailureReasons;
    }

    public void setFailureReasons(String FailureReasons) {
        this.FailureReasons = FailureReasons;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "SServicelog{" +
        "id=" + id +
        ", ServiceID=" + ServiceID +
        ", StartRunTime=" + StartRunTime +
        ", EndRunTime=" + EndRunTime +
        ", FailureReasons=" + FailureReasons +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
