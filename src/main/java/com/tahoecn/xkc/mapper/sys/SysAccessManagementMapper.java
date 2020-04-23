package com.tahoecn.xkc.mapper.sys;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.SysAccessManagement;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2020-03-31
 */
public interface SysAccessManagementMapper extends BaseMapper<SysAccessManagement> {

    /**
     * 获取所有系统允许ip配置信息
     *
     * @return
     */
    List<SysAccessManagement> getAll();
}
