package com.tahoecn.xkc.mapper.project;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.project.BProjectcollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-02
 */
public interface BProjectcollectionMapper extends BaseMapper<BProjectcollection> {

    int mBrokerProjectCollectionIsExist_Select(@Param("ProjectID") String projectID, @Param("UserID")String userID);

    IPage<Map<String, Object>> mBrokerProjectCollectionList_Select(IPage page, @Param("UserID")String userID);

    int BrokerProjectCollection_Insert(@Param("UserID")String userID,@Param("ProjectID") String projectID);

    int BrokerProjectCollection_Delete(@Param("UserID")String userID,@Param("ProjectID") String projectID);
}
