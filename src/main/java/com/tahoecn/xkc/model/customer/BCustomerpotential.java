package com.tahoecn.xkc.model.customer;

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
 * @since 2019-07-06
 */
@TableName("B_CustomerPotential")
@ApiModel(value="BCustomerpotential对象", description="")
public class BCustomerpotential implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("Name")
    private String Name;

    @TableField("LastName")
    private String LastName;

    @TableField("FirstName")
    private String FirstName;

    @TableField("Gender")
    private String Gender;

    @TableField("Mobile")
    private String Mobile;

    @ApiModelProperty(value = "包括：0：非业主；1：业主")
    @TableField("IsOwner")
    private Integer IsOwner;

    @ApiModelProperty(value = "1，身份证")
    @TableField("CardType")
    private String CardType;

    @TableField("CardID")
    private String CardID;

    @ApiModelProperty(value = "1已填写 0 未填写")
    @TableField("AcceptFactor")
    private String AcceptFactor;

    @TableField("Remark")
    private String Remark;

    @TableField("OrgID")
    private String OrgID;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @ApiModelProperty(value = "0 否 1是")
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
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public Integer getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(Integer IsOwner) {
        this.IsOwner = IsOwner;
    }
    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }
    public String getCardID() {
        return CardID;
    }

    public void setCardID(String CardID) {
        this.CardID = CardID;
    }
    public String getAcceptFactor() {
        return AcceptFactor;
    }

    public void setAcceptFactor(String AcceptFactor) {
        this.AcceptFactor = AcceptFactor;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
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
        return "BCustomerpotential{" +
        "id=" + id +
        ", Name=" + Name +
        ", LastName=" + LastName +
        ", FirstName=" + FirstName +
        ", Gender=" + Gender +
        ", Mobile=" + Mobile +
        ", IsOwner=" + IsOwner +
        ", CardType=" + CardType +
        ", CardID=" + CardID +
        ", AcceptFactor=" + AcceptFactor +
        ", Remark=" + Remark +
        ", OrgID=" + OrgID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
