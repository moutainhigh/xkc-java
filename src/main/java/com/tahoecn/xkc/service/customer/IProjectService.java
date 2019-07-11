package com.tahoecn.xkc.service.customer;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.converter.Result;

public interface IProjectService {
	
	public Result Detail_FindById(String ID);
	
	public Result BuildingDetailByProjectIDTop_Select(JSONObject Parameter);
	
	public Result BuildingDetail_Select(JSONObject Parameter);
	
	public Result RoomList_Select(JSONObject Parameter);
}
