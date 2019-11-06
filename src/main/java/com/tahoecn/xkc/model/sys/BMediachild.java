package com.tahoecn.xkc.model.sys;

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
 * @since 2019-08-06
 */
@TableName("B_MediaChild")
@ApiModel(value="BMediachild对象", description="")
public class BMediachild implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("MediaLargeID")
    private String MediaLargeID;

    @TableField("Name")
    private String Name;

    @TableField("ShortName")
    private String ShortName;

    @TableField("ListIndex")
    private Integer ListIndex;

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

    @TableField("QRCodeImg")
    private String QRCodeImg;

    @TableField("Desc")
    private String Desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public String getMediaLargeID() {
        return MediaLargeID;
    }

    public void setMediaLargeID(String mediaLargeID) {
        MediaLargeID = mediaLargeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer listIndex) {
        ListIndex = listIndex;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getEditor() {
        return Editor;
    }

    public void setEditor(String editor) {
        Editor = editor;
    }

    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date editTime) {
        EditTime = editTime;
    }

    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer isDel) {
        IsDel = isDel;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getQRCodeImg() {
        return QRCodeImg;
    }

    public void setQRCodeImg(String QRCodeImg) {
        this.QRCodeImg = QRCodeImg;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    @Override
    public String toString() {
        return "BMediachild{" +
                "id='" + id + '\'' +
                ", ProjectID='" + ProjectID + '\'' +
                ", MediaLargeID='" + MediaLargeID + '\'' +
                ", Name='" + Name + '\'' +
                ", ShortName='" + ShortName + '\'' +
                ", ListIndex=" + ListIndex +
                ", Creator='" + Creator + '\'' +
                ", CreateTime=" + CreateTime +
                ", Editor='" + Editor + '\'' +
                ", EditTime=" + EditTime +
                ", IsDel=" + IsDel +
                ", Status=" + Status +
                ", QRCodeImg='" + QRCodeImg + '\'' +
                ", Desc='" + Desc + '\'' +
                '}';
    }
}
