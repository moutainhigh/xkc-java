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
 * @since 2019-07-12
 */
@TableName("S_Menus_xkc")
@ApiModel(value="SMenusXkc对象", description="")
public class SMenusXkc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("PID")
    private String pid;

    @TableField("MenuName")
    private String MenuName;

    @TableField("MenuSysName")
    private String MenuSysName;

    @TableField("Url")
    private String Url;

    @TableField("ImageUrl")
    private String ImageUrl;

    @TableField("IconClass")
    private String IconClass;

    @TableField("IsHomePage")
    private Integer IsHomePage;

    @TableField("IsShow")
    private Integer IsShow;

    @TableField("Levels")
    private Integer Levels;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("FullPath")
    private String FullPath;

    @TableField("IsLast")
    private Integer IsLast;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Status")
    private Integer Status;

    @TableField("IsDel")
    private Integer IsDel;

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
    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String MenuName) {
        this.MenuName = MenuName;
    }
    public String getMenuSysName() {
        return MenuSysName;
    }

    public void setMenuSysName(String MenuSysName) {
        this.MenuSysName = MenuSysName;
    }
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }
    public String getIconClass() {
        return IconClass;
    }

    public void setIconClass(String IconClass) {
        this.IconClass = IconClass;
    }
    public Integer getIsHomePage() {
        return IsHomePage;
    }

    public void setIsHomePage(Integer IsHomePage) {
        this.IsHomePage = IsHomePage;
    }
    public Integer getIsShow() {
        return IsShow;
    }

    public void setIsShow(Integer IsShow) {
        this.IsShow = IsShow;
    }
    public Integer getLevels() {
        return Levels;
    }

    public void setLevels(Integer Levels) {
        this.Levels = Levels;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getFullPath() {
        return FullPath;
    }

    public void setFullPath(String FullPath) {
        this.FullPath = FullPath;
    }
    public Integer getIsLast() {
        return IsLast;
    }

    public void setIsLast(Integer IsLast) {
        this.IsLast = IsLast;
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

    @Override
    public String toString() {
        return "SMenusXkc{" +
        "id=" + id +
        ", pid=" + pid +
        ", MenuName=" + MenuName +
        ", MenuSysName=" + MenuSysName +
        ", Url=" + Url +
        ", ImageUrl=" + ImageUrl +
        ", IconClass=" + IconClass +
        ", IsHomePage=" + IsHomePage +
        ", IsShow=" + IsShow +
        ", Levels=" + Levels +
        ", ListIndex=" + ListIndex +
        ", FullPath=" + FullPath +
        ", IsLast=" + IsLast +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", Status=" + Status +
        ", IsDel=" + IsDel +
        "}";
    }
}
