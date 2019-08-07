package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface BChannelorgMapper extends BaseMapper<BChannelorg> {

    List<ChannelDto> getList(@Param("ProjectID") String ProjectID,@Param("sqlWhere") String sqlWhere,@Param("pageSize")int pageSize,@Param("pageNum")int pageNum);

    void ChannelDetail_InsertN(ChannelInsertDto channelInsertDto);

    List<Map<String, String>> ChannelOrgAllList_SelectN(@Param("ProjectID")String projectID, @Param("pOrgName")String pOrgName);

    Integer getRecordCount(@Param("ProjectID")String projectID, @Param("sqlWhere")String sqlWhere);

    void ChannelStatus_UpdateN(@Param("ID")String id, @Param("UserID")String userID, @Param("Status")String status);

    int ChannelOrgNameIsExist_SelectN(@Param("OrgName")String orgName,@Param("sqlWhere")String sqlWhere);

    String getOrgCode();

    List<Map<String,Object>> ChannelExcelList_SelectN(@Param("projectID")String projectID, @Param("sqlWhere")StringBuilder sqlWhere);

    IPage<Map<String,String>> AgenList_SelectN(IPage page, @Param("where")StringBuffer where, @Param("orderBy")StringBuffer orderBy);

    List<Map<String,Object>> getParentOrg();
}
