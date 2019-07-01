package com.tahoecn.xkc.service.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.BSystemad;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface IBSystemadService extends IService<BSystemad> {

	/**
	 * 获取广告列表
	 * @param map
	 * @return
	 */
	List<BSystemad> SystemAD_Detail_Find(Map<String, Object> map);

}
