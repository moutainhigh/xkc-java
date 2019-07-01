package com.tahoecn.xkc.common;

import com.alibaba.fastjson.JSONObject;

public class ReturnEntity {
	public ReturnEntity() {
		this.errCode = 0;
		this.errMsg = "";
		this.dataType = "text";
	}

	public ReturnEntity(int _errCode, String _errMsg, Object _data) {
		this.errCode = _errCode;
		this.errMsg = _errMsg;
		this.data = _data;
		this.dataType = "text";
	}

	//返回类型 0 成功 1 失败
	public int errCode;
	//说明
	public String errMsg;
	//集合
	public Object data;

	public JSONObject jObjectData;
	public String startTime;
	public String endTime;
	public String dataType;
	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JSONObject getjObjectData() {
		return jObjectData;
	}

	public void setjObjectData(JSONObject jObjectData) {
		this.jObjectData = jObjectData;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}
