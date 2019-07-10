package com.tahoecn.xkc.service.channel;

import com.tahoecn.xkc.model.channel.BChanneltask;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface IBChanneltaskService extends IService<BChanneltask> {

	/*
	 * 新建任务
	 */
	String mChannelTask_Insert(Map<String, Object> map);
	
	void mChannelTask_Insert2(Map<String, Object> map);
	
	void mChannelTask_Insert3(Map<String, Object> map);
	
	List<Map<String, Object>> mChannelTask_Insert4(Map<String, Object> map);

	/*
	 * 结束任务
	 */
	void mChannelTaskClose_Update(Map<String, Object> map);

	void mChannelTaskClose_Update2(Map<String, Object> map);
	
	List<BChanneltask> mChannelTaskClose_Update3(Map<String, Object> map);
	
	/*
	 * 专员-是否有进行中的任务
	 */
	List<Map<String, Object>> mChannelTaskIsHaveUndoneZQ_Select(Map<String, Object> map);

	/*
	 * 兼职-是否有进行中的任务
	 */
	List<Map<String, Object>> mChannelTaskIsHaveUndoneJZ_Select(Map<String, Object> map);

	/*
	 * 打卡
	 */
	boolean mChannelCheckClock_Insert(Map<String, Object> map);

	/*
	 * 获取当天第一条打卡记录
	 */
	List<Map<String, Object>> mChannelCheckClockTopOne_Select(Map<String, Object> map);

	/*
	 * 获取当天第一条打卡记录
	 */
	List<Map<String, Object>> mChannelCheckClockByDeviceCode_Select(Map<String, Object> map);

	/*
	 * 分配专员客户
	 */
	void mChannelTempPersonQuitCus_Update(Map<String, Object> map);
	void mChannelTempPersonQuitCus_Update2(Map<String, Object> map);
	void mChannelTempPersonQuitCus_Update3(Map<String, Object> map);
}
