package com.tahoecn.xkc.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.sys.BMedialarge;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
public interface IBMedialargeService extends IService<BMedialarge> {

    List<Map<String, Object>> getMediaList_Select(String projectID);

    Result Media_SaveOrUpdate(String projectID,String mediaLargeID, String name, String shortName, int listIndex, int status);

    Result Media_Status(String mediaLargeID, String id, int status);

    Result Media_Delete(String mediaLargeID, String id);
}
