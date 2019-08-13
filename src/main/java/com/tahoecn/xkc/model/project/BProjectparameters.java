package com.tahoecn.xkc.model.project;

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
 * @since 2019-08-13
 */
@TableName("B_ProjectParameters")
@ApiModel(value="BProjectparameters对象", description="")
public class BProjectparameters implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ProjectID")
    private String ProjectID;

    @ApiModelProperty(value = "1. 客储抢单公共客户池 2. 客储抢单丢弃客户池 3. 分享传播抢单公共客户池 4. 分享传播抢单丢弃客户池")
    @TableField("Category")
    private Integer Category;

    @TableField("Sale")
    private Integer Sale;

    @TableField("SelfDrains")
    private Integer SelfDrains;

    @TableField("Channel")
    private Integer Channel;

    @TableField("RecommendedChannel")
    private Integer RecommendedChannel;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
    }
    public Integer getSale() {
        return Sale;
    }

    public void setSale(Integer Sale) {
        this.Sale = Sale;
    }
    public Integer getSelfDrains() {
        return SelfDrains;
    }

    public void setSelfDrains(Integer SelfDrains) {
        this.SelfDrains = SelfDrains;
    }
    public Integer getChannel() {
        return Channel;
    }

    public void setChannel(Integer Channel) {
        this.Channel = Channel;
    }
    public Integer getRecommendedChannel() {
        return RecommendedChannel;
    }

    public void setRecommendedChannel(Integer RecommendedChannel) {
        this.RecommendedChannel = RecommendedChannel;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
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

    @Override
    public String toString() {
        return "BProjectparameters{" +
        "id=" + id +
        ", ProjectID=" + ProjectID +
        ", Category=" + Category +
        ", Sale=" + Sale +
        ", SelfDrains=" + SelfDrains +
        ", Channel=" + Channel +
        ", RecommendedChannel=" + RecommendedChannel +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        "}";
    }
}
