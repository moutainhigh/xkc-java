package com.tahoecn.xkc.model.dto;


import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.project.BProjectparameters;
import com.tahoecn.xkc.model.rule.BRemindrule;
import com.tahoecn.xkc.model.rule.BSalescenterrule;
import java.io.Serializable;


public class ParameterDto implements Serializable {

    private String UserId;

    private BProject bProject;

    private BRemindrule bRemindrule;

    private BSalescenterrule bSalescenterrule;

    private BProjectparameters bProjectparameters;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public BProject getbProject() {
        return bProject;
    }

    public void setbProject(BProject bProject) {
        this.bProject = bProject;
    }

    public BRemindrule getbRemindrule() {
        return bRemindrule;
    }

    public void setbRemindrule(BRemindrule bRemindrule) {
        this.bRemindrule = bRemindrule;
    }

    public BSalescenterrule getbSalescenterrule() {
        return bSalescenterrule;
    }

    public void setbSalescenterrule(BSalescenterrule bSalescenterrule) {
        this.bSalescenterrule = bSalescenterrule;
    }

    public BProjectparameters getbProjectparameters() {
        return bProjectparameters;
    }

    public void setbProjectparameters(BProjectparameters bProjectparameters) {
        this.bProjectparameters = bProjectparameters;
    }
}
