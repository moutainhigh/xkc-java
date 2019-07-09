package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BCustomerpotential;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface IBCustomerpotentialService extends IService<BCustomerpotential> {

    /**
     * 根据渠道身份返回机会客户来源(认知途径)
     * @param adviserGroupID
     * @return
     */
    String getOpportunitySourceByAdviserGroup(String adviserGroupID);
}
