package com.tahoecn.xkc.common.enums;

public enum AppTokenStatus {

    TOKEN_SUCCESS(200, "成功"),
    TOKEN_EXPIRED(403, "token过期"),
    INVALID_REQUEST(401, "token无效"),
    TOKEN_INEXISTENCE(400, "token不存在");

    private int code;

    private String message;

    AppTokenStatus(int code, String message) {
        this.code = code;
        this.message = message;
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
