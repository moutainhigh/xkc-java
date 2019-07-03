package com.tahoecn.xkc.model.channel;

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
 * @since 2019-06-25
 */
@TableName("B_PojectChannelOrgRel")
@ApiModel(value="BPojectchannelorgrel对象", description="")
public class BPojectchannelorgrel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Status")
    private Integer Status;

    @TableField("Editor")
    private String Editor;

    @TableField("ProjectID")
    private String ProjectID;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("OrgID")
    private String OrgID;

    @TableField("IsDel")
    private Integer IsDel;

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
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }

    @Override
    public String toString() {
        return "BPojectchannelorgrel{" +
        "Status=" + Status +
        ", Editor=" + Editor +
        ", ProjectID=" + ProjectID +
        ", id=" + id +
        ", EditTime=" + EditTime +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", OrgID=" + OrgID +
        ", IsDel=" + IsDel +
        "}";
    }
}
