package com.tahoecn.xkc.service.channel;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.channel.BSalesuser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-09
 */
public interface IBSalesuserService extends IService<BSalesuser> {

    List<Map<String, Object>> UserSalesList_Select(String projectID, String teamID);
}
