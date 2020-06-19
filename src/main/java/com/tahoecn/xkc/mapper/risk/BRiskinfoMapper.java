package com.tahoecn.xkc.mapper.risk;

import com.tahoecn.xkc.model.risk.BRiskinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.risk.vo.TiskInfoFindVO;
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
 * @since 2020-05-06
 */
public interface BRiskinfoMapper extends BaseMapper<BRiskinfo> {

    List<BRiskinfo> findNot46Bloc(@Param("startTime") Date startTime, @Param("endTime")Date endTime,
                                  @Param("regionalId")String regionalId, @Param("cityId")String cityId ,
                                  @Param("projectId")String projectId);

    List<BRiskinfo> find6Bloc(@Param("startTime") Date startTime, @Param("endTime")Date endTime,
                              @Param("regionalId")String regionalId, @Param("cityId")String cityId ,
                              @Param("projectId")String projectId);

    List<Map<String, Object>> listExport(TiskInfoFindVO vo);

    List<BRiskinfo> findNot6Bloc(@Param("startTime") Date startTime, @Param("endTime")Date endTime,
                                  @Param("regionalId")String regionalId, @Param("cityId")String cityId ,
                                  @Param("projectId")String projectId);

}
