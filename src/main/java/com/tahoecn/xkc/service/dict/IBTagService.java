package com.tahoecn.xkc.service.dict;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.dict.BTag;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface IBTagService extends IService<BTag> {
	/**
	 * 获取Tag列表信息
	 */
	List<Map<String, Object>> BTaglist(String userID);

}
