package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.BVerificationcodeMapper;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class BVerificationcodeServiceImpl extends ServiceImpl<BVerificationcodeMapper, BVerificationcode> implements IBVerificationcodeService {

    @Override
    public void getCodeAndSendsmg(String mobile, String verificationCode) {
        //先删除号码之前的验证码信息
        QueryWrapper<BVerificationcode> wrapper=new QueryWrapper<>();
        wrapper.eq("Mobile",mobile);
        baseMapper.delete(wrapper);
        //在添加当前时间的验证码信息
        Date time=new Date();
        Date overdueTime=new Date(time.getTime()+10*60*1000);
        BVerificationcode vc=new BVerificationcode();
        vc.setMobile(mobile);
        vc.setCreateTime(time);
        vc.setIsDel(0);
        vc.setStatus(1);
        vc.setVerificationCode(verificationCode);
        vc.setOverdueTime(overdueTime);
        baseMapper.insert(vc);
        //todo 发送短信未完成

    }

    @Override
    public BVerificationcode checkAuthCode(String mobile) {
        return baseMapper.checkAuthCode(mobile);
    }
}
