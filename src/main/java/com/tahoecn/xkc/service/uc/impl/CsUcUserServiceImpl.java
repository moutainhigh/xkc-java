package com.tahoecn.xkc.service.uc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.landray.sso.client.EKPSSOContext;
import com.tahoecn.xkc.mapper.uc.CsUcUserMapper;
import com.tahoecn.xkc.model.CsUcUser;
import com.tahoecn.xkc.model.vo.UserVo;
import com.tahoecn.xkc.service.uc.CsUcUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * UC用户信息表 服务实现类
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
@Transactional
@Service
public class CsUcUserServiceImpl extends ServiceImpl<CsUcUserMapper, CsUcUser> implements CsUcUserService {

    @Autowired
    private CsUcUserMapper csUcUserMapper;


    @Override
    public void synUserInfo(CsUcUser userInfo) {
        CsUcUser userInfoSelect = new CsUcUser();
        userInfoSelect.setFdSid(userInfo.getFdSid());
        QueryWrapper<CsUcUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CsUcUser::getFdSid,userInfo.getFdSid());
        CsUcUser userInfoOne = csUcUserMapper.selectOne(wrapper);
        if (userInfoOne != null) {
            userInfo.setId(userInfoOne.getId());
            userInfo.setCreateTime(userInfoOne.getCreateTime());
            csUcUserMapper.updateById(userInfo);
        } else {
            csUcUserMapper.insert(userInfo);
        }
    }

    @Override
    public CsUcUser selectByUsername(String userName) {
        QueryWrapper<CsUcUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CsUcUser::getFdUsername,userName);
        return csUcUserMapper.selectOne(wrapper);
    }

    @Override
    public CsUcUser selectByUsername() {
        String userName =EKPSSOContext.getInstance().getCurrentUsername();
//        String userName = "wanghongxin";
        if (StringUtils.isNotBlank(userName)) {
            return selectByUsername(userName);
        }
        return null;
    }

    @Override
    public List<UserVo> findUserByOrgId(String orgId) {
        List<UserVo> list =  csUcUserMapper.findUserByOrgId(orgId) ;
        if(list  != null && list.size() > 0){
            return list;
        }
        return null;
    }

    /**
     * 查询用户信息
     * @param username  用户名或用户帐号
     * @return
     */
    @Override
    public List<UserVo> findUsersByNameOrCode(String username) {
        String usercode = "";
        if(username != null && !"".equals(username)){
            usercode = username;
            return csUcUserMapper.findUsersByUsername(username, usercode);
        }else{
            return null;
        }
    }

}
