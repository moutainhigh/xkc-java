package com.tahoecn.xkc.mapper.miniprogram;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户信息 Mapper 接口
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public interface CustomerMapper extends BaseMapper<BChanneluser> {

    /**
     * 获取客户列表
     *
     * @param relationshipVO
     * @return
     */
    List<Map> getCustomerList1(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取客户列表
     *
     * @param relationshipVO
     * @return
     */
    List<Map> getCustomerList2(@Param("relationship") RelationshipVO relationshipVO);

    /**
     * 获取客户列表
     *
     * @param opportunityId
     * @return
     */
    Map getCustomerDetail1(@Param("opportunityId") String opportunityId);

    /**
     * 获取客户列表
     *
     * @param opportunityId
     * @return
     */
    Map getCustomerDetail2(@Param("opportunityId") String opportunityId);

    /**
     * 获取销售机会获取客户id
     *
     * @param opportunityId
     * @return
     */
    String getCustomerId1(@Param("opportunityId") String opportunityId);

    /**
     * 获取销售机会获取客户id
     *
     * @param opportunityId
     * @return
     */
    String getCustomerId2(@Param("opportunityId") String opportunityId);

    /**
     * 获取客户跟进记录
     *
     * @param customerId
     * @return
     */
    Map getCustomerFollowRecord(@Param("customerId") String customerId);
}
