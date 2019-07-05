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

    public Result(String errmsg, int errcode) {
        this.errmsg = errmsg;
        this.errcode = errcode;
    }

    public Result(Object data, int errcode) {
        this.data = data;
        this.errcode = errcode;
    }

    public static Result ok(Object data) {
        return new Result(data,"成功",0);
    }

    public static Result okm(String message) {
        Result result = new Result();
        result.setErrcode(0);
        result.setErrmsg(message);
        return result;
    }
    public static Result errormsg(int errcode,String message) {
        return new Result(message,errcode);
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
