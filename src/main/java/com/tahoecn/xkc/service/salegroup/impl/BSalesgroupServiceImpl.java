package com.tahoecn.xkc.service.salegroup.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.clue.ClueListSelectMapper;
import com.tahoecn.xkc.mapper.opportunity.BOpportunityMapper;
import com.tahoecn.xkc.mapper.quit.BQuituserMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesgroupmemberMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.job.BProjectjobrel;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.job.SJobsuserrel;
import com.tahoecn.xkc.model.quit.BQuituser;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import com.tahoecn.xkc.service.job.IBProjectjobrelService;
import com.tahoecn.xkc.service.job.ISJobsService;
import com.tahoecn.xkc.service.job.ISJobsuserrelService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
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
 * 树形结构：项目--团队--组 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class BSalesgroupServiceImpl extends ServiceImpl<BSalesgroupMapper, BSalesgroup> implements IBSalesgroupService {

    @Autowired
    private IBSalesgroupmemberService iBSalesgroupmemberService;

    @Autowired
    private ISJobsService iSJobsService;

    @Autowired
    private ISJobsuserrelService iSJobsuserrelService;

    @Autowired
    private IBProjectjobrelService iBProjectjobrelService;

    @Autowired
    private BChanneluserMapper bChanneluserMapper;

    @Autowired
    private BSalesgroupmemberMapper bSaleGroupMemberMapper;

    @Autowired
    private BSalesgroupMapper bSalesgroupMapper;

    @Autowired
    private BOpportunityMapper bOpportunityMapper;

    @Autowired
    private ClueListSelectMapper clueListSelectMapper;

    @Autowired
    private BQuituserMapper bQuituserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void UserTeam_DeleteNew(Map<String, Object> map) {
        UpdateWrapper<BSalesgroup> updateSalesGroupWrapper = new UpdateWrapper<>();
        String UserID = (String)map.get("UserID");
        String ReceptionGroupID = (String)map.get("ReceptionGroupID");

        BSalesgroup bsalesgroup = new BSalesgroup();
        bsalesgroup.setIsDel(1);
        bsalesgroup.setEditor(UserID);
        bsalesgroup.setEditTime(new Date());
        updateSalesGroupWrapper.eq("ID",ReceptionGroupID);
        update(bsalesgroup,updateSalesGroupWrapper);
        //--删除岗位、人员岗位、项目岗位

        QueryWrapper<BSalesgroupmember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ReceptionGroupID",ReceptionGroupID);
        queryWrapper.eq("IsDel",0);
        List memberList = iBSalesgroupmemberService.list(queryWrapper);

        for(int i = 0;i<memberList.size();i++){
            //@projectId=ProjectID,@roleId=RoleID,@memberid=MemberID
            BSalesgroupmember mi = (BSalesgroupmember)memberList.get(i);
            String projectId = mi.getProjectID();
            String roleId = mi.getRoleID();
            String memberId = mi.getMemberID();

            QueryWrapper<SJobs> sjobsQueryWrapper = new QueryWrapper<>();
            sjobsQueryWrapper.eq("CommonJobID",roleId);
            sjobsQueryWrapper.eq("JobOrgID",projectId);
            SJobs sJobs = iSJobsService.getOne(sjobsQueryWrapper);
            if(sJobs!=null) {
                String jobId = sJobs.getId();
                iSJobsService.remove(sjobsQueryWrapper);
                QueryWrapper<SJobsuserrel> sjobsusrrelQueryWrapper = new QueryWrapper<>();
                sjobsusrrelQueryWrapper.eq("AccountID",memberId);
                sjobsusrrelQueryWrapper.eq("JobID",jobId);
                //SJobsuserrel sJobsuserrel = iSJobsuserrelService.getOne(sjobsusrrelQueryWrapper);
                iSJobsuserrelService.remove(sjobsusrrelQueryWrapper);

                QueryWrapper<BProjectjobrel> bprojectjobrelQueryWrapper = new QueryWrapper<>();
                bprojectjobrelQueryWrapper.eq("JobID",jobId);
                bprojectjobrelQueryWrapper.eq("ProjectID",projectId);
                //BProjectjobrel bProjectjobrel = iBProjectjobrelService.getOne(bprojectjobrelQueryWrapper);
                iBProjectjobrelService.remove(bprojectjobrelQueryWrapper);
            }
            //更新渠道团队人员ChannelOrgID
            QueryWrapper<BChanneluser> selchanneluserWrapper = new QueryWrapper<BChanneluser>();
            selchanneluserWrapper.eq("ID",memberId);
            BChanneluser channeluser = (BChanneluser) bChanneluserMapper.selectOne(selchanneluserWrapper);
            if(channeluser!=null  && (roleId.equals("B0BF5636-94AD-4814-BB67-9C1873566F29") || roleId.equals("WSD12580-003A-6505-B211-C2C2C2C2C2C2") || roleId.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582"))){
                map.put("memberId",memberId);
                String channelOrgId = bSaleGroupMemberMapper.getChannelOrgId(map);
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
            if(roleId.equals("B0BF5636-94AD-4814-BB67-9C1873566F29") || roleId.equals("WSD12580-003A-6505-B211-C2C2C2C2C2C2") || roleId.equals("48FC928F-6EB5-4735-BF2B-29B1F591A582")){
                int customerCount = clueListSelectMapper.getCustomerCount(projectId,memberId);
                if(customerCount>0){
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
                }
            }else if(roleId.equals("3BC23001-BC31-4594-8463-C7DA89C0FB36") || roleId.equals("0269F35E-B32D-4D12-8496-4E6E4CE597B7")){
                //案场销售(顾问) 团队负责人 团队人员 判断该用户(顾问)下在该项目下是否有客户，有客户添加到 离职/调岗表
                int customerCount = bOpportunityMapper.getCustomerCount(projectId,memberId);
                if(customerCount>0){
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
                }
            }

            UpdateWrapper<BSalesgroupmember> updateSalesGroupMemberWrapper = new UpdateWrapper<>();
            BSalesgroupmember bSalesgroupmember = new BSalesgroupmember();
            bSalesgroupmember.setIsDel(1);
            bSalesgroupmember.setEditor(UserID);
            bSalesgroupmember.setEditTime(new Date());
            updateSalesGroupMemberWrapper.eq("ReceptionGroupID",ReceptionGroupID);
            iBSalesgroupmemberService.update(bSalesgroupmember,updateSalesGroupMemberWrapper);
        }
    }

    @Override
    public void UserTeamStatus_Update(Map<String, Object> map) {
        UpdateWrapper<BSalesgroup> updateSalesGroupWrapper = new UpdateWrapper<>();
        String UserID = (String)map.get("UserID");
        String ReceptionGroupID = (String)map.get("ReceptionGroupID");
        Integer status = (Integer)map.get("status");

        BSalesgroup bsalesgroup = new BSalesgroup();
        bsalesgroup.setStatus(status);
        bsalesgroup.setEditor(UserID);
        bsalesgroup.setEditTime(new Date());
        updateSalesGroupWrapper.eq("ID",ReceptionGroupID);
        update(bsalesgroup,updateSalesGroupWrapper);

        QueryWrapper<BSalesgroupmember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ReceptionGroupID",ReceptionGroupID);
        queryWrapper.eq("IsDel",0);
        List memberList = iBSalesgroupmemberService.list(queryWrapper);
        //岗位、项目岗位禁用启用
        for(int i = 0;i<memberList.size();i++){
            BSalesgroupmember mi = (BSalesgroupmember)memberList.get(i);
            String projectId = mi.getProjectID();
            String roleId = mi.getRoleID();
            String memberId = mi.getMemberID();

            QueryWrapper<SJobs> sjobsQueryWrapper = new QueryWrapper<>();
            sjobsQueryWrapper.eq("CommonJobID",roleId);
            sjobsQueryWrapper.eq("JobOrgID",projectId);
            SJobs sJobs = iSJobsService.getOne(sjobsQueryWrapper);
            String jobId = sJobs.getId();
            sJobs.setIsDel(status);
            iSJobsService.updateById(sJobs);

            UpdateWrapper<BProjectjobrel> projectjobUpdateWrapper = new UpdateWrapper<>();
            BProjectjobrel bProjectjobrel = new BProjectjobrel();
            bProjectjobrel.setIsDel(status);
            projectjobUpdateWrapper.eq("JobID",jobId);
            projectjobUpdateWrapper.eq("ProjectID",projectId);
            iBProjectjobrelService.update(bProjectjobrel,projectjobUpdateWrapper);

            Map<String,Object> channelOrgIdMap = new HashMap<String,Object>();
            channelOrgIdMap.put("memberId",memberId);
            String channelOrgId = bSaleGroupMemberMapper.getChannelOrgId(channelOrgIdMap);
            if(channelOrgId==null || "".equals(channelOrgId)){
                channelOrgId = "46830C26-0E01-4041-8054-3865CCDD26AD";
            }

            UpdateWrapper<BChanneluser> updateChannelUserWrapper = new UpdateWrapper<>();
            BChanneluser channeluser1 = new BChanneluser();
            channeluser1.setChannelOrgID(channelOrgId);
            updateChannelUserWrapper.eq("id",memberId);
            bChanneluserMapper.update(channeluser1,updateChannelUserWrapper);
        }
    }
    /**
     * 查询渠道身份
     */
	@Override
	public Map<String, Object> mShareChannelTypeID_Select(Map<String, Object> channelTypeObj) {
		return baseMapper.mShareChannelTypeID_Select(channelTypeObj);
	}
}
