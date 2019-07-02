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
@TableName("S_Logs")
@ApiModel(value="SLogs对象", description="")
public class SLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("IP")
    private String ip;

    @TableField("Ext3")
    private String Ext3;

    @TableField("Editor")
    private String Editor;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("BizID")
    private String BizID;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("BizType")
    private String BizType;

    @TableField("OrgID")
    private String OrgID;

    @TableField("Creator")
    private String Creator;

    @TableField("Ext4")
    private String Ext4;

    @TableField("AuthCompanyID")
    private String AuthCompanyID;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Ext1")
    private String Ext1;

    @TableField("Status")
    private Integer Status;

    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField("ProductID")
    private String ProductID;

    @TableField("BizDesc")
    private String BizDesc;

    @TableField("Ext2")
    private String Ext2;

    @TableField("Data")
    private String Data;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getExt3() {
        return Ext3;
    }

    public void setExt3(String Ext3) {
        this.Ext3 = Ext3;
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
    public String getBizID() {
        return BizID;
    }

    public void setBizID(String BizID) {
        this.BizID = BizID;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getBizType() {
        return BizType;
    }

    public void setBizType(String BizType) {
        this.BizType = BizType;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getExt4() {
        return Ext4;
    }

    public void setExt4(String Ext4) {
        this.Ext4 = Ext4;
    }
    public String getAuthCompanyID() {
        return AuthCompanyID;
    }

    public void setAuthCompanyID(String AuthCompanyID) {
        this.AuthCompanyID = AuthCompanyID;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getExt1() {
        return Ext1;
    }

    public void setExt1(String Ext1) {
        this.Ext1 = Ext1;
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
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    public String getBizDesc() {
        return BizDesc;
    }

    public void setBizDesc(String BizDesc) {
        this.BizDesc = BizDesc;
    }
    public String getExt2() {
        return Ext2;
    }

    public void setExt2(String Ext2) {
        this.Ext2 = Ext2;
    }
    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    @Override
    public String toString() {
        return "SLogs{" +
        "ip=" + ip +
        ", Ext3=" + Ext3 +
        ", Editor=" + Editor +
        ", IsDel=" + IsDel +
        ", BizID=" + BizID +
        ", EditTime=" + EditTime +
        ", BizType=" + BizType +
        ", OrgID=" + OrgID +
        ", Creator=" + Creator +
        ", Ext4=" + Ext4 +
        ", AuthCompanyID=" + AuthCompanyID +
        ", CreateTime=" + CreateTime +
        ", Ext1=" + Ext1 +
        ", Status=" + Status +
        ", id=" + id +
        ", ProductID=" + ProductID +
        ", BizDesc=" + BizDesc +
        ", Ext2=" + Ext2 +
        ", Data=" + Data +
        "}";
    }
}
