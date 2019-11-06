package com.tahoecn.xkc.service.salegroup;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import java.util.Map;

/**
 * <p>
 * 树形结构：项目--团队--组 服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
public interface IBSalesgroupService extends IService<BSalesgroup> {

    void UserTeam_DeleteNew(Map<String, Object> map);

    void UserTeamStatus_Update(Map<String, Object> map);

    /**
     * 查询渠道身份
     */
	Map<String, Object> mShareChannelTypeID_Select(Map<String, Object> channelTypeObj);
}
