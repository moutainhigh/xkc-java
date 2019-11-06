package com.tahoecn.xkc.model.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@TableName("B_SystemAD")
@ApiModel(value="BSystemad对象", description="")
public class BSystemad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Title")
    private String Title;

    @TableField("Creator")
    private String Creator;

    @TableField("ShareContent")
    private String ShareContent;

    @TableField("PictureURL")
    private String PictureURL;

    @TableField("ShareBanerURL")
    private String ShareBanerURL;

    @TableField("Status")
    private Integer Status;

    @TableField("PicturePath")
    private String PicturePath;

    @TableField("Editor")
    private String Editor;

    @TableField("SortNo")
    private Integer SortNo;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("ADType")
    private Integer ADType;

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
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getShareContent() {
        return ShareContent;
    }

    public void setShareContent(String ShareContent) {
        this.ShareContent = ShareContent;
    }
    public String getPictureURL() {
        return PictureURL;
    }

    public void setPictureURL(String PictureURL) {
        this.PictureURL = PictureURL;
    }
    public String getShareBanerURL() {
        return ShareBanerURL;
    }

    public void setShareBanerURL(String ShareBanerURL) {
        this.ShareBanerURL = ShareBanerURL;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String PicturePath) {
        this.PicturePath = PicturePath;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getSortNo() {
        return SortNo;
    }

    public void setSortNo(Integer SortNo) {
        this.SortNo = SortNo;
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
    public Integer getADType() {
        return ADType;
    }

    public void setADType(Integer ADType) {
        this.ADType = ADType;
    }

    @Override
    public String toString() {
        return "BSystemad{" +
        "id=" + id +
        ", EditTime=" + EditTime +
        ", Title=" + Title +
        ", Creator=" + Creator +
        ", ShareContent=" + ShareContent +
        ", PictureURL=" + PictureURL +
        ", ShareBanerURL=" + ShareBanerURL +
        ", Status=" + Status +
        ", PicturePath=" + PicturePath +
        ", Editor=" + Editor +
        ", SortNo=" + SortNo +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        ", ADType=" + ADType +
        "}";
    }
}
