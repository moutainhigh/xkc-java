package com.tahoecn.xkc.mapper.project;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.BRoom;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface BRoomMapper extends BaseMapper<BRoom> {

	/**
	 * 获取项目房源信息
	 */
	List<Map<String, Object>> ProjectRoomDetail_Select(Map<String, Object> paramMap);
	/**
	 * 获取项目房源信息(营销经理)
	 */
	List<Map<String, Object>> ProjectRoomDetailYXJL_Select(Map<String, Object> paramMap);

}
