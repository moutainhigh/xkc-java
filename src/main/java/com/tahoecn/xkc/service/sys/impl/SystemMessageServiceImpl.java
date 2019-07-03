package com.tahoecn.xkc.service.sys.impl;

import com.tahoecn.xkc.mapper.sys.SystemMessageMapper;
import com.tahoecn.xkc.model.vo.UnreadCountVo;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
@Service
public class SystemMessageServiceImpl implements ISystemMessageService {

    @Autowired
    private SystemMessageMapper systemMessageMapper;

    /**
	 * 未读消息数
	 */
    @Override
    public List<UnreadCountVo> UnreadCountListByMessageType_Select(Map<String, Object> map) {
        return systemMessageMapper.UnreadCountListByMessageType_Select(map);
    }

    /**
     * 消息列表-普通消息
     */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageType_Select(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageType_Select(map);
	}
	
	/**
     * 消息列表-普通消息-总数
     */
	@Override
	public int SystemMessageListByMessageType_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageType_SelectCount(map);
	}

	/**
	 * 更新消息信息
	 */
	@Override
	public void updMessage(Map<String, Object> map) {
		systemMessageMapper.updMessage(map);
	}

	/**
	 * 消息列表-渠道任务消息
	 */
	@Override
	public List<Map<String, Object>> ListByMessageTypeChannelTask_Select(Map<String, Object> map) {
		return systemMessageMapper.ListByMessageTypeChannelTask_Select(map);
	}
	
	/**
	 * 消息列表-渠道任务消息-总数
	 */
	@Override
	public int ListByMessageTypeChannelTask_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.ListByMessageTypeChannelTask_SelectCount(map);
	}
	
	/**
	 * 消息列表-机会消息-自渠
	 */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageTypeOpportunityZQ_Select(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunityZQ_Select(map);
	}

	/**
	 * 消息列表-机会消息-自渠-总数
	 */
	@Override
	public int SystemMessageListByMessageTypeOpportunityZQ_SelectCount(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunityZQ_SelectCount(map);
	}
	
	/**
	 * 更新消息信息-自渠
	 */
	@Override
	public void updMessageZQ(Map<String, Object> map){
		systemMessageMapper.updMessageZQ(map);
	}

	/**
	 * 消息列表-丢失消息
	 */
	@Override
	public List<Map<String, Object>> ListByMessageTypeKHDS_Select(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageTypeKHDS_Select(map);
	}

	/**
	 * 消息列表-丢失消息-总数
	 */
	@Override
	public int ListByMessageTypeKHDS_SelectCount(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageListByMessageTypeKHDS_SelectCount(map);
	}
	
	/**
	 * 顾问客户消息列表
	 */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageTypeOpportunity_Select(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunity_Select(map);
	}

	/**
	 * 顾问客户消息列表-总数
	 */
	@Override
	public int SystemMessageListByMessageTypeOpportunity_SelectCount(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeOpportunity_SelectCount(map);
	}
	
	/**
	 * 拓客客户消息列表
	 */
	@Override
	public List<Map<String,Object>> SystemMessageListByMessageTypeClue_Select(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeClue_Select(map);
	}

	/**
	 * 拓客客户消息列表-总数
	 */
	@Override
	public int SystemMessageListByMessageTypeClue_SelectCount(Map<String, Object> map){
		return systemMessageMapper.SystemMessageListByMessageTypeClue_SelectCount(map);
	}
	
	/**
	 * 该项目是否有分享项目信息
	 */
	@Override
	public int IsExistsShareProject(Map<String, Object> map){
		return systemMessageMapper.IsExistsShareProject(map);
	}

	/**
	 * 消息详情
	 */
	@Override
	public List<Map<String, Object>> SystemMessageDetail_Select(Map<String, Object> map) {
		return systemMessageMapper.SystemMessageDetail_Select(map);
	}

	/**
	 * 消息添加
	 */
	@Override
	public void SystemMessageDetail_Insert(Map<String, Object> map) {
		systemMessageMapper.SystemMessageDetail_Insert(map);
	}

	/**
	 * 设消息为已读
	 */
	@Override
	public void SystemMessageReadDetail_Update(Map<String, Object> map) {
		systemMessageMapper.SystemMessageReadDetail_Update(map);
	}
}
