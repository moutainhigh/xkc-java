package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BOpportunitygiveupMapper;
import com.tahoecn.xkc.model.customer.BOpportunitygiveup;
import com.tahoecn.xkc.service.customer.IBOpportunitygiveupService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-15
 */
@Service
public class BOpportunitygiveupServiceImpl extends ServiceImpl<BOpportunitygiveupMapper, BOpportunitygiveup> implements IBOpportunitygiveupService {
	
	/**
	 * 机会列表中是否存在丢失中的机会
	 */
	@Override
	public List<Map<String, Object>> GiveUpListStatusFind(Map<String, Object> paramMap) {
		return baseMapper.sOpportunityGiveUpListDetail_Select(paramMap);
	}
	/**
	 * 客户重新分配顾问
	 * 0.已办机会消息 1.变更机会顾问 2.新增销售轨迹 3.删除公共池机会 4.分配待跟进消息提醒
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLAdviser_Update(Map<String, Object> paramMap) {
		//1
		baseMapper.updMessByOpportunity(paramMap);
		//2
		baseMapper.updMessByOpportunityGiveUp(paramMap);
		//3
		baseMapper.insCustomerFollowUp(paramMap);
		//4
		baseMapper.upOpportunity(paramMap);
		//5
		baseMapper.insCustomerTrack(paramMap);
		//6
		baseMapper.upCustomerPublicPool(paramMap);
		//7
		baseMapper.insMessage(paramMap);
		//8
		baseMapper.insTask(paramMap);
	}
	/**
	 * 协作人置空
	 */
	@Override
	public void mCustomerYXJLSalePartnerSetNull_Update(Map<String, Object> paramMap) {
		baseMapper.mCustomerYXJLSalePartnerSetNull_Update(paramMap);
	}
	/**
	 * 获取SalesUser列表信息
	 */
	@Override
	public IPage<Map<String, Object>> mCustomerYXJLAdviserList_Select(IPage<Map<String, Object>> page, 
			String projectID, String where, String order, String siteUrl) {
		return baseMapper.mCustomerYXJLAdviserList_Select(page, projectID, where, order, siteUrl);
	}
	/**
	 * 获取CustomerPublicPool列表信息
	 */
	@Override
	public IPage<Map<String, Object>> mCustomerYXJLList_Select(IPage<Map<String, Object>> page, 
			String projectID, String where, String order) {
		return baseMapper.mCustomerYXJLList_Select(page, projectID, where, order);
	}
}
