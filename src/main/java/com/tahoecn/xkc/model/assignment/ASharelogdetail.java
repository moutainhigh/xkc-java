package com.tahoecn.xkc.model.assignment;

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
 * @since 2020-01-06
 */
@TableName("A_ShareLogDetail")
@ApiModel(value="ASharelogdetail对象", description="")
public class ASharelogdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ShareLogID")
    private String ShareLogID;

    @ApiModelProperty(value = "发起分享的用户ID")
    @TableField("ShareWXUserID")
    private String ShareWXUserID;

    @TableField("WXUserID")
    private String WXUserID;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @ApiModelProperty(value = "0.分享 1.扫二维码")
    @TableField("Category")
    private Integer Category;

    @ApiModelProperty(value = "是否授权记录")
    @TableField("IsAuthorize")
    private Integer IsAuthorize;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("Status")
    private Integer Status;

    @TableField("ShareProjectID")
    private String ShareProjectID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getShareLogID() {
        return ShareLogID;
    }

    public void setShareLogID(String ShareLogID) {
        this.ShareLogID = ShareLogID;
    }
    public String getShareWXUserID() {
        return ShareWXUserID;
    }

    public void setShareWXUserID(String ShareWXUserID) {
        this.ShareWXUserID = ShareWXUserID;
    }
    public String getWXUserID() {
        return WXUserID;
    }

    public void setWXUserID(String WXUserID) {
        this.WXUserID = WXUserID;
    }
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
    }
    public Integer getIsAuthorize() {
        return IsAuthorize;
    }

    public void setIsAuthorize(Integer IsAuthorize) {
        this.IsAuthorize = IsAuthorize;
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
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
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
    public String getShareProjectID() {
        return ShareProjectID;
    }

    public void setShareProjectID(String ShareProjectID) {
        this.ShareProjectID = ShareProjectID;
    }

    @Override
    public String toString() {
        return "ASharelogdetail{" +
        "id=" + id +
        ", ShareLogID=" + ShareLogID +
        ", ShareWXUserID=" + ShareWXUserID +
        ", WXUserID=" + WXUserID +
        ", AdviserGroupID=" + AdviserGroupID +
        ", Category=" + Category +
        ", IsAuthorize=" + IsAuthorize +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", ShareProjectID=" + ShareProjectID +
        "}";
    }
}
