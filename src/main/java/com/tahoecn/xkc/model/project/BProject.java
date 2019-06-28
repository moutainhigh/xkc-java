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
 * @since 2019-06-25
 */
@TableName("B_Project")
@ApiModel(value="BProject对象", description="")
public class BProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分期")
    @TableField("ShortName")
    private String ShortName;

    @ApiModelProperty(value = "是否关闭分接 0.开启 1.不开启")
    @TableField("IsNoAllotRole")
    private Integer IsNoAllotRole;

    @ApiModelProperty(value = "是否允许报备自己 0.不允许 1.允许")
    @TableField("IsReportOwn")
    private Integer IsReportOwn;

    @ApiModelProperty(value = "是否开启渠道经纪人注册人工审核0.不开启 1.开启")
    @TableField("IsChannelBrokerRegAudit")
    private Integer IsChannelBrokerRegAudit;

    @ApiModelProperty(value = "1.小格子 2.大格子 3.列表")
    @TableField("HouseStyle")
    private Integer HouseStyle;

    @ApiModelProperty(value = "外部人员允许修改密码 0.不允许 1.允许")
    @TableField("OutUserAllowModifyPwd")
    private Integer OutUserAllowModifyPwd;

    @ApiModelProperty(value = "外部人员展示房源页 0.不展示 1.展示")
    @TableField("OutUserIsShowHouseStyle")
    private Integer OutUserIsShowHouseStyle;

    @ApiModelProperty(value = "抢单模式 0.手动分配模式 1.客储抢单模式 2.自动分配模式")
    @TableField("SnatchingMode")
    private Integer SnatchingMode;

    @ApiModelProperty(value = "置业顾问每天可申领客户数取值1-10，默认3")
    @TableField("EveryDayApplyCustomerNum")
    private Integer EveryDayApplyCustomerNum;

    @ApiModelProperty(value = "客户被回收几次进入丢弃客户池")
    @TableField("CustomerRecoveryCount")
    private Integer CustomerRecoveryCount;

    @ApiModelProperty(value = "分享传播抢单模式(分享传播客户池分配设置) 0.手动分配模式 1.客储抢单模式 2.自动分配模式")
    @TableField("ShareSnatchingMode")
    private Integer ShareSnatchingMode;

    @ApiModelProperty(value = "分享传播自动模式下类别 0.随机自动分配所有公共客户池资源 1. 顺序自动分配所有公共客户池资源 2. 每日自动随机分配[5个]客户资源 3. 每日自动顺序分配[5个]客户资源")
    @TableField("ShareSnatchingAutoModeType")
    private Integer ShareSnatchingAutoModeType;

    @ApiModelProperty(value = "分享传播置业顾问每天可申领客户")
    @TableField("ShareEveryDayApplyCustomerNum")
    private Integer ShareEveryDayApplyCustomerNum;

    @ApiModelProperty(value = "分享传播客户被回收几次进入丢弃客户池")
    @TableField("ShareCustomerRecoveryCount")
    private Integer ShareCustomerRecoveryCount;

    @ApiModelProperty(value = "0.自动轮循给内部置业顾问 1.自动丢弃到丢失客户，由营销经理手动分配")
    @TableField("CustomerPoolStopType")
    private Integer CustomerPoolStopType;

    @ApiModelProperty(value = "在公共客户池停留超过天数")
    @TableField("CustomerPoolStopExceedDay")
    private Integer CustomerPoolStopExceedDay;

    @ApiModelProperty(value = "分享报备模式-自由经纪人 0手动 1自动")
    @TableField("BrokerModel")
    private Integer BrokerModel;

    @ApiModelProperty(value = "分享报备模式-中介同行 0手动 1自动")
    @TableField("MediumrModel")
    private Integer MediumrModel;

    @ApiModelProperty(value = "分享报备模式-置业顾问 0手动 1自动")
    @TableField("SaleModel")
    private Integer SaleModel;

    @ApiModelProperty(value = "分享报备模式-自有渠道 0手动 1自动")
    @TableField("HaveModel")
    private Integer HaveModel;

    @ApiModelProperty(value = "分享报备模式-老带新 0手动 1自动")
    @TableField("OwnerModel")
    private Integer OwnerModel;

    @ApiModelProperty(value = "分享报备模式-员工推荐 0手动 1自动")
    @TableField("StaffModel")
    private Integer StaffModel;

    @ApiModelProperty(value = "置业顾问沉淀非自有项目的客户几天")
    @TableField("SaleCustomerNotReportNum")
    private Integer SaleCustomerNotReportNum;

    @ApiModelProperty(value = "自有渠道沉淀非自有项目的客户几天")
    @TableField("HaveCustomerNotReportNum")
    private Integer HaveCustomerNotReportNum;

    @ApiModelProperty(value = "中介同行沉淀非自有项目的客户几天")
    @TableField("MediumrCustomerNotReportNum")
    private Integer MediumrCustomerNotReportNum;

    @ApiModelProperty(value = "案场管理人员沉淀的客户几天")
    @TableField("AdminCustomerNotReportNum")
    private Integer AdminCustomerNotReportNum;

    @ApiModelProperty(value = "分销渠道管理人员沉淀的客户几天")
    @TableField("DistributionCustomerNotReportNum")
    private Integer DistributionCustomerNotReportNum;

    @ApiModelProperty(value = "普通用户沉淀的客户几天")
    @TableField("PlainCustomerNotReportNum")
    private Integer PlainCustomerNotReportNum;

    @TableField("AgentTeamIsShowHouseStyle")
    private Integer AgentTeamIsShowHouseStyle;

    @TableField("PID")
    private String pid;

    @TableField("WeChatPicPath")
    private String WeChatPicPath;

    @TableField("Category")
    private String Category;

    @TableField("TwoRankAllowSubscribe")
    private Integer TwoRankAllowSubscribe;

    @TableField("Name")
    private String Name;

    @TableField("IsLocationValidate")
    private Integer IsLocationValidate;

    @TableField("Editor")
    private String Editor;

    @TableField("SerialNumber")
    private String SerialNumber;

    @TableField("PCode")
    private String PCode;

    @TableField("Level")
    private Integer Level;

    @TableField("Status")
    private Integer Status;

    @TableField("AllowDeviceType")
    private Integer AllowDeviceType;

    @TableField("Creator")
    private String Creator;

    @TableId("ID")
    private String id;

    @TableField("Rise")
    private String Rise;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("CustomerGuideTemplate")
    private String CustomerGuideTemplate;

    @TableField("SelfSoldTeamIsShowHouseStyle")
    private Integer SelfSoldTeamIsShowHouseStyle;

    @TableField("IsNoMobileVerify")
    private Integer IsNoMobileVerify;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Alias")
    private String Alias;

    @TableField("BUGUID")
    private String buguid;

    @TableField("IsNoCustomerRank")
    private Integer IsNoCustomerRank;

    @TableField("IsOffSiteSale")
    private Integer IsOffSiteSale;

    @TableField("Mark")
    private String Mark;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Code")
    private String Code;

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }
    public Integer getIsNoAllotRole() {
        return IsNoAllotRole;
    }

    public void setIsNoAllotRole(Integer IsNoAllotRole) {
        this.IsNoAllotRole = IsNoAllotRole;
    }
    public Integer getIsReportOwn() {
        return IsReportOwn;
    }

    public void setIsReportOwn(Integer IsReportOwn) {
        this.IsReportOwn = IsReportOwn;
    }
    public Integer getIsChannelBrokerRegAudit() {
        return IsChannelBrokerRegAudit;
    }

    public void setIsChannelBrokerRegAudit(Integer IsChannelBrokerRegAudit) {
        this.IsChannelBrokerRegAudit = IsChannelBrokerRegAudit;
    }
    public Integer getHouseStyle() {
        return HouseStyle;
    }

    public void setHouseStyle(Integer HouseStyle) {
        this.HouseStyle = HouseStyle;
    }
    public Integer getOutUserAllowModifyPwd() {
        return OutUserAllowModifyPwd;
    }

    public void setOutUserAllowModifyPwd(Integer OutUserAllowModifyPwd) {
        this.OutUserAllowModifyPwd = OutUserAllowModifyPwd;
    }
    public Integer getOutUserIsShowHouseStyle() {
        return OutUserIsShowHouseStyle;
    }

    public void setOutUserIsShowHouseStyle(Integer OutUserIsShowHouseStyle) {
        this.OutUserIsShowHouseStyle = OutUserIsShowHouseStyle;
    }
    public Integer getSnatchingMode() {
        return SnatchingMode;
    }

    public void setSnatchingMode(Integer SnatchingMode) {
        this.SnatchingMode = SnatchingMode;
    }
    public Integer getEveryDayApplyCustomerNum() {
        return EveryDayApplyCustomerNum;
    }

    public void setEveryDayApplyCustomerNum(Integer EveryDayApplyCustomerNum) {
        this.EveryDayApplyCustomerNum = EveryDayApplyCustomerNum;
    }
    public Integer getCustomerRecoveryCount() {
        return CustomerRecoveryCount;
    }

    public void setCustomerRecoveryCount(Integer CustomerRecoveryCount) {
        this.CustomerRecoveryCount = CustomerRecoveryCount;
    }
    public Integer getShareSnatchingMode() {
        return ShareSnatchingMode;
    }

    public void setShareSnatchingMode(Integer ShareSnatchingMode) {
        this.ShareSnatchingMode = ShareSnatchingMode;
    }
    public Integer getShareSnatchingAutoModeType() {
        return ShareSnatchingAutoModeType;
    }

    public void setShareSnatchingAutoModeType(Integer ShareSnatchingAutoModeType) {
        this.ShareSnatchingAutoModeType = ShareSnatchingAutoModeType;
    }
    public Integer getShareEveryDayApplyCustomerNum() {
        return ShareEveryDayApplyCustomerNum;
    }

    public void setShareEveryDayApplyCustomerNum(Integer ShareEveryDayApplyCustomerNum) {
        this.ShareEveryDayApplyCustomerNum = ShareEveryDayApplyCustomerNum;
    }
    public Integer getShareCustomerRecoveryCount() {
        return ShareCustomerRecoveryCount;
    }

    public void setShareCustomerRecoveryCount(Integer ShareCustomerRecoveryCount) {
        this.ShareCustomerRecoveryCount = ShareCustomerRecoveryCount;
    }
    public Integer getCustomerPoolStopType() {
        return CustomerPoolStopType;
    }

    public void setCustomerPoolStopType(Integer CustomerPoolStopType) {
        this.CustomerPoolStopType = CustomerPoolStopType;
    }
    public Integer getCustomerPoolStopExceedDay() {
        return CustomerPoolStopExceedDay;
    }

    public void setCustomerPoolStopExceedDay(Integer CustomerPoolStopExceedDay) {
        this.CustomerPoolStopExceedDay = CustomerPoolStopExceedDay;
    }
    public Integer getBrokerModel() {
        return BrokerModel;
    }

    public void setBrokerModel(Integer BrokerModel) {
        this.BrokerModel = BrokerModel;
    }
    public Integer getMediumrModel() {
        return MediumrModel;
    }

    public void setMediumrModel(Integer MediumrModel) {
        this.MediumrModel = MediumrModel;
    }
    public Integer getSaleModel() {
        return SaleModel;
    }

    public void setSaleModel(Integer SaleModel) {
        this.SaleModel = SaleModel;
    }
    public Integer getHaveModel() {
        return HaveModel;
    }

    public void setHaveModel(Integer HaveModel) {
        this.HaveModel = HaveModel;
    }
    public Integer getOwnerModel() {
        return OwnerModel;
    }

    public void setOwnerModel(Integer OwnerModel) {
        this.OwnerModel = OwnerModel;
    }
    public Integer getStaffModel() {
        return StaffModel;
    }

    public void setStaffModel(Integer StaffModel) {
        this.StaffModel = StaffModel;
    }
    public Integer getSaleCustomerNotReportNum() {
        return SaleCustomerNotReportNum;
    }

    public void setSaleCustomerNotReportNum(Integer SaleCustomerNotReportNum) {
        this.SaleCustomerNotReportNum = SaleCustomerNotReportNum;
    }
    public Integer getHaveCustomerNotReportNum() {
        return HaveCustomerNotReportNum;
    }

    public void setHaveCustomerNotReportNum(Integer HaveCustomerNotReportNum) {
        this.HaveCustomerNotReportNum = HaveCustomerNotReportNum;
    }
    public Integer getMediumrCustomerNotReportNum() {
        return MediumrCustomerNotReportNum;
    }

    public void setMediumrCustomerNotReportNum(Integer MediumrCustomerNotReportNum) {
        this.MediumrCustomerNotReportNum = MediumrCustomerNotReportNum;
    }
    public Integer getAdminCustomerNotReportNum() {
        return AdminCustomerNotReportNum;
    }

    public void setAdminCustomerNotReportNum(Integer AdminCustomerNotReportNum) {
        this.AdminCustomerNotReportNum = AdminCustomerNotReportNum;
    }
    public Integer getDistributionCustomerNotReportNum() {
        return DistributionCustomerNotReportNum;
    }

    public void setDistributionCustomerNotReportNum(Integer DistributionCustomerNotReportNum) {
        this.DistributionCustomerNotReportNum = DistributionCustomerNotReportNum;
    }
    public Integer getPlainCustomerNotReportNum() {
        return PlainCustomerNotReportNum;
    }

    public void setPlainCustomerNotReportNum(Integer PlainCustomerNotReportNum) {
        this.PlainCustomerNotReportNum = PlainCustomerNotReportNum;
    }
    public Integer getAgentTeamIsShowHouseStyle() {
        return AgentTeamIsShowHouseStyle;
    }

    public void setAgentTeamIsShowHouseStyle(Integer AgentTeamIsShowHouseStyle) {
        this.AgentTeamIsShowHouseStyle = AgentTeamIsShowHouseStyle;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getWeChatPicPath() {
        return WeChatPicPath;
    }

    public void setWeChatPicPath(String WeChatPicPath) {
        this.WeChatPicPath = WeChatPicPath;
    }
    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
    public Integer getTwoRankAllowSubscribe() {
        return TwoRankAllowSubscribe;
    }

    public void setTwoRankAllowSubscribe(Integer TwoRankAllowSubscribe) {
        this.TwoRankAllowSubscribe = TwoRankAllowSubscribe;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Integer getIsLocationValidate() {
        return IsLocationValidate;
    }

    public void setIsLocationValidate(Integer IsLocationValidate) {
        this.IsLocationValidate = IsLocationValidate;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }
    public String getPCode() {
        return PCode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }
    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer Level) {
        this.Level = Level;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getAllowDeviceType() {
        return AllowDeviceType;
    }

    public void setAllowDeviceType(Integer AllowDeviceType) {
        this.AllowDeviceType = AllowDeviceType;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRise() {
        return Rise;
    }

    public void setRise(String Rise) {
        this.Rise = Rise;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getCustomerGuideTemplate() {
        return CustomerGuideTemplate;
    }

    public void setCustomerGuideTemplate(String CustomerGuideTemplate) {
        this.CustomerGuideTemplate = CustomerGuideTemplate;
    }
    public Integer getSelfSoldTeamIsShowHouseStyle() {
        return SelfSoldTeamIsShowHouseStyle;
    }

    public void setSelfSoldTeamIsShowHouseStyle(Integer SelfSoldTeamIsShowHouseStyle) {
        this.SelfSoldTeamIsShowHouseStyle = SelfSoldTeamIsShowHouseStyle;
    }
    public Integer getIsNoMobileVerify() {
        return IsNoMobileVerify;
    }

    public void setIsNoMobileVerify(Integer IsNoMobileVerify) {
        this.IsNoMobileVerify = IsNoMobileVerify;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }
    public String getBuguid() {
        return buguid;
    }

    public void setBuguid(String buguid) {
        this.buguid = buguid;
    }
    public Integer getIsNoCustomerRank() {
        return IsNoCustomerRank;
    }

    public void setIsNoCustomerRank(Integer IsNoCustomerRank) {
        this.IsNoCustomerRank = IsNoCustomerRank;
    }
    public Integer getIsOffSiteSale() {
        return IsOffSiteSale;
    }

    public void setIsOffSiteSale(Integer IsOffSiteSale) {
        this.IsOffSiteSale = IsOffSiteSale;
    }
    public String getMark() {
        return Mark;
    }

    public void setMark(String Mark) {
        this.Mark = Mark;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Override
    public String toString() {
        return "BProject{" +
        "ShortName=" + ShortName +
        ", IsNoAllotRole=" + IsNoAllotRole +
        ", IsReportOwn=" + IsReportOwn +
        ", IsChannelBrokerRegAudit=" + IsChannelBrokerRegAudit +
        ", HouseStyle=" + HouseStyle +
        ", OutUserAllowModifyPwd=" + OutUserAllowModifyPwd +
        ", OutUserIsShowHouseStyle=" + OutUserIsShowHouseStyle +
        ", SnatchingMode=" + SnatchingMode +
        ", EveryDayApplyCustomerNum=" + EveryDayApplyCustomerNum +
        ", CustomerRecoveryCount=" + CustomerRecoveryCount +
        ", ShareSnatchingMode=" + ShareSnatchingMode +
        ", ShareSnatchingAutoModeType=" + ShareSnatchingAutoModeType +
        ", ShareEveryDayApplyCustomerNum=" + ShareEveryDayApplyCustomerNum +
        ", ShareCustomerRecoveryCount=" + ShareCustomerRecoveryCount +
        ", CustomerPoolStopType=" + CustomerPoolStopType +
        ", CustomerPoolStopExceedDay=" + CustomerPoolStopExceedDay +
        ", BrokerModel=" + BrokerModel +
        ", MediumrModel=" + MediumrModel +
        ", SaleModel=" + SaleModel +
        ", HaveModel=" + HaveModel +
        ", OwnerModel=" + OwnerModel +
        ", StaffModel=" + StaffModel +
        ", SaleCustomerNotReportNum=" + SaleCustomerNotReportNum +
        ", HaveCustomerNotReportNum=" + HaveCustomerNotReportNum +
        ", MediumrCustomerNotReportNum=" + MediumrCustomerNotReportNum +
        ", AdminCustomerNotReportNum=" + AdminCustomerNotReportNum +
        ", DistributionCustomerNotReportNum=" + DistributionCustomerNotReportNum +
        ", PlainCustomerNotReportNum=" + PlainCustomerNotReportNum +
        ", AgentTeamIsShowHouseStyle=" + AgentTeamIsShowHouseStyle +
        ", pid=" + pid +
        ", WeChatPicPath=" + WeChatPicPath +
        ", Category=" + Category +
        ", TwoRankAllowSubscribe=" + TwoRankAllowSubscribe +
        ", Name=" + Name +
        ", IsLocationValidate=" + IsLocationValidate +
        ", Editor=" + Editor +
        ", SerialNumber=" + SerialNumber +
        ", PCode=" + PCode +
        ", Level=" + Level +
        ", Status=" + Status +
        ", AllowDeviceType=" + AllowDeviceType +
        ", Creator=" + Creator +
        ", id=" + id +
        ", Rise=" + Rise +
        ", EditTime=" + EditTime +
        ", CustomerGuideTemplate=" + CustomerGuideTemplate +
        ", SelfSoldTeamIsShowHouseStyle=" + SelfSoldTeamIsShowHouseStyle +
        ", IsNoMobileVerify=" + IsNoMobileVerify +
        ", IsDel=" + IsDel +
        ", Alias=" + Alias +
        ", buguid=" + buguid +
        ", IsNoCustomerRank=" + IsNoCustomerRank +
        ", IsOffSiteSale=" + IsOffSiteSale +
        ", Mark=" + Mark +
        ", CreateTime=" + CreateTime +
        ", Code=" + Code +
        "}";
    }
}
