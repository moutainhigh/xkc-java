package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.model.salegroup.BSalesgroupmember;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupmemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class BProjectServiceImpl extends ServiceImpl<BProjectMapper, BProject> implements IBProjectService {

    @Autowired
    private IBChanneluserService channeluserService;
    @Autowired
    private IBSalesgroupmemberService salesgroupmemberService;
    @Override
    public Map<String,Object> findByOrgID(String orgID,String Name,int PageIndex,int PageSize) {
        List<Map<String, Object>> byOrgID = baseMapper.findByOrgID(orgID, Name, PageIndex, PageSize);
        int byOrgID_count = baseMapper.findByOrgID_count(orgID, Name, PageIndex, PageSize);
        Map<String,Object> map=new HashMap<>();
        map.put("List",byOrgID);
        map.put("total",byOrgID_count);
        return map;
    }

    @Override
    public Map<String, Object> findAllProject(String name, int pageIndex, int pageSize) {
        List<Map<String, Object>> byOrgID = baseMapper.findAllProject(name, pageIndex, pageSize);
        int byOrgID_count = baseMapper.findAllProject_count(name, pageIndex, pageSize);
        Map<String,Object> map=new HashMap<>();
        map.put("List",byOrgID);
        map.put("total",byOrgID_count);
        return map;
    }

    @Override
    public List<Map<String,Object>> ProjectInfoList_SelectN(IPage page,String name, String cityID) {
        return baseMapper.ProjectInfoList_SelectN(page,name,cityID);
    }

    @Override
    public int isReport(String projectId, String userID, String mobile) {
        //电话和用户id相同 表示自己报备自己  返回1为不可 0表示可以
        BProject project = baseMapper.selectById(projectId);
        BChanneluser channeluser = channeluserService.getById(userID);
        if (channeluser!=null&&project!=null){
            if (StringUtils.equals(mobile,channeluser.getMobile())&&project.getIsReportOwn()==0){
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int ProjectIsNoAllot_Select(String projectID) {
    	String IsNoAllotRole = baseMapper.ProjectIsNoAllot_Select(projectID);
    	if(IsNoAllotRole == null || "".equals(IsNoAllotRole)){
    		return 0;
    	}else{
    		return Integer.parseInt(IsNoAllotRole);
    	}
    }

    @Override
    public List<String> getProjectIDs() {
        return baseMapper.getProjectIDs();
    }

    @Override
    public List<Map<String, Object>> ProjectList_Select(String name,String userID) {
        List<Map<String, Object>> list = baseMapper.ProjectList_Select(name);
        if (userID!=null){
        QueryWrapper<BSalesgroupmember> wrapper=new QueryWrapper<>();
        wrapper.eq("IsDel",0).eq("Status",1).eq("MemberID",userID)
                .in("RoleID","48FC928F-6EB5-4735-BF2B-29B1F591A582","0269F35E-B32D-4D12-8496-4E6E4CE597B7");
        List<BSalesgroupmember> memberList= salesgroupmemberService.list(wrapper);
        List<Map<String, Object>> tpyeList=new ArrayList<>();
        //获取已存在项目
        if (memberList.size()>0) {
            for (BSalesgroupmember bSalesgroupmember : memberList) {
                for (Map<String, Object> map : list) {
                    if (StringUtils.equalsIgnoreCase((String) map.get("ProjectID"), bSalesgroupmember.getProjectID())) {
                        tpyeList.add(map);
                    }
                }
            }
            list.removeAll(tpyeList);
        }
            return list;
        }
        //没有已存在项目  直接返回
        return list;
    }

    @Override
    public List<Map<String, Object>> addName(List<Map<String, Object>> list) {
        List<Map<String, Object>> result=new ArrayList<>();
        for (Map<String, Object> map : list) {
            String projectID = (String) map.get("BUGUID");
            Map<String, Object> addName=baseMapper.addName(projectID);
            addName.put("ProjectID",map.get("ProjectID"));
            addName.put("ProjectName",map.get("ProjectName"));
            result.add(addName);
        }
        return result;
    }



}
