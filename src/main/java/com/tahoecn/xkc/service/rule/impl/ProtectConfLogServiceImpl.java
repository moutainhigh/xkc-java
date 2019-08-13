package com.tahoecn.xkc.service.rule.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.rule.ProtectConfLogMapper;
import com.tahoecn.xkc.model.rule.ProtectConfLog;
import com.tahoecn.xkc.service.rule.IProtectConfLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-08-13
 */
@Service
public class ProtectConfLogServiceImpl extends ServiceImpl<ProtectConfLogMapper, ProtectConfLog> implements IProtectConfLogService {

    @Override
    public List<Map<String, Object>> getProtectConfLogList(String projectName, String groupDictName, Integer protectSource, String ruleName, String editorName, Date start, Date end) {
        return baseMapper.getProtectConfLogList( projectName,  groupDictName,  protectSource,  ruleName,  editorName,  start,  end);
    }
}
