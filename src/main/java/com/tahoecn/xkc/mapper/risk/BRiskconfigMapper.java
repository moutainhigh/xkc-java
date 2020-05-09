package com.tahoecn.xkc.mapper.risk;

import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
public interface BRiskconfigMapper extends BaseMapper<BRiskconfig> {

    /**
     *
     * @description: 禁用全部风控项目配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 17:50
     */
    void disableAll();

    /**
     *
     * @description: 通过id禁用风控配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 17:50
     */
    void disable(String id);

}
