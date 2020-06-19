package com.tahoecn.xkc.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 旺小宝-客户刷证表
 * @author: 张晓东
 * @time: 2020/6/2 17:41
 */
@Document(collection = "faceDetectCustomerFreshCardInfo")
public class FaceDetectCustomerFreshCardInfo implements Serializable {

    @Id
    private String id;//主键
    private String projectId;//项目id
    private String customerId;//客户id
    private String name;//姓名
    private Integer sex;//性别
    private String nation;//名族
    private String birthday;//生日
    private String address;//地址
    private String idNumber;//身份证号码
    private String authority;//签发机关
    private String validity;//有效期
    private String idImage;//省份证base64码
    private String faceImage;//现场抓拍人脸base64码
    private Date verifyTime;//认证时间
    private Boolean hasPass;//认证结果
    private Boolean approved;//复核结果
    private Float verifyScore;//对比分值
    private Date uploadTime;//上传时间
    private Date approveTime;//复核时间
    private Date updateTime;//更新时间
    private String proveType;//认证类型
    private String deviceId;//设备id
    private List matchFaces;//匹配faceToken

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Boolean getHasPass() {
        return hasPass;
    }

    public void setHasPass(Boolean hasPass) {
        this.hasPass = hasPass;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Float getVerifyScore() {
        return verifyScore;
    }

    public void setVerifyScore(Float verifyScore) {
        this.verifyScore = verifyScore;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProveType() {
        return proveType;
    }

    public void setProveType(String proveType) {
        this.proveType = proveType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List getMatchFaces() {
        return matchFaces;
    }

    public void setMatchFaces(List matchFaces) {
        this.matchFaces = matchFaces;
    }
}
