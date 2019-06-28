package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
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

    List<ChannelDto> getList(@Param("ProjectID") String ProjectID,@Param("sqlWhere") String sqlWhere,@Param("pageSize")int pageSize,@Param("pageNum")int pageNum);

    void ChannelDetail_InsertN(ChannelInsertDto channelInsertDto);

    List<Map<String, String>> ChannelOrgAllList_SelectN(String projectID, String pOrgName);

    Integer getRecordCount(@Param("ProjectID")String projectID, @Param("sqlWhere")String sqlWhere);

    void ChannelStatus_UpdateN(@Param("ID")String id, @Param("UserID")String userID, @Param("Status")String status);
}
