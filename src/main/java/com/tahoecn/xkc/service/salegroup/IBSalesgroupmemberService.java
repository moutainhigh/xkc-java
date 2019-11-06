package com.tahoecn.xkc.service.salegroup;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface IBSalesgroupmemberService extends IService<BSalesgroupmember> {
    List<Map<String, Object>> SalesGroupMemberList_Select(Map<String, Object> map);

    Map<String,Object> SalesGroupTeamList_Select(IPage page,Map<String, Object> map);

    Result SalesGroupMembers_Insert(Map<String, Object> map);
    
    Result setZqLeader(Map<String, Object> map);
}
