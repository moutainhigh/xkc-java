package com.tahoecn.xkc.mapper.customer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.customer.BClue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-03
 */
public interface BClueMapper extends BaseMapper<BClue> {

    Map<String, Object> BrokerClueDetail_Select(@Param("ClueID") String clueID);

    List<Map<String, Object>> TrackList_Select(@Param("ClueID")String clueID);
}
