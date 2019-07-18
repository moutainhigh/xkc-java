package com.tahoecn.xkc.mapper.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface BClueruleAdvisergroupMapper extends BaseMapper<BClueruleAdvisergroup> {

    void deleteByOrgID(@Param("orgID") String orgID, @Param("UserID")String UserID, @Param("projectIDWhere")String projectIDWhere);

    int selectDeleteCount(@Param("OrgID") String orgID,@Param("rProjectID") String rProjectID);

    String selectID(@Param("OrgID")String orgID, @Param("rProjectID")String rProjectID);

    void deleteByID(@Param("UserID")String userID,@Param("ID")String ID);
}
