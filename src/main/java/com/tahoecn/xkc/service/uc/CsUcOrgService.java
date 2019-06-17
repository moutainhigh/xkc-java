package com.tahoecn.xkc.service.uc;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.CsUcOrg;

/**
 * <p>
 * UC组织机构信息 服务类
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
public interface CsUcOrgService extends IService<CsUcOrg> {
    /**
     * 同步组织数据
     *
     * @param orgInfo 组织信息
     */
    void synOrgInfo(CsUcOrg orgInfo);
}
