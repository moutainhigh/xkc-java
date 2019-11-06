package com.tahoecn.xkc.service.uc;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.CsSendSmsLog;

/**
 * <p>
 * 常见问题表 服务类
 * </p>
 *
 * @author zghw
 * @since 2018-11-18
 */
public interface CsSendSmsLogService extends IService<CsSendSmsLog> {

	void sendSms(String mobiles, String content, String userName);

	void sendToAll(String msg);

}
