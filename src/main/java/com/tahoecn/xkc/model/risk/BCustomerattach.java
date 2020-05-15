package com.tahoecn.xkc.model.risk;

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
 * @since 2020-05-15
 */
@TableName("B_CustomerAttach")
@ApiModel(value="BCustomerattach对象", description="")
public class BCustomerattach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("IntentProjectID")
    private String IntentProjectID;

    @TableField("UserID")
    private String UserID;

    @ApiModelProperty(value = "销售状态")
    @TableField("SalesStatus")
    private Integer SalesStatus;

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
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getIntentProjectID() {
        return IntentProjectID;
    }

    public void setIntentProjectID(String IntentProjectID) {
        this.IntentProjectID = IntentProjectID;
    }
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public Integer getSalesStatus() {
        return SalesStatus;
    }

    public void setSalesStatus(Integer SalesStatus) {
        this.SalesStatus = SalesStatus;
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
        return "BCustomerattach{" +
        "id=" + id +
        ", OpportunityID=" + OpportunityID +
        ", CustomerID=" + CustomerID +
        ", ProjectID=" + ProjectID +
        ", IntentProjectID=" + IntentProjectID +
        ", UserID=" + UserID +
        ", SalesStatus=" + SalesStatus +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
