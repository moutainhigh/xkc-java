package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;

/**
 * <p>
 * 2 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface VCustomerfjlistSelectMapper extends BaseMapper<VCustomerfjlistSelect> {

	/**
	 * 案场分接客户列表
	 */
	List<VCustomerfjlistSelect> sCustomerFJList_Select(Map<String, Object> pa);
	
	List<Map<String,Object>> sCustomerFJAdviserList_Select(Map<String, Object> pmap);

}
