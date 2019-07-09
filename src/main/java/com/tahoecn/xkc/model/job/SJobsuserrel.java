package com.tahoecn.xkc.model.job;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@TableName("S_JobsUserRel")
@ApiModel(value="SJobsuserrel对象", description="")
public class SJobsuserrel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("AccountID")
    private String AccountID;

    @TableField("JobID")
    private String JobID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String AccountID) {
        this.AccountID = AccountID;
    }
    public String getJobID() {
        return JobID;
    }

    public void setJobID(String JobID) {
        this.JobID = JobID;
    }

    @Override
    public String toString() {
        return "SJobsuserrel{" +
        "id=" + id +
        ", AccountID=" + AccountID +
        ", JobID=" + JobID +
        "}";
    }
}
