package com.tahoecn.xkc.model.quit;

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
@TableName("B_QuitUser")
@ApiModel(value="BQuituser对象", description="")
public class BQuituser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID，渠道人员用不到它")
    @TableField("ProjectID")
    private String ProjectID;

    @ApiModelProperty(value = "1:自渠， 2:顾问， 3:机构。")
    @TableField("UserType")
    private Integer UserType;

    @ApiModelProperty(value = "用户ID")
    @TableField("UserID")
    private String UserID;

    @ApiModelProperty(value = "团队ID")
    @TableField("TeamID")
    private String TeamID;

    @ApiModelProperty(value = "团队名称")
    @TableField("TeamName")
    private String TeamName;

    @ApiModelProperty(value = "是否处理了 0：未处理； 1：已处理")
    @TableField("IsDispose")
    private Integer IsDispose;

    @ApiModelProperty(value = "0：禁用 1：启用")
    @TableField("Status")
    private Integer Status;

    @ApiModelProperty(value = "0 否 1是")
    @TableField("IsDel")
    private Integer IsDel;

    @TableId("ID")
    private String id;

    @TableField("Creator")
    private String Creator;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public Integer getUserType() {
        return UserType;
    }

    public void setUserType(Integer UserType) {
        this.UserType = UserType;
    }
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public String getTeamID() {
        return TeamID;
    }

    public void setTeamID(String TeamID) {
        this.TeamID = TeamID;
    }
    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }
    public Integer getIsDispose() {
        return IsDispose;
    }

    public void setIsDispose(Integer IsDispose) {
        this.IsDispose = IsDispose;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
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
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
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

    @Override
    public String toString() {
        return "BQuituser{" +
        "ProjectID=" + ProjectID +
        ", UserType=" + UserType +
        ", UserID=" + UserID +
        ", TeamID=" + TeamID +
        ", TeamName=" + TeamName +
        ", IsDispose=" + IsDispose +
        ", Status=" + Status +
        ", IsDel=" + IsDel +
        ", id=" + id +
        ", Creator=" + Creator +
        ", EditeTime=" + EditeTime +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        "}";
    }
}
