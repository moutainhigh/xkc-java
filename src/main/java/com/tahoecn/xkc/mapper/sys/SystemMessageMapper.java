package com.tahoecn.xkc.mapper.sys;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.vo.UnreadCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
public interface SystemMessageMapper {

	/**
	 * 未读消息数
	 */
    List<UnreadCountVo> UnreadCountListByMessageType_Select(Map<String, Object> map);

    /**
     * 消息列表-普通消息
     */
    List<Map<String,Object>> SystemMessageListByMessageType_Select(Map<String, Object> map);

    /**
     * 消息列表-普通消息-总数
     */
    int SystemMessageListByMessageType_SelectCount(Map<String, Object> map);
    
	/**
	 * 更新消息信息
	 */
	void updMessage(Map<String, Object> map);

	/**
     * 消息列表-渠道任务消息
     */
	List<Map<String, Object>> ListByMessageTypeChannelTask_Select(Map<String, Object> map);

	/**
	 * 消息列表-渠道任务消息-总数
	 */
	int ListByMessageTypeChannelTask_SelectCount(Map<String, Object> map);
	
	/**
	 * 消息列表-机会消息-自渠
	 */
	List<Map<String,Object>> SystemMessageListByMessageTypeOpportunityZQ_Select(Map<String, Object> map);

	/**
	 * 消息列表-机会消息-自渠-总数
	 */
	int SystemMessageListByMessageTypeOpportunityZQ_SelectCount(Map<String, Object> map);

	/**
	 * 更新消息信息-自渠
	 */
	void updMessageZQ(Map<String, Object> map);

	/**
	 * 消息列表-丢失消息
	 */
	List<Map<String, Object>> SystemMessageListByMessageTypeKHDS_Select(Map<String, Object> map);

	/**
	 * 消息列表-丢失消息-总数
	 */
	int SystemMessageListByMessageTypeKHDS_SelectCount(Map<String, Object> map);
	
	/**
	 * 顾问客户消息列表
	 */
	List<Map<String,Object>> SystemMessageListByMessageTypeOpportunity_Select(Map<String, Object> map);

	/**
	 * 顾问客户消息列表-总数
	 */
	int SystemMessageListByMessageTypeOpportunity_SelectCount(Map<String, Object> map);
	
	/**
	 * 拓客客户消息列表
	 */
	List<Map<String,Object>> SystemMessageListByMessageTypeClue_Select(Map<String, Object> map);

	/**
	 * 拓客客户消息列表-总数
	 */
	int SystemMessageListByMessageTypeClue_SelectCount(Map<String, Object> map);

	/**
	 * 该项目是否有分享项目信息
	 */
	int IsExistsShareProject(Map<String, Object> map);

	/**
	 * 消息详情
	 */
	List<Map<String, Object>> SystemMessageDetail_Select(Map<String, Object> map);

	/**
	 * 消息添加
	 */
	void SystemMessageDetail_Insert(Map<String, Object> map);
	/**
	 * 设消息为已读
	 */
	void SystemMessageReadDetail_Update(Map<String, Object> map);

    IPage<Map<String, Object>> mMessageAllList_Select(IPage page, @Param("UserID") String userID);
    int mMessageAllList_Updata(@Param("ID") String ID);
    
    void SystemMessageDetailByHandle_Update(@Param("BizType")String BizType,@Param("SetStatus")String SetStatus,@Param("Where")String Where);
    /**
	 * 设置动态消息已读
	 */
	void mMessageDynamicReadList_Update(Map<String, Object> map);
	/**
	 * 今日待跟进列表
	 */
	List<Map<String, Object>> ListDRDGJ_Select(Map<String, Object> map);
	/**
	 * 今日待跟进列表-总数
	 */
	int ListDRDGJ_SelectCount(Map<String, Object> map);
	/**
	 * 当日认购逾期列表
	 */
	List<Map<String, Object>> ListDRRGYQ_Select(Map<String, Object> map);
	/**
	 * 当日认购逾期列表-总数
	 */
	int ListDRRGYQ_SelectCount(Map<String, Object> map);
	/**
	 * 当日跟进逾期-销售团队负责人/营销负责人
	 */
	List<Map<String, Object>> ListDRGJYQOpportunity_Select(Map<String, Object> map);
	/**
	 * 当日跟进逾期-销售团队负责人/营销负责人-总数
	 */
	int ListDRGJYQOpportunity_SelectCount(Map<String, Object> map);
	/**
	 * 当日跟进逾期-其他
	 */
	List<Map<String, Object>> ListDRGJYQClue_Select(Map<String, Object> map);
	/**
	 * 当日跟进逾期-其他-总数
	 */
	int ListDRGJYQClue_SelectCount(Map<String, Object> map);
	/**
     * 当日签约逾期
     */
	List<Map<String, Object>> ListDRQYYQ_Select(Map<String, Object> map);
	/**
     * 当日签约逾期-总数
     */
	int ListDRQYYQ_SelectCount(Map<String, Object> map);
	/**
     * 当日回款逾期
     */
	List<Map<String, Object>> ListDRHKYQ_Select(Map<String, Object> map);
	/**
     * 当日回款逾期-总数
     */
	int ListDRHKYQ_SelectCount(Map<String, Object> map);
	//今日待跟进
	List<String> DRDGJCalendar(Map<String, Object> paramMap);
	//当日跟进逾期-机会
	List<String> DRGJYQOpportunityCalendar(Map<String, Object> paramMap);
	//当日跟进逾期-线索
	List<String> DRGJYQClueCalendar(Map<String, Object> paramMap);
	//当日认购逾期
	List<String> DRRGYQCalendar(Map<String, Object> paramMap);
    //当日签约逾期
	List<String> DRQYYQCalendar(Map<String, Object> paramMap);
    //当日回款逾期
	List<String> DRHKYQCalendar(Map<String, Object> paramMap);
}
