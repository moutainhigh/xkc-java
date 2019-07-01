package com.tahoecn.xkc.model.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("S_AppDevice")
@ApiModel(value="SAppdevice对象", description="")
public class SAppdevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("LastUserID")
    private String LastUserID;

    @TableField("Model")
    private String Model;

    @TableField("DeviceCode")
    private String DeviceCode;

    @TableField("JPushAlias")
    private String JPushAlias;

    @TableField("AppName")
    private String AppName;

    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Creator")
    private String Creator;

    @TableField("Status")
    private Integer Status;

    @TableField("Editor")
    private String Editor;

    @TableField("LastUserName")
    private String LastUserName;

    @TableField("JPushID")
    private String JPushID;

    @TableField("Platform")
    private String Platform;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getLastUserID() {
        return LastUserID;
    }

    public void setLastUserID(String LastUserID) {
        this.LastUserID = LastUserID;
    }
    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }
    public String getDeviceCode() {
        return DeviceCode;
    }

    public void setDeviceCode(String DeviceCode) {
        this.DeviceCode = DeviceCode;
    }
    public String getJPushAlias() {
        return JPushAlias;
    }

    public void setJPushAlias(String JPushAlias) {
        this.JPushAlias = JPushAlias;
    }
    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
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
    public String getLastUserName() {
        return LastUserName;
    }

    public void setLastUserName(String LastUserName) {
        this.LastUserName = LastUserName;
    }
    public String getJPushID() {
        return JPushID;
    }

    public void setJPushID(String JPushID) {
        this.JPushID = JPushID;
    }
    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String Platform) {
        this.Platform = Platform;
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

    @Override
    public String toString() {
        return "SAppdevice{" +
        "LastUserID=" + LastUserID +
        ", Model=" + Model +
        ", DeviceCode=" + DeviceCode +
        ", JPushAlias=" + JPushAlias +
        ", AppName=" + AppName +
        ", id=" + id +
        ", EditTime=" + EditTime +
        ", Creator=" + Creator +
        ", Status=" + Status +
        ", Editor=" + Editor +
        ", LastUserName=" + LastUserName +
        ", JPushID=" + JPushID +
        ", Platform=" + Platform +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
