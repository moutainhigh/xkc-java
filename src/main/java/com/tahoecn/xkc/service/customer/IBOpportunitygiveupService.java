package com.tahoecn.xkc.service.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BOpportunitygiveup;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-15
 */
public interface IBOpportunitygiveupService extends IService<BOpportunitygiveup> {

	/**
	 * 机会列表中是否存在丢失中的机会
	 */
	List<Map<String, Object>> GiveUpListStatusFind(Map<String, Object> paramMap);
	/**
	 * 客户重新分配顾问
	 */
	void mCustomerYXJLAdviser_Update(Map<String, Object> paramMap);
	/**
	 * 协作人置空
	 */
	void mCustomerYXJLSalePartnerSetNull_Update(Map<String, Object> paramMap);

}
