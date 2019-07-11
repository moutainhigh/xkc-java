package com.tahoecn.xkc.mapper.opportunity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.opportunity.BOpportunity;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BOpportunityMapper extends BaseMapper<BOpportunity> {

    int getCustomerCount(String projectId, String memberId);

    /**
     * 客户确认归属
     */
	void mCustomerBelong_Update(Map<String,Object> paramMap);
	/*List<Map<String,Object>> getYQWGJ(Map<String,Object> paramMap);
	List<Map<String,Object>> getYQWRG(Map<String,Object> paramMap);
	List<Map<String,Object>> getYQWQY(Map<String,Object> paramMap);
	List<Map<String,Object>> getYQWJK(Map<String,Object> paramMap);
	void updateCustomerRecovery1(Map<String,Object> paramMap);
	void updateCustomerRecovery2(Map<String,Object> paramMap);
	void updateCustomerRecovery3(Map<String,Object> paramMap);
	void updateCustomerRecovery4(Map<String,Object> paramMap);
	void updateCustomerRecovery5(Map<String,Object> paramMap);*/
	/**
	 * 回归
	 */
	void mCustomerRecovery_Update(Map<String,Object> paramMap);
	/**
	 * 客户分配置业顾问
	 */
	void mCustomerAllotAdviser_Update(Map<String, Object> paramMap);
}
