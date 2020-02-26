package com.tahoecn.xkc.model.assignment;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 由于小程序二维码参数长度限制在32个字节 所有先把要携带的参数保存到本表
 * </p>
 *
 * @author YYY
 * @since 2020-01-06
 */
@TableName("A_ShareQRCodeParameter")
@ApiModel(value="AShareqrcodeparameter对象", description="由于小程序二维码参数长度限制在32个字节 所有先把要携带的参数保存到本表")
public class AShareqrcodeparameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "非GUID")
    @TableId("ID")
    private String id;

    @TableField("SID")
    private String sid;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("IdentityID")
    private String IdentityID;

    @TableField("CodeParameter")
    private String CodeParameter;

    @TableField("Category")
    private Integer Category;

    @ApiModelProperty(value = "0.全民经纪人")
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
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getIdentityID() {
        return IdentityID;
    }

    public void setIdentityID(String IdentityID) {
        this.IdentityID = IdentityID;
    }
    public String getCodeParameter() {
        return CodeParameter;
    }

    public void setCodeParameter(String CodeParameter) {
        this.CodeParameter = CodeParameter;
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
        return "AShareqrcodeparameter{" +
        "id=" + id +
        ", sid=" + sid +
        ", ProjectID=" + ProjectID +
        ", IdentityID=" + IdentityID +
        ", CodeParameter=" + CodeParameter +
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
