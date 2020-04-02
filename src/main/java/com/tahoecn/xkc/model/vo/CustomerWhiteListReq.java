package com.tahoecn.xkc.model.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author mystic
 */
@ApiModel(value = "新增VIP白名单请求对象", description = "新增VIP白名单请求对象")
public class CustomerWhiteListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerID;
    private String customerMobile;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    @Override
    public String toString() {
        return "CustomerWhiteListReq{" +
                "customerID='" + customerID + '\'' +
                ", customerMobile='" + customerMobile + '\'' +
                '}';
    }
}
