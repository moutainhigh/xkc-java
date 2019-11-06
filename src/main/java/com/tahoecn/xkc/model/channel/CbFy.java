package com.tahoecn.xkc.model.channel;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-08
 */
@TableName("cb_Fy")
@ApiModel(value="CbFy对象", description="")
public class CbFy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FyGUID")
    private String FyGUID;

    @TableField("FyName")
    private String FyName;

    @TableField("FyCode")
    private String FyCode;

    @TableField("FyOrder")
    private Integer FyOrder;

    public String getFyGUID() {
        return FyGUID;
    }

    public void setFyGUID(String FyGUID) {
        this.FyGUID = FyGUID;
    }
    public String getFyName() {
        return FyName;
    }

    public void setFyName(String FyName) {
        this.FyName = FyName;
    }
    public String getFyCode() {
        return FyCode;
    }

    public void setFyCode(String FyCode) {
        this.FyCode = FyCode;
    }
    public Integer getFyOrder() {
        return FyOrder;
    }

    public void setFyOrder(Integer FyOrder) {
        this.FyOrder = FyOrder;
    }

    @Override
    public String toString() {
        return "CbFy{" +
        "FyGUID=" + FyGUID +
        ", FyName=" + FyName +
        ", FyCode=" + FyCode +
        ", FyOrder=" + FyOrder +
        "}";
    }
}
