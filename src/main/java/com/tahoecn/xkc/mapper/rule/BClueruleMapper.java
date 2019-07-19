package com.tahoecn.xkc.mapper.rule;

import java.util.List;
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
    /**
	 * 验证渠道人员是否在做重复报备
	 */
	List<Map<String,Object>> IsRepeatedReg_Select(Map<String, Object> obj);
	/**
	 * 验证是否存在一条有效线索模式为报备保护
	 */
	List<Map<String, Object>> IsExistReportProtectClue_Select(Map<String, Object> obj);
	/**
	 * 验证是否存在销售机会（不区分机会状态）
	 */
	List<Map<String, Object>> IsExistOpportunity_Select(Map<String, Object> obj);
}
