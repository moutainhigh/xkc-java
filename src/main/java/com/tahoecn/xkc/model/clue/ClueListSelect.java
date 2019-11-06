package com.tahoecn.xkc.model.clue;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 1
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@TableName("Clue_List_Select")
@ApiModel(value="ClueListSelect对象", description="1")
public class ClueListSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CustomerState")
    private String CustomerState;

    @TableField("genjinTime")
    private Date genjinTime;

    @TableField("AdviserName")
    private String AdviserName;

    @TableField("IntentProjectID")
    private String IntentProjectID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("Creator")
    private String Creator;

    @TableField("CustomerMobile")
    private String CustomerMobile;

    private Integer rengou;

    @TableField("ID")
    private String id;

    private Integer wuxiao;

    @TableField("Status")
    private Integer Status;

    private Integer cluestatus;

    private Integer renchou;

    @TableField("rengouTime")
    private Date rengouTime;

    @TableField("ReportUserID")
    private String ReportUserID;

    private Integer laifang;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Followupdate")
    private Date Followupdate;

    private Integer duishi;

    public String getCustomerState() {
        return CustomerState;
    }

    public void setCustomerState(String CustomerState) {
        this.CustomerState = CustomerState;
    }
    public Date getGenjinTime() {
        return genjinTime;
    }

    public void setGenjinTime(Date genjinTime) {
        this.genjinTime = genjinTime;
    }
    public String getAdviserName() {
        return AdviserName;
    }

    public void setAdviserName(String AdviserName) {
        this.AdviserName = AdviserName;
    }
    public String getIntentProjectID() {
        return IntentProjectID;
    }

    public void setIntentProjectID(String IntentProjectID) {
        this.IntentProjectID = IntentProjectID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    public Integer getRengou() {
        return rengou;
    }

    public void setRengou(Integer rengou) {
        this.rengou = rengou;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getWuxiao() {
        return wuxiao;
    }

    public void setWuxiao(Integer wuxiao) {
        this.wuxiao = wuxiao;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getCluestatus() {
        return cluestatus;
    }

    public void setCluestatus(Integer cluestatus) {
        this.cluestatus = cluestatus;
    }
    public Integer getRenchou() {
        return renchou;
    }

    public void setRenchou(Integer renchou) {
        this.renchou = renchou;
    }
    public Date getRengouTime() {
        return rengouTime;
    }

    public void setRengouTime(Date rengouTime) {
        this.rengouTime = rengouTime;
    }
    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String ReportUserID) {
        this.ReportUserID = ReportUserID;
    }
    public Integer getLaifang() {
        return laifang;
    }

    public void setLaifang(Integer laifang) {
        this.laifang = laifang;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Date getFollowupdate() {
        return Followupdate;
    }

    public void setFollowupdate(Date Followupdate) {
        this.Followupdate = Followupdate;
    }
    public Integer getDuishi() {
        return duishi;
    }

    public void setDuishi(Integer duishi) {
        this.duishi = duishi;
    }

    @Override
    public String toString() {
        return "ClueListSelect{" +
        "CustomerState=" + CustomerState +
        ", genjinTime=" + genjinTime +
        ", AdviserName=" + AdviserName +
        ", IntentProjectID=" + IntentProjectID +
        ", CustomerName=" + CustomerName +
        ", Creator=" + Creator +
        ", CustomerMobile=" + CustomerMobile +
        ", rengou=" + rengou +
        ", id=" + id +
        ", wuxiao=" + wuxiao +
        ", Status=" + Status +
        ", cluestatus=" + cluestatus +
        ", renchou=" + renchou +
        ", rengouTime=" + rengouTime +
        ", ReportUserID=" + ReportUserID +
        ", laifang=" + laifang +
        ", CreateTime=" + CreateTime +
        ", Followupdate=" + Followupdate +
        ", duishi=" + duishi +
        "}";
    }
}
