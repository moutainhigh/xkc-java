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
    public boolean SystemMenu_Update(SMenusXkc menu) {
        try {
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
            Integer IsLast = menu.getIsLast();
            String Editor = ThreadLocalUtils.getUserName();
            Integer Status = menu.getStatus();
            String OldPath=baseMapper.getOldPath(ID);
            String NewPath=baseMapper.getNewPath(ID);
            if (NewPath!=null){
                NewPath=NewPath+"/"+MenuSysName;
            }else {
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
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> SystemCommonJobAuth_Select(String userID, String authCompanyID, String productID, String jobID) {

        return null;
    }

    @Override
    public List<HashMap<String, Object>> UserMenus(String userID, String authCompanyID, String productID) {
        List<HashMap<String, Object>> list=accountService.insertJob(userID,authCompanyID,productID);

        return list;
    }

    @Override
    public List<HashMap<String, Object>> getResult() {
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
        List result=new ArrayList();
        Map<String, Object> objectMap=new HashMap<>();
        for (Map<String, Object> map : list) {
            result.add(map.get("IDS"));
        }
        for (Map<String, Object> map : list1) {
            result.add(map.get("IDS"));
        }
        objectMap.put("IDS",result);
        return objectMap;
    }

    public List<HashMap<String, Object>> getElseResult(String commonJobID) {
        return baseMapper.getElseResult(commonJobID);
    }

    public List<HashMap<String, Object>> getOtherResult(String jobID, String id) {
        return baseMapper.getOtherResult(jobID,id);
    }
}
