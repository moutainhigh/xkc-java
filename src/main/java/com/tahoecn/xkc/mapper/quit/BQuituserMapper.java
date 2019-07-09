package com.tahoecn.xkc.mapper.quit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.quit.BQuituser;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface BQuituserMapper extends BaseMapper<BQuituser> {
    IPage<Map<String,Object>> QuitUserOwnTeamList_Select(IPage page,Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamList_Select(IPage page,Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelList_Select(IPage page,Map<String, Object> map);
}
