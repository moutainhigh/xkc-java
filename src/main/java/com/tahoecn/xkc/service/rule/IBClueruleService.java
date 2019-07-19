package com.tahoecn.xkc.service.rule;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.rule.BCluerule;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface IBClueruleService extends IService<BCluerule> {

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

    Map<String, Object> getRegisterRule(String projectId, String adviserGroupID);
	/**
	 * 获取规则信息
	 */
	BCluerule CaseFieRuleDetail_Select(Map<String, Object> ruleP);
	/**
	 * 验证渠道人员是否在做重复报备
	 */
	boolean IsRepeatedReg(String mobile, String projectId, String channelUserId);
	/**
	 * 验证是否存在一条有效线索模式为报备保护
	 */
	boolean isExistReportProtectClue(String mobile, String projectId);
	/**
	 * 判断是否存在销售机会（不区分有效无效）
	 */
	boolean IsExistOpportunity(String phone, String projectId);
}
