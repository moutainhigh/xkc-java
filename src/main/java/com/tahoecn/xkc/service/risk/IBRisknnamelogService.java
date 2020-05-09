package com.tahoecn.xkc.service.risk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.risk.BRisknnamelog;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
public interface IBRisknnamelogService extends IService<BRisknnamelog> {

    /**
     *
     * @description: 插入数据
     * @return:
     * @author: 张晓东
     * @time: 2020/5/6 15:15
     */
    void insert(BRisknnamelog data);
}
