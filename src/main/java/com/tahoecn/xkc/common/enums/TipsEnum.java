/***************************************
 * @FileName
 * @FileID
 * @CreaterName konghao
 * @Email konghao@sky-dome.com.cn
 ***************************************/
package com.tahoecn.xkc.common.enums;

/**
 * <p>
 * 提示信息统一记录类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public enum TipsEnum {

    /**
     * 成功
     */
    Success(0, "成功"),

    /**
     * 失败
     */
    Failed(1, "失败"),

    ServerError(1, "运行异常，请联系管理员");

    /**
     * 状态名称
     */
    private String msg;
    /**
     * 状态
     */
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private TipsEnum(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static TipsEnum getEnumByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (TipsEnum tipsEnum : TipsEnum.values()) {
            if (tipsEnum.getCode().equals(code)) {
                return tipsEnum;
            }
        }
        return null;
    }
}
