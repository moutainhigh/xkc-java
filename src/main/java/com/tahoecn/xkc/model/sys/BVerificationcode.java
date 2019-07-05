package com.tahoecn.xkc.model.sys;

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
 * @since 2019-07-04
 */
@TableName("B_VerificationCode")
@ApiModel(value="BVerificationcode对象", description="")
public class BVerificationcode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("OverdueTime")
    private Date OverdueTime;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Mobile")
    private String Mobile;

    @TableField("VerificationCode")
    private String VerificationCode;

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
    public Date getOverdueTime() {
        return OverdueTime;
    }

    public void setOverdueTime(Date OverdueTime) {
        this.OverdueTime = OverdueTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(String VerificationCode) {
        this.VerificationCode = VerificationCode;
    }

    @Override
    public String toString() {
        return "BVerificationcode{" +
        "IsDel=" + IsDel +
        ", Status=" + Status +
        ", OverdueTime=" + OverdueTime +
        ", id=" + id +
        ", CreateTime=" + CreateTime +
        ", Mobile=" + Mobile +
        ", VerificationCode=" + VerificationCode +
        "}";
    }
}
