package com.tahoecn.xkc.service.customer;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomerpublicpool;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-11
 */
public interface IBCustomerpublicpoolService extends IService<BCustomerpublicpool> {

	/**
	 * 客户公共池查询
	 */
	Map<String, Object> mCustomerGGCList_Select(Map<String, Object> paramMap);

	/**
	 * 今日可抢客户数
	 */
	Map<String, Object> mCustomerGGCGrabDetail_Select(Map<String,Object> data, Map<String, Object> paramMap);
	/**
	 * 客户分享传播池查询
	 */
	Map<String, Object> mCustomerSharePageList_Select(Map<String,Object> paramMap);

	/**
	 * 分享传播今日可抢客户数
	 */
	Map<String, Object> mCustomerFXCBGrabDetail_Select(Map<String, Object> data, Map<String,Object> paramMap);

	/**
	 * 获取公共池内客户的机会，线索以及状态，先将其置为无效
	 */
	Map<String,Object> mGetPublicPoolDetail_Select(Map<String, Object> obj);

	/**
	 * 公共池抢客
	 */
	String mCustomerGGCList_Insert(Map<String, Object> paramMap);
	/**
	 * 自渠抢客公共池客户
	 */
	void mGGCCustomerByZQDetail_Update(Map<String, Object> param);

}
