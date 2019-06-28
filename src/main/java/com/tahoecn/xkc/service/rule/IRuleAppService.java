package com.tahoecn.xkc.service.rule;

import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.vo.BClueruleGourpVo;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface IRuleAppService {
    List<BChannelorg> getChanelorgList(String projectID);

    List<BClueruleGourpVo> getFenxiao(String projectId, Integer protectSource);

    List<BClueruleGourpVo> getZiyouOrTuijian(String projectId, Integer protectSource);
}
