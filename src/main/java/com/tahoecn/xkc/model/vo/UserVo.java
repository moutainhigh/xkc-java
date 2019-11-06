package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4481527477527400600L;
    private Long id;
    /**
     * 用户全局id
     */
    private String sid;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 登录用户名
     */
    private String username;
    /**
     * 所属部门id全路径
     */
    private String orgIdTree;
    /**
     * 所属部门名称全路径
     */
    private String orgNameTree;
    /**
     * 部门id
     */
    private String orgId;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 数据是否锁定(-1不锁定)
     */
    private Integer lockStatus;
    /**
     * 是否可用(1可用)
     */
    private Integer available;
    /**
     * 是否删除(-1不删除)
     */
    private Integer isdelete;
    /**
     * 修改标记(1user修改,2org修改)
     */
    private Integer updateSign;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 排序号
     */
    private Integer seq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrgIdTree() {
		return orgIdTree;
	}

	public void setOrgIdTree(String orgIdTree) {
		this.orgIdTree = orgIdTree;
	}

	public String getOrgNameTree() {
		return orgNameTree;
	}

	public void setOrgNameTree(String orgNameTree) {
		this.orgNameTree = orgNameTree;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getUpdateSign() {
		return updateSign;
	}

	public void setUpdateSign(Integer updateSign) {
		this.updateSign = updateSign;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

    @Override
    public String toString() {
        return "UserVo [id=" + id + ", sid=" + sid + ", name=" + name
                + ", username=" + username + ", orgIdTree=" + orgIdTree
                + ", orgNameTree=" + orgNameTree + ", orgId=" + orgId
                + ", orgName=" + orgName + ", lockStatus=" + lockStatus
                + ", available=" + available + ", isdelete=" + isdelete
                + ", updateSign=" + updateSign + ", gmtCreate=" + gmtCreate
                + ", gmtModified=" + gmtModified + ", seq=" + seq + "]";
    }
    
	
}
