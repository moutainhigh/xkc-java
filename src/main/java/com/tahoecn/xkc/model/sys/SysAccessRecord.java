package com.tahoecn.xkc.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="SysAccessRecord对象", description="")
public class SysAccessRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "访问ip")
    private String accessIp;

    @ApiModelProperty(value = "接口名称")
    private String interfaceName;

    @ApiModelProperty(value = "接口状态(0:成功 1:失败)")
    private String interfaceState;

    @ApiModelProperty(value = "访问时间")
    private Date accessTime;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private String userId;

    @ApiModelProperty(value = "失败原因(状态为1时记录)")
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    public String getInterfaceState() {
        return interfaceState;
    }

    public void setInterfaceState(String interfaceState) {
        this.interfaceState = interfaceState;
    }
    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "SysAccessRecord{" +
        "id=" + id +
        ", accessIp=" + accessIp +
        ", interfaceName=" + interfaceName +
        ", interfaceState=" + interfaceState +
        ", accessTime=" + accessTime +
        ", userId=" + userId +
        ", reason=" + reason +
        "}";
    }
}
