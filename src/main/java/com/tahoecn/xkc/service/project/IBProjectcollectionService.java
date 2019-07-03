package com.tahoecn.xkc.service.project;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.project.BProjectcollection;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
public interface IBProjectcollectionService extends IService<BProjectcollection> {

    int mBrokerProjectCollectionIsExist_Select(String projectID, String userID);

    List<Map<String, Object>> mBrokerProjectCollectionList_Select(IPage page, String userID);

    int BrokerProjectCollection_Insert(String userID, String projectID);

    int BrokerProjectCollection_Delete(String userID, String projectID);
}
