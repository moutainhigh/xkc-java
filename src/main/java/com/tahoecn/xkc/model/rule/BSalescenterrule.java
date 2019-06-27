package com.tahoecn.xkc.model.rule;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-06-25
 */
@TableName("B_SalesCenterRule")
@ApiModel(value="BSalescenterrule对象", description="")
public class BSalescenterrule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("OwnerReleasePeriod")
    private Integer OwnerReleasePeriod;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Creator")
    private String Creator;

    @TableField("Status")
    private Integer Status;

    @TableField("Editor")
    private String Editor;

    @TableField("AgentReleasePeriod")
    private Integer AgentReleasePeriod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Integer getOwnerReleasePeriod() {
        return OwnerReleasePeriod;
    }

    public void setOwnerReleasePeriod(Integer OwnerReleasePeriod) {
        this.OwnerReleasePeriod = OwnerReleasePeriod;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
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
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getAgentReleasePeriod() {
        return AgentReleasePeriod;
    }

    public void setAgentReleasePeriod(Integer AgentReleasePeriod) {
        this.AgentReleasePeriod = AgentReleasePeriod;
    }

    @Override
    public String toString() {
        return "BSalescenterrule{" +
        "id=" + id +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        ", OwnerReleasePeriod=" + OwnerReleasePeriod +
        ", ProjectID=" + ProjectID +
        ", EditTime=" + EditTime +
        ", Creator=" + Creator +
        ", Status=" + Status +
        ", Editor=" + Editor +
        ", AgentReleasePeriod=" + AgentReleasePeriod +
        "}";
    }
}
