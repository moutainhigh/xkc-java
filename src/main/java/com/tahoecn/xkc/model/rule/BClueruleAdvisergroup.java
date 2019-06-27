package com.tahoecn.xkc.model.rule;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@TableName("B_ClueRule_AdviserGroup")
@ApiModel(value="BClueruleAdvisergroup对象", description="")
public class BClueruleAdvisergroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Status")
    private Integer Status;

    @TableField("AdviserGroupID")
    private String AdviserGroupID;

    @TableField("Editor")
    private String Editor;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    @TableField("ClueRuleID")
    private String ClueRuleID;

    @TableId("ID")
    private String id;

    @TableField("Creator")
    private String Creator;

    @TableField("EditTime")
    private Date EditTime;

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public String getAdviserGroupID() {
        return AdviserGroupID;
    }

    public void setAdviserGroupID(String AdviserGroupID) {
        this.AdviserGroupID = AdviserGroupID;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    public String getClueRuleID() {
        return ClueRuleID;
    }

    public void setClueRuleID(String ClueRuleID) {
        this.ClueRuleID = ClueRuleID;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }

    @Override
    public String toString() {
        return "BClueruleAdvisergroup{" +
        "Status=" + Status +
        ", AdviserGroupID=" + AdviserGroupID +
        ", Editor=" + Editor +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        ", ClueRuleID=" + ClueRuleID +
        ", id=" + id +
        ", Creator=" + Creator +
        ", EditTime=" + EditTime +
        "}";
    }
}
