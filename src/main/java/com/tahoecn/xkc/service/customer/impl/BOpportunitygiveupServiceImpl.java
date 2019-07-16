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
		baseMapper.updSMessByOpportunity_Adviser(paramMap);
		//2
		baseMapper.updSMessByOpportunityGiveUp_Adviser(paramMap);
		//3
		baseMapper.insBCustomerFollowUp_Adviser(paramMap);
		//4
		baseMapper.updBOpportunity_Adviser(paramMap);
		//5
		baseMapper.insBCustomerTrack_Adviser(paramMap);
		//6
		baseMapper.updBCustomerPublicPool_Adviser(paramMap);
		//7
		baseMapper.insSMessage_Adviser(paramMap);
		//8
		baseMapper.insSTask(paramMap);
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
	/**
	 * 客户激活
	 * 1.删除丢失机会 2.新增销售机会 2.新增销售轨迹 3.删除公共池机会 4.分配待跟进消息提醒
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLActive_Update(Map<String, Object> paramMap) {
		//1
		baseMapper.updBOpportunity_Active(paramMap);
		//2
		baseMapper.insBOpportunity_Active(paramMap);
		//3
		baseMapper.callPOpportunityCustomerRank_Active(paramMap);
		//4
		baseMapper.insBCustomerTrack_Active(paramMap);
		//5
		baseMapper.insBOpportunityCustomerRel_Active(paramMap);
		//6
		baseMapper.insSMessage_Active(paramMap);
		//7
		baseMapper.insSTask(paramMap);
	}
	/**
	 * 客户回收
	 * 0.在途丢失申请拒回 0.删除机会消息 1.删除公共池机会 2.增加公共池机会 3.回收消息提醒 4.变更机会顾问
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void mCustomerYXJLRecovery_Update(Map<String, Object> paramMap) {
		//1
		baseMapper.updBOpportunityGiveUp_Recovery(paramMap);
		//2
		baseMapper.updMessByOpportunity_Recovery(paramMap);
		//3
		baseMapper.updMessByOpportunityGiveUp_Recovery(paramMap);
		//4
		baseMapper.updBCustomerPublicPool_Adviser(paramMap);
		//5
		baseMapper.insBCustomerTrack_Recovery(paramMap);
		//6
		baseMapper.insBCustomerPublicPool_Recovery(paramMap);
		//7
		baseMapper.insSMessage_Recovery(paramMap);
		//8
		baseMapper.updBOpportunity_Recovery(paramMap);
		//9
		baseMapper.insSTask(paramMap);
	}
}
