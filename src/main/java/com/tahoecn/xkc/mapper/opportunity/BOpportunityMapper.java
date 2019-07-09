package com.tahoecn.xkc.mapper.opportunity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.opportunity.BOpportunity;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BOpportunityMapper extends BaseMapper<BOpportunity> {

    int getCustomerCount(String projectId, String memberId);
}
