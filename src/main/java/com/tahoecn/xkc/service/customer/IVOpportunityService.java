package com.tahoecn.xkc.service.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.VOpportunity;
import com.tahoecn.xkc.model.vo.CSearchModel;
import com.tahoecn.xkc.model.vo.CustomerModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface IVOpportunityService extends IService<VOpportunity> {

	/**
	 * 机会信息
	 */
	List<Map<String,Object>> OpportunityInfo(String opportunityID);

	/**
	 * 初始化客户动态结构数据
	 * @param model 案场分接客户
	 * @param jsonFileName 模板数据结构
	 * @param map
	 * @param customerModeType 
	 * @return
	 */
	CustomerModel InitCustomerModeData(CSearchModel model, String jsonFileName, Map<String, Object> map,
			String customerModeType);

}
