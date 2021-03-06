package com.tahoecn.xkc.service.sys.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.http.HttpUtil;
import com.tahoecn.xkc.common.ucapi.UcApiUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.org.SOrganization;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.org.ISOrganizationService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * <p>
 * 服务实现类
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
    private SMenusXkcServiceImpl menusXkcService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ISOrganizationService organizationService;

    @Autowired
    private IBChanneluserService channeluserService;

    @Value("${uc_api_url}")
    private String baseUrl;

    @Value("${uc_sysId}")
    private String sysId;

    @Value("${uc_priv_key}")
    private String privKey;

    @Override
    public HashMap<String, String> getUserJob(String userName) {
        return sAccountMapper.getUserJob(userName);
    }

    @Override
    public HashMap<String, String> getUserPorject(String userId, String productId) {
        return sAccountMapper.getUserPorject(userId, productId);
    }

    @Override
    public List<HashMap<String, String>> getUserPorduct(String userName) {
        return sAccountMapper.getUserProduct(userName);
    }

    @Override
    public List<HashMap<String, String>> getUserWXApp(String userName) {
        return sAccountMapper.getUserWXApp(userName);
    }

    @Override
    public List<HashMap<String, String>> getUserJobs(String userName, String productID) {
        return sAccountMapper.getUserJobs(userName, productID);
    }

    @Override
    public List<HashMap<String, String>> getUserJobMenus(String userName, String productID) {
        return sAccountMapper.getUserJobMenus(userName, productID);
    }

    @Override
    public List<HashMap<String, String>> getJobFunctions(String userName, String productID) {
        return sAccountMapper.getJobFunctions(userName, productID);
    }

    @Override
    public List<HashMap<String, String>> getJobProject(String userName) {
        return sAccountMapper.getJobProject(userName);
    }

    @Override
    public HashMap<String, Object> mLoginSelectByAccount(String userName, String mobile) {
        return sAccountMapper.mLoginSelectByAccount(userName, mobile);
    }

    @Override
    public HashMap<String, Object> mLoginSelectByChannelUser(String userName, String mobile) {
        return sAccountMapper.mLoginSelectByChannelUser(userName, mobile);
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

        String url = String.format(UcApiUtils.setUrl("/v1/user/verify") + "&username=%s&pd=%s", userName, password);
        return HttpUtil.httpGet(url);
    }

    @Override
    public List<HashMap<String, String>> salesUserProjectList(String userID) {
        return sAccountMapper.salesUserProjectList(userID);
    }

    @Override
    public List<HashMap<String, String>> userProjectJobList(String userID, String projectID) {
        return sAccountMapper.userProjectJobList(userID, projectID);
    }

    @Override
    public List<HashMap<String, String>> GetMenuAndFunList_Select(String jobCode, String projectID) {
        return sAccountMapper.GetMenuAndFunList_Select(jobCode, projectID);
    }

    @Override
    public IPage<Map<String, Object>> SystemUserListByOrgID_Select(IPage page, String authCompanyID, String OrgID, String key, int Type) {
        return baseMapper.SystemUserListByOrgID_Select(page, authCompanyID, OrgID, key, Type);
    }

    @Override
    public IPage<Map<String, Object>> SystemUserListByOrgID_SelectN(IPage page, String authCompanyID, String OrgID, String key, int Type,
                                                                    String UserName, String Mobile, String Status) {
        if (OrgID != null) {
            SOrganization byId = organizationService.getById(OrgID);
            return baseMapper.SystemUserListByOrgID_SelectN(page, authCompanyID, byId.getFullPath(), key, Type,
                    UserName, Mobile, Status);
        } else {
            return baseMapper.SystemUserListByOrgID_SelectN(page, authCompanyID, null, key, Type,
                    UserName, Mobile, Status);
        }

    }

    @Override
    public List<Map<String, Object>> insertJob(String userID, String authCompanyID, String productID) {
        List<Map<String, Object>> jobList = baseMapper.insertJob(userID, authCompanyID, productID);
        List<Map<String, Object>> result = new ArrayList<>();
        Set<Map<String, Object>> resultSet = new HashSet<>();
        for (Map<String, Object> job : jobList) {
            if (StringUtils.equals((String) job.get("CommonJobID"), "8B95D9B6-1F85-7565-A0B9-F1E07AE73C12")) {
                result = menusXkcService.getResult();
                for (Map<String, Object> stringObjectHashMap : result) {
                    resultSet.add(stringObjectHashMap);
                }
            } else {
                String commonJobID = (String) job.get("CommonJobID");
                result = menusXkcService.getElseResult(commonJobID);
                if (result.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Map<String, Object> map : result) {
                        sb.append("'");
                        sb.append((String) map.get("ID"));
                        sb.append("'");
                        sb.append(",");
                    }

                    String substring = sb.substring(0, sb.length() - 1);
                    String jobID = (String) job.get("JobID");
                    String ID = substring;
                    List<Map<String, Object>> otherResult = menusXkcService.getOtherResult(jobID, ID);
                    for (Map<String, Object> map : otherResult) {
                        result.add(map);
                    }
                    resultSet.addAll(result);
                }
            }
        }
        //应返回resultSet
        return result;
    }

    @Override
    public Result getJobByUserName(String userID) {
        BChanneluser byId = channeluserService.getById(userID);
        //如果有多个 取第一个
        SAccount sAccount = baseMapper.findUser(byId.getUserName(), byId.getMobile());
        if (sAccount == null) {
            return Result.ok(null);
        } else {
            List<String> nameList = baseMapper.getJobName(sAccount.getId());
            if (CollectionUtil.isNotEmpty(nameList)) {
                return Result.errormsg(1, "您的角色是置业顾问，无法使用此功能进行推荐，请前往客储2.0APP案场联动进行客户推荐。");
            }
        }
        return Result.ok(null);
    }

    @Override
    public SAccount checkUser0(String username, String mobile) {
        return baseMapper.checkUser0(username, mobile);
    }

    @Override
    public SAccount checkUser1(String username, String mobile) {
        return baseMapper.checkUser1(username, mobile);
    }

    @Override
    public int getUserAmount(String mobile) throws Exception{
        int amount = 0;
        QueryWrapper<SAccount> queryWrapper = new QueryWrapper<SAccount>();
        queryWrapper.eq("Mobile", mobile);
        amount = sAccountMapper.selectCount(queryWrapper);
        return amount;
    }
}
