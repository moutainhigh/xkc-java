package com.tahoecn.xkc.model.sys;

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
 * @since 2019-07-03
 */
@TableName("B_AppUpgrade")
@ApiModel(value="BAppupgrade对象", description="")
public class BAppupgrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "IOS ANDROID")
    @TableField("Platform")
    private String Platform;

    @ApiModelProperty(value = "1 是 0 否")
    @TableField("IsForceUpgrade")
    private Integer IsForceUpgrade;

    @ApiModelProperty(value = "默认为:升级所有机型")
    @TableField("Model")
    private String Model;

    @ApiModelProperty(value = "1 启用 0未启用")
    @TableField("Status")
    private Integer Status;

    @ApiModelProperty(value = "1 是  0 否")
    @TableField("IsDel")
    private Integer IsDel;

    @TableField("AppVersionCode")
    private String AppVersionCode;

    @TableField("AppName")
    private String AppName;

    @TableField("Url")
    private String Url;

    @TableField("UpgradeDesc")
    private String UpgradeDesc;

    @TableField("AppSecret")
    private String AppSecret;

    @TableField("AppVersionName")
    private String AppVersionName;

    @TableField("AppKey")
    private String AppKey;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Creator")
    private String Creator;

    @TableField("ID")
    private String id;

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String Platform) {
        this.Platform = Platform;
    }
    public Integer getIsForceUpgrade() {
        return IsForceUpgrade;
    }

    public void setIsForceUpgrade(Integer IsForceUpgrade) {
        this.IsForceUpgrade = IsForceUpgrade;
    }
    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getAppVersionCode() {
        return AppVersionCode;
    }

    public void setAppVersionCode(String AppVersionCode) {
        this.AppVersionCode = AppVersionCode;
    }
    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
    public String getUpgradeDesc() {
        return UpgradeDesc;
    }

    public void setUpgradeDesc(String UpgradeDesc) {
        this.UpgradeDesc = UpgradeDesc;
    }
    public String getAppSecret() {
        return AppSecret;
    }

    public void setAppSecret(String AppSecret) {
        this.AppSecret = AppSecret;
    }
    public String getAppVersionName() {
        return AppVersionName;
    }

    public void setAppVersionName(String AppVersionName) {
        this.AppVersionName = AppVersionName;
    }
    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String AppKey) {
        this.AppKey = AppKey;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BAppupgrade{" +
        "Platform=" + Platform +
        ", IsForceUpgrade=" + IsForceUpgrade +
        ", Model=" + Model +
        ", Status=" + Status +
        ", IsDel=" + IsDel +
        ", AppVersionCode=" + AppVersionCode +
        ", AppName=" + AppName +
        ", Url=" + Url +
        ", UpgradeDesc=" + UpgradeDesc +
        ", AppSecret=" + AppSecret +
        ", AppVersionName=" + AppVersionName +
        ", AppKey=" + AppKey +
        ", CreateTime=" + CreateTime +
        ", Creator=" + Creator +
        ", id=" + id +
        "}";
    }
}
