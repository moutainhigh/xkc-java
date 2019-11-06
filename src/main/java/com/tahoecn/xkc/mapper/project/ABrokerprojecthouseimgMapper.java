package com.tahoecn.xkc.mapper.project;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.ABrokerprojecthouseimg;
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
public interface ABrokerprojecthouseimgMapper extends BaseMapper<ABrokerprojecthouseimg> {

    List<Map<String, String>> HouseImgList(@Param("BrokerProjectID") String brokerProjectID);
}
