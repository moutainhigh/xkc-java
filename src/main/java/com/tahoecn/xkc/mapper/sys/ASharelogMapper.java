package com.tahoecn.xkc.mapper.sys;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.ASharelog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-18
 */
public interface ASharelogMapper extends BaseMapper<ASharelog> {

	Map<String, Object> selShareProjectID(@Param("ProjectID")String projectID);

}
