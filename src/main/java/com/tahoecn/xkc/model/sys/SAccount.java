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
 * @since 2019-06-21
 */
@TableName("S_Account")
@ApiModel(value="SAccount对象", description="")
public class SAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "证件类型")
    @TableField("CertificatesType")
    private String CertificatesType;

    @ApiModelProperty(value = "证件号")
    @TableField("CertificatesNo")
    private String CertificatesNo;

    @ApiModelProperty(value = "登录名")
    @TableField("UserName")
    private String userName;

    @ApiModelProperty(value = "编辑时间")
    @TableField("EditTime")
    private Date EditTime;

    @ApiModelProperty(value = "办公电话")
    @TableField("OfficeTel")
    private String OfficeTel;

    @ApiModelProperty(value = "创建人")
    @TableField("Creator")
    private String Creator;

    @ApiModelProperty(value = "工号")
    @TableField("EmployeeCode")
    private String EmployeeCode;

    @ApiModelProperty(value = "")
    @TableField("AuthCompanyID")
    private String AuthCompanyID;

    @ApiModelProperty(value = "密码")
    @TableField("Password")
    private String password;

    @ApiModelProperty(value = "邮编")
    @TableField("PostCode")
    private String PostCode;

    @ApiModelProperty(value = "状态")
    @TableField("Status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("CreateTime")
    private Date CreateTime;

    @ApiModelProperty(value = "姓名")
    @TableField("EmployeeName")
    private String EmployeeName;

    @ApiModelProperty(value = "编辑人")
    @TableField("Editor")
    private String Editor;

    @ApiModelProperty(value = "项目ID")
    @TableField("ProductID")
    private String ProductID;

    @ApiModelProperty(value = "地址")
    @TableField("Address")
    private String Address;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("OfficeMail")
    private String OfficeMail;

    @ApiModelProperty(value = "移动电话")
    @TableField("Mobile")
    private String mobile;

    @ApiModelProperty(value = "性别")
    @TableField("Gender")
    private Integer Gender;

    @ApiModelProperty(value = "账号类型")
    @TableField("AccountType")
    private Integer AccountType;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "所属组织ID")
    @TableField("UserOrgID")
    private String UserOrgID;

    @ApiModelProperty(value = "是否删除")
    @TableField("IsDel")
    private Integer isDel;

    public String getCertificatesType() {
        return CertificatesType;
    }

    public void setCertificatesType(String CertificatesType) {
        this.CertificatesType = CertificatesType;
    }
    public String getCertificatesNo() {
        return CertificatesNo;
    }

    public void setCertificatesNo(String CertificatesNo) {
        this.CertificatesNo = CertificatesNo;
    }

    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getOfficeTel() {
        return OfficeTel;
    }

    public void setOfficeTel(String OfficeTel) {
        this.OfficeTel = OfficeTel;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String EmployeeCode) {
        this.EmployeeCode = EmployeeCode;
    }
    public String getAuthCompanyID() {
        return AuthCompanyID;
    }

    public void setAuthCompanyID(String AuthCompanyID) {
        this.AuthCompanyID = AuthCompanyID;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String PostCode) {
        this.PostCode = PostCode;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getOfficeMail() {
        return OfficeMail;
    }

    public void setOfficeMail(String OfficeMail) {
        this.OfficeMail = OfficeMail;
    }
    public Integer getGender() {
        return Gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(Integer Gender) {
        this.Gender = Gender;
    }
    public Integer getAccountType() {
        return AccountType;
    }

    public void setAccountType(Integer AccountType) {
        this.AccountType = AccountType;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserOrgID() {
        return UserOrgID;
    }

    public void setUserOrgID(String UserOrgID) {
        this.UserOrgID = UserOrgID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "SAccount{" +
        "CertificatesType=" + CertificatesType +
        ", CertificatesNo=" + CertificatesNo +
        ", UserName=" + userName +
        ", EditTime=" + EditTime +
        ", OfficeTel=" + OfficeTel +
        ", Creator=" + Creator +
        ", EmployeeCode=" + EmployeeCode +
        ", AuthCompanyID=" + AuthCompanyID +
        ", Password=" + password +
        ", PostCode=" + PostCode +
        ", Status=" + status +
        ", CreateTime=" + CreateTime +
        ", EmployeeName=" + EmployeeName +
        ", Editor=" + Editor +
        ", ProductID=" + ProductID +
        ", Address=" + Address +
        ", OfficeMail=" + OfficeMail +
        ", Mobile=" + mobile +
        ", Gender=" + Gender +
        ", AccountType=" + AccountType +
        ", id=" + id +
        ", UserOrgID=" + UserOrgID +
        ", IsDel=" + isDel +
        "}";
    }
}
