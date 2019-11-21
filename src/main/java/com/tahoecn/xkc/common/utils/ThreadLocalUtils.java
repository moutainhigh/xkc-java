/**
 * 
 */
package com.tahoecn.xkc.common.utils;

import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.model.sys.SAccount;

/**
 * @author 王胜东--wangsd@t-ark.com
 * @date 2018年9月5日 下午5:18:08
 * @desc
 */
public class ThreadLocalUtils {


	private static final Log log = LogFactory.get();

	private static final ThreadLocal<SAccount> LOCAL = new ThreadLocal<>();

	public static void setUser(SAccount csUcUser) {
		LOCAL.set(csUcUser);
	}

	/**
	 * 获取对象
	 * 
	 * @return
	 */
	public static SAccount get() {
		return LOCAL.get();
	}

	/**
	 * 获取当前登陆用户名
	 * 
	 * @return
	 */
	public static String getUserName() {
		if (LOCAL.get() == null) {
			log.error("LOCAL.get() is null");
			return "";
		}
		return LOCAL.get().getUserName();
	}

	/**
	 * 获取当前登陆用户中文名
	 * 
	 * @return
	 */
	public static String getRealName() {
		return LOCAL.get().getEmployeeName();
	}

	/**
	 * 获取当前登陆用户ID
	 * 
	 * @return
	 */
	public static String getUserId() {
		return LOCAL.get().getId();
	}

	/**
	 * 清理线程
	 */
	public static void remove() {
		LOCAL.remove();
	}

}
