package com.tahoecn.xkc.service.customer;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.VCustomerxsjlsalesuserlistSelect;

/**
 * <p>
 * 1 服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
public interface IVCustomerxsjlsalesuserlistSelectService extends IService<VCustomerxsjlsalesuserlistSelect> {
	/**
	 * 案场销售经理盘客销售顾问查询
	 */
	IPage<Map<String, Object>> mCustomerXSJLSalesUserList_Select(IPage<Map<String, Object>> page, 
			String orgID, String projectID, String whereSb, String OrderSb, String siteUrl);

}
