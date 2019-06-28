package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class BClueruleGourpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String ClueRuleID;

    private String DictID;

    private String DictName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClueRuleID() {
        return ClueRuleID;
    }

    public void setClueRuleID(String clueRuleID) {
        ClueRuleID = clueRuleID;
    }

    public String getDictID() {
        return DictID;
    }

    public void setDictID(String dictID) {
        DictID = dictID;
    }

    public String getDictName() {
        return DictName;
    }

    public void setDictName(String dictName) {
        DictName = dictName;
    }
}
