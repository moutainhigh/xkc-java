package com.tahoecn.xkc.service.miniprogram;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.UserRegisterVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序客储用户中心验证类接口服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public interface ICustomerValidationService extends IService<BChanneluser> {

    /**
     * 获取登录信息
     *
     * @param request
     * @param userRegisterVO
     * @return
     * @throws Exception
     */
    public JSONResult getCustomerMessage(HttpServletRequest request, UserRegisterVO userRegisterVO) throws Exception;
}
