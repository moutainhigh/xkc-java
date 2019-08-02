package com.tahoecn.xkc.mapper.rule;

import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface RuleAppMapper {
    List<BChannelorg> getChanelorgList(String projectID);

    List<BClueruleGourpVo> getFenxiao(@Param("projectId") String projectId,@Param("protectSource") Integer protectSource,@Param("clueRuleId")String clueRuleId);

    List<BClueruleGourpVo> getZiyouOrTuijian(@Param("projectId")String projectId, @Param("protectSource")Integer protectSource,@Param("clueRuleId")String clueRuleId);
}
