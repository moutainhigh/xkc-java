package com.tahoecn.xkc.service.channel.impl;

import com.tahoecn.xkc.model.channel.BChanneltask;
import com.tahoecn.xkc.model.channel.BChanneltaskarea;
import com.tahoecn.xkc.mapper.channel.BChanneltaskMapper;
import com.tahoecn.xkc.service.channel.IBChanneltaskService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
 * @since 2019-07-04
 */
@Service
public class BChanneltaskServiceImpl extends ServiceImpl<BChanneltaskMapper, BChanneltask> implements IBChanneltaskService {

	@Autowired
	private BChanneltaskMapper bChanneltaskMapper;
	
	/*
	 * 新建任务
	 */
	@Override
	public String mChannelTask_Insert(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTask_Insert(map);
	}
	
	@Override
	public void mChannelTask_Insert2(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTask_Insert2(map);
	}
	
	@Override
	public void mChannelTask_Insert3(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTask_Insert3(map);
	}
	
	@Override
	public Map<String, Object> mChannelTask_Insert4(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTask_Insert4(map);
	}

	/*
	 * 结束任务
	 */
	@Override
	public void mChannelTaskClose_Update(Map<String, Object> map) {
		 bChanneltaskMapper.mChannelTaskClose_Update(map);
	}
	@Override
	public void mChannelTaskClose_Update2(Map<String, Object> map) {
		 bChanneltaskMapper.mChannelTaskClose_Update2(map);
	}
	@Override
	public List<BChanneltask> mChannelTaskClose_Update3(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTaskClose_Update3(map);
	}

	/*
	 * 专员-是否有进行中的任务
	 */
	@Override
	public List<Map<String, Object>> mChannelTaskIsHaveUndoneZQ_Select(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTaskIsHaveUndoneZQ_Select(map);
		
	}

	/*
	 *  兼职考勤日历打卡信息
	 */
	@Override
	public List<Map<String, Object>> mChannelTaskIsHaveUndoneJZ_Select(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTaskIsHaveUndoneJZ_Select(map);
		
	}

	/*
	 * 打卡
	 */
	@Override
	public boolean mChannelCheckClock_Insert(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelCheckClock_Insert(map);
	}

	/*
	 * 获取当天第一条打卡记录
	 */
	@Override
	public Map<String, Object> mChannelCheckClockTopOne_Select(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelCheckClockTopOne_Select(map);
	}

	/*
	 * 获取当天第一条打卡记录
	 */
	@Override
	public Map<String, Object> mChannelCheckClockByDeviceCode_Select(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelCheckClockByDeviceCode_Select(map);
	}

	/*
	 * 分配专员客户
	 */
	@Override
	public void mChannelTempPersonQuitCus_Update(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTempPersonQuitCus_Update(map);
	}
	@Override
	public void mChannelTempPersonQuitCus_Update2(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTempPersonQuitCus_Update2(map);
	}
	@Override
	public void mChannelTempPersonQuitCus_Update3(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTempPersonQuitCus_Update3(map);
	}

	/*
	 * 领取任务
	 */
	@Override
	public int mChannelTaskAccept_Insert(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTaskAccept_Insert(map);
	}
	@Override
	public void mChannelTaskAccept_Insert2(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTaskAccept_Insert2(map);
	}
	@Override
	public void mChannelTaskAccept_Insert3(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTaskAccept_Insert3(map);
	}
	/*
	 * 更新兼职归属关系
	 */
	@Override
	public void mChannelTaskAccept_Update(Map<String, Object> map) {
		bChanneltaskMapper.mChannelTaskAccept_Update(map);
	}
	/*
	 * 领取的任务状态
	 */
	@Override
	public List<Map<String, Object>> mChannelTaskAccept_Select(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelTaskAccept_Select(map);
	}

	/*
	 * 写入任务结束消息
	 */
	@Override
	public void mChannelLeaderQuit_Update(Map<String, Object> map) {
		bChanneltaskMapper.mChannelLeaderQuit_Update(map);
	}
	/*
	 * 强制结束任务
	 */
	@Override
	public void mChannelLeaderQuit_Insert(Map<String, Object> map) {
		bChanneltaskMapper.mChannelLeaderQuit_Insert(map);
	}

	@Override
	public void mChannelLeaderQuit_Update2(Map<String, Object> map) {
		bChanneltaskMapper.mChannelLeaderQuit_Update2(map);
	}
	/*
	 * 写入将分配的线索跟进记录
	 */
	@Override
	public void mChannelLeaderQuit_Insert2(Map<String, Object> map) {
		bChanneltaskMapper.mChannelLeaderQuit_Insert2(map);
	}
	/*
	 * 分配线索 
	 */
	@Override
	public void mChannelLeaderQuit_Update3(Map<String, Object> map) {
		bChanneltaskMapper.mChannelLeaderQuit_Update3(map);
	}
	/*
	 * 分配兼职 
	 */
	@Override
	public void mChannelLeaderQuit_Update4(Map<String, Object> map) {
		bChanneltaskMapper.mChannelLeaderQuit_Update4(map);
	}

	/*
	 * 经理团队列表
	 */
	@Override
	public List<Map<String, Object>> mChannelLeaderList_Select(Map<String, Object> map) {
		return 	bChanneltaskMapper.mChannelLeaderList_Select(map);
	}

	/*
	 * 经理团队列表数量统计
	 */
	@Override
	public int mChannelLeaderList_SelectAllCount(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelLeaderList_SelectAllCount(map);
	}

	/*
	 * 是否保存锁房图片到本地
	 */
	@Override
	public void CustomerLockRoomClientSaveDetail_Update(Map<String, Object> map) {
		bChanneltaskMapper.CustomerLockRoomClientSaveDetail_Update(map);
	}

	/*
	 * 经理作战图任务列表
	 */
	@Override
	public List<Map<String, Object>> mChannelLeaderTaskList_Select(Map<String, Object> map) {
		return bChanneltaskMapper.mChannelLeaderTaskList_Select(map);
	}

//	@Override
//	public List<Map<String, Object>> mChannelLeaderList_Select(IPage page, String ProjectID, String sqlWhere) {
//		return bChanneltaskMapper.mChannelLeaderList_Select(page, ProjectID, sqlWhere);
//	}

	
}
