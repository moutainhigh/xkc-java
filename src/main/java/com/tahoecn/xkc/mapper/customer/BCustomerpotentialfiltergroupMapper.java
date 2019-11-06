package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BCustomerpotentialfiltergroup;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-10
 */
public interface BCustomerpotentialfiltergroupMapper extends BaseMapper<BCustomerpotentialfiltergroup> {

	/**
	 * 潜在客户筛选分组列表
	 */
	List<Map<String, Object>> mCustomerPotentialFilterGroupList_Select(Map<String, Object> paramMap);

}
