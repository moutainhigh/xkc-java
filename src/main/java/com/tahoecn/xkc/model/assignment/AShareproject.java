package com.tahoecn.xkc.model.assignment;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-01-06
 */
@TableName("A_ShareProject")
@ApiModel(value="AShareproject对象", description="")
public class AShareproject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("HotDesc")
    private String HotDesc;

    @TableField("FollowNum")
    private Integer FollowNum;

    @TableField("Name")
    private String Name;

    @TableField("Characteristic")
    private String Characteristic;

    @TableField("ProductPriceAvg")
    private String ProductPriceAvg;

    @TableField("ProductPriceTotal")
    private String ProductPriceTotal;

    @TableField("HouseAreaMin")
    private String HouseAreaMin;

    @TableField("HouseAreaMax")
    private String HouseAreaMax;

    @TableField("PropertyCompany")
    private String PropertyCompany;

    @TableField("PropertyCompanyType")
    private String PropertyCompanyType;

    @TableField("ProductType")
    private String ProductType;

    @TableField("DecorationStatus")
    private String DecorationStatus;

    @TableField("PropertyRightYear")
    private String PropertyRightYear;

    @TableField("DevelopersName")
    private String DevelopersName;

    @TableField("ProvinceID")
    private String ProvinceID;

    @TableField("CityID")
    private String CityID;

    @TableField("Area")
    private String Area;

    @TableField("Address")
    private String Address;

    @TableField("Tel")
    private String Tel;

    @ApiModelProperty(value = "1待开盘2开盘3售罄4已入住")
    @TableField("SellStatus")
    private Integer SellStatus;

    @TableField("SellHouseType")
    private String SellHouseType;

    @TableField("StartTime")
    private Date StartTime;

    @TableField("EndTime")
    private Date EndTime;

    @TableField("SellAddress")
    private String SellAddress;

    @TableField("CoveredArea")
    private String CoveredArea;

    @TableField("BuildArea")
    private String BuildArea;

    @TableField("VolumeRate")
    private String VolumeRate;

    @TableField("GreenRate")
    private String GreenRate;

    @TableField("ParkingRatio")
    private String ParkingRatio;

    @TableField("PlanningBuilding")
    private String PlanningBuilding;

    @TableField("WaterSupplyType")
    private String WaterSupplyType;

    @TableField("PowerSupplyType")
    private String PowerSupplyType;

    @TableField("PlanningHouseholds")
    private String PlanningHouseholds;

    @TableField("PropertyFee")
    private String PropertyFee;

    @TableField("ProjectProgress")
    private String ProjectProgress;

    @ApiModelProperty(value = "楼盘纬度")
    @TableField("ProjectLocationX")
    private String ProjectLocationX;

    @ApiModelProperty(value = "楼盘经度")
    @TableField("ProjectLocationY")
    private String ProjectLocationY;

    @ApiModelProperty(value = "售楼处纬度")
    @TableField("SellLocationX")
    private String SellLocationX;

    @ApiModelProperty(value = "售楼处经度")
    @TableField("SellLocationY")
    private String SellLocationY;

    @TableField("Intro")
    private String Intro;

    @TableField("RecommonedReason")
    private String RecommonedReason;

    @TableField("IsShowProjectBasicInfo")
    private Integer IsShowProjectBasicInfo;

    @TableField("IsShowProjectIntro")
    private Integer IsShowProjectIntro;

    @TableField("IsShowProjectRecommonedReason")
    private Integer IsShowProjectRecommonedReason;

    @TableField("RecommendImg")
    private String RecommendImg;

    @TableField("DetailsImg")
    private String DetailsImg;

    @TableField("ActivityRulesDesc")
    private String ActivityRulesDesc;

    @TableField("SandTableImg")
    private String SandTableImg;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("ShareNum")
    private Integer ShareNum;

    @TableField("VRUrl")
    private String VRUrl;

    @TableField("AddressImg")
    private String AddressImg;

    @TableField("OnlineLittleBookingImg")
    private String OnlineLittleBookingImg;

    @TableField("ProjectCostImg")
    private String ProjectCostImg;

    @TableField("Approver")
    private String Approver;

    @TableField("ApprovalStatus")
    private Integer ApprovalStatus;

    @TableField("ApprovalDate")
    private Date ApprovalDate;

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
    public String getHotDesc() {
        return HotDesc;
    }

    public void setHotDesc(String HotDesc) {
        this.HotDesc = HotDesc;
    }
    public Integer getFollowNum() {
        return FollowNum;
    }

    public void setFollowNum(Integer FollowNum) {
        this.FollowNum = FollowNum;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getCharacteristic() {
        return Characteristic;
    }

    public void setCharacteristic(String Characteristic) {
        this.Characteristic = Characteristic;
    }
    public String getProductPriceAvg() {
        return ProductPriceAvg;
    }

    public void setProductPriceAvg(String ProductPriceAvg) {
        this.ProductPriceAvg = ProductPriceAvg;
    }
    public String getProductPriceTotal() {
        return ProductPriceTotal;
    }

    public void setProductPriceTotal(String ProductPriceTotal) {
        this.ProductPriceTotal = ProductPriceTotal;
    }
    public String getHouseAreaMin() {
        return HouseAreaMin;
    }

    public void setHouseAreaMin(String HouseAreaMin) {
        this.HouseAreaMin = HouseAreaMin;
    }
    public String getHouseAreaMax() {
        return HouseAreaMax;
    }

    public void setHouseAreaMax(String HouseAreaMax) {
        this.HouseAreaMax = HouseAreaMax;
    }
    public String getPropertyCompany() {
        return PropertyCompany;
    }

    public void setPropertyCompany(String PropertyCompany) {
        this.PropertyCompany = PropertyCompany;
    }
    public String getPropertyCompanyType() {
        return PropertyCompanyType;
    }

    public void setPropertyCompanyType(String PropertyCompanyType) {
        this.PropertyCompanyType = PropertyCompanyType;
    }
    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }
    public String getDecorationStatus() {
        return DecorationStatus;
    }

    public void setDecorationStatus(String DecorationStatus) {
        this.DecorationStatus = DecorationStatus;
    }
    public String getPropertyRightYear() {
        return PropertyRightYear;
    }

    public void setPropertyRightYear(String PropertyRightYear) {
        this.PropertyRightYear = PropertyRightYear;
    }
    public String getDevelopersName() {
        return DevelopersName;
    }

    public void setDevelopersName(String DevelopersName) {
        this.DevelopersName = DevelopersName;
    }
    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String ProvinceID) {
        this.ProvinceID = ProvinceID;
    }
    public String getCityID() {
        return CityID;
    }

    public void setCityID(String CityID) {
        this.CityID = CityID;
    }
    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }
    public Integer getSellStatus() {
        return SellStatus;
    }

    public void setSellStatus(Integer SellStatus) {
        this.SellStatus = SellStatus;
    }
    public String getSellHouseType() {
        return SellHouseType;
    }

    public void setSellHouseType(String SellHouseType) {
        this.SellHouseType = SellHouseType;
    }
    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }
    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }
    public String getSellAddress() {
        return SellAddress;
    }

    public void setSellAddress(String SellAddress) {
        this.SellAddress = SellAddress;
    }
    public String getCoveredArea() {
        return CoveredArea;
    }

    public void setCoveredArea(String CoveredArea) {
        this.CoveredArea = CoveredArea;
    }
    public String getBuildArea() {
        return BuildArea;
    }

    public void setBuildArea(String BuildArea) {
        this.BuildArea = BuildArea;
    }
    public String getVolumeRate() {
        return VolumeRate;
    }

    public void setVolumeRate(String VolumeRate) {
        this.VolumeRate = VolumeRate;
    }
    public String getGreenRate() {
        return GreenRate;
    }

    public void setGreenRate(String GreenRate) {
        this.GreenRate = GreenRate;
    }
    public String getParkingRatio() {
        return ParkingRatio;
    }

    public void setParkingRatio(String ParkingRatio) {
        this.ParkingRatio = ParkingRatio;
    }
    public String getPlanningBuilding() {
        return PlanningBuilding;
    }

    public void setPlanningBuilding(String PlanningBuilding) {
        this.PlanningBuilding = PlanningBuilding;
    }
    public String getWaterSupplyType() {
        return WaterSupplyType;
    }

    public void setWaterSupplyType(String WaterSupplyType) {
        this.WaterSupplyType = WaterSupplyType;
    }
    public String getPowerSupplyType() {
        return PowerSupplyType;
    }

    public void setPowerSupplyType(String PowerSupplyType) {
        this.PowerSupplyType = PowerSupplyType;
    }
    public String getPlanningHouseholds() {
        return PlanningHouseholds;
    }

    public void setPlanningHouseholds(String PlanningHouseholds) {
        this.PlanningHouseholds = PlanningHouseholds;
    }
    public String getPropertyFee() {
        return PropertyFee;
    }

    public void setPropertyFee(String PropertyFee) {
        this.PropertyFee = PropertyFee;
    }
    public String getProjectProgress() {
        return ProjectProgress;
    }

    public void setProjectProgress(String ProjectProgress) {
        this.ProjectProgress = ProjectProgress;
    }
    public String getProjectLocationX() {
        return ProjectLocationX;
    }

    public void setProjectLocationX(String ProjectLocationX) {
        this.ProjectLocationX = ProjectLocationX;
    }
    public String getProjectLocationY() {
        return ProjectLocationY;
    }

    public void setProjectLocationY(String ProjectLocationY) {
        this.ProjectLocationY = ProjectLocationY;
    }
    public String getSellLocationX() {
        return SellLocationX;
    }

    public void setSellLocationX(String SellLocationX) {
        this.SellLocationX = SellLocationX;
    }
    public String getSellLocationY() {
        return SellLocationY;
    }

    public void setSellLocationY(String SellLocationY) {
        this.SellLocationY = SellLocationY;
    }
    public String getIntro() {
        return Intro;
    }

    public void setIntro(String Intro) {
        this.Intro = Intro;
    }
    public String getRecommonedReason() {
        return RecommonedReason;
    }

    public void setRecommonedReason(String RecommonedReason) {
        this.RecommonedReason = RecommonedReason;
    }
    public Integer getIsShowProjectBasicInfo() {
        return IsShowProjectBasicInfo;
    }

    public void setIsShowProjectBasicInfo(Integer IsShowProjectBasicInfo) {
        this.IsShowProjectBasicInfo = IsShowProjectBasicInfo;
    }
    public Integer getIsShowProjectIntro() {
        return IsShowProjectIntro;
    }

    public void setIsShowProjectIntro(Integer IsShowProjectIntro) {
        this.IsShowProjectIntro = IsShowProjectIntro;
    }
    public Integer getIsShowProjectRecommonedReason() {
        return IsShowProjectRecommonedReason;
    }

    public void setIsShowProjectRecommonedReason(Integer IsShowProjectRecommonedReason) {
        this.IsShowProjectRecommonedReason = IsShowProjectRecommonedReason;
    }
    public String getRecommendImg() {
        return RecommendImg;
    }

    public void setRecommendImg(String RecommendImg) {
        this.RecommendImg = RecommendImg;
    }
    public String getDetailsImg() {
        return DetailsImg;
    }

    public void setDetailsImg(String DetailsImg) {
        this.DetailsImg = DetailsImg;
    }
    public String getActivityRulesDesc() {
        return ActivityRulesDesc;
    }

    public void setActivityRulesDesc(String ActivityRulesDesc) {
        this.ActivityRulesDesc = ActivityRulesDesc;
    }
    public String getSandTableImg() {
        return SandTableImg;
    }

    public void setSandTableImg(String SandTableImg) {
        this.SandTableImg = SandTableImg;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
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
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
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
    public Integer getShareNum() {
        return ShareNum;
    }

    public void setShareNum(Integer ShareNum) {
        this.ShareNum = ShareNum;
    }
    public String getVRUrl() {
        return VRUrl;
    }

    public void setVRUrl(String VRUrl) {
        this.VRUrl = VRUrl;
    }
    public String getAddressImg() {
        return AddressImg;
    }

    public void setAddressImg(String AddressImg) {
        this.AddressImg = AddressImg;
    }
    public String getOnlineLittleBookingImg() {
        return OnlineLittleBookingImg;
    }

    public void setOnlineLittleBookingImg(String OnlineLittleBookingImg) {
        this.OnlineLittleBookingImg = OnlineLittleBookingImg;
    }
    public String getProjectCostImg() {
        return ProjectCostImg;
    }

    public void setProjectCostImg(String ProjectCostImg) {
        this.ProjectCostImg = ProjectCostImg;
    }
    public String getApprover() {
        return Approver;
    }

    public void setApprover(String Approver) {
        this.Approver = Approver;
    }
    public Integer getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(Integer ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }
    public Date getApprovalDate() {
        return ApprovalDate;
    }

    public void setApprovalDate(Date ApprovalDate) {
        this.ApprovalDate = ApprovalDate;
    }

    @Override
    public String toString() {
        return "AShareproject{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", HotDesc=" + HotDesc +
        ", FollowNum=" + FollowNum +
        ", Name=" + Name +
        ", Characteristic=" + Characteristic +
        ", ProductPriceAvg=" + ProductPriceAvg +
        ", ProductPriceTotal=" + ProductPriceTotal +
        ", HouseAreaMin=" + HouseAreaMin +
        ", HouseAreaMax=" + HouseAreaMax +
        ", PropertyCompany=" + PropertyCompany +
        ", PropertyCompanyType=" + PropertyCompanyType +
        ", ProductType=" + ProductType +
        ", DecorationStatus=" + DecorationStatus +
        ", PropertyRightYear=" + PropertyRightYear +
        ", DevelopersName=" + DevelopersName +
        ", ProvinceID=" + ProvinceID +
        ", CityID=" + CityID +
        ", Area=" + Area +
        ", Address=" + Address +
        ", Tel=" + Tel +
        ", SellStatus=" + SellStatus +
        ", SellHouseType=" + SellHouseType +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", SellAddress=" + SellAddress +
        ", CoveredArea=" + CoveredArea +
        ", BuildArea=" + BuildArea +
        ", VolumeRate=" + VolumeRate +
        ", GreenRate=" + GreenRate +
        ", ParkingRatio=" + ParkingRatio +
        ", PlanningBuilding=" + PlanningBuilding +
        ", WaterSupplyType=" + WaterSupplyType +
        ", PowerSupplyType=" + PowerSupplyType +
        ", PlanningHouseholds=" + PlanningHouseholds +
        ", PropertyFee=" + PropertyFee +
        ", ProjectProgress=" + ProjectProgress +
        ", ProjectLocationX=" + ProjectLocationX +
        ", ProjectLocationY=" + ProjectLocationY +
        ", SellLocationX=" + SellLocationX +
        ", SellLocationY=" + SellLocationY +
        ", Intro=" + Intro +
        ", RecommonedReason=" + RecommonedReason +
        ", IsShowProjectBasicInfo=" + IsShowProjectBasicInfo +
        ", IsShowProjectIntro=" + IsShowProjectIntro +
        ", IsShowProjectRecommonedReason=" + IsShowProjectRecommonedReason +
        ", RecommendImg=" + RecommendImg +
        ", DetailsImg=" + DetailsImg +
        ", ActivityRulesDesc=" + ActivityRulesDesc +
        ", SandTableImg=" + SandTableImg +
        ", ListIndex=" + ListIndex +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", ShareNum=" + ShareNum +
        ", VRUrl=" + VRUrl +
        ", AddressImg=" + AddressImg +
        ", OnlineLittleBookingImg=" + OnlineLittleBookingImg +
        ", ProjectCostImg=" + ProjectCostImg +
        ", Approver=" + Approver +
        ", ApprovalStatus=" + ApprovalStatus +
        ", ApprovalDate=" + ApprovalDate +
        "}";
    }
}
