package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String ID;
    /// 身份证
    private String Name;
	
    private String Type;
	
    private int MaxLength;
	
    @JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("Type")
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	@JsonProperty("MaxLength")
	public int getMaxLength() {
		return MaxLength;
	}
	public void setMaxLength(int maxLength) {
		MaxLength = maxLength;
	}
}
