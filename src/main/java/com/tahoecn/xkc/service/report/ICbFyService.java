package com.tahoecn.xkc.service.report;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.channel.CbFy;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface ICbFyService extends IService<CbFy> {

    //@DS("slave_my")
    void save1();

}
