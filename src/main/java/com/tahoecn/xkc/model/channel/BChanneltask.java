package com.tahoecn.xkc.model.channel;

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
 * @since 2019-07-04
 */
@TableName("B_ChannelTask")
@ApiModel(value="BChanneltask对象", description="")
public class BChanneltask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1=未开始,2=进行中,3=已结束")
    @TableField("Status")
    private Integer Status;

    @TableField("Name")
    private String Name;

    @TableField("StartTime")
    private Date StartTime;

    @TableField("TaskAreaName")
    private String TaskAreaName;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("TaskType")
    private String TaskType;

    @TableField("WorkRange")
    private Integer WorkRange;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableId("ID")
    private String id;

    @TableField("Editor")
    private String Editor;

    @TableField("EndTime")
    private Date EndTime;

    @TableField("WorkStartTime")
    private Date WorkStartTime;

    @TableField("CustomerTarget")
    private Integer CustomerTarget;

    @TableField("TaskAreaID")
    private String TaskAreaID;

    @TableField("WorkEndTime")
    private Date WorkEndTime;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("Creator")
    private String Creator;

    @TableField("TaskCode")
    private String TaskCode;

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }
    public String getTaskAreaName() {
        return TaskAreaName;
    }

    public void setTaskAreaName(String TaskAreaName) {
        this.TaskAreaName = TaskAreaName;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getTaskType() {
        return TaskType;
    }

    public void setTaskType(String TaskType) {
        this.TaskType = TaskType;
    }
    public Integer getWorkRange() {
        return WorkRange;
    }

    public void setWorkRange(Integer WorkRange) {
        this.WorkRange = WorkRange;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }
    public Date getWorkStartTime() {
        return WorkStartTime;
    }

    public void setWorkStartTime(Date WorkStartTime) {
        this.WorkStartTime = WorkStartTime;
    }
    public Integer getCustomerTarget() {
        return CustomerTarget;
    }

    public void setCustomerTarget(Integer CustomerTarget) {
        this.CustomerTarget = CustomerTarget;
    }
    public String getTaskAreaID() {
        return TaskAreaID;
    }

    public void setTaskAreaID(String TaskAreaID) {
        this.TaskAreaID = TaskAreaID;
    }
    public Date getWorkEndTime() {
        return WorkEndTime;
    }

    public void setWorkEndTime(Date WorkEndTime) {
        this.WorkEndTime = WorkEndTime;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getTaskCode() {
        return TaskCode;
    }

    public void setTaskCode(String TaskCode) {
        this.TaskCode = TaskCode;
    }

    @Override
    public String toString() {
        return "BChanneltask{" +
        "Status=" + Status +
        ", Name=" + Name +
        ", StartTime=" + StartTime +
        ", TaskAreaName=" + TaskAreaName +
        ", EditTime=" + EditTime +
        ", TaskType=" + TaskType +
        ", WorkRange=" + WorkRange +
        ", CreateTime=" + CreateTime +
        ", IsDel=" + IsDel +
        ", id=" + id +
        ", Editor=" + Editor +
        ", EndTime=" + EndTime +
        ", WorkStartTime=" + WorkStartTime +
        ", CustomerTarget=" + CustomerTarget +
        ", TaskAreaID=" + TaskAreaID +
        ", WorkEndTime=" + WorkEndTime +
        ", ProjectID=" + ProjectID +
        ", Creator=" + Creator +
        ", TaskCode=" + TaskCode +
        "}";
    }
}
