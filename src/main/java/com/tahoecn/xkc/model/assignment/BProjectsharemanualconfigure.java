package com.tahoecn.xkc.model.assignment;

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
 * @since 2020-01-06
 */
@TableName("B_ProjectShareManualConfigure")
@ApiModel(value="BProjectsharemanualconfigure对象", description="")
public class BProjectsharemanualconfigure implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("MediaChildID")
    private String MediaChildID;

    @TableField("RoleID")
    private String RoleID;

    @TableField("RoleName")
    private String RoleName;

    @TableField("DistributionUserRoleID")
    private String DistributionUserRoleID;

    @TableField("DistributionUserRoleName")
    private String DistributionUserRoleName;

    @TableField("DistributionUserID")
    private String DistributionUserID;

    @TableField("DistributionUserName")
    private String DistributionUserName;

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

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getMediaChildID() {
        return MediaChildID;
    }

    public void setMediaChildID(String MediaChildID) {
        this.MediaChildID = MediaChildID;
    }
    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }
    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    public String getDistributionUserRoleID() {
        return DistributionUserRoleID;
    }

    public void setDistributionUserRoleID(String DistributionUserRoleID) {
        this.DistributionUserRoleID = DistributionUserRoleID;
    }
    public String getDistributionUserRoleName() {
        return DistributionUserRoleName;
    }

    public void setDistributionUserRoleName(String DistributionUserRoleName) {
        this.DistributionUserRoleName = DistributionUserRoleName;
    }
    public String getDistributionUserID() {
        return DistributionUserID;
    }

    public void setDistributionUserID(String DistributionUserID) {
        this.DistributionUserID = DistributionUserID;
    }
    public String getDistributionUserName() {
        return DistributionUserName;
    }

    public void setDistributionUserName(String DistributionUserName) {
        this.DistributionUserName = DistributionUserName;
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
        return "BProjectsharemanualconfigure{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", MediaChildID=" + MediaChildID +
        ", RoleID=" + RoleID +
        ", RoleName=" + RoleName +
        ", DistributionUserRoleID=" + DistributionUserRoleID +
        ", DistributionUserRoleName=" + DistributionUserRoleName +
        ", DistributionUserID=" + DistributionUserID +
        ", DistributionUserName=" + DistributionUserName +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
