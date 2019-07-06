package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class PanelItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
    public String Name;
    public String Type;
    public int ListIndex;
    public List<ChildItem> Child;
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
	public int getListIndex() {
		return ListIndex;
	}
	public void setListIndex(int listIndex) {
		ListIndex = listIndex;
	}
	public List<ChildItem> getChild() {
		return Child;
	}
	public void setChild(List<ChildItem> child) {
		Child = child;
	}
}
