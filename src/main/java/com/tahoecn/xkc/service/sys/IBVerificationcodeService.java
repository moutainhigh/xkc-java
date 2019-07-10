package com.tahoecn.xkc.service.sys;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.sys.BVerificationcode;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface IBVerificationcodeService extends IService<BVerificationcode> {

    void getCodeAndSendsmg(String mobile, String verificationCode);

    BVerificationcode checkAuthCode(String mobile);

    /**
     * 新增验证码
     */
	void Detail_Add(Map<String, Object> parameter);
}
