package com.tahoecn.xkc.service.channel;

import com.tahoecn.xkc.model.channel.BChanneltaskarea;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface IBChanneltaskareaService extends IService<BChanneltaskarea> {

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
