package com.tahoecn.xkc.mapper.customer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
	
	/**
	 * 获取渠道任务列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mChannelTaskListZQ_Select(Map<String,Object> pmap);
	
	/**
	 * 根据线索获取渠道任务
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mChannelTaskGetByClueID_Select(Map<String,Object> pmap);
	
	/**
	 * 获取自渠认知途径
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> SystemDictionaryZQRZTJList_Select(Map<String,Object> pmap);
	
	/**
	 * 获取潜在客户
	 * @param pmap
	 * @return
	 */
	Map<String,Object> CustomerPotentialClueDetail_Select(Map<String,Object> pmap);
	
	/**
	 * 潜在客户跟进记录列表
	 * @param pmap
	 * @return
	 */
	IPage<Map<String,Object>> mCustomerPotentialFollowUpList_Select(IPage<Map<String,Object>> page,@Param("pmap")Map<String,Object> pmap);
	List<Map<String,Object>> mCustomerPotentialFollowUpList_Select(@Param("pmap")Map<String,Object> pmap);
	Long mCustomerPotentialFollowUpList_Select_Count(@Param("pmap")Map<String,Object> pmap);
	
	/**
	 * 自渠潜在客户资料完善
	 * @param pmap
	 */
	void mCustomerPotentialZQDetail_Update_step1(Map<String,Object> pmap);
	List<Map<String,Object>> mCustomerPotentialZQDetail_Update_valid_1(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Update_step2(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Update_step3(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Update_step4(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Update_step5(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Update_step6(Map<String,Object> pmap);
	
	void P_ClueCustomerRank(Map<String,Object> pmap);
	
	/**
	 * 潜在客户线索跟进信息更新
	 * @param pmap
	 */
	void CustomerPotentialClueFollowUpDetail_Update(Map<String,Object> pmap);
	
	/**
	 * 获取渠道任务详细
	 * @param pmap
	 * @return
	 */
	Map<String,Object> mChannelTaskDetail_Select(Map<String,Object> pmap);
	
	/**
	 * 根据线索id获取有效线索
	 * @param pmap
	 * @return
	 */
	Map<String,Object> UnconfirmedClueByClueId_Select(Map<String,Object> pmap);
	
	/**
	 * 验证是否存超过到访保护期
	 * @param pmap
	 * @return
	 */
	Map<String,Object> IsOverdueCome_Select(Map<String,Object> pmap);
	
	/**
	 * 验证是否存在销售机会（不区分机会状态）
	 * @param pmap
	 * @return
	 */
	Map<String,Object> IsExistOpportunity_Select(Map<String,Object> pmap);
	
	/**
	 * 根据手机号、项目id获取有效线索
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> RuleClueList_Select(Map<String,Object> pmap);
	
	/**
	 * 验证是否是老业主
	 * @param pmap
	 * @return
	 */
	Map<String,Object> IsOwner_Select(Map<String,Object> pmap);
	
	/**
	 * 验证是否是本项目老业主
	 * @param pmap
	 * @return
	 */
	Map<String,Object> IsProjectOwner_Select(Map<String,Object> pmap);
	
	/**
	 * 根据手机号、项目id获取销售机会
	 * @param pmap
	 * @return
	 */
	Map<String,Object> ValidOpp_Select(Map<String,Object> pmap);
	
	/**
	 * 验证是否重复报备
	 * @param pmap
	 * @return
	 */
	Map<String,Object> IsRepeatedReg_Select(Map<String,Object> pmap);
	
	/**
	 * 是否存在一条有效的报备保护线索
	 * @param pmap
	 * @return
	 */
	Map<String,Object> IsExistReportProtectClue_Select(Map<String,Object> pmap);
	
	/**
	 * 自渠潜在客户登记
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mCustomerPotentialZQDetail_Insert_valid_1(Map<String,Object> pmap);
	List<Map<String,Object>> mCustomerPotentialZQDetail_Insert_valid_2(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step1(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step2(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step3(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step4(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step5(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step6(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step7(Map<String,Object> pmap);
	void mCustomerPotentialZQDetail_Insert_step8(Map<String,Object> pmap);
	Map<String,Object> mCustomerPotentialZQDetail_Insert_step9(Map<String,Object> pmap);
	
	/**
	 * 潜在客户手机号搜索信息新增
	 * @param pmap
	 * @return
	 */
	Map<String,Object> CustomerPotentialMobileSearchDetail_Insert_select(Map<String,Object> pmap);
	void CustomerPotentialMobileSearchDetail_Insert(Map<String,Object> pmap);
	
	/**
	 * 获取潜在客户
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> CustomerPotentialDetail_Select(Map<String,Object> pmap);
	
	/**
	 * 自渠潜在客户查询列表
	 * @param pmap
	 * @return
	 */
	List<Map<String,Object>> mCustomerPotentialZQList_Select(Map<String,Object> pmap);
	Long mCustomerPotentialZQList_Select_count(Map<String,Object> pmap);
	
	
}
