package com.tahoecn.xkc.model.rule;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-08-13
 */
@TableName("Protect_Conf_log")
@ApiModel(value="ProtectConfLog对象", description="")
public class ProtectConfLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("ProjectId")
    private String ProjectId;

    @TableField("ProjectName")
    private String ProjectName;

    @TableField("ProtectSource")
    private Integer ProtectSource;

    @TableField("ClueRuleId")
    private String ClueRuleId;

    @TableField("ClueRule_AdviserGroup_Id")
    private String clueruleAdvisergroupId;

    @TableField("GroupDictId")
    private String GroupDictId;

    @TableField("GroupDictName")
    private String GroupDictName;

    @TableField("RuleType")
    private Integer RuleType;

    @TableField("RuleName")
    private String RuleName;

    @TableField("IsSelect")
    private Integer IsSelect;

    @TableField("OriProtectDays")
    private String OriProtectDays;

    @TableField("ChangeProtectDays")
    private String ChangeProtectDays;

    @TableField("EditorId")
    private String EditorId;

    @TableField("EditorName")
    private String EditorName;

    @TableField("CreateTime")
    private Date CreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }
    public Integer getProtectSource() {
        return ProtectSource;
    }

    public void setProtectSource(Integer ProtectSource) {
        this.ProtectSource = ProtectSource;
    }
    public String getClueRuleId() {
        return ClueRuleId;
    }

    public void setClueRuleId(String ClueRuleId) {
        this.ClueRuleId = ClueRuleId;
    }
    public String getClueruleAdvisergroupId() {
        return clueruleAdvisergroupId;
    }

    public void setClueruleAdvisergroupId(String clueruleAdvisergroupId) {
        this.clueruleAdvisergroupId = clueruleAdvisergroupId;
    }
    public String getGroupDictId() {
        return GroupDictId;
    }

    public void setGroupDictId(String GroupDictId) {
        this.GroupDictId = GroupDictId;
    }
    public String getGroupDictName() {
        return GroupDictName;
    }

    public void setGroupDictName(String GroupDictName) {
        this.GroupDictName = GroupDictName;
    }
    public Integer getRuleType() {
        return RuleType;
    }

    public void setRuleType(Integer RuleType) {
        this.RuleType = RuleType;
    }
    public String getRuleName() {
        return RuleName;
    }

    public void setRuleName(String RuleName) {
        this.RuleName = RuleName;
    }
    public Integer getIsSelect() {
        return IsSelect;
    }

    public void setIsSelect(Integer IsSelect) {
        this.IsSelect = IsSelect;
    }
    public String getOriProtectDays() {
        return OriProtectDays;
    }

    public void setOriProtectDays(String OriProtectDays) {
        this.OriProtectDays = OriProtectDays;
    }
    public String getChangeProtectDays() {
        return ChangeProtectDays;
    }

    public void setChangeProtectDays(String ChangeProtectDays) {
        this.ChangeProtectDays = ChangeProtectDays;
    }
    public String getEditorId() {
        return EditorId;
    }

    public void setEditorId(String EditorId) {
        this.EditorId = EditorId;
    }
    public String getEditorName() {
        return EditorName;
    }

    public void setEditorName(String EditorName) {
        this.EditorName = EditorName;
    }
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    public String toString() {
        return "ProtectConfLog{" +
        "id=" + id +
        ", ProjectId=" + ProjectId +
        ", ProjectName=" + ProjectName +
        ", ProtectSource=" + ProtectSource +
        ", ClueRuleId=" + ClueRuleId +
        ", clueruleAdvisergroupId=" + clueruleAdvisergroupId +
        ", GroupDictId=" + GroupDictId +
        ", GroupDictName=" + GroupDictName +
        ", RuleType=" + RuleType +
        ", RuleName=" + RuleName +
        ", IsSelect=" + IsSelect +
        ", OriProtectDays=" + OriProtectDays +
        ", ChangeProtectDays=" + ChangeProtectDays +
        ", EditorId=" + EditorId +
        ", EditorName=" + EditorName +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
