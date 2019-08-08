package com.tahoecn.xkc.model.house;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-08-08
 */
@TableName("B_HousePlan")
@ApiModel(value="BHouseplan对象", description="")
public class BHouseplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("CustomerID")
    private String CustomerID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("SalesUserID")
    private String SalesUserID;

    @TableField("SalesUserName")
    private String SalesUserName;

    @TableField("HouseID")
    private String HouseID;

    @TableField("HouseName")
    private String HouseName;

    @ApiModelProperty(value = "URL或磁盘路径")
    @TableField("ApartmentDiagram")
    private String ApartmentDiagram;

    @TableField("BuildingArea")
    private Double BuildingArea;

    @TableField("BuildingPrice")
    private Double BuildingPrice;

    @TableField("TotalPrice")
    private Double TotalPrice;

    @TableField("DiscountTotal")
    private Double DiscountTotal;

    @TableField("DiscountDetail")
    private String DiscountDetail;

    @TableField("PayTypeID")
    private String PayTypeID;

    @TableField("PayTypeName")
    private String PayTypeName;

    @TableField("DownPayment")
    private Double DownPayment;

    @TableField("DownPaymentratio")
    private Double DownPaymentratio;

    @TableField("LoanTypeID")
    private String LoanTypeID;

    @TableField("LoanBusiness")
    private Double LoanBusiness;

    @TableField("LoanTypeName")
    private String LoanTypeName;

    @TableField("LoanBusinessRate")
    private Double LoanBusinessRate;

    @TableField("LoanAccumulationFund")
    private Double LoanAccumulationFund;

    @TableField("LoanAccumulationFundRate")
    private Double LoanAccumulationFundRate;

    @TableField("LoanTerm")
    private Integer LoanTerm;

    @ApiModelProperty(value = "1=等额本息,2=等额本金")
    @TableField("RepaymentType")
    private Integer RepaymentType;

    @TableField("LoanTotal")
    private Double LoanTotal;

    @TableField("MonthlyPayment")
    private String MonthlyPayment;

    @TableField("InterestTotal")
    private Double InterestTotal;

    @TableField("PrincipalTotal")
    private Double PrincipalTotal;

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
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getSalesUserID() {
        return SalesUserID;
    }

    public void setSalesUserID(String SalesUserID) {
        this.SalesUserID = SalesUserID;
    }
    public String getSalesUserName() {
        return SalesUserName;
    }

    public void setSalesUserName(String SalesUserName) {
        this.SalesUserName = SalesUserName;
    }
    public String getHouseID() {
        return HouseID;
    }

    public void setHouseID(String HouseID) {
        this.HouseID = HouseID;
    }
    public String getHouseName() {
        return HouseName;
    }

    public void setHouseName(String HouseName) {
        this.HouseName = HouseName;
    }
    public String getApartmentDiagram() {
        return ApartmentDiagram;
    }

    public void setApartmentDiagram(String ApartmentDiagram) {
        this.ApartmentDiagram = ApartmentDiagram;
    }
    public Double getBuildingArea() {
        return BuildingArea;
    }

    public void setBuildingArea(Double BuildingArea) {
        this.BuildingArea = BuildingArea;
    }
    public Double getBuildingPrice() {
        return BuildingPrice;
    }

    public void setBuildingPrice(Double BuildingPrice) {
        this.BuildingPrice = BuildingPrice;
    }
    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
    public Double getDiscountTotal() {
        return DiscountTotal;
    }

    public void setDiscountTotal(Double DiscountTotal) {
        this.DiscountTotal = DiscountTotal;
    }
    public String getDiscountDetail() {
        return DiscountDetail;
    }

    public void setDiscountDetail(String DiscountDetail) {
        this.DiscountDetail = DiscountDetail;
    }
    public String getPayTypeID() {
        return PayTypeID;
    }

    public void setPayTypeID(String PayTypeID) {
        this.PayTypeID = PayTypeID;
    }
    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String PayTypeName) {
        this.PayTypeName = PayTypeName;
    }
    public Double getDownPayment() {
        return DownPayment;
    }

    public void setDownPayment(Double DownPayment) {
        this.DownPayment = DownPayment;
    }
    public Double getDownPaymentratio() {
        return DownPaymentratio;
    }

    public void setDownPaymentratio(Double DownPaymentratio) {
        this.DownPaymentratio = DownPaymentratio;
    }
    public String getLoanTypeID() {
        return LoanTypeID;
    }

    public void setLoanTypeID(String LoanTypeID) {
        this.LoanTypeID = LoanTypeID;
    }
    public Double getLoanBusiness() {
        return LoanBusiness;
    }

    public void setLoanBusiness(Double LoanBusiness) {
        this.LoanBusiness = LoanBusiness;
    }
    public String getLoanTypeName() {
        return LoanTypeName;
    }

    public void setLoanTypeName(String LoanTypeName) {
        this.LoanTypeName = LoanTypeName;
    }
    public Double getLoanBusinessRate() {
        return LoanBusinessRate;
    }

    public void setLoanBusinessRate(Double LoanBusinessRate) {
        this.LoanBusinessRate = LoanBusinessRate;
    }
    public Double getLoanAccumulationFund() {
        return LoanAccumulationFund;
    }

    public void setLoanAccumulationFund(Double LoanAccumulationFund) {
        this.LoanAccumulationFund = LoanAccumulationFund;
    }
    public Double getLoanAccumulationFundRate() {
        return LoanAccumulationFundRate;
    }

    public void setLoanAccumulationFundRate(Double LoanAccumulationFundRate) {
        this.LoanAccumulationFundRate = LoanAccumulationFundRate;
    }
    public Integer getLoanTerm() {
        return LoanTerm;
    }

    public void setLoanTerm(Integer LoanTerm) {
        this.LoanTerm = LoanTerm;
    }
    public Integer getRepaymentType() {
        return RepaymentType;
    }

    public void setRepaymentType(Integer RepaymentType) {
        this.RepaymentType = RepaymentType;
    }
    public Double getLoanTotal() {
        return LoanTotal;
    }

    public void setLoanTotal(Double LoanTotal) {
        this.LoanTotal = LoanTotal;
    }
    public String getMonthlyPayment() {
        return MonthlyPayment;
    }

    public void setMonthlyPayment(String MonthlyPayment) {
        this.MonthlyPayment = MonthlyPayment;
    }
    public Double getInterestTotal() {
        return InterestTotal;
    }

    public void setInterestTotal(Double InterestTotal) {
        this.InterestTotal = InterestTotal;
    }
    public Double getPrincipalTotal() {
        return PrincipalTotal;
    }

    public void setPrincipalTotal(Double PrincipalTotal) {
        this.PrincipalTotal = PrincipalTotal;
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

    @Override
    public String toString() {
        return "BHouseplan{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", CustomerID=" + CustomerID +
        ", CustomerName=" + CustomerName +
        ", SalesUserID=" + SalesUserID +
        ", SalesUserName=" + SalesUserName +
        ", HouseID=" + HouseID +
        ", HouseName=" + HouseName +
        ", ApartmentDiagram=" + ApartmentDiagram +
        ", BuildingArea=" + BuildingArea +
        ", BuildingPrice=" + BuildingPrice +
        ", TotalPrice=" + TotalPrice +
        ", DiscountTotal=" + DiscountTotal +
        ", DiscountDetail=" + DiscountDetail +
        ", PayTypeID=" + PayTypeID +
        ", PayTypeName=" + PayTypeName +
        ", DownPayment=" + DownPayment +
        ", DownPaymentratio=" + DownPaymentratio +
        ", LoanTypeID=" + LoanTypeID +
        ", LoanBusiness=" + LoanBusiness +
        ", LoanTypeName=" + LoanTypeName +
        ", LoanBusinessRate=" + LoanBusinessRate +
        ", LoanAccumulationFund=" + LoanAccumulationFund +
        ", LoanAccumulationFundRate=" + LoanAccumulationFundRate +
        ", LoanTerm=" + LoanTerm +
        ", RepaymentType=" + RepaymentType +
        ", LoanTotal=" + LoanTotal +
        ", MonthlyPayment=" + MonthlyPayment +
        ", InterestTotal=" + InterestTotal +
        ", PrincipalTotal=" + PrincipalTotal +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
