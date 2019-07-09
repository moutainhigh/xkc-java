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
 * 树形结构：项目--团队--组
 * </p>
 *
 * @author YYY
 * @since 2019-07-09
 */
@TableName("B_SalesGroup")
@ApiModel(value="BSalesgroup对象", description="树形结构：项目--团队--组")
public class BSalesgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("PID")
    private String pid;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("Name")
    private String Name;

    @ApiModelProperty(value = "0=顶级，1=自建，2=代理")
    @TableField("Nature")
    private Integer Nature;

    @TableField("MemberNum")
    private Integer MemberNum;

    @ApiModelProperty(value = "0=否，1=是")
    @TableField("IsDisable")
    private Integer IsDisable;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @ApiModelProperty(value = "0=否，1=是")
    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("SortNo")
    private Integer SortNo;

    @TableField("ShortName")
    private String ShortName;

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
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Integer getNature() {
        return Nature;
    }

    public void setNature(Integer Nature) {
        this.Nature = Nature;
    }
    public Integer getMemberNum() {
        return MemberNum;
    }

    public void setMemberNum(Integer MemberNum) {
        this.MemberNum = MemberNum;
    }
    public Integer getIsDisable() {
        return IsDisable;
    }

    public void setIsDisable(Integer IsDisable) {
        this.IsDisable = IsDisable;
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
    public Integer getSortNo() {
        return SortNo;
    }

    public void setSortNo(Integer SortNo) {
        this.SortNo = SortNo;
    }
    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }

    @Override
    public String toString() {
        return "BSalesgroup{" +
        "id=" + id +
        ", pid=" + pid +
        ", ProjectID=" + ProjectID +
        ", Name=" + Name +
        ", Nature=" + Nature +
        ", MemberNum=" + MemberNum +
        ", IsDisable=" + IsDisable +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", SortNo=" + SortNo +
        ", ShortName=" + ShortName +
        "}";
    }
}
