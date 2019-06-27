package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface BChannelorgMapper extends BaseMapper<BChannelorg> {

    List<ChannelDto> getList(Page page,@Param("ProjectID") String ProjectID,@Param("sqlWhere") String sqlWhere);

}
