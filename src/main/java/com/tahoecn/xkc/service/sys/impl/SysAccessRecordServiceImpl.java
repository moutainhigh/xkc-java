package com.tahoecn.xkc.service.sys.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 接口信息记录服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-03-31
 */
@Service
public class SysAccessRecordServiceImpl extends ServiceImpl<SysAccessRecordMapper, SysAccessRecord> implements ISysAccessRecordService {

    @Resource
    private SysAccessManagementServiceImpl sysAccessManagementServiceImpl;

    @Override
    public SysAccessRecord getSysAccessRecord(HttpServletRequest request) {
        SysAccessRecord sysAccessRecord = new SysAccessRecord();
        sysAccessRecord.setId(UUID.randomUUID().toString());
        String remoteAddr = sysAccessManagementServiceImpl.getIpAddress(request);
        sysAccessRecord.setAccessIp(remoteAddr);
        sysAccessRecord.setInterfaceName(request.getRequestURL().toString());
        sysAccessRecord.setAccessTime(new Date());
        return sysAccessRecord;
    }
}
