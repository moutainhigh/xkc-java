package com.tahoecn.xkc.service.project;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.BRoom;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface IBRoomService extends IService<BRoom> {

	/**
	 * 获取项目房源信息
	 */
	List<Map<String, Object>> RoomDetail_Select(Map<String, Object> paramMap);

	/**
	 * 获取项目房源信息(营销经理)
	 */
	List<Map<String, Object>> RoomDetailYXJL_Select(Map<String, Object> paramMap);

}
