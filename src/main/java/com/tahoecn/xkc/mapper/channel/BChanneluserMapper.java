package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import org.apache.ibatis.annotations.Param;

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
public interface BChanneluserMapper extends BaseMapper<BChanneluser> {

    List<Map<String, String>> AgenApproverList();

    void ChannelDetail_InsertN(ChannelInsertDto channelInsertDto);

    void ChannelStatus_UpdateN(@Param("ID")String id, @Param("UserID")String userID, @Param("Status")String status);
}
