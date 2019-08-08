package com.tahoecn.xkc.service.project;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.BProject;

/**
 * <p>
 *  业绩看板
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface IAAchievementBoardService extends IService<BProject> {

	/**
	 * 首页销售业绩认购数据
	 */
	Map<String, Object> mYJKBSalesPerformanceData_Select(JSONObject param);

	/**
	 * 首页销售业绩逾期未签约数据
	 */
	Map<String, Object> mSalesPerformanceOverdueContractData_Select(JSONObject param);
	/**
	 * 首页销售业绩逾期款数据
	 */
	Map<String, Object> mSalesPerformanceOverduePaymentData_Select(JSONObject param);

	/**
	 * 客储达成首页数据
	 */
	List<Map<String,Object>> mYJKBCustomerRankData_Select(JSONObject param);
	/**
	 * 业绩排名列表
	 */
	Map<String, Object> mYJKBSaleUserRankList_Select(JSONObject param);
	/**
	 * 客储达成详情列表
	 */
	Map<String, Object> mYJKBCustomerRankDetailList_Select(JSONObject param);
	/**
	 * 认购详情列表
	 */
	Map<String, Object> mYJKBOrderDetailList_Select(JSONObject param);
	/**
	 * 签约详情列表
	 */
	Map<String, Object> mYJKBContractDetailList_Select(JSONObject param);
	/**
	 * 逾期未签约详情列表
	 */
	Map<String, Object> mYJKBOverdueContractDetailList_Select(JSONObject param);
	/**
	 * 认筹详情列表
	 */
	Map<String, Object> mYJKBBookingDetailList_Select(JSONObject param);
	/**
	 * 回款详情列表
	 */
	Map<String, Object> mYJKBPaybackDetailList_Select(JSONObject param);
	/**
	 * 逾期款详情列表
	 */
	Map<String, Object> mYJKBOverduePayDetailList_Select(JSONObject param);


}
