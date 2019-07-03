package com.tahoecn.xkc.service.project;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.ABrokerproject;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface IABrokerprojectService extends IService<ABrokerproject> {

    List<HashMap<String,String>> mBrokerCityList_Select(String appID);

    HashMap<String, Object> mBrokerProjectDetail_Select(String brokerProjectID, String channelOrgID);
}
