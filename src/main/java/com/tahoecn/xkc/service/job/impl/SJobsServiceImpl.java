package com.tahoecn.xkc.service.job.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.mapper.job.SJobsMapper;
import com.tahoecn.xkc.model.dto.SMenusXkcDto;
import com.tahoecn.xkc.model.job.BProjectjobrel;
import com.tahoecn.xkc.model.job.SJobs;
import com.tahoecn.xkc.model.job.SJobsmenurel;
import com.tahoecn.xkc.model.job.SJobsuserrel;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.sys.*;
import com.tahoecn.xkc.service.job.IBProjectjobrelService;
import com.tahoecn.xkc.service.job.ISJobsService;
import com.tahoecn.xkc.service.job.ISJobsmenurelService;
import com.tahoecn.xkc.service.job.ISJobsuserrelService;
import com.tahoecn.xkc.service.org.ISOrganizationService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISCommonjobsfunctionsrelService;
import com.tahoecn.xkc.service.sys.ISCommonjobsmenurelService;
import com.tahoecn.xkc.service.sys.ISMenusService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-04
 */
@Service
public class SJobsServiceImpl extends ServiceImpl<SJobsMapper, SJobs> implements ISJobsService {

    @Autowired
    private ISAccountService accountService;
    @Autowired
    private ISJobsuserrelService jobsuserrelService;
    @Autowired
    private ISJobsmenurelService jobsmenurelService;
    @Autowired
    private ISMenusService menusService;
    @Autowired
    private IBProjectjobrelService projectjobrelService;
    @Autowired
    private IBSalesuserService salesuserService;
    @Autowired
    private ISOrganizationService organizationService;
    @Autowired
    private ISCommonjobsmenurelService commonjobsmenurelService;
    @Autowired
    private ISCommonjobsfunctionsrelService commonjobsfunctionsrelService;

    @Autowired
    private IBProjectService ibProjectService;

    @Override
    public IPage<Map<String,Object>> SystemJobList_Select(IPage page,String authCompanyID, String productID, String orgID) {
        return baseMapper.SystemJobList_Select(page,authCompanyID,productID,orgID);
    }

    @Override
    public List<SAccount> SystemUserList_Select(String jobID, String authCompanyID) {
        return baseMapper.SystemUserList_Select(jobID,authCompanyID);
    }

