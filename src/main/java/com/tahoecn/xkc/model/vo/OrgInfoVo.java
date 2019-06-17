package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrgInfoVo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3449307523723458243L;
    private Long id;
    /**
     * 机构全局id
     */
    private String sid;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构code
     */
    private String code;
    /**
     * 父机构id
     */
    private String parentSid;
    /**
     * 父机构名称
     */
    private String parentName;
    /**
     * 机构id全路径
     */
    private String sidTree;
    /**
     * 机构名称全路径
     */
    private String nameTree;
    /**
     * 显示序列
     */
    private Integer seq;
    /**
     * 是否可用(1可用)
     */
    private Integer available;
    /**
     * 是否删除(-1不删除)
     */
    private Integer isdelete;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    
    List<OrgInfoVo> childs;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getSidTree() {
		return sidTree;
	}

	public void setSidTree(String sidTree) {
		this.sidTree = sidTree;
	}

	public String getNameTree() {
		return nameTree;
	}

	public void setNameTree(String nameTree) {
		this.nameTree = nameTree;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public List<OrgInfoVo> getChilds() {
		return childs;
	}

	public void setChilds(List<OrgInfoVo> childs) {
		this.childs = childs;
	}

    @Override
    public String toString() {
        return "OrgInfoVo [id=" + id + ", sid=" + sid + ", name=" + name
                + ", code=" + code + ", parentSid=" + parentSid
                + ", parentName=" + parentName + ", sidTree=" + sidTree
                + ", nameTree=" + nameTree + ", seq=" + seq + ", available="
                + available + ", isdelete=" + isdelete + ", gmtCreate="
                + gmtCreate + ", gmtModified=" + gmtModified + ", childs="
                + childs + "]";
    }
    
	
}
