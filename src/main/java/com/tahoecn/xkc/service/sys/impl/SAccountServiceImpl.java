package com.tahoecn.xkc.service.sys.impl;

import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public HashMap<String,String> getUserPorject(String userId,String productId) {
        return sAccountMapper.getUserPorject(userId,productId);
    }

    @Override
    public List<HashMap<String, String>> getUserPorduct(String userName){
        return sAccountMapper.getUserProduct(userName);
    }

    @Override
    public List<HashMap<String, String>> getUserWXApp(String userName) {
        return sAccountMapper.getUserWXApp(userName);
    }

    @Override
    public List<HashMap<String, String>> getUserJobs(String userName, String productID) {
        return sAccountMapper.getUserJobs(userName,productID);
    }

    @Override
    public List<HashMap<String, String>> getUserJobMenus(String userName, String productID) {
        return sAccountMapper.getUserJobMenus(userName,productID);
    }

    @Override
    public List<HashMap<String, String>> getJobFunctions(String userName, String productID) {
        return sAccountMapper.getJobFunctions(userName,productID);
    }

    @Override
    public List<HashMap<String, String>> getJobProject(String userName) {
        return sAccountMapper.getJobProject(userName);
    }
}
