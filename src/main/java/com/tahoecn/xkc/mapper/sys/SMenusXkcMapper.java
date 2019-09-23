package com.tahoecn.xkc.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.model.sys.SMenusXkc;
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
 * @since 2019-07-12
 */
public interface SMenusXkcMapper extends BaseMapper<SMenusXkc> {
    List<Map<String,Object>> SystemMenusList_Select();

    void SystemMenu_Insert(SMenusXkc menus);

    String getOldPath(@Param("ID") String id);

    String getNewPath(@Param("ID")String id);

    List<Map<String, Object>> getResult();

    List<Map<String, Object>> getElseResult(@Param("CommonJobID") String commonJobID);

    List<Map<String, Object>> getOtherResult(@Param("JobID") String jobID, @Param("ID") String id);

    List<Map<String, Object>> UserFunctions(@Param("UserID") String userID, @Param("AuthCompanyID")String authCompanyID, @Param("ProductID")String productID);

    List<Map<String, Object>> CommonJobFunctions(@Param("JobID")String jobID);

    List<Map<String, Object>> getCommonJobFunctions(@Param("JobID")String jobID);

    List<Map<String, Object>> getAllMenuList();

}
