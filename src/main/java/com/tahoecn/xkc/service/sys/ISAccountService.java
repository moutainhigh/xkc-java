package com.tahoecn.xkc.service.sys;

import com.tahoecn.xkc.model.sys.SAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-21
 */
public interface ISAccountService extends IService<SAccount> {

    HashMap<String,String> getUserJob(String userName);

}
