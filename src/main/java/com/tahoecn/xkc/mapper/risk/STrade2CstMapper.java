package com.tahoecn.xkc.mapper.risk;

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
    @DataSource(value = DataSourceEnum.DB2)
    List<Map<String, Object>> searchJointName(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}
