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
 * @since 2019-07-18
 */
@TableName("A_SharePool")
@ApiModel(value="ASharepool对象", description="")
public class ASharepool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("WXUserID")
    private String WXUserID;

    @ApiModelProperty(value = "谁分享的")
    @TableField("ShareWXUserID")
    private String ShareWXUserID;

    @TableField("ClueID")
    private String ClueID;

    @TableField("Mobile")
    private String Mobile;

    @TableField("ShareProjectID")
    private String ShareProjectID;

    @ApiModelProperty(value = "0.媒体子类二维码 1.自然访客 2.置业顾问  3.自渠  4.案场管理人员  5.中介同行 6.中介同行负责人")
    @TableField("Category")
    private Integer Category;

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
    public String getWXUserID() {
        return WXUserID;
    }

    public void setWXUserID(String WXUserID) {
        this.WXUserID = WXUserID;
    }
    public String getShareWXUserID() {
        return ShareWXUserID;
    }

    public void setShareWXUserID(String ShareWXUserID) {
        this.ShareWXUserID = ShareWXUserID;
    }
    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getShareProjectID() {
        return ShareProjectID;
    }

    public void setShareProjectID(String ShareProjectID) {
        this.ShareProjectID = ShareProjectID;
    }
    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
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
        return "ASharepool{" +
        "id=" + id +
        ", WXUserID=" + WXUserID +
        ", ShareWXUserID=" + ShareWXUserID +
        ", ClueID=" + ClueID +
        ", Mobile=" + Mobile +
        ", ShareProjectID=" + ShareProjectID +
        ", Category=" + Category +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
