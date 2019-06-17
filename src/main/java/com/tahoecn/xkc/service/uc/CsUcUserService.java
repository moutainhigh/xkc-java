package com.tahoecn.xkc.service.uc;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.CsUcUser;
import com.tahoecn.xkc.model.vo.UserVo;

import java.util.List;

/**
 * <p>
 * UC用户信息表 服务类
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
public interface CsUcUserService extends IService<CsUcUser> {

    /**
     * 同步用户信息
     *
     * @param userInfo
     */
    void synUserInfo(CsUcUser userInfo);

    /**
     * 获取用户信息，实现用户信息查询功能
     *
     * @param userName 用户帐号
     * @return 用户信息
     */
    CsUcUser selectByUsername(String userName);

    /**
     * sso直接获得用户信息
     *
     * @return
     */
    CsUcUser selectByUsername();

    List<UserVo> findUserByOrgId(String orgId);

    List<UserVo> findUsersByNameOrCode(String username);
}
