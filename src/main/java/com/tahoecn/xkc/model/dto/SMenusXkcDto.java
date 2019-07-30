package com.tahoecn.xkc.model.dto;

import com.tahoecn.xkc.model.sys.SMenusXkc;

import java.io.Serializable;
import java.util.List;

public class SMenusXkcDto extends SMenusXkc implements Serializable {

    private List<SMenusXkcDto> children;

    private boolean isLeaf=true;

    private boolean signal;

    private static final long serialVersionUID = 9167288162711797241L;

    public List<SMenusXkcDto> getChildren() {
        return children;
    }

    public void setChildren(List<SMenusXkcDto> children) {
        this.children = children;
    }

    public boolean getisLeaf() {
        return isLeaf;
    }

    public void setisLeaf(boolean isLeaf) {
        isLeaf = isLeaf;
    }
    public boolean getSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }
}
