package com.tahoecn.xkc.model.clue;

import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;

@TableName("B_CustomerPotentialFollowUp")
@ApiModel(value="BCustomerpotentialfollowup对象", description="")
public class BCustomerpotentialfollowup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("OrgID")
    private String OrgID;

    @TableField("FollowUpContent")
    private String FollowUpContent;

    @TableField("FollwUpUserMobile")
    private String FollwUpUserMobile;

    @TableField("Status")
    private Integer Status;

    @TableField("ClueID")
    private String ClueID;

    @TableField("Editor")
    private String Editor;

    @TableField("CustomerPotentialMobile")
    private String CustomerPotentialMobile;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("Creator")
    private String Creator;

    @TableField("NextFollowUpDate")
    private Date NextFollowUpDate;

    @TableField("FollwUpType")
    private String FollwUpType;

    @TableField("FollwUpUserID")
    private String FollwUpUserID;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("FollwUpUserName")
    private String FollwUpUserName;

    @TableField("CustomerPotentialName")
    private String CustomerPotentialName;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("FollwUpUserRole")
    private String FollwUpUserRole;

    @TableField("FollwUpWay")
    private String FollwUpWay;

    @TableField("CustomerRank")
    private String CustomerRank;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IntentionLevel")
    private String IntentionLevel;

    @TableField("CustomerPotentialID")
    private String CustomerPotentialID;

    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String OrgID) {
        this.OrgID = OrgID;
    }
    public String getFollowUpContent() {
        return FollowUpContent;
    }

    public void setFollowUpContent(String FollowUpContent) {
        this.FollowUpContent = FollowUpContent;
    }
    public String getFollwUpUserMobile() {
        return FollwUpUserMobile;
    }

    public void setFollwUpUserMobile(String FollwUpUserMobile) {
        this.FollwUpUserMobile = FollwUpUserMobile;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getCustomerPotentialMobile() {
        return CustomerPotentialMobile;
    }

    public void setCustomerPotentialMobile(String CustomerPotentialMobile) {
        this.CustomerPotentialMobile = CustomerPotentialMobile;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(Date NextFollowUpDate) {
        this.NextFollowUpDate = NextFollowUpDate;
    }
    public String getFollwUpType() {
        return FollwUpType;
    }

    public void setFollwUpType(String FollwUpType) {
        this.FollwUpType = FollwUpType;
    }
    public String getFollwUpUserID() {
        return FollwUpUserID;
    }

    public void setFollwUpUserID(String FollwUpUserID) {
        this.FollwUpUserID = FollwUpUserID;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public String getFollwUpUserName() {
        return FollwUpUserName;
    }

    public void setFollwUpUserName(String FollwUpUserName) {
        this.FollwUpUserName = FollwUpUserName;
    }
    public String getCustomerPotentialName() {
        return CustomerPotentialName;
    }

    public void setCustomerPotentialName(String CustomerPotentialName) {
        this.CustomerPotentialName = CustomerPotentialName;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFollwUpUserRole() {
        return FollwUpUserRole;
    }

    public void setFollwUpUserRole(String FollwUpUserRole) {
        this.FollwUpUserRole = FollwUpUserRole;
    }
    public String getFollwUpWay() {
        return FollwUpWay;
    }

    public void setFollwUpWay(String FollwUpWay) {
        this.FollwUpWay = FollwUpWay;
    }
    public String getCustomerRank() {
        return CustomerRank;
    }

    public void setCustomerRank(String CustomerRank) {
        this.CustomerRank = CustomerRank;
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
    public String getIntentionLevel() {
        return IntentionLevel;
    }

    public void setIntentionLevel(String IntentionLevel) {
        this.IntentionLevel = IntentionLevel;
    }
    public String getCustomerPotentialID() {
        return CustomerPotentialID;
    }

    public void setCustomerPotentialID(String CustomerPotentialID) {
        this.CustomerPotentialID = CustomerPotentialID;
    }

    @Override
    public String toString() {
        return "BCustomerpotentialfollowup{" +
        "OrgID=" + OrgID +
        ", FollowUpContent=" + FollowUpContent +
        ", FollwUpUserMobile=" + FollwUpUserMobile +
        ", Status=" + Status +
        ", ClueID=" + ClueID +
        ", Editor=" + Editor +
        ", CustomerPotentialMobile=" + CustomerPotentialMobile +
        ", ProjectID=" + ProjectID +
        ", Creator=" + Creator +
        ", NextFollowUpDate=" + NextFollowUpDate +
        ", FollwUpType=" + FollwUpType +
        ", FollwUpUserID=" + FollwUpUserID +
        ", EditeTime=" + EditeTime +
        ", FollwUpUserName=" + FollwUpUserName +
        ", CustomerPotentialName=" + CustomerPotentialName +
        ", id=" + id +
        ", FollwUpUserRole=" + FollwUpUserRole +
        ", FollwUpWay=" + FollwUpWay +
        ", CustomerRank=" + CustomerRank +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        ", IntentionLevel=" + IntentionLevel +
        ", CustomerPotentialID=" + CustomerPotentialID +
        "}";
    }
}
