package com.tahoecn.xkc.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@TableName("S_AccountUserType")
@ApiModel(value="SAccountusertype对象", description="")
public class SAccountusertype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("UserName")
    private String UserName;

    @TableField("AccountType")
    private Integer AccountType;

    @TableField("Password")
    private String Password;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public Integer getAccountType() {
        return AccountType;
    }

    public void setAccountType(Integer AccountType) {
        this.AccountType = AccountType;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "SAccountusertype{" +
        "UserName=" + UserName +
        ", AccountType=" + AccountType +
        ", Password=" + Password +
        "}";
    }
}
