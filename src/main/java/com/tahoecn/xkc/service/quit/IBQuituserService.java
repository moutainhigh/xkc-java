package com.tahoecn.xkc.service.quit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface IBQuituserService {

    IPage<Map<String,Object>> QuitUserOwnTeamList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelList_Select(IPage page,Map<String, Object> map);
}
