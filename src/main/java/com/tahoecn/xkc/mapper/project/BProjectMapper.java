package com.tahoecn.xkc.mapper.project;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.project.BProject;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface BProjectMapper extends BaseMapper<BProject> {

    List<Map<String,Object>> findByOrgID(IPage page,@Param("OrgID") String orgID);

    List<Map<String,Object>> ProjectInfoList_SelectN(IPage page,@Param("Name")String Name, @Param("CityID")String cityID);

    String ProjectIsNoAllot_Select(@Param("ProjectID") String projectID);
    /**
	 * 业绩看板-首页销售业绩认购数据
	 */
	Map<String, Object> mYJKBSalesPerformanceData_Select(JSONObject param);
	/**
	 * 业绩看板-首页销售业绩逾期未签约数据
	 */
	Map<String, Object> mSalesPerformanceOverdueContractData_Select(JSONObject param);
	/**
	 * 业绩看板-首页销售业绩逾期款数据
	 */
	Map<String, Object> mSalesPerformanceOverduePaymentData_Select(JSONObject param);
	/**
	 * 业绩看板-客储达成首页数据
	 */
	List<Map<String,Object>> mYJKBCustomerRankData_Select(JSONObject param);
	/**
	 * 业绩看板-业绩排名列表
	 */
	IPage<Map<String, Object>> mYJKBSaleUserRankList_Select(IPage page, @Param("ProjectID")String ProjectID, 
			@Param("StartDate")String StartDate, @Param("EndDate")String EndDate, 
			@Param("ORDERBY")String ORDERBY, @Param("WHERE")String WHERE);
	/**
	 * 客储达成详情列表
	 */
	IPage<Map<String, Object>> mYJKBCustomerRankDetailList_Select(IPage page, @Param("ProjectID")String ProjectID, 
			@Param("CustomerRank")String CustomerRank, @Param("UserID")String UserID, 
			@Param("StartDate")String StartDate, @Param("EndDate")String EndDate,
			@Param("ORDERBY")String ORDERBY);
	/**
	 * 认购详情列表
	 */
	IPage<Map<String, Object>> mYJKBOrderDetailList_Select(IPage page, @Param("ProjectID")String projectID,
			@Param("UserID")String userID, @Param("StartDate")String StartDate, @Param("EndDate")String EndDate);
	/**
	 * 签约详情列表
	 */
	IPage<Map<String, Object>> mYJKBContractDetailList_Select(IPage page, @Param("ProjectID")String projectID,
			@Param("UserID")String userID, @Param("StartDate")String StartDate, @Param("EndDate")String EndDate);
	/**
	 * 逾期未签约详情列表
	 */
	IPage<Map<String, Object>> mYJKBOverdueContractDetailList_Select(IPage page, @Param("ProjectID")String projectID,
			@Param("UserID")String userID, @Param("StartDate")String StartDate, @Param("EndDate")String EndDate);
	/**
	 * 认筹详情列表
	 */
	IPage<Map<String, Object>> mYJKBBookingDetailList_Select(IPage page, @Param("ProjectID")String projectID,
			@Param("UserID")String userID, @Param("StartDate")String StartDate, @Param("EndDate")String EndDate);
	/**
	 * 回款详情列表
	 */
	IPage<Map<String, Object>> mYJKBPaybackDetailList_Select(IPage page, @Param("ProjectID")String projectID,
			@Param("UserID")String userID, @Param("StartDate")String StartDate, @Param("EndDate")String EndDate);
	/**
	 * 逾期款详情列表
	 */
	IPage<Map<String, Object>> mYJKBOverduePayDetailList_Select(IPage page, @Param("ProjectID")String projectID,
			@Param("UserID")String userID, @Param("StartDate")String StartDate, @Param("EndDate")String EndDate);
}
