package com.tahoecn.xkc.mapper.channel;

import com.tahoecn.xkc.model.channel.BChanneltaskarea;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BChanneltaskareaMapper extends BaseMapper<BChanneltaskarea> {

	/*
	 * 任务地点列表
	 */
	List<BChanneltaskarea> mChannelTaskAreaList_Select(IPage page, String UserID);

	/*
	 * 新建任务地点
	 */
	void mChannelTaskArea_Insert(Map<String, Object> map);

	/*
	 * 任务地点数量
	 */
	int mChannelTaskAreaList_SelectAllCount(Map<String, Object> map);

}
