package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class ChildItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
    public String ID;
    // 姓(必填)
    public String Name;
    // 请输入姓氏
    public String Placeholder;
    public String Type;
    public String Value;
    public String ValueID;
    public int ListIndex;
    public int IsMust;
    public int IsHide;
    public int IsMustShow;
    public int IsEdit;
    public int IsMulti;
    public int IsFullLine;
    public int MaxLength;
    public int MaxSelectedNum;
    public String MinDate;
    public String MaxDate;
    public String NormalColor;
    public String SelectedColor;
    public String Partner;
    public String SubPartner;
    public List<VerifyItem> Verify;
    public List<OptionItem> Option;
    
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

	public String getPlaceholder() {
		return Placeholder;
	}

	public void setPlaceholder(String placeholder) {
		Placeholder = placeholder;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
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

	public int getListIndex() {
		return ListIndex;
	}

	public void setListIndex(int listIndex) {
		ListIndex = listIndex;
	}

	public int getIsMust() {
		return IsMust;
	}

	public void setIsMust(int isMust) {
		IsMust = isMust;
	}

	public int getIsHide() {
		return IsHide;
	}

	public void setIsHide(int isHide) {
		IsHide = isHide;
	}

	public int getIsMustShow() {
		return IsMustShow;
	}

	public void setIsMustShow(int isMustShow) {
		IsMustShow = isMustShow;
	}

	public int getIsEdit() {
		return IsEdit;
	}

	public void setIsEdit(int isEdit) {
		IsEdit = isEdit;
	}

	public int getIsMulti() {
		return IsMulti;
	}

	public void setIsMulti(int isMulti) {
		IsMulti = isMulti;
	}

	public int getIsFullLine() {
		return IsFullLine;
	}

	public void setIsFullLine(int isFullLine) {
		IsFullLine = isFullLine;
	}

	public int getMaxLength() {
		return MaxLength;
	}

	public void setMaxLength(int maxLength) {
		MaxLength = maxLength;
	}

	public int getMaxSelectedNum() {
		return MaxSelectedNum;
	}

	public void setMaxSelectedNum(int maxSelectedNum) {
		MaxSelectedNum = maxSelectedNum;
	}

	public String getMinDate() {
		return MinDate;
	}

	public void setMinDate(String minDate) {
		MinDate = minDate;
	}

	public String getMaxDate() {
		return MaxDate;
	}

	public void setMaxDate(String maxDate) {
		MaxDate = maxDate;
	}

	public String getNormalColor() {
		return NormalColor;
	}

	public void setNormalColor(String normalColor) {
		NormalColor = normalColor;
	}

	public String getSelectedColor() {
		return SelectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		SelectedColor = selectedColor;
	}

	public String getPartner() {
		return Partner;
	}

	public void setPartner(String partner) {
		Partner = partner;
	}

	public String getSubPartner() {
		return SubPartner;
	}

	public void setSubPartner(String subPartner) {
		SubPartner = subPartner;
	}

	public List<VerifyItem> getVerify() {
		return Verify;
	}

	public void setVerify(List<VerifyItem> verify) {
		Verify = verify;
	}

	public List<OptionItem> getOption() {
		return Option;
	}

	public void setOption(List<OptionItem> option) {
		Option = option;
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
