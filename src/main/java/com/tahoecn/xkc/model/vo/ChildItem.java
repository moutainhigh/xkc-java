package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String ID;
    // 姓(必填)
    private String Name;
    // 请输入姓氏
    private String Placeholder;
	
    private String Type;
	
    private String Value;
	
    private String ValueID;
	
    private int ListIndex;
	
    private int IsMust;
	
    private int IsHide;
	
    private int IsMustShow;
	
    private int IsEdit;
	
    private int IsMulti;
	
    private int IsFullLine;
	
    private int MaxLength;
	
    private int MaxSelectedNum;
	
    private String MinDate;
	
    private String MaxDate;
	
    private String NormalColor;
	
    private String SelectedColor;
	
    private String Partner;
	
    private String SubPartner;
	
    private List<VerifyItem> Verify;
	
    private List<OptionItem> Option;
    
    private JSONArray Child;
    
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

	@JsonProperty("Placeholder")
	public String getPlaceholder() {
		return Placeholder;
	}

	public void setPlaceholder(String placeholder) {
		Placeholder = placeholder;
	}

	@JsonProperty("Type")
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@JsonProperty("Value")
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@JsonProperty("ValueID")
	public String getValueID() {
		return ValueID;
	}

	public void setValueID(String valueID) {
		ValueID = valueID;
	}

	@JsonProperty("ListIndex")
	public int getListIndex() {
		return ListIndex;
	}

	public void setListIndex(int listIndex) {
		ListIndex = listIndex;
	}

	@JsonProperty("IsMust")
	public int getIsMust() {
		return IsMust;
	}

	public void setIsMust(int isMust) {
		IsMust = isMust;
	}

	@JsonProperty("IsHide")
	public int getIsHide() {
		return IsHide;
	}

	public void setIsHide(int isHide) {
		IsHide = isHide;
	}

	@JsonProperty("IsMustShow")
	public int getIsMustShow() {
		return IsMustShow;
	}

	public void setIsMustShow(int isMustShow) {
		IsMustShow = isMustShow;
	}

	@JsonProperty("IsEdit")
	public int getIsEdit() {
		return IsEdit;
	}

	public void setIsEdit(int isEdit) {
		IsEdit = isEdit;
	}

	@JsonProperty("IsMulti")
	public int getIsMulti() {
		return IsMulti;
	}

	public void setIsMulti(int isMulti) {
		IsMulti = isMulti;
	}

	@JsonProperty("IsFullLine")
	public int getIsFullLine() {
		return IsFullLine;
	}

	public void setIsFullLine(int isFullLine) {
		IsFullLine = isFullLine;
	}

	@JsonProperty("MaxLength")
	public int getMaxLength() {
		return MaxLength;
	}

	public void setMaxLength(int maxLength) {
		MaxLength = maxLength;
	}

	@JsonProperty("MaxSelectedNum")
	public int getMaxSelectedNum() {
		return MaxSelectedNum;
	}

	public void setMaxSelectedNum(int maxSelectedNum) {
		MaxSelectedNum = maxSelectedNum;
	}

	@JsonProperty("MinDate")
	public String getMinDate() {
		return MinDate;
	}

	public void setMinDate(String minDate) {
		MinDate = minDate;
	}

	@JsonProperty("MaxDate")
	public String getMaxDate() {
		return MaxDate;
	}

	public void setMaxDate(String maxDate) {
		MaxDate = maxDate;
	}

	@JsonProperty("NormalColor")
	public String getNormalColor() {
		return NormalColor;
	}

	public void setNormalColor(String normalColor) {
		NormalColor = normalColor;
	}

	@JsonProperty("SelectedColor")
	public String getSelectedColor() {
		return SelectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		SelectedColor = selectedColor;
	}

	@JsonProperty("Partner")
	public String getPartner() {
		return Partner;
	}

	public void setPartner(String partner) {
		Partner = partner;
	}

	@JsonProperty("SubPartner")
	public String getSubPartner() {
		return SubPartner;
	}

	public void setSubPartner(String subPartner) {
		SubPartner = subPartner;
	}

	@JsonProperty("Verify")
	public List<VerifyItem> getVerify() {
		return Verify;
	}

	public void setVerify(List<VerifyItem> verify) {
		Verify = verify;
	}

	@JsonProperty("Option")
	public List<OptionItem> getOption() {
		return Option;
	}

	public void setOption(List<OptionItem> option) {
		Option = option;
	}

	public JSONArray getChild() {
		return Child;
	}

	public void setChild(JSONArray child) {
		Child = child;
	}

	public ChildItem()
    {
        IsMust = 0;
        IsHide = 0;
        IsMustShow = 0;
        IsEdit = 0;
        IsMulti = 0;
    }
}
