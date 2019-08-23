package com.tahoecn.xkc.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.model.sys.SMenusXkc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-12
 */
public interface ISMenusXkcService extends IService<SMenusXkc> {

    List<Map<String,Object>> SystemMenusList_Select();

    void SystemMenu_Insert(SMenusXkc menus);

    Result SystemMenu_Update(SMenusXkc menus);

    List<Map<String, Object>> SystemCommonJobAuth_Select(String userID, String authCompanyID, String productID, String jobID);

    List<Map<String, Object>> UserMenus(String userID, String authCompanyID, String productID);

    List<Map<String, Object>> getResult();

    List<Map<String, Object>> UserFunctions(String userID, String authCompanyID, String productID);

    Map<String, Object> CommonJobFunctions(String jobID);
}