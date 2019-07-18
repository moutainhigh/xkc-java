package com.tahoecn.xkc.mapper.salegroup;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;

/**
 * <p>
 * 树形结构：项目--团队--组 Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface BSalesgroupMapper extends BaseMapper<BSalesgroup> {
	/**
     * 查询渠道身份
     */
	Map<String, Object> mShareChannelTypeID_Select(Map<String, Object> channelTypeObj);

}
