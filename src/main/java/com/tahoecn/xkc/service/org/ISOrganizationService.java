package com.tahoecn.xkc.service.org;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.org.SOrganization;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-06
 */
public interface ISOrganizationService extends IService<SOrganization> {

    List<Map<String, Object>> SystemOrganization_Select(String authCompanyID, String orgID, String productID, String pid, String status);

}
