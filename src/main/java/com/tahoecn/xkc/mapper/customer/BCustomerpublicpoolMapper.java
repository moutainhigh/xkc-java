package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BCustomerpublicpool;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-11
 */
public interface BCustomerpublicpoolMapper extends BaseMapper<BCustomerpublicpool> {

	/**
	 * 客户公共池查询
	 */
	List<Map<String,Object>> mCustomerGGCList_Select(Map<String, Object> paramMap);
	int mCustomerGGCList_Select_count(Map<String, Object> paramMap);
	int GGCGrab_Select1(Map<String, Object> paramMap);
	int GGCGrab_Select2(Map<String, Object> paramMap);
	/**
	 * 客户分享传播池查询
	 */
	List<Map<String,Object>> mCustomerSharePageList_Select(Map<String, Object> paramMap);
	int mCustomerSharePageList_Select_count(Map<String, Object> paramMap);
	int FXCBGrab_Select1(Map<String, Object> paramMap);
	int FXCBGrab_Select2(Map<String, Object> paramMap);
	/**
	 * 
	 * @param obj
	 * @return
	 */
	Map<String,Object> mGetPublicPoolDetail_Select(Map<String, Object> obj);
	/**
	 * 公共池抢客
	 */
	void mCustomerGGCList_Insert(Map<String, Object> paramMap);
	/**
	 * 获取自渠所在组织
	 */
	Map<String, Object> fromBSalesGroupMember(Map<String, Object> param);
	/**
	 * 创建线索
	 */
	void newBClue(Map<String, Object> param);
	//添加跟进记录
	void insBCustomerPotentialFollowUp(Map<String, Object> param);
	/**
	 * 分享传播池客户信息
	 */
	List<Map<String, Object>> mShareCustomerList_Select(Map<String, Object> parameter);

}
