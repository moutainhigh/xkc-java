package com.tahoecn.xkc.service.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.SMenus;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
public interface ISMenusService extends IService<SMenus> {

    Result SystemDictionaryDetail(HashMap<String,Object> param);
}
