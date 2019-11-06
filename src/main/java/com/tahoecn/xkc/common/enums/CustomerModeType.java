package com.tahoecn.xkc.common.enums;

public enum CustomerModeType {

	分接_老机会_老客户("1"),
	分接_老机会_老客户_未分配("21"),
	分接_老机会_老客户_公共客户("23"),
	分接_新机会_老客户("2"),
	分接_新机会_新客户_老潜在客户("3"),
	分接_新机会_新客户_新潜在客户("4"),
	顾问_老机会_老客户("11"),
	顾问_新机会_老客户("12"),
	顾问_新机会_新客户_老潜在客户("13"),
	顾问_新机会_新客户_新潜在客户("14"),
	顾问_客户_详情("81"),
	顾问_客户_更新("82"),
	无("99");
	
	// 成员变量  
	private String TypeID;
	
    // 构造方法  
    private CustomerModeType(String TypeID) {  
        this.TypeID = TypeID;  
    }  
    //接口方法  
    public String getTypeID() {  
        return this.TypeID;  
    } 
}
