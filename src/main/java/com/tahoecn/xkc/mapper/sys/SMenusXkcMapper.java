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

    void SystemMenu_Insert(SMenus menus);

    String getOldPath(@Param("ID") String id);

    String getNewPath(@Param("ID")String id);

    List<HashMap<String, Object>> getResult();
}
