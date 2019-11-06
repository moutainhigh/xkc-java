package com.tahoecn.xkc.model.project;

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
 * @since 2019-07-01
 */
@TableName("A_BrokerProject")
@ApiModel(value="ABrokerproject对象", description="")
public class ABrokerproject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否显示项目详情")
    @TableField("IsShowProjectDetail")
    private Integer IsShowProjectDetail;

    @ApiModelProperty(value = "是否显示基本信息")
    @TableField("IsShowProjectBasicInfo")
    private Integer IsShowProjectBasicInfo;

    @ApiModelProperty(value = "是否显示项目简介")
    @TableField("IsShowProjectIntro")
    private Integer IsShowProjectIntro;

    @ApiModelProperty(value = "是否显示推荐理由")
    @TableField("IsShowProjectRecommonedReason")
    private Integer IsShowProjectRecommonedReason;

    @ApiModelProperty(value = "是否显示周边配套")
    @TableField("IsShowProjectAroundAssort")
    private Integer IsShowProjectAroundAssort;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("CityID")
    private String CityID;

    @TableField("RecommonedReason")
    private String RecommonedReason;

    @TableField("Creator")
    private String Creator;

    @TableField("CommisionRate")
    private String CommisionRate;

    @TableField("ProductPriceAvg")
    private String ProductPriceAvg;

    @TableField("LocationX")
    private String LocationX;

    @TableField("Tel")
    private String Tel;

    @TableField("Address")
    private String Address;

    @TableField("Status")
    private Integer Status;

    @TableField("BirtView")
    private String BirtView;

    @TableField("CoverArea")
    private String CoverArea;

    @TableId("ID")
    private String id;

    @TableField("ProductType")
    private String ProductType;

    @TableField("ExpectHourse")
    private String ExpectHourse;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("Bitmap")
    private String Bitmap;

    @TableField("Intro")
    private String Intro;

    @TableField("ShortName")
    private String ShortName;

    @TableField("LocationY")
    private String LocationY;

    @TableField("VolumeRate")
    private String VolumeRate;

    @TableField("Editor")
    private String Editor;

    @TableField("CommisionRule")
    private String CommisionRule;

    @TableField("OpenDiscTime")
    private String OpenDiscTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Discount")
    private String Discount;

    @TableField("BuildArea")
    private String BuildArea;

    @TableField("AppID")
    private String AppID;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Name")
    private String Name;

    @TableField("AroundAssort")
    private String AroundAssort;

    public Integer getIsShowProjectDetail() {
        return IsShowProjectDetail;
    }

    public void setIsShowProjectDetail(Integer IsShowProjectDetail) {
        this.IsShowProjectDetail = IsShowProjectDetail;
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
    public Integer getIsShowProjectAroundAssort() {
        return IsShowProjectAroundAssort;
    }

    public void setIsShowProjectAroundAssort(Integer IsShowProjectAroundAssort) {
        this.IsShowProjectAroundAssort = IsShowProjectAroundAssort;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public String getCityID() {
        return CityID;
    }

    public void setCityID(String CityID) {
        this.CityID = CityID;
    }
    public String getRecommonedReason() {
        return RecommonedReason;
    }

    public void setRecommonedReason(String RecommonedReason) {
        this.RecommonedReason = RecommonedReason;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getCommisionRate() {
        return CommisionRate;
    }

    public void setCommisionRate(String CommisionRate) {
        this.CommisionRate = CommisionRate;
    }
    public String getProductPriceAvg() {
        return ProductPriceAvg;
    }

    public void setProductPriceAvg(String ProductPriceAvg) {
        this.ProductPriceAvg = ProductPriceAvg;
    }
    public String getLocationX() {
        return LocationX;
    }

    public void setLocationX(String LocationX) {
        this.LocationX = LocationX;
    }
    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getBirtView() {
        return BirtView;
    }

    public void setBirtView(String BirtView) {
        this.BirtView = BirtView;
    }
    public String getCoverArea() {
        return CoverArea;
    }

    public void setCoverArea(String CoverArea) {
        this.CoverArea = CoverArea;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }
    public String getExpectHourse() {
        return ExpectHourse;
    }

    public void setExpectHourse(String ExpectHourse) {
        this.ExpectHourse = ExpectHourse;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getBitmap() {
        return Bitmap;
    }

    public void setBitmap(String Bitmap) {
        this.Bitmap = Bitmap;
    }
    public String getIntro() {
        return Intro;
    }

    public void setIntro(String Intro) {
        this.Intro = Intro;
    }
    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }
    public String getLocationY() {
        return LocationY;
    }

    public void setLocationY(String LocationY) {
        this.LocationY = LocationY;
    }
    public String getVolumeRate() {
        return VolumeRate;
    }

    public void setVolumeRate(String VolumeRate) {
        this.VolumeRate = VolumeRate;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getCommisionRule() {
        return CommisionRule;
    }

    public void setCommisionRule(String CommisionRule) {
        this.CommisionRule = CommisionRule;
    }
    public String getOpenDiscTime() {
        return OpenDiscTime;
    }

    public void setOpenDiscTime(String OpenDiscTime) {
        this.OpenDiscTime = OpenDiscTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }
    public String getBuildArea() {
        return BuildArea;
    }

    public void setBuildArea(String BuildArea) {
        this.BuildArea = BuildArea;
    }
    public String getAppID() {
        return AppID;
    }

    public void setAppID(String AppID) {
        this.AppID = AppID;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getAroundAssort() {
        return AroundAssort;
    }

    public void setAroundAssort(String AroundAssort) {
        this.AroundAssort = AroundAssort;
    }

    @Override
    public String toString() {
        return "ABrokerproject{" +
        "IsShowProjectDetail=" + IsShowProjectDetail +
        ", IsShowProjectBasicInfo=" + IsShowProjectBasicInfo +
        ", IsShowProjectIntro=" + IsShowProjectIntro +
        ", IsShowProjectRecommonedReason=" + IsShowProjectRecommonedReason +
        ", IsShowProjectAroundAssort=" + IsShowProjectAroundAssort +
        ", EditeTime=" + EditeTime +
        ", CityID=" + CityID +
        ", RecommonedReason=" + RecommonedReason +
        ", Creator=" + Creator +
        ", CommisionRate=" + CommisionRate +
        ", ProductPriceAvg=" + ProductPriceAvg +
        ", LocationX=" + LocationX +
        ", Tel=" + Tel +
        ", Address=" + Address +
        ", Status=" + Status +
        ", BirtView=" + BirtView +
        ", CoverArea=" + CoverArea +
        ", id=" + id +
        ", ProductType=" + ProductType +
        ", ExpectHourse=" + ExpectHourse +
        ", ListIndex=" + ListIndex +
        ", ProjectID=" + ProjectID +
        ", Bitmap=" + Bitmap +
        ", Intro=" + Intro +
        ", ShortName=" + ShortName +
        ", LocationY=" + LocationY +
        ", VolumeRate=" + VolumeRate +
        ", Editor=" + Editor +
        ", CommisionRule=" + CommisionRule +
        ", OpenDiscTime=" + OpenDiscTime +
        ", IsDel=" + IsDel +
        ", Discount=" + Discount +
        ", BuildArea=" + BuildArea +
        ", AppID=" + AppID +
        ", CreateTime=" + CreateTime +
        ", Name=" + Name +
        ", AroundAssort=" + AroundAssort +
        "}";
    }
}
