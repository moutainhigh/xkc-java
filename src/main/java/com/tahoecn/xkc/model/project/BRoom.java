package com.tahoecn.xkc.model.project;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
@TableName("B_Room")
@ApiModel(value="BRoom对象", description="")
public class BRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("BuildingID")
    private String BuildingID;

    @TableField("Unit")
    private String Unit;

    @TableField("AbsolutelyFloor")
    private String AbsolutelyFloor;

    @TableField("Floor")
    private String Floor;

    @TableField("No")
    private String No;

    @TableField("Room")
    private String Room;

    @TableField("RoomCode")
    private String RoomCode;

    @TableField("HuXing")
    private String HuXing;

    @TableField("SaleStatus")
    private String SaleStatus;

    @TableField("BldArea")
    private BigDecimal BldArea;

    @TableField("TnArea")
    private BigDecimal TnArea;

    @TableField("YsBldArea")
    private BigDecimal YsBldArea;

    @TableField("YsTnArea")
    private BigDecimal YsTnArea;

    @TableField("ScBldArea")
    private BigDecimal ScBldArea;

    @TableField("ScTnArea")
    private BigDecimal ScTnArea;

    @TableField("AreaStatus")
    private String AreaStatus;

    @TableField("DjArea")
    private String DjArea;

    @TableField("Price")
    private BigDecimal Price;

    @TableField("TnPrice")
    private BigDecimal TnPrice;

    @TableField("Total")
    private BigDecimal Total;

    @TableField("RoomStru")
    private String RoomStru;

    @TableField("West")
    private String West;

    @TableField("BProductTypeCode")
    private String BProductTypeCode;

    @TableField("SpLock")
    private Integer SpLock;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("IsBind")
    private Integer IsBind;

    @TableField("IsValidComm")
    private Integer IsValidComm;

    @TableField("IsAudit")
    private Integer IsAudit;

    @TableField("IsWarning")
    private Integer IsWarning;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getBuildingID() {
        return BuildingID;
    }

    public void setBuildingID(String BuildingID) {
        this.BuildingID = BuildingID;
    }
    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }
    public String getAbsolutelyFloor() {
        return AbsolutelyFloor;
    }

    public void setAbsolutelyFloor(String AbsolutelyFloor) {
        this.AbsolutelyFloor = AbsolutelyFloor;
    }
    public String getFloor() {
        return Floor;
    }

    public void setFloor(String Floor) {
        this.Floor = Floor;
    }
    public String getNo() {
        return No;
    }

    public void setNo(String No) {
        this.No = No;
    }
    public String getRoom() {
        return Room;
    }

    public void setRoom(String Room) {
        this.Room = Room;
    }
    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String RoomCode) {
        this.RoomCode = RoomCode;
    }
    public String getHuXing() {
        return HuXing;
    }

    public void setHuXing(String HuXing) {
        this.HuXing = HuXing;
    }
    public String getSaleStatus() {
        return SaleStatus;
    }

    public void setSaleStatus(String SaleStatus) {
        this.SaleStatus = SaleStatus;
    }
    public BigDecimal getBldArea() {
        return BldArea;
    }

    public void setBldArea(BigDecimal BldArea) {
        this.BldArea = BldArea;
    }
    public BigDecimal getTnArea() {
        return TnArea;
    }

    public void setTnArea(BigDecimal TnArea) {
        this.TnArea = TnArea;
    }
    public BigDecimal getYsBldArea() {
        return YsBldArea;
    }

    public void setYsBldArea(BigDecimal YsBldArea) {
        this.YsBldArea = YsBldArea;
    }
    public BigDecimal getYsTnArea() {
        return YsTnArea;
    }

    public void setYsTnArea(BigDecimal YsTnArea) {
        this.YsTnArea = YsTnArea;
    }
    public BigDecimal getScBldArea() {
        return ScBldArea;
    }

    public void setScBldArea(BigDecimal ScBldArea) {
        this.ScBldArea = ScBldArea;
    }
    public BigDecimal getScTnArea() {
        return ScTnArea;
    }

    public void setScTnArea(BigDecimal ScTnArea) {
        this.ScTnArea = ScTnArea;
    }
    public String getAreaStatus() {
        return AreaStatus;
    }

    public void setAreaStatus(String AreaStatus) {
        this.AreaStatus = AreaStatus;
    }
    public String getDjArea() {
        return DjArea;
    }

    public void setDjArea(String DjArea) {
        this.DjArea = DjArea;
    }
    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }
    public BigDecimal getTnPrice() {
        return TnPrice;
    }

    public void setTnPrice(BigDecimal TnPrice) {
        this.TnPrice = TnPrice;
    }
    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }
    public String getRoomStru() {
        return RoomStru;
    }

    public void setRoomStru(String RoomStru) {
        this.RoomStru = RoomStru;
    }
    public String getWest() {
        return West;
    }

    public void setWest(String West) {
        this.West = West;
    }
    public String getBProductTypeCode() {
        return BProductTypeCode;
    }

    public void setBProductTypeCode(String BProductTypeCode) {
        this.BProductTypeCode = BProductTypeCode;
    }
    public Integer getSpLock() {
        return SpLock;
    }

    public void setSpLock(Integer SpLock) {
        this.SpLock = SpLock;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getIsBind() {
        return IsBind;
    }

    public void setIsBind(Integer IsBind) {
        this.IsBind = IsBind;
    }
    public Integer getIsValidComm() {
        return IsValidComm;
    }

    public void setIsValidComm(Integer IsValidComm) {
        this.IsValidComm = IsValidComm;
    }
    public Integer getIsAudit() {
        return IsAudit;
    }

    public void setIsAudit(Integer IsAudit) {
        this.IsAudit = IsAudit;
    }
    public Integer getIsWarning() {
        return IsWarning;
    }

    public void setIsWarning(Integer IsWarning) {
        this.IsWarning = IsWarning;
    }

    @Override
    public String toString() {
        return "BRoom{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", BuildingID=" + BuildingID +
        ", Unit=" + Unit +
        ", AbsolutelyFloor=" + AbsolutelyFloor +
        ", Floor=" + Floor +
        ", No=" + No +
        ", Room=" + Room +
        ", RoomCode=" + RoomCode +
        ", HuXing=" + HuXing +
        ", SaleStatus=" + SaleStatus +
        ", BldArea=" + BldArea +
        ", TnArea=" + TnArea +
        ", YsBldArea=" + YsBldArea +
        ", YsTnArea=" + YsTnArea +
        ", ScBldArea=" + ScBldArea +
        ", ScTnArea=" + ScTnArea +
        ", AreaStatus=" + AreaStatus +
        ", DjArea=" + DjArea +
        ", Price=" + Price +
        ", TnPrice=" + TnPrice +
        ", Total=" + Total +
        ", RoomStru=" + RoomStru +
        ", West=" + West +
        ", BProductTypeCode=" + BProductTypeCode +
        ", SpLock=" + SpLock +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", IsBind=" + IsBind +
        ", IsValidComm=" + IsValidComm +
        ", IsAudit=" + IsAudit +
        ", IsWarning=" + IsWarning +
        "}";
    }
}
