package com.tahoecn.xkc.model.risk.vo;

/**
 * @description:
 * @author: 张晓东
 * @time: 2020/6/19 14:39
 */
public class WxbLabelVO {
    private int allCustomer = 0;//全部客户
    private int freshCardCustomer = 0;//刷证客户
    private int attestFail = 0;//认证失败
    private int reportCustomer = 0;//报备客户
    private int dealCustomer = 0;//成交客户
    private int unknownCustomer = 0;//未知客户
    private int ysqz = 0;//疑似确认风险
    private int ysdqz = 0;//疑似待确认
    private int ok = 0;//确认风险
    private int noOk = 0;//确认无风险
    private String rate;//风险率
    private int unverified = 0;//未刷证

    public int getUnverified() {
        return unverified;
    }

    public void setUnverified(int unverified) {
        this.unverified = unverified;
    }

    public int getAllCustomer() {
        return allCustomer;
    }

    public void setAllCustomer(int allCustomer) {
        this.allCustomer = allCustomer;
    }

    public int getFreshCardCustomer() {
        return freshCardCustomer;
    }

    public void setFreshCardCustomer(int freshCardCustomer) {
        this.freshCardCustomer = freshCardCustomer;
    }

    public int getAttestFail() {
        return attestFail;
    }

    public void setAttestFail(int attestFail) {
        this.attestFail = attestFail;
    }

    public int getReportCustomer() {
        return reportCustomer;
    }

    public void setReportCustomer(int reportCustomer) {
        this.reportCustomer = reportCustomer;
    }

    public int getDealCustomer() {
        return dealCustomer;
    }

    public void setDealCustomer(int dealCustomer) {
        this.dealCustomer = dealCustomer;
    }

    public int getUnknownCustomer() {
        return unknownCustomer;
    }

    public void setUnknownCustomer(int unknownCustomer) {
        this.unknownCustomer = unknownCustomer;
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
