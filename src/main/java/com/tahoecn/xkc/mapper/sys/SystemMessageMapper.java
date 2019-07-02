package com.tahoecn.xkc.mapper.sys;


import com.tahoecn.xkc.model.vo.UnreadCountVo;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
public interface SystemMessageMapper {

    List<UnreadCountVo> UnreadCountListByMessageType_Select(@Param("projectId")String projectId, @Param("userId") String userId);

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
}
