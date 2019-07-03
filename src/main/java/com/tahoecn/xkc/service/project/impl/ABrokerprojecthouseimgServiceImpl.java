package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.ABrokerprojecthouseimgMapper;
import com.tahoecn.xkc.model.project.ABrokerprojecthouseimg;
import com.tahoecn.xkc.service.project.IABrokerprojecthouseimgService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
@Service
public class ABrokerprojecthouseimgServiceImpl extends ServiceImpl<ABrokerprojecthouseimgMapper, ABrokerprojecthouseimg> implements IABrokerprojecthouseimgService {

    @Override
    public List<Map<String, String>> HouseImgList(String brokerProjectID) {
        return baseMapper.HouseImgList(brokerProjectID);
    }
}
