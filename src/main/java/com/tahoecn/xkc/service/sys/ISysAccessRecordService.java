package com.tahoecn.xkc.service.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.SysAccessRecord;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  接口信息记录服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-03-31
 */
public interface ISysAccessRecordService extends IService<SysAccessRecord> {

    /**
     * 根据请求获取相关信息用于记录接口
     *
     * @param request
     * @return
     */
    public SysAccessRecord getSysAccessRecord(HttpServletRequest request);
}
