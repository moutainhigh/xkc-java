package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.VCustomerxsjlsalesuserlistSelectMapper;
import com.tahoecn.xkc.model.customer.VCustomerxsjlsalesuserlistSelect;
import com.tahoecn.xkc.service.customer.IVCustomerxsjlsalesuserlistSelectService;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 1 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
@Service
public class VCustomerxsjlsalesuserlistSelectServiceImpl extends ServiceImpl<VCustomerxsjlsalesuserlistSelectMapper, VCustomerxsjlsalesuserlistSelect> implements IVCustomerxsjlsalesuserlistSelectService {
	/**
	 * 案场销售经理盘客销售顾问查询
	 */
	@Override
	public IPage<Map<String, Object>> mCustomerXSJLSalesUserList_Select(IPage<Map<String, Object>> page, 
			String orgID, String projectID, String whereSb, String OrderSb, String siteUrl) {
		return baseMapper.mCustomerXSJLSalesUserList_Select(page, orgID, projectID, whereSb, OrderSb, siteUrl);
	}
	/**
	 * 案场销售经理盘客销售顾问客户查询
	 */
	@Override
	public IPage<Map<String, Object>> mCustomerXSJLSalesUserCustomerList_Select(IPage<Map<String, Object>> page, 
			String SaleUserID, String projectID, String whereSb, String OrderSb) {
		return baseMapper.mCustomerXSJLSalesUserCustomerList_Select(page, SaleUserID, projectID, whereSb, OrderSb);
	}

}
