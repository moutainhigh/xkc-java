package com.tahoecn.xkc.service.sys;

import com.tahoecn.xkc.model.sys.SAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

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
}
