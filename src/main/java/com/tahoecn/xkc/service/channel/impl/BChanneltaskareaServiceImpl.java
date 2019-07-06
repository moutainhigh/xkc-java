package com.tahoecn.xkc.service.channel.impl;

import com.tahoecn.xkc.model.channel.BChanneltaskarea;
import com.tahoecn.xkc.service.channel.IBChanneltaskareaService;
import com.tahoecn.xkc.mapper.channel.BChanneltaskareaMapper;
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
public class BChanneltaskareaServiceImpl extends ServiceImpl<BChanneltaskareaMapper, BChanneltaskarea> implements IBChanneltaskareaService {

	@Autowired
	private BChanneltaskareaMapper bChanneltaskareaMapper;
	
	/*
	 * 任务地点列表
	 */
	@Override
	public List<BChanneltaskarea> mChannelTaskAreaList_Select(IPage page, String UserID) {
		return bChanneltaskareaMapper.mChannelTaskAreaList_Select(page, UserID);
	}

	/*
	 * 新建任务地点
	 */
	@Override
	public void mChannelTaskArea_Insert(Map<String, Object> map) {
		bChanneltaskareaMapper.mChannelTaskArea_Insert(map);
		
	}

	/*
	 * 任务地点数量
	 */
	@Override
	public int mChannelTaskAreaList_SelectAllCount(Map<String, Object> map) {
		
		return bChanneltaskareaMapper.mChannelTaskAreaList_SelectAllCount(map);
	}
}
