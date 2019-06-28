package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class UnreadCountVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String MessageType;

    private Integer MessageCount;

    private String Content;

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public Integer getMessageCount() {
        return MessageCount;
    }

    public void setMessageCount(Integer messageCount) {
        MessageCount = messageCount;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
