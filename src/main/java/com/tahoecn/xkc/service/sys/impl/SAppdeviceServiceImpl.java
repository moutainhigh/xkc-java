package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.SAppdeviceMapper;
import com.tahoecn.xkc.model.sys.SAppdevice;
import com.tahoecn.xkc.service.sys.ISAppdeviceService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@Service
public class SAppdeviceServiceImpl extends ServiceImpl<SAppdeviceMapper, SAppdevice> implements ISAppdeviceService {

	@Autowired
    private SAppdeviceMapper sAppdeviceMapper;
	
	/**
	 * 解绑APP设备信息
	 * @param map
	 */
	@Override
	public void SystemAppDeviceDetail_Update(Map<String, Object> map) {
		sAppdeviceMapper.SystemAppDeviceDetail_Update(map);
	}

}
