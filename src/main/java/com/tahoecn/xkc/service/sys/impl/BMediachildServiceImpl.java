package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.BMediachildMapper;
import com.tahoecn.xkc.model.sys.BMediachild;
import com.tahoecn.xkc.service.sys.IBMediachildService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
@Service
public class BMediachildServiceImpl extends ServiceImpl<BMediachildMapper, BMediachild> implements IBMediachildService {

    @Override
    public void MediaChildSave(BMediachild bMediachild) {
        baseMapper.MediaChildSave(bMediachild);
    }

    @Override
    public void MediaChildUpdate(BMediachild bMediachild) {
        baseMapper.MediaChildUpdate(bMediachild);
    }
}
