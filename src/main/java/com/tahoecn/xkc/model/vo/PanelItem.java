package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String Name;
	
	private String Type;
	
	private int ListIndex;
	
	private List<ChildItem> Child;
	
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
	
	@JsonProperty("ListIndex")
	public int getListIndex() {
		return ListIndex;
	}
	public void setListIndex(int listIndex) {
		ListIndex = listIndex;
	}
	
	@JsonProperty("Child")
	public List<ChildItem> getChild() {
		return Child;
	}
	public void setChild(List<ChildItem> child) {
		Child = child;
	}
}
