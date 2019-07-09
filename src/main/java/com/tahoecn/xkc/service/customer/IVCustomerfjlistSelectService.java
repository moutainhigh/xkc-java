package com.tahoecn.xkc.service.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;

/**
 * <p>
 * 2 服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface IVCustomerfjlistSelectService extends IService<VCustomerfjlistSelect> {

	/**
	 * 客户列表
	 * @param pa
	 * @return
	 */
	List<VCustomerfjlistSelect> CustomerList(Map<String, Object> pa);

}
