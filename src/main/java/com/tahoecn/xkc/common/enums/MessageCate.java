package com.tahoecn.xkc.common.enums;

public enum MessageCate {

	系统通知 ("1"),
    顾问客户列表("2"),
    全部顾问客户列表("3"),
    客户丢失列表("4"),
    拓客客户列表("5"),
    全部回收列表("6"),
    催办客户列表("7"),
    渠道任务通知("8"),
    楼盘动态("9"),
    预约客户列表("10");
	
	// 成员变量  
	private String CateID;
	
    // 构造方法  
    private MessageCate(String CateID) {  
        this.CateID = CateID;  
    }  
    //接口方法  
    public String getCateID() {  
        return this.CateID;  
    } 
}
