/**
 * 
 */
package com.tahoecn.xkc.model.vo;

/**
 * 定时任务统一回调
 * 
 * @author wq_qy
 */
public class TaskReturnVo {

	private Integer errCode;

	private String errMsg;

	private Object data;

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
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

	public static TaskReturnVo success() {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(0);
		return vo;
	}

	public static TaskReturnVo success(String message) {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(0);
		vo.setErrMsg(message);
		return vo;
	}

	public static TaskReturnVo success(Object object) {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(0);
		vo.setData(object);
		return vo;
	}

	public static TaskReturnVo success(String message, Object object) {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(0);
		vo.setErrMsg(message);
		vo.setData(object);
		return vo;
	}

	public static TaskReturnVo error(Integer errCode, String message) {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(errCode);
		vo.setErrMsg(message);
		return vo;
	}

	public static TaskReturnVo error(Integer errCode, Object object) {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(errCode);
		vo.setData(object);
		return vo;
	}

	public static TaskReturnVo error(Integer errCode, String message, Object object) {
		TaskReturnVo vo = new TaskReturnVo();
		vo.setErrCode(errCode);
		vo.setErrMsg(message);
		vo.setData(object);
		return vo;
	}

}
