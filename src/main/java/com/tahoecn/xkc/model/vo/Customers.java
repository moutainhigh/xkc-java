package com.tahoecn.xkc.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="返回客户列表")
public class Customers {
	@ApiModelProperty(value="列表总数")
	private int total;
	@ApiModelProperty(value="客户列表")
	private List<Customer> customerList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	
}