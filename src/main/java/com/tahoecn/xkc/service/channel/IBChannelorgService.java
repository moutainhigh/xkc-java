package com.tahoecn.xkc.service.channel;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
public interface IBChannelorgService extends IService<BChannelorg> {

    Map getList(String ProjectID,String sqlWhere,int pageSize,int pageNum);

    void ChannelDetail_InsertN(ChannelInsertDto channelDto);

    List<Map<String, String>> ChannelOrgAllList_SelectN(String projectID, String pOrgName);

    void ChannelStatus_UpdateN(String id, String userID, String status);

    int ChannelOrgNameIsExist_SelectN(String orgName,String sqlWhere);

    String getOrgCode();

    JSONResult insertOrg(Map<String,String>  map);

    List<Map<String, Object>> ChannelExcelList_SelectN(String projectID, StringBuilder sqlWhere);

    IPage<Map<String, String>> AgenList_SelectN(IPage page,StringBuffer where, StringBuffer orderBy);

    List<Map<String, Object>> getParentOrg();

    List<Map<String,Object>> getChildOrg(String id);
}
