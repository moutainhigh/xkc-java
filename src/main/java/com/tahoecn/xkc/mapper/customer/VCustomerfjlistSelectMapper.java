package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.VCustomerfjlistSelect;

/**
 * <p>
 * 2 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface VCustomerfjlistSelectMapper extends BaseMapper<VCustomerfjlistSelect> {

	/**
	 * 案场分接客户列表
	 */
	List<VCustomerfjlistSelect> sCustomerFJList_Select(@Param("pa")Map<String, Object> pa);
	
	IPage<VCustomerfjlistSelect> sCustomerFJList_Select(IPage<VCustomerfjlistSelect> page,@Param("pa")Map<String, Object> pa);
	
	List<Map<String,Object>> sCustomerFJAdviserList_Select(@Param("pmap")Map<String, Object> pmap);
	
	IPage<Map<String,Object>> sCustomerFJAdviserList_Select(IPage<Map<String,Object>> page,@Param("pmap")Map<String, Object> pmap);
	
	Long mSystemFormSessionStatus_Select_step1(Map<String, Object> pmap);

	void mSystemFormSessionStatus_Select_step2(Map<String, Object> pmap);
	
	/**
	 * 更新分接老机会老客户信息
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mCustomerFJDetail_Update_step1(Map<String, Object> pmap);
	void mCustomerFJDetail_Update_step2(Map<String, Object> pmap);
	void mCustomerFJDetail_Update_step3(Map<String, Object> pmap);
	void mCustomerFJDetail_Update_step4(Map<String, Object> pmap);
	void mCustomerFJDetail_Update_step5(Map<String, Object> pmap);
	
	/**
	 * 分接新机会新客户信息
	 * @param pmap
	 */
	void mCustomerFJDetail_Insert_step1(Map<String, Object> pmap);
	void mCustomerFJDetail_Insert_step2(Map<String, Object> pmap);
	void mCustomerFJDetail_Insert_step3(Map<String, Object> pmap);
	void mCustomerFJDetail_Insert_step4(Map<String, Object> pmap);
	void mCustomerFJDetail_Insert_step5(Map<String, Object> pmap);
	
	/**
	 * 新增分接新机会老客户信息
	 * @param pmap
	 */
	void mOldCustomerFJDetail_Insert_step1(Map<String, Object> pmap);
	void mOldCustomerFJDetail_Insert_step2(Map<String, Object> pmap);
	void mOldCustomerFJDetail_Insert_step3(Map<String, Object> pmap);
	void mOldCustomerFJDetail_Insert_step4(Map<String, Object> pmap);
	
	Map<String,Object> SystemAccountDetail_Select(@Param("userID")String userID);
	
	/**
	 * 用户授权
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mUserAuthorDetail_Select_get1(Map<String, Object> pmap);
	Map<String,Object> mUserAuthorDetail_Select_get2(Map<String, Object> pmap);
	Long mUserAuthorDetail_Select_getCount(Map<String, Object> pmap);
	Map<String,Object> mUserAuthorDetail_Select_get3(Map<String, Object> pmap);
	void mUserAuthorDetail_Select_Insert(Map<String, Object> pmap);
	Map<String,Object> mUserAuthorDetail_Select_get4(Map<String, Object> pmap);
	Map<String,Object> mUserAuthorDetail_Select_get5(Map<String, Object> pmap);
	Map<String,Object> mUserAuthorDetail_Select_get6(Map<String, Object> pmap);
	void mUserAuthorDetail_Select_update(Map<String, Object> pmap);
}
