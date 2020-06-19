package com.tahoecn.xkc.mapper.customer;

import com.tahoecn.xkc.common.annotation.DataSource;
import com.tahoecn.xkc.common.enums.DataSourceEnum;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface STrade2CstMapper {

    /**
     * @description: 按时间搜索联名购房
     * @return:
     * @author: 张晓东
     * @time: 2020/5/15 11:07
     */
    @DataSource(DataSourceEnum.DB2)
    List<Map<String, Object>> searchJointName(@Param("startTime") Date startTime, @Param("endTime") Date endTime);


    /**
     * @description: 按时间搜索短期成交
     * @return:
     * @author: 张晓东
     * @time: 2020/5/18 17:25
     */
    @DataSource(DataSourceEnum.DB2)
    List<Map<String, Object>> searchShortDeal(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * @description: 签约
     * @return:
     * @author: 张晓东
     * @time: 2020/5/25 10:52
     */
//    @DataSource(DataSourceEnum.DB2)
    List<Map<String, Object>> agreement(@Param("attachId") String attachId);

    /**
     * @description: 认购
     * @return:
     * @author: 张晓东
     * @time: 2020/5/25 10:52
     */
//    @DataSource(DataSourceEnum.DB2)
    List<Map<String, Object>> subscribe(@Param("attachId") String attachId);

}
