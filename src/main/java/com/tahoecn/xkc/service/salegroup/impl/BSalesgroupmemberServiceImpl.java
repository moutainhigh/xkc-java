package com.tahoecn.xkc.service.salegroup.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.landray.sso.client.oracle.StringUtil;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.clue.ClueListSelectMapper;
import com.tahoecn.xkc.mapper.job.BProjectjobrelMapper;
import com.tahoecn.xkc.mapper.job.SJobsMapper;
import com.tahoecn.xkc.mapper.job.SJobsuserrelMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.org.SOrganizationMapper;
import com.tahoecn.xkc.mapper.quit.BQuituserMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupmemberMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.mapper.sys.SAccountMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.clue.ClueListSelect;
import com.tahoecn.xkc.model.job.BProjectjobrel;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.job.SJobsuserrel;
import com.tahoecn.xkc.model.org.SOrganization;
import com.tahoecn.xkc.model.quit.BQuituser;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.model.vo.SalesGroupMemberExistProjectVo;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupmemberService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class BSalesgroupmemberServiceImpl extends ServiceImpl<BSalesgroupmemberMapper, BSalesgroupmember> implements IBSalesgroupmemberService {

    @Autowired
    private BSalesgroupmemberMapper bSaleGroupMemberMapper;

    @Autowired
    private SJobsMapper sjobsMapper;

    @Autowired
    private BProjectjobrelMapper bProjectjobrelMapper;

    @Autowired
    private SJobsuserrelMapper sJobsuserrelMapper;

    @Autowired
    private BChanneluserMapper bChanneluserMapper;

    @Autowired
    private ClueListSelectMapper clueListSelectMapper;

    @Autowired
    private BSalesgroupMapper bSalesgroupMapper;

    @Autowired
    private BQuituserMapper bQuituserMapper;

    @Autowired
    private BOpportunityMapper bOpportunityMapper;

    @Autowired
    private BSalesuserMapper bSalesuserMapper;

    @Autowired
    private SAccountMapper sAccountMapper;

    @Autowired
    private SOrganizationMapper sOrganizationMapper;

    @Autowired
    private SJobsMapper sJobsMapper;

    @Override
    public List<Map<String, Object>> SalesGroupMemberList_Select(Map<String, Object> map) {
        return bSaleGroupMemberMapper.SalesGroupMemberList_Select(map);
    }

    @Override
    public Map<String,Object> SalesGroupTeamList_Select(IPage page,Map<String, Object> map) {

        int tp = Integer.valueOf((String)map.get("Tp"));
        String RoleID = (String)map.get("RoleID");
        String ReceptionGroupID = (String)map.get("ReceptionGroupID");
        String kw = (String)map.get("Kw");
        String ProjectID = (String)map.get("ProjectID");
        StringBuffer sqlWhere = new StringBuffer();
        Map<String,Object> result = new HashMap<String,Object>();

        String whereOne = "";

        if (StringUtil.isNotNull(kw))
        {
            sqlWhere.append("AND (UserName LIKE  '%"+kw.trim()+"%' OR EmployeeName LIKE '%"+kw.trim()+"%')");
        }

        if (Integer.valueOf(tp) == 0)
        {
            if (StringUtil.isNotNull(RoleID))
            {
                //2018-11-05 限制渠道(自有渠道负责人、自有渠道团队)和置业顾问(除了自有渠道负责人、自有渠道团队其它都是)不能兼岗
                if (RoleID.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582"))//自渠人员
                {
                    whereOne = " and RoleId in ('48FC928F-6EB5-4735-BF2B-29B1F591A582'," +  //自有渠道团队人员
                            "'B0BF5636-94AD-4814-BB67-9C1873566F29'," + //自有渠道团队负责人
                            "'A2C076C4-09D1-4B42-862D-8688A93320F4'," + //项目营销负责人
                            "'3BC23001-BC31-4594-8463-C7DA89C0FB36'," + //案场销售团队负责人
                            "'8100EAF4-FBCE-408B-A51D-B7247A3ADB19'," + //销管组
                            "'97F5E202-2D3B-4935-BA91-4AFD5C5578FC'," + //策划组
                            "'938935B7-4131-4E61-A811-4323A7F193A2'," + //案场销售负责人
                            "'0269F35E-B32D-4D12-8496-4E6E4CE597B7')";  //案场销售团队人员
                }
                else if(RoleID.equals("9584A4B7-F105-44BA-928D-F2FBA2F3B4A4"))//自有渠道负责人
                {
                    whereOne = " and RoleId in('9584A4B7-F105-44BA-928D-F2FBA2F3B4A4'," + //自有渠道负责人
                            "'A2C076C4-09D1-4B42-862D-8688A93320F4'," + //项目营销负责人
                            "'3BC23001-BC31-4594-8463-C7DA89C0FB36'," + //案场销售团队负责人
                            "'8100EAF4-FBCE-408B-A51D-B7247A3ADB19'," + //销管组
                            "'97F5E202-2D3B-4935-BA91-4AFD5C5578FC'," + //策划组
                            "'938935B7-4131-4E61-A811-4323A7F193A2'," + //案场销售负责人
                            "'0269F35E-B32D-4D12-8496-4E6E4CE597B7')";  //案场销售团队人员
                }else if(RoleID.equals("938935B7-4131-4E61-A811-4323A7F193A2"))//案场销售负责人
                {
                    whereOne = " and RoleId in (" +
                            "'48FC928F-6EB5-4735-BF2B-29B1F591A582'," +  //自有渠道团队人员
                            "'B0BF5636-94AD-4814-BB67-9C1873566F29'," + //自有渠道团队负责人
                            //"'A2C076C4-09D1-4B42-862D-8688A93320F4'," + //项目营销负责人

                            "'9584A4B7-F105-44BA-928D-F2FBA2F3B4A4'," +  //自有渠道负责人
                            "'8100EAF4-FBCE-408B-A51D-B7247A3ADB19'," + //销管组
                            "'97F5E202-2D3B-4935-BA91-4AFD5C5578FC'," + //策划组

                            "'938935B7-4131-4E61-A811-4323A7F193A2')";  //案场销售负责人
                }
                else if(RoleID.equals("8100EAF4-FBCE-408B-A51D-B7247A3ADB19"))//销支组
                {
                    whereOne = " and RoleId in('"+RoleID+"','48FC928F-6EB5-4735-BF2B-29B1F591A582'," + //自有渠道团队人员
                            "'B0BF5636-94AD-4814-BB67-9C1873566F29'," + //自有渠道团队负责人
                            "'938935B7-4131-4E61-A811-4323A7F193A2'," + //案场销售负责人
                            "'97F5E202-2D3B-4935-BA91-4AFD5C5578FC'," + //策划组
                            "'9584A4B7-F105-44BA-928D-F2FBA2F3B4A4')";  //自有渠道负责人
                }
                else if(RoleID.equals("97F5E202-2D3B-4935-BA91-4AFD5C5578FC"))//策划组
                {
                    whereOne = " and RoleId in('"+RoleID+"','48FC928F-6EB5-4735-BF2B-29B1F591A582'," + //自有渠道团队人员
                            "'B0BF5636-94AD-4814-BB67-9C1873566F29'," + //自有渠道团队负责人
                            "'8100EAF4-FBCE-408B-A51D-B7247A3ADB19'"  + //销支组
                            "'938935B7-4131-4E61-A811-4323A7F193A2'," + //案场销售负责人
                            "'9584A4B7-F105-44BA-928D-F2FBA2F3B4A4')";  //自有渠道负责人
                }
                else
                {
                    whereOne = " and RoleId in('"+RoleID+"','48FC928F-6EB5-4735-BF2B-29B1F591A582'," + //自有渠道团队人员
                            "'B0BF5636-94AD-4814-BB67-9C1873566F29'," + //自有渠道团队负责人
                            "'9584A4B7-F105-44BA-928D-F2FBA2F3B4A4')";  //自有渠道负责人
                }
            }
            map.put("whereOne", whereOne);
            map.put("where",sqlWhere.toString());
            result.put("NoAddedList",bSaleGroupMemberMapper.SalesGroupTeamNoAddedList_Select(page,map));

        }else{
            if (StringUtil.isNotNull(ReceptionGroupID))
            {
                sqlWhere.append("and a.ReceptionGroupID='"+ReceptionGroupID.trim()+"' ");//团队ID
            }
            else
            {
                sqlWhere.append(" and a.RoleID= '"+RoleID+"'");
            }
            sqlWhere.append(" and a.ProjectID='"+ProjectID+"' ");

            map.put("where",sqlWhere.toString());
            result.put("AreadyAddedList",bSaleGroupMemberMapper.SalesGroupTeamAlreadyAddedList_Select(page,map));
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result SalesGroupMembers_Insert(Map<String, Object> map) {
        String Ids = (String)map.get("Ids");
        String UserID = (String)map.get("UserID");
        String ProjectID = (String)map.get("ProjectID");
        String PersonId = (String)map.get("PersonId");//负责人ID
        int Nature = 0;
        String RoleID = "";//角色ID
        String RoleName = "";
        String ReceptionGroupID = (String)map.get("ReceptionGroupID");//团队ID
        String RemoveIds = (String)map.get("RemoveIds");
        String msg = "";
        String custCount = (String)map.get("custCount");


        if (StringUtil.isNull(ReceptionGroupID))
        {
            RoleID = (String)map.get("RoleID");
            RoleName = (String)map.get("RoleName");
        }
        else
        {
            QueryWrapper<BSalesgroup> queryWrapper = new QueryWrapper<BSalesgroup>();
            queryWrapper.eq("ID", ReceptionGroupID);
            BSalesgroup bSalesgroup = (BSalesgroup)bSalesgroupMapper.selectOne(queryWrapper);
            if(bSalesgroup==null){
                return Result.errormsg(9527,"无效的团队ID");
            }

            Nature = bSalesgroup.getNature()==null?1:bSalesgroup.getNature();
            if (Integer.valueOf(Nature) == 3 || Integer.valueOf(Nature) == 4 || Integer.valueOf(Nature) == 5|| Integer.valueOf(Nature)==6|| Integer.valueOf(Nature)==7)
            {
                RoleID = "48FC928F-6EB5-4735-BF2B-29B1F591A582";
                RoleName = "自有渠道人员";
            }
            else
            {
                RoleID = "0269F35E-B32D-4D12-8496-4E6E4CE597B7";
                RoleName = "案场销售人员";
            }
        }

        if (StringUtil.isNotNull(RoleID) &&  RoleID.equals("8100EAF4-FBCE-408B-A51D-B7247A3ADB19")) //销支
        {
            String[] idsStr = Ids.split(",");
            for(int i=0;i<idsStr.length;i++){
                map.put("MemberID",idsStr[i]);
                map.put("RoleID",RoleID);
                map.put("ProjectID",ProjectID);
                SalesGroupMemberExistProjectVo salesGroupMemberExistProjectVo = bSaleGroupMemberMapper.SalesGroupMemberExistProject_Select(map);
                if(salesGroupMemberExistProjectVo!=null){
                    return Result.errormsg(9527,"操作失败，" + salesGroupMemberExistProjectVo.getUserName() + "已在其他项目“" + salesGroupMemberExistProjectVo.getProjectName() + "”的销管岗位");
                }
            }
        }
        //添加人员
        if (StringUtil.isNotNull(Ids))
        {
            String[] idStrs = Ids.split(",");
            for(int i=0;i<idStrs.length;i++)
            {
                String ID = UUID.randomUUID().toString();
                map.put("ID",ID);
                //saleGroupMemberMapper."UserMember_InsertNew", obj, out msg);//添加人员
                String MemberID = idStrs[i];
                QueryWrapper<BSalesgroupmember> sgmWrapper = new QueryWrapper<BSalesgroupmember>();
                sgmWrapper.eq("ID", MemberID);
                int sgmCount = bSaleGroupMemberMapper.selectCount(sgmWrapper);

                QueryWrapper<BSalesgroupmember> gmWrapper = new QueryWrapper<BSalesgroupmember>();
                gmWrapper.eq("isdel", 0);
                gmWrapper.eq("status", 1);
                gmWrapper.eq("ProjectID", ProjectID);
                gmWrapper.eq("MemberID", idStrs[i]);
                gmWrapper.ne("RoleID", "E417A997-9A91-4D22-8428-9C2C2C560656");
                gmWrapper.eq("RoleID", RoleID);
                List gmCount = bSaleGroupMemberMapper.selectList(gmWrapper);
                if(sgmCount==0 && gmCount.size()==0){
                    BSalesgroupmember bSalesgroupmember = new BSalesgroupmember();
                    bSalesgroupmember.setId(ID);
                    bSalesgroupmember.setProjectID(ProjectID);
                    bSalesgroupmember.setReceptionGroupID(ReceptionGroupID);
                    bSalesgroupmember.setMemberID(idStrs[i]);
                    bSalesgroupmember.setRoleID(RoleID);
                    bSalesgroupmember.setRoleName(RoleName);
                    bSalesgroupmember.setCreateTime(new Date());
                    bSalesgroupmember.setCreator(UserID);
                    bSalesgroupmember.setIsDel(0);
                    bSalesgroupmember.setStatus(1);
                    bSaleGroupMemberMapper.insert(bSalesgroupmember);
                }else{
                    BSalesgroupmember tempMember = (BSalesgroupmember)gmCount.get(0);
                    ID = tempMember.getId();
                }

                QueryWrapper<BSalesuser> salesuserWrapper = new QueryWrapper<BSalesuser>();
                salesuserWrapper.eq("id", idStrs[i]);
                int salesuserCount = bSalesuserMapper.selectCount(salesuserWrapper);

                if(salesuserCount==0){
                    QueryWrapper<SAccount> sAccountWrapper = new QueryWrapper<SAccount>();
                    sAccountWrapper.eq("id",idStrs[i]);
                    SAccount sAccount = sAccountMapper.selectOne(sAccountWrapper);
                    if(sAccount!=null){
                        BSalesuser bSalesuser = new BSalesuser();
                        bSalesuser.setId(sAccount.getId());
                        bSalesuser.setName(sAccount.getEmployeeName());
                        bSalesuser.setTelPhone(sAccount.getMobile()==null?"":sAccount.getMobile());
                        bSalesuser.setUserName(sAccount.getUserName());
                        bSalesuser.setHeadImg("/Skin/Green/images/headimg.png");
                        bSalesuser.setCreator(UserID);
                        bSalesuser.setCreateTime(new Date());
                        bSalesuser.setIsDel(0);
                        bSalesuser.setStatus(1);
                        bSalesuserMapper.insert(bSalesuser);
                    }
                }
                //渠道人员
                QueryWrapper<BChanneluser> chanelUserWrapper = new QueryWrapper<BChanneluser>();
                chanelUserWrapper.eq("id", idStrs[i]);
                int chanelUserCount = bChanneluserMapper.selectCount(chanelUserWrapper);
                if(chanelUserCount==0 && (RoleID.equals("B0BF5636-94AD-4814-BB67-9C1873566F29")|| RoleID.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582")))
                {
                    map.put("MemberID",idStrs[i]);
                    map.put("UserID",UserID);
                    bChanneluserMapper.insertBChannelUser(map);
                }else if(chanelUserCount>0 && (RoleID.equals("B0BF5636-94AD-4814-BB67-9C1873566F29")|| RoleID.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582"))){
                    String channelOrgId = bSaleGroupMemberMapper.getChannelOrgId(map);
                    BChanneluser updateChannelUser = new BChanneluser();
                    updateChannelUser.setChannelOrgID(channelOrgId);
                    UpdateWrapper<BChanneluser> userUpdateWrapper = new UpdateWrapper<>();
                    userUpdateWrapper.eq("id", idStrs[i]);
                    bChanneluserMapper.update(updateChannelUser,userUpdateWrapper);
                }
                //组织
                QueryWrapper<SOrganization> orgWrapper = new QueryWrapper<SOrganization>();
                orgWrapper.eq("id", ProjectID);
                int orgCount = sOrganizationMapper.selectCount(orgWrapper);

                if(orgCount==0){
                    sOrganizationMapper.userOrgInsert(UserID,ProjectID);
                }

                //岗位
                QueryWrapper<SJobs> jobWrapper = new QueryWrapper<SJobs>();
                jobWrapper.eq("CommonJobID", RoleID);
                jobWrapper.eq("JobOrgID", ProjectID);
                SJobs sJobs = sJobsMapper.selectOne(jobWrapper);
                if(sJobs==null){
                    sJobsMapper.userJobInsert(UserID,RoleID,ProjectID);
                }

                QueryWrapper<SJobs> job2Wrapper = new QueryWrapper<SJobs>();
                job2Wrapper.eq("CommonJobID", RoleID);
                job2Wrapper.eq("JobOrgID", ProjectID);
                sJobs = sJobsMapper.selectOne(job2Wrapper);

                //人员和岗位
                QueryWrapper<SJobsuserrel> jobsuserrelWrapper = new QueryWrapper<SJobsuserrel>();
                jobsuserrelWrapper.eq("AccountID", MemberID);
                jobsuserrelWrapper.eq("JobID", sJobs.getId());
                int jobuserrelCount = sJobsuserrelMapper.selectCount(jobsuserrelWrapper);

                SJobsuserrel insertjobuserrel = new SJobsuserrel();
                insertjobuserrel.setId(UUID.randomUUID().toString());
                insertjobuserrel.setAccountID(MemberID);
                insertjobuserrel.setJobID(sJobs.getId());

                sJobsuserrelMapper.insert(insertjobuserrel);
                //项目岗位
                QueryWrapper<BProjectjobrel> projectjobrelWrapper = new QueryWrapper<BProjectjobrel>();
                projectjobrelWrapper.eq("ProjectID", MemberID);
                projectjobrelWrapper.eq("JobID", sJobs.getId());
                int projectJobCount = bProjectjobrelMapper.selectCount(projectjobrelWrapper);

                if(projectJobCount==0){
                    BProjectjobrel bProjectjobrel = new BProjectjobrel();
                    bProjectjobrel.setId(UUID.randomUUID().toString());
                    bProjectjobrel.setJobID(sJobs.getId());
                    bProjectjobrel.setProjectID(ProjectID);
                    bProjectjobrel.setCreator(UserID);
                    bProjectjobrel.setEditTime(new Date());
                    bProjectjobrel.setIsDel(0);
                    bProjectjobrel.setStatus(1);
                    bProjectjobrelMapper.insert(bProjectjobrel);
                }

                //看看离职./调岗表里有木有数据，有的话看看有没有处理，没有处理完的话就改成删除状态
                //自有渠道 团队负责人 团队人员
                if(RoleID.equals("B0BF5636-94AD-4814-BB67-9C1873566F29") || RoleID.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582")){
                    QueryWrapper<BQuituser> quitWrapper = new QueryWrapper<BQuituser>();
                    quitWrapper.eq("IsDel", 0);
                    quitWrapper.eq("Status", 1);
                    quitWrapper.eq("UserType", 1);
                    quitWrapper.eq("UserID", UserID);
                    quitWrapper.eq("ProjectID", ProjectID);
                    int QuitCount = bQuituserMapper.selectCount(quitWrapper);
                    if(QuitCount>0){
                        QueryWrapper<ClueListSelect> customerWrapper = new QueryWrapper<ClueListSelect>();
                        customerWrapper.eq("ReportUserID", idStrs[i]);
                        customerWrapper.eq("IntentProjectID", ProjectID);
                        int CustomerCount = clueListSelectMapper.selectCount(customerWrapper);
                        if(CustomerCount>0){
                            BQuituser updateQuitUser = new BQuituser();
                            updateQuitUser.setIsDel(1);
                            updateQuitUser.setEditor(UserID);
                            updateQuitUser.setEditeTime(new Date());
                            UpdateWrapper<BQuituser> quitUserWrapper = new UpdateWrapper<>();
                            quitUserWrapper.eq("ProjectID", ProjectID);
                            quitUserWrapper.eq("UserID", UserID);
                            quitUserWrapper.eq("UserType", 1);
                            quitUserWrapper.eq("Status", 1);
                            quitUserWrapper.eq("IsDel", 0);
                            bQuituserMapper.update(updateQuitUser,quitUserWrapper);
                        }
                    }
                }else if(RoleID.equals("3BC23001-BC31-4594-8463-C7DA89C0FB36") || RoleID.equals("0269F35E-B32D-4D12-8496-4E6E4CE597B7")){
                    //案场销售(顾问) 团队负责人 团队人员
                    QueryWrapper<BQuituser> quitWrapper = new QueryWrapper<BQuituser>();
                    quitWrapper.eq("IsDel", 0);
                    quitWrapper.eq("Status", 1);
                    quitWrapper.eq("UserType", 2);
                    quitWrapper.eq("UserID", UserID);
                    quitWrapper.eq("ProjectID", ProjectID);
                    int QuitCount = bQuituserMapper.selectCount(quitWrapper);
                    if(QuitCount>0){
                        int CustomerCount = bOpportunityMapper.getCustomerCount(ProjectID,idStrs[i]);
                        if(CustomerCount>0){
                            BQuituser updateQuitUser = new BQuituser();
                            updateQuitUser.setIsDel(1);
                            updateQuitUser.setEditor(UserID);
                            updateQuitUser.setEditeTime(new Date());
                            UpdateWrapper<BQuituser> quitUserWrapper = new UpdateWrapper<>();
                            quitUserWrapper.eq("ProjectID", ProjectID);
                            quitUserWrapper.eq("UserID", UserID);
                            quitUserWrapper.eq("UserType", 2);
                            quitUserWrapper.eq("Status", 1);
                            quitUserWrapper.eq("IsDel", 0);
                            bQuituserMapper.update(updateQuitUser,quitUserWrapper);
                        }

                    }
                }
            }
        }

        if (StringUtil.isNotNull(RemoveIds))
        {
            String[] removeIdStrs = RemoveIds.split(",");
            for(int i=0;i<removeIdStrs.length;i++)
            {

                int clueCount = clueListSelectMapper.getCustomerCount(ProjectID,removeIdStrs[i]);
                int customerCount = bOpportunityMapper.getCustomerCount(ProjectID,removeIdStrs[i]);

                if(StringUtil.isNotNull(RoleID) && (RoleID.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582") || RoleID.equals("0269F35E-B32D-4D12-8496-4E6E4CE597B7"))
                        && StringUtil.isNull(custCount)){
                    int sumcount = clueCount + customerCount;
                    if(sumcount >0) {
                        return Result.errormsg(9527, "该成员下有客户数" + sumcount + "个,请及时前往离职/调岗列表处理!");
                    }else{
                        return Result.errormsg(9527, "该成员下有客户数" + sumcount + "个!");
                    }
                }

                if(StringUtil.isNull(custCount)){
                        return Result.errormsg(9527, "是否删除该成员!");
                }

                //删除人员
                BSalesgroupmember salesgroupmember = new BSalesgroupmember();
                salesgroupmember.setId(removeIdStrs[i]);
                BSalesgroupmember salesgroupmemberdel = bSaleGroupMemberMapper.selectById(salesgroupmember);
                salesgroupmemberdel.setIsDel(1);
                salesgroupmemberdel.setEditor(UserID);
                salesgroupmemberdel.setEditTime(new Date());
                bSaleGroupMemberMapper.updateById(salesgroupmemberdel);

                String roleId = salesgroupmemberdel.getRoleID();
                String memberId = removeIdStrs[i];
                String projectId = salesgroupmemberdel.getProjectID();
                String jobId = "";

                QueryWrapper<SJobs> queryWrapper = new QueryWrapper<SJobs>();
                queryWrapper.eq("CommonJobID",roleId);
                queryWrapper.eq("JobOrgID",projectId);
                SJobs sJobs = sjobsMapper.selectOne(queryWrapper);
                if(sJobs!=null){
                    jobId = sJobs.getId();
                }
                //删除岗位、人员岗位、项目岗位
                sjobsMapper.deleteById(jobId);

                QueryWrapper<SJobsuserrel> deljobsuserrelWrapper = new QueryWrapper<SJobsuserrel>();
                deljobsuserrelWrapper.eq("AccountID",memberId);
                deljobsuserrelWrapper.eq("JobID",jobId);
                sJobsuserrelMapper.delete(deljobsuserrelWrapper);

                QueryWrapper<BProjectjobrel> delprojectjobrelWrapper = new QueryWrapper<BProjectjobrel>();
                delprojectjobrelWrapper.eq("JobID",jobId);
                delprojectjobrelWrapper.eq("ProjectID",projectId);
                bProjectjobrelMapper.delete(delprojectjobrelWrapper);



                //更新渠道团队人员ChannelOrgID
                QueryWrapper<BChanneluser> selchanneluserWrapper = new QueryWrapper<BChanneluser>();
                selchanneluserWrapper.eq("ID",memberId);
                BChanneluser channeluser = (BChanneluser) bChanneluserMapper.selectOne(selchanneluserWrapper);
                if(channeluser!=null  && (roleId.equals("B0BF5636-94AD-4814-BB67-9C1873566F29") || roleId.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582"))){
                    map.put("memberId",memberId);
                    String channelOrgId = bSaleGroupMemberMapper.getChannelOrgId2(map);
                    if(channelOrgId==null || "".equals(channelOrgId)){
                        channelOrgId = "46830C26-0E01-4041-8054-3865CCDD26AD";
                    }
                    BChanneluser channeluser1 = new BChanneluser();
                    channeluser1.setId(memberId);
                    channeluser1.setChannelOrgID(channelOrgId);
                    bChanneluserMapper.updateById(channeluser1);
                }
                //判断该用户下在该项目下是否有客户，有客户添加到 离职/调岗表
                //自有渠道 团队负责人 团队人员
                if(roleId.equals("B0BF5636-94AD-4814-BB67-9C1873566F29") || roleId.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582")){
//                   int customerCount = clueListSelectMapper.getCustomerCount(projectId,memberId);
//                    if(customerCount>0){
                        BQuituser insertQuituser = new BQuituser();
                        insertQuituser.setProjectID(projectId);
                        insertQuituser.setUserType(1);
                        BSalesgroupmember groupmember = bSaleGroupMemberMapper.selectById(memberId);
                        if(groupmember!=null){
                            insertQuituser.setUserID(groupmember.getMemberID());
                            insertQuituser.setTeamID(groupmember.getReceptionGroupID());

                            BSalesgroup salesgroup = bSalesgroupMapper.selectById(groupmember.getReceptionGroupID());

                            insertQuituser.setTeamName(salesgroup.getName());
                            insertQuituser.setStatus(1);
                            insertQuituser.setIsDel(0);
                            insertQuituser.setCreator(UserID);
                            insertQuituser.setCreateTime(new Date());
                            bQuituserMapper.insert(insertQuituser);
                        }
//                    }
                }else if(roleId.equals("3BC23001-BC31-4594-8463-C7DA89C0FB36") || roleId.equals("0269F35E-B32D-4D12-8496-4E6E4CE597B7")){
                    //案场销售(顾问) 团队负责人 团队人员 判断该用户(顾问)下在该项目下是否有客户，有客户添加到 离职/调岗表
//                    int customerCount = bOpportunityMapper.getCustomerCount(projectId,memberId);
//                    if(customerCount>0){
                        BQuituser insertQuituser = new BQuituser();
                        insertQuituser.setProjectID(projectId);
                        insertQuituser.setUserType(2);
                        BSalesgroupmember groupmember = bSaleGroupMemberMapper.selectById(memberId);
                        if(groupmember!=null){
                            insertQuituser.setUserID(groupmember.getMemberID());
                            insertQuituser.setTeamID(groupmember.getReceptionGroupID());

                            BSalesgroup salesgroup = bSalesgroupMapper.selectById(groupmember.getReceptionGroupID());

                            insertQuituser.setTeamName(salesgroup.getName());
                            insertQuituser.setStatus(1);
                            insertQuituser.setIsDel(0);
                            insertQuituser.setCreator(UserID);
                            insertQuituser.setCreateTime(new Date());
                            bQuituserMapper.insert(insertQuituser);
                        }
//                    }
                }
            }
        }
        if (StringUtil.isNotNull(ReceptionGroupID) && StringUtil.isNull(RemoveIds)){
            String leaderRoleId = "";
            String leaderRoleName = "";
            if (Integer.valueOf(Nature) == 3 || Integer.valueOf(Nature) == 4 || Integer.valueOf(Nature) == 5|| Integer.valueOf(Nature)==6|| Integer.valueOf(Nature)==7)
            {
                leaderRoleId = "B0BF5636-94AD-4814-BB67-9C1873566F29";
                leaderRoleName = "自有渠道团队负责人";
            }
            else
            {
                leaderRoleId = "3BC23001-BC31-4594-8463-C7DA89C0FB36";
                leaderRoleName = "案场销售团队负责人";
            }

            BSalesgroupmember temp1 = new BSalesgroupmember();
            UpdateWrapper<BSalesgroupmember> updateWrapper1 = new UpdateWrapper<>();
            temp1.setRoleID(RoleID);
            temp1.setRoleName(RoleName);
            updateWrapper1.eq("RoleID",leaderRoleId);
            updateWrapper1.eq("ReceptionGroupID",ReceptionGroupID);
            this.update(temp1,updateWrapper1);

            BSalesgroupmember temp2 = new BSalesgroupmember();
            UpdateWrapper<BSalesgroupmember> updateWrapper2 = new UpdateWrapper<>();
            temp2.setRoleID(leaderRoleId);
            temp2.setRoleName(leaderRoleName);
            updateWrapper2.eq("ID",PersonId);
            this.update(temp2,updateWrapper2);

            BSalesgroupmember temp3 = new BSalesgroupmember();
            UpdateWrapper<BSalesgroupmember> updateWrapper3 = new UpdateWrapper<>();
            temp3.setRoleID(leaderRoleId);
            temp3.setRoleName(leaderRoleName);
            updateWrapper3.eq("MemberID",PersonId);
            updateWrapper3.eq("ReceptionGroupID",ReceptionGroupID);
            this.update(temp3,updateWrapper3);
        }


        return Result.ok("成功");
    }
}
