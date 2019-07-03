package com.tahoecn.xkc.converter;

public class Result {
    /**
     * 反馈数据
     */
    private Object data;

    /**
     * 反馈信息
     */
    private String errmsg;

    /**
     * 状态码
     */
    private int errcode;
    public Result() {

    }
    public Result(Object data, String errmsg, int errcode) {
        this.data = data;
        this.errmsg = errmsg;
        this.errcode = errcode;
    }

    public Result(String errmsg) {
        this.errmsg = errmsg;
    }

    public Result(Object data, int errcode) {
        this.data = data;
        this.errcode = errcode;
    }

    public static ResponseMessage ok(Object data) {
        return new ResponseMessage(true, data);
    }

    public static ResponseMessage okm(String message) {
        ResponseMessage responseMessage = new ResponseMessage(true, null);
        responseMessage.setCode(200);
        responseMessage.setMessage(message);
        return responseMessage;
    }

    public static ResponseMessage error(String message) {

        return new ResponseMessage(message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
