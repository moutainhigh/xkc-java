package com.tahoecn.xkc.model.customer;

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
 * @since 2019-07-11
 */
@TableName("S_Message")
@ApiModel(value="SMessage对象", description="")
public class SMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("BizID")
    private String BizID;

    @TableField("BizType")
    private String BizType;

    @TableField("Subject")
    private String Subject;

    @TableField("Content")
    private String Content;

    @TableField("Sender")
    private String Sender;

    @TableField("SendTime")
    private Date SendTime;

    @TableField("MessageType")
    private String MessageType;

    @TableField("Receiver")
    private String Receiver;

    @TableField("IsRead")
    private Integer IsRead;

    @TableField("ReadTime")
    private Date ReadTime;

    @TableField("MsgUrl")
    private String MsgUrl;

    @TableField("IsPush")
    private Integer IsPush;

    @TableField("IsNeedPush")
    private Integer IsNeedPush;

    @TableField("IsApprove")
    private Integer IsApprove;

    @TableField("PushTime")
    private Date PushTime;

    @TableField("PushResult")
    private String PushResult;

    @TableField("Ext1")
    private String Ext1;

    @TableField("Ext2")
    private String Ext2;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getBizID() {
        return BizID;
    }

    public void setBizID(String BizID) {
        this.BizID = BizID;
    }
    public String getBizType() {
        return BizType;
    }

    public void setBizType(String BizType) {
        this.BizType = BizType;
    }
    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }
    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    public String getSender() {
        return Sender;
    }

    public void setSender(String Sender) {
        this.Sender = Sender;
    }
    public Date getSendTime() {
        return SendTime;
    }

    public void setSendTime(Date SendTime) {
        this.SendTime = SendTime;
    }
    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String MessageType) {
        this.MessageType = MessageType;
    }
    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }
    public Integer getIsRead() {
        return IsRead;
    }

    public void setIsRead(Integer IsRead) {
        this.IsRead = IsRead;
    }
    public Date getReadTime() {
        return ReadTime;
    }

    public void setReadTime(Date ReadTime) {
        this.ReadTime = ReadTime;
    }
    public String getMsgUrl() {
        return MsgUrl;
    }

    public void setMsgUrl(String MsgUrl) {
        this.MsgUrl = MsgUrl;
    }
    public Integer getIsPush() {
        return IsPush;
    }

    public void setIsPush(Integer IsPush) {
        this.IsPush = IsPush;
    }
    public Integer getIsNeedPush() {
        return IsNeedPush;
    }

    public void setIsNeedPush(Integer IsNeedPush) {
        this.IsNeedPush = IsNeedPush;
    }
    public Integer getIsApprove() {
        return IsApprove;
    }

    public void setIsApprove(Integer IsApprove) {
        this.IsApprove = IsApprove;
    }
    public Date getPushTime() {
        return PushTime;
    }

    public void setPushTime(Date PushTime) {
        this.PushTime = PushTime;
    }
    public String getPushResult() {
        return PushResult;
    }

    public void setPushResult(String PushResult) {
        this.PushResult = PushResult;
    }
    public String getExt1() {
        return Ext1;
    }

    public void setExt1(String Ext1) {
        this.Ext1 = Ext1;
    }
    public String getExt2() {
        return Ext2;
    }

    public void setExt2(String Ext2) {
        this.Ext2 = Ext2;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "SMessage{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", BizID=" + BizID +
        ", BizType=" + BizType +
        ", Subject=" + Subject +
        ", Content=" + Content +
        ", Sender=" + Sender +
        ", SendTime=" + SendTime +
        ", MessageType=" + MessageType +
        ", Receiver=" + Receiver +
        ", IsRead=" + IsRead +
        ", ReadTime=" + ReadTime +
        ", MsgUrl=" + MsgUrl +
        ", IsPush=" + IsPush +
        ", IsNeedPush=" + IsNeedPush +
        ", IsApprove=" + IsApprove +
        ", PushTime=" + PushTime +
        ", PushResult=" + PushResult +
        ", Ext1=" + Ext1 +
        ", Ext2=" + Ext2 +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
