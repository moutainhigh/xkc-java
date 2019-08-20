package com.tahoecn.xkc.service.project.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.service.channel.IBChanneluserService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Override
    public List<Map<String,Object>> findByOrgID(IPage page,String orgID) {
        return baseMapper.findByOrgID(page,orgID);
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
    public List<Map<String, Object>> ProjectList_Select(String name) {
        return baseMapper.ProjectList_Select(name);
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
