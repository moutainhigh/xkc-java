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
@TableName("S_CommonJobsMenuRel")
@ApiModel(value="SCommonjobsmenurel对象", description="")
public class SCommonjobsmenurel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("JobID")
    private String JobID;

    @TableField("MenuID")
    private String MenuID;

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
    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String MenuID) {
        this.MenuID = MenuID;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }

    @Override
    public String toString() {
        return "SCommonjobsmenurel{" +
        "id=" + id +
        ", JobID=" + JobID +
        ", MenuID=" + MenuID +
        ", IsDel=" + IsDel +
        "}";
    }
}
