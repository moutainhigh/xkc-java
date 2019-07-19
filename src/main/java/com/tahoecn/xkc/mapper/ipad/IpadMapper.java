package com.tahoecn.xkc.mapper.ipad;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface IpadMapper extends BaseMapper<Object> {
	
	/**
	 * 获取项目列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mLFProjectList_Select(Map<String,Object> pmap);
	
	/**
	 * 用户项目切换
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mUserProjectChange_Update_valid(Map<String,Object> pmap);
	void mUserProjectChange_Update_insert(Map<String,Object> pmap);
	void mUserProjectChange_Update_update(Map<String,Object> pmap);
	
	/**
	 * 获取项目信息
	 * @param pmap
	 * @return
	 */
	Map<String,Object> Project_Detail_FindById(Map<String,Object> pmap);
	
	/**
	 * 查询客户详情
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mLFCustomerDetailByMobile_Select(Map<String,Object> pmap);
	
	/**
	 * 获取顾问客户基本信息
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> sCustomerPotentialClue(Map<String,Object> pmap);
	
	/**
	 * 查询客户详情
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mLFCustomerPotentialDetailByMobile_Select(Map<String,Object> pmap);
	
	/**
	 * 查询公共客户池客户
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mCustomerPublicPoolByMobile_Select(Map<String,Object> pmap);
	
	/**
	 * 获取客户线索状态
	 * @param clueID
	 * @return
	 */
	Map<String,Object> mCustomerClueStatus_Select(@Param("clueID")String clueID);
}
