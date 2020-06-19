package com.tahoecn.xkc.model.risk.vo;

import java.io.Serializable;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/18 14:33
 */
public class WxbRiskStatisticalResultVO implements Serializable {

    private String regionalName;//区域
    private String cityName;//城市
    private String projectName;//项目
    private String DictName; //渠道来源名称
    private String ChannelCompany;// 渠道机构
    private int cardCustomers;//刷证客户
    private int subscribeCustomers;//认购客户
    private int contractCustomers;//签约客户
    private int ysqz;//疑似确认
    private int ysdqz;//疑似待确认
    private int ok;//确认风险
    private int noOk;//确认无风险
    private String rate;//风险率

    public String getRegionalName() {
        return regionalName;
    }

    public void setRegionalName(String regionalName) {
        this.regionalName = regionalName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDictName() {
        return DictName;
    }

    public void setDictName(String dictName) {
        DictName = dictName;
    }

    public String getChannelCompany() {
        return ChannelCompany;
    }

    public void setChannelCompany(String channelCompany) {
        ChannelCompany = channelCompany;
    }

    public int getCardCustomers() {
        return cardCustomers;
    }

    public void setCardCustomers(int cardCustomers) {
        this.cardCustomers = cardCustomers;
    }

    public int getSubscribeCustomers() {
        return subscribeCustomers;
    }

    public void setSubscribeCustomers(int subscribeCustomers) {
        this.subscribeCustomers = subscribeCustomers;
    }

    public int getContractCustomers() {
        return contractCustomers;
    }

    public void setContractCustomers(int contractCustomers) {
        this.contractCustomers = contractCustomers;
    }

    public int getYsqz() {
        return ysqz;
    }

    public void setYsqz(int ysqz) {
        this.ysqz = ysqz;
    }

    public int getYsdqz() {
        return ysdqz;
    }

    public void setYsdqz(int ysdqz) {
        this.ysdqz = ysdqz;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public int getNoOk() {
        return noOk;
    }

    public void setNoOk(int noOk) {
        this.noOk = noOk;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
