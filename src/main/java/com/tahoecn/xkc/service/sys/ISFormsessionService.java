package com.tahoecn.xkc.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.SFormsession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface ISFormsessionService extends IService<SFormsession> {

    /**
     * 查询是否已经存在无效的FormSessionID,不存在则更新为无效状态
     * @param formSessionID
     * @return
     */
    int checkFormSessionID(String formSessionID);
}
