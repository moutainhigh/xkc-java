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
 * @since 2020-05-11
 */
@TableName("B_WangXiaobao")
@ApiModel(value="BWangxiaobao对象", description="")
public class BWangxiaobao implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectId")
    private String ProjectId;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("Name")
    private String Name;

    @TableField("IdNumber")
    private String IdNumber;

    @TableField("VerifyTime")
    private Date VerifyTime;

    @TableField("RiskStatus")
    private String RiskStatus;

    @TableField("RiskApproveStatus")
    private String RiskApproveStatus;

    @TableField("RiskApproveRemark")
    private String RiskApproveRemark;

    @TableField("VerifyResult")
    private Integer VerifyResult;

    @TableField("FirstPhotoTime")
    private Date FirstPhotoTime;

    @TableField("Image")
    private String Image;

    @TableField("PanoramicView")
    private String PanoramicView;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }
    public Date getVerifyTime() {
        return VerifyTime;
    }

    public void setVerifyTime(Date VerifyTime) {
        this.VerifyTime = VerifyTime;
    }
    public String getRiskStatus() {
        return RiskStatus;
    }

    public void setRiskStatus(String RiskStatus) {
        this.RiskStatus = RiskStatus;
    }
    public String getRiskApproveStatus() {
        return RiskApproveStatus;
    }

    public void setRiskApproveStatus(String RiskApproveStatus) {
        this.RiskApproveStatus = RiskApproveStatus;
    }
    public String getRiskApproveRemark() {
        return RiskApproveRemark;
    }

    public void setRiskApproveRemark(String RiskApproveRemark) {
        this.RiskApproveRemark = RiskApproveRemark;
    }
    public Integer getVerifyResult() {
        return VerifyResult;
    }

    public void setVerifyResult(Integer VerifyResult) {
        this.VerifyResult = VerifyResult;
    }
    public Date getFirstPhotoTime() {
        return FirstPhotoTime;
    }

    public void setFirstPhotoTime(Date FirstPhotoTime) {
        this.FirstPhotoTime = FirstPhotoTime;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    public String getPanoramicView() {
        return PanoramicView;
    }

    public void setPanoramicView(String PanoramicView) {
        this.PanoramicView = PanoramicView;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return "BWangxiaobao{" +
        "id=" + id +
        ", projectId=" + ProjectId +
        ", projectName=" + ProjectName +
        ", name=" + Name +
        ", idNumber=" + IdNumber +
        ", verifyTime=" + VerifyTime +
        ", riskStatus=" + RiskStatus +
        ", riskApproveStatus=" + RiskApproveStatus +
        ", riskApproveRemark=" + RiskApproveRemark +
        ", verifyResult=" + VerifyResult +
        ", firstPhotoTime=" + FirstPhotoTime +
        ", image=" + Image +
        ", panoramicView=" + PanoramicView +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
