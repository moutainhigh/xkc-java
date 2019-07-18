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
 * @since 2019-07-18
 */
@TableName("A_ShareLog")
@ApiModel(value="ASharelog对象", description="")
public class ASharelog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ShareID")
    private String ShareID;

    @TableField("PagePath")
    private String PagePath;

    @TableField("WXUserID")
    private String WXUserID;

    @ApiModelProperty(value = "0.首页 1.项目 2.集团动态 3,项目动态")
    @TableField("Category")
    private Integer Category;

    @TableField("Remark")
    private String Remark;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @ApiModelProperty(value = "身份ID")
    @TableField("ShareWay")
    private Integer ShareWay;

    @ApiModelProperty(value = "身份ID")
    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

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
    public String getShareID() {
        return ShareID;
    }

    public void setShareID(String ShareID) {
        this.ShareID = ShareID;
    }
    public String getPagePath() {
        return PagePath;
    }

    public void setPagePath(String PagePath) {
        this.PagePath = PagePath;
    }
    public String getWXUserID() {
        return WXUserID;
    }

    public void setWXUserID(String WXUserID) {
        this.WXUserID = WXUserID;
    }
    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public Integer getShareWay() {
        return ShareWay;
    }

    public void setShareWay(Integer ShareWay) {
        this.ShareWay = ShareWay;
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
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
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
        return "ASharelog{" +
        "id=" + id +
        ", ShareID=" + ShareID +
        ", PagePath=" + PagePath +
        ", WXUserID=" + WXUserID +
        ", Category=" + Category +
        ", Remark=" + Remark +
        ", AdviserGroupID=" + AdviserGroupID +
        ", ShareWay=" + ShareWay +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
