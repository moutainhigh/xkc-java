package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UnitVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String RoomUnit;
	private int RoomMaxCount;
	private List<FrVo> RoomFloorList;
	private Map<String,FrVo> RoomFloorObj;
	public String getRoomUnit() {
		return RoomUnit;
	}
	public void setRoomUnit(String roomUnit) {
		RoomUnit = roomUnit;
	}
	public int getRoomMaxCount() {
		return RoomMaxCount;
	}
	public void setRoomMaxCount(int roomMaxCount) {
		RoomMaxCount = roomMaxCount;
	}
	public List<FrVo> getRoomFloorList() {
		return RoomFloorList;
	}
	public void setRoomFloorList(List<FrVo> roomFloorList) {
		RoomFloorList = roomFloorList;
	}
	public Map<String, FrVo> getRoomFloorObj() {
		return RoomFloorObj;
	}
	public void setRoomFloorObj(Map<String, FrVo> roomFloorObj) {
		RoomFloorObj = roomFloorObj;
	}
	
}
