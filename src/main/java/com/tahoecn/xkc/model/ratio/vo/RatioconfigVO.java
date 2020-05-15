package com.tahoecn.xkc.model.ratio.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @description: 渠道占比配置新增vo
 * @author: 张晓东
 * @time: 2020/5/12 11:35
 */
public class RatioconfigVO implements Serializable {

    private String id;
    @NotNull(message = "规则名称不能为空")
    @NotEmpty(message = "规则名称不能为空")
    private String name;
    @NotNull(message = "渠道类型不能为空")
    @NotEmpty(message = "渠道类型不能为空")
    private String[] channels;
    @NotNull(message = "报备占比不能为空")
    @Min(value = 0, message = "报备占比0-100")
    @Max(value = 100, message = "报备占比0-100")
    private Integer reportRatio;
    @NotNull(message = "成交占比不能为空")
    @Min(value = 0, message = "成交占比0-100")
    @Max(value = 100, message = "成交占比0-100")
    private Integer dealRatio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getChannels() {
        return channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

    public Integer getReportRatio() {
        return reportRatio;
    }

    public void setReportRatio(Integer reportRatio) {
        this.reportRatio = reportRatio;
    }

    public Integer getDealRatio() {
        return dealRatio;
    }

    public void setDealRatio(Integer dealRatio) {
        this.dealRatio = dealRatio;
    }

}
