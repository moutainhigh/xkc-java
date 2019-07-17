package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BCustomerpotential;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface BCustomerpotentialMapper extends BaseMapper<BCustomerpotential> {
	
	/**
	 * 潜在客户标签新增
	 * @param pmap
	 */
	void mCustomerPotentialTagDetail_Insert_delete(Map<String,Object> pmap);
	void mCustomerPotentialTagDetail_Insert(Map<String,Object> pmap);
	
	/**
	 * 用户关注潜在客户列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mUserFollowPotentialList_Select(Map<String,Object> pmap);
	Long mUserFollowPotentialList_Select_count(Map<String,Object> pmap);
	
	/**
	 * 用户关注潜在客户新增
	 * @param pmap
	 */
	void mUserFollowPotentialDetail_Insert_delete(Map<String,Object> pmap);
	void mUserFollowPotentialDetail_Insert(Map<String,Object> pmap);
	
	/**
	 * 用户关注潜在客户删除
	 * @param pmap
	 */
	void mUserFollowPotentialDetail_Delete(Map<String,Object> pmap);
	
	/**
	 * 复制潜在客户
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mCustomerPotentialCopyList_Insert(Map<String,Object> pmap);
	
	/**
	 * 自渠潜在客户基本信息
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mCustomerPotentialZQBaseDetail_Select(Map<String,Object> pmap);
	
	/**
	 * 自渠潜在客户销售轨迹信息
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mCustomerPotentialZQTractDetail_Select(Map<String,Object> pmap);
}
