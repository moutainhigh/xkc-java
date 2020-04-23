package com.tahoecn.xkc.model.miniprogram.vo;

import com.tahoecn.xkc.model.miniprogram.ValidationParameters;


/**
 * <p>
 *  外渠角色注册参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public class UserRegisterVO extends ValidationParameters {

    private static final long serialVersionUID = 1L;

    private String mobile;

    private String name;

    private String gender;

    private String channelTypeID;

    private String certificatesType;

    private String certificatesNo;

    private String channelOrgCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChannelTypeID() {
        return channelTypeID;
    }

    public void setChannelTypeID(String channelTypeID) {
        this.channelTypeID = channelTypeID;
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesNo() {
        return certificatesNo;
    }

    public void setCertificatesNo(String certificatesNo) {
        this.certificatesNo = certificatesNo;
    }

    public String getChannelOrgCode() {
        return channelOrgCode;
    }

    public void setChannelOrgCode(String channelOrgCode) {
        this.channelOrgCode = channelOrgCode;
    }
}
