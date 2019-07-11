package com.tahoecn.xkc.service.customer.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.customer.VCustomergwlistSelectMapper;
import com.tahoecn.xkc.service.customer.IProjectService;

@Service
public class ProjectService implements IProjectService {
	
	@Resource
	private VCustomergwlistSelectMapper vCustomergwlistSelectMapper;
	
	@Override
	public Result Detail_FindById(String ID) {
		Result re = new Result();
        Map<String,Object> map = vCustomergwlistSelectMapper.Project_Detail_FindById(ID);
        re.setData(new JSONObject(map));
        return re;
	}

	@Override
	public Result BuildingDetailByProjectIDTop_Select(JSONObject Parameter) {
		Result re = new Result();
		try {
			String projectID = Parameter.getString("ProjectID");
			Map<String, Object> data = vCustomergwlistSelectMapper.ProjectBuildingDetailByProjectIDTop_Select(projectID);
			if(data.size()>0){
				re.setData(data);
				re.setErrcode(0);
				re.setErrmsg("成功");
			}else{
				re.setErrcode(99);
				re.setErrmsg("失败");
			}
		} catch (Exception e) {
			re.setErrcode(99);
			re.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result BuildingDetail_Select(JSONObject Parameter) {
		Result re = new Result();
		try {
			String buildingID = Parameter.getString("BuildingID");
			Map<String, Object> data = vCustomergwlistSelectMapper.ProjectBuildingDetail_Select(buildingID);
			if(data.size()>0){
				re.setData(data);
				re.setErrcode(0);
				re.setErrmsg("成功");
			}else{
				re.setErrcode(99);
				re.setErrmsg("失败");
			}
		} catch (Exception e) {
			re.setErrcode(99);
			re.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return re;
	}

	@Override
	public Result RoomList_Select(JSONObject Parameter) {
		Result re = new Result();
		try {
			String RoomTotalStart = Parameter.getString("RoomTotalStart");
	        String RoomTotalEnd = Parameter.getString("RoomTotalEnd");
	        String RoomAreaStart = Parameter.getString("RoomAreaStart");
	        String RoomAreaEnd = Parameter.getString("RoomAreaEnd");
	        String RoomSaleStatus = Parameter.getString("RoomSaleStatus");
	        String RoomType = Parameter.getString("RoomType");
	        String Where = "";
	        if (!StringUtils.isEmpty(RoomTotalStart) && !StringUtils.isEmpty(RoomTotalEnd)){
	            Where += String.format(" AND RoomTotalV>='%s' AND RoomTotalV<='%s'", RoomTotalStart, RoomTotalEnd);
	        }
	        if (!StringUtils.isEmpty(RoomAreaStart) && !StringUtils.isEmpty(RoomAreaEnd)){
	            Where += String.format(" AND RoomAreaV>='%s' AND RoomAreaV<='%s'", RoomAreaStart, RoomAreaEnd);
	        }
	        if (!StringUtils.isEmpty(RoomSaleStatus)){
	            Where += String.format(" AND RoomSaleStatus='%s'", RoomSaleStatus);
	        }
	        if (!StringUtils.isEmpty(RoomType)){
	            Where += String.format(" AND RoomType='%s'", RoomType);
	        }
	        String buildingID = Parameter.getString("BuildingID");
	        List<Map<String,Object>> data = vCustomergwlistSelectMapper.ProjectRoomList_Select(buildingID, Where);
	        if(data.size()>0){
				re.setData(data);
				re.setErrcode(0);
				re.setErrmsg("成功");
			}else{
				re.setErrcode(99);
				re.setErrmsg("失败");
			}
		} catch (Exception e) {
			re.setErrcode(99);
			re.setErrmsg("系统异常");
			e.printStackTrace();
		}
        return re;
	}
	

}
