package com.tahoecn.xkc.service.quit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.converter.Result;
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

    IPage<Map<String,Object>> QuitUserOwnTeamUserList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserOwnTeamCustomerList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamUserList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamCustomerList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelUserList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelCustomerList_Select(IPage page, Map<String, Object> map);

    Result QuitUserOwnTeamCustomerDetail_Update(Map<String, Object> map);

    Result QuitUserSalesTeamCustomerDetail_Update(Map<String, Object> map);

    Result QuitUserChannelCustomerDetail_Update(Map<String, Object> map);
}
