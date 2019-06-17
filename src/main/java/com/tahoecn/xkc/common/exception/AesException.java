package com.tahoecn.xkc.common.exception;

/**
 * 用来处理导入导出中遇到的问题  数据源为空 有重复行,待导入数据已在数据库中存在等
 * @author 
 * @version 
 *
 */
public class AesException extends Exception {
	
	public AesException() {
        // TODO Auto-generated constructor stub
    }

    public AesException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public AesException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public AesException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
