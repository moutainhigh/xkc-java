package com.tahoecn.xkc.model.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class UnitVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("RoomUnit")
    private String RoomUnit;
    @JsonProperty("RoomMaxCount")
    private int RoomMaxCount;
    @JsonProperty("RoomFloorList")
    private LinkedList<FrVo> RoomFloorList;
    @JsonProperty("RoomFloorObj")
    private LinkedHashMap<String, FrVo> RoomFloorObj;

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

    public void setRoomFloorList(LinkedList<FrVo> roomFloorList) {
        RoomFloorList = roomFloorList;
    }

    public Map<String, FrVo> getRoomFloorObj() {
        return RoomFloorObj;
    }

    public void setRoomFloorObj(LinkedHashMap<String, FrVo> roomFloorObj) {
        RoomFloorObj = roomFloorObj;
    }

}
