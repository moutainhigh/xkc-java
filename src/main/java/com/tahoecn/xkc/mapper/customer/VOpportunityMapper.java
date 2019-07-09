package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.VOpportunity;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface VOpportunityMapper extends BaseMapper<VOpportunity> {

	/**
	 * 机会信息
	 */
	List<Map<String,Object>> OpportunityDetail_Select(Map<String, Object> pram);

	/**
	 * 项目列表
	 */
	List<Map<String, Object>> sProject_Select(Map<String, Object> paramAry);

	/**
	 * 获取销售认知途径
	 */
	List<Map<String, Object>> SystemDictionaryXSRZTJList_Select(Map<String, Object> paramAry);

	/**
	 * 获取认知媒体及媒体子类
	 */
	List<Map<String, Object>> SystemDictionaryRZMTList_Select(String projectID);

	/**
	 * 获取顾问客户基本信息
	 */
	List<Map<String, Object>> sCustomerPotentialClue(Map<String, Object> paramAry);

}
