package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("RoomFloorName")
	private String RoomFloorName;
	@JsonProperty("RoomList")
	private List<Map<String,Object>> RoomList;
	public String getRoomFloorName() {
		return RoomFloorName;
	}
	public void setRoomFloorName(String roomFloorName) {
		RoomFloorName = roomFloorName;
	}
	public List<Map<String, Object>> getRoomList() {
		return RoomList;
	}
	public void setRoomList(List<Map<String, Object>> RoomList) {
		this.RoomList = RoomList;
	}
}
