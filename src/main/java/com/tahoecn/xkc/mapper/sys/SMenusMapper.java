package com.tahoecn.xkc.mapper.sys;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.sys.SMenus;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
public interface SMenusMapper extends BaseMapper<SMenus> {

    List<Map<String, Object>> SystemMenusList_Select(IPage page);
}
