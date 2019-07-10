package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String RoomFloorName;
	private List<Map<String,Object>> RoomList;
	
	@JsonProperty("RoomFloorName")
	public String getRoomFloorName() {
		return RoomFloorName;
	}
	public void setRoomFloorName(String roomFloorName) {
		RoomFloorName = roomFloorName;
	}
	@JsonProperty("RoomList")
	public List<Map<String, Object>> getRoomList() {
		return RoomList;
	}
	public void setRoomList(List<Map<String, Object>> RoomList) {
		this.RoomList = RoomList;
	}
}
