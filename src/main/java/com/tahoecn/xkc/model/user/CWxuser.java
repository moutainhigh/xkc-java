package com.tahoecn.xkc.model.user;

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
 * @since 2019-07-17
 */
@TableName("C_WXUser")
@ApiModel(value="CWxuser对象", description="")
public class CWxuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("OpenID")
    private String OpenID;

    @TableField("UnionID")
    private String UnionID;

    @TableField("ShareProjectID")
    private String ShareProjectID;

    @TableField("Name")
    private String Name;

    @TableField("NickName")
    private String NickName;

    @ApiModelProperty(value = "0.楼盘鉴赏 1.户型图 2.周边")
    @TableField("Gender")
    private Integer Gender;

    @TableField("AvatarUrl")
    private String AvatarUrl;

    @TableField("Province")
    private String Province;

    @TableField("City")
    private String City;

    @TableField("Mobile")
    private String Mobile;

    @TableField("ChannelUserID")
    private String ChannelUserID;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @TableField("SessionKey")
    private String SessionKey;

    @TableField("BindChannelUserID")
    private String BindChannelUserID;

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

    @TableField("IsFirstAuthorize")
    private Integer IsFirstAuthorize;

    @TableField("BindAdviserGroupID")
    private String BindAdviserGroupID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOpenID() {
        return OpenID;
    }

    public void setOpenID(String OpenID) {
        this.OpenID = OpenID;
    }
    public String getUnionID() {
        return UnionID;
    }

    public void setUnionID(String UnionID) {
        this.UnionID = UnionID;
    }
    public String getShareProjectID() {
        return ShareProjectID;
    }

    public void setShareProjectID(String ShareProjectID) {
        this.ShareProjectID = ShareProjectID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
    public Integer getGender() {
        return Gender;
    }

    public void setGender(Integer Gender) {
        this.Gender = Gender;
    }
    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String AvatarUrl) {
        this.AvatarUrl = AvatarUrl;
    }
    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }
    public String getChannelUserID() {
        return ChannelUserID;
    }

    public void setChannelUserID(String ChannelUserID) {
        this.ChannelUserID = ChannelUserID;
    }
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String SessionKey) {
        this.SessionKey = SessionKey;
    }
    public String getBindChannelUserID() {
        return BindChannelUserID;
    }

    public void setBindChannelUserID(String BindChannelUserID) {
        this.BindChannelUserID = BindChannelUserID;
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
    public Integer getIsFirstAuthorize() {
        return IsFirstAuthorize;
    }

    public void setIsFirstAuthorize(Integer IsFirstAuthorize) {
        this.IsFirstAuthorize = IsFirstAuthorize;
    }
    public String getBindAdviserGroupID() {
        return BindAdviserGroupID;
    }

    public void setBindAdviserGroupID(String BindAdviserGroupID) {
        this.BindAdviserGroupID = BindAdviserGroupID;
    }

    @Override
    public String toString() {
        return "CWxuser{" +
        "id=" + id +
        ", OpenID=" + OpenID +
        ", UnionID=" + UnionID +
        ", ShareProjectID=" + ShareProjectID +
        ", Name=" + Name +
        ", NickName=" + NickName +
        ", Gender=" + Gender +
        ", AvatarUrl=" + AvatarUrl +
        ", Province=" + Province +
        ", City=" + City +
        ", Mobile=" + Mobile +
        ", ChannelUserID=" + ChannelUserID +
        ", AdviserGroupID=" + AdviserGroupID +
        ", SessionKey=" + SessionKey +
        ", BindChannelUserID=" + BindChannelUserID +
        ", Creator=" + Creator +
        ", CreateTime=" + CreateTime +
        ", Editor=" + Editor +
        ", EditeTime=" + EditeTime +
        ", IsDel=" + IsDel +
        ", Status=" + Status +
        ", IsFirstAuthorize=" + IsFirstAuthorize +
        ", BindAdviserGroupID=" + BindAdviserGroupID +
        "}";
    }
}
