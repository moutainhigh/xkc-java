package com.tahoecn.xkc.service.user;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.user.CWxuser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
public interface ICWxuserService extends IService<CWxuser> {
	/**
	 * 获取该分享人的信息
	 */
	Map<String, Object> mGetShareDetail_Select(JSONObject parameter);

}
