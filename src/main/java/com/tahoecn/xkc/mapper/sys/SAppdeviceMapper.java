package com.tahoecn.xkc.mapper.sys;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.SAppdevice;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface SAppdeviceMapper extends BaseMapper<SAppdevice> {

	/**
	 * 解绑APP设备信息
	 * @param map
	 */
	void SystemAppDeviceDetail_Update(Map<String, Object> map);

}
