package com.tahoecn.xkc.model.ratio;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author YYY
 * @since 2020-05-12
 */
@TableName("B_RatioConfig")
@ApiModel(value = "BRatioconfig对象", description = "")
public class BRatioconfig implements Serializable, Comparable<BRatioconfig> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("Type")
    private Integer Type;

    @TableField("MasterId")
    private String MasterId;

    @TableField("Name")
    private String Name;

    @TableField("Channels")
    private String Channels;

    @TableField("ReportRatio")
    private Integer ReportRatio;

    @TableField("DealRatio")
    private Integer DealRatio;

    @TableField("ReportRatioName")
    private String ReportRatioName;

    @TableField("DealRatioName")
    private String DealRatioName;

    @TableField("ProjectId")
    private String ProjectId;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("RegionalId")
    private String RegionalId;

    @TableField("RegionalName")
    private String RegionalName;

    @TableField("CityId")
    private String CityId;

    @TableField("CityName")
    private String CityName;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }

    public String getMasterId() {
        return MasterId;
    }

    public void setMasterId(String MasterId) {
        this.MasterId = MasterId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getChannels() {
        return Channels;
    }

    public void setChannels(String Channels) {
        this.Channels = Channels;
    }

    public Integer getReportRatio() {
        return ReportRatio;
    }

    public void setReportRatio(Integer ReportRatio) {
        this.ReportRatio = ReportRatio;
    }

    public Integer getDealRatio() {
        return DealRatio;
    }

    public String getReportRatioName() {
        return ReportRatioName;
    }

    public void setReportRatioName(String ReportRatioName) {
        this.ReportRatioName = ReportRatioName;
    }


    public String getDealRatioName() {
        return DealRatioName;
    }

    public void setDealRatioName(String DealRatioName) {
        this.DealRatioName = DealRatioName;
    }

    public void setDealRatio(Integer DealRatio) {
        this.DealRatio = DealRatio;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getRegionalId() {
        return RegionalId;
    }

    public void setRegionalId(String RegionalId) {
        this.RegionalId = RegionalId;
    }

    public String getRegionalName() {
        return RegionalName;
    }

    public void setRegionalName(String RegionalName) {
        this.RegionalName = RegionalName;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String CityId) {
        this.CityId = CityId;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
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
        return "BRatioconfig{" +
                "id=" + id +
                ", Type=" + Type +
                ", MasterId=" + MasterId +
                ", Name=" + Name +
                ", Channels=" + Channels +
                ", ReportRatio=" + ReportRatio +
                ", DealRatio=" + DealRatio +
                ", ProjectId=" + ProjectId +
                ", ProjectName=" + ProjectName +
                ", RegionalId=" + RegionalId +
                ", RegionalName=" + RegionalName +
                ", CityId=" + CityId +
                ", CityName=" + CityName +
                ", Creator=" + Creator +
                ", CreateTime=" + CreateTime +
                ", Editor=" + Editor +
                ", EditeTime=" + EditeTime +
                ", IsDel=" + IsDel +
                ", Status=" + Status +
                "}";
    }

    @Override
    public int compareTo(BRatioconfig o) {
        int sorte = this.getId().compareTo(o.getId());
        if (StringUtils.isNotEmpty(this.getMasterId())) sorte = this.getMasterId().compareTo(o.getMasterId());
        return sorte;
    }
}
