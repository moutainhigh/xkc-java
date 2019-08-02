package com.tahoecn.xkc.model.vo;

import com.tahoecn.xkc.model.rule.BCluerule;
import java.io.Serializable;

public class BClueruleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private BCluerule bCluerule;

    private String IsUpdateGroup;

    private String grouplist;

    public BCluerule getbCluerule() {
        return bCluerule;
    }

    public void setbCluerule(BCluerule bCluerule) {
        this.bCluerule = bCluerule;
    }

    public String getIsUpdateGroup() {
        return IsUpdateGroup;
    }

    public void setIsUpdateGroup(String isUpdateGroup) {
        IsUpdateGroup = isUpdateGroup;
    }

    public String getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(String grouplist) {
        this.grouplist = grouplist;
    }
}
