package com.tahoecn.xkc.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 旺小宝-客户表
 * @author: 张晓东
 * @time: 2020/6/2 17:40
 */
@Document(collection = "faceDetectCustomer")
public class FaceDetectCustomer implements Serializable {
    @Id
    private String id;//主键
    private String name;//客户名称
    private String status;//客户状态
    private String salerName;//置业顾问名称
    private String agent;//经纪人
    private String channelCompany;//所属渠道公司
    private Date firstReadTime;//首次带看时间
    private Date subscriptionTime;//认购时间
    private Date finishTime;//签约时间
    private String finishNo;//签约房号
    private String mobile;//移动电话
    private String remark;//备注
    private Date reportTime;//报备时间
    private Date firstPhotoTime;//首次人脸抓拍时间
    private Date lastPhotoTime;//最后人脸抓拍时间
    private Date firstPhoneTime;//首次来电时间
    private Date firstContactTime;//
    private String firstContactType;//
    private Date freshCardTime;//刷生份证时间
    private String proveType;//认证类型
    private String faceImage;//人脸的base64码,没有这个码的后台都不展示
    private String account;//属于哪个用户导入的
    private String channeId;//渠道身份
    private String riskApproveStatus;//复核风险状态
    private String riskApproveRemark;//风险复核备注
    private String riskApproveFile;//风险复核文件(附件)地址
    private String riskApproveUserName;//风险复核人描述
    private Date riskApproveTime;//风险复核时间
    private Date importTime;//导入时间
    private String correctionType;//校正类型
    private String correctionStatus;//校正状态
    private String projectId;//项目id
    private String projectName;//项目名称
    private String fptUpdateSource;//首次抓拍时间更新来源
    private Integer brushCardNotPassCount;//刷证未通过次数
    private Integer brushCardHasPassCount;//刷证通过次数
    private String brushCardApprovedCount;//书证复核通过次数
    private String riskRuleId;//风控规则id
    private Boolean prepareReportRisk;//提前报备风险
    private Boolean limitReportRisk;//报备期限风险
    private Boolean limitFinishRisk;//成交期限风险
    private Boolean nonePhotoBeforeBrushCard;//刷证前无抓拍异常
    private Date createTime;//创建时间
    private String createUser;//创建人
    private Date updateTime;//更新时间
    private String updateUser;//更新人
    private String idNumber;//客户身份证号
    private String groupId;//
    private String enabled;//
    private String riskStatus;//
    private Date riskDetermineTime;//
    private List reports;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getChannelCompany() {
        return channelCompany;
    }

    public void setChannelCompany(String channelCompany) {
        this.channelCompany = channelCompany;
    }

    public Date getFirstReadTime() {
        return firstReadTime;
    }

    public void setFirstReadTime(Date firstReadTime) {
        this.firstReadTime = firstReadTime;
    }

    public Date getSubscriptionTime() {
        return subscriptionTime;
    }

