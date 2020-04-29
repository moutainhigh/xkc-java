package com.tahoecn.xkc.model.miniprogram;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @description: 上海报备其他备份
 * @author: 张晓东
 * @time: 2020/4/28 10:37
 */
@TableName("B_Opportunity_Other")
@ApiModel(value = "BOpportunityOther对象", description = "")
public class BOpportunityOther implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ClueID")
    private String ClueID;

    @TableField("OpportunityID")
    private String OpportunityID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClueID() {
        return ClueID;
    }

    public void setClueID(String ClueID) {
        this.ClueID = ClueID;
    }

    public String getOpportunityID() {
        return OpportunityID;
    }

    public void setOpportunityID(String OpportunityID) {
        this.OpportunityID = OpportunityID;
    }

    @Override
    public String toString() {
        return "BOpportunityOther{" +
                "id=" + id +
                ", ClueID=" + ClueID +
                ", OpportunityID=" + OpportunityID +
                "}";
    }
}
