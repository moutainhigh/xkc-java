package com.tahoecn.xkc.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串屏蔽工具类
 *
 * @author mystic
 */
public class StringShieldUtil {

    /**
     * 大部分敏感词汇在10个以内，直接返回缓存的字符串
     */
    private static final String[] starArr = {"*", "**", "***", "****", "*****", "******", "*******", "********", "*********", "**********"};

    /**
     * 只保留首字符的带*字符串
     *
     * @param content 文本
     * @return 带*字符串，eg: 陈**、王*
     */
    public static String getFilterStrHasFirstChar(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }

        if (content.length() == 1) {
            return content;
        }

        String wantStr = content.substring(0, 1);

        String starChar = getStarChar(content.length() - 1);

        return wantStr + starChar;
    }

    /**
     * 把字符串全部变成*号
     *
     * @param content 文本
     * @return 全*字符串，eg: ****、***
     */
    public static String getAllStarStr(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }

        return getStarChar(content.length());
    }

    /**
     * 过滤字符串中的敏感词汇
     *
     * @param content       文本
     * @param sensitiveWord 敏感词汇
     * @return
     */
    public static String filterSensitiveWords(String content, String sensitiveWord) {

        if (StringUtils.isBlank(content)
                || StringUtils.isBlank(sensitiveWord)) {
            return content;
        }

        //获取和敏感词汇相同数量的星号
        String starChar = getStarChar(sensitiveWord.length());

        //替换敏感词汇
        return content.replace(sensitiveWord, starChar);
    }

    /**
     * 生成n个星号的字符串
     *
     * @param length
     * @return
     */
    private static String getStarChar(int length) {
        if (length <= 0) {
            return "";
        }
        //大部分敏感词汇在10个以内，直接返回缓存的字符串
        if (length <= 10) {
            return starArr[length - 1];
        }

        //生成n个星号的字符串
        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            arr[i] = '*';
        }
        return new String(arr);
    }
}