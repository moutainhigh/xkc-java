package com.tahoecn.xkc.model.dict;

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
 * @since 2019-06-27
 */
@TableName("S_Dictionary")
@ApiModel(value="SDictionary对象", description="")
public class SDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("AuthCompanyID")
    private String AuthCompanyID;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("IsReadOnly")
    private Integer IsReadOnly;

    @TableField("Ext2")
    private String Ext2;

    @TableField("Status")
    private Integer Status;

    @TableField("Levels")
    private Integer Levels;

    @TableField("DictCode")
    private String DictCode;

    @TableField("ProductID")
    private String ProductID;

    @TableField("FullPath")
    private String FullPath;

    @TableField("Ext3")
    private String Ext3;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Remark")
    private String Remark;

    @TableField("Ext1")
    private String Ext1;

    @TableField("Editor")
    private String Editor;

    @TableField("DictName")
    private String DictName;

    @TableField("PID")
    private String pid;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("Ext4")
    private String Ext4;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("DictType")
    private Integer DictType;

    @TableField("Creator")
    private String Creator;

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getAuthCompanyID() {
        return AuthCompanyID;
    }

    public void setAuthCompanyID(String AuthCompanyID) {
        this.AuthCompanyID = AuthCompanyID;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getIsReadOnly() {
        return IsReadOnly;
    }

    public void setIsReadOnly(Integer IsReadOnly) {
        this.IsReadOnly = IsReadOnly;
    }
    public String getExt2() {
        return Ext2;
    }

    public void setExt2(String Ext2) {
        this.Ext2 = Ext2;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getLevels() {
        return Levels;
    }

    public void setLevels(Integer Levels) {
        this.Levels = Levels;
    }
    public String getDictCode() {
        return DictCode;
    }

    public void setDictCode(String DictCode) {
        this.DictCode = DictCode;
    }
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    public String getFullPath() {
        return FullPath;
    }

    public void setFullPath(String FullPath) {
        this.FullPath = FullPath;
    }
    public String getExt3() {
        return Ext3;
    }

    public void setExt3(String Ext3) {
        this.Ext3 = Ext3;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getExt1() {
        return Ext1;
    }

    public void setExt1(String Ext1) {
        this.Ext1 = Ext1;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getDictName() {
        return DictName;
    }

    public void setDictName(String DictName) {
        this.DictName = DictName;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getExt4() {
        return Ext4;
    }

    public void setExt4(String Ext4) {
        this.Ext4 = Ext4;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public Integer getDictType() {
        return DictType;
    }

    public void setDictType(Integer DictType) {
        this.DictType = DictType;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }

    @Override
    public String toString() {
        return "SDictionary{" +
        "CreateTime=" + CreateTime +
        ", AuthCompanyID=" + AuthCompanyID +
        ", id=" + id +
        ", IsReadOnly=" + IsReadOnly +
        ", Ext2=" + Ext2 +
        ", Status=" + Status +
        ", Levels=" + Levels +
        ", DictCode=" + DictCode +
        ", ProductID=" + ProductID +
        ", FullPath=" + FullPath +
        ", Ext3=" + Ext3 +
        ", IsDel=" + IsDel +
        ", Remark=" + Remark +
        ", Ext1=" + Ext1 +
        ", Editor=" + Editor +
        ", DictName=" + DictName +
        ", pid=" + pid +
        ", ListIndex=" + ListIndex +
        ", Ext4=" + Ext4 +
        ", EditTime=" + EditTime +
        ", DictType=" + DictType +
        ", Creator=" + Creator +
        "}";
    }
}
