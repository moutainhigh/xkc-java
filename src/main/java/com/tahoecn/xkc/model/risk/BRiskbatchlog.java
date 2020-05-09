package com.tahoecn.xkc.model.risk;

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
 * @since 2020-05-06
 */
@TableName("B_RiskBatchLog")
@ApiModel(value = "BRiskbatchlog对象", description = "")
public class BRiskbatchlog implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("RiskType")
    private Integer RiskType;

    @TableField("DataMaxTime")
    private Date DataMaxTime;

    @TableField("StartTime")
    private Date StartTime;

    @TableField("EndTime")
    private Date EndTime;

    @TableField("Status")
    private Integer Status;

    @TableField("ErrorMsg")
    private String ErrorMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRiskType() {
        return RiskType;
    }

    public void setRiskType(Integer RiskType) {
        this.RiskType = RiskType;
    }

    public Date getDataMaxTime() {
        return DataMaxTime;
    }

    public void setDataMaxTime(Date DataMaxTime) {
        this.DataMaxTime = DataMaxTime;
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

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    @Override
    public String toString() {
        return "BRiskbatchlog{" +
                "id=" + id +
                ", RiskType=" + RiskType +
                ", DataMaxTime=" + DataMaxTime +
                ", StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                ", Status=" + Status +
                ", ErrorMsg=" + ErrorMsg +
                "}";
    }

    @Override
    public BRiskbatchlog clone() {
        try {
            return (BRiskbatchlog) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
