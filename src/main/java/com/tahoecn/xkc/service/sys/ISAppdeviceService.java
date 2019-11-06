package com.tahoecn.xkc.service.sys;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.SAppdevice;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface ISAppdeviceService extends IService<SAppdevice> {

	/**
	 * 解绑APP设备信息
	 * @param map
	 */
	void SystemAppDeviceDetail_Update(Map<String, Object> map);

}
