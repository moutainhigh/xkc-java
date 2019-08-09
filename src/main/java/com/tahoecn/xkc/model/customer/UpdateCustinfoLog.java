package com.tahoecn.xkc.model.customer;

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
 * @since 2019-08-08
 */
@TableName("Update_Custinfo_log")
@ApiModel(value="UpdateCustinfoLog对象", description="")
public class UpdateCustinfoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("AuxiliaryMobile")
    private String AuxiliaryMobile;

    @TableField("Gender")
    private String Gender;

    @TableField("CardType")
    private String CardType;

    @TableField("CardID")
    private String CardID;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getAuxiliaryMobile() {
        return AuxiliaryMobile;
    }

    public void setAuxiliaryMobile(String AuxiliaryMobile) {
        this.AuxiliaryMobile = AuxiliaryMobile;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
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

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    @Override
    public String toString() {
        return "UpdateCustinfoLog{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", OpportunityID=" + OpportunityID +
        ", CustomerID=" + CustomerID +
        ", CustomerName=" + CustomerName +
        ", AuxiliaryMobile=" + AuxiliaryMobile +
        ", Gender=" + Gender +
        ", CardType=" + CardType +
        ", CardID=" + CardID +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
