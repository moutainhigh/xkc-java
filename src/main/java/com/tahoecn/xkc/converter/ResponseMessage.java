package com.tahoecn.xkc.converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.tahoecn.xkc.common.enums.ResultStatus;

/**
 * 返回信息封装
 * @author 张晓曦
 *
 */
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 3896336125845619148L;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 反馈数据
     */
    private Object data;

    /**
     * 反馈信息
     */
    private String message;

    /**
     * 响应码 ,token失效、认证失败时的code为：401
     */
    private int code;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", this.success);
        if (data != null)
            map.put("data", this.getData());
        if (message != null)
            map.put("message", this.getMessage());
        map.put("code", this.getCode());
        return map;
    }

    protected ResponseMessage(String message) {
        this.code = 500;
        this.message = message;
        this.success = false;
    }

    protected ResponseMessage(boolean success, Object data) {
        this.code = success ? 200 : 500;
        this.data = data;
        this.success = success;
    }

    protected ResponseMessage(boolean success, Object data, int code) {
        this(success, data);
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public ResponseMessage setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
    }

    public int getCode() {
        return code;
    }

    public ResponseMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public static ResponseMessage fromJson(String json) {
        return JSON.parseObject(json, ResponseMessage.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseMessage ok() {
        return ok(null);
    }

    public static ResponseMessage ok(Object data) {
        return new ResponseMessage(true, data);
    }

    public static ResponseMessage error(String message) {
        return new ResponseMessage(message);
    }

    public static ResponseMessage error(String message, int code) {
        return new ResponseMessage(message).setCode(code);
    }
    
    public static ResponseMessage error(String message, int code,Object data) {
        return new ResponseMessage(message).setCode(code).setData(data);
    }

    public static ResponseMessage error(ResultStatus resultStatus) {
        return  new ResponseMessage(resultStatus.getMessage()).setCode(resultStatus.getCode());
    }
}
