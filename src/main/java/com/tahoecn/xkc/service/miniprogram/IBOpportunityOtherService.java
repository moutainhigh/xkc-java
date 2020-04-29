package com.tahoecn.xkc.service.miniprogram;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.miniprogram.BOpportunityOther;

/**
 * @description: 上海报备其他备份服务实现类
 * @author: 张晓东
 * @time: 2020/4/28 10:38
 */
public interface IBOpportunityOtherService extends IService<BOpportunityOther> {

    /**
     *
     * @description: 插入数据
     * @return:
     * @author: 张晓东
     * @time: 2020/4/28 10:40
     */
    void insert(BOpportunityOther other);

}
