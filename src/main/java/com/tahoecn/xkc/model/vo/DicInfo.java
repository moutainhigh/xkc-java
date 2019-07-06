package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class DicInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // 字典ID
    public String DicID;
    // 字典名称
    public String DicName;
    public String Value;
    public String ValueID;
    public String FieldName;
    public String TableName;
    public String Type;
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
