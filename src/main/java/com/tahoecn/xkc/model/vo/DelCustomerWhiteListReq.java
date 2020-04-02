package com.tahoecn.xkc.model.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author mystic
 */
@ApiModel(value = "删除VIP白名单请求对象", description = "删除VIP白名单请求对象")
public class DelCustomerWhiteListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerID;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "DelCustomerWhiteListReq{" +
                "customerID='" + customerID + '\'' +
                '}';
    }
}
