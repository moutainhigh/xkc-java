package com.tahoecn.xkc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 期初发送短信通知表
 * </p>
 *
 * @author zghw
 * @since 2018-11-29
 */
public class CsSendtoall implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 表单id
     */
    private String telephone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "CsSendtoall{" +
        "id=" + id +
        ", telephone=" + telephone +
        "}";
    }
}
