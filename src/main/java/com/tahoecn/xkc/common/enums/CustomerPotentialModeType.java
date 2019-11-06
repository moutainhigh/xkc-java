package com.tahoecn.xkc.common.enums;

public enum CustomerPotentialModeType {
	自渠_老线索_老潜在客户("1"),
	自渠_新线索_老潜在客户("2"),
	自渠_新线索_新潜在客户("3"),
	自渠_客户_详情("81"),
	自渠_客户_更新("82"),
	无("99");
	
	// 成员变量  
	private String TypeID;
	
    // 构造方法  
    private CustomerPotentialModeType(String TypeID) {  
        this.TypeID = TypeID;  
    }  
    //接口方法  
    public String getTypeID() {  
        return this.TypeID;  
    }
}
