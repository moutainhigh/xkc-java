package com.tahoecn.xkc.common.exception;

import com.tahoecn.xkc.common.enums.AppTokenStatus;
import org.apache.ibatis.executor.ErrorContext;

public class AppUserLoginException extends RuntimeException{
    private static final long serialVersionUID = 6958499248468627021L;

    /** 错误码 */
    private int errorCode;


    public AppUserLoginException(AppTokenStatus status){
        super(status.getMessage());
        this.errorCode = status.getCode();
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
