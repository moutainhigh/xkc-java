package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class OptionItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // 主键
    public String ID;
    // 名称
    public String Name;
    // 是否可以选择
    public int IsChoose;
    // 是否子集必填
    public int IsSubMust;
    // 可以选择颜色
    public String SelectedColor;
    // 不可以选择颜色
    public String UnSelectedColor;
    // 子集
    public List<OptionItem> Child;

    public OptionItem()
    {
        IsSubMust = 0;
        IsChoose = 1;
        SelectedColor = "#000000";
        UnSelectedColor = "#cccccc";
    }
    public OptionItem(String ID, String Name,List<OptionItem> Child)
    {
    	this.ID = ID; 
    	this.Name = Name; 
    	this.Child = Child;
    }
    public OptionItem(String ID, String Name,int IsChoose)
    {
    	this.ID = ID; 
    	this.Name = Name; 
    	this.IsChoose = IsChoose;
    }
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

	public int getIsChoose() {
		return IsChoose;
	}

	public void setIsChoose(int isChoose) {
		IsChoose = isChoose;
	}

	public int getIsSubMust() {
		return IsSubMust;
	}

	public void setIsSubMust(int isSubMust) {
		IsSubMust = isSubMust;
	}

	public String getSelectedColor() {
		return SelectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		SelectedColor = selectedColor;
	}

	public String getUnSelectedColor() {
		return UnSelectedColor;
	}

	public void setUnSelectedColor(String unSelectedColor) {
		UnSelectedColor = unSelectedColor;
	}

	public List<OptionItem> getChild() {
		return Child;
	}

	public void setChild(List<OptionItem> child) {
		Child = child;
	}
}
