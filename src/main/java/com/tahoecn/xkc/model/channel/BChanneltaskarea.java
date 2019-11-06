package com.tahoecn.xkc.model.channel;

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
@TableName("B_ChannelTaskArea")
@ApiModel(value="BChanneltaskarea对象", description="")
public class BChanneltaskarea implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Longitude")
    private Double Longitude;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Editor")
    private String Editor;

    @TableField("Status")
    private Integer Status;

    @TableId("ID")
    private String id;

    @TableField("Dimension")
    private Double Dimension;

    @TableField("Name")
    private String Name;

    @TableField("Creator")
    private String Creator;

    @TableField("EditTime")
    private Date EditTime;

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Double getDimension() {
        return Dimension;
    }

    public void setDimension(Double Dimension) {
        this.Dimension = Dimension;
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
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }

    @Override
    public String toString() {
        return "BChanneltaskarea{" +
        "Longitude=" + Longitude +
        ", CreateTime=" + CreateTime +
        ", IsDel=" + IsDel +
        ", Editor=" + Editor +
        ", Status=" + Status +
        ", id=" + id +
        ", Dimension=" + Dimension +
        ", Name=" + Name +
        ", Creator=" + Creator +
        ", EditTime=" + EditTime +
        "}";
    }
}
