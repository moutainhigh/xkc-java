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
 * @since 2019-07-11
 */
@TableName("B_CustomerFollowUp")
@ApiModel(value="BCustomerfollowup对象", description="")
public class BCustomerfollowup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("FollwUpUserID")
    private String FollwUpUserID;

    @TableField("FollwUpUserName")
    private String FollwUpUserName;

    @TableField("FollwUpUserMobile")
    private String FollwUpUserMobile;

    @ApiModelProperty(value = "0：机会创建 1：业务跟进 2：领导批示 3：机会放弃 4：认购 5：签约")
    @TableField("FollwUpType")
    private String FollwUpType;

    @ApiModelProperty(value = "字典数据")
    @TableField("FollwUpWay")
    private String FollwUpWay;

    @TableField("FollowUpContent")
    private String FollowUpContent;

    @TableField("NextFollowUpDate")
    private Date NextFollowUpDate;

    @ApiModelProperty(value = "数据字典")
    @TableField("IntentionLevel")
    private String IntentionLevel;

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

    @TableField("FollwUpUserRole")
    private String FollwUpUserRole;

    @TableField("CustomerRank")
    private String CustomerRank;

    @TableField("ProjectID")
    private String ProjectID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
    }
    public String getFollwUpUserID() {
        return FollwUpUserID;
    }

    public void setFollwUpUserID(String FollwUpUserID) {
        this.FollwUpUserID = FollwUpUserID;
    }
    public String getFollwUpUserName() {
        return FollwUpUserName;
    }

    public void setFollwUpUserName(String FollwUpUserName) {
        this.FollwUpUserName = FollwUpUserName;
    }
    public String getFollwUpUserMobile() {
        return FollwUpUserMobile;
    }

    public void setFollwUpUserMobile(String FollwUpUserMobile) {
        this.FollwUpUserMobile = FollwUpUserMobile;
    }
    public String getFollwUpType() {
        return FollwUpType;
    }

    public void setFollwUpType(String FollwUpType) {
        this.FollwUpType = FollwUpType;
    }
    public String getFollwUpWay() {
        return FollwUpWay;
    }

    public void setFollwUpWay(String FollwUpWay) {
        this.FollwUpWay = FollwUpWay;
    }
    public String getFollowUpContent() {
        return FollowUpContent;
    }

    public void setFollowUpContent(String FollowUpContent) {
        this.FollowUpContent = FollowUpContent;
    }
    public Date getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(Date NextFollowUpDate) {
        this.NextFollowUpDate = NextFollowUpDate;
    }
    public String getIntentionLevel() {
        return IntentionLevel;
    }

    public void setIntentionLevel(String IntentionLevel) {
        this.IntentionLevel = IntentionLevel;
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
    public String getFollwUpUserRole() {
        return FollwUpUserRole;
    }

    public void setFollwUpUserRole(String FollwUpUserRole) {
        this.FollwUpUserRole = FollwUpUserRole;
    }
    public String getCustomerRank() {
        return CustomerRank;
    }

    public void setCustomerRank(String CustomerRank) {
        this.CustomerRank = CustomerRank;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }

    @Override
    public String toString() {
        return "BCustomerfollowup{" +
        "id=" + id +
        ", CustomerID=" + CustomerID +
        ", CustomerMobile=" + CustomerMobile +
        ", CustomerName=" + CustomerName +
        ", OpportunityID=" + OpportunityID +
        ", FollwUpUserID=" + FollwUpUserID +
        ", FollwUpUserName=" + FollwUpUserName +
        ", FollwUpUserMobile=" + FollwUpUserMobile +
        ", FollwUpType=" + FollwUpType +
        ", FollwUpWay=" + FollwUpWay +
        ", FollowUpContent=" + FollowUpContent +
        ", NextFollowUpDate=" + NextFollowUpDate +
        ", IntentionLevel=" + IntentionLevel +
        ", OrgID=" + OrgID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", FollwUpUserRole=" + FollwUpUserRole +
        ", CustomerRank=" + CustomerRank +
        ", ProjectID=" + ProjectID +
        "}";
    }
}
