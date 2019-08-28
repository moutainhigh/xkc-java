package com.tahoecn.xkc.model.customer;

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
 * @since 2019-08-28
 */
@ApiModel(value="CustomerBook对象", description="")
public class CustomerBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Name")
    private String name;

    @TableField("Gender")
    private String gender;

    @TableField("Mobile")
    private String mobile;

    @TableField("CardType")
    private String cardType;

    @TableField("CardID")
    private String cardID;

    @TableField("Address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
