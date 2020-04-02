package com.tahoecn.xkc.model.sys;

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
 * @since 2020-03-31
 */
@ApiModel(value="SysAccessManagement对象", description="")
public class SysAccessManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String sysSid;

    @ApiModelProperty(value = "系统名称")
    private String sysName;

    @ApiModelProperty(value = "管理员名称")
    private String sysAdmin;

    @ApiModelProperty(value = "创建时间")
    private Date sysCreateTime;

    @ApiModelProperty(value = "更新时间")
    private Date sysUpdateTime;

    @ApiModelProperty(value = "允许访问ip")
    private String fdAllowIp;

    public String getSysSid() {
        return sysSid;
    }

    public void setSysSid(String sysSid) {
        this.sysSid = sysSid;
    }
    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
    public String getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(String sysAdmin) {
        this.sysAdmin = sysAdmin;
    }
    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }
    public Date getSysUpdateTime() {
        return sysUpdateTime;
    }

    public void setSysUpdateTime(Date sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }
    public String getFdAllowIp() {
        return fdAllowIp;
    }

    public void setFdAllowIp(String fdAllowIp) {
        this.fdAllowIp = fdAllowIp;
    }

    @Override
    public String toString() {
        return "SysAccessManagement{" +
        "sysSid=" + sysSid +
        ", sysName=" + sysName +
        ", sysAdmin=" + sysAdmin +
        ", sysCreateTime=" + sysCreateTime +
        ", sysUpdateTime=" + sysUpdateTime +
        ", fdAllowIp=" + fdAllowIp +
        "}";
    }
}
