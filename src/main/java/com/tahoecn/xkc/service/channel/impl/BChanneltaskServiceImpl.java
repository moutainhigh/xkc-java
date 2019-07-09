package com.tahoecn.xkc.service.channel.impl;

import com.tahoecn.xkc.model.channel.BChanneltask;
import com.tahoecn.xkc.mapper.channel.BChanneltaskMapper;
import com.tahoecn.xkc.service.channel.IBChanneltaskService;
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
	public List<BChanneltask> mChannelTask_Insert(Map<String, Object> map) {
	
		return bChanneltaskMapper.mChannelTask_Insert(map);
	}
	
	@Override
	public List<BChanneltask> mChannelTask_Insert2(Map<String, Object> map) {
	
		return bChanneltaskMapper.mChannelTask_Insert2(map);
	}
	
	@Override
	public List<BChanneltask> mChannelTask_Insert3(Map<String, Object> map) {
	
		return bChanneltaskMapper.mChannelTask_Insert3(map);
	}
	
	@Override
	public List<BChanneltask> mChannelTask_Insert4(Map<String, Object> map) {
	
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

}
