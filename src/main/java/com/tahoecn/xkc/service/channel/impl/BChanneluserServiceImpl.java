package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.PhoneUtil;
import com.tahoecn.xkc.common.utils.RqCodeUtils;
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

import com.tahoecn.xkc.service.customer.IVABrokerMycustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.sys.IBVerificationcodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private IVABrokerMycustomersService mycustomersService;

    @Value("${tahoe.application.physicalPath}")
    private  String physicalPath;

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
        List<Map<String, Object>> map = baseMapper.BrokerMyCenter_Select(brokerID);
        Map<String, Object> resultMap=new HashMap<>();
        if (map.size()==2){
            BigDecimal a= (BigDecimal) map.get(0).get("DFCount");
            BigDecimal b= (BigDecimal) map.get(1).get("DFCount");
            resultMap=map.get(0);
            resultMap.put("DFCount",a.intValue()+b.intValue());
        }else {
            resultMap=map.get(0);
        }
        int count=mycustomersService.getWuXiao(brokerID);
        resultMap.put("WXcount",count);
        return resultMap;
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
	 * 忘记密码修改
	 */
	@Override
	public boolean ChannelUserForgetPassWord_Update(Map<String, Object> map){
		return bChanneluserMapper.ChannelUserForgetPassWord_Update(map);
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
            if (PhoneUtil.isNotValidChinesePhone(mobile)){
                result.setErrcode(1);
                result.setErrmsg("手机号格式不正确");
                return result;
            }

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
            int approvalStatus = 0;

            if (StringUtils.equals(mobile, channelorgs.get(0).getMobile())) {
                job = 1;
                approvalStatus = 0;
            }
            String orgId = channelorgs.get(0).getId();
            //保存人员信息   组建人员信息参数
            BChanneluser channeluser = new BChanneluser();
            channeluser.setId(UUID.randomUUID().toString().toUpperCase());
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
//        String channelOrgCode = (String) paramMap.get("ChannelOrgCode");
            //自由经纪
        String channelTypeID = "46830C26-0E01-4041-8054-3865CCDD26AD";
        String userID = (String) paramMap.get("UserID");
        int job = 3;
        int approvalStatus = 1;
            if (PhoneUtil.isNotValidChinesePhone(mobile)){
                result.setErrcode(1);
                result.setErrmsg("手机号格式不正确");
                return result;
            }

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
        channeluser.setId(UUID.randomUUID().toString().toUpperCase());
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
	public List<Map<String,Object>>  mChannelCheckClockTotal_Select(Map<String, Object> map) {
		return bChanneluserMapper.mChannelCheckClockTotal_Select(map);

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
        }
        if (StringUtils.isNotEmpty(orgID))
            bChanneluserMapper.ChannelOrgImport_Insert_B_ClueRule_AdviserGroup(RuleIDs==null?"":RuleIDs, orgID, userID);
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
                                                        ,Date CreateStartTime,Date CreateEndTime,String ApprovalUserID
                                                        ,String Mobile,String CertificatesNo,String ChannelOrgName, String Status,Date effectiveStartTime,Date effectiveEndTime
    ) {
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
        if (StringUtils.isNotBlank(Mobile)){
            where.append(" and cu.Mobile like '%" + Mobile + "%'");
        }
        if (StringUtils.isNotBlank(CertificatesNo)){
            where.append(" and cu.CertificatesNo like '%" + CertificatesNo + "%'");
        }
        if (StringUtils.isNotBlank(ChannelOrgName)){
            where.append(" and o.OrgName like '%" + ChannelOrgName + "%'");
        }
        if (StringUtils.isNotBlank(Status)){
            where.append(" and cu.Status like '%" + Status + "%'");
        }
        if (StringUtils.isNotBlank(PassStatu)&&!StringUtils.equals("-1",PassStatu)){
            where.append(" and cu.ApprovalStatus= '" + PassStatu + "'");
        }
        if (CreateStartTime!=null){
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startStr = sf.format(CreateStartTime);
            where.append(" and cu.CreateTime > '" + startStr + "'");
        }
        if (CreateEndTime!=null){
            CreateEndTime = new Date(CreateEndTime.getTime() + 60 * 60 * 24 * 1000);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endstr = sf.format(CreateEndTime);
            where.append(" and cu.CreateTime < '" + endstr + "'");
        }

        if (effectiveStartTime!=null){
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startStr = sf.format(effectiveStartTime);
            where.append(" and cu.ApprovalDate > '" + startStr + "'");
        }
        if (effectiveEndTime!=null){
            effectiveEndTime = new Date(effectiveEndTime.getTime() + 60 * 60 * 24 * 1000);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endstr = sf.format(effectiveEndTime);
            where.append(" and cu.ApprovalDate < '" + endstr + "'");
        }

        if (StringUtils.isNotBlank(ApprovalUserID)){
            where.append(" and cu.Approver = '" +ApprovalUserID + "'");
        }
//        if (CreateEndTime!=null){
//            where.append(" and cu.CreateTime < '" + CreateEndTime + "'");
//        }
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

    @Override
    public IPage<Map<String, Object>> mChannelStoreUserList_SelectN(Map<String, Object> paramMap) {
        int PageIndex= (int) paramMap.get("PageIndex");
        int PageSize= (int) paramMap.get("PageSize");
        String StoreID= (String) paramMap.get("StoreID");
        IPage page=new Page(PageIndex,PageSize);
        String UserName= "";
        if ( paramMap.get("UserName")!=null){
            UserName= (String) paramMap.get("UserName");
        }
        int ApprovalStatus= (int) paramMap.get("ApprovalStatus");
        StringBuilder sqlWhere=new StringBuilder();
        StringBuilder Parameter=new StringBuilder();
        if (StringUtils.isNotBlank(UserName)){
            Parameter.append(" and (name like '%" + UserName + "%' or mobile like '%" + UserName + "%')");
        }

            if ("1".equals(ApprovalStatus)){
                sqlWhere.append("and ApprovalStatus='1'");
            }
            else {
                sqlWhere.append("and (ApprovalStatus='0' OR ApprovalStatus='1')");
            }

        return baseMapper.mChannelStoreUserList_SelectN(page,StoreID,sqlWhere.toString(),Parameter.toString());
    }

    @Override
    public Map<String, Object> ChannelOrgRuleInfoDetail_Select(String projectId, String adviserGroupID) {
        return baseMapper.ChannelOrgRuleInfoDetail_Select(projectId,adviserGroupID);
    }

    @Override
    public Result mChannelStoreUserApproval_Update(Map<String, Object> paramMap) {
        try {
            String UserID= (String) paramMap.get("UserID");
            int ApprovalStatus= (int) paramMap.get("ApprovalStatus");
            String Approval= (String) paramMap.get("Approval");
            BChanneluser channeluser=new BChanneluser();
            channeluser.setId(Approval);
            channeluser.setApprovalStatus(ApprovalStatus);
            channeluser.setApprovalDate(new Date());
            channeluser.setApprover(UserID);
            baseMapper.updateById(channeluser);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.errormsg(1,"失败");
        }
        return Result.okm("成功");
    }

    @Override
    public Result getRqCode() {
	    int width= 500;
        String accessToken = RqCodeUtils.getToken();
        System.out.println("accessToken = " + accessToken);
        String twoCodeUrl = RqCodeUtils.getminiqrQr(accessToken,width,physicalPath);//todo:根据活动id生成路径
        if (twoCodeUrl==null){
            return Result.errormsg(1,"生成失败");
        }
        return Result.okm("成功");
    }

    @Override
    public Result mBrokerChannelUserDetail_UpdateN(Map<String, Object> paramMap) {
        //首先根据ID获取信息，判断是否是审核不通过状态，不通过状态可以修改机构编码
//        BChanneluser channeluser = this.getById((String) paramMap.get("UserID"));
        String Name= (String) paramMap.get("Name");
        String Gender= (String) paramMap.get("Gender");
        String UserID= (String) paramMap.get("UserID");
//        StringBuilder sqlUpdate=new StringBuilder();
//        if (channeluser!=null){
//            //是中介同行的，审核不通过的，才能修改机构编码和机构ID
//            if ("32C92DA0-DA13-4C21-A55E-A1D16955882C".equals(channeluser.getChannelTypeID())&&channeluser.getApprovalStatus()==2){
//                //检查输入的机构编码是否存在
//               String OrgCode= (String) paramMap.get("ChannelOrgCode");
//               QueryWrapper<BChannelorg> wrapper=new QueryWrapper<>();
//               wrapper.eq("OrgCode",OrgCode).eq("IsDel",0).eq("Status",1);
//                List<BChannelorg> list = channelorgService.list(wrapper);
//                if (list.size()==0){
//                    return  Result.errormsg(1,"机构编码输入有误，请重新输入");
//                }
//                sqlUpdate.append(",ChannelOrgCode='").append(paramMap.get("ChannelOrgCode")).append("',ChannelOrgID=(SELECT TOP 1 ID FROM dbo.B_ChannelOrg WHERE OrgCode='").append(paramMap.get("ChannelOrgCode")).append("'),ApprovalStatus=0");
//                baseMapper.ChannelUserDetail_UpateN(Name,Gender,UserID,sqlUpdate.toString());
//            }
//        }else {
//            return Result.errormsg(99,"修改失败");
//        }
        BChanneluser channeluser=new BChanneluser();
        channeluser.setId(UserID);
        channeluser.setName(Name);
        channeluser.setGender(Gender);
        boolean b = this.updateById(channeluser);
        if (b){
            return Result.okm("成功");
        }
        return Result.errormsg(1,"修改失败");
    }

    @Override
    public Result mBrokerChannelUserPassWord_Update(String userID, String password, String oldPassword) {
	    QueryWrapper<BChanneluser> wrapper=new QueryWrapper<>();
	    wrapper.eq("ID",userID).eq("Password",SecureUtil.md5(oldPassword).toUpperCase());
        BChanneluser one = this.getOne(wrapper);
        if (one==null){
            return Result.errormsg(99,"当前密码输入错误");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("UserID",userID);
        map.put("Password",SecureUtil.md5(password).toUpperCase());
        map.put("OldPassword",SecureUtil.md5(oldPassword).toUpperCase());
        boolean b = this.ChannelUserPassWord_Update(map);
        if (b){
            return Result.okm("密码修改成功");
        }
        return Result.errormsg(1,"密码修改失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result mChannelUserUpdate_Delete(Map<String, Object> paramMap) {
        try {
            String ID= (String) paramMap.get("ID");
            String UserID= (String) paramMap.get("UserID");
            BChanneluser channeluser=new BChanneluser();
            channeluser.setId(ID);
            channeluser.setEditeTime(new Date());
            channeluser.setIsDel(1);
            channeluser.setEditor(UserID);
            int count=baseMapper.CustomerCount(ID);
            //判断是否有客户
            if (count>0){
                Result.errormsg(1,"请将名下客户分配给其他经纪人!");
            }else {
                baseMapper.QuitUser_Update(ID,UserID);
            }
            this.updateById(channeluser);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(1,"删除失败");
        }
        return Result.okm("成功");
    }

    @Override
    public Result mCustomerTCList_SelectN(Map<String, Object> paramMap) {
       int PageIndex= (int) paramMap.get("PageIndex");
       int PageSize= (int) paramMap.get("PageSize");
        String UserID=(String) paramMap.get("UserID");
        String KeyWord=(String) paramMap.get("KeyWord");
        //目前前端无这几项
//        String Sort=(String) paramMap.get("Sort");
//        String OrderBy=(String) paramMap.get("OrderBy");
//        String Filter=(String) paramMap.get("Filter");
        IPage page=new Page(PageIndex,PageSize);
        IPage<Map<String,Object>> mapIPage=baseMapper.mCustomerTCList_SelectN(page,UserID,KeyWord);
        return Result.ok(mapIPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result mCustomerTCTransfer_Update(Map<String, Object> paramMap) {
        try {
            String ids= (String) paramMap.get("CustomerIDs");
            String TransferID= (String) paramMap.get("TransferID");
            String UserID= (String) paramMap.get("UserID");
            String[] split = ids.split(",");
            StringBuilder sb=new StringBuilder();
            for (String s : split) {
                sb.append("'").append(s).append("'").append(",");
            }
            String substring = sb.toString().substring(0, sb.toString().length() - 1);
            System.out.println("substring = " + substring);
            baseMapper.mCustomerTCTransfer_Update(substring,TransferID,UserID);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(99,"分配失败");
        }
        return Result.okm("成功");
    }


}
