package com.tahoecn.xkc.model.channel;

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
@TableName("B_ChannelOrg")
@ApiModel(value="BChannelorg对象", description="")
public class BChannelorg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织机构分类(1,公司;2,门店)")
    @TableField("OrgCategory")
    private Integer OrgCategory;

    @ApiModelProperty(value = "0 未审批 1 通过 2 未通过")
    @TableField("ApprovalStatus")
    private Integer ApprovalStatus;

    @ApiModelProperty(value = "是否删除")
    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "创建时间")
    @TableField("CreateTime")
    private Date CreateTime;

    @ApiModelProperty(value = "父ID")
    @TableField("PID")
    private String pid;

    @ApiModelProperty(value = "经纪人类型")
    @TableField("ChannelTypeID")
    private String ChannelTypeID;

    @ApiModelProperty(value = "简称")
    @TableField("OrgShortName")
    private String OrgShortName;

    @ApiModelProperty(value = "手机号")
    @TableField("Mobile")
    private String Mobile;

    @ApiModelProperty(value = "层级")
    @TableField("Levels")
    private Integer Levels;

    @ApiModelProperty(value = "责任人")
    @TableField("Responsible")
    private String Responsible;

    @ApiModelProperty(value = "审批时间")
    @TableField("ApprovalDate")
    private Date ApprovalDate;

    @ApiModelProperty(value = "城市")
    @TableField("City")
    private String City;

    @ApiModelProperty(value = "项目")
    @TableField("ProjectID")
    private String ProjectID;

    @ApiModelProperty(value = "营业执照")
    @TableField("BizLicense")
    private String BizLicense;

    @ApiModelProperty(value = "排序")
    @TableField("ListIndex")
    private Integer ListIndex;

    @ApiModelProperty(value = "全路径")
    @TableField("FullPath")
    private String FullPath;

    @ApiModelProperty(value = "状态")
    @TableField("Status")
    private Integer Status;

    @ApiModelProperty(value = "编辑人")
    @TableField("Editor")
    private String Editor;

    @ApiModelProperty(value = "组织机构代码")
    @TableField("OrgCode")
    private String OrgCode;

    @ApiModelProperty(value = "二维码")
    @TableField("QRcode")
    private String QRcode;

    @ApiModelProperty(value = "审批人")
    @TableField("Approver")
    private String Approver;

    @ApiModelProperty(value = "创建人")
    @TableField("Creator")
    private String Creator;

    @ApiModelProperty(value = "ID")
    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "编辑时间")
    @TableField("EditTime")
    private Date EditTime;

    @ApiModelProperty(value = "组织机构名称")
    @TableField("OrgName")
    private String OrgName;

    @ApiModelProperty(value = "省份")
    @TableField("Province")
    private String Province;

    public Integer getOrgCategory() {
        return OrgCategory;
    }

    public void setOrgCategory(Integer OrgCategory) {
        this.OrgCategory = OrgCategory;
    }
    public Integer getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(Integer ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getChannelTypeID() {
        return ChannelTypeID;
    }

    public void setChannelTypeID(String ChannelTypeID) {
        this.ChannelTypeID = ChannelTypeID;
    }
    public String getOrgShortName() {
        return OrgShortName;
    }

    public void setOrgShortName(String OrgShortName) {
        this.OrgShortName = OrgShortName;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public Integer getLevels() {
        return Levels;
    }

    public void setLevels(Integer Levels) {
        this.Levels = Levels;
    }
    public String getResponsible() {
        return Responsible;
    }

    public void setResponsible(String Responsible) {
        this.Responsible = Responsible;
    }
    public Date getApprovalDate() {
        return ApprovalDate;
    }

    public void setApprovalDate(Date ApprovalDate) {
        this.ApprovalDate = ApprovalDate;
    }
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getBizLicense() {
        return BizLicense;
    }

    public void setBizLicense(String BizLicense) {
        this.BizLicense = BizLicense;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getFullPath() {
        return FullPath;
    }

    public void setFullPath(String FullPath) {
        this.FullPath = FullPath;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String OrgCode) {
        this.OrgCode = OrgCode;
    }
    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }
    public String getApprover() {
        return Approver;
    }

    public void setApprover(String Approver) {
        this.Approver = Approver;
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
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String OrgName) {
        this.OrgName = OrgName;
    }
    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    @Override
    public String toString() {
        return "BChannelorg{" +
        "OrgCategory=" + OrgCategory +
        ", ApprovalStatus=" + ApprovalStatus +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        ", pid=" + pid +
        ", ChannelTypeID=" + ChannelTypeID +
        ", OrgShortName=" + OrgShortName +
        ", Mobile=" + Mobile +
        ", Levels=" + Levels +
        ", Responsible=" + Responsible +
        ", ApprovalDate=" + ApprovalDate +
        ", City=" + City +
        ", ProjectID=" + ProjectID +
        ", BizLicense=" + BizLicense +
        ", ListIndex=" + ListIndex +
        ", FullPath=" + FullPath +
        ", Status=" + Status +
        ", Editor=" + Editor +
        ", OrgCode=" + OrgCode +
        ", QRcode=" + QRcode +
        ", Approver=" + Approver +
        ", Creator=" + Creator +
        ", id=" + id +
        ", EditTime=" + EditTime +
        ", OrgName=" + OrgName +
        ", Province=" + Province +
        "}";
    }
}
