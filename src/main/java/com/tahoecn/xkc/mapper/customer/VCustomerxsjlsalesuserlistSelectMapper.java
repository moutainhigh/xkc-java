package com.tahoecn.xkc.mapper.customer;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.VCustomerxsjlsalesuserlistSelect;


/**
 * <p>
 * 1 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
public interface VCustomerxsjlsalesuserlistSelectMapper extends BaseMapper<VCustomerxsjlsalesuserlistSelect> {

	/**
	 * 案场销售经理盘客销售顾问查询
	 */
	IPage<Map<String, Object>> mCustomerXSJLSalesUserList_Select(IPage<Map<String, Object>> page, 
			@Param("OrgID")String orgID, @Param("ProjectID")String projectID,
			@Param("WHERE")String whereSb, @Param("ORDER")String orderSb, @Param("SiteUrl")String siteUrl);

}
