package com.tahoecn.xkc.model.miniprogram.vo;


/**
 * <p>
 *  项目-人员-组织关系类参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public class RelationshipVO {

    private String userId;

    private String name;

    private String userName;

    private String projectId;

    private String projectName;

    private String roleId;

    private String roleName;

    private String orgId;

    private String orgName;

    private String orgCode;

    private String mobile;

    private String channelTypeId;

    private String channelTypeName;

    private Integer isNoAllotRole;
    private Integer allowDeviceType;
    private String ucId;

    public String getUcId() {
        return ucId;
    }

    public void setUcId(String ucId) {
        this.ucId = ucId;
    }

    public Integer getIsNoAllotRole() {
        return isNoAllotRole;
    }

    public void setIsNoAllotRole(Integer isNoAllotRole) {
        this.isNoAllotRole = isNoAllotRole;
    }

    public Integer getAllowDeviceType() {
        return allowDeviceType;
    }

    public void setAllowDeviceType(Integer allowDeviceType) {
        this.allowDeviceType = allowDeviceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(String channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getChannelTypeName() {
        return channelTypeName;
    }

    public void setChannelTypeName(String channelTypeName) {
        this.channelTypeName = channelTypeName;
    }
}
