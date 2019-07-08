package com.tahoecn.xkc.mapper.project;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.VProjectroom;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface VProjectroomMapper extends BaseMapper<VProjectroom> {
	/**
	 * 获取项目房间列表信息
	 */
	List<Map<String, Object>> ProjectRoomList_Select(Map<String,Object> map);

}
