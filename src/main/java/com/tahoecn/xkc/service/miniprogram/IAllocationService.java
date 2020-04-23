package com.tahoecn.xkc.service.miniprogram;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.allocation.ChannelVO;
import com.tahoecn.xkc.model.miniprogram.vo.allocation.CourtCaseVO;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 思为小程序客户分配服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-08
 */
public interface IAllocationService extends IService<BChanneluser> {

    /**
     * 案场分配客户
     *
     * @param request
     * @param courtCase
     * @return
     * @throws Exception
     */
    public JSONResult courtCase(HttpServletRequest request, CourtCaseVO courtCase) throws Exception;

    /**
     * 自渠分配客户
     *
     * @param request
     * @param channelVO
     * @return
     * @throws Exception
     */
    public JSONResult channel(HttpServletRequest request, ChannelVO channelVO) throws Exception;
}
