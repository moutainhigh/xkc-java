package com.tahoecn.xkc.mapper.user;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.user.CWxuser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-17
 */
public interface CWxuserMapper extends BaseMapper<CWxuser> {
	/**
	 * 根据电话获取信息
	 */
	Map<String, Object> selWXUserByMobil(JSONObject parameter);

	Map<String, Object> selJobID(@Param("shareLogID")String shareLogID);

	Map<String, Object> selUserID(@Param("shareWXUserID")String shareWXUserID);
	/**
	 * 获取该分享人的信息
	 */
	Map<String, Object> getShareDetail(@Param("jobID")String jobID, @Param("saleUserID")String saleUserID);

}
