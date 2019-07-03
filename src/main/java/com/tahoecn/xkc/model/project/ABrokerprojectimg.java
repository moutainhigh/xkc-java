package com.tahoecn.xkc.model.project;

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
 * @since 2019-07-02
 */
@TableName("A_BrokerProjectImg")
@ApiModel(value="ABrokerprojectimg对象", description="")
public class ABrokerprojectimg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Url")
    private String Url;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("Editor")
    private String Editor;

    @TableField("IsFirst")
    private Integer IsFirst;

    @TableId("ID")
    private String id;

    @TableField("Status")
    private Integer Status;

    @TableField("Creator")
    private String Creator;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("BrokerProjectID")
    private String BrokerProjectID;

    @TableField("Remark")
    private String Remark;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getIsFirst() {
        return IsFirst;
    }

    public void setIsFirst(Integer IsFirst) {
        this.IsFirst = IsFirst;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public String getBrokerProjectID() {
        return BrokerProjectID;
    }

    public void setBrokerProjectID(String BrokerProjectID) {
        this.BrokerProjectID = BrokerProjectID;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    @Override
    public String toString() {
        return "ABrokerprojectimg{" +
        "Url=" + Url +
        ", ListIndex=" + ListIndex +
        ", Editor=" + Editor +
        ", IsFirst=" + IsFirst +
        ", id=" + id +
        ", Status=" + Status +
        ", Creator=" + Creator +
        ", EditeTime=" + EditeTime +
        ", CreateTime=" + CreateTime +
        ", IsDel=" + IsDel +
        ", BrokerProjectID=" + BrokerProjectID +
        ", Remark=" + Remark +
        "}";
    }
}
