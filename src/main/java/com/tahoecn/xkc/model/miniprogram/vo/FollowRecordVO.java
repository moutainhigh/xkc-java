package com.tahoecn.xkc.model.miniprogram.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public class FollowRecordVO {

    private String follwUpUserId;

    private String follwUpUserName;

    private String follwUpUserMobile;

    private String followUpContent;

    private String follwUpType;

    private String follwUpWay;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String createTime;

    public String getFollwUpUserId() {
        return follwUpUserId;
    }

    public void setFollwUpUserId(String follwUpUserId) {
        this.follwUpUserId = follwUpUserId;
    }

    public String getFollwUpUserName() {
        return follwUpUserName;
    }

    public void setFollwUpUserName(String follwUpUserName) {
        this.follwUpUserName = follwUpUserName;
    }

    public String getFollwUpUserMobile() {
        return follwUpUserMobile;
    }

    public void setFollwUpUserMobile(String follwUpUserMobile) {
        this.follwUpUserMobile = follwUpUserMobile;
    }

    public String getFollowUpContent() {
        return followUpContent;
    }

    public void setFollowUpContent(String followUpContent) {
        this.followUpContent = followUpContent;
    }

    public String getFollwUpType() {
        return follwUpType;
    }

    public void setFollwUpType(String follwUpType) {
        this.follwUpType = follwUpType;
    }

    public String getFollwUpWay() {
        return follwUpWay;
    }

    public void setFollwUpWay(String follwUpWay) {
        this.follwUpWay = follwUpWay;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
