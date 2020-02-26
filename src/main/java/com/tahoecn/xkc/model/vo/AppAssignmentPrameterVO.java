package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-07 11:13
 * @Description: //TODO app指定分配新增接口vo的参数内部的vo
 **/
public class AppAssignmentPrameterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 当前对象的id*/
    private String thisId;

    /* 当前对象里面的WXUserID字段*/
    private String wxUserId;

    public String getThisId() {
        return thisId;
    }

    public void setThisId(String thisId) {
        this.thisId = thisId;
    }

    public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId;
    }
}
