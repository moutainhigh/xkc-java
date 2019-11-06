package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.ABrokerprojectimgMapper;
import com.tahoecn.xkc.model.project.ABrokerprojectimg;
import com.tahoecn.xkc.service.project.IABrokerprojectimgService;
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
public class ABrokerprojectimgServiceImpl extends ServiceImpl<ABrokerprojectimgMapper, ABrokerprojectimg> implements IABrokerprojectimgService {

    @Override
    public List<Map<String, String>> TopImgList(String brokerProjectID) {
        return baseMapper.TopImgList(brokerProjectID);
    }
}
