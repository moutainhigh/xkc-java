package com.tahoecn.xkc.common.enums;

public enum MessageHandleType {
	首访资料填写(1),
	新增跟进(2),
	转认购(3),
	转签约(4),
	已回款(5),
	已回收(6),
	已丢失(7);
	private int value;
	
	MessageHandleType(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
