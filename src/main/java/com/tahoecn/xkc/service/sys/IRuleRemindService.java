package com.tahoecn.xkc.service.sys;

/**
 * TODO 消息分发服务
 * 
 * @ClassName: IRuleRemindService
 * @author: wq_qycc
 * @date: 2019年8月2日
 */
public interface IRuleRemindService {

	/**
	 * 案场消息提醒NEW
	 */
	void serviceAutoRemindNew();

	/**
	 * 渠道消息提醒
	 */
	void serviceAutoRemind_QD();

}
