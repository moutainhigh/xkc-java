package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class VerifyItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ID;
    /// 身份证
    private String Name;
    private String Type;
    private int MaxLength;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getMaxLength() {
		return MaxLength;
	}
	public void setMaxLength(int maxLength) {
		MaxLength = maxLength;
	}
}
