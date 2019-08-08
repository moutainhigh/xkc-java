package com.tahoecn.xkc.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.sys.BMedialarge;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
public interface BMedialargeMapper extends BaseMapper<BMedialarge> {

    List<Map<String, Object>> getMediaLargeList();

    List<Map<String, Object>> getMediaChildList(String projectID);

    void MediaLargeSave(BMedialarge bMedialarge);

    void MediaLargeUpdate(BMedialarge bMedialarge);

}
