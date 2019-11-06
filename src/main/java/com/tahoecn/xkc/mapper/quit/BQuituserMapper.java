package com.tahoecn.xkc.mapper.quit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.quit.BQuituser;
import com.tahoecn.xkc.model.vo.QuitUserUpdateVo;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface BQuituserMapper extends BaseMapper<BQuituser> {
    IPage<Map<String,Object>> QuitUserOwnTeamList_Select(IPage page,@Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamList_Select(IPage page,@Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelList_Select(IPage page,@Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserOwnTeamUserList_Select(IPage page, @Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserOwnTeamCustomerList_Select(IPage page, @Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamUserList_Select(IPage page, @Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserSalesTeamCustomerList_Select(IPage page, @Param("paramMap") Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelUserList_Select(IPage page, @Param("paramMap")Map<String, Object> map);

    IPage<Map<String,Object>> QuitUserChannelCustomerList_Select(IPage page, @Param("paramMap")Map<String, Object> map);

    QuitUserUpdateVo getQuitUserOwnTeamMember1(@Param("userID")String userID, @Param("ProjectID")String ProjectID);

    QuitUserUpdateVo getQuitUserOwnTeamMember3(@Param("clueId")String clueId);

    int getCustomerCount(@Param("ID")String id);

    QuitUserUpdateVo getQuitSalesTeamMember1(@Param("UserID")String UserID,@Param("ProjectID")String ProjectID);

    QuitUserUpdateVo getQuitSalesTeamMember2(@Param("SelectUserID")String SelectUserID,@Param("ProjectID")String ProjectID);

    QuitUserUpdateVo getQuitSalesTeamMember3(@Param("oppoId")String oppoId);

    QuitUserUpdateVo getQuitSalesTeamMember4(@Param("SaleUser")String SaleUser);

    int getOppoCustomerCount(@Param("ID")String id);

    int getChannelCustomerCount(@Param("ID") String id);
}
