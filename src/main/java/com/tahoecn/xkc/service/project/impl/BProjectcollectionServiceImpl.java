package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.project.BProjectcollectionMapper;
import com.tahoecn.xkc.model.project.BProjectcollection;
import com.tahoecn.xkc.service.project.IBProjectcollectionService;
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
public class BProjectcollectionServiceImpl extends ServiceImpl<BProjectcollectionMapper, BProjectcollection> implements IBProjectcollectionService {

    @Override
    public int mBrokerProjectCollectionIsExist_Select(String projectID, String userID) {
        return baseMapper.mBrokerProjectCollectionIsExist_Select(projectID,userID);
    }

    @Override
    public IPage<Map<String, Object>> mBrokerProjectCollectionList_Select(IPage page, String userID) {
        return baseMapper.mBrokerProjectCollectionList_Select(page,userID);
    }

    @Override
    public int BrokerProjectCollection_Insert(String userID, String projectID) {
        return baseMapper.BrokerProjectCollection_Insert(userID,projectID);
    }

    @Override
    public int BrokerProjectCollection_Delete(String userID, String projectID) {
        return baseMapper.BrokerProjectCollection_Delete(userID,projectID);
    }
}
