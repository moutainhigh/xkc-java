package com.tahoecn.xkc.model.org;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@TableName("S_Organization")
@ApiModel(value="SOrganization对象", description="")
public class SOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ProductID")
    private String ProductID;

    @TableField("OrgCode")
    private String OrgCode;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Editor")
    private String Editor;

    @TableField("FullPath")
    private String FullPath;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("Creator")
    private String Creator;

    @TableField("CurrentPoint")
    private Double CurrentPoint;

    @TableField("OrgCategory")
    private Integer OrgCategory;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("OrgName")
    private String OrgName;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("AuthCompanyID")
    private String AuthCompanyID;

    @TableField("Status")
    private Integer Status;

    @TableField("OrgShortName")
    private String OrgShortName;

    @TableField("Levels")
    private Integer Levels;

    @TableField("PID")
    private String pid;

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String OrgCode) {
        this.OrgCode = OrgCode;
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
    public String getFullPath() {
        return FullPath;
    }

    public void setFullPath(String FullPath) {
        this.FullPath = FullPath;
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
    public Double getCurrentPoint() {
        return CurrentPoint;
    }

    public void setCurrentPoint(Double CurrentPoint) {
        this.CurrentPoint = CurrentPoint;
    }
    public Integer getOrgCategory() {
        return OrgCategory;
    }

    public void setOrgCategory(Integer OrgCategory) {
        this.OrgCategory = OrgCategory;
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
    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String OrgName) {
        this.OrgName = OrgName;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getAuthCompanyID() {
        return AuthCompanyID;
    }

    public void setAuthCompanyID(String AuthCompanyID) {
        this.AuthCompanyID = AuthCompanyID;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getOrgShortName() {
        return OrgShortName;
    }

    public void setOrgShortName(String OrgShortName) {
        this.OrgShortName = OrgShortName;
    }
    public Integer getLevels() {
        return Levels;
    }

    public void setLevels(Integer Levels) {
        this.Levels = Levels;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "SOrganization{" +
        "ProductID=" + ProductID +
        ", OrgCode=" + OrgCode +
        ", IsDel=" + IsDel +
        ", Editor=" + Editor +
        ", FullPath=" + FullPath +
        ", EditTime=" + EditTime +
        ", Creator=" + Creator +
        ", CurrentPoint=" + CurrentPoint +
        ", OrgCategory=" + OrgCategory +
        ", id=" + id +
        ", CreateTime=" + CreateTime +
        ", OrgName=" + OrgName +
        ", ListIndex=" + ListIndex +
        ", AuthCompanyID=" + AuthCompanyID +
        ", Status=" + Status +
        ", OrgShortName=" + OrgShortName +
        ", Levels=" + Levels +
        ", pid=" + pid +
        "}";
    }
}
