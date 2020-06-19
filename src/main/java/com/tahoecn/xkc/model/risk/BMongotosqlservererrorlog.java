package com.tahoecn.xkc.model.risk;

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
 * @since 2020-06-16
 */
@TableName("B_MongoToSqlServerErrorLog")
@ApiModel(value="BMongotosqlservererrorlog对象", description="")
public class BMongotosqlservererrorlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("Msg")
    private String Msg;

    @TableField("Error")
    private String Error;

    @TableField("Desc")
    private String Desc;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }
    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }
    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return "BMongotosqlservererrorlog{" +
        "id=" + id +
        ", Msg=" + Msg +
        ", Error=" + Error +
        ", Desc=" + Desc +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
