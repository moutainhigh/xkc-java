package com.tahoecn.xkc.model.salegroup;

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
 * @since 2019-07-04
 */
@TableName("B_SalesGroupMember")
@ApiModel(value="BSalesgroupmember对象", description="")
public class BSalesgroupmember implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "0=离职，1=在职")
    @TableField("Status")
    private Integer Status;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("QRCodeUrl")
    private String QRCodeUrl;

    @TableField("MemberID")
    private String MemberID;

    @TableField("Creator")
    private String Creator;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("RoleID")
    private String RoleID;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("RoleName")
    private String RoleName;

    @TableField("ReceptionGroupID")
    private String ReceptionGroupID;

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
    public String getQRCodeUrl() {
        return QRCodeUrl;
    }

    public void setQRCodeUrl(String QRCodeUrl) {
        this.QRCodeUrl = QRCodeUrl;
    }
    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String MemberID) {
        this.MemberID = MemberID;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
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
    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    public String getReceptionGroupID() {
        return ReceptionGroupID;
    }

    public void setReceptionGroupID(String ReceptionGroupID) {
        this.ReceptionGroupID = ReceptionGroupID;
    }

    @Override
    public String toString() {
        return "BSalesgroupmember{" +
        "Status=" + Status +
        ", id=" + id +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", QRCodeUrl=" + QRCodeUrl +
        ", MemberID=" + MemberID +
        ", Creator=" + Creator +
        ", ProjectID=" + ProjectID +
        ", RoleID=" + RoleID +
        ", CreateTime=" + CreateTime +
        ", IsDel=" + IsDel +
        ", RoleName=" + RoleName +
        ", ReceptionGroupID=" + ReceptionGroupID +
        "}";
    }
}
