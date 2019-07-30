package com.tahoecn.xkc.mapper.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.SLogs;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface SLogsMapper extends BaseMapper<SLogs> {

	/*
	 * 操作日志
	 */
	List<Map<String, Object>> mBrokerCustomerDetail_Select(Map<String, Object> map);

	int mBrokerCustomerDetail_SelectAll(Map<String, Object> map);

}
