package com.tahoecn.xkc.service.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomerpotentialfiltergroup;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-10
 */
public interface IBCustomerpotentialfiltergroupService extends IService<BCustomerpotentialfiltergroup> {

	/**
	 * 潜在客户筛选分组删除
	 */
	void mCustomerPotentialFilterGroupDetail_Delete(String ID);

	/**
	 * 潜在客户分组新增
	 */
	void mCustomerPotentialFilterGroupDetail_Insert(Map<String,Object> paramMap);
	/**
	 * 潜在客户筛选分组列表
	 */
	List<Map<String,Object>> mCustomerPotentialFilterGroupList_Select(Map<String,Object> paramMap);

}
