package com.tahoecn.xkc.model.project;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 
 * </p>
 *	用户对应项目表
 * @author YYY
 * @since 2019-06-28
 */
@TableName("S_AccountUserProject")
@ApiModel(value="SAccountuserproject对象", description="")
public class SAccountuserproject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ProjectID")
    private String ProjectID;//项目id

    @TableId("UserID")
    private String UserID;//用户id

    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "SAccountuserproject{" +
        "ProjectID=" + ProjectID +
        ", UserID=" + UserID +
        "}";
    }
}
