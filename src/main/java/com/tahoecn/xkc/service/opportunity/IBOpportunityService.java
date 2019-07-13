package com.tahoecn.xkc.service.opportunity;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.opportunity.BOpportunity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface IBOpportunityService extends IService<BOpportunity> {

	/**
	 * 客户确认归属
	 */
	void mCustomerBelong_Update(Map<String,Object> paramMap);

	/**
	 * 客户回收
	 */
	void mCustomerRecovery_Update(Map<String,Object> paramMap);

	/**
	 * 客户分配置业顾问
	 */
	void mCustomerAllotAdviser_Update(Map<String,Object> paramMap);

	/**
	 * 自有渠道客户列表
	 */
	Map<String,Object> mCustomerZYQDList_Select(Map<String,Object> paramMap);
	/**
	 * 丢失客户列表
	 */
	Map<String,Object> mCustomerDSList_Select(Map<String,Object> paramMap);
	/**
	 * 正常跟进列表
	 */
	Map<String,Object> mCustomerGJList_Select(Map<String,Object> paramMap);
	/**
	 * 撞单客户列表
	 */
	Map<String,Object> mCustomerZDList_Select(Map<String,Object> paramMap);
	/**
	 * 公共客户列表
	 */
	Map<String,Object> mCustomerGGList_Select(Map<String,Object> paramMap);
	/**
	 * 逾期客户未交款列表
	 */
	Map<String,Object> mCustomerYQWJKList_Select(Map<String,Object> paramMap);
	/**
	 * 逾期客户未签约列表
	 */
	Map<String,Object> mCustomerYQWQYList_Select(Map<String,Object> paramMap);
	/**
	 * 逾期客户未认购列表
	 */
	Map<String,Object> mCustomerYQWRGList_Select(Map<String,Object> paramMap);
	/**
	 * 逾期客户未跟进列表
	 */
	Map<String,Object> mCustomerYQWGJList_Select(Map<String,Object> paramMap);

}
