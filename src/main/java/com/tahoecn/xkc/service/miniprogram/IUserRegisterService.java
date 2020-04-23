package com.tahoecn.xkc.service.miniprogram;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.UserRegisterVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序外渠角色注册接口服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-03-31
 */
public interface IUserRegisterService extends IService<BChanneluser> {

    /**
     * 外渠角色注册接口
     *
     * @param request
     * @param userRegisterVO
     * @return
     * @throws Exception
     */
    public JSONResult userRegister(HttpServletRequest request, UserRegisterVO userRegisterVO) throws Exception;
}
