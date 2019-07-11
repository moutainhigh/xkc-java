package com.tahoecn.xkc.model.customer;

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
 * @since 2019-07-11
 */
@TableName("S_Task")
@ApiModel(value="STask对象", description="")
public class STask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("PID")
    private String pid;

    @TableField("TaskType")
    private String TaskType;

    @TableField("TaskPara")
    private String TaskPara;

    @TableField("TaskResult")
    private String TaskResult;

    @TableField("TaskVersion")
    private String TaskVersion;

    @TableField("Remark")
    private String Remark;

    @TableField("StartTime")
    private Date StartTime;

    @TableField("EndTime")
    private Date EndTime;

    @TableField("EndRemark")
    private String EndRemark;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("EditTime")
    private Date EditTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getTaskType() {
        return TaskType;
    }

    public void setTaskType(String TaskType) {
        this.TaskType = TaskType;
    }
    public String getTaskPara() {
        return TaskPara;
    }

    public void setTaskPara(String TaskPara) {
        this.TaskPara = TaskPara;
    }
    public String getTaskResult() {
        return TaskResult;
    }

    public void setTaskResult(String TaskResult) {
        this.TaskResult = TaskResult;
    }
    public String getTaskVersion() {
        return TaskVersion;
    }

    public void setTaskVersion(String TaskVersion) {
        this.TaskVersion = TaskVersion;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }
    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }
    public String getEndRemark() {
        return EndRemark;
    }

    public void setEndRemark(String EndRemark) {
        this.EndRemark = EndRemark;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }

    @Override
    public String toString() {
        return "STask{" +
        "id=" + id +
        ", pid=" + pid +
        ", TaskType=" + TaskType +
        ", TaskPara=" + TaskPara +
        ", TaskResult=" + TaskResult +
        ", TaskVersion=" + TaskVersion +
        ", Remark=" + Remark +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", EndRemark=" + EndRemark +
        ", CreateTime=" + CreateTime +
        ", EditTime=" + EditTime +
        "}";
    }
}
