package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.dict.SDictionaryMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.model.sys.BVerificationcode;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import com.tahoecn.xkc.service.channel.IBChanneluserService;

import org.springframework.beans.factory.annotation.Autowired;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
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

    @Autowired
    private IBVerificationcodeService verificationcodeService;

    @Autowired
    private IBChanneluserService channeluserService;

    @Autowired
    private IBChannelorgService channelorgService;

    @Autowired
    private ISDictionaryService dictionaryService;
    @Autowired
    private SDictionaryMapper sDictionaryMapper;
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
    /**
     * H5注册
     * @param paramMap
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result register(Map paramMap) {
        Result result = new Result();
        try {
            String mobile = (String) paramMap.get("Mobile");
            String authCode = (String) paramMap.get("AuthCode");
            String password = (String) paramMap.get("Password");
            String name = (String) paramMap.get("Name");
            String gender = (String) paramMap.get("Gender");
            String channelOrgCode = (String) paramMap.get("ChannelOrgCode");
            String channelTypeID = (String) paramMap.get("ChannelTypeID");
            String userID = (String) paramMap.get("UserID");
            BVerificationcode vc = verificationcodeService.checkAuthCode(mobile);

            if (vc == null || !StringUtils.equals(authCode, vc.getVerificationCode())) {
                result.setErrcode(1);
                result.setErrmsg("验证码验证失败");
                return result;
            }
            //手机验证码存在且未超时 验证码正确
            //验证手机号重复 真可用
            boolean b = channeluserService.checkMobile(mobile);
            if (!b) {
                result.setErrcode(1);
                result.setErrmsg("手机号重复");
                return result;
            }
            //用手机号关联门店信息
            //验证机构码是否存在
            QueryWrapper<BChannelorg> wrapper = new QueryWrapper<>();
            wrapper.eq("OrgCode", channelOrgCode).eq("IsDel", 0).eq("Status", 1);
            BChannelorg one = channelorgService.getOne(wrapper);
            if (one == null) {
                result.setErrcode(1);
                result.setErrmsg("邀请链接已经失效，请联系机构负责人");
                return result;
            }
            //重新查一次 不知道为什么
            QueryWrapper<BChannelorg> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("OrgCode", channelOrgCode);
            List<BChannelorg> channelorgs = channelorgService.list(wrapper);
            int job = 2;
            int approvalStatus = 1;
            if (StringUtils.equals(mobile, channelorgs.get(0).getMobile())) {
                job = 1;
                approvalStatus = 1;
            }
            String orgId = channelorgs.get(0).getId();
            //保存人员信息   组建人员信息参数
            BChanneluser channeluser = new BChanneluser();
            channeluser.setMobile(mobile);
            channeluser.setUserName(mobile);
            channeluser.setPassword(SecureUtil.md5(password));
            channeluser.setName(name);
            channeluser.setGender(gender);
            channeluser.setChannelOrgCode(channelOrgCode);
            channeluser.setChannelOrgID(orgId);
            channeluser.setJob(job);
            channeluser.setApprovalStatus(approvalStatus);
            channeluser.setApprovalDate(new Date());
            channeluser.setChannelTypeID(channelTypeID);
            channeluser.setCreator(userID);
            channeluser.setCreateTime(new Date());
            channeluser.setIsDel(0);
            channeluser.setStatus(1);
            channeluserService.save(channeluser);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setErrcode(1);
            result.setErrmsg("保存错误");
            return result;
        }
            result.setErrcode(0);
            result.setErrmsg("保存成功");
            return result;
        }

        @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<BChanneluser> wrapper=new QueryWrapper<>();
        wrapper.eq("Mobile",mobile).eq("IsDel",0);
        BChanneluser channeluser = baseMapper.selectOne(wrapper);
        return channeluser==null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result registerN(Map paramMap) {
        Result result=new Result();
        try {
        String mobile = (String) paramMap.get("Mobile");
        String authCode = (String) paramMap.get("AuthCode");
        String password = (String) paramMap.get("Password");
        String name = (String) paramMap.get("Name");
        String gender = (String) paramMap.get("Gender");
        String channelOrgCode = (String) paramMap.get("ChannelOrgCode");
        String channelTypeID = (String) paramMap.get("ChannelTypeID");
        String userID = (String) paramMap.get("UserID");
        int job = 3;
        int approvalStatus = 1;
        BVerificationcode vc = verificationcodeService.checkAuthCode(mobile);

        if (vc == null || !StringUtils.equals(authCode, vc.getVerificationCode())) {
            result.setErrcode(1);
            result.setErrmsg("验证码验证失败");
            return result;
        }
        //手机验证码存在且未超时 验证码正确
        //验证手机号重复 真可用
        boolean b = channeluserService.checkMobile(mobile);
        if (!b) {
            result.setErrcode(1);
            result.setErrmsg("手机号重复");
            return result;
        }
        SDictionary channelType = dictionaryService.getById(channelTypeID);
        BChanneluser channeluser = new BChanneluser();
        channeluser.setMobile(mobile);
        channeluser.setUserName(mobile);
        channeluser.setPassword(SecureUtil.md5(password));
        channeluser.setName(name);
        channeluser.setGender(gender);
        channeluser.setJob(job);
        channeluser.setApprovalStatus(approvalStatus);
        channeluser.setChannelTypeID(channelTypeID);
        channeluser.setChannelType(channelType.getDictName());
        channeluser.setCreator(userID);
        channeluser.setCreateTime(new Date());
        channeluser.setIsDel(0);
        channeluser.setStatus(1);
        channeluserService.save(channeluser);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setErrcode(1);
            result.setErrmsg("保存错误");
            return result;
        }
        result.setErrcode(0);
        result.setErrmsg("保存成功");
        return result;
    }


    /*
   	 * 删除兼职成员
   	 *
   	 */
	@Override
	public void mChannelTempPerson_Delete(Map<String, Object> map) {
		bChanneluserMapper.mChannelTempPerson_Delete(map);
	}

	/*
	 * 专员考勤
	 *
	 */
	@Override
	public List<Map<String,Object>>  mChannelCheckClockTotal_Select(IPage page, String ChannelTaskID, String CheckDate) {
		return bChanneluserMapper.mChannelCheckClockTotal_Select(page, ChannelTaskID, CheckDate);

	}

	/*
	 * 详细考勤
	 *
	 */
	@Override
	public List<Map<String, Object>> mChannelTaskCheckClockList_Select(IPage page, String ChannelTaskID,
			String CheckDate) {
		return bChanneluserMapper.mChannelTaskCheckClockList_Select(page, ChannelTaskID, CheckDate);

	}

	/*
	 * 打卡状态查询
	 */
	@Override
	public List<Map<String, Object>> mChannelCheckClockPage_Select(Map<String, Object> map) {
		return bChanneluserMapper.mChannelCheckClockPage_Select(map);

	}


	/*
	 * 详细考勤数量统计
	 */
	@Override
	public int mChannelTaskCheckClockList_SelectAllCount(Map<String, Object> map) {
		return bChanneluserMapper.mChannelTaskCheckClockList_SelectAllCount(map);
	}

	/*
	 * 考勤信息查询
	 */
	@Override
	public List<Map<String, Object>> mChannelCheckClockPerson_Select(IPage page, String UserID, String CheckDate) {
		return bChanneluserMapper.mChannelCheckClockPerson_Select(page, UserID, CheckDate);

	}

	/*
	 * 考勤信息数量统计
	 */
	@Override
	public int mChannelCheckClockPerson_SelectAllCount(Map<String, Object> map) {
		return bChanneluserMapper.mChannelCheckClockPerson_SelectAllCount(map);

	}

	/*
	 * 兼职考勤日历打卡信息
	 */
	@Override
	public List<Map<String, Object>> mChannelCheckClockCountDaily_Select(Map<String, Object> map) {
		return bChanneluserMapper.mChannelCheckClockCountDaily_Select(map);

	}
    @Override
    public int isReport(String userID) {
        QueryWrapper<BChanneluser> wrapper=new QueryWrapper<>();
        wrapper.eq("ID",userID).eq("Job",2);
        BChanneluser channeluser = this.getOne(wrapper);
        if (channeluser==null) {
            //自渠
            return -1;
        }
            if (channeluser.getChannelOrgID()==null){
                //没有ChannelOrgID 的不能报备
                return -2;
            }
            BChannelorg channelorg = channelorgService.getById(channeluser.getChannelOrgID());
            if (channelorg.getStatus()==0){
                //ChannelOrgID禁用状态的不能报备
                return 0;
            }else {
                //有ChannelOrgID存在并且在启用状态，可以报备
                return 1;
            }
    }

    @Override
    public String getChannelOrgID(String userID,String adviserGroupID) {
        String channelOrgID= baseMapper.getChannelOrgID(userID);
        //如果渠道是分销中介，则把渠道身份赋值成机构ID
        if (StringUtils.isBlank(channelOrgID)){
            channelOrgID=adviserGroupID;
        }

        return channelOrgID;
    }

    @Override
    public Map<String, Object> GetReportUserInfo_Select(String userID, String intentProjectID, String channelIdentify) {
        return baseMapper.GetReportUserInfo_Select(userID,intentProjectID,channelIdentify);
    }

	/*
	 * 考勤异常
	 */
	@Override
	public List<Map<String, Object>> mChannelUserAbnormal_Select(IPage page, String ChannelTaskID, String SiteUrl) {
		return bChanneluserMapper.mChannelUserAbnormal_Select(page,ChannelTaskID,SiteUrl);
		
	}

	/*
	 * 考勤异常数量统计
	 */
	@Override
	public int mChannelUserAbnormal_SelectAllCount(Map<String, Object> map) {
		return bChanneluserMapper.mChannelUserAbnormal_SelectAllCount(map);
		
	}
	/**
	 * 验证是否重复手机号
	 */
	@Override
	public Integer ChannelUserCheckMobile_Select(Map<String, Object> map) {
		QueryWrapper<BChanneluser> wrapper = new QueryWrapper<BChanneluser>();
		wrapper.eq("Mobile", map.get("Mobile"));
		wrapper.eq("IsDel", 0);
		return bChanneluserMapper.selectCount(wrapper);
	}

    @Override
    public IPage<Map<String, Object>> ChannelOrgList_Select(Integer pageNum, Integer pageSize, String s) {
        return bChanneluserMapper.ChannelOrgList_Select(new Page(pageNum,pageSize), s);
    }

    @Override
    public void ChannelOrgImport_Insert(String orgID, String projectID, String userID,String RuleIDs) {
        Integer count = bChanneluserMapper.getB_PojectChannelOrgRel(orgID,projectID);
        if (count == 0){
            bChanneluserMapper.ChannelOrgImport_Insert_B_PojectChannelOrgRel(orgID, projectID, userID);
        }else{
            bChanneluserMapper.ChannelOrgImport_Insert_B_ClueRule_AdviserGroup(RuleIDs==null?"":RuleIDs, orgID, userID);
        }
    }

    /**
	 * 行销拓客兼职注册
	 */
	@Override
	public void mChannelRegistJZ_Insert(Map<String, Object> map) {
		String pwd = (String) map.get("Pwd");
		SDictionary d = new SDictionary();
		d = sDictionaryMapper.selectById("62DC076A-0900-4877-B4A3-D8A9ED0BC10E");
		BChanneluser user = new BChanneluser();
		user.setUserName(map.get("Mobile").toString());
		user.setPassword(SecureUtil.md5(pwd).toUpperCase());
		user.setGender("EC3936F8-82DC-49AF-A8EB-153730359DE7");//默认女
		user.setMobile(map.get("Mobile").toString());
		user.setName(map.get("Name").toString());
//		user.setCreator(Creator);
		user.setCreateTime(new Date());
		user.setIsDel(0);
		user.setStatus(1);
		user.setApprovalStatus(1);
		user.setApprovalDate(new Date());
		user.setChannelTypeID("62DC076A-0900-4877-B4A3-D8A9ED0BC10E");//默认兼职
		user.setChannelType(d.getDictName());
		user.setJob(3);
		user.setCertificatesType("62DC076A-0900-4877-B4A3-D8A9ED0BC10E");//--默认身份证
		user.setCertificatesName("身份证");
		user.setCertificatesNo(map.get("CardID").toString());
		bChanneluserMapper.insert(user);
		user.setCreator(user.getId());
		bChanneluserMapper.updateById(user);
	}

    @Override
    public IPage<Map<String, Object>> AgenList_SelectN(IPage page,int PageType,String ProjectID,String ChannelTypeID,String Name,String PassStatu
                                                        ,Date CreateStartTime,Date CreateEndTime,String ApprovalUserID) {
        StringBuilder where=new StringBuilder();
        if (PageType==0){
            where.append(" and cu.ChannelTypeID IN ('32C92DA0-DA13-4C21-A55E-A1D16955882C','E55FC76C-4696-40F9-8640-EF18572822CD') AND Job<>0");
            if (StringUtils.isNotBlank(ProjectID)){
                where.append(" and o.ID in (SELECT DISTINCT OrgID FROM [dbo].[B_PojectChannelOrgRel] WHERE ProjectID='" + ProjectID + "' AND IsDel=0 AND Status=1)");
            }
        }else {
            where.append(" and cu.ChannelTypeID IN ('725FA5F6-EC92-4DC6-8D47-A8E74B7829AD','EB4AD331-F4AD-46D6-889A-D45575ECEE66', '46830C26-0E01-4041-8054-3865CCDD26AD')");
            if (StringUtils.isNotBlank(ChannelTypeID)&&!StringUtils.equals("-1",ChannelTypeID)){
                where.append(" and cu.ChannelTypeID='" + ChannelTypeID + "'");
            }
        }
        if (StringUtils.isNotBlank(Name)){
            where.append(" and cu.Name like '%" + Name + "%'");
        }
        if (StringUtils.isNotBlank(PassStatu)&&!StringUtils.equals("-1",PassStatu)){
            where.append(" and cu.ApprovalStatus= '" + PassStatu + "'");
        }
        if (CreateStartTime!=null){
            where.append(" and cu.CreateTime > '" + CreateStartTime + "'");
        }
        if (CreateEndTime!=null){
            where.append(" and cu.CreateTime < '" + CreateEndTime + "'");
        }
        if (StringUtils.isNotBlank(ApprovalUserID)){
            where.append(" and cu.Approver = '" +ApprovalUserID + "'");
        }
        if (CreateEndTime!=null){
            where.append(" and cu.CreateTime < '" + CreateEndTime + "'");
        }
        //是否有机构和//项目ID没判断 貌似不需要了
        IPage<Map<String,Object>> list=baseMapper.AgenList_SelectN(page,where.toString());

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean AgenStatus_UpdateN(String id, int status) {
        try {
            BChanneluser channeluser=new BChanneluser();
            channeluser.setId(id);
            channeluser.setStatus(status);
            channeluser.setEditeTime(new Date());
            channeluser.setEditor(ThreadLocalUtils.getUserName());
            int i = baseMapper.updateById(channeluser);
            if (i==1){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> AgenChannelTypeList_SelectN() {
        return baseMapper.AgenChannelTypeList_SelectN();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result AgenInfo_UpdateN(BChanneluser channeluser) {
        try {
            //判断手机号是否重复
            QueryWrapper<BChanneluser> wrapper=new QueryWrapper<>();
            wrapper.eq("Mobile",channeluser.getMobile());
            wrapper.eq("IsDel",0);
            wrapper.ne("ID",channeluser.getId());
            int count = this.count(wrapper);
            if (count>0){
                return Result.errormsg(99,"存在重复手机号");
            }
            //判断用户名是否重复
            QueryWrapper<BChanneluser> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("UserName",channeluser.getUserName());
            queryWrapper.eq("IsDel",0);
            queryWrapper.ne("ID",channeluser.getId());
            int i = this.count(wrapper);
            if (i>0){
                return Result.errormsg(99,"存在重复用户名");
            }
            //新增
            SDictionary dictionary = sDictionaryMapper.selectById(channeluser.getCertificatesType());
            channeluser.setCertificatesName(dictionary.getDictName());
            BChannelorg channelorg = channelorgService.getById(channeluser.getChannelOrgID());
            if (channelorg != null)
                channeluser.setChannelOrgCode(channelorg.getOrgCode());
            SDictionary sDictionary = sDictionaryMapper.selectById(channeluser.getChannelTypeID());
            channeluser.setChannelType(sDictionary.getDictName());
            channeluser.setEditor(ThreadLocalUtils.getUserName());
            channeluser.setEditeTime(new Date());
            this.updateById(channeluser);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(99,"修改失败");
        }
        return Result.ok("成功");
    }

    @Override
    public Map<String, Object> ChannelUser_Detail_FindByIdN(String id) {
        return baseMapper.ChannelUser_Detail_FindByIdN(id);
    }


}
