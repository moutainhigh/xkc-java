package com.tahoecn.xkc.model.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 1
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
@TableName("V_A_Broker_MyCustomers")
@ApiModel(value="VABrokerMycustomers对象", description="1")
public class VABrokerMycustomers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ClueID")
    private String ClueID;

    @TableField("IntentProjectName")
    private String IntentProjectName;

    @TableField("Status")
    private Integer Status;

    @TableField("ReportUserID")
    private String ReportUserID;

    @TableField("ReportMethod")
    private Integer ReportMethod;

    @TableField("InvalidType")
    private Integer InvalidType;

    @TableField("StatusText")
    private String StatusText;

    private Integer isvisit;

    @TableField("Mobile")
    private String Mobile;

    @TableField("OppID")
    private String OppID;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("ReportTime")
    private Date ReportTime;

    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }
    public String getIntentProjectName() {
        return IntentProjectName;
    }

    public void setIntentProjectName(String IntentProjectName) {
        this.IntentProjectName = IntentProjectName;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getReportUserID() {
        return ReportUserID;
    }

    public void setReportUserID(String ReportUserID) {
        this.ReportUserID = ReportUserID;
    }
    public Integer getReportMethod() {
        return ReportMethod;
    }

    public void setReportMethod(Integer ReportMethod) {
        this.ReportMethod = ReportMethod;
    }
    public Integer getInvalidType() {
        return InvalidType;
    }

    public void setInvalidType(Integer InvalidType) {
        this.InvalidType = InvalidType;
    }
    public String getStatusText() {
        return StatusText;
    }

    public void setStatusText(String StatusText) {
        this.StatusText = StatusText;
    }
    public Integer getIsvisit() {
        return isvisit;
    }

    public void setIsvisit(Integer isvisit) {
        this.isvisit = isvisit;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getOppID() {
        return OppID;
    }

    public void setOppID(String OppID) {
        this.OppID = OppID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public Date getReportTime() {
        return ReportTime;
    }

    public void setReportTime(Date ReportTime) {
        this.ReportTime = ReportTime;
    }

    @Override
    public String toString() {
        return "VABrokerMycustomers{" +
        "ClueID=" + ClueID +
        ", IntentProjectName=" + IntentProjectName +
        ", Status=" + Status +
        ", ReportUserID=" + ReportUserID +
        ", ReportMethod=" + ReportMethod +
        ", InvalidType=" + InvalidType +
        ", StatusText=" + StatusText +
        ", isvisit=" + isvisit +
        ", Mobile=" + Mobile +
        ", OppID=" + OppID +
        ", CustomerName=" + CustomerName +
        ", ReportTime=" + ReportTime +
        "}";
    }
}
