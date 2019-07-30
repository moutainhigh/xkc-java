package com.tahoecn.xkc.service.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.SLogs;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface ISLogsService extends IService<SLogs> {

	/**
	 * 记录日志
	 */
	void SystemLogsDetail_Insert(Map<String, Object> logMap, HttpServletRequest request);

	/**
	 * 操作日志
	 */

	List<Map<String, Object>> mBrokerCustomerDetail_Select(Map<String, Object> map);

	int mBrokerCustomerDetail_SelectAll(Map<String, Object> map);

}
