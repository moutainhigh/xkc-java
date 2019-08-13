package com.tahoecn.xkc.model.salegroup;

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
 * 树形结构：项目--团队--组
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@TableName("B_SalesGroup")
@ApiModel(value="BSalesgroup对象", description="树形结构：项目--团队--组")
public class BSalesgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "0=顶级，1=自建，2=代理")
    @TableField("Nature")
    private Integer Nature;

    @ApiModelProperty(value = "0=否，1=是")
    @TableField("IsDisable")
    private Integer IsDisable;

    @ApiModelProperty(value = "0=否，1=是")
    @TableField("IsDel")
    private Integer IsDel;

    @TableField("MemberNum")
    private Integer MemberNum;

    @TableField("SortNo")
    private Integer SortNo;

    @TableField("ProjectID")
    private String ProjectID;

    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("ShortName")
    private String ShortName;

    @TableField("Status")
    private Integer Status;

    @TableField("Editor")
    private String Editor;

    @TableField("Name")
    private String Name;

    @TableField("Creator")
    private String Creator;

    @TableField("PID")
    private String pid;

    @TableField("EditTime")
    private Date EditTime;

    public Integer getNature() {
        return Nature;
    }

    public void setNature(Integer Nature) {
        this.Nature = Nature;
    }
    public Integer getIsDisable() {
        return IsDisable;
    }

    public void setIsDisable(Integer IsDisable) {
        this.IsDisable = IsDisable;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Integer getMemberNum() {
        return MemberNum;
    }

    public void setMemberNum(Integer MemberNum) {
        this.MemberNum = MemberNum;
    }
    public Integer getSortNo() {
        return SortNo;
    }

    public void setSortNo(Integer SortNo) {
        this.SortNo = SortNo;
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
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
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
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }

    @Override
    public String toString() {
        return "BSalesgroup{" +
        "Nature=" + Nature +
        ", IsDisable=" + IsDisable +
        ", IsDel=" + IsDel +
        ", MemberNum=" + MemberNum +
        ", SortNo=" + SortNo +
        ", ProjectID=" + ProjectID +
        ", id=" + id +
        ", CreateTime=" + CreateTime +
        ", ShortName=" + ShortName +
        ", Status=" + Status +
        ", Editor=" + Editor +
        ", Name=" + Name +
        ", Creator=" + Creator +
        ", pid=" + pid +
        ", EditTime=" + EditTime +
        "}";
    }
}
