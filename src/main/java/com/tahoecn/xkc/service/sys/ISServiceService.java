package com.tahoecn.xkc.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.SService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-25
 */
public interface ISServiceService extends IService<SService> {

    IPage<Map<String, Object>> ServiceList_Select(int pageindex, int pagesize);

    IPage<Map<String, Object>> ServiceLogListByService_Select(IPage page, String serviceID, int status);
}
