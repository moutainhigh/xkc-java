package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.channel.BSalesuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-09
 */
public interface BSalesuserMapper extends BaseMapper<BSalesuser> {

    List<Map<String, Object>> UserSalesList_Select(@Param("projectID") String projectID, @Param("teamID") String teamID);
}
