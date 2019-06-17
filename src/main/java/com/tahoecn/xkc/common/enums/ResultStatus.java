package com.tahoecn.xkc.common.enums;

/**
 * 自定义请求状态码
 * @author 张晓曦
 *
 */
public enum ResultStatus {
    SUCCESS(200, "成功"),;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
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
