package com.tahoecn.xkc.service.salegroup;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.salegroup.BSalesuser;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface IBSalesuserService extends IService<BSalesuser> {

    List<Map<String, Object>> UserSalesList_Select(String projectID, String teamID);

    /*
     * 分享小程序
     */
	Map<String, Object> mShareAppDetail_Select(Map<String, Object> map);

	String mShareAppDetail_Select1(Map<String, Object> map);

    Map<String, Object> mLFLogin_Select(Map<String, Object> paramMap);
}
