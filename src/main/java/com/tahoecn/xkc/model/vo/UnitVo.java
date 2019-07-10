package com.tahoecn.xkc.model.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
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
    private List<FrVo> RoomFloorList;
    @JsonProperty("RoomFloorObj")
    private Map<String, FrVo> RoomFloorObj;

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
