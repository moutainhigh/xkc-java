package com.tahoecn.xkc.model.project;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
 * @since 2019-07-02
 */
@TableName("B_ProjectCollection")
@ApiModel(value="BProjectcollection对象", description="")
public class BProjectcollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("ProjectId")
    private String ProjectId;

    @TableField("UserId")
    private String UserId;

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    @Override
    public String toString() {
        return "BProjectcollection{" +
        "CreateTime=" + CreateTime +
        ", ProjectId=" + ProjectId +
        ", UserId=" + UserId +
        "}";
    }
}
