package com.tahoecn.xkc.mapper.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.BAppupgrade;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface BAppupgradeMapper extends BaseMapper<BAppupgrade> {

	/**
	 * 获取版本信息-默认第一条
	 */
	List<BAppupgrade> SystemAppVersion_Select(Map<String, Object> map);

}
