package com.tahoecn.xkc.service.customer;

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

}
