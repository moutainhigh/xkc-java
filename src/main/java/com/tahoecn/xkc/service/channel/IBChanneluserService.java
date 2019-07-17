package com.tahoecn.xkc.service.channel;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.model.channel.BChanneluser;

import java.util.Date;
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

    HashMap<String, Object> ChannelUserCurrency_Find(String mobile);

    Map<String, Object> BrokerMyCenter_Select(String brokerID);

    int mBrokerChannelUserCardDetail_Update(String userID, String certificatesName, String certificatesType, String certificatesNo, String certificatesPicFace, String certificatesPicBack);

    int mBrokerChannelUserBankCardDetail_Update(String userID, String bankCardPerson, String bankCardCreate, String bankCard, String bankCardProvince, String bankCardCity, String bankCardArea, String bankCardBranch, String bankCardPic);

    Map<String, Object> mBrokerChannelUserDetail_Select(String userID);

    int mBrokerChannelUserDetail_Upate(String userID, String name, String gender, String mobile, String channelTypeID);

    Map<String, Object> ChannelUser_Find(String mobile, String password);

    /*
	 * 用户信息查询
	 *
	 */
	List<BChanneluser> ChannelUser_Detail_FindById(Map<String, Object> map);

	/**
	 * 修改密码-判断原密码是否正确
	 */
	List<BChanneluser> ChannelUserPassWord_Select(Map<String, Object> map);

	/**
	 * 修改密码
	 */
	boolean ChannelUserPassWord_Update(Map<String, Object> map);

    Result  register(Map paramMap);

    boolean checkMobile(String mobile);

    Result registerN(Map paramMap);

    /*
	 * 删除兼职成员
	 *
	 */
	void mChannelTempPerson_Delete(Map<String, Object> map);

	/*
	 * 专员考勤
	 *
	 */
	List<Map<String, Object>> mChannelCheckClockTotal_Select(IPage page, String ChannelTaskID, String CheckDate);

	/*
	 * 详细考勤
	 */
	List<Map<String, Object>> mChannelTaskCheckClockList_Select(IPage page, String ChannelTaskID, String CheckDate);

	/*
	 * 打卡状态查询
	 */
	List<Map<String, Object>> mChannelCheckClockPage_Select(Map<String, Object> map);

	/*
	 * 详细考勤数量
	 */
	int mChannelTaskCheckClockList_SelectAllCount(Map<String, Object> map);

	/*
	 * 考勤信息查询
	 */
	List<Map<String, Object>> mChannelCheckClockPerson_Select(IPage page, String UserID, String CheckDate);

	/*
	 * 考勤信息数量统计
	 */
	int mChannelCheckClockPerson_SelectAllCount(Map<String, Object> map);

	/*
	 * 兼职考勤日历打卡信息
	 */
	List<Map<String, Object>> mChannelCheckClockCountDaily_Select(Map<String, Object> map);




    int isReport(String userID);

    String getChannelOrgID(String userID,String adviserGroupID);

    Map<String, Object> GetReportUserInfo_Select(String userID, String intentProjectID, String channelIdentify);

	/*
	 * 考勤异常
	 */
	List<Map<String, Object>> mChannelUserAbnormal_Select(IPage page, String ChannelTaskID, String SiteUrl);
	
    /*
     * 考勤异常数量统计
     */
	int mChannelUserAbnormal_SelectAllCount(Map<String, Object> map);

	/**
	 * 验证是否重复手机号
	 */
	Integer ChannelUserCheckMobile_Select(Map<String, Object> paramMap);

	/**
	 * 行销拓客兼职注册
	 */
	void mChannelRegistJZ_Insert(Map<String, Object> paramMap);


    IPage<Map<String, Object>> AgenList_SelectN(IPage page, int PageType, String ProjectID, String ChannelTypeID, String Name, String PassStatu
            , Date CreateStartTime, Date CreateEndTime, String ApprovalUserID);
}
