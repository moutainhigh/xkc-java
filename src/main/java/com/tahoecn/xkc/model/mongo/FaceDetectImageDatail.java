package com.tahoecn.xkc.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 旺小宝-抓拍记录表
 * @author: 张晓东
 * @time: 2020/6/2 17:41
 */
@Document(collection = "faceDetectImageDatail")
public class FaceDetectImageDatail implements Serializable {

    @Id
    private String id;
    private String projectId;//项目id
    private String stDeviceId;//设备id
    private String deviceEnum;//设备供应商
    private String faceToken;//脸唯一标识
    private String panoramicView;//全景
    private String faceImage;//人脸
    private Date snapDate;//抓拍时间
    private String newFace;//是否为新人脸
    private List similarFaces;//相似人脸
    private String syncId;//同步唯一标识
    private String score;//抓拍分值
    private String faceIQA;//评分
    private String video;//视频

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

    public String getStDeviceId() {
        return stDeviceId;
    }

    public void setStDeviceId(String stDeviceId) {
        this.stDeviceId = stDeviceId;
    }

    public String getDeviceEnum() {
        return deviceEnum;
    }

    public void setDeviceEnum(String deviceEnum) {
        this.deviceEnum = deviceEnum;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getPanoramicView() {
        return panoramicView;
    }

    public void setPanoramicView(String panoramicView) {
        this.panoramicView = panoramicView;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public Date getSnapDate() {
        return snapDate;
    }

    public void setSnapDate(Date snapDate) {
        this.snapDate = snapDate;
    }

    public String getNewFace() {
        return newFace;
    }

    public void setNewFace(String newFace) {
        this.newFace = newFace;
    }

    public List getSimilarFaces() {
        return similarFaces;
    }

    public void setSimilarFaces(List similarFaces) {
        this.similarFaces = similarFaces;
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFaceIQA() {
        return faceIQA;
    }

    public void setFaceIQA(String faceIQA) {
        this.faceIQA = faceIQA;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
