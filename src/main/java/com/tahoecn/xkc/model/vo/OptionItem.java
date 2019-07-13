package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // 主键
    private String ID;
    // 名称
    private String Name;
    // 是否可以选择
    private int IsChoose;
    // 是否子集必填
    private int IsSubMust;
    // 可以选择颜色
    private String SelectedColor;
    // 不可以选择颜色
    private String UnSelectedColor;
    // 子集
    private List<OptionItem> Child;

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

	@JsonProperty("IsChoose")
	public int getIsChoose() {
		return IsChoose;
	}

	public void setIsChoose(int isChoose) {
		IsChoose = isChoose;
	}

	@JsonProperty("IsSubMust")
	public int getIsSubMust() {
		return IsSubMust;
	}

	public void setIsSubMust(int isSubMust) {
		IsSubMust = isSubMust;
	}

	@JsonProperty("SelectedColor")
	public String getSelectedColor() {
		return SelectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		SelectedColor = selectedColor;
	}

	@JsonProperty("UnSelectedColor")
	public String getUnSelectedColor() {
		return UnSelectedColor;
	}

	public void setUnSelectedColor(String unSelectedColor) {
		UnSelectedColor = unSelectedColor;
	}

	@JsonProperty("Child")
	public List<OptionItem> getChild() {
		return Child;
	}

	public void setChild(List<OptionItem> child) {
		Child = child;
	}
}
