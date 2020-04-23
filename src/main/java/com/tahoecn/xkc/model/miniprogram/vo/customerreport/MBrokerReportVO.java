package com.tahoecn.xkc.model.miniprogram.vo.customerreport;

/**
 * <p>
 *  思为小程序自由经纪人/中介同行客户报备参数实体类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-03
 */
public class MBrokerReportVO {

    private String name;

    private String mobile;

    private String intentProjectID;

    private String remark;

    private String userId;

    private String gender;

    private String adviserGroupId;

    private String jobId;

    private String formSessionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIntentProjectID() {
        return intentProjectID;
    }

    public void setIntentProjectID(String intentProjectID) {
        this.intentProjectID = intentProjectID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdviserGroupId() {
        return adviserGroupId;
    }

    public void setAdviserGroupId(String adviserGroupId) {
        this.adviserGroupId = adviserGroupId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getFormSessionId() {
        return formSessionId;
    }

    public void setFormSessionId(String formSessionId) {
        this.formSessionId = formSessionId;
    }
}
