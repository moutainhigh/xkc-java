package com.tahoecn.xkc.model.project;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
@TableName("V_ProjectRoom")
@ApiModel(value="VProjectroom对象", description="")
public class VProjectroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("RoomID")
    private String RoomID;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("BuildingID")
    private String BuildingID;

    @TableField("BuildingName")
    private String BuildingName;

    @TableField("RoomSaleStatus")
    private Integer RoomSaleStatus;

    @TableField("RoomStatus")
    private Integer RoomStatus;

    @TableField("RoomStatusName")
    private String RoomStatusName;

    @TableField("FloorIndex")
    private Integer FloorIndex;

    @TableField("RoomIndex")
    private String RoomIndex;

    @TableField("FloorNum")
    private Integer FloorNum;

    @TableField("RoomFloorName")
    private String RoomFloorName;

    @TableField("RoomUnit")
    private String RoomUnit;

    @TableField("RoomName")
    private String RoomName;

    @TableField("RoomAreaV")
    private BigDecimal RoomAreaV;

    @TableField("RoomArea")
    private String RoomArea;

    @TableField("RoomPrice")
    private String RoomPrice;

    @TableField("RoomTotal")
    private String RoomTotal;

    @TableField("RoomTotalV")
    private BigDecimal RoomTotalV;

    @TableField("RoomType")
    private String RoomType;

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String RoomID) {
        this.RoomID = RoomID;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }
    public String getBuildingID() {
        return BuildingID;
    }

    public void setBuildingID(String BuildingID) {
        this.BuildingID = BuildingID;
    }
    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String BuildingName) {
        this.BuildingName = BuildingName;
    }
    public Integer getRoomSaleStatus() {
        return RoomSaleStatus;
    }

    public void setRoomSaleStatus(Integer RoomSaleStatus) {
        this.RoomSaleStatus = RoomSaleStatus;
    }
    public Integer getRoomStatus() {
        return RoomStatus;
    }

    public void setRoomStatus(Integer RoomStatus) {
        this.RoomStatus = RoomStatus;
    }
    public String getRoomStatusName() {
        return RoomStatusName;
    }

    public void setRoomStatusName(String RoomStatusName) {
        this.RoomStatusName = RoomStatusName;
    }
    public Integer getFloorIndex() {
        return FloorIndex;
    }

    public void setFloorIndex(Integer FloorIndex) {
        this.FloorIndex = FloorIndex;
    }
    public String getRoomIndex() {
        return RoomIndex;
    }

    public void setRoomIndex(String RoomIndex) {
        this.RoomIndex = RoomIndex;
    }
    public Integer getFloorNum() {
        return FloorNum;
    }

    public void setFloorNum(Integer FloorNum) {
        this.FloorNum = FloorNum;
    }
    public String getRoomFloorName() {
        return RoomFloorName;
    }

    public void setRoomFloorName(String RoomFloorName) {
        this.RoomFloorName = RoomFloorName;
    }
    public String getRoomUnit() {
        return RoomUnit;
    }

    public void setRoomUnit(String RoomUnit) {
        this.RoomUnit = RoomUnit;
    }
    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }
    public BigDecimal getRoomAreaV() {
        return RoomAreaV;
    }

    public void setRoomAreaV(BigDecimal RoomAreaV) {
        this.RoomAreaV = RoomAreaV;
    }
    public String getRoomArea() {
        return RoomArea;
    }

    public void setRoomArea(String RoomArea) {
        this.RoomArea = RoomArea;
    }
    public String getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(String RoomPrice) {
        this.RoomPrice = RoomPrice;
    }
    public String getRoomTotal() {
        return RoomTotal;
    }

    public void setRoomTotal(String RoomTotal) {
        this.RoomTotal = RoomTotal;
    }
    public BigDecimal getRoomTotalV() {
        return RoomTotalV;
    }

    public void setRoomTotalV(BigDecimal RoomTotalV) {
        this.RoomTotalV = RoomTotalV;
    }
    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String RoomType) {
        this.RoomType = RoomType;
    }

    @Override
    public String toString() {
        return "VProjectroom{" +
        "RoomID=" + RoomID +
        ", ProjectID=" + ProjectID +
        ", ProjectName=" + ProjectName +
        ", BuildingID=" + BuildingID +
        ", BuildingName=" + BuildingName +
        ", RoomSaleStatus=" + RoomSaleStatus +
        ", RoomStatus=" + RoomStatus +
        ", RoomStatusName=" + RoomStatusName +
        ", FloorIndex=" + FloorIndex +
        ", RoomIndex=" + RoomIndex +
        ", FloorNum=" + FloorNum +
        ", RoomFloorName=" + RoomFloorName +
        ", RoomUnit=" + RoomUnit +
        ", RoomName=" + RoomName +
        ", RoomAreaV=" + RoomAreaV +
        ", RoomArea=" + RoomArea +
        ", RoomPrice=" + RoomPrice +
        ", RoomTotal=" + RoomTotal +
        ", RoomTotalV=" + RoomTotalV +
        ", RoomType=" + RoomType +
        "}";
    }
}
