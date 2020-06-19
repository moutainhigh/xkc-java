package com.tahoecn.xkc.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @description: 旺小宝-客户详情表
 * @author: 张晓东
 * @time: 2020/6/2 17:40
 */
@Document(collection = "faceDetectCustomerDetail")
public class FaceDetectCustomerDetail implements Serializable {

    @Id
    private String id;//主键
    private String name;//姓名
    private String sex;//性别
    private String nation;//名族
    private String birthday;//生日
    private String address;//地址
    private String idNumber;//省份证号码
    private String authority;//签发机关
    private String validity;//有效期
    private String idImage;//身份证照片base64码
    private String faceImage;//现场拍摄人脸base64码
    private String verifyTime;//认证时间
    private String verifyResult;//认证结果
    private Float verifyScore;//对比分值
    private String projectId;//项目id
    private String channelName;//渠道名称
    private String projectName;//项目名称
    private String saleId;//置业顾问id
    private String agent;//经纪人
    private String freshenTime;//刷新时间
    private String faceToken;//匹配
    private String deviceId;//拍摄设备
    private Set matchFaces;//
    private Date createTime;//
    private String createUser;//
    private Date updateTime;//
    private String updateUser;//

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(String verifyResult) {
        this.verifyResult = verifyResult;
    }

    public Float getVerifyScore() {
        return verifyScore;
    }

    public void setVerifyScore(Float verifyScore) {
        this.verifyScore = verifyScore;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getFreshenTime() {
        return freshenTime;
    }

    public void setFreshenTime(String freshenTime) {
        this.freshenTime = freshenTime;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Set getMatchFaces() {
        return matchFaces;
    }

    public void setMatchFaces(Set matchFaces) {
        this.matchFaces = matchFaces;
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
}
