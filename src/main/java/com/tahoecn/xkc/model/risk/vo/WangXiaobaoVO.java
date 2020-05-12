package com.tahoecn.xkc.model.risk.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 旺小宝数据传入数据类
 * @author: 张晓东
 * @time: 2020/5/11 15:04
 */
public class WangXiaobaoVO implements Serializable {

    @NotNull(message = "项目id不能为空")
    private String projectId;    //客户方项目名称
    private String projectName;    //项目名称
    private String name;    //用户姓名
    @NotNull(message = "用户身份证号不能为空")
    private String idNumber;//用户身份证号
    private Date verifyTime;    //刷证时间
    private String riskStatus;    //系统风险状态:NORMAL正常，UNKNOWN未知，RISK风险
    private String riskApproveStatus;    //人工复核状态,二次复核时风险状态
    private String riskApproveRemark;    //人工复核备注,二次复核备注
    private Integer verifyResult;    //认证结果 1通过，-1未通过
    @NotNull(message = "首次抓拍时间不能为空")
    private Date firstPhotoTime;    //首次抓拍时间
    private String image;    //抓怕人脸小图URL
    private String panoramicView;    //抓拍人脸大图URL


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
}
