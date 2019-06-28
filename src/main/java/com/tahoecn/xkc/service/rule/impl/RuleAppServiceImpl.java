package com.tahoecn.xkc.service.rule.impl;

import com.tahoecn.xkc.mapper.rule.RuleAppMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import com.tahoecn.xkc.service.rule.IRuleAppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class RuleAppServiceImpl implements IRuleAppService{

    @Autowired
    private RuleAppMapper ruleAppMapper;

    @Override
    public List<BChannelorg> getChanelorgList(String projectID) {
        List<BChannelorg> chanelorgList = ruleAppMapper.getChanelorgList(projectID);
        return chanelorgList;
    }

    @Override
    public List<BClueruleGourpVo> getFenxiao(String projectId, Integer protectSource) {
        return ruleAppMapper.getFenxiao(projectId, protectSource);
    }

    @Override
    public List<BClueruleGourpVo> getZiyouOrTuijian(String projectId, Integer protectSource) {
        return ruleAppMapper.getZiyouOrTuijian(projectId, protectSource);
    }
}
