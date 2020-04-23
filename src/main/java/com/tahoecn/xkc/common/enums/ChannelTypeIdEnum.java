package com.tahoecn.xkc.common.enums;

/**
 * <p>
 * 角色类型枚举
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public enum ChannelTypeIdEnum {

    // 自由经纪
    FREE(0, "46830C26-0E01-4041-8054-3865CCDD26AD"),
    // 中介同行
    MIDDLE(1, "32C92DA0-DA13-4C21-A55E-A1D16955882C"),
    // 老业主
    OLD(2, "EB4AD331-F4AD-46D6-889A-D45575ECEE66"),
    // 泰禾员工
    STAFF(3, "725FA5F6-EC92-4DC6-8D47-A8E74B7829AD"),

    // 自渠负责人
    Self_Drains(4, "9584A4B7-F105-44BA-928D-F2FBA2F3B4A4"),
    // 销售负责人
    Sale(5, "938935B7-4131-4E61-A811-4323A7F193A2"),
    // 置业顾问
    Adviser(6, "0269F35E-B32D-4D12-8496-4E6E4CE597B7"),
    // 自渠专员
    Self_Commissioner(7, "48FC928F-6EB5-4735-BF2B-29B1F591A582");

    ChannelTypeIdEnum(int code, String message) {
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

    public static ChannelTypeIdEnum getEnumByCode(Integer code) {
        if (code == null) {
            // code为空返回草稿
            return null;
        }
        for (ChannelTypeIdEnum channelTypeEnum : ChannelTypeIdEnum.values()) {
            if (channelTypeEnum.getCode() == code) {
                return channelTypeEnum;
            }
        }
        return null;
    }

    public static ChannelTypeIdEnum getEnumByMessage(String message) {
        if (null == message || "".equals(message)) {
            // code为空返回草稿
            return null;
        }
        for (ChannelTypeIdEnum channelTypeEnum : ChannelTypeIdEnum.values()) {
            if (channelTypeEnum.getMessage().equals(message)) {
                return channelTypeEnum;
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
