package com.tahoecn.xkc.service.sys;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.ASharelog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-18
 */
public interface IASharelogService extends IService<ASharelog> {

	/**
	 * 分享页面
	 */
	void mShareAppLog_Insert(Map<String, Object> paramMap);

}
