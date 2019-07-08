package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.VProjectroomMapper;
import com.tahoecn.xkc.model.project.VProjectroom;
import com.tahoecn.xkc.service.project.IVProjectroomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@Service
public class VProjectroomServiceImpl extends ServiceImpl<VProjectroomMapper, VProjectroom> implements IVProjectroomService {

	@Autowired 
	private VProjectroomMapper vProjectroomMapper;
	/**
	 * 获取项目房间列表信息
	 */
	@Override
	public List<Map<String, Object>> RoomList_Select(Map<String, Object> paramMap) {
		String BuildingID = (String) paramMap.get("BuildingID");
        String RoomTotalStart = (String) paramMap.get("RoomTotalStart");
        String RoomTotalEnd = (String) paramMap.get("RoomTotalEnd");
        String RoomAreaStart = (String) paramMap.get("RoomAreaStart");
        String RoomAreaEnd = (String) paramMap.get("RoomAreaEnd");
        String RoomSaleStatus = (String) paramMap.get("RoomSaleStatus");
        String RoomType = (String) paramMap.get("RoomType");
        String sqlWhere = "";
        if (!StringUtils.isEmpty(RoomTotalStart) && !StringUtils.isEmpty(RoomTotalEnd)){
        	sqlWhere += String.format(" AND RoomTotalV>='{0}' AND RoomTotalV<='{1}'", RoomTotalStart, RoomTotalEnd);
        }
        if (!StringUtils.isEmpty(RoomAreaStart) && !StringUtils.isEmpty(RoomAreaEnd)){
        	sqlWhere += String.format(" AND RoomAreaV>='{0}' AND RoomAreaV<='{1}'", RoomAreaStart, RoomAreaEnd);
        }
        if (!StringUtils.isEmpty(RoomSaleStatus)){
        	sqlWhere += String.format(" AND RoomSaleStatus='{0}'", RoomSaleStatus);
        }
        if (!StringUtils.isEmpty(RoomType)){
        	sqlWhere += String.format(" AND RoomType='{0}'", RoomType);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sqlWhere", sqlWhere);
        map.put("BuildingID", BuildingID);
        return vProjectroomMapper.ProjectRoomList_Select(map);
	}

}
