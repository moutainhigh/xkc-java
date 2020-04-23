package com.tahoecn.xkc.service.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.sys.SysAccessManagement;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-03-31
 */
public interface ISysAccessManagementService extends IService<SysAccessManagement> {

    /**
     * ip请求校验方法
     *
     * @param request
     * @return
     */
    public JSONResult handlerIpVerification(HttpServletRequest request) throws Exception;
}
