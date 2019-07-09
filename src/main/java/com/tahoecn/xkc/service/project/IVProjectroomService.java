package com.tahoecn.xkc.service.project;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.VProjectroom;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface IVProjectroomService extends IService<VProjectroom> {

	/**
	 * 获取项目房间列表信息
	 */
	List<Map<String,Object>> RoomList_Select(Map<String, Object> paramMap);

	/**
	 * 获取项目房间列表信息(营销经理)
	 */
	List<Map<String, Object>> RoomListYXJL_Select(Map<String, Object> paramMap);

}
