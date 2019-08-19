package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tahoecn.xkc.model.sys.*;
import com.tahoecn.xkc.service.sys.*;
import com.tahoecn.xkc.mapper.sys.SCommonjobsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class SCommonjobsServiceImpl extends ServiceImpl<SCommonjobsMapper, SCommonjobs> implements ISCommonjobsService {
	
	@Autowired
	private SCommonjobsMapper SCommonjobsMapper;

    @Autowired
    private ISCommonjobsmenurelService commonjobsmenurelService;

    @Autowired
    private ISCommonjobsfunctionsrelService commonjobsfunctionsrelService;

    @Autowired
    private ISMenusService menusService;

    @Autowired
    private ISMenusXkcService menusXkcService;

	@Override
	public List<SCommonjobs> SystemCommonJobsList_Select(String AuthCompanyID, String ProductID, String JobName) {
		List<SCommonjobs> list = SCommonjobsMapper.SystemCommonJobsList_Select(AuthCompanyID, ProductID, JobName);
		return list;
	}

	@Override
	public List<SCommonjobs> SystemCommonJobsList_SelectList() {
		List<SCommonjobs> list = SCommonjobsMapper.SystemCommonJobsList_SelectList();
		return list;
	}

	@Override
	public Boolean SystemCommonJobNameIsExists_Select(String AuthCompanyID,String ProductID,String JobName) {
		String result= SCommonjobsMapper.SystemCommonJobNameIsExists_Select(AuthCompanyID,ProductID,JobName);
		if(StringUtils.isNotEmpty(result)){
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public void SystemCommonJobStatus_Update(Map<String, Object> map) {
		SCommonjobsMapper.SystemCommonJobStatus_Update(map);
	}

	@Override
	public void SystemCommonJob_Delete(Map<String, Object> map) {
		SCommonjobsMapper.SystemCommonJob_Delete(map);
	}

	@Override
	public String SystemCommonJob_Insert(Map<String, Object> map) {
		String id = UUID.randomUUID().toString();
		map.put("id", id);
		SCommonjobsMapper.SystemCommonJob_Insert(map);
		return id;
	}

	@Override
	public void SystemCommonJob_Update(Map<String, Object> map) {
		SCommonjobsMapper.SystemCommonJob_Update(map);
	}

    /**
     * 功能授权
     * @param oldMenus
     * @param oldFunctions
     * @param menus
     * @param functions
     * @param jobID
     */
    @Override
    public boolean SystemCommonJobAuth_Insert(String oldMenus, String oldFunctions, String menus, String functions, String jobID) {
                try {
                    //字符串分组
//            String[] oldMenusSplit = oldMenus.split("|");
//            String[] oldFunctionsSplit = oldFunctions.split("|");
                    String[] menusSplit = menus.split(",");
//                    String[] functionsSplit = functions.split("|");

                    //删除原功能
                    QueryWrapper<SCommonjobsmenurel> wrapper=new QueryWrapper<>();
                    wrapper.eq("JobID",jobID);
//            wrapper.in("MenuID",oldMenusSplit);
                    List<SCommonjobsmenurel> list = commonjobsmenurelService.list(wrapper);
                    //判断menuID在原menu还是xkc menu  如果原menu不删除
                    for (SCommonjobsmenurel commonjobsmenurel : list) {
                        String menuID = commonjobsmenurel.getMenuID();
                        SMenus byId = menusService.getById(menuID);
                        if (byId==null){
                            commonjobsmenurelService.removeById(commonjobsmenurel);
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
            //查询出所有子菜单父级, 传递的菜单ID若子集没全选 是没有父级菜单ID的
                    HashSet<String> set=new HashSet();
                    List<SMenusXkc> fuList = menusXkcService.list();
                    for (String s : menusSplit) {
                        for (SMenusXkc sMenusXkc : fuList) {
                            if (StringUtils.equals(s,sMenusXkc.getId())){
                                //去pid存入set
                                set.add(sMenusXkc.getPid());
                            }
                        }
                        set.add(s);
                    }

                    if (menusSplit.length!=0){
                    for (String s : set) {
                        SCommonjobsmenurel commonjobsmenurel=new SCommonjobsmenurel();
                        commonjobsmenurel.setJobID(jobID);
                        commonjobsmenurel.setMenuID(s);
                        commonjobsmenurelService.save(commonjobsmenurel);
                    }
            }
//            if (functionsSplit.length!=0){
//                for (String s : functionsSplit) {
//                    SCommonjobsfunctionsrel commonjobsfunctionsrel=new SCommonjobsfunctionsrel();
//                    commonjobsfunctionsrel.setJobID(jobID);
//                    commonjobsfunctionsrel.setFuncID(s);
//                    commonjobsfunctionsrelService.save(commonjobsfunctionsrel);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    return true;

    }

}
