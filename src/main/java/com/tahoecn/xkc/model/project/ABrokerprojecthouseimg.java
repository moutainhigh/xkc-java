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
@TableName("A_BrokerProjectHouseImg")
@ApiModel(value="ABrokerprojecthouseimg对象", description="")
public class ABrokerprojecthouseimg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("Remark")
    private String Remark;

    @TableField("Url")
    private String Url;

    @TableField("EditeTime")
    private Date EditeTime;

    @TableField("Creator")
    private String Creator;

    @TableField("Area")
    private String Area;

    @TableField("Name")
    private String Name;

    @TableField("Status")
    private Integer Status;

    @TableField("Editor")
    private String Editor;

    @TableField("ListIndex")
    private Integer ListIndex;

    @TableField("TypeName")
    private String TypeName;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("BrokerProjectID")
    private String BrokerProjectID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
    public Date getEditeTime() {
        return EditeTime;
    }

    public void setEditeTime(Date EditeTime) {
        this.EditeTime = EditeTime;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getListIndex() {
        return ListIndex;
    }

    public void setListIndex(Integer ListIndex) {
        this.ListIndex = ListIndex;
    }
    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
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

    @Override
    public String toString() {
        return "ABrokerprojecthouseimg{" +
        "id=" + id +
        ", CreateTime=" + CreateTime +
        ", Remark=" + Remark +
        ", Url=" + Url +
        ", EditeTime=" + EditeTime +
        ", Creator=" + Creator +
        ", Area=" + Area +
        ", Name=" + Name +
        ", Status=" + Status +
        ", Editor=" + Editor +
        ", ListIndex=" + ListIndex +
        ", TypeName=" + TypeName +
        ", IsDel=" + IsDel +
        ", BrokerProjectID=" + BrokerProjectID +
        "}";
    }
}
