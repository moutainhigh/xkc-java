package com.tahoecn.xkc.service.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.BAppupgrade;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface IBAppupgradeService extends IService<BAppupgrade> {

	/**
	 * 获取版本信息-默认第一条
	 */
	List<BAppupgrade> SystemAppVersion_Select(Map<String, Object> map);
	/**
	 * APP版本信息
	 */
	List<Map<String,Object>> SystemAppVersionList_Select();
	/**
	 * APP版本信息修改
	 */
	void SystemAppVersion_Update(String versionID,String AppVersionCode,String Url);

}
