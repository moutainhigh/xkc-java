package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.http.HttpUtil;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.ucapi.UcApiUtils;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${uc_api_url}")
    private String baseUrl;

    @Value("${uc_sysId}")
    private String sysId;

    @Value("${uc_priv_key}")
    private String privKey;

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

    @Override
    public HashMap<String,Object> mLoginSelectByAccount(String userName) {
        return sAccountMapper.mLoginSelectByAccount(userName);
    }

    @Override
    public HashMap<String,Object> mLoginSelectByChannelUser(String userName) {
        return sAccountMapper.mLoginSelectByChannelUser(userName);
    }

    @Override
    public String checkUCUser(String userName, String password) {
        //String url = String.format("{0}user/verify?sysId={1}&username={2}&pd={3}&token={4}&timestamp={5}", baseUrl, sysId, userName, password, token, timestamp);
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte;
        try {
            textByte = password.getBytes("UTF-8");
            password = encoder.encodeToString(textByte);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = String.format(UcApiUtils.setUrl("/v1/user/verify") + "&username=%s&pd=%s", userName,password);
        return HttpUtil.httpGet(url);
    }

    @Override
    public List<HashMap<String,String>>  salesUserProjectList(String userID) {
        return sAccountMapper.salesUserProjectList(userID);
    }

    @Override
    public List<HashMap<String, String>> userProjectJobList(String userID, String projectID) {
        return sAccountMapper.userProjectJobList(userID,projectID);
    }

    @Override
    public List<HashMap<String, String>> GetMenuAndFunList_Select(String jobCode, String projectID) {
        return sAccountMapper.GetMenuAndFunList_Select(jobCode,projectID);
    }

    @Override
    public List<Map<String, Object>> SystemUserListByOrgID_Select(IPage page, String authCompanyID, String orgID, String key) {
        return baseMapper.SystemUserListByOrgID_Select(page,authCompanyID,orgID,key);
    }
}
