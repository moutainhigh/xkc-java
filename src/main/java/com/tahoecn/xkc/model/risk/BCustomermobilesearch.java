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
 * @since 2020-05-08
 */
@TableName("B_CustomerMobileSearch")
@ApiModel(value="BCustomermobilesearch对象", description="")
public class BCustomermobilesearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("Mobile")
    private String Mobile;

    @TableField("JobCode")
    private String JobCode;

    @TableField("OpportunityID")
    private String OpportunityID;

    @TableField("Creator")
    private String Creator;

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
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getJobCode() {
        return JobCode;
    }

    public void setJobCode(String JobCode) {
        this.JobCode = JobCode;
    }
    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
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

    @Override
    public String toString() {
        return "BCustomermobilesearch{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", Mobile=" + Mobile +
        ", JobCode=" + JobCode +
        ", OpportunityID=" + OpportunityID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
