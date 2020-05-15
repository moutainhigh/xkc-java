package com.tahoecn.xkc.model.ratio.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/5/13 14:49
 */
public class RatioConfigInfoVO {

    //配置id
    @NotEmpty(message = "项目id不能为空")
    private String projectId;

    //占比key
    @NotEmpty(message = "占比key不能为空")
    private String ratioKey;

    @Min(value = 0, message = "占比0-100")
    @Max(value = 100, message = "占比0-100")
    private Integer ratio;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRatioKey() {
        return ratioKey;
    }

    public void setRatioKey(String ratioKey) {
        this.ratioKey = ratioKey;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }
}
