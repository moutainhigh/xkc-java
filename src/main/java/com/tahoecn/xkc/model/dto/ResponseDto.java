package com.tahoecn.xkc.model.dto;

import java.io.Serializable;

/**
 * Created by zhanghw on 2018/10/10.
 */
public class ResponseDto<T> implements Serializable {
	private Integer code;
	private String msg;
	private Integer totalPages;
	private T result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
