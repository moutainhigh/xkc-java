package com.tahoecn.xkc.mapper.sys;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wq_qycc @
 */
public interface RuleRemindMapper {

	/**
	 * 查询设置回收规则的项目
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> RemindRuleList_Select();

	/**
	 * 渠道消息是否开启状态
	 * 
	 * @return
	 */
	List<Map<String, Object>> ChannelMessageList_Select();

	// 新消息推送开始
	List<Map<String, Object>> WaitingForTodayList_Select(Map<String, Object> map);

	List<Map<String, Object>> FollowUpOverdueList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSubList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSignList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverduePaymentList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSubMList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSubMBeforeList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSubMAfterList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSignMList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSignMBeforeList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSignMAfterList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueUnPaymentMList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueUnPaymentMBeforeList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueUnPaymentMAfterList_Select(Map<String, Object> map);
	// 新消息推送结束

	// 渠道消息推送开始
	List<Map<String, Object>> RecognizeRemindList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSubRemindList_Select(Map<String, Object> map);

	List<Map<String, Object>> OverdueSignRemindList_Select(Map<String, Object> map);

	List<Map<String, Object>> CheckOutRemindList_Select(Map<String, Object> map);

	List<Map<String, Object>> CustomerInvalidRemindList_Select(Map<String, Object> map);

	List<Map<String, Object>> FollowUpOverdueListZQ_Select(Map<String, Object> map);
	// 渠道消息推送结束

	/**
	 * 跟进即将逾期消息更新为无效
	 */
	void ToFollowOverdueDate_Update();

	/**
	 * 写入消息
	 * 
	 * @return
	 */
	Long RemindMessage_Insert(List<Map<String, Object>> list);
}
