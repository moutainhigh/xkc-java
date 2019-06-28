package com.tahoecn.xkc.model.rule;

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
 * @since 2019-06-25
 */
@TableName("B_RemindChannel")
@ApiModel(value="BRemindchannel对象", description="")
public class BRemindchannel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分配提醒")
    @TableField("AllotRemind")
    private Integer AllotRemind;

    @ApiModelProperty(value = "认筹提醒")
    @TableField("RecognizeRemind")
    private Integer RecognizeRemind;

    @ApiModelProperty(value = "认购提醒")
    @TableField("OverdueSubRemind")
    private Integer OverdueSubRemind;

    @ApiModelProperty(value = "签约提醒")
    @TableField("OverdueSignRemind")
    private Integer OverdueSignRemind;

    @ApiModelProperty(value = "退房提醒")
    @TableField("CheckOutRemind")
    private Integer CheckOutRemind;

    @ApiModelProperty(value = "客户无效提醒")
    @TableField("CustomerInvalidRemind")
    private Integer CustomerInvalidRemind;

    @ApiModelProperty(value = "渠道来源0.推荐 1.自有 2.分销")
    @TableField("ChannelSource")
    private Integer ChannelSource;

    @ApiModelProperty(value = "客户到访提醒")
    @TableField("CustomerVisitsRemind")
    private Integer CustomerVisitsRemind;

    @TableField("Editor")
    private String Editor;

    @TableId("ID")
    private String id;

    @TableField("FollowUpOverdue")
    private Integer FollowUpOverdue;

    @TableField("Status")
    private Integer Status;

    @TableField("EditTime")
    private Date EditTime;

    @TableField("FollowUpOverdueBefore")
    private Integer FollowUpOverdueBefore;

    @TableField("Creator")
    private String Creator;

    @TableField("ProjectID")
    private String ProjectID;

    @TableField("FollowUpOverdueBeforeNum")
    private Integer FollowUpOverdueBeforeNum;

    @TableField("IsDel")
    private Integer IsDel;

    @TableField("CreateTime")
    private Date CreateTime;

    public Integer getAllotRemind() {
        return AllotRemind;
    }

    public void setAllotRemind(Integer AllotRemind) {
        this.AllotRemind = AllotRemind;
    }
    public Integer getRecognizeRemind() {
        return RecognizeRemind;
    }

    public void setRecognizeRemind(Integer RecognizeRemind) {
        this.RecognizeRemind = RecognizeRemind;
    }
    public Integer getOverdueSubRemind() {
        return OverdueSubRemind;
    }

    public void setOverdueSubRemind(Integer OverdueSubRemind) {
        this.OverdueSubRemind = OverdueSubRemind;
    }
    public Integer getOverdueSignRemind() {
        return OverdueSignRemind;
    }

    public void setOverdueSignRemind(Integer OverdueSignRemind) {
        this.OverdueSignRemind = OverdueSignRemind;
    }
    public Integer getCheckOutRemind() {
        return CheckOutRemind;
    }

    public void setCheckOutRemind(Integer CheckOutRemind) {
        this.CheckOutRemind = CheckOutRemind;
    }
    public Integer getCustomerInvalidRemind() {
        return CustomerInvalidRemind;
    }

    public void setCustomerInvalidRemind(Integer CustomerInvalidRemind) {
        this.CustomerInvalidRemind = CustomerInvalidRemind;
    }
    public Integer getChannelSource() {
        return ChannelSource;
    }

    public void setChannelSource(Integer ChannelSource) {
        this.ChannelSource = ChannelSource;
    }
    public Integer getCustomerVisitsRemind() {
        return CustomerVisitsRemind;
    }

    public void setCustomerVisitsRemind(Integer CustomerVisitsRemind) {
        this.CustomerVisitsRemind = CustomerVisitsRemind;
    }
    public String getEditor() {
        return Editor;
    }

    public void setEditor(String Editor) {
        this.Editor = Editor;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getFollowUpOverdue() {
        return FollowUpOverdue;
    }

    public void setFollowUpOverdue(Integer FollowUpOverdue) {
        this.FollowUpOverdue = FollowUpOverdue;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Date getEditTime() {
        return EditTime;
    }

    public void setEditTime(Date EditTime) {
        this.EditTime = EditTime;
    }
    public Integer getFollowUpOverdueBefore() {
        return FollowUpOverdueBefore;
    }

    public void setFollowUpOverdueBefore(Integer FollowUpOverdueBefore) {
        this.FollowUpOverdueBefore = FollowUpOverdueBefore;
    }
    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }
    public String getProjectID() {
        return ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }
    public Integer getFollowUpOverdueBeforeNum() {
        return FollowUpOverdueBeforeNum;
    }

    public void setFollowUpOverdueBeforeNum(Integer FollowUpOverdueBeforeNum) {
        this.FollowUpOverdueBeforeNum = FollowUpOverdueBeforeNum;
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

    @Override
    public String toString() {
        return "BRemindchannel{" +
        "AllotRemind=" + AllotRemind +
        ", RecognizeRemind=" + RecognizeRemind +
        ", OverdueSubRemind=" + OverdueSubRemind +
        ", OverdueSignRemind=" + OverdueSignRemind +
        ", CheckOutRemind=" + CheckOutRemind +
        ", CustomerInvalidRemind=" + CustomerInvalidRemind +
        ", ChannelSource=" + ChannelSource +
        ", CustomerVisitsRemind=" + CustomerVisitsRemind +
        ", Editor=" + Editor +
        ", id=" + id +
        ", FollowUpOverdue=" + FollowUpOverdue +
        ", Status=" + Status +
        ", EditTime=" + EditTime +
        ", FollowUpOverdueBefore=" + FollowUpOverdueBefore +
        ", Creator=" + Creator +
        ", ProjectID=" + ProjectID +
        ", FollowUpOverdueBeforeNum=" + FollowUpOverdueBeforeNum +
        ", IsDel=" + IsDel +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
