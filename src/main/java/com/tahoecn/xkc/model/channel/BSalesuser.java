package com.tahoecn.xkc.model.channel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-09
 */
@TableName("B_SalesUser")
@ApiModel(value="BSalesuser对象", description="")
public class BSalesuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("Name")
    private String Name;

    @TableField("TelPhone")
    private String TelPhone;

    @TableField("UserName")
    private String UserName;

    @TableField("UserPwd")
    private String UserPwd;

    @ApiModelProperty(value = "1=内部,2=外部")
    @TableField("Nature")
    private Integer Nature;

    @TableField("HeadImg")
    private String HeadImg;

    @TableField("Creator")
    private String Creator;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Editor")
    private String Editor;

    @TableField("EditTime")
    private Date EditTime;

    @ApiModelProperty(value = "0=否，1=是")
    @TableField("IsDel")
    private Integer IsDel;

    @ApiModelProperty(value = "0=离职，1=在职")
    @TableField("Status")
    private Integer Status;

    @TableField("VisitingCardPic")
    private String VisitingCardPic;

    @TableField("SelfSummary")
    private String SelfSummary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String UserPwd) {
        this.UserPwd = UserPwd;
    }
    public Integer getNature() {
        return Nature;
    }

    public void setNature(Integer Nature) {
        this.Nature = Nature;
    }
    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String HeadImg) {
        this.HeadImg = HeadImg;
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

    @Override
    public String toString() {
        return "BSalesuser{" +
        "id=" + id +
        ", Name=" + Name +
        ", TelPhone=" + TelPhone +
        ", UserName=" + UserName +
        ", UserPwd=" + UserPwd +
        ", Nature=" + Nature +
        ", HeadImg=" + HeadImg +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditTime=" + EditTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", VisitingCardPic=" + VisitingCardPic +
        ", SelfSummary=" + SelfSummary +
        "}";
    }
}
