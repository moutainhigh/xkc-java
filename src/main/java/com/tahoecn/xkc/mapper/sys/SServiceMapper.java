package com.tahoecn.xkc.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.sys.SService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-25
 */
public interface SServiceMapper extends BaseMapper<SService> {

    IPage<Map<String, Object>> ServiceList_Select(IPage page);

    Map<String, Object> ServiceLogList_Select(String id);

    IPage<Map<String, Object>> ServiceLogListByService_Select(IPage page, @Param("serviceID") String serviceID,@Param("status") int status);
}
