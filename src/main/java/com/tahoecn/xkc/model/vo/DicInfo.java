package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DicInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // 字典ID
	@JsonProperty("DicID")
    private String DicID;
    // 字典名称
	@JsonProperty("DicName")
    private String DicName;
	@JsonProperty("Value")
    private String Value;
	@JsonProperty("ValueID")
    private String ValueID;
	@JsonProperty("FieldName")
    private String FieldName;
	@JsonProperty("TableName")
    private String TableName;
	@JsonProperty("Type")
    private String Type;
	public String getDicID() {
		return DicID;
	}
	public void setDicID(String dicID) {
		DicID = dicID;
	}
	public String getDicName() {
		return DicName;
	}
	public void setDicName(String dicName) {
		DicName = dicName;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getValueID() {
		return ValueID;
	}
	public void setValueID(String valueID) {
		ValueID = valueID;
	}
	public String getFieldName() {
		return FieldName;
	}
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
    
}