    public void setSubscriptionTime(Date subscriptionTime) {
        this.subscriptionTime = subscriptionTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getFinishNo() {
        return finishNo;
    }

    public void setFinishNo(String finishNo) {
        this.finishNo = finishNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getFirstPhotoTime() {
        return firstPhotoTime;
    }

    public void setFirstPhotoTime(Date firstPhotoTime) {
        this.firstPhotoTime = firstPhotoTime;
    }

    public Date getLastPhotoTime() {
        return lastPhotoTime;
    }

    public void setLastPhotoTime(Date lastPhotoTime) {
        this.lastPhotoTime = lastPhotoTime;
    }

    public Date getFirstPhoneTime() {
        return firstPhoneTime;
    }

    public void setFirstPhoneTime(Date firstPhoneTime) {
        this.firstPhoneTime = firstPhoneTime;
    }

    public Date getFirstContactTime() {
        return firstContactTime;
    }

    public void setFirstContactTime(Date firstContactTime) {
        this.firstContactTime = firstContactTime;
    }

    public String getFirstContactType() {
        return firstContactType;
    }

    public void setFirstContactType(String firstContactType) {
        this.firstContactType = firstContactType;
    }

    public Date getFreshCardTime() {
        return freshCardTime;
    }

    public void setFreshCardTime(Date freshCardTime) {
        this.freshCardTime = freshCardTime;
    }

    public String getProveType() {
        return proveType;
    }

    public void setProveType(String proveType) {
        this.proveType = proveType;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getChanneId() {
        return channeId;
    }

    public void setChanneId(String channeId) {
        this.channeId = channeId;
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

    public String getRiskApproveFile() {
        return riskApproveFile;
    }

    public void setRiskApproveFile(String riskApproveFile) {
        this.riskApproveFile = riskApproveFile;
    }

    public String getRiskApproveUserName() {
        return riskApproveUserName;
    }

    public void setRiskApproveUserName(String riskApproveUserName) {
        this.riskApproveUserName = riskApproveUserName;
    }

    public Date getRiskApproveTime() {
        return riskApproveTime;
    }

    public void setRiskApproveTime(Date riskApproveTime) {
        this.riskApproveTime = riskApproveTime;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public String getCorrectionType() {
        return correctionType;
    }

    public void setCorrectionType(String correctionType) {
        this.correctionType = correctionType;
    }

    public String getCorrectionStatus() {
        return correctionStatus;
    }

    public void setCorrectionStatus(String correctionStatus) {
        this.correctionStatus = correctionStatus;
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

    public String getFptUpdateSource() {
        return fptUpdateSource;
    }

    public void setFptUpdateSource(String fptUpdateSource) {
        this.fptUpdateSource = fptUpdateSource;
    }

    public Integer getBrushCardNotPassCount() {
        return brushCardNotPassCount;
    }

    public void setBrushCardNotPassCount(Integer brushCardNotPassCount) {
        this.brushCardNotPassCount = brushCardNotPassCount;
    }

    public Integer getBrushCardHasPassCount() {
        return brushCardHasPassCount;
    }

    public void setBrushCardHasPassCount(Integer brushCardHasPassCount) {
        this.brushCardHasPassCount = brushCardHasPassCount;
    }

    public String getBrushCardApprovedCount() {
        return brushCardApprovedCount;
    }

    public void setBrushCardApprovedCount(String brushCardApprovedCount) {
        this.brushCardApprovedCount = brushCardApprovedCount;
    }

    public String getRiskRuleId() {
        return riskRuleId;
    }

    public void setRiskRuleId(String riskRuleId) {
        this.riskRuleId = riskRuleId;
    }

    public Boolean getPrepareReportRisk() {
        return prepareReportRisk;
    }

    public void setPrepareReportRisk(Boolean prepareReportRisk) {
        this.prepareReportRisk = prepareReportRisk;
    }

    public Boolean getLimitReportRisk() {
        return limitReportRisk;
    }

    public void setLimitReportRisk(Boolean limitReportRisk) {
        this.limitReportRisk = limitReportRisk;
    }

    public Boolean getLimitFinishRisk() {
        return limitFinishRisk;
    }

    public void setLimitFinishRisk(Boolean limitFinishRisk) {
        this.limitFinishRisk = limitFinishRisk;
    }

    public Boolean getNonePhotoBeforeBrushCard() {
        return nonePhotoBeforeBrushCard;
    }

    public void setNonePhotoBeforeBrushCard(Boolean nonePhotoBeforeBrushCard) {
        this.nonePhotoBeforeBrushCard = nonePhotoBeforeBrushCard;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(String riskStatus) {
        this.riskStatus = riskStatus;
    }

    public Date getRiskDetermineTime() {
        return riskDetermineTime;
    }

    public void setRiskDetermineTime(Date riskDetermineTime) {
        this.riskDetermineTime = riskDetermineTime;
    }

    public List getReports() {
        return reports;
    }

    public void setReports(List reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "FaceDetectCustomer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", salerName='" + salerName + '\'' +
                ", agent='" + agent + '\'' +
                ", channelCompany='" + channelCompany + '\'' +
                ", firstReadTime=" + firstReadTime +
                ", finishTime=" + finishTime +
                ", finishNo='" + finishNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", remark='" + remark + '\'' +
                ", reportTime=" + reportTime +
                ", firstPhotoTime=" + firstPhotoTime +
                ", lastPhotoTime=" + lastPhotoTime +
                ", firstPhoneTime=" + firstPhoneTime +
                ", firstContactTime=" + firstContactTime +
                ", firstContactType='" + firstContactType + '\'' +
                ", freshCardTime=" + freshCardTime +
                ", proveType='" + proveType + '\'' +
                ", faceImage='" + faceImage + '\'' +
                ", account='" + account + '\'' +
                ", channeId='" + channeId + '\'' +
                ", riskApproveStatus='" + riskApproveStatus + '\'' +
                ", riskApproveRemark='" + riskApproveRemark + '\'' +
                ", riskApproveFile='" + riskApproveFile + '\'' +
                ", riskApproveUserName='" + riskApproveUserName + '\'' +
                ", riskApproveTime=" + riskApproveTime +
                ", importTime=" + importTime +
                ", correctionType='" + correctionType + '\'' +
                ", correctionStatus='" + correctionStatus + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", fptUpdateSource='" + fptUpdateSource + '\'' +
                ", brushCardNotPassCount=" + brushCardNotPassCount +
                ", brushCardHasPassCount=" + brushCardHasPassCount +
                ", brushCardApprovedCount='" + brushCardApprovedCount + '\'' +
                ", riskRuleId='" + riskRuleId + '\'' +
                ", prepareReportRisk=" + prepareReportRisk +
                ", limitReportRisk=" + limitReportRisk +
                ", limitFinishRisk=" + limitFinishRisk +
                ", nonePhotoBeforeBrushCard=" + nonePhotoBeforeBrushCard +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", groupId='" + groupId + '\'' +
                ", enabled='" + enabled + '\'' +
                ", riskStatus='" + riskStatus + '\'' +
                ", riskDetermineTime=" + riskDetermineTime +
                ", reports=" + reports +
                '}';
    }
}
