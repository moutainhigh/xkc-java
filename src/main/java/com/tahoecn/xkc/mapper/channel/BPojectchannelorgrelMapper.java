package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.channel.BPojectchannelorgrel;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface BPojectchannelorgrelMapper extends BaseMapper<BPojectchannelorgrel> {
    //改为删除状态
    int updateToDelete(@Param("OrgID")String OrgID,@Param("UserID")String UserID);

}
