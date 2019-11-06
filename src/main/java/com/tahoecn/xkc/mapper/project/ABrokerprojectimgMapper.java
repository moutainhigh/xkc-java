package com.tahoecn.xkc.mapper.project;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.project.ABrokerprojectimg;
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
public interface ABrokerprojectimgMapper extends BaseMapper<ABrokerprojectimg> {

    List<Map<String, String>> TopImgList(@Param("BrokerProjectID") String brokerProjectID);
}
