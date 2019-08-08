package com.tahoecn.xkc.service.project.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.service.project.IAAchievementBoardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  业绩看板
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@Service
public class AAchievementBoardServiceImpl extends ServiceImpl<BProjectMapper, BProject> implements IAAchievementBoardService {
	/**
	 * 首页销售业绩认购数据
	 */
	@Override
	public Map<String, Object> mYJKBSalesPerformanceData_Select(JSONObject param) {
		param.put("SaleUserID", param.get("UserID"));
		return baseMapper.mYJKBSalesPerformanceData_Select(param);
	}
	/**
	 * 首页销售业绩逾期未签约数据
	 */
	@Override
	public Map<String, Object> mSalesPerformanceOverdueContractData_Select(JSONObject param) {
		return baseMapper.mSalesPerformanceOverdueContractData_Select(param);
	}
	/**
	 * 首页销售业绩逾期款数据
	 */
	@Override
	public Map<String, Object> mSalesPerformanceOverduePaymentData_Select(JSONObject param) {
		return baseMapper.mSalesPerformanceOverduePaymentData_Select(param);
	}
	/**
	 * 客储达成首页数据
	 */
	@Override
	public List<Map<String,Object>> mYJKBCustomerRankData_Select(JSONObject param) {
		return baseMapper.mYJKBCustomerRankData_Select(param);
	}
	/**
	 * 业绩排名列表
	 */
	@Override
	public Map<String, Object> mYJKBSaleUserRankList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBSaleUserRankList_Select(param));
		map.put("AllCount", baseMapper.mYJKBSaleUserRankList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 客储达成详情列表
	 */
	@Override
	public Map<String, Object> mYJKBCustomerRankDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBCustomerRankDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBCustomerRankDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 认购详情列表
	 */
	@Override
	public Map<String, Object> mYJKBOrderDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("List", baseMapper.mYJKBOrderDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBOrderDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 签约详情列表
	 */
	@Override
	public Map<String, Object> mYJKBContractDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBContractDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBContractDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 逾期未签约详情列表
	 */
	@Override
	public Map<String, Object> mYJKBOverdueContractDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBOverdueContractDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBOverdueContractDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 认筹详情列表
	 */
	@Override
	public Map<String, Object> mYJKBBookingDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBBookingDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBBookingDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 回款详情列表
	 */
	@Override
	public Map<String, Object> mYJKBPaybackDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBPaybackDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBPaybackDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}
	/**
	 * 逾期款详情列表
	 */
	@Override
	public Map<String, Object> mYJKBOverduePayDetailList_Select(JSONObject param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("List", baseMapper.mYJKBOverduePayDetailList_Select(param));
		map.put("AllCount", baseMapper.mYJKBOverduePayDetailList_Select_count(param));
		map.put("PageSize", param.get("PageSize"));
		return map;
	}

}
