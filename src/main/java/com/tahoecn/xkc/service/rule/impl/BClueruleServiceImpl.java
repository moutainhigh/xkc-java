package com.tahoecn.xkc.service.rule.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.rule.BClueruleMapper;
import com.tahoecn.xkc.model.rule.BCluerule;
import com.tahoecn.xkc.service.rule.IBClueruleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class BClueruleServiceImpl extends ServiceImpl<BClueruleMapper, BCluerule> implements IBClueruleService {

	@Autowired
	BClueruleMapper bClueruleMapper;
	
	/**
	 * 删除渠道规则，根据ID
	 * @param clueRuleId 规则ID
	 */
	@Override
	public void delClueruleById(String clueRuleId) {
		bClueruleMapper.delClueruleById(clueRuleId);
	}

	/**
	 * 更新线索规则——群体
	 * @param map
	 */
	@Override
	public void updateAdviserGroup(Map<String, Object> map) {
		bClueruleMapper.updateAdviserGroup(map);
	}

	/**
	 * 设置规则启用禁用状态，根据ID
	 * @param map
	 */
	@Override
	public void updateClueRuleStatusById(Map<String, Object> map) {
		bClueruleMapper.updateClueRuleStatusById(map);
	}

	/**
	 * 设置规则群体启用禁用状态，根据ID
	 * @param map
	 */
	@Override
	public void updateAdviserGroupStatusById(Map<String, Object> map) {
		bClueruleMapper.updateAdviserGroupStatusById(map);
	}

	/**
	 * 编辑规则
	 * @param bCluerule
	 */
	@Override
	public void updateAdviserGroupById(Map<String, Object> map) {
		bClueruleMapper.updateAdviserGroupById(map);
	}

    @Override
    public Map<String, Object> getRegisterRule(String projectId, String adviserGroupID) {
        return baseMapper.getRegisterRule(projectId,adviserGroupID);
    }
	/**
	 * 获取规则信息
	 */
	@Override
	public BCluerule CaseFieRuleDetail_Select(Map<String, Object> ruleP) {
		QueryWrapper<BCluerule> wrapper = new QueryWrapper<BCluerule>();
		wrapper.eq("id", ruleP.get("ID"));
		wrapper.eq("status", 1);
		wrapper.eq("IsDel", 0);
		return bClueruleMapper.selectOne(wrapper);
	}
	/**
	 * 验证渠道人员是否在做重复报备
	 */
	@Override
	public boolean IsRepeatedReg(String mobile, String projectId, String channelUserId) {
		Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("Mobile", mobile);
        obj.put("IntentProjectID", projectId);
        obj.put("ReportUserID", channelUserId);
        List<Map<String,Object>> data = bClueruleMapper.IsRepeatedReg_Select(obj);
        return data.size() == 0 ? false : true;
	}
	/**
	 * 验证是否存在一条有效线索模式为报备保护
	 */
	@Override
	public boolean isExistReportProtectClue(String mobile, String projectId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("Mobile", mobile);
		obj.put("IntentProjectID", projectId);
		List<Map<String,Object>> data = bClueruleMapper.IsExistReportProtectClue_Select(obj);
		return data.size() == 0 ? false : true;
	}
	/**
	 * 判断是否存在销售机会（不区分有效无效）
	 */
	@Override
	public boolean IsExistOpportunity(String mobile, String projectId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("CustomerMobile", mobile);
		obj.put("ProjectID", projectId);
		List<Map<String,Object>> data = bClueruleMapper.IsExistOpportunity_Select(obj);
		return data.size() == 0 ? false : true;
	}

    /**
     *根据手机号、项目id获取销售机会
     * @param mobile
     * @param projectId
     * @return
     */
    @Override
    public List<Map<String, Object>> getOpp(String mobile, String projectId) {
        return baseMapper.getOpp(mobile,projectId);
    }
}
