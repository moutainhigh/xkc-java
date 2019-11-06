package com.tahoecn.xkc.service.salegroup.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
@Service
public class BSalesuserServiceImpl extends ServiceImpl<BSalesuserMapper, BSalesuser> implements IBSalesuserService {

    @Override
    public List<Map<String, Object>> UserSalesList_Select(String projectID, String teamID) {
        return baseMapper.UserSalesList_Select(projectID,teamID);
    }

    /*
     * 分享小程序
     */
	@Override
	public Map<String, Object> mShareAppDetail_Select(Map<String, Object> map) {
		return baseMapper.mShareAppDetail_Select(map);
	}
	@Override
	public String mShareAppDetail_Select1(Map<String, Object> map) {
		return baseMapper.mShareAppDetail_Select1(map);
	}

    @Override
    public Map<String, Object> mLFLogin_Select(Map<String, Object> paramMap) {
        return baseMapper.mLFLogin_Select(paramMap);
    }
}
