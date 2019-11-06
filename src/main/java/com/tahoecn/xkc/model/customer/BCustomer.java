package com.tahoecn.xkc.model.customer;

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
 * @since 2019-07-05
 */
@TableName("B_Customer")
@ApiModel(value="BCustomer对象", description="")
public class BCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Name")
    private String Name;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("CustomerType")
    private String CustomerType;

    @TableField("Creator")
    private String Creator;

    @TableField("Gender")
    private String Gender;

    @TableField("Remark")
    private String Remark;

    @TableField("LastName")
    private String LastName;

    @TableField("CardType")
    private String CardType;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Mobile")
    private String Mobile;

    @TableField("Editor")
    private String Editor;

    @TableField("OrgID")
    private String OrgID;

    @TableField("CardID")
    private String CardID;

    @TableField("IsOwner")
    private Integer IsOwner;

    @TableField("IsEmployee")
    private String IsEmployee;

    @TableField("TheFirstVisitDate")
    private Date TheFirstVisitDate;

    @TableField("FirstName")
    private String FirstName;

    @TableField("Status")
    private Integer Status;

    @TableId("ID")
    private String id;

    @TableField("AcceptFactor")
    private String AcceptFactor;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String CustomerType) {
        this.CustomerType = CustomerType;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public String getCardID() {
        return CardID;
    }

    public void setCardID(String CardID) {
        this.CardID = CardID;
    }
    public Integer getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(Integer IsOwner) {
        this.IsOwner = IsOwner;
    }
    public String getIsEmployee() {
        return IsEmployee;
    }

    public void setIsEmployee(String IsEmployee) {
        this.IsEmployee = IsEmployee;
    }
    public Date getTheFirstVisitDate() {
        return TheFirstVisitDate;
    }

    public void setTheFirstVisitDate(Date TheFirstVisitDate) {
        this.TheFirstVisitDate = TheFirstVisitDate;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
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
    public String getAcceptFactor() {
        return AcceptFactor;
    }

    public void setAcceptFactor(String AcceptFactor) {
        this.AcceptFactor = AcceptFactor;
    }

    @Override
    public String toString() {
        return "BCustomer{" +
        "Name=" + Name +
        ", EditeTime=" + EditeTime +
        ", CustomerType=" + CustomerType +
        ", Creator=" + Creator +
        ", Gender=" + Gender +
        ", Remark=" + Remark +
        ", LastName=" + LastName +
        ", CardType=" + CardType +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        ", Mobile=" + Mobile +
        ", Editor=" + Editor +
        ", OrgID=" + OrgID +
        ", CardID=" + CardID +
        ", IsOwner=" + IsOwner +
        ", IsEmployee=" + IsEmployee +
        ", TheFirstVisitDate=" + TheFirstVisitDate +
        ", FirstName=" + FirstName +
        ", Status=" + Status +
        ", id=" + id +
        ", AcceptFactor=" + AcceptFactor +
        "}";
    }
}
