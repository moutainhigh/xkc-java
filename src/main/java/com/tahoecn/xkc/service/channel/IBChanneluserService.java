package com.tahoecn.xkc.service.channel;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.dto.ChannelDto;

import java.util.HashMap;
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
public interface IBChanneluserService extends IService<BChanneluser> {

    List<Map<String, String>> AgenApproverList();

    int ChannelAgenUserNameIsExist_SelectN(String userName,String sqlWhere);

    int ChannelAgenMobileIsExist_SelectN(String mobile,String sqlWhere);

    HashMap<String, Object> mLoginTK_SelectN(String mobile);

    Map<String, Object> BrokerMyCenter_Select(String brokerID);

    int mBrokerChannelUserCardDetail_Update(String userID, String certificatesName, String certificatesType, String certificatesNo, String certificatesPicFace, String certificatesPicBack);

    int mBrokerChannelUserBankCardDetail_Update(String userID, String bankCardPerson, String bankCardCreate, String bankCard, String bankCardProvince, String bankCardCity, String bankCardArea, String bankCardBranch, String bankCardPic);

    Map<String, Object> mBrokerChannelUserDetail_Select(String userID);

    int mBrokerChannelUserDetail_Upate(String userID, String name, String gender, String mobile, String channelTypeID);
}
