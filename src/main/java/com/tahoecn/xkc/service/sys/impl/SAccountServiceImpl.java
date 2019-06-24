package com.tahoecn.xkc.service.sys.impl;

import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
@Service
public class SAccountServiceImpl extends ServiceImpl<SAccountMapper, SAccount> implements ISAccountService {

    @Autowired
    private SAccountMapper sAccountMapper;

    @Override
    public HashMap<String,String> getUserJob(String userName) {
        return sAccountMapper.getUserJob(userName);
    }
}
