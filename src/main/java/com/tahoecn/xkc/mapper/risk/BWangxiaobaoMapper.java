package com.tahoecn.xkc.mapper.risk;

import com.tahoecn.xkc.model.risk.BWangxiaobao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2020-05-11
 */
public interface BWangxiaobaoMapper extends BaseMapper<BWangxiaobao> {

    List<Map<String, Object>> searchFace(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}
