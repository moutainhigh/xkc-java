package com.tahoecn.xkc.mapper.project;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.ABrokerproject;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
public interface ABrokerprojectMapper extends BaseMapper<ABrokerproject> {

    List<HashMap<String,String>> mBrokerCityList_Select(@Param("AppID") String appID);

    HashMap<String, Object> mBrokerProjectDetail_Select(@Param("BrokerProjectID")String brokerProjectID, @Param("ChannelOrgID")String channelOrgID);
}
