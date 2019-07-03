package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BClueMapper;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.service.customer.IBClueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
@Service
public class BClueServiceImpl extends ServiceImpl<BClueMapper, BClue> implements IBClueService {

    @Override
    public Map<String, Object> mBrokerCustomerDetail_Select(String clueID) {

        Map<String, Object> map = baseMapper.BrokerClueDetail_Select(clueID);
        if (map.size()>0){
        //获取轨迹记录
            List<Map<String, Object>> list=baseMapper.TrackList_Select(clueID);
            map.put("List" ,list);
        }

        return map;
    }
}
