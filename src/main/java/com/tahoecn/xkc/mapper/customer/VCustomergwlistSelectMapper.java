package com.tahoecn.xkc.mapper.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.customer.VCustomergwlistSelect;
import com.tahoecn.xkc.model.dto.GWCustomerPageDto;
import com.tahoecn.xkc.model.vo.CustomerActionVo;

/**
 * <p>
 * 1 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface VCustomergwlistSelectMapper extends BaseMapper<VCustomergwlistSelect> {
	/**
	 * 置业顾问客户列表
	 * @param gWCustomerPageDto
	 * @return
	 */
	List<Map<String,Object>> sCustomerGWListNew_Select(GWCustomerPageDto gWCustomerPageDto);
	
	Long sCustomerGWListNew_Select_count(GWCustomerPageDto gWCustomerPageDto);
	
	/**
	 * 置业顾问客户基本信息1
	 * @param opportunityID
	 * @param projectID
	 * @return
	 */
	HashMap<String,Object> sCustomerGWBase_Select(@Param("opportunityID")String opportunityID,@Param("projectID")String projectID);
	
	/**
	 * 置业顾问客户基本信息2
	 * @param opportunityID
	 * @param projectID
	 * @return
	 */
	List<HashMap<String,Object>> sCustomerGWBaseSalerInfo_Select(@Param("opportunityID")String opportunityID,@Param("projectID")String projectID,@Param("siteUrl")String siteUrl);
	
	/**
	 * 置业顾问客跟进记录
	 * @param page
	 * @param opportunityID
	 * @param projectID
	 * @return
	 */
	IPage<Map<String,Object>> mCustomerFollowUpList_Select(IPage<Map<String,Object>> page,@Param("opportunityID")String opportunityID,@Param("projectID")String projectID);
	
	/**
	 * 置业顾问销售轨迹
	 * @param page
	 * @param opportunityID
	 * @return
	 */
	IPage<Map<String,Object>> sCustomerTrackList_Select(IPage<Map<String,Object>> page,@Param("opportunityID")String opportunityID);
	
	/**
	 * 置业顾问 新增跟进记录 开启异地销售，置业顾问申请客户丢失后，协作人不能对客户进行操作，查询符合条件的记录
	 * @param opportunityID
	 * @param userID
	 * @return
	 */
	List<Map<String,Object>> mCustomerSalePartnerIsCanOperate_Select(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 置业顾问 新增跟进记录 客户跟进记录新增
	 * @param opportunityID
	 * @param customerID
	 * @param userID
	 * @param mode
	 */
	void mCustomerFollowUpDetail_Insert(@Param("opportunityID")String opportunityID,@Param("customerID")String customerID,@Param("userID")String userID,@Param("mode")String mode);
	
	Map<String,Object> CFGetClueRule_Select(@Param("clueID")String clueID);
	
	Map<String,Object> CFGetOppRule_Select(@Param("opportunityID")String opportunityID);
	
	Map<String,Object> CFIsOwnerChannel_Select(@Param("channelTypeID")String channelTypeID);
	
	/**
	 * 置业顾问 创建跟进跟访记录1
	 * @param customerActionVo
	 */
	void CFCreateClueFollowUp_Insert(CustomerActionVo customerActionVo);
	
	/**
	 * 置业顾问 创建跟进跟访记录2
	 * @param customerActionVo
	 */
	void CFCreateOppFollowUp_Insert(CustomerActionVo customerActionVo);
	
	/**
	 * 置业顾问  渠道到访消息设置查询
	 * @param opportunityID
	 * @param clueID
	 * @param projectID
	 * @return
	 */
	Map<String,Object> RemindRuleArriveDetail_Select(@Param("opportunityID")String opportunityID,@Param("clueID")String clueID);
	
	Map<String,Object> RemindRuleArriveDetail_Select_f(@Param("projectID")String projectID,@Param("protectSource")String protectSource);
	
	Map<String,Object> RemindRuleArriveDetail_Select_s(@Param("clueID")String clueID);
	
	/**
	 * 置业顾问 客户机会跟进信息更新
	 * @param opportunityID
	 */
	void CustomerOpportunityFollowUpDetail_Update(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 置业顾问 客户机会跟进信息更新3
	 * @param opportunityID
	 * 项目渠道设置客户为永久保护的话，则客户报备、到访都不会有成交保护期，适用身份：中介同行、老业主、自由经纪、员工推荐    
	 * 自渠客户没有成交保护期  
	 */
	void mClueTradeOverdueTime_Update(@Param("opportunityID")String opportunityID);
	
	/**
	 * 新增关注客户step1
	 * @param opportunityID
	 * @param userID
	 */
	void sUserFollowDetail_Insert_delete(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 新增关注客户step2
	 * @param opportunityID
	 * @param userID
	 */
	void sUserFollowDetail_Insert(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 删除关注客户
	 */
	void sUserFollowDetail_Delete(@Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 新增客户标签时验证
	 * @param tagName
	 * @return
	 */
	List<Map<String,Object>> CustomerTagDetail_InsertValid(@Param("tagName")String tagName);
	
	void CustomerTagDetail_Insert_delete(@Param("customerID")String customerID,@Param("userID")String userID);
	
	void CustomerTagDetail_Insert(@Param("customerID")String customerID,@Param("userID")String userID,@Param("tagName")String tagName);
	
	
	List<Map<String,Object>> TagDetail_Select(@Param("userID")String userID,@Param("tagName")String tagName);
	
	void TagDetail_Insert(@Param("userID")String userID,@Param("tagName")String tagName);
	
	/**
	 * 获取Tag列表信息
	 * @param userID
	 * @return
	 */
	List<Map<String,Object>> TagList_Select(@Param("userID")String userID);
	
	/**
	 * 客户意向项目列表
	 * @param projectID
	 * @param customerID
	 * @param opportunityID
	 * @return
	 */
	List<Map<String,Object>> sCustomerAttachList_Select(@Param("projectID")String projectID,@Param("customerID")String customerID,@Param("opportunityID")String opportunityID);
	
	/**
	 * 获取SubscribeInfo详情及附属信息
	 * @param ID
	 * @return
	 */
	List<Map<String,Object>> sSubscribeInfoDetails_Select(@Param("ID")String ID);
	
	/**
	 * 项目列表
	 * @param where
	 * @return
	 */
	List<Map<String,Object>> sProject_Select(@Param("where")String where);
	
	/**
	 * 机会信息
	 * @param where
	 * @return
	 */
	Map<String,Object> OpportunityDetail_Select(@Param("where")String where);
	
	/**
	 * 获取项目信息
	 * @param ID
	 * @return
	 */
	Map<String,Object> Project_Detail_FindById(@Param("ID")String ID);
	
	/**
	 * 获取销售认知途径
	 * @return
	 */
	List<Map<String,Object>> SystemDictionaryXSRZTJList_Select();
	
	/**
	 * 获取认知媒体及媒体子类
	 * @param projectID
	 * @return
	 */
	List<Map<String,Object>> SystemDictionaryRZMTList_Select(@Param("projectID")String projectID);
	
	/**
	 * 获取字典集合
	 * @param pID
	 * @return
	 */
	List<Map<String,Object>> DictionaryAllList_Select(@Param("pID")String pID);
	
	/**
	 * 获取字典集合
	 * @param pID
	 * @return
	 */
	List<Map<String,Object>> DictionaryList_Select(@Param("pID")String pID);
	
	/**
	 * 获取顾问客户基本信息
	 * @return
	 */
	Map<String,Object> sCustomerPotentialClue(@Param("where")String where);
	
	/**
	 * 获取CustomerAttach详情信息
	 * @param customerID
	 * @param projectID
	 * @param intentProjectID
	 * @param opportunityID
	 * @return
	 */
	Map<String,Object> CustomerAttachDetail_Select(@Param("customerID")String customerID, @Param("projectID")String projectID, @Param("intentProjectID")String intentProjectID, @Param("opportunityID")String opportunityID);
	
	/**
	 * 插入CustomerAttach新增信息
	 * @param customerID
	 * @param projectID
	 * @param intentProjectID
	 * @param opportunityID
	 * @param userID
	 */
	void CustomerAttachDetail_Insert(@Param("customerID")String customerID, @Param("projectID")String projectID, @Param("intentProjectID")String intentProjectID, @Param("opportunityID")String opportunityID,@Param("userID")String userID);
	
	/**
	 * 开启销支并且客储等级小于2级
	 * @param opportunityID
	 * @return
	 */
	Map<String,Object> mCustomerAllotRoleAndRank_Select(@Param("opportunityID")String opportunityID);
	
	/**
	 * 获取OpportunityGiveUp详情信息
	 * @param opportunityID
	 * @return
	 */
	Map<String,Object> sOpportunityGiveUpDetail_Exists(@Param("opportunityID")String opportunityID);
	
	/**
	 * 插入OpportunityGiveUp新增信息
	 * @param map
	 */
	void sOpportunityGiveUpDetail_Insert(Map<String,Object> map);
	
	/**
	 * 获取顾问上一级营销经理列表
	 * @param projectID
	 * @return
	 */
	List<Map<String,Object>> SystemMessageSaleUserYXJL_Select(@Param("projectID")String projectID);
	
	/**
	 * 获取SignInfo详情及附属信息
	 * @param signID
	 * @return
	 */
	Map<String,Object> sSignInfoDetail_Select(@Param("signID")String signID);
	
	/**
	 * 获取PayInfo信息
	 * @param signID
	 * @return
	 */
	List<Map<String,Object>> sPaymentNodeList_Select(@Param("signID")String signID);
	
	/**
	 * 修改用户资料
	 * @param map
	 */
	void SalesUserDetail_Update(Map<String,Object> map);
	
	/**
	 * 修改兼职用户资料
	 * @param map
	 */
	void JZDetail_Update(Map<String,Object> map);
	
	/**
	 * 查询是否已经存在无效的FormSessionID,不存在则更新为无效状态(count)
	 * @param formSessionID
	 * @return
	 */
	Long mSystemFormSessionStatus_Select_count(@Param("formSessionID")String formSessionID);
	
	/**
	 * 查询是否已经存在无效的FormSessionID,不存在则更新为无效状态(count)
	 * @param formSessionID
	 * @return
	 */
	void mSystemFormSessionStatus_Select_update(@Param("formSessionID")String formSessionID);
	
	/**
	 * 验证机会客户
	 * @param map
	 * @return
	 */
	Map<String,Object> CustomerOpportunity_Exist(Map<String,Object> map);
	
	/**
	 * 客户信息
	 * @param mobile
	 * @return
	 */
	List<Map<String,Object>> Customer_Exist(@Param("mobile")String mobile);
	
	/**
	 * 渠道分配消息设置查询step1
	 * @param clueID
	 * @return
	 */
	Map<String,Object> RemindRuleAllotDetail_Select_step1(@Param("clueID")String clueID);
	
	/**
	 * 渠道分配消息设置查询step2
	 * @param projectID
	 * @param protectSource
	 * @return
	 */
	Map<String,Object> RemindRuleAllotDetail_Select_step2(@Param("projectID")String projectID,@Param("protectSource")String protectSource);
	
	/**
	 * 添加新客户
	 * @param map
	 */
	void mNewCustomerGWDetail_Insert_step1(Map<String,Object> map);
	void mNewCustomerGWDetail_Insert_step2(Map<String,Object> map);
	void mNewCustomerGWDetail_Insert_step3(Map<String,Object> map);
	void mNewCustomerGWDetail_Insert_step4(Map<String,Object> map);
	void mNewCustomerGWDetail_Insert_step5(Map<String,Object> map);
	
	void P_OpportunityCustomerRank(Map<String,Object> map);
	void P_SyncClueOpportunity_Update(Map<String,Object> map);
	
	/**
	 * 顾问新增已有客户机会信息
	 * @param map
	 */
	void mOldCustomerGWDetail_Insert_step1(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step2(Map<String,Object> map);
	List<Map<String,Object>> mOldCustomerGWDetail_Insert_step3_vaild(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step3_insert(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step3_update(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step4(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step5(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step6(Map<String,Object> map);
	void mOldCustomerGWDetail_Insert_step7(Map<String,Object> map);
	
	List<Map<String,Object>> sClueSiblingList_Select(@Param("clueID") String clueID,@Param("projectID")String projectID);
	
	/**
	 * 新增线索客户信息
	 * @param map
	 */
	void sClueCustomer_Update_step1(Map<String,Object> map);
	void sClueCustomer_Update_step2(Map<String,Object> map);
	void sClueCustomer_Update_step3(Map<String,Object> map);
	
	/**
	 * 新增线索客户信息
	 * @param map
	 */
	List<Map<String,Object>> sClueCustomer_Insert_step1_valid_1(Map<String,Object> map);
	void sClueCustomer_Insert_step1_insert_1(Map<String,Object> map);
	List<Map<String,Object>> sClueCustomer_Insert_step1_valid_2(Map<String,Object> map);
	void sClueCustomer_Insert_step1_insert_2(Map<String,Object> map);
	void sClueCustomer_Insert_step2(Map<String,Object> map);
	void sClueCustomer_Insert_step3(Map<String,Object> map);
	
	/**
	 * 获取线索信息
	 * @param mobile
	 * @param projectID
	 * @return
	 */
	List<Map<String,Object>> sClue_Select(String mobile,String projectID);
	
	/**
	 * 新增线索客户信息
	 * @param clueID
	 * @param projectID
	 */
	void sClueCustomerFailure_Update(@Param("clueID") String clueID,@Param("projectID")String projectID);
	
	/**
	 * 插入CustomerAttach新增信息
	 * @param map
	 * @return
	 */
	Map<String,Object> sStageCustomerAttachDetail_Insert_step1(Map<String,Object> map);
	void sStageCustomerAttachDetail_Insert_step2(Map<String,Object> map);
	void sStageCustomerAttachDetail_Insert_step3(Map<String,Object> map);
	
	/**
	 * 客户手机号搜索信息新增
	 * @param map
	 * @return
	 */
	Map<String,Object> CustomerMobileSearchDetail_Insert_step1(Map<String,Object> map);
	void CustomerMobileSearchDetail_Insert_step2(Map<String,Object> map);
	
	/**
	 * 刷新到访逾期线索
	 * @param map
	 */
	void ComeOverdueClue_Update_step1(Map<String,Object> map);
	void ComeOverdueClue_Update_step2(Map<String,Object> map);
	
	/**
	 * 若是公共客户并且已更新渠道，获取客户信息
	 * @param mobile
	 * @param projectID
	 * @return
	 */
	Map<String,Object> mCustomerGGUpdateChannelSource_Select(@Param("mobile")String mobile,@Param("projectID")String projectID);
	
	/**
	 * 查询项目是否开启分接
	 * @param projectID
	 * @return
	 */
	Map<String,Object> ProjectIsNoAllot_Select(@Param("projectID")String projectID);
	
	/**
	 * 潜在客户信息
	 * @param mobile
	 * @return
	 */
	Map<String,Object> CustomerPotential_Exist(@Param("mobile")String mobile);
	
	/**
	 * 获取机会客户信息
	 * @param opportunityID
	 * @return
	 */
	Map<String,Object> sCustomerGWSearch_Select(@Param("opportunityID")String opportunityID);
	
	/**
	 * 更新客户信息
	 * @param map
	 */
	void mCustomerGWDetail_Update_step1(Map<String,Object> map);
	List<Map<String,Object>> mCustomerGWDetail_Update_step2_valid(Map<String,Object> map);
	void mCustomerGWDetail_Update_step2_insert(Map<String,Object> map);
	void mCustomerGWDetail_Update_step2_update(Map<String,Object> map);
	void mCustomerGWDetail_Update_step3(Map<String,Object> map);
	void mCustomerGWDetail_Update_step4(Map<String,Object> map);
	void mCustomerGWDetail_Update_step5(Map<String,Object> map);
	
	/**
	 * 开启允许提交认购并且客储等级小于2级
	 * @param opportunityID
	 * @return
	 */
	Map<String,Object> mCustomerTwoRankAllowSubscribe_Select(@Param("opportunityID")String opportunityID);
	
	/**
	 * 插入OpportunityCustomerRel新增信息
	 * @param map
	 */
	void mCustomerSubscribeDetail_Insert_step1(Map<String,Object> map);
	
	List<Map<String,Object>> mCustomerSubscribeDetail_Insert_step2_valid(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step2_insert(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step2_update(Map<String,Object> map);
	
	void mCustomerSubscribeDetail_Insert_step3(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step4(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step5(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step6(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step7(Map<String,Object> map);
	void mCustomerSubscribeDetail_Insert_step8(Map<String,Object> map);
	
	/**
	 * 获取客户关联权益人列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> sCustomerRelPeople_Select(Map<String,Object> map);
	
	/**
	 * 用户关注客户列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> sUserFollowList_Select(Map<String,Object> map);
	Long sUserFollowList_Select_count(Map<String,Object> map);
	
	/**
	 * 获取项目楼栋信息信息
	 * @param projectID
	 * @return
	 */
	Map<String,Object> ProjectBuildingDetailByProjectIDTop_Select(@Param("projectID")String projectID);
	
	/**
	 * 获取项目楼栋信息信息
	 * @param buildingID
	 * @return
	 */
	Map<String,Object> ProjectBuildingDetail_Select(@Param("buildingID")String buildingID);
	
	/**
	 * 获取项目房间列表信息
	 * @param buildingID
	 * @param where
	 * @return
	 */
	List<Map<String,Object>> ProjectRoomList_Select(@Param("buildingID")String buildingID,@Param("where")String where);
	
	/**
	 * 查询本地客户信息
	 * @param customerID
	 * @return
	 */
	Map<String,Object> LocalCustomerDetail_Select(@Param("customerID")String customerID);
	
	/**
	 * 明源添加用户信息
	 * @param map
	 */
	@DS("slave_my")
	List<Map<String,Object>> MYUserDetail_Insert_valid_1(Map<String,Object> map);
	@DS("slave_my")
	List<Map<String,Object>> MYUserDetail_Insert_valid_2(Map<String,Object> map);
	@DS("slave_my")
	void MYUserDetail_Insert_step1(Map<String,Object> map);
	@DS("slave_my")
	void MYUserDetail_Insert_step2(Map<String,Object> map);
	
	/**
	 * 添加明源客户信息
	 * @param map
	 */
	@DS("slave_my")
	List<Map<String,Object>> MYCustomerDetail_Insert_vaild_1(Map<String,Object> map);
	@DS("slave_my")
	List<Map<String,Object>> MYCustomerDetail_Insert_vaild_2(Map<String,Object> map);
	@DS("slave_my")
	void MYCustomerDetail_Insert_insert_1(Map<String,Object> map);
	@DS("slave_my")
	void MYCustomerDetail_Insert_insert_2(Map<String,Object> map);
	@DS("slave_my")
	void MYCustomerDetail_Insert_update(Map<String,Object> map);
	
	
	/**
	 * 查询本地机会信息
	 * @param opportunityID
	 * @return
	 */
	List<Map<String,Object>> LocalOpportunityDetail_Select(@Param("opportunityID")String opportunityID);
	
	/**
	 * 查询本地机会客户信息
	 * @param opportunityID
	 * @return
	 */
	List<Map<String,Object>> LocalOpportunityCustomerList_Select(@Param("opportunityID")String opportunityID);
	
	@DS("slave_my")
	List<Map<String,Object>> MYOpportunityDetail_Insert_valid_1(Map<String,Object> map);
	@DS("slave_my")
	List<Map<String,Object>> MYOpportunityDetail_Insert_valid_2(Map<String,Object> map);
	@DS("slave_my")
	List<Map<String,Object>> MYOpportunityDetail_Insert_valid_3(Map<String,Object> map);
	@DS("slave_my")
	List<Map<String,Object>> MYOpportunityDetail_Insert_valid_4(Map<String,Object> map);
	@DS("slave_my")
	void MYOpportunityDetail_Insert_insert_1(Map<String,Object> map);
	@DS("slave_my")
	void MYOpportunityDetail_Insert_insert_2(Map<String,Object> map);
	@DS("slave_my")
	void MYOpportunityDetail_Insert_update(Map<String,Object> map);
	
	@DS("slave_my")
	void MyOpportunityCustomerDetail_Delete(Map<String,Object> map);
	
	@DS("slave_my")
	void MyOpportunityCustomerDetail_Insert(Map<String,Object> map);
	
	
}
