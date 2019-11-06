package com.tahoecn.xkc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 短息发送日志表
 * </p>
 *
 * @author zghw
 * @since 2018-11-18
 */
public class CsSendSmsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 手机号
     */
    private String sendMobiles;
    /**
     * 发送内容
     */
    private String contentDesc;
    /**
     * 返回信息
     */
    private String sendResult;
    /**
     * 发送时间
     */
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSendMobiles() {
        return sendMobiles;
    }

    public void setSendMobiles(String sendMobiles) {
        this.sendMobiles = sendMobiles;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CsSendSmsLog{" +
        "id=" + id +
        ", userName=" + userName +
        ", sendMobiles=" + sendMobiles +
        ", contentDesc=" + contentDesc +
        ", sendResult=" + sendResult +
        ", createDate=" + createDate +
        "}";
    }
}
