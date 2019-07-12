package com.tahoecn.xkc.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-12
 */
@TableName("S_CommonJobsFunctionsRel")
@ApiModel(value="SCommonjobsfunctionsrel对象", description="")
public class SCommonjobsfunctionsrel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("JobID")
    private String JobID;

    @TableField("FuncID")
    private String FuncID;

    @TableField("IsDel")
    private Integer IsDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getJobID() {
        return JobID;
    }

    public void setJobID(String JobID) {
        this.JobID = JobID;
    }
    public String getFuncID() {
        return FuncID;
    }

    public void setFuncID(String FuncID) {
        this.FuncID = FuncID;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }

    @Override
    public String toString() {
        return "SCommonjobsfunctionsrel{" +
        "id=" + id +
        ", JobID=" + JobID +
        ", FuncID=" + FuncID +
        ", IsDel=" + IsDel +
        "}";
    }
}
