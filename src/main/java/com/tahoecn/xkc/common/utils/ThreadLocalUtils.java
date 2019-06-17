/**
 * 
 */
package com.tahoecn.xkc.common.utils;

import com.tahoecn.xkc.model.CsUcUser;

/**
 * @author 王胜东--wangsd@t-ark.com
 * @date 2018年9月5日 下午5:18:08
 * @desc
 */
public class ThreadLocalUtils {

	private static final ThreadLocal<CsUcUser> LOCAL = new ThreadLocal<CsUcUser>();

	public static void setUser(CsUcUser csUcUser) {
		LOCAL.set(csUcUser);
	}

	/**
	 * 获取对象
	 * 
	 * @return
	 */
	public static CsUcUser get() {
		return LOCAL.get();
	}

	/**
	 * 获取当前登陆用户名
	 * 
	 * @return
	 */
	public static String getUserName() {
		return LOCAL.get().getFdUsername();
	}

	/**
	 * 获取当前登陆用户中文名
	 * 
	 * @return
	 */
	public static String getRealName() {
		return LOCAL.get().getFdName();
	}

	/**
	 * 获取当前登陆用户ID
	 * 
	 * @return
	 */
	public static String getUserId() {
		return LOCAL.get().getFdSid();
	}

	/**
	 * 清理线程
	 */
	public static void remove() {
		LOCAL.remove();
	}

}
