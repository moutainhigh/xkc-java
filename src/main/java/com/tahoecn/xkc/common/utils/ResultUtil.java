package com.tahoecn.xkc.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.TipsEnum;

import java.util.List;

/**
 * 返回结果处理类
 */
public class ResultUtil {

    /**
     * 根据枚举获取返回信息 包含data
     *
     * @param code 错误编码
     * @param data 要返回的数据
     */
    public static <T> JSONResult setJsonResult(Integer code, T data) {
        JSONResult<T> jsonResult = new JSONResult<>();
        // 获取枚举信息
        TipsEnum tipsEnum = TipsEnum.getEnumByCode(code);
        if (tipsEnum == null) {
            return jsonResult;
        } else {
            jsonResult.setCode(tipsEnum.getCode());
            jsonResult.setMsg(tipsEnum.getMsg());
            jsonResult.setData(data);
        }
        return jsonResult;
    }

    /**
     * 根据枚举获取返回信息
     *
     * @param code 错误编码
     */
    public static <T> JSONResult setJsonResult(Integer code) {
        return setJsonResult(code, null);
    }


    /**
     * 操作成功
     */
    public static <T> JSONResult getSuccessJsonResult() {
        return new JSONResult<>(TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    /**
     * 操作失败
     */
    public static <T> JSONResult getFailJsonResult() {
        return new JSONResult<>(TipsEnum.Failed.getCode(), TipsEnum.Failed.getMsg());
    }

    /**
     * 操作成功
     */
    public static <T> JSONResult setServerErrorJsonResult(Integer code, String msg) {
        return new JSONResult<>(code, msg);
    }

    public static <T> JSONResult setJsonResult(JSONResult<T> jsonResult, Boolean dmlStatus) {
        if (dmlStatus) {
            jsonResult.setCode(TipsEnum.Success.getCode());
            jsonResult.setMsg(TipsEnum.Success.getMsg());
        } else {
            jsonResult.setCode(TipsEnum.Failed.getCode());
            jsonResult.setMsg(TipsEnum.Failed.getMsg());
        }
        return jsonResult;
    }

    public static <T> void setJsonResult(List<T> parameters, JSONResult<List<T>> jsonResult) {
        if (parameters != null) {
            jsonResult.setCode(TipsEnum.Success.getCode());
            jsonResult.setMsg(TipsEnum.Success.getMsg());
            jsonResult.setData(parameters);
        } else {
            jsonResult.setCode(TipsEnum.Failed.getCode());
            jsonResult.setMsg(TipsEnum.Failed.getMsg());
        }
    }

    public static <T> JSONResult<List<T>> setJsonResult(JSONResult<List<T>> jsonResult, IPage<T> Page) {
        if (Page.getRecords() != null) {
            jsonResult.setCode(TipsEnum.Success.getCode());
            jsonResult.setMsg(TipsEnum.Success.getMsg());
            jsonResult.setData(Page.getRecords());
            return jsonResult;
        } else {
            jsonResult.setCode(TipsEnum.Failed.getCode());
            jsonResult.setMsg(TipsEnum.Failed.getMsg());
            return jsonResult;
        }
    }

    public static <T> JSONResult<Object> setJsonResult(JSONResult<Object> jsonResult, Object resultData, String inputError) {
        if (inputError != null && !"".equals(inputError)) {
            jsonResult = setJsonResult(jsonResult, null, inputError, TipsEnum.Failed.getCode(), TipsEnum.Failed.getMsg());
        } else {
            if (resultData != null) {
                jsonResult = setJsonResult(jsonResult, resultData, inputError, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
            } else {
                jsonResult = setJsonResult(jsonResult, null, inputError, TipsEnum.Failed.getCode(), TipsEnum.Failed.getMsg());
            }
        }
        return jsonResult;
    }

    public static <T> JSONResult<Object> setJsonResult(JSONResult<Object> jsonResult, Object resultData, String inputError, int code, String msg) {
        if (inputError != null && !"".equals(inputError)) {
            jsonResult.setCode(TipsEnum.Failed.getCode());
            jsonResult.setMsg(TipsEnum.Failed.getMsg());
        } else {
            jsonResult.setCode(code);
            jsonResult.setMsg(msg);
            jsonResult.setData(resultData);
        }
        return jsonResult;
    }
}
