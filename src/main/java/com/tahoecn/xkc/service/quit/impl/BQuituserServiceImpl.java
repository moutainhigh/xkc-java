package com.tahoecn.xkc.service.quit.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.quit.BQuituserMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.clue.BCustomerpotentialfollowup;
import com.tahoecn.xkc.model.customer.BClue;
import com.tahoecn.xkc.model.customer.BCustomerfollowup;
import com.tahoecn.xkc.model.opportunity.BOpportunity;
import com.tahoecn.xkc.model.quit.BQuituser;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.vo.QuitUserUpdateVo;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.customer.IBClueService;
import com.tahoecn.xkc.service.customer.IBCustomerfollowupService;
import com.tahoecn.xkc.service.customer.IBCustomerpotentialfollowupService;
import com.tahoecn.xkc.service.opportunity.IBOpportunityService;
import com.tahoecn.xkc.service.quit.IBQuituserService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class BQuituserServiceImpl extends ServiceImpl<BQuituserMapper, BQuituser> implements IBQuituserService {

    @Autowired
    private BQuituserMapper bQuituserMapper;

    @Autowired
    private IBCustomerpotentialfollowupService iBCustomerpotentialfollowupService;

    @Autowired
    private ISAccountService iSAccountService;

    @Autowired
    private IBClueService iBClueService;

    @Autowired
    IBCustomerfollowupService iBCustomerfollowupService;

    @Autowired
    private IBOpportunityService iBOpportunityService;

    @Autowired
    private IBChanneluserService iBChanneluserService;

    @Override
    public IPage<Map<String, Object>> QuitUserOwnTeamList_Select(IPage page, Map<String, Object> map) {
        setSqlWhere(map);
        return bQuituserMapper.QuitUserOwnTeamList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserSalesTeamList_Select(IPage page, Map<String, Object> map) {

        StringBuffer sqlWhere = new StringBuffer();

        String Name = (String)map.get("Name");
        String Mobile = (String)map.get("Mobile");
        String UserName = (String)map.get("UserName");
        String TeamName = (String)map.get("TeamName");
        String ProjectID = (String)map.get("ProjectID");
        String IsDispose = (String)map.get("IsDispose");
        String OrgName = (String)map.get("OrgName");
        String IsExcel = (String)map.get("IsExcel");

        if(StringUtils.isNotEmpty(IsExcel) && "1".equals(IsExcel)) {
            //用户姓名
            if (StringUtils.isNotEmpty(Name)) {
                sqlWhere.append(" AND CU.Name LIKE '%" + Name + "%'");
            }
            //用户手机号
            if (StringUtils.isNotEmpty(Mobile)) {
                sqlWhere.append(" AND CU.Mobile LIKE '%" + Mobile + "%'");
            }
            //登录帐号
            if (StringUtils.isNotEmpty(UserName)) {
                sqlWhere.append(" AND CU.UserName LIKE '%" + UserName + "%'");
            }
            //机构名称
            if (StringUtils.isNotEmpty(OrgName)) {
                sqlWhere.append(" AND CO.OrgName LIKE '%" + UserName + "%'");
            }


            //是否处理
            if (StringUtils.isNotEmpty(IsDispose)) {
                if (IsDispose.equals("0")) {
                    sqlWhere.append(" AND QU.IsDispose=0");
                } else if (IsDispose.equals("1")) {
                    sqlWhere.append(" AND QU.IsDispose=1");
                }
            }
        }

        setSqlWhere(map);
        return bQuituserMapper.QuitUserSalesTeamList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserChannelList_Select(IPage page, Map<String, Object> map) {
        StringBuffer sqlWhere = new StringBuffer();
        String Name = (String)map.get("Name");
        String Mobile = (String)map.get("Mobile");
        String UserName = (String)map.get("UserName");
        String TeamName = (String)map.get("TeamName");
        String ProjectID = (String)map.get("ProjectID");
        String IsDispose = (String)map.get("IsDispose");
        String OrgName = (String)map.get("OrgName");
        String IsExcel = (String)map.get("IsExcel");
        //用户姓名
        if (StringUtils.isNotEmpty(Name)) {
            sqlWhere.append(" AND CU.Name LIKE '%" + Name + "%'");
        }
        //用户手机号
        if (StringUtils.isNotEmpty(Mobile)) {
            sqlWhere.append(" AND CU.Mobile LIKE '%" + Mobile + "%'");
        }
        //登录帐号
        if (StringUtils.isNotEmpty(UserName)) {
            sqlWhere.append(" AND CU.UserName LIKE '%" + UserName + "%'");
        }
        //机构名称
        if (StringUtils.isNotEmpty(OrgName)) {
            sqlWhere.append(" AND CO.OrgName LIKE '%" + UserName + "%'");
        }
        //是否处理
        if (StringUtils.isNotEmpty(IsDispose)) {
            if (IsDispose.equals("0")) {
                sqlWhere.append(" AND QU.IsDispose=0");
            } else if (IsDispose.equals("1")) {
                sqlWhere.append(" AND QU.IsDispose=1");
            }
        }
        map.put("sqlWhere",sqlWhere.toString());
        return bQuituserMapper.QuitUserChannelList_Select(page,map);
    }

    private void setSqlWhere(Map<String, Object> map) {
        StringBuffer sqlWhere = new StringBuffer();

        String Name = (String)map.get("Name");
        String Mobile = (String)map.get("Mobile");
        String UserName = (String)map.get("UserName");
        String TeamName = (String)map.get("TeamName");
        String ProjectID = (String)map.get("ProjectID");
        String IsDispose = (String)map.get("IsDispose");
        //用户姓名
        if (StringUtils.isNotEmpty(Name))
        {
            sqlWhere.append(" AND A.EmployeeName LIKE '%"+Name+"%'");
        }
        //用户手机号
        if (StringUtils.isNotEmpty(Mobile))
        {
            sqlWhere.append(" AND A.Mobile LIKE '%"+Mobile+"%'");
        }
        //登录帐号
        if (StringUtils.isNotEmpty(UserName))
        {
            sqlWhere.append(" AND A.UserName LIKE '%"+UserName+"%'");
        }
        //机构名称
        if (StringUtils.isNotEmpty(TeamName))
        {
            sqlWhere.append(" AND SG.Name LIKE '%"+TeamName+"%'");
        }
        //项目ID
        if (StringUtils.isNotEmpty(ProjectID))
        {
            sqlWhere.append(" AND SG.ProjectID LIKE '%"+ProjectID+"%' ");
        }
        //是否处理
        if (StringUtils.isNotEmpty(IsDispose))
        {
            if (IsDispose.equals("0"))
            {
                sqlWhere.append(" AND QU.IsDispose=0");
            }
            else if (IsDispose.equals("1"))
            {
                sqlWhere.append(" AND QU.IsDispose=1");
            }
        }
        map.put("sqlWhere",sqlWhere.toString());
    }

    @Override
    public IPage<Map<String, Object>> QuitUserOwnTeamUserList_Select(IPage page, Map<String, Object> map) {
        return bQuituserMapper.QuitUserOwnTeamUserList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserOwnTeamCustomerList_Select(IPage page, Map<String, Object> map) {
        return bQuituserMapper.QuitUserOwnTeamCustomerList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserSalesTeamUserList_Select(IPage page, Map<String, Object> map) {
        return bQuituserMapper.QuitUserSalesTeamUserList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserSalesTeamCustomerList_Select(IPage page, Map<String, Object> map) {
        return bQuituserMapper.QuitUserSalesTeamCustomerList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserChannelUserList_Select(IPage page, Map<String, Object> map) {
        return bQuituserMapper.QuitUserChannelUserList_Select(page,map);
    }

    @Override
    public IPage<Map<String, Object>> QuitUserChannelCustomerList_Select(IPage page, Map<String, Object> map) {
        return bQuituserMapper.QuitUserChannelCustomerList_Select(page,map);
    }

    @Override
    public Result QuitUserOwnTeamCustomerDetail_Update(Map<String, Object> map) {
        String ID = (String)map.get("ID");
        String SelectUserID = (String)map.get("SelectUserID");
        String ClueIDs = (String)map.get("ClueIDs");
        String UserID = (String)map.get("UserID");
        String ProjectID = (String)map.get("ProjectID");

        QuitUserUpdateVo vo1 = bQuituserMapper.getQuitUserOwnTeamMember1(UserID,ProjectID);

        String[] ClueIDsArr = ClueIDs.split(",");

        for(int i=0;i<ClueIDsArr.length;i++) {
            QuitUserUpdateVo vo3 = bQuituserMapper.getQuitUserOwnTeamMember3(ClueIDsArr[i]);

            BCustomerpotentialfollowup followup = new BCustomerpotentialfollowup();

            followup.setCustomerPotentialID(vo3.getCustomerPotentialID());
            followup.setCustomerPotentialMobile(vo3.getCustomerPotentialMobile());
            followup.setCustomerPotentialName(vo3.getCustomerPotentialName());
            followup.setClueID(ClueIDsArr[i]);
            followup.setFollwUpUserID(UserID);
            followup.setFollwUpUserName(vo1.getUserName());
            followup.setFollwUpUserMobile(vo1.getUserMobile());
            followup.setFollwUpType("C1E26493-7D4E-4BFC-9238-725180DFC7AE");
            followup.setFollwUpWay("C1E26493-7D4E-4BFC-9238-725180DFC7AE");
            followup.setFollowUpContent("渠道经理重分配渠道专员");
            followup.setNextFollowUpDate(new Date());
            followup.setIntentionLevel("");
            followup.setOrgID("");
            followup.setCreator(UserID);
            followup.setCreateTime(new Date());
            followup.setEditor("");
            followup.setEditeTime(null);
            followup.setIsDel(0);
            followup.setStatus(1);
            followup.setFollwUpUserRole(vo1.getRoleID());
            followup.setCustomerRank("");
            followup.setProjectID(ProjectID);
            iBCustomerpotentialfollowupService.save(followup);
        }

        QueryWrapper<SAccount> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("ID",SelectUserID);
        SAccount account = iSAccountService.getOne(accountQueryWrapper);


        BClue clue = new BClue();
        UpdateWrapper<BClue> clueUpdateWrapper = new UpdateWrapper<>();
        clueUpdateWrapper.eq("IntentProjectID",ProjectID);
        clueUpdateWrapper.in("ID",this.spilt(ClueIDs));
        clue.setReportUserID(SelectUserID);
        clue.setReportUserName(account.getEmployeeName());
        clue.setReportUserMobile(account.getMobile());
        clue.setEditor(UserID);
        clue.setEditeTime(new Date());
        iBClueService.update(clue,clueUpdateWrapper);

        //看看这次处理完之后 这个人下边还有没有客户，没有客户就设置为已处理
        int customerCount = bQuituserMapper.getCustomerCount(ID);

        if(customerCount>0){
            UpdateWrapper<BQuituser> quituserUpdateWrapper = new UpdateWrapper<>();
            BQuituser quitUser = new BQuituser();
            quituserUpdateWrapper.eq("ID",ID);
            quitUser.setEditor(UserID);
            quitUser.setEditeTime(new Date());
            bQuituserMapper.update(quitUser,quituserUpdateWrapper);
        }else{
            UpdateWrapper<BQuituser> quituserUpdateWrapper = new UpdateWrapper<>();
            BQuituser quitUser = new BQuituser();
            quituserUpdateWrapper.eq("ID",ID);
            quitUser.setEditor(UserID);
            quitUser.setEditeTime(new Date());
            quitUser.setIsDispose(1);
            bQuituserMapper.update(quitUser,quituserUpdateWrapper);
        }
        return Result.ok("成功");
    }

    @Override
    public Result QuitUserSalesTeamCustomerDetail_Update(Map<String, Object> map) {
        String ID = (String)map.get("ID");
        String SelectUserID = (String)map.get("SelectUserID");
        String ClueIDs = (String)map.get("ClueIDs");
        String UserID = (String)map.get("UserID");
        String ProjectID = (String)map.get("ProjectID");
        QuitUserUpdateVo vo1 = bQuituserMapper.getQuitSalesTeamMember1(UserID,ProjectID);
        QuitUserUpdateVo vo2 = bQuituserMapper.getQuitSalesTeamMember2(SelectUserID,ProjectID);
        String[] oppoIdArr = ClueIDs.split(",");

        for(int i=0;i<oppoIdArr.length;i++) {
            QuitUserUpdateVo vo3 = bQuituserMapper.getQuitSalesTeamMember3(oppoIdArr[i]);
            QuitUserUpdateVo vo4 = bQuituserMapper.getQuitSalesTeamMember4(vo3.getSaleUser());
            BCustomerfollowup followup = new BCustomerfollowup();
            followup.setCustomerID(vo3.getCustomerID());
            followup.setCustomerMobile(vo3.getCustomerMobile());
            followup.setCustomerName(vo3.getCustomerName());
            followup.setOpportunityID(oppoIdArr[i]);
            followup.setFollwUpUserID(UserID);
            followup.setFollwUpUserName(vo1.getUserName());
            followup.setFollwUpUserMobile(vo1.getUserMobile());
            followup.setFollwUpType("69331990-DBF4-0A2F-80CD-7BC424AA8912");
            followup.setFollwUpWay("69331990-DBF4-0A2F-80CD-7BC424AA8912");
            followup.setFollowUpContent("从原顾问( '"+vo4.getUserName2Old()+"')分配给新顾问('"+vo2.getUserName2()+"')  ");
            followup.setNextFollowUpDate(new Date());
            followup.setIntentionLevel("");
            followup.setOrgID("");
            followup.setCreator(UserID);
            followup.setCreateTime(new Date());
            followup.setEditor("");
            followup.setEditeTime(null);
            followup.setIsDel(0);
            followup.setStatus(1);
            followup.setFollwUpUserRole(vo1.getRoleID());
            followup.setCustomerRank("");
            followup.setProjectID(ProjectID);
            iBCustomerfollowupService.save(followup);

        }

        QueryWrapper<SAccount> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("ID",SelectUserID);
        SAccount account = iSAccountService.getOne(accountQueryWrapper);

        BOpportunity opportunity = new BOpportunity();
        UpdateWrapper<BOpportunity> oppoUpdateWrapper = new UpdateWrapper<>();
        oppoUpdateWrapper.eq("ProjectID",ProjectID);
        oppoUpdateWrapper.in("ID",this.spilt(ClueIDs));

        opportunity.setSaleUserID(SelectUserID);
        opportunity.setSaleUserName(account.getEmployeeName());
        opportunity.setEditor(UserID);
        opportunity.setEditeTime(new Date());
        iBOpportunityService.update(opportunity,oppoUpdateWrapper);

        //看看这次处理完之后 这个人下边还有没有客户，没有客户就设置为已处理
        int customerCount = bQuituserMapper.getOppoCustomerCount(ID);

        if(customerCount>0){
            UpdateWrapper<BQuituser> quituserUpdateWrapper = new UpdateWrapper<>();
            BQuituser quitUser = new BQuituser();
            quituserUpdateWrapper.eq("ID",ID);
            quitUser.setEditor(UserID);
            quitUser.setEditeTime(new Date());
            bQuituserMapper.update(quitUser,quituserUpdateWrapper);
        }else{
            UpdateWrapper<BQuituser> quituserUpdateWrapper = new UpdateWrapper<>();
            BQuituser quitUser = new BQuituser();
            quituserUpdateWrapper.eq("ID",ID);
            quitUser.setEditor(UserID);
            quitUser.setEditeTime(new Date());
            quitUser.setIsDispose(1);
            bQuituserMapper.update(quitUser,quituserUpdateWrapper);
        }
        return Result.ok("成功");
    }

    @Override
    public Result QuitUserChannelCustomerDetail_Update(Map<String, Object> map) {
        String ID = (String)map.get("ID");
        String SelectUserID = (String)map.get("SelectUserID");
        String ClueIDs = (String)map.get("ClueIDs");
        String UserID = (String)map.get("UserID");

        QueryWrapper<BChanneluser> channeluserQueryWrapper = new QueryWrapper<>();
        channeluserQueryWrapper.eq("ID",SelectUserID);
        BChanneluser channeluser = iBChanneluserService.getOne(channeluserQueryWrapper);
        BClue clue = new BClue();
        UpdateWrapper<BClue> clueUpdateWrapper = new UpdateWrapper<>();
        clueUpdateWrapper.in("ID",this.spilt(ClueIDs));
        clue.setReportUserID(SelectUserID);
        clue.setReportUserName(channeluser.getName());
        clue.setReportUserMobile(channeluser.getMobile());
        clue.setEditor(UserID);
        clue.setEditeTime(new Date());
        iBClueService.update(clue,clueUpdateWrapper);

        //看看这次处理完之后 这个人下边还有没有客户，没有客户就设置为已处理
        int customerCount = bQuituserMapper.getChannelCustomerCount(ID);

        if(customerCount>0){
            UpdateWrapper<BQuituser> quituserUpdateWrapper = new UpdateWrapper<>();
            BQuituser quitUser = new BQuituser();
            quituserUpdateWrapper.eq("ID",ID);
            quitUser.setEditor(UserID);
            quitUser.setEditeTime(new Date());
            bQuituserMapper.update(quitUser,quituserUpdateWrapper);
        }else{
            UpdateWrapper<BQuituser> quituserUpdateWrapper = new UpdateWrapper<>();
            BQuituser quitUser = new BQuituser();
            quituserUpdateWrapper.eq("ID",ID);
            quitUser.setEditor(UserID);
            quitUser.setEditeTime(new Date());
            quitUser.setIsDispose(1);
            bQuituserMapper.update(quitUser,quituserUpdateWrapper);
        }
        return Result.ok("成功");
    }

    public String spilt(String str) {
        StringBuffer sb = new StringBuffer();
        String[] temp = str.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (!"".equals(temp[i]) && temp[i] != null)
                sb.append("'" + temp[i] + "',");
        }
        String result = sb.toString();
        String tp = result.substring(result.length() - 1, result.length());
        if (",".equals(tp))
            return result.substring(0, result.length() - 1);
        else
            return result;
    }
}