    @Override
    public IPage<Map<String,Object>> SystemJobAllList_Select(IPage page,String authCompanyID, String productID, String orgID) {
        return baseMapper.SystemJobAllList_Select(page,authCompanyID,productID,orgID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemJobUser_Insert(SAccount account, String jobID) {

        try {
            account.setPassword("C8837B23FF8AAA8A2DDE915473CE0991");
            account.setCreator(ThreadLocalUtils.getUserName());
            account.setCreateTime(new Date());
            account.setIsDel(0);
            boolean save = accountService.save(account);
            SJobsuserrel jobsuserrel=new SJobsuserrel();
            jobsuserrel.setJobID(jobID);
            jobsuserrel.setAccountID(account.getId());
            boolean save1 = jobsuserrelService.save(jobsuserrel);
            if (save&&save1){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return false;
    }

    @Override
    public IPage<SAccount> SystemOrgUserList_Select(IPage page, String userName, String employeeName, String jobID) {
        return baseMapper.SystemOrgUserList_Select(page,userName,employeeName,jobID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemJobUserRel_Insert(String userIDS, String jobID) {
        try {
            String[] split = userIDS.split(",");
            for (String userID : split) {
                QueryWrapper<SJobsuserrel> wrapper=new QueryWrapper<>();
                wrapper.eq("JobID",jobID);
                wrapper.eq("AccountID",userID);
                SJobsuserrel one = jobsuserrelService.getOne(wrapper);
                if (one==null){
                    SJobsuserrel jobsuserrel=new SJobsuserrel();
                    jobsuserrel.setJobID(jobID);
                    jobsuserrel.setAccountID(userID);
                    jobsuserrelService.save(jobsuserrel);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemJobAuth_Insert(String menus, String jobID) {
        boolean save =false;
        try {
            String[] menusSplit = menus.split(",");
            //删除原功能
            QueryWrapper<SJobsmenurel> wrapper=new QueryWrapper<>();
            wrapper.eq("JobID",jobID);
//            wrapper.in("MenuID",oldMenusSplit);
            List<SJobsmenurel> list = jobsmenurelService.list(wrapper);
            //判断menuID在原menu还是xkc menu  如果原menu不删除
            for (SJobsmenurel jobsmenurel : list) {
                String menuID = jobsmenurel.getMenuID();
                SMenus byId = menusService.getById(menuID);
                if (byId==null){
                    jobsmenurelService.removeById(jobsmenurel);
                }
            }
//            QueryWrapper<SCommonjobsfunctionsrel> queryWrapper=new QueryWrapper<>();
//            queryWrapper.eq("JobID",jobID);
////            queryWrapper.in("FuncID",oldFunctionsSplit);
//            List<SCommonjobsfunctionsrel> list1 = commonjobsfunctionsrelService.list(queryWrapper);
//            for (SCommonjobsfunctionsrel sCommonjobsfunctionsrel : list1) {
//                commonjobsfunctionsrelService.removeById(sCommonjobsfunctionsrel);
//            }
            //新增新菜单
            if (menusSplit.length!=0){
                for (String s : menusSplit) {
                    SJobsmenurel jobsmenurel=new SJobsmenurel();
                    jobsmenurel.setJobID(jobID);
                    jobsmenurel.setMenuID(s);
                     save = jobsmenurelService.save(jobsmenurel);
                }
            }
//        if (functionsSplit.length!=0){
//            for (String s : functionsSplit) {
//                SCommonjobsfunctionsrel commonjobsfunctionsrel=new SCommonjobsfunctionsrel();
//                commonjobsfunctionsrel.setJobID(jobID);
//                commonjobsfunctionsrel.setFuncID(s);
//                commonjobsfunctionsrelService.save(commonjobsfunctionsrel);
//            }
//        }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemJobUserRel_Delete(String userIDS, String jobID) {
        try {
            String[] split = userIDS.split(",");
            for (String s : split) {
                QueryWrapper<SJobsuserrel> wrapper=new QueryWrapper<>();
                wrapper.eq("JobID",jobID);
                wrapper.eq("AccountID",s);
                jobsuserrelService.remove(wrapper);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    @Override
    public boolean SystemJobAuthOrg_Insert(String orgIDS, String jobID) {
        boolean save=false;
        try {
            String[] split = orgIDS.split(",");
            List<String> IDs=new ArrayList<>();
            //去除非项目选项 如泰禾集团 地产板块
            List<String> projects = ibProjectService.getProjectIDs();
            for (String s : split) {
                if (projects.contains(s)){
                    IDs.add(s);
                }
            }
            QueryWrapper<BProjectjobrel> wrapper=new QueryWrapper<>();
            wrapper.eq("JobID",jobID);
            List<BProjectjobrel> list = projectjobrelService.list(wrapper);
            for (BProjectjobrel projectjobrel : list) {
                projectjobrel.setIsDel(1);
                projectjobrelService.updateById(projectjobrel);
            }
            for (String s : IDs) {
                BProjectjobrel projectjobrel=new BProjectjobrel();
                projectjobrel.setCreator(ThreadLocalUtils.getUserName());
                projectjobrel.setJobID(jobID);
                projectjobrel.setProjectID(s);
                projectjobrel.setCreateTime(new Date());
                projectjobrel.setIsDel(0);
                projectjobrel.setStatus(1);
                save = projectjobrelService.save(projectjobrel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemJobUser_Update(SAccount account) {
        try {
            account.setEditTime(new Date());
            account.setEditor(ThreadLocalUtils.getUserName());
            accountService.updateById(account);
//         得同时更改掉B_SalesUser表里面的信息
            BSalesuser salesuser=new BSalesuser();
            salesuser.setId(account.getId());
            salesuser.setUserName(account.getUserName());
            salesuser.setTelPhone(account.getMobile());
            salesuser.setEditor(account.getEditor());
            salesuser.setEditTime(account.getEditTime());
            salesuserService.updateById(salesuser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> SystemJobAuthOrg_Select(String pid, String jobID) {
        //原数据授权,显示全部组织
//        List<Map<String, Object>> list = organizationService.SystemOrganizationChec_Select(pid);
        //数据授权只显示项目相关
        List<Map<String, Object>> list = organizationService.SystemProject_Select(pid);
        List<Map<String, Object>> result=new ArrayList<>();
        for (Map<String, Object> map : list) {
            QueryWrapper<BProjectjobrel> wrapper=new QueryWrapper<>();
            wrapper.eq("JobID",jobID);
            wrapper.eq("ProjectID",map.get("ID"));
            List<BProjectjobrel> projectjobrels = projectjobrelService.list(wrapper);
            if (projectjobrels.size()!=0){
                map.put("IsAuth",true);
            }else {
                map.put("IsAuth",false);
            }
            result.add(map);
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> CommonJobList_Select() {
        return baseMapper.CommonJobList_Select();
    }

    @Override
    public List<SMenusXkcDto> MenuOrFunIDList_Select_Tree() {
        List<SMenusXkcDto> menus=baseMapper.MenuOrFunIDList_Select_Tree();
        List<SMenusXkcDto> build = this.buildByRecursive(menus);
        //查询子功能列表在Sfunction表
        List<Map<String,Object>> list=getFuncList();
        List<SMenusXkcDto> result = new ArrayList<>();
        //build第一级为APP菜单,getChildren筛选
        for (SMenusXkcDto sMenusXkcDto : build.get(0).getChildren()) {
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"数据")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(false);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"消息")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setisLeaf(false);
                        child.setSignal(true);
                        //添加子集
                        SMenusXkcDto menusXkcDto=new SMenusXkcDto();
                        List funcList=new ArrayList();
                        for (Map<String, Object> map : list) {
                            if (StringUtils.equals(child.getId(),(String)map.get("MenuID"))){
                                menusXkcDto.setId((String) map.get("ID"));
                                menusXkcDto.setMenuName((String) map.get("FuncName"));
                                menusXkcDto.setisLeaf(true);
                                menusXkcDto.setSignal(false);
                                funcList.add(menusXkcDto);
                            }
                        }
                        child.setChildren(funcList);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"客户")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setisLeaf(false);
                        child.setSignal(true);
                        //添加子集
                        List funcList=new ArrayList();
                        for (Map<String, Object> map : list) {
                            if (StringUtils.equals(child.getId(),(String)map.get("MenuID"))){
                                SMenusXkcDto menusXkcDto=new SMenusXkcDto();
                                menusXkcDto.setId((String) map.get("ID"));
                                menusXkcDto.setMenuName((String) map.get("FuncName"));
                                menusXkcDto.setisLeaf(true);
                                menusXkcDto.setSignal(false);
                                funcList.add(menusXkcDto);
                            }
                        }
                        child.setChildren(funcList);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"锦囊")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(false);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"团队")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(false);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"考勤")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(false);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"房源页面")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(false);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"任务")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(true);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"作战图")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(true);
                    }
                }
            }
            if (StringUtils.equals(sMenusXkcDto.getMenuName(),"个人中心")){
                if (sMenusXkcDto.getChildren()!=null){
                    for (SMenusXkcDto child : sMenusXkcDto.getChildren()) {
                        child.setSignal(false);
                    }
                }
            }

            if (sMenusXkcDto.getChildren()!=null){
                result.add(sMenusXkcDto);
            }
        }
//        List<Map<String, Object>> children = getChildren(menus.getId());
//        List<Map<String,Object>> result = new ArrayList<>();
//        //加入单选复选标识
//        for (Map<String, Object> map : children) {
//            if (!(Boolean) map.get("isLeaf")){
//                List<Map<String,Object>> resultChildren=new ArrayList<>();
//                if (StringUtils.equals((String) map.get("MenuName"),"数据")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",false);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"消息")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",true);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"客户")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",true);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"锦囊")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",false);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"团队")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",false);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"考勤")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",false);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"房源页面")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",false);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"任务")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",true);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"作战图")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",true);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                if (StringUtils.equals((String) map.get("MenuName"),"个人中心")){
//                    List<Map<String, Object>> children1 = (List<Map<String, Object>>) map.get("children");
//                    for (Map<String, Object> objectMap : children1) {
//                        objectMap.put("signal",false);
//                        resultChildren.add(objectMap);
//                    }
//                    map.put("children",resultChildren);
//                }
//                result.add(map);
//            }
//        }
        return result;
    }

    private List<Map<String, Object>> getFuncList() {
        return baseMapper.getFuncList();
    }

    @Override
    public List<Map<String, Object>> MenuOrFunIDList_Select(String JobID) {
        return  baseMapper.MenuOrFunIDList_Select(JobID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean FunctionAuthorization_Insert(String jobID, String mainID, String sonID) {
        try {
            //先删除jobid 为此参数的项
            QueryWrapper<SCommonjobsmenurel> wrapper=new QueryWrapper<>();
            wrapper.eq("JobID",jobID);
            wrapper.eq("IsDel",0);
            List<SCommonjobsmenurel> commonjobsmenurels = commonjobsmenurelService.list(wrapper);
            for (SCommonjobsmenurel commonjobsmenurel : commonjobsmenurels) {
                commonjobsmenurel.setIsDel(1);
                commonjobsmenurelService.updateById(commonjobsmenurel);
            }
            QueryWrapper<SCommonjobsfunctionsrel> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("JobID",jobID);
            queryWrapper.eq("IsDel",0);
            List<SCommonjobsfunctionsrel> commonjobsfunctionsrels = commonjobsfunctionsrelService.list(queryWrapper);
            for (SCommonjobsfunctionsrel commonjobsfunctionsrel : commonjobsfunctionsrels) {
                commonjobsfunctionsrel.setIsDel(1);
                commonjobsfunctionsrelService.updateById(commonjobsfunctionsrel);
            }
            //重新添加新增
            String[] mainIDs = mainID.split(",");
            for (String id : mainIDs) {
                SCommonjobsmenurel commonjobsmenurel=new SCommonjobsmenurel();
                commonjobsmenurel.setMenuID(id);
                commonjobsmenurel.setJobID(jobID);
                commonjobsmenurel.setIsDel(0);
                commonjobsmenurelService.save(commonjobsmenurel);
            }
            String[] sonIDs = sonID.split(",");
            for (String id : sonIDs) {
                SCommonjobsfunctionsrel commonjobsfunctionsrel=new SCommonjobsfunctionsrel();
                commonjobsfunctionsrel.setFuncID(id);
                commonjobsfunctionsrel.setJobID(jobID);
                commonjobsfunctionsrel.setIsDel(0);
                commonjobsfunctionsrelService.save(commonjobsfunctionsrel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

        return true;
    }


//    /**
//     * 通过父级id获取所有子集
//     * @param PID
//     * @return
//     */
//    public List<Map<String,Object>> getChildren(String PID){
//        List<Map<String,Object>> list = new ArrayList<>();
//        //menu
//        List<Map<String,Object>> children = baseMapper.getChildrenByPID(PID);
//        for (Map<String,Object> typeInfo : children) {
//            List<Map<String,Object>> children1 = getChildren((String) typeInfo.get("ID"));
//            List<Map<String,Object>> FuncChildren = baseMapper.getFuncChildren((String) typeInfo.get("ID"));
//            if (FuncChildren.size()>0){
//                typeInfo.put("isLeaf",false);
//                typeInfo.put("children", FuncChildren);
//            }
//            if (children1.size()>0){
//                typeInfo.put("isLeaf",false);
//                typeInfo.put("children", children1);
//            }else {
//                typeInfo.put("isLeaf",true);
//            }
//            list.add(typeInfo);
//        }
//        return list;
//    }

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    private   List<SMenusXkcDto> buildByRecursive(List<SMenusXkcDto> treeNodes) {
        List<SMenusXkcDto> trees = new ArrayList<SMenusXkcDto>();
        for (SMenusXkcDto treeNode : treeNodes) {
            if ("-1".equals(treeNode.getPid())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }


    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private  SMenusXkcDto findChildren(SMenusXkcDto treeNode,List<SMenusXkcDto> treeNodes) {
        for (SMenusXkcDto it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<SMenusXkcDto>());
                }
                treeNode.setisLeaf(false);
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    private List<Map<String, Object>> getFuncChildren(String  menuID){
        return baseMapper.getFuncChildren(menuID);
    }

}
