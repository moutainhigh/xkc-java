package com.tahoecn.xkc.model.vo;

import java.io.Serializable;

public class SalesGroupMemberExistProjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String projectName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
