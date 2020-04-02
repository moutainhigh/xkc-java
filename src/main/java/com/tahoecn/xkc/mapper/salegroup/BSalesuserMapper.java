package com.tahoecn.xkc.mapper.salegroup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.miniprogram.vo.UserRegisterVO;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
public interface BSalesuserMapper extends BaseMapper<BSalesuser> {

    List<Map<String, Object>> UserSalesList_Select(@Param("projectID") String projectID, @Param("teamID") String teamID);

    /*
     * 分享小程序
     */
	Map<String, Object> mShareAppDetail_Select(Map<String, Object> map);

	String mShareAppDetail_Select1(Map<String, Object> map);

    Map<String, Object> mLFLogin_Select(Map<String, Object> paramMap);

    /**
     * 思为小程序客储用户中心验证
     *
     * @param userRegisterVO
     * @return
     */
    BSalesuser getBSalesuser(@Param("userRegister") UserRegisterVO userRegisterVO);
}
