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

}
