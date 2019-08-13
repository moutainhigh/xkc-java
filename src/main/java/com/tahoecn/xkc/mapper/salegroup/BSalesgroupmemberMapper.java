package com.tahoecn.xkc.mapper.salegroup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import com.tahoecn.xkc.model.vo.SalesGroupMemberExistProjectVo;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface BSalesgroupmemberMapper extends BaseMapper<BSalesgroupmember> {
    List<Map<String,Object>> SalesGroupMemberList_Select(Map<String, Object> map);

    IPage<Map<String,Object>> SalesGroupTeamNoAddedList_Select(IPage page, Map<String, Object> map);

    IPage<Map<String,Object>> SalesGroupTeamAlreadyAddedList_Select(IPage page, Map<String, Object> map);

    String getChannelOrgId(Map<String, Object> map);

    SalesGroupMemberExistProjectVo SalesGroupMemberExistProject_Select(Map<String, Object> map);

	Map<String,Object> getSaleGroupName(Map<String, Object> map);
}
