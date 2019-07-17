package com.tahoecn.xkc.model.vo;

import java.io.Serializable;
import java.util.List;

public class FilterItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String TagID;
	private List<String> Child;
	public String getTagID() {
		return TagID;
	}
	public void setTagID(String tagID) {
		TagID = tagID;
	}
	public List<String> getChild() {
		return Child;
	}
	public void setChild(List<String> child) {
		Child = child;
	}
}
