package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.common.ReturnEntity;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;

/**
 * 
 *客户
 */
public interface IVCustomergwlistSelectService extends IService<VCustomergwlistSelect> {
	/**
	 * 置业顾问客户列表
	 * @return
	 */
	 public ReturnEntity customerList(GWCustomerPageDto gWCustomerPageDto);
}
