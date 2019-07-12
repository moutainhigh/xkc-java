package com.tahoecn.xkc.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-06-17
 */
@TableName("S_Menus")
@ApiModel(value="SMenus对象", description="")
public class SMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("MenuSysName")
    private String MenuSysName;

    @TableId(value = "ID",type = IdType.UUID)
    private String ID;

    @TableField("IsLast")
    private Integer IsLast;

    @TableField("Levels")
    private Integer Levels;

    @TableField("IconClass")
    private String IconClass;

    @TableField("Status")
    private Integer Status;

    @TableField("IsShow")
    private Integer IsShow;

    @TableField("ImageUrl")
    private String ImageUrl;

    @TableField("FullPath")
    private String FullPath;

    @TableField("MenuName")
    private String MenuName;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsHomePage")
    private Integer IsHomePage;

    @TableField("Creator")
    private String Creator;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("Url")
    private String Url;

    @TableField("PID")
    private String pid;

    @TableField("Editor")
    private String Editor;

    @TableField("IsDel")
    private Integer IsDel;

    public String getMenuSysName() {
        return MenuSysName;
    }

    public void setMenuSysName(String MenuSysName) {
        this.MenuSysName = MenuSysName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getIsLast() {
        return IsLast;
    }

    public void setIsLast(Integer IsLast) {
        this.IsLast = IsLast;
    }
    public Integer getLevels() {
        return Levels;
    }

    public void setLevels(Integer Levels) {
        this.Levels = Levels;
    }
    public String getIconClass() {
        return IconClass;
    }

    public void setIconClass(String IconClass) {
        this.IconClass = IconClass;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getIsShow() {
        return IsShow;
    }

    public void setIsShow(Integer IsShow) {
        this.IsShow = IsShow;
    }
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }
    public String getFullPath() {
        return FullPath;
    }

    public void setFullPath(String FullPath) {
        this.FullPath = FullPath;
    }
    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String MenuName) {
        this.MenuName = MenuName;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Integer getIsHomePage() {
        return IsHomePage;
    }

    public void setIsHomePage(Integer IsHomePage) {
        this.IsHomePage = IsHomePage;
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
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }

    @Override
    public String toString() {
        return "SMenus{" +
                "MenuSysName='" + MenuSysName + '\'' +
                ", ID='" + ID + '\'' +
                ", IsLast=" + IsLast +
                ", Levels=" + Levels +
                ", IconClass='" + IconClass + '\'' +
                ", Status=" + Status +
                ", IsShow=" + IsShow +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", FullPath='" + FullPath + '\'' +
                ", MenuName='" + MenuName + '\'' +
                ", CreateTime=" + CreateTime +
                ", IsHomePage=" + IsHomePage +
                ", Creator='" + Creator + '\'' +
                ", EditTime=" + EditTime +
                ", ListIndex=" + ListIndex +
                ", Url='" + Url + '\'' +
                ", pid='" + pid + '\'' +
                ", Editor='" + Editor + '\'' +
                ", IsDel=" + IsDel +
                '}';
    }
}
