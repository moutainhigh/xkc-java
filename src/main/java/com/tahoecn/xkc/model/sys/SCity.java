package com.tahoecn.xkc.model.sys;

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
 * @since 2019-07-08
 */
@TableName("S_City")
@ApiModel(value="SCity对象", description="")
public class SCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("DispName")
    private String DispName;

    @TableField("Code")
    private String Code;

    @TableField("PID")
    private String pid;

    @TableField("Levels")
    private Integer Levels;

    @TableField("StandardIndex")
    private Integer StandardIndex;

    @TableField("SpecificIndex")
    private Integer SpecificIndex;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDispName() {
        return DispName;
    }

    public void setDispName(String DispName) {
        this.DispName = DispName;
    }
    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public Integer getLevels() {
        return Levels;
    }

    public void setLevels(Integer Levels) {
        this.Levels = Levels;
    }
    public Integer getStandardIndex() {
        return StandardIndex;
    }

    public void setStandardIndex(Integer StandardIndex) {
        this.StandardIndex = StandardIndex;
    }
    public Integer getSpecificIndex() {
        return SpecificIndex;
    }

    public void setSpecificIndex(Integer SpecificIndex) {
        this.SpecificIndex = SpecificIndex;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "SCity{" +
        "id=" + id +
        ", DispName=" + DispName +
        ", Code=" + Code +
        ", pid=" + pid +
        ", Levels=" + Levels +
        ", StandardIndex=" + StandardIndex +
        ", SpecificIndex=" + SpecificIndex +
        ", CreateTime=" + CreateTime +
        ", Status=" + Status +
        "}";
    }
}
