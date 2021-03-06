package com.tahoecn.xkc.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
public interface ISAccountService extends IService<SAccount> {

    HashMap<String,String> getUserJob(String userName);

    /**
     *  获取当前登录人可管理的项目列表
     */
    HashMap<String,String> getUserPorject(String userId,String productId);

    /**
     * 获取用户关联的产品
     * @param userName
     * @return
     */
    List<HashMap<String, String>> getUserPorduct(String userName);

    /**
     * 可管理的微信账号
     * @param userName
     * @return
     */
    List<HashMap<String,String>> getUserWXApp(String userName);

    /**
     * 岗位
     * @param userName
     * @return
     */
    List<HashMap<String,String>> getUserJobs(String userName,String productID);

    /**
     * 该产品下与登录人相关岗位的菜单
     * @param userName
     * @return
     */
    List<HashMap<String,String>> getUserJobMenus(String userName,String productID);

    /**
     * 该产品下与登录人相关岗位的功能
     * @param userName
     * @return
     */
    List<HashMap<String,String>> getJobFunctions(String userName,String productID);

    /**
     * 该产品下与登录人相关岗位的功能
     * @param userName
     * @return
     */
    List<HashMap<String,String>> getJobProject(String userName);

    HashMap<String,Object> mLoginSelectByAccount(String userName,String mobile);
    HashMap<String,Object> mLoginSelectByChannelUser(String userName,String mobile);

    String checkUCUser(String userName, String password);

    List<HashMap<String,String>> salesUserProjectList(String userID);

    List<HashMap<String,String>> userProjectJobList(String userID, String projectID);

    List<HashMap<String,String>> GetMenuAndFunList_Select(String jobCode, String projectID);

    IPage<Map<String,Object>> SystemUserListByOrgID_Select(IPage page, String authCompanyID,String OrgID,String key,int Type);

    IPage<Map<String,Object>> SystemUserListByOrgID_SelectN(IPage page, String authCompanyID,String OrgID,String key,int Type,
    		String UserName,String Mobile,String Status);

    List<Map<String, Object>> insertJob(String userID, String authCompanyID, String productID);


    Result getJobByUserName(String userID);

    SAccount checkUser0(String username, String mobile);

    SAccount checkUser1(String username, String mobile);

    /**
     * 根据手机号获取存在员工数量
     *
     * @param mobile
     * @return
     */
    int getUserAmount(String mobile) throws Exception;
}
