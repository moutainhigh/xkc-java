package com.tahoecn.xkc.model.channel;

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
 * @since 2019-06-25
 */
@TableName("B_ChannelUser")
@ApiModel(value="BChanneluser对象", description="")
public class BChanneluser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1，渠道负责人 2，门店负责人 3，拓客人员")
    @TableField("Job")
    private Integer Job;

    @ApiModelProperty(value = "0 未审批 1 通过 2 未通过")
    @TableField("ApprovalStatus")
    private Integer ApprovalStatus;

    @ApiModelProperty(value = "银行卡照片")
    @TableField("BankCardPic")
    private String BankCardPic;

    @ApiModelProperty(value = "邀请人")
    @TableField("Inviter")
    private String Inviter;

    @ApiModelProperty(value = "应用ID")
    @TableField("AppID")
    private String AppID;

    @ApiModelProperty(value = "证件号码")
    @TableField("CertificatesNo")
    private String CertificatesNo;

    @ApiModelProperty(value = "用户名")
    @TableField("UserName")
    private String UserName;

    @ApiModelProperty(value = "创建时间")
    @TableField("CreateTime")
    private Date CreateTime;

    @ApiModelProperty(value = "银行卡号")
    @TableField("BankCard")
    private String BankCard;

    @ApiModelProperty(value = "姓名")
    @TableField("Name")
    private String Name;

    @ApiModelProperty(value = "开户市")
    @TableField("BankCardCity")
    private String BankCardCity;

    @ApiModelProperty(value = "门店识别码")
    @TableField("ChannelOrgCode")
    private String ChannelOrgCode;

    @ApiModelProperty(value = "开户省")
    @TableField("BankCardProvince")
    private String BankCardProvince;

    @ApiModelProperty(value = "编辑时间")
    @TableField("EditeTime")
    private Date EditeTime;

    @ApiModelProperty(value = "邀请人")
    @TableField("ShareQRCodeID")
    private String ShareQRCodeID;

    @ApiModelProperty(value = "头像")
    @TableField("HeadImg")
    private String HeadImg;

    @ApiModelProperty(value = "开户支行")
    @TableField("BankCardBranch")
    private String BankCardBranch;

    @ApiModelProperty(value = "渠道类型ID")
    @TableField("ChannelTypeID")
    private String ChannelTypeID;

    @ApiModelProperty(value = "渠道类型")
    @TableField("ChannelType")
    private String ChannelType;

    @ApiModelProperty(value = "审批人")
    @TableField("Approver")
    private String Approver;

    @ApiModelProperty(value = "二维码url")
    @TableField("QRCodeUrl")
    private String QRCodeUrl;

    @ApiModelProperty(value = "创建人")
    @TableField("Creator")
    private String Creator;

    @ApiModelProperty(value = "审批时间")
    @TableField("ApprovalDate")
    private Date ApprovalDate;

    @ApiModelProperty(value = "报备人员")
    @TableField("ReportUserID")
    private String ReportUserID;

    @ApiModelProperty(value = "编辑人")
    @TableField("Editor")
    private String Editor;

    @ApiModelProperty(value = "")
    @TableField("PlacardUrl")
    private String PlacardUrl;

    @ApiModelProperty(value = "状态")
    @TableField("Status")
    private Integer Status;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "")
    @TableField("BankCardCreate")
    private String BankCardCreate;

    @ApiModelProperty(value = "邀请人姓名")
    @TableField("InviterName")
    private String InviterName;

    @ApiModelProperty(value = "证件照片下")
    @TableField("CertificatesPicBack")
    private String CertificatesPicBack;

    @ApiModelProperty(value = "证件类型")
    @TableField("CertificatesType")
    private String CertificatesType;

    @ApiModelProperty(value = "手机号")
    @TableField("Mobile")
    private String Mobile;

    @ApiModelProperty(value = "证件名")
    @TableField("CertificatesName")
    private String CertificatesName;

    @ApiModelProperty(value = "证件照片上")
    @TableField("CertificatesPicFace")
    private String CertificatesPicFace;

    @ApiModelProperty(value = "密码")
    @TableField("Password")
    private String Password;

    @ApiModelProperty(value = "持卡人")
    @TableField("BankCardPerson")
    private String BankCardPerson;

    @ApiModelProperty(value = "性别")
    @TableField("Gender")
    private String Gender;

    @ApiModelProperty(value = "渠道组织归属")
    @TableField("ChannelOrgID")
    private String ChannelOrgID;

    @ApiModelProperty(value = "证件照片url")
    @TableField("CertificatesPicUrl")
    private String CertificatesPicUrl;

    @ApiModelProperty(value = "开户区")
    @TableField("BankCardArea")
    private String BankCardArea;

    @ApiModelProperty(value = "是否删除")
    @TableField("IsDel")
    private Integer IsDel;

    public Integer getJob() {
        return Job;
    }

    public void setJob(Integer Job) {
        this.Job = Job;
    }
    public Integer getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(Integer ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }
    public String getBankCardPic() {
        return BankCardPic;
    }

    public void setBankCardPic(String BankCardPic) {
        this.BankCardPic = BankCardPic;
    }
    public String getInviter() {
        return Inviter;
    }

    public void setInviter(String Inviter) {
        this.Inviter = Inviter;
    }
    public String getAppID() {
        return AppID;
    }

    public void setAppID(String AppID) {
        this.AppID = AppID;
    }
    public String getCertificatesNo() {
        return CertificatesNo;
    }

    public void setCertificatesNo(String CertificatesNo) {
        this.CertificatesNo = CertificatesNo;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getBankCard() {
        return BankCard;
    }

    public void setBankCard(String BankCard) {
        this.BankCard = BankCard;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getBankCardCity() {
        return BankCardCity;
    }

    public void setBankCardCity(String BankCardCity) {
        this.BankCardCity = BankCardCity;
    }
    public String getChannelOrgCode() {
        return ChannelOrgCode;
    }

    public void setChannelOrgCode(String ChannelOrgCode) {
        this.ChannelOrgCode = ChannelOrgCode;
    }
    public String getBankCardProvince() {
        return BankCardProvince;
    }

    public void setBankCardProvince(String BankCardProvince) {
        this.BankCardProvince = BankCardProvince;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public String getShareQRCodeID() {
        return ShareQRCodeID;
    }

    public void setShareQRCodeID(String ShareQRCodeID) {
        this.ShareQRCodeID = ShareQRCodeID;
    }
    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String HeadImg) {
        this.HeadImg = HeadImg;
    }
    public String getBankCardBranch() {
        return BankCardBranch;
    }

    public void setBankCardBranch(String BankCardBranch) {
        this.BankCardBranch = BankCardBranch;
    }
    public String getChannelTypeID() {
        return ChannelTypeID;
    }

    public void setChannelTypeID(String ChannelTypeID) {
        this.ChannelTypeID = ChannelTypeID;
    }
    public String getChannelType() {
        return ChannelType;
    }

    public void setChannelType(String ChannelType) {
        this.ChannelType = ChannelType;
    }
    public String getApprover() {
        return Approver;
    }

    public void setApprover(String Approver) {
        this.Approver = Approver;
    }
    public String getQRCodeUrl() {
        return QRCodeUrl;
    }

    public void setQRCodeUrl(String QRCodeUrl) {
        this.QRCodeUrl = QRCodeUrl;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getApprovalDate() {
        return ApprovalDate;
    }

    public void setApprovalDate(Date ApprovalDate) {
        this.ApprovalDate = ApprovalDate;
    }
    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String ReportUserID) {
        this.ReportUserID = ReportUserID;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getPlacardUrl() {
        return PlacardUrl;
    }

    public void setPlacardUrl(String PlacardUrl) {
        this.PlacardUrl = PlacardUrl;
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
    public String getBankCardCreate() {
        return BankCardCreate;
    }

    public void setBankCardCreate(String BankCardCreate) {
        this.BankCardCreate = BankCardCreate;
    }
    public String getInviterName() {
        return InviterName;
    }

    public void setInviterName(String InviterName) {
        this.InviterName = InviterName;
    }
    public String getCertificatesPicBack() {
        return CertificatesPicBack;
    }

    public void setCertificatesPicBack(String CertificatesPicBack) {
        this.CertificatesPicBack = CertificatesPicBack;
    }
    public String getCertificatesType() {
        return CertificatesType;
    }

    public void setCertificatesType(String CertificatesType) {
        this.CertificatesType = CertificatesType;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getCertificatesName() {
        return CertificatesName;
    }

    public void setCertificatesName(String CertificatesName) {
        this.CertificatesName = CertificatesName;
    }
    public String getCertificatesPicFace() {
        return CertificatesPicFace;
    }

    public void setCertificatesPicFace(String CertificatesPicFace) {
        this.CertificatesPicFace = CertificatesPicFace;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String getBankCardPerson() {
        return BankCardPerson;
    }

    public void setBankCardPerson(String BankCardPerson) {
        this.BankCardPerson = BankCardPerson;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public String getChannelOrgID() {
        return ChannelOrgID;
    }

    public void setChannelOrgID(String ChannelOrgID) {
        this.ChannelOrgID = ChannelOrgID;
    }
    public String getCertificatesPicUrl() {
        return CertificatesPicUrl;
    }

    public void setCertificatesPicUrl(String CertificatesPicUrl) {
        this.CertificatesPicUrl = CertificatesPicUrl;
    }
    public String getBankCardArea() {
        return BankCardArea;
    }

    public void setBankCardArea(String BankCardArea) {
        this.BankCardArea = BankCardArea;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }

    @Override
    public String toString() {
        return "BChanneluser{" +
        "Job=" + Job +
        ", ApprovalStatus=" + ApprovalStatus +
        ", BankCardPic=" + BankCardPic +
        ", Inviter=" + Inviter +
        ", AppID=" + AppID +
        ", CertificatesNo=" + CertificatesNo +
        ", UserName=" + UserName +
        ", CreateTime=" + CreateTime +
        ", BankCard=" + BankCard +
        ", Name=" + Name +
        ", BankCardCity=" + BankCardCity +
        ", ChannelOrgCode=" + ChannelOrgCode +
        ", BankCardProvince=" + BankCardProvince +
        ", EditeTime=" + EditeTime +
        ", ShareQRCodeID=" + ShareQRCodeID +
        ", HeadImg=" + HeadImg +
        ", BankCardBranch=" + BankCardBranch +
        ", ChannelTypeID=" + ChannelTypeID +
        ", ChannelType=" + ChannelType +
        ", Approver=" + Approver +
        ", QRCodeUrl=" + QRCodeUrl +
        ", Creator=" + Creator +
        ", ApprovalDate=" + ApprovalDate +
        ", ReportUserID=" + ReportUserID +
        ", Editor=" + Editor +
        ", PlacardUrl=" + PlacardUrl +
        ", Status=" + Status +
        ", id=" + id +
        ", BankCardCreate=" + BankCardCreate +
        ", InviterName=" + InviterName +
        ", CertificatesPicBack=" + CertificatesPicBack +
        ", CertificatesType=" + CertificatesType +
        ", Mobile=" + Mobile +
        ", CertificatesName=" + CertificatesName +
        ", CertificatesPicFace=" + CertificatesPicFace +
        ", Password=" + Password +
        ", BankCardPerson=" + BankCardPerson +
        ", Gender=" + Gender +
        ", ChannelOrgID=" + ChannelOrgID +
        ", CertificatesPicUrl=" + CertificatesPicUrl +
        ", BankCardArea=" + BankCardArea +
        ", IsDel=" + IsDel +
        "}";
    }
}
