package com.tahoecn.xkc.model.project;

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
 * @since 2019-06-28
 */
@TableName("S_AccountUserProjectJob")
@ApiModel(value="SAccountuserprojectjob对象", description="")
public class SAccountuserprojectjob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("UserID")
    private String UserID;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("JobCode")
    private String JobCode;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getJobCode() {
        return JobCode;
    }

    public void setJobCode(String JobCode) {
        this.JobCode = JobCode;
    }

    @Override
    public String toString() {
        return "SAccountuserprojectjob{" +
        "UserID=" + UserID +
        ", ProjectID=" + ProjectID +
        ", JobCode=" + JobCode +
        "}";
    }
}
