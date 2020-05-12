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

    @TableField("projectId")
    private String projectId;

    @TableField("projectName")
    private String projectName;

    private String name;

    @TableField("idNumber")
    private String idNumber;

    @TableField("verifyTime")
    private Date verifyTime;

    @TableField("riskStatus")
    private String riskStatus;

    @TableField("riskApproveStatus")
    private String riskApproveStatus;

    @TableField("riskApproveRemark")
    private String riskApproveRemark;

    @TableField("verifyResult")
    private Integer verifyResult;

    @TableField("firstPhotoTime")
    private Date firstPhotoTime;

    private String image;

    @TableField("panoramicView")
    private String panoramicView;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }
    public String getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }
    public String getRiskApproveStatus() {
        return riskApproveStatus;
    }

    public void setRiskApproveStatus(String riskApproveStatus) {
        this.riskApproveStatus = riskApproveStatus;
    }
    public String getRiskApproveRemark() {
        return riskApproveRemark;
    }

    public void setRiskApproveRemark(String riskApproveRemark) {
        this.riskApproveRemark = riskApproveRemark;
    }
    public Integer getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(Integer verifyResult) {
        this.verifyResult = verifyResult;
    }
    public Date getFirstPhotoTime() {
        return firstPhotoTime;
    }

    public void setFirstPhotoTime(Date firstPhotoTime) {
        this.firstPhotoTime = firstPhotoTime;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getPanoramicView() {
        return panoramicView;
    }

    public void setPanoramicView(String panoramicView) {
        this.panoramicView = panoramicView;
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
        ", projectId=" + projectId +
        ", projectName=" + projectName +
        ", name=" + name +
        ", idNumber=" + idNumber +
        ", verifyTime=" + verifyTime +
        ", riskStatus=" + riskStatus +
        ", riskApproveStatus=" + riskApproveStatus +
        ", riskApproveRemark=" + riskApproveRemark +
        ", verifyResult=" + verifyResult +
        ", firstPhotoTime=" + firstPhotoTime +
        ", image=" + image +
        ", panoramicView=" + panoramicView +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
