package com.tahoecn.xkc.service.sys.impl;

import com.tahoecn.xkc.mapper.sys.SystemMessageMapper;
import com.tahoecn.xkc.model.vo.UnreadCountVo;
import com.tahoecn.xkc.service.sys.ISystemMessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
@Service
public class SystemMessageServiceImpl implements ISystemMessageService {

    @Autowired
    private SystemMessageMapper systemMessageMapper;

    @Override
    public List<UnreadCountVo> UnreadCountListByMessageType_Select(String projectId, String userId) {
        return systemMessageMapper.UnreadCountListByMessageType_Select(projectId, userId);
    }
}
