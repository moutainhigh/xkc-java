package com.tahoecn.xkc.service.uc.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.uc.CsSendtoallMapper;
import com.tahoecn.xkc.model.CsSendtoall;
import com.tahoecn.xkc.service.uc.CsSendtoallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 期初发送短信通知表 服务实现类
 * </p>
 *
 * @author zghw
 * @since 2018-11-29
 */
@Transactional
@Service
public class CsSendtoallServiceImpl extends ServiceImpl<CsSendtoallMapper, CsSendtoall> implements CsSendtoallService {

}
