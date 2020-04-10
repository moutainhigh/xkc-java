package com.tahoecn.xkc.service.miniprogram;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.customerreport.MDynatownCustomerVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序提交销售系统服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
public interface ISubmitSaleService extends IService<BChanneluser> {

    /**
     * 提交销售系统
     *
     * @param request
     * @param mDynatownCustomerVO
     * @return
     * @throws Exception
     */
    public JSONResult submit(HttpServletRequest request, MDynatownCustomerVO mDynatownCustomerVO) throws Exception;
}
