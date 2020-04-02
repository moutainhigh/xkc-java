package com.tahoecn.xkc.model.customer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mystic
 */
@TableName("B_CustomerWhiteList")
@ApiModel(value="BCustomerWhiteList对象", description="白名单客户")
public class BCustomerWhiteList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @TableField("CustomerID")
    private String customerID;

    @TableField("CustomerMobile")
    private String customerMobile;

    @TableField("Creator")
    private String creator;

    @TableField("CreateTime")
    private Date createTime;

    @TableField("Editor")
    private String editor;

    @TableField("EditeTime")
    private Date editeTime;

    @TableField("IsDel")
    private Integer IsDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getEditeTime() {
        return editeTime;
    }

    public void setEditeTime(Date editeTime) {
        this.editeTime = editeTime;
    }

    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer isDel) {
        IsDel = isDel;
    }

    @Override
    public String toString() {
        return "BCustomerWhiteList{" +
                "id='" + id + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerMobile='" + customerMobile + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", editor='" + editor + '\'' +
                ", editeTime=" + editeTime +
                ", IsDel=" + IsDel +
                '}';
    }
}
