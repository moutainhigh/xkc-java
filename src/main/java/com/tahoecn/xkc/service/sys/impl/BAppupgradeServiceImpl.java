package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.BAppupgradeMapper;
import com.tahoecn.xkc.model.sys.BAppupgrade;
import com.tahoecn.xkc.service.sys.IBAppupgradeService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
@Service
public class BAppupgradeServiceImpl extends ServiceImpl<BAppupgradeMapper, BAppupgrade> implements IBAppupgradeService {

	@Autowired
	private BAppupgradeMapper bAppupgradeMapper;
	/**
	 * 获取版本信息-默认第一条
	 */
	@Override
	public List<BAppupgrade> SystemAppVersion_Select(Map<String, Object> map) {
		return bAppupgradeMapper.SystemAppVersion_Select(map);
	}
	/**
	 * APP版本信息
	 */
	@Override
	public List<Map<String,Object>> SystemAppVersionList_Select() {
		return bAppupgradeMapper.SystemAppVersionList_Select();
	}
	/**
	 * APP版本信息修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void SystemAppVersion_Update(String versionID_ANDROID,String AppVersionCode_ANDROID,String Url_ANDROID,
			String versionID_IOS,String AppVersionCode_IOS,String Url_IOS,
			String versionID_IPAD,String AppVersionCode_IPAD,String Url_IPAD) {
		BAppupgrade ba_ANDROID = new BAppupgrade();
		ba_ANDROID.setId(versionID_ANDROID);
		ba_ANDROID.setAppVersionCode(AppVersionCode_ANDROID);
		ba_ANDROID.setAppVersionName(AppVersionCode_ANDROID + "版本");
		ba_ANDROID.setUrl(Url_ANDROID);
		bAppupgradeMapper.updateById(ba_ANDROID);
		BAppupgrade ba_IOS = new BAppupgrade();
		ba_IOS.setId(versionID_IOS);
		ba_IOS.setAppVersionCode(AppVersionCode_IOS);
		ba_IOS.setAppVersionName(AppVersionCode_IOS + "版本");
		ba_IOS.setUrl(Url_IOS);
		bAppupgradeMapper.updateById(ba_IOS);
		BAppupgrade ba_IPAD = new BAppupgrade();
		ba_IPAD.setId(versionID_IPAD);
		ba_IPAD.setAppVersionCode(AppVersionCode_IPAD);
		ba_IPAD.setAppVersionName(AppVersionCode_IPAD + "版本");
		ba_IPAD.setUrl(Url_IPAD);
		bAppupgradeMapper.updateById(ba_IPAD);
	}

}
