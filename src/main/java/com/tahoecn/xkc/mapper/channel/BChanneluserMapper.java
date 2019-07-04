package com.tahoecn.xkc.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
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
public interface BChanneluserMapper extends BaseMapper<BChanneluser> {

    List<Map<String, String>> AgenApproverList();

    void ChannelDetail_InsertN(ChannelInsertDto channelInsertDto);

    void ChannelStatus_UpdateN(@Param("ID")String id, @Param("UserID")String userID, @Param("Status")String status);

    int ChannelAgenUserNameIsExist_SelectN(@Param("UserName")String userName,@Param("sqlWhere")String sqlWhere);

    int ChannelAgenMobileIsExist_SelectN(@Param("Mobile")String mobile,@Param("sqlWhere")String sqlWhere);

    HashMap<String, Object> ChannelUserCurrency_Find(@Param("Mobile")String mobile);

    Map<String, Object> BrokerMyCenter_Select(@Param("BrokerID")String brokerID);

    int mBrokerChannelUserCardDetail_Update(@Param("UserID")String userID, @Param("CertificatesName")String certificatesName,
                                            @Param("CertificatesType")String certificatesType, @Param("CertificatesNo")String certificatesNo,
                                            @Param("CertificatesPicFace")String certificatesPicFace, @Param("CertificatesPicBack")String certificatesPicBack);

    int mBrokerChannelUserBankCardDetail_Update(@Param("UserID")String userID, @Param("BankCardPerson")String bankCardPerson, @Param("BankCardCreate")String bankCardCreate, @Param("BankCard")String bankCard, @Param("BankCardProvince")String bankCardProvince, @Param("BankCardCity")String bankCardCity, @Param("BankCardArea")String bankCardArea, @Param("BankCardBranch")String bankCardBranch, @Param("BankCardPic")String bankCardPic);

    Map<String, Object> mBrokerChannelUserDetail_Select(@Param("UserID")String userID);

    int mBrokerChannelUserDetail_Upate(@Param("UserID")String userID, @Param("Name")String name, @Param("Gender")String gender, @Param("Mobile")String mobile, @Param("ChannelTypeID")String channelTypeID);

    Map<String, Object> ChannelUser_Find(@Param("Mobile")String mobile,@Param("Password") String password);
    
    /*
	 * 用户信息查询
	 */
	List<BChanneluser> ChannelUser_Detail_FindById(Map<String, Object> map);
}
