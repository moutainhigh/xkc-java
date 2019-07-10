package com.tahoecn.xkc.model.salegroup;

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
 * @since 2019-07-05
 */
@TableName("B_SalesUser")
@ApiModel(value="BSalesuser对象", description="")
public class BSalesuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1=内部,2=外部")
    @TableField("Nature")
    private Integer Nature;

    @ApiModelProperty(value = "名片图")
    @TableField("VisitingCardPic")
    private String VisitingCardPic;

    @ApiModelProperty(value = "自我介绍")
    @TableField("SelfSummary")
    private String SelfSummary;

    @ApiModelProperty(value = "0=否，1=是")
    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "0=离职，1=在职")
    @TableField("Status")
    private Integer Status;

    @TableField("UserPwd")
    private String UserPwd;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Name")
    private String Name;

    @TableField("TelPhone")
    private String TelPhone;

    @TableField("Editor")
    private String Editor;

    @TableId("ID")
    private String id;

    @TableField("UserName")
    private String UserName;

    @TableField("Creator")
    private String Creator;

    @TableField("HeadImg")
    private String HeadImg;

    @TableField("EditTime")
    private Date EditTime;

    public Integer getNature() {
        return Nature;
    }

    public void setNature(Integer Nature) {
        this.Nature = Nature;
    }
    public String getVisitingCardPic() {
        return VisitingCardPic;
    }

    public void setVisitingCardPic(String VisitingCardPic) {
        this.VisitingCardPic = VisitingCardPic;
    }
    public String getSelfSummary() {
        return SelfSummary;
    }

    public void setSelfSummary(String SelfSummary) {
        this.SelfSummary = SelfSummary;
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
    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String UserPwd) {
        this.UserPwd = UserPwd;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getTelPhone() {
        return TelPhone;
    }

    public void setTelPhone(String TelPhone) {
        this.TelPhone = TelPhone;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String HeadImg) {
        this.HeadImg = HeadImg;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }

    @Override
    public String toString() {
        return "BSalesuser{" +
        "Nature=" + Nature +
        ", VisitingCardPic=" + VisitingCardPic +
        ", SelfSummary=" + SelfSummary +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", UserPwd=" + UserPwd +
        ", CreateTime=" + CreateTime +
        ", Name=" + Name +
        ", TelPhone=" + TelPhone +
        ", Editor=" + Editor +
        ", id=" + id +
        ", UserName=" + UserName +
        ", Creator=" + Creator +
        ", HeadImg=" + HeadImg +
        ", EditTime=" + EditTime +
        "}";
    }
}
