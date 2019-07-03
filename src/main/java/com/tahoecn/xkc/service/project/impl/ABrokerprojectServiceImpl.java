package com.tahoecn.xkc.service.project.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.ABrokerprojectMapper;
import com.tahoecn.xkc.model.project.ABrokerproject;
import com.tahoecn.xkc.service.project.IABrokerprojectService;
import com.tahoecn.xkc.service.project.IABrokerprojecthouseimgService;
import com.tahoecn.xkc.service.project.IABrokerprojectimgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@Service
public class ABrokerprojectServiceImpl extends ServiceImpl<ABrokerprojectMapper, ABrokerproject> implements IABrokerprojectService {

    @Autowired
    private IABrokerprojecthouseimgService brokerprojecthouseimgService;
    @Autowired
    private IABrokerprojectimgService brokerprojectimgService;
    @Override
    public List<HashMap<String,String>> mBrokerCityList_Select(String appID) {
        return baseMapper.mBrokerCityList_Select(appID);
    }

    @Override
    public HashMap<String, Object> mBrokerProjectDetail_Select(String brokerProjectID, String channelOrgID) {
        HashMap<String, Object> map=baseMapper.mBrokerProjectDetail_Select(brokerProjectID,channelOrgID);
        List<Map<String,String>> HouseImgList=brokerprojecthouseimgService.HouseImgList(brokerProjectID);
        List<Map<String,String>> TopImgList=brokerprojectimgService.TopImgList(brokerProjectID);
        if (HouseImgList.size()>0){
            map.put("HouseImgList",HouseImgList);
        }
        if (TopImgList.size()>0){
            map.put("TopImgList",TopImgList);
        }
        return map;
    }
}
