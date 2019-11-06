package com.tahoecn.xkc.model.vo;

import io.swagger.annotations.ApiModel;

@ApiModel(value="客户状态")
public class CustomerStatus {
	private String status;
	private String statusTime;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}
	
}
