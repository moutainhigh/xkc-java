package com.tahoecn.xkc.mapper.rule;

import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import java.util.List;

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

    List<BClueruleGourpVo> getFenxiao(String projectId, Integer protectSource);

    List<BClueruleGourpVo> getZiyouOrTuijian(String projectId, Integer protectSource);
}
