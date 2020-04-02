package com.tahoecn.xkc.common.enums;


/**
 * <p>
 * 证件类型枚举
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public enum CertificatesIdEnum {

    // 身份证
    IDCARD(0, "BAB5C6D2-76DD-4708-9938-D281C3103F9E"),
    // 军官证
    OFFICER(1, "9159A206-2AB4-4924-A724-D0CB03E2D523"),
    // 护照
    passport(2, "D50C24E1-2FEA-4517-B613-E88A436397AD"),
    // 台胞证
    compatriots(3, "14A2EB9F-A3B2-45EE-BBC6-75CB99B89B1A"),
    // 通行证
    pass(4, "E952F8E8-9F96-4017-8179-2703D5377036"),
    // 其他
    other(5, "D2F21115-FD38-4654-8F3F-FA8B4E526A45");

    CertificatesIdEnum(int code, String message) {
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

    public static CertificatesIdEnum getEnumByCode(Integer code) {
        if (code == null) {
            // code为空返回草稿
            return null;
        }
        for (CertificatesIdEnum certificatesIdEnum : CertificatesIdEnum.values()) {
            if (certificatesIdEnum.getCode() == code) {
                return certificatesIdEnum;
            }
        }
        return null;
    }

    public static CertificatesIdEnum getEnumByMessage(String message) {
        if (null == message || "".equals(message)) {
            // code为空返回草稿
            return null;
        }
        for (CertificatesIdEnum certificatesIdEnum : CertificatesIdEnum.values()) {
            if (certificatesIdEnum.getMessage().equals(message)) {
                return certificatesIdEnum;
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
