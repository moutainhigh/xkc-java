package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.service.channel.IBChanneluserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-25
 */
@Service
public class BChanneluserServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements IBChanneluserService {

	@Autowired
    private BChanneluserMapper bChanneluserMapper;
	
    @Override
    public List<Map<String, String>> AgenApproverList() {
        return baseMapper.AgenApproverList();
    }

    @Override
    public int ChannelAgenUserNameIsExist_SelectN(String userName,String sqlWhere) {
        return baseMapper.ChannelAgenUserNameIsExist_SelectN(userName,sqlWhere);
    }

    @Override
    public int ChannelAgenMobileIsExist_SelectN(String mobile,String sqlWhere) {
        return baseMapper.ChannelAgenMobileIsExist_SelectN(mobile,sqlWhere);
    }

    @Override
    public HashMap<String, Object> ChannelUserCurrency_Find(String mobile) {
        return baseMapper.ChannelUserCurrency_Find(mobile);
    }

    @Override
    public Map<String, Object> BrokerMyCenter_Select(String brokerID) {
        return baseMapper.BrokerMyCenter_Select(brokerID);
    }

    @Override
    public int mBrokerChannelUserCardDetail_Update(String userID, String certificatesName, String certificatesType, String certificatesNo, String certificatesPicFace, String certificatesPicBack) {
        return baseMapper.mBrokerChannelUserCardDetail_Update(userID, certificatesName,certificatesType,certificatesNo,certificatesPicFace,certificatesPicBack);
    }

    @Override
    public int mBrokerChannelUserBankCardDetail_Update(String userID, String bankCardPerson, String bankCardCreate, String bankCard, String bankCardProvince, String bankCardCity, String bankCardArea, String bankCardBranch, String bankCardPic) {
        return baseMapper.mBrokerChannelUserBankCardDetail_Update( userID,  bankCardPerson,  bankCardCreate,  bankCard,  bankCardProvince,
                                                                   bankCardCity,  bankCardArea,  bankCardBranch,  bankCardPic);
    }

    @Override
    public Map<String, Object> mBrokerChannelUserDetail_Select(String userID) {
        return baseMapper.mBrokerChannelUserDetail_Select(userID);
    }

    @Override
    public int mBrokerChannelUserDetail_Upate(String userID, String name, String gender, String mobile, String channelTypeID) {
        return baseMapper.mBrokerChannelUserDetail_Upate(userID, name, gender, mobile, channelTypeID);
    }

    @Override
    public Map<String, Object> ChannelUser_Find(String mobile, String password) {
        return baseMapper.ChannelUser_Find(mobile,password);
    }

    /*
	 * 用户信息查询
	 *
	 */
	@Override
	public List<BChanneluser> ChannelUser_Detail_FindById(Map<String, Object> map) {
		return bChanneluserMapper.ChannelUser_Detail_FindById(map);
	}

	/**
	 * 修改密码-判断原密码是否正确
	 */
	@Override
	public List<BChanneluser> ChannelUserPassWord_Select(Map<String, Object> map) {
		return bChanneluserMapper.ChannelUserPassWord_Select(map);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean ChannelUserPassWord_Update(Map<String, Object> map){
		return bChanneluserMapper.ChannelUserPassWord_Update(map);
	}

}
