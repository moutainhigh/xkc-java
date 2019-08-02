package com.tahoecn.xkc.model.vo;

import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="客户")
public class Customer {
	@ApiModelProperty(value="id标识")
	private String clueId;
	@ApiModelProperty(value="客户姓名")
	private String name;
	@ApiModelProperty(value="客户手机号")
	private String mobile;
	@ApiModelProperty(value="意向项目名称")
	private String intentProjectName;
	@ApiModelProperty(value="报备时间")
	private String createTime;
	@ApiModelProperty(value="二维码URL")
	private String qrUrl;
	@ApiModelProperty(value="客户状态")
	private String statusText;
	@ApiModelProperty(value="备注")
	private String remark;
	@ApiModelProperty(value="状态列表")
	private List<CustomerStatus> cs;
	
	public List<CustomerStatus> getCs() {
		return cs;
	}
	public void setCs(List<CustomerStatus> cs) {
		this.cs = cs;
	}
	public String getClueId() {
		return clueId;
	}
	public void setClueId(String clueId) {
		this.clueId = clueId;
	}
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
	public String getIntentProjectName() {
		return intentProjectName;
	}
	public void setIntentProjectName(String intentProjectName) {
		this.intentProjectName = intentProjectName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getQrUrl() {
		return qrUrl;
	}
	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}