package com.tahoecn.xkc.service.customer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.customer.BClue;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface IBClueService extends IService<BClue> {

    Map<String, Object> mBrokerCustomerDetail_Select(String clueID);
}
