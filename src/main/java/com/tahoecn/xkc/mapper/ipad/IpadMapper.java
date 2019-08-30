package com.tahoecn.xkc.mapper.ipad;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

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
	List<Map<String,Object>> mLFCustomerDetailByMobile_Select(Map<String,Object> pmap);
	
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
	
	/**
	 * 待分配列表查询
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mLFCustomerNeedFPList_Select(@Param("pmap")Map<String,Object> pmap);
	Long mLFCustomerNeedFPList_Select_count(@Param("pmap")Map<String,Object> pmap);
	
	/**
	 * 接待记录列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mLFReceptRecordList_Select(@Param("pmap")Map<String,Object> pmap);
	IPage<Map<String,Object>> mLFReceptRecordList_Select(IPage<Map<String,Object>> page,@Param("pmap")Map<String,Object> pmap);
	
	
	/**
	 * 根据顾问筛选的接待记录客户列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mLFReceptRecordCustomerList_Select(@Param("pmap")Map<String,Object> pmap);
	IPage<Map<String,Object>> mLFReceptRecordCustomerList_Select(IPage<Map<String,Object>> page,@Param("pmap")Map<String,Object> pmap);
	
	/**
	 * 获取有效置业顾问列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mLFReceptRecordList_Select_forSaleUser(@Param("pmap")Map<String,Object> pmap);
	
	/**
	 * 添加签到
	 * @param pmap
	 */
	void addSaleUserSign(Map<String,Object> pmap);
	
	/**
	 * 获取当天最大的排序编码
	 * @param SignInDate
	 * @return
	 */
	Long getMaxSortByDate(@Param("SignInDate")String SignInDate);
	
	/**
	 * 更新排序编码和排序时间
	 * @param pmap
	 */
	void updateSortCodeAndTime(Map<String,Object> pmap);
	
	/**
	 * 更新排序编码
	 * @param pmap
	 */
	void updateSortCode(Map<String,Object> pmap);
	
	/**
	 * 获取当天签到的客户信息
	 * @param pmap
	 * @return
	 */
	Map<String,Object> selectByDateAndSaleUserID(Map<String,Object> pmap);
	
	/**
	 * 获取置业顾问当天的状态信息
	 * @param pmap
	 * @return
	 */
	Map<String,Object> selectSaleUserStatus(Map<String,Object> pmap);
	
	/**
	 * 添加置业顾问当天的状态信息
	 * @param pmap
	 */
	void insertSaleUserStatus(Map<String,Object> pmap);
	
	/**
	 * 修改置业顾问当天的状态信息
	 * @param pmap
	 */
	void updateSaleUserStatus(Map<String,Object> pmap);
	
	Map<String,Object> mLFReceptRecordList_Select_forSaleUser_ByID(Map<String,Object> pmap);
	
}
