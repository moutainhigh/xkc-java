package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.sys.SMenusXkcMapper;
import com.tahoecn.xkc.model.dict.BTag;
import com.tahoecn.xkc.model.salegroup.BSalesgroup;
import com.tahoecn.xkc.model.sys.SCity;
import com.tahoecn.xkc.model.sys.SMenus;
import com.tahoecn.xkc.model.sys.SMenusXkc;
import com.tahoecn.xkc.service.customer.IBCustomerfiltergroupService;
import com.tahoecn.xkc.service.dict.IBTagService;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import com.tahoecn.xkc.service.project.IBProjectService;
import com.tahoecn.xkc.service.project.IVProjectroomService;
import com.tahoecn.xkc.service.salegroup.IBSalesgroupService;
import com.tahoecn.xkc.service.salegroup.IBSalesuserService;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.sys.ISCityService;
import com.tahoecn.xkc.service.sys.ISMenusXkcService;
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
 * @since 2019-07-12
 */
@Service
public class SMenusXkcServiceImpl extends ServiceImpl<SMenusXkcMapper, SMenusXkc> implements ISMenusXkcService {


    @Autowired
    private ISAccountService accountService;

    @Autowired
    private ISMenusXkcService menusXkcService;



    @Override
    public List<Map<String,Object>> SystemMenusList_Select() {
        return baseMapper.SystemMenusList_Select();
    }

    @Override
    public void SystemMenu_Insert(SMenusXkc menus) {
        baseMapper.SystemMenu_Insert(menus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result SystemMenu_Update(SMenusXkc menu) {
        try {
            SMenusXkc oldMenu = this.getById(menu.getId());
            String ID = menu.getId();
            String MenuSysName = menu.getMenuSysName();
            String MenuName = menu.getMenuName();
            String Url = menu.getUrl();
            String ImageUrl = menu.getImageUrl();
            String IconClass = menu.getIconClass();
            Integer IsHomePage = menu.getIsHomePage();
            Integer IsShow = menu.getIsShow();
            String pid=menu.getPid();
            Integer Levels;
            if (!"-1".equals(pid)){
                SMenusXkc sMenusXkc = baseMapper.selectById(pid);
                Levels=sMenusXkc.getLevels()+1;
            }else {
                Levels=1;
            }
            Integer ListIndex = menu.getListIndex();
            // 1为一级菜单  0为非一级
            Integer IsLast = menu.getIsLast();
            String Editor = ThreadLocalUtils.getUserName();
            Integer Status = menu.getStatus();
            String OldPath=baseMapper.getOldPath(ID);
            String NewPath=baseMapper.getNewPath(pid);
            if (NewPath!=null){
                NewPath=NewPath+"/"+MenuSysName;
            }else {
                NewPath=MenuSysName;
            }

            //一级菜单修改为非一级
            if (oldMenu.getIsLast()==1&&IsLast==0){
                QueryWrapper<SMenusXkc> wrapper=new QueryWrapper<>();
                wrapper.eq("PID",ID);
                wrapper.eq("IsDel",0);
                List<SMenusXkc> list = this.list(wrapper);
                if (list.size()>0){
                    return Result.errormsg(1,"此菜单有下级菜单,请移出下级菜单");
                }
            } if (oldMenu.getIsLast()==0&&IsLast==1){//非一级菜单修改为一级
                pid="-1";
                Levels=1;
                NewPath=MenuSysName;
            }
            SMenusXkc menus=new SMenusXkc();
            menus.setId(ID);
            menus.setMenuSysName(MenuSysName);
            menus.setMenuName(MenuName);
            menus.setUrl(Url);
            menus.setImageUrl(ImageUrl);
            menus.setIconClass(IconClass);
            menus.setIsHomePage(IsHomePage);
            menus.setIsShow(IsShow);
            menus.setLevels(Levels);
            menus.setListIndex(ListIndex);
            menus.setIsLast(IsLast);
            menus.setEditor(Editor);
            menus.setEditTime(new Date());
            menus.setStatus(Status);
            menus.setFullPath(NewPath);
            menus.setPid(pid);
            baseMapper.updateById(menus);
            if (StringUtils.isNotBlank(OldPath)){
                QueryWrapper<SMenusXkc> wrapper=new QueryWrapper<>();
                wrapper.likeRight("FullPath",OldPath);
                List<SMenusXkc> list = baseMapper.selectList(wrapper);
                for (SMenusXkc sMenus : list) {
                    String fullPath = sMenus.getFullPath();
                    String replace = fullPath.replace(OldPath, NewPath);
                    sMenus.setFullPath(replace);
                    baseMapper.updateById(sMenus);
                }
            }
            //如果是一级菜单,修改其下所有菜单IsLast=0
            if (IsLast==1){
                QueryWrapper<SMenusXkc> wrapper=new QueryWrapper<>();
                wrapper.likeRight("FullPath",NewPath);
                wrapper.ne("ID",ID);
                List<SMenusXkc> list = baseMapper.selectList(wrapper);
                for (SMenusXkc sMenus : list) {
                    sMenus.setIsLast(0);
                    baseMapper.updateById(sMenus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(1,"修改失败");
        }
        return Result.okm("成功");
    }

    @Override
    public List<Map<String, Object>> SystemCommonJobAuth_Select(String userID, String authCompanyID, String productID, String jobID) {

        return null;
    }

    @Override
    public List<Map<String, Object>> UserMenus(String userID, String authCompanyID, String productID) {
        List<Map<String, Object>> list=accountService.insertJob(userID,authCompanyID,productID);

        return list;
    }

    @Override
    public List<Map<String, Object>> getResult() {
        return baseMapper.getResult();
    }

    @Override
    public List<Map<String, Object>> UserFunctions(String userID, String authCompanyID, String productID) {
        return baseMapper.UserFunctions(userID,authCompanyID, productID);
    }

    @Override
    public Map<String, Object> CommonJobFunctions(String jobID) {
        List<Map<String, Object>> list=baseMapper.getCommonJobFunctions(jobID);
        List<Map<String, Object>> list1=baseMapper.CommonJobFunctions(jobID);
        List<String> result=new ArrayList();
        Map<String, Object> objectMap=new HashMap<>();
        for (Map<String, Object> map : list) {
            result.add((String)map.get("IDS"));
        }
        for (Map<String, Object> map : list1) {
            result.add((String)map.get("IDS"));
        }
        //去掉父级ID 只要子级ID
        QueryWrapper<SMenusXkc> wrapper=new QueryWrapper<>();
        wrapper.eq("PID","-1").eq("IsDel",0);
        List<SMenusXkc> PIDList = menusXkcService.list(wrapper);
        List<String> type=new ArrayList();
        for (String s : result) {
            for (SMenusXkc sMenusXkc : PIDList) {
                if (StringUtils.equals(s,sMenusXkc.getId())){
                    type.add(s);
                }
            }
        }
        result.removeAll(type);
        objectMap.put("IDS",result);
        return objectMap;
    }

    public List<Map<String, Object>> getElseResult(String commonJobID) {
        return baseMapper.getElseResult(commonJobID);
    }

    public List<Map<String, Object>> getOtherResult(String jobID, String id) {
        return baseMapper.getOtherResult(jobID,id);
    }
}
