package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.BRoomMapper;
import com.tahoecn.xkc.model.project.BRoom;
import com.tahoecn.xkc.service.project.IBRoomService;

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
 * @since 2019-07-08
 */
@Service
public class BRoomServiceImpl extends ServiceImpl<BRoomMapper, BRoom> implements IBRoomService {
	@Autowired
	private BRoomMapper bRoomMapper;
	/**
	 * 获取项目房源信息
	 */
	@Override
	public List<Map<String, Object>> RoomDetail_Select(Map<String, Object> paramMap) {
		return bRoomMapper.ProjectRoomDetail_Select(paramMap);
	}
	/**
	 * 获取项目房源信息(营销经理)
	 */
	@Override
	public List<Map<String, Object>> RoomDetailYXJL_Select(Map<String, Object> paramMap) {
		return bRoomMapper.ProjectRoomDetailYXJL_Select(paramMap);
	}

}
