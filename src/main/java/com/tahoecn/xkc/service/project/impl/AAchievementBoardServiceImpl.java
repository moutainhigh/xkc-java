package com.tahoecn.xkc.service.project.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.service.project.IAAchievementBoardService;

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
	public IPage<Map<String, Object>> mYJKBSaleUserRankList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		String ORDERBY = param.getString("ORDERBY");
		String WHERE = param.getString("WHERE");
		return baseMapper.mYJKBSaleUserRankList_Select(page, ProjectID, StartDate, EndDate, ORDERBY, WHERE);
	}
	/**
	 * 客储达成详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBCustomerRankDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String CustomerRank = param.getString("CustomerRank");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		String ORDERBY = param.getString("ORDERBY");
		return baseMapper.mYJKBCustomerRankDetailList_Select(page, ProjectID, CustomerRank, UserID,
				StartDate, EndDate, ORDERBY);
	}
	/**
	 * 认购详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBOrderDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		return baseMapper.mYJKBOrderDetailList_Select(page, ProjectID, UserID,
				StartDate, EndDate);
	}
	/**
	 * 签约详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBContractDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		return baseMapper.mYJKBContractDetailList_Select(page, ProjectID, UserID,
				StartDate, EndDate);
	}
	/**
	 * 逾期未签约详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBOverdueContractDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		return baseMapper.mYJKBOverdueContractDetailList_Select(page, ProjectID, UserID,
				StartDate, EndDate);
	}
	/**
	 * 认筹详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBBookingDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		return baseMapper.mYJKBBookingDetailList_Select(page, ProjectID, UserID,
				StartDate, EndDate);
	}
	/**
	 * 回款详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBPaybackDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		return baseMapper.mYJKBPaybackDetailList_Select(page, ProjectID, UserID,
				StartDate, EndDate);
	}
	/**
	 * 逾期款详情列表
	 */
	@Override
	public IPage<Map<String, Object>> mYJKBOverduePayDetailList_Select(IPage page, JSONObject param) {
		String ProjectID = param.getString("ProjectID");
		String UserID = param.getString("UserID");
		String StartDate = param.getString("StartDate");
		String EndDate = param.getString("EndDate");
		return baseMapper.mYJKBOverduePayDetailList_Select(page, ProjectID, UserID,
				StartDate, EndDate);
	}

}
