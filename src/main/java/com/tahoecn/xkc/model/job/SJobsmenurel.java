package com.tahoecn.xkc.model.job;

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
 * @since 2019-07-16
 */
@TableName("S_JobsMenuRel")
@ApiModel(value="SJobsmenurel对象", description="")
public class SJobsmenurel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableField("JobID")
    private String JobID;

    @TableField("MenuID")
    private String MenuID;

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

    @Override
    public String toString() {
        return "SJobsmenurel{" +
        "id=" + id +
        ", JobID=" + JobID +
        ", MenuID=" + MenuID +
        "}";
    }
}
