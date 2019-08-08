package com.tahoecn.xkc.mapper.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.BSystemad;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface BSystemadMapper extends BaseMapper<BSystemad> {

	/**
	 * 获取广告列表
	 * @param map
	 * @return
	 */
	List<BSystemad> SystemAD_Detail_Find(Map<String, Object> map);

	void SystemAD_Insert(Map<String, Object> map);
}
