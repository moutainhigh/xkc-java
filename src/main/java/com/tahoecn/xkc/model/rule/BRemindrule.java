package com.tahoecn.xkc.model.rule;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@TableName("B_RemindRule")
@ApiModel(value="BRemindrule对象", description="")
public class BRemindrule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("OverdueSignM")
    private Integer OverdueSignM;

    @TableField("OverdueUnSignM")
    private Integer OverdueUnSignM;

    @TableField("OverdueUnFollowup")
    private Integer OverdueUnFollowup;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("OverdueFollowupTime")
    private Integer OverdueFollowupTime;

    @TableField("OverduePaymentM")
    private Integer OverduePaymentM;

    @TableField("OverdueSignAgoTime")
    private Integer OverdueSignAgoTime;

    @TableField("OverdueUnSign")
    private Integer OverdueUnSign;

    @TableField("OverdueSubAgoM")
    private Integer OverdueSubAgoM;

    @TableField("OverdueUnPayment")
    private Integer OverdueUnPayment;

    @TableField("OverdueSubTime")
    private Integer OverdueSubTime;

    @TableField("Creator")
    private String Creator;

    @TableField("OverduePaymentAgoTimeM")
    private Integer OverduePaymentAgoTimeM;

    @TableField("OverduePaymentAgoTime")
    private Integer OverduePaymentAgoTime;

    @TableField("OverdueSubTimeM")
    private Integer OverdueSubTimeM;

    @TableField("OverdueSignAgoUnitM")
    private Integer OverdueSignAgoUnitM;

    @TableField("VersionEndTime")
    private Date VersionEndTime;

    @TableField("OverdueFirstVisitTime")
    private Integer OverdueFirstVisitTime;

    @TableField("OverdueSignAgoUnit")
    private Integer OverdueSignAgoUnit;

    @TableField("OverdueUnPaymentM")
    private Integer OverdueUnPaymentM;

    @TableField("OverduePaymentMTime")
    private Integer OverduePaymentMTime;

    @TableField("OverdueFollowupM")
    private Integer OverdueFollowupM;

    @TableField("OverdueSignAgoM")
    private Integer OverdueSignAgoM;

    @TableField("Editor")
    private String Editor;

    @TableField("OverduePaymentAgoUnit")
    private Integer OverduePaymentAgoUnit;

    @TableField("OverdueFollowupAgoUnit")
    private Integer OverdueFollowupAgoUnit;

    @TableField("OverdueSubAgoTimeM")
    private Integer OverdueSubAgoTimeM;

    @TableField("RuleStartTime")
    private Date RuleStartTime;

    @TableField("OverdueSignTime")
    private Integer OverdueSignTime;

    @TableField("OverdueFirstVisitUnit")
    private Integer OverdueFirstVisitUnit;

    @TableField("OverdueSubAgoUnit")
    private Integer OverdueSubAgoUnit;

    @TableField("OverdueSignMTime")
    private Integer OverdueSignMTime;

    @TableField("OverduePaymentAgoUnitM")
    private Integer OverduePaymentAgoUnitM;

    @TableField("OverdueFollowupAgoUnitM")
    private Integer OverdueFollowupAgoUnitM;

    @TableField("OverduePaymentTime")
    private Integer OverduePaymentTime;

    @TableField("OverdueUnSubM")
    private Integer OverdueUnSubM;

    @TableField("OverdueFollowupAgo")
    private Integer OverdueFollowupAgo;

    @TableField("OverdueSubUnit")
    private Integer OverdueSubUnit;

    @TableField("OverduePaymentUnit")
    private Integer OverduePaymentUnit;

    @TableField("OverdueSubAgo")
    private Integer OverdueSubAgo;

    @TableField("OverdueFollowupAgoM")
    private Integer OverdueFollowupAgoM;

    @TableField("OverdueSignUnit")
    private Integer OverdueSignUnit;

    @TableField("OverdueSignMUnit")
    private Integer OverdueSignMUnit;

    @TableField("OverdueSignAgo")
    private Integer OverdueSignAgo;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("WaitForVisitM")
    private Integer WaitForVisitM;

    @TableField("OverduePaymentAgo")
    private Integer OverduePaymentAgo;

    @TableField("OverdueSubAgoUnitM")
    private Integer OverdueSubAgoUnitM;

    @TableField("WaitForFollowup")
    private Integer WaitForFollowup;

    @TableField("OverdueFollowupUNit")
    private Integer OverdueFollowupUNit;

    @TableField("OverdueSubUnitM")
    private Integer OverdueSubUnitM;

    @TableField("OverdueSubAgoTime")
    private Integer OverdueSubAgoTime;

    @TableField("VersionStartTime")
    private Date VersionStartTime;

    @TableField("OverdueFirstVisit")
    private Integer OverdueFirstVisit;

    @TableField("OverduePayment")
    private Integer OverduePayment;

    @TableField("OverduePaymentAgoM")
    private Integer OverduePaymentAgoM;

    @TableField("Status")
    private Integer Status;

    @TableId("ID")
    private String id;

    @TableField("WaitForVisit")
    private Integer WaitForVisit;

    @TableField("OverdueSubM")
    private Integer OverdueSubM;

    @TableField("OverdueFollowup")
    private Integer OverdueFollowup;

    @TableField("OverdueSignAgoTimeM")
    private Integer OverdueSignAgoTimeM;

    @TableField("OverdueUnSub")
    private Integer OverdueUnSub;

    @TableField("OverdueFollowupAgoTimeM")
    private Integer OverdueFollowupAgoTimeM;

    @TableField("OverdueSub")
    private Integer OverdueSub;

    @TableField("OverduePaymentMUnit")
    private Integer OverduePaymentMUnit;

    @TableField("OverdueFollowupAgoTime")
    private Integer OverdueFollowupAgoTime;

    @TableField("WaitForFollowupM")
    private Integer WaitForFollowupM;

    @TableField("OverdueSign")
    private Integer OverdueSign;

    public Integer getOverdueSignM() {
        return OverdueSignM;
    }

    public void setOverdueSignM(Integer OverdueSignM) {
        this.OverdueSignM = OverdueSignM;
    }
    public Integer getOverdueUnSignM() {
        return OverdueUnSignM;
    }

    public void setOverdueUnSignM(Integer OverdueUnSignM) {
        this.OverdueUnSignM = OverdueUnSignM;
    }
    public Integer getOverdueUnFollowup() {
        return OverdueUnFollowup;
    }

    public void setOverdueUnFollowup(Integer OverdueUnFollowup) {
        this.OverdueUnFollowup = OverdueUnFollowup;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public Integer getOverdueFollowupTime() {
        return OverdueFollowupTime;
    }

    public void setOverdueFollowupTime(Integer OverdueFollowupTime) {
        this.OverdueFollowupTime = OverdueFollowupTime;
    }
    public Integer getOverduePaymentM() {
        return OverduePaymentM;
    }

    public void setOverduePaymentM(Integer OverduePaymentM) {
        this.OverduePaymentM = OverduePaymentM;
    }
    public Integer getOverdueSignAgoTime() {
        return OverdueSignAgoTime;
    }

    public void setOverdueSignAgoTime(Integer OverdueSignAgoTime) {
        this.OverdueSignAgoTime = OverdueSignAgoTime;
    }
    public Integer getOverdueUnSign() {
        return OverdueUnSign;
    }

    public void setOverdueUnSign(Integer OverdueUnSign) {
        this.OverdueUnSign = OverdueUnSign;
    }
    public Integer getOverdueSubAgoM() {
        return OverdueSubAgoM;
    }

    public void setOverdueSubAgoM(Integer OverdueSubAgoM) {
        this.OverdueSubAgoM = OverdueSubAgoM;
    }
    public Integer getOverdueUnPayment() {
        return OverdueUnPayment;
    }

    public void setOverdueUnPayment(Integer OverdueUnPayment) {
        this.OverdueUnPayment = OverdueUnPayment;
    }
    public Integer getOverdueSubTime() {
        return OverdueSubTime;
    }

    public void setOverdueSubTime(Integer OverdueSubTime) {
        this.OverdueSubTime = OverdueSubTime;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Integer getOverduePaymentAgoTimeM() {
        return OverduePaymentAgoTimeM;
    }

    public void setOverduePaymentAgoTimeM(Integer OverduePaymentAgoTimeM) {
        this.OverduePaymentAgoTimeM = OverduePaymentAgoTimeM;
    }
    public Integer getOverduePaymentAgoTime() {
        return OverduePaymentAgoTime;
    }

    public void setOverduePaymentAgoTime(Integer OverduePaymentAgoTime) {
        this.OverduePaymentAgoTime = OverduePaymentAgoTime;
    }
    public Integer getOverdueSubTimeM() {
        return OverdueSubTimeM;
    }

    public void setOverdueSubTimeM(Integer OverdueSubTimeM) {
        this.OverdueSubTimeM = OverdueSubTimeM;
    }
    public Integer getOverdueSignAgoUnitM() {
        return OverdueSignAgoUnitM;
    }

    public void setOverdueSignAgoUnitM(Integer OverdueSignAgoUnitM) {
        this.OverdueSignAgoUnitM = OverdueSignAgoUnitM;
    }
    public Date getVersionEndTime() {
        return VersionEndTime;
    }

    public void setVersionEndTime(Date VersionEndTime) {
        this.VersionEndTime = VersionEndTime;
    }
    public Integer getOverdueFirstVisitTime() {
        return OverdueFirstVisitTime;
    }

    public void setOverdueFirstVisitTime(Integer OverdueFirstVisitTime) {
        this.OverdueFirstVisitTime = OverdueFirstVisitTime;
    }
    public Integer getOverdueSignAgoUnit() {
        return OverdueSignAgoUnit;
    }

    public void setOverdueSignAgoUnit(Integer OverdueSignAgoUnit) {
        this.OverdueSignAgoUnit = OverdueSignAgoUnit;
    }
    public Integer getOverdueUnPaymentM() {
        return OverdueUnPaymentM;
    }

    public void setOverdueUnPaymentM(Integer OverdueUnPaymentM) {
        this.OverdueUnPaymentM = OverdueUnPaymentM;
    }
    public Integer getOverduePaymentMTime() {
        return OverduePaymentMTime;
    }

    public void setOverduePaymentMTime(Integer OverduePaymentMTime) {
        this.OverduePaymentMTime = OverduePaymentMTime;
    }
    public Integer getOverdueFollowupM() {
        return OverdueFollowupM;
    }

    public void setOverdueFollowupM(Integer OverdueFollowupM) {
        this.OverdueFollowupM = OverdueFollowupM;
    }
    public Integer getOverdueSignAgoM() {
        return OverdueSignAgoM;
    }

    public void setOverdueSignAgoM(Integer OverdueSignAgoM) {
        this.OverdueSignAgoM = OverdueSignAgoM;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getOverduePaymentAgoUnit() {
        return OverduePaymentAgoUnit;
    }

    public void setOverduePaymentAgoUnit(Integer OverduePaymentAgoUnit) {
        this.OverduePaymentAgoUnit = OverduePaymentAgoUnit;
    }
    public Integer getOverdueFollowupAgoUnit() {
        return OverdueFollowupAgoUnit;
    }

    public void setOverdueFollowupAgoUnit(Integer OverdueFollowupAgoUnit) {
        this.OverdueFollowupAgoUnit = OverdueFollowupAgoUnit;
    }
    public Integer getOverdueSubAgoTimeM() {
        return OverdueSubAgoTimeM;
    }

    public void setOverdueSubAgoTimeM(Integer OverdueSubAgoTimeM) {
        this.OverdueSubAgoTimeM = OverdueSubAgoTimeM;
    }
    public Date getRuleStartTime() {
        return RuleStartTime;
    }

    public void setRuleStartTime(Date RuleStartTime) {
        this.RuleStartTime = RuleStartTime;
    }
    public Integer getOverdueSignTime() {
        return OverdueSignTime;
    }

    public void setOverdueSignTime(Integer OverdueSignTime) {
        this.OverdueSignTime = OverdueSignTime;
    }
    public Integer getOverdueFirstVisitUnit() {
        return OverdueFirstVisitUnit;
    }

    public void setOverdueFirstVisitUnit(Integer OverdueFirstVisitUnit) {
        this.OverdueFirstVisitUnit = OverdueFirstVisitUnit;
    }
    public Integer getOverdueSubAgoUnit() {
        return OverdueSubAgoUnit;
    }

    public void setOverdueSubAgoUnit(Integer OverdueSubAgoUnit) {
        this.OverdueSubAgoUnit = OverdueSubAgoUnit;
    }
    public Integer getOverdueSignMTime() {
        return OverdueSignMTime;
    }

    public void setOverdueSignMTime(Integer OverdueSignMTime) {
        this.OverdueSignMTime = OverdueSignMTime;
    }
    public Integer getOverduePaymentAgoUnitM() {
        return OverduePaymentAgoUnitM;
    }

    public void setOverduePaymentAgoUnitM(Integer OverduePaymentAgoUnitM) {
        this.OverduePaymentAgoUnitM = OverduePaymentAgoUnitM;
    }
    public Integer getOverdueFollowupAgoUnitM() {
        return OverdueFollowupAgoUnitM;
    }

    public void setOverdueFollowupAgoUnitM(Integer OverdueFollowupAgoUnitM) {
        this.OverdueFollowupAgoUnitM = OverdueFollowupAgoUnitM;
    }
    public Integer getOverduePaymentTime() {
        return OverduePaymentTime;
    }

    public void setOverduePaymentTime(Integer OverduePaymentTime) {
        this.OverduePaymentTime = OverduePaymentTime;
    }
    public Integer getOverdueUnSubM() {
        return OverdueUnSubM;
    }

    public void setOverdueUnSubM(Integer OverdueUnSubM) {
        this.OverdueUnSubM = OverdueUnSubM;
    }
    public Integer getOverdueFollowupAgo() {
        return OverdueFollowupAgo;
    }

    public void setOverdueFollowupAgo(Integer OverdueFollowupAgo) {
        this.OverdueFollowupAgo = OverdueFollowupAgo;
    }
    public Integer getOverdueSubUnit() {
        return OverdueSubUnit;
    }

    public void setOverdueSubUnit(Integer OverdueSubUnit) {
        this.OverdueSubUnit = OverdueSubUnit;
    }
    public Integer getOverduePaymentUnit() {
        return OverduePaymentUnit;
    }

    public void setOverduePaymentUnit(Integer OverduePaymentUnit) {
        this.OverduePaymentUnit = OverduePaymentUnit;
    }
    public Integer getOverdueSubAgo() {
        return OverdueSubAgo;
    }

    public void setOverdueSubAgo(Integer OverdueSubAgo) {
        this.OverdueSubAgo = OverdueSubAgo;
    }
    public Integer getOverdueFollowupAgoM() {
        return OverdueFollowupAgoM;
    }

    public void setOverdueFollowupAgoM(Integer OverdueFollowupAgoM) {
        this.OverdueFollowupAgoM = OverdueFollowupAgoM;
    }
    public Integer getOverdueSignUnit() {
        return OverdueSignUnit;
    }

    public void setOverdueSignUnit(Integer OverdueSignUnit) {
        this.OverdueSignUnit = OverdueSignUnit;
    }
    public Integer getOverdueSignMUnit() {
        return OverdueSignMUnit;
    }

    public void setOverdueSignMUnit(Integer OverdueSignMUnit) {
        this.OverdueSignMUnit = OverdueSignMUnit;
    }
    public Integer getOverdueSignAgo() {
        return OverdueSignAgo;
    }

    public void setOverdueSignAgo(Integer OverdueSignAgo) {
        this.OverdueSignAgo = OverdueSignAgo;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Integer getWaitForVisitM() {
        return WaitForVisitM;
    }

    public void setWaitForVisitM(Integer WaitForVisitM) {
        this.WaitForVisitM = WaitForVisitM;
    }
    public Integer getOverduePaymentAgo() {
        return OverduePaymentAgo;
    }

    public void setOverduePaymentAgo(Integer OverduePaymentAgo) {
        this.OverduePaymentAgo = OverduePaymentAgo;
    }
    public Integer getOverdueSubAgoUnitM() {
        return OverdueSubAgoUnitM;
    }

    public void setOverdueSubAgoUnitM(Integer OverdueSubAgoUnitM) {
        this.OverdueSubAgoUnitM = OverdueSubAgoUnitM;
    }
    public Integer getWaitForFollowup() {
        return WaitForFollowup;
    }

    public void setWaitForFollowup(Integer WaitForFollowup) {
        this.WaitForFollowup = WaitForFollowup;
    }
    public Integer getOverdueFollowupUNit() {
        return OverdueFollowupUNit;
    }

    public void setOverdueFollowupUNit(Integer OverdueFollowupUNit) {
        this.OverdueFollowupUNit = OverdueFollowupUNit;
    }
    public Integer getOverdueSubUnitM() {
        return OverdueSubUnitM;
    }

    public void setOverdueSubUnitM(Integer OverdueSubUnitM) {
        this.OverdueSubUnitM = OverdueSubUnitM;
    }
    public Integer getOverdueSubAgoTime() {
        return OverdueSubAgoTime;
    }

    public void setOverdueSubAgoTime(Integer OverdueSubAgoTime) {
        this.OverdueSubAgoTime = OverdueSubAgoTime;
    }
    public Date getVersionStartTime() {
        return VersionStartTime;
    }

    public void setVersionStartTime(Date VersionStartTime) {
        this.VersionStartTime = VersionStartTime;
    }
    public Integer getOverdueFirstVisit() {
        return OverdueFirstVisit;
    }

    public void setOverdueFirstVisit(Integer OverdueFirstVisit) {
        this.OverdueFirstVisit = OverdueFirstVisit;
    }
    public Integer getOverduePayment() {
        return OverduePayment;
    }

    public void setOverduePayment(Integer OverduePayment) {
        this.OverduePayment = OverduePayment;
    }
    public Integer getOverduePaymentAgoM() {
        return OverduePaymentAgoM;
    }

    public void setOverduePaymentAgoM(Integer OverduePaymentAgoM) {
        this.OverduePaymentAgoM = OverduePaymentAgoM;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getWaitForVisit() {
        return WaitForVisit;
    }

    public void setWaitForVisit(Integer WaitForVisit) {
        this.WaitForVisit = WaitForVisit;
    }
    public Integer getOverdueSubM() {
        return OverdueSubM;
    }

    public void setOverdueSubM(Integer OverdueSubM) {
        this.OverdueSubM = OverdueSubM;
    }
    public Integer getOverdueFollowup() {
        return OverdueFollowup;
    }

    public void setOverdueFollowup(Integer OverdueFollowup) {
        this.OverdueFollowup = OverdueFollowup;
    }
    public Integer getOverdueSignAgoTimeM() {
        return OverdueSignAgoTimeM;
    }

    public void setOverdueSignAgoTimeM(Integer OverdueSignAgoTimeM) {
        this.OverdueSignAgoTimeM = OverdueSignAgoTimeM;
    }
    public Integer getOverdueUnSub() {
        return OverdueUnSub;
    }

    public void setOverdueUnSub(Integer OverdueUnSub) {
        this.OverdueUnSub = OverdueUnSub;
    }
    public Integer getOverdueFollowupAgoTimeM() {
        return OverdueFollowupAgoTimeM;
    }

    public void setOverdueFollowupAgoTimeM(Integer OverdueFollowupAgoTimeM) {
        this.OverdueFollowupAgoTimeM = OverdueFollowupAgoTimeM;
    }
    public Integer getOverdueSub() {
        return OverdueSub;
    }

    public void setOverdueSub(Integer OverdueSub) {
        this.OverdueSub = OverdueSub;
    }
    public Integer getOverduePaymentMUnit() {
        return OverduePaymentMUnit;
    }

    public void setOverduePaymentMUnit(Integer OverduePaymentMUnit) {
        this.OverduePaymentMUnit = OverduePaymentMUnit;
    }
    public Integer getOverdueFollowupAgoTime() {
        return OverdueFollowupAgoTime;
    }

    public void setOverdueFollowupAgoTime(Integer OverdueFollowupAgoTime) {
        this.OverdueFollowupAgoTime = OverdueFollowupAgoTime;
    }
    public Integer getWaitForFollowupM() {
        return WaitForFollowupM;
    }

    public void setWaitForFollowupM(Integer WaitForFollowupM) {
        this.WaitForFollowupM = WaitForFollowupM;
    }
    public Integer getOverdueSign() {
        return OverdueSign;
    }

    public void setOverdueSign(Integer OverdueSign) {
        this.OverdueSign = OverdueSign;
    }

    @Override
    public String toString() {
        return "BRemindrule{" +
        "OverdueSignM=" + OverdueSignM +
        ", OverdueUnSignM=" + OverdueUnSignM +
        ", OverdueUnFollowup=" + OverdueUnFollowup +
        ", ProjectID=" + ProjectID +
        ", OverdueFollowupTime=" + OverdueFollowupTime +
        ", OverduePaymentM=" + OverduePaymentM +
        ", OverdueSignAgoTime=" + OverdueSignAgoTime +
        ", OverdueUnSign=" + OverdueUnSign +
        ", OverdueSubAgoM=" + OverdueSubAgoM +
        ", OverdueUnPayment=" + OverdueUnPayment +
        ", OverdueSubTime=" + OverdueSubTime +
        ", Creator=" + Creator +
        ", OverduePaymentAgoTimeM=" + OverduePaymentAgoTimeM +
        ", OverduePaymentAgoTime=" + OverduePaymentAgoTime +
        ", OverdueSubTimeM=" + OverdueSubTimeM +
        ", OverdueSignAgoUnitM=" + OverdueSignAgoUnitM +
        ", VersionEndTime=" + VersionEndTime +
        ", OverdueFirstVisitTime=" + OverdueFirstVisitTime +
        ", OverdueSignAgoUnit=" + OverdueSignAgoUnit +
        ", OverdueUnPaymentM=" + OverdueUnPaymentM +
        ", OverduePaymentMTime=" + OverduePaymentMTime +
        ", OverdueFollowupM=" + OverdueFollowupM +
        ", OverdueSignAgoM=" + OverdueSignAgoM +
        ", Editor=" + Editor +
        ", OverduePaymentAgoUnit=" + OverduePaymentAgoUnit +
        ", OverdueFollowupAgoUnit=" + OverdueFollowupAgoUnit +
        ", OverdueSubAgoTimeM=" + OverdueSubAgoTimeM +
        ", RuleStartTime=" + RuleStartTime +
        ", OverdueSignTime=" + OverdueSignTime +
        ", OverdueFirstVisitUnit=" + OverdueFirstVisitUnit +
        ", OverdueSubAgoUnit=" + OverdueSubAgoUnit +
        ", OverdueSignMTime=" + OverdueSignMTime +
        ", OverduePaymentAgoUnitM=" + OverduePaymentAgoUnitM +
        ", OverdueFollowupAgoUnitM=" + OverdueFollowupAgoUnitM +
        ", OverduePaymentTime=" + OverduePaymentTime +
        ", OverdueUnSubM=" + OverdueUnSubM +
        ", OverdueFollowupAgo=" + OverdueFollowupAgo +
        ", OverdueSubUnit=" + OverdueSubUnit +
        ", OverduePaymentUnit=" + OverduePaymentUnit +
        ", OverdueSubAgo=" + OverdueSubAgo +
        ", OverdueFollowupAgoM=" + OverdueFollowupAgoM +
        ", OverdueSignUnit=" + OverdueSignUnit +
        ", OverdueSignMUnit=" + OverdueSignMUnit +
        ", OverdueSignAgo=" + OverdueSignAgo +
        ", IsDel=" + IsDel +
        ", WaitForVisitM=" + WaitForVisitM +
        ", OverduePaymentAgo=" + OverduePaymentAgo +
        ", OverdueSubAgoUnitM=" + OverdueSubAgoUnitM +
        ", WaitForFollowup=" + WaitForFollowup +
        ", OverdueFollowupUNit=" + OverdueFollowupUNit +
        ", OverdueSubUnitM=" + OverdueSubUnitM +
        ", OverdueSubAgoTime=" + OverdueSubAgoTime +
        ", VersionStartTime=" + VersionStartTime +
        ", OverdueFirstVisit=" + OverdueFirstVisit +
        ", OverduePayment=" + OverduePayment +
        ", OverduePaymentAgoM=" + OverduePaymentAgoM +
        ", Status=" + Status +
        ", id=" + id +
        ", WaitForVisit=" + WaitForVisit +
        ", OverdueSubM=" + OverdueSubM +
        ", OverdueFollowup=" + OverdueFollowup +
        ", OverdueSignAgoTimeM=" + OverdueSignAgoTimeM +
        ", OverdueUnSub=" + OverdueUnSub +
        ", OverdueFollowupAgoTimeM=" + OverdueFollowupAgoTimeM +
        ", OverdueSub=" + OverdueSub +
        ", OverduePaymentMUnit=" + OverduePaymentMUnit +
        ", OverdueFollowupAgoTime=" + OverdueFollowupAgoTime +
        ", WaitForFollowupM=" + WaitForFollowupM +
        ", OverdueSign=" + OverdueSign +
        "}";
    }
}
