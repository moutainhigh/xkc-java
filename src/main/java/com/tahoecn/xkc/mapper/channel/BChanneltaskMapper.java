package com.tahoecn.xkc.mapper.channel;

import com.tahoecn.xkc.model.channel.BChanneltask;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BChanneltaskMapper extends BaseMapper<BChanneltask> {

	/*
	 * 新建任务
	 */
	List<BChanneltask> mChannelTask_Insert(Map<String, Object> map) ;
	List<BChanneltask> mChannelTask_Insert2(Map<String, Object> map);
	List<BChanneltask> mChannelTask_Insert3(Map<String, Object> map);
	List<BChanneltask> mChannelTask_Insert4(Map<String, Object> map);

	/*
	 * 结束任务
	 */
	void mChannelTaskClose_Update(Map<String, Object> map);
	void mChannelTaskClose_Update2(Map<String, Object> map);
	List<BChanneltask> mChannelTaskClose_Update3(Map<String, Object> map);


}
