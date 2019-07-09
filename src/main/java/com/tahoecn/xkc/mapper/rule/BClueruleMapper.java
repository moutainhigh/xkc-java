package com.tahoecn.xkc.mapper.rule;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.rule.BCluerule;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface BClueruleMapper extends BaseMapper<BCluerule> {

	/**
	 * 删除渠道规则，根据ID
	 * @param clueRuleId 规则ID
	 */
	void delClueruleById(String clueRuleId);

	/**
	 * 更新线索规则——群体
	 * @param map
	 */
	void updateAdviserGroup(Map<String, Object> map);
	
	/**
	 * 设置规则启用禁用状态，根据ID
	 * @param map
	 */
	void updateClueRuleStatusById(Map<String, Object> map);
	
	/**
	 * 设置规则群体启用禁用状态，根据ID
	 * @param map
	 */
	void updateAdviserGroupStatusById(Map<String, Object> map);

	/**
	 * 编辑规则
	 * @param bCluerule
	 */
	void updateAdviserGroupById(Map<String, Object> map);

    Map<String, Object> getRegisterRule(@Param("ProjectID") String projectId, @Param("AdviserGroupID") String adviserGroupID);
}
