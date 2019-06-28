package com.tahoecn.xkc.service.sys;


import com.tahoecn.xkc.model.vo.UnreadCountVo;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-17
 */
public interface ISystemMessageService {

    List<UnreadCountVo> UnreadCountListByMessageType_Select(String projectId, String userId);
}
