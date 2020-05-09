package com.tahoecn.xkc.service.risk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.vo.RiskConfigVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
public interface IBRiskconfigService extends IService<BRiskconfig> {

    /**
     *
     * @description: 风控规则配置查询
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 17:21
     */
    JSONResult search(HttpServletRequest request, Integer type, String projectId);

    /**
     *
     * @description: 替换配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 18:38
     */
    JSONResult replace(HttpServletRequest request, RiskConfigVo vo);

    /**
     *
     * @description: 集团下达风控规则配置到项目
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 17:39
     */
    JSONResult release(HttpServletRequest request);


}
