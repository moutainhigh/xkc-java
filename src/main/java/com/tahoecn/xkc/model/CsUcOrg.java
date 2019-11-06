package com.tahoecn.xkc.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * UC组织机构信息
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
public class CsUcOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 机构全局id
     */
    private String fdSid;
    /**
     * 机构全局id
     */
    private String fdName;
    /**
     * 机构code
     */
    private String fdCode;
    /**
     * 对应机构类型的code
     */
    private String fdType;
    /**
     * 父机构名称
     */
    private String fdPname;
    /**
     * 父机构id
     */
    private String fdPsid;
    /**
     * 机构名称全路径
     */
    private String fdNameTree;
    /**
     * 机构id全路径
     */
    private String fdSidTree;
    /**
     * 显示序列
     */
    private Integer fdOrder;
    /**
     * 是否可用1可用
     */
    private Integer fdAvailable;
    /**
     * 是否删除-1不删除
     */
    private Integer fdIsdelete;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFdSid() {
        return fdSid;
    }

    public void setFdSid(String fdSid) {
        this.fdSid = fdSid;
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    public String getFdCode() {
        return fdCode;
    }

    public void setFdCode(String fdCode) {
        this.fdCode = fdCode;
    }

    public String getFdType() {
        return fdType;
    }

    public void setFdType(String fdType) {
        this.fdType = fdType;
    }

    public String getFdPname() {
        return fdPname;
    }

    public void setFdPname(String fdPname) {
        this.fdPname = fdPname;
    }

    public String getFdPsid() {
        return fdPsid;
    }

    public void setFdPsid(String fdPsid) {
        this.fdPsid = fdPsid;
    }

    public String getFdNameTree() {
        return fdNameTree;
    }

    public void setFdNameTree(String fdNameTree) {
        this.fdNameTree = fdNameTree;
    }

    public String getFdSidTree() {
        return fdSidTree;
    }

    public void setFdSidTree(String fdSidTree) {
        this.fdSidTree = fdSidTree;
    }

    public Integer getFdOrder() {
        return fdOrder;
    }

    public void setFdOrder(Integer fdOrder) {
        this.fdOrder = fdOrder;
    }

    public Integer getFdAvailable() {
        return fdAvailable;
    }

    public void setFdAvailable(Integer fdAvailable) {
        this.fdAvailable = fdAvailable;
    }

    public Integer getFdIsdelete() {
        return fdIsdelete;
    }

    public void setFdIsdelete(Integer fdIsdelete) {
        this.fdIsdelete = fdIsdelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "CsUcOrg{" +
        "id=" + id +
        ", fdSid=" + fdSid +
        ", fdName=" + fdName +
        ", fdCode=" + fdCode +
        ", fdType=" + fdType +
        ", fdPname=" + fdPname +
        ", fdPsid=" + fdPsid +
        ", fdNameTree=" + fdNameTree +
        ", fdSidTree=" + fdSidTree +
        ", fdOrder=" + fdOrder +
        ", fdAvailable=" + fdAvailable +
        ", fdIsdelete=" + fdIsdelete +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
