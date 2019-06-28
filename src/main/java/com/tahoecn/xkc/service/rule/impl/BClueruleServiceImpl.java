package com.tahoecn.xkc.service.rule.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.rule.BClueruleMapper;
import com.tahoecn.xkc.model.rule.BCluerule;
import com.tahoecn.xkc.service.rule.IBClueruleService;

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

}
