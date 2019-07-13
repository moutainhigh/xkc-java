package com.tahoecn.xkc.mapper.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.sys.SAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
public interface SAccountMapper extends BaseMapper<SAccount> {

    HashMap<String,String> getUserJob(@Param("userName") String userName);
    HashMap<String,String> getUserPorject(@Param("userId") String userId,@Param("productID") String productID);
    List<HashMap<String,String>> getUserProduct(@Param("userName") String userName);

    List<HashMap<String,String>> getUserWXApp(@Param("userName") String userName);
    List<HashMap<String,String>> getUserJobs(@Param("userName") String userName,@Param("productID") String productID);
    List<HashMap<String,String>> getUserJobMenus(@Param("userName") String userName,@Param("productID") String productID);
    List<HashMap<String,String>> getJobFunctions(@Param("userName") String userName,@Param("productID") String productID);
    List<HashMap<String,String>> getJobProject(@Param("userName") String userName);

    HashMap<String,Object> mLoginSelectByAccount(@Param("userName") String userName);
    HashMap<String,Object> mLoginSelectByChannelUser(@Param("userName") String userName);

    List<HashMap<String,String>> salesUserProjectList(@Param("userId") String userId);

    List<HashMap<String,String>> userProjectJobList(@Param("userId") String userID, @Param("projectID") String projectID);

    List<HashMap<String,String>> GetMenuAndFunList_Select(@Param("jobCode") String jobCode, @Param("projectID") String projectID);

    IPage<Map<String,Object>> SystemUserListByOrgID_Select(IPage page, @Param("AuthCompanyID") String authCompanyID, @Param("OrgID")String orgID, @Param("Key")String key);

    HashMap<String,Object> insertJob(@Param("UserID")String userID, @Param("AuthCompanyID")String authCompanyID, @Param("ProductID")String productID);
}
