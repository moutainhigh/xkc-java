package com.tahoecn.xkc.service.miniprogram;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序客户信息获取服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public interface ICustomerService extends IService<BChanneluser> {

    /**
     * 获取客户列表
     *
     * @param request
     * @param relationshipVO
     * @return
     * @throws Exception
     */
    public JSONResult getCustomerList(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception;

    /**
     * 获取客户详情
     *
     * @param request
     * @param opportunityId
     * @return
     * @throws Exception
     */
    public JSONResult getCustomerDetail(HttpServletRequest request, String opportunityId) throws Exception;

    /**
     * 获取客户跟进记录
     *
     * @param request
     * @param opportunityId
     * @return
     * @throws Exception
     */
    public JSONResult getCustomerFollowRecord(HttpServletRequest request, String opportunityId) throws Exception;
}
