package com.tahoecn.xkc.common.constants;

import org.springframework.beans.factory.annotation.Value;

public class GlobalConstants {

	/**
	 * 小程序请求头openid
	 */
	public static final String OPEN_ID = "openId";


	/**
	 * 是否启用 y/n
	 */
	public static final String Y = "y";
	public static final String N = "n";

	public static final String USERABLE = "1";

	//预制订单状态
	public static final String BEFOREHAND_STATUS_SHOW = "s";
	public static final String BEFOREHAND_STATUS_HIDE = "h";
	/**
	 * 返回code
	 */
	public static final int S_CODE = 200;
	public static final int E_CODE = 500;

	/**
	 * 状态码 status
	 */
	public static final String STATUS_D = "draft"; 
	public static final String STATUS_D_NAME = "未发布"; 
	
	public static final String STATUS_R = "running";
	public static final String STATUS_R_NAME = "进行中";

	public static final String STATUS_CLOSE = "close";
	public static final String STATUS_CLOSE_NAME = "已关闭";

	public static final String STATUS_END = "end";
	public static final String STATUS_END_NAME = "已结束";
}
