package com.tahoecn.xkc.model.customer;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-08-21
 */
@TableName("B_CustomerAttribute")
@ApiModel(value="BCustomerattribute对象", description="")
public class BCustomerattribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("DomicilePlace")
    private String DomicilePlace;

    @TableField("DomicilePlaceName")
    private String DomicilePlaceName;

    @TableField("HomeAddress")
    private String HomeAddress;

    @ApiModelProperty(value = "0：机会创建 1：业务跟进 2：领导批示 3：机会放弃 4：认购 5：签约")
    @TableField("HomeArea")
    private String HomeArea;

    @TableField("HomeAreaName")
    private String HomeAreaName;

    @ApiModelProperty(value = "字典数据")
    @TableField("WorkArea")
    private String WorkArea;

    @TableField("WorkAreaName")
    private String WorkAreaName;

    @ApiModelProperty(value = "字典数据")
    @TableField("Marriage")
    private String Marriage;

    @TableField("MarriageName")
    private String MarriageName;

    @TableField("Family")
    private String Family;

    @TableField("FamilyName")
    private String FamilyName;

    @TableField("Industry")
    private String Industry;

    @TableField("IndustryName")
    private String IndustryName;

    @TableField("AgeGroup")
    private String AgeGroup;

    @TableField("AgeGroupName")
    private String AgeGroupName;

    @TableField("PropertyNum")
    private String PropertyNum;

    @TableField("FollowUpContent")
    private String FollowUpContent;

    @TableField("Birthday")
    private Date Birthday;

    @TableField("Address")
    private String Address;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @ApiModelProperty(value = "0 否 1是")
    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getDomicilePlace() {
        return DomicilePlace;
    }

    public void setDomicilePlace(String DomicilePlace) {
        this.DomicilePlace = DomicilePlace;
    }
    public String getDomicilePlaceName() {
        return DomicilePlaceName;
    }

    public void setDomicilePlaceName(String DomicilePlaceName) {
        this.DomicilePlaceName = DomicilePlaceName;
    }
    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String HomeAddress) {
        this.HomeAddress = HomeAddress;
    }
    public String getHomeArea() {
        return HomeArea;
    }

    public void setHomeArea(String HomeArea) {
        this.HomeArea = HomeArea;
    }
    public String getHomeAreaName() {
        return HomeAreaName;
    }

    public void setHomeAreaName(String HomeAreaName) {
        this.HomeAreaName = HomeAreaName;
    }
    public String getWorkArea() {
        return WorkArea;
    }

    public void setWorkArea(String WorkArea) {
        this.WorkArea = WorkArea;
    }
    public String getWorkAreaName() {
        return WorkAreaName;
    }

    public void setWorkAreaName(String WorkAreaName) {
        this.WorkAreaName = WorkAreaName;
    }
    public String getMarriage() {
        return Marriage;
    }

    public void setMarriage(String Marriage) {
        this.Marriage = Marriage;
    }
    public String getMarriageName() {
        return MarriageName;
    }

    public void setMarriageName(String MarriageName) {
        this.MarriageName = MarriageName;
    }
    public String getFamily() {
        return Family;
    }

    public void setFamily(String Family) {
        this.Family = Family;
    }
    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String FamilyName) {
        this.FamilyName = FamilyName;
    }
    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String Industry) {
        this.Industry = Industry;
    }
    public String getIndustryName() {
        return IndustryName;
    }

    public void setIndustryName(String IndustryName) {
        this.IndustryName = IndustryName;
    }
    public String getAgeGroup() {
        return AgeGroup;
    }

    public void setAgeGroup(String AgeGroup) {
        this.AgeGroup = AgeGroup;
    }
    public String getAgeGroupName() {
        return AgeGroupName;
    }

    public void setAgeGroupName(String AgeGroupName) {
        this.AgeGroupName = AgeGroupName;
    }
    public String getPropertyNum() {
        return PropertyNum;
    }

    public void setPropertyNum(String PropertyNum) {
        this.PropertyNum = PropertyNum;
    }
    public String getFollowUpContent() {
        return FollowUpContent;
    }

    public void setFollowUpContent(String FollowUpContent) {
        this.FollowUpContent = FollowUpContent;
    }
    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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

    @Override
    public String toString() {
        return "BCustomerattribute{" +
        "id=" + id +
        ", CustomerID=" + CustomerID +
        ", DomicilePlace=" + DomicilePlace +
        ", DomicilePlaceName=" + DomicilePlaceName +
        ", HomeAddress=" + HomeAddress +
        ", HomeArea=" + HomeArea +
        ", HomeAreaName=" + HomeAreaName +
        ", WorkArea=" + WorkArea +
        ", WorkAreaName=" + WorkAreaName +
        ", Marriage=" + Marriage +
        ", MarriageName=" + MarriageName +
        ", Family=" + Family +
        ", FamilyName=" + FamilyName +
        ", Industry=" + Industry +
        ", IndustryName=" + IndustryName +
        ", AgeGroup=" + AgeGroup +
        ", AgeGroupName=" + AgeGroupName +
        ", PropertyNum=" + PropertyNum +
        ", FollowUpContent=" + FollowUpContent +
        ", Birthday=" + Birthday +
        ", Address=" + Address +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
