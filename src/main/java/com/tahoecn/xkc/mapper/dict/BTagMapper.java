package com.tahoecn.xkc.mapper.dict;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.dict.BTag;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-08
 */
public interface BTagMapper extends BaseMapper<BTag> {
	/**
	 * 获取Tag列表信息
	 */
	List<Map<String, Object>> BTaglist(@Param("UserID")String userID);

}
