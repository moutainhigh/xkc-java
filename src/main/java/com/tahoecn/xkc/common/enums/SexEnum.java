package com.tahoecn.xkc.common.enums;

public enum SexEnum {

    // 男
    MAN(1, "50827B18-5BCC-454C-B658-09AF4328D2A0"),
    // 女
    WOMAN(0, "EC3936F8-82DC-49AF-A8EB-153730359DE7");

    SexEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    public static SexEnum getEnumByCode(Integer code) {
        if (code == null) {
            // code为空返回草稿
            return null;
        }
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum;
            }
        }
        return null;
    }

    public static SexEnum getEnumByMessage(String message) {
        if (null == message || "".equals(message)) {
            // code为空返回草稿
            return null;
        }
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getMessage().equals(message)) {
                return sexEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
