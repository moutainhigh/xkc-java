package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.BVerificationcodeMapper;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;

import com.tahoecn.xkc.service.uc.CsSendSmsLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

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

    @Autowired
    private CsSendSmsLogService csSendSmsLogService;
    @Override
    public void getCodeAndSendsmg(String mobile,String msg) {
        //生成验证码
        StringBuilder verificationCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            verificationCode.append(random.nextInt(10));
        }
        //先删除号码之前的验证码信息------验证时取创建时间最近的一条
//        QueryWrapper<BVerificationcode> wrapper=new QueryWrapper<>();
//        wrapper.eq("Mobile",mobile);
//        baseMapper.delete(wrapper);
        //在添加当前时间的验证码信息
        Date time=new Date();
        Date overdueTime=new Date(time.getTime()+10*60*1000);
        BVerificationcode vc=new BVerificationcode();
        vc.setMobile(mobile);
        vc.setCreateTime(time);
        vc.setIsDel(0);
        vc.setStatus(1);
        vc.setVerificationCode(verificationCode.toString());
        vc.setOverdueTime(overdueTime);
        baseMapper.insert(vc);

        csSendSmsLogService.sendSms(mobile,msg+verificationCode.toString()+"，5分钟内有效","");

    }

    @Override
    public BVerificationcode checkAuthCode(String mobile) {
        return baseMapper.checkAuthCode(mobile);
    }

    /**
     * 新增验证码
     */
	@Override
	public void Detail_Add(Map<String, Object> parameter) {
		String Mobile = (String) parameter.get("Mobile");
		String VerificationCode = (String) parameter.get("VerificationCode");
		baseMapper.VerificationCodeDetail_Insert(Mobile);
		Date time=new Date();
        Date overdueTime=new Date(time.getTime()+24*60*60*1000);
		BVerificationcode bv = new BVerificationcode();
		bv.setCreateTime(time);
		bv.setIsDel(0);
		bv.setMobile(Mobile);
		bv.setOverdueTime(overdueTime);
		bv.setStatus(1);
		bv.setVerificationCode(VerificationCode);
		baseMapper.insert(bv);
	}
	/**
	 * 验证验证码
	 */
	@Override
	public boolean Verification(String mobile,String AuthCode){
		//获取验证码
		Map<String, Object> entity = baseMapper.VerificationCodeDetail_Select(mobile);
		if(entity != null ){
			String code = (String) entity.get("VerificationCode");
			if(AuthCode.equals(code)){
				return true;
			}
		}
		return false;
	}
}
