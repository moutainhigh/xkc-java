package com.tahoecn.xkc.service.ratio;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.ratio.BRatioconfig;
import com.tahoecn.xkc.model.ratio.vo.RatioconfigVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YYY
 * @since 2020-05-12
 */
public interface IBRatioconfigService extends IService<BRatioconfig> {

    /**
     * @description: 更新渠道占比, 已选择不能新增
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 13:47
     */
    JSONResult replace(HttpServletRequest request, RatioconfigVO vo);

    /**
     * @description: 渠道占比主配置查询
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 16:38
     */
    JSONResult listConfig();

    /**
     * @description: 删除风控规则配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 18:14
     */
    JSONResult iRemove(HttpServletRequest request, String id);

    /**
     *
     * @description: 禁用风控规则配置
     * @param all true为全部禁用
     * @param id 当all为false是, id必传
     * @author: 张晓东
     * @time: 2020/5/12 18:26
     */
    JSONResult disable(HttpServletRequest request, Boolean all, String id);

    /**
     *
     * @description: 下发风控规则配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 19:55
     */
    JSONResult command(HttpServletRequest request);

    /**
     *
     * @description: 启用风控规则配置
     * @return:
     * @author: 张晓东
     * @time: 2020/5/12 20:31
     */
    JSONResult enable(HttpServletRequest request, String id);
}
