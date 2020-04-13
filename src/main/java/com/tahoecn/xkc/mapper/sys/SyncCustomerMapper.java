package com.tahoecn.xkc.mapper.sys;

import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;

import java.util.Map;

/**
 * @author mystic
 */
public interface SyncCustomerMapper {

    /**
     * 根据客户ID更新白名单
     * @param map
     */
    @DataSource(DataSourceEnum.DB2)
    void updateByCustomerID(Map map);

    /**
     * 保存至白名单
     * @param map
     */
    @DataSource(DataSourceEnum.DB2)
    void save(Map map);

    /**
     * 根据客户ID删除白名单客户
     * @param map
     */
    @DataSource(DataSourceEnum.DB2)
    void deleteByCustomerID(Map map);
}
