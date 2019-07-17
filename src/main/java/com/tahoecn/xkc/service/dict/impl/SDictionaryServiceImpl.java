package com.tahoecn.xkc.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.mapper.dict.SDictionaryMapper;
import com.tahoecn.xkc.model.dict.SDictionary;
import com.tahoecn.xkc.service.dict.ISDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-27
 */
@Service
public class SDictionaryServiceImpl extends ServiceImpl<SDictionaryMapper, SDictionary> implements ISDictionaryService {

    @Override
    public List<Map<String, String>> AgenCertificatesList_SelectN() {
        return baseMapper.AgenCertificatesList_SelectN();
    }

    @Override
    public List<Map<String,Object>> ListByCode_Select(String dictCodes) {
        return baseMapper.ListByCode_Select(dictCodes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemParam_Delete(String id) {
        //先修改为删除状态 在通过路径判断把带有他路径的子项都改为删除状态
        try {
            SDictionary dictionary=new SDictionary();
            dictionary.setId(id);
            dictionary.setIsDel(1);
            baseMapper.updateById(dictionary);
            //FullPath全路径 不为空
            if (StringUtils.isNotBlank(dictionary.getFullPath())){
                String ProductID=dictionary.getProductID();
                String newFullPath=dictionary.getFullPath()+"/";
                QueryWrapper<SDictionary> wrapper=new QueryWrapper<>();
                wrapper.eq("ProductID",ProductID);
                wrapper.likeRight("FullPath",newFullPath);
                //查询出子项
                List<SDictionary> list = baseMapper.selectList(wrapper);
                //修改子项
                for (SDictionary sDictionary : list) {
                    sDictionary.setIsDel(1);
                    baseMapper.updateById(sDictionary);
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
    @Transactional(rollbackFor = Exception.class)
    public boolean SystemParam_Update(SDictionary dictionary) {
        try {
            SDictionary byId = this.getById(dictionary.getId());
            String OldPath=byId.getFullPath();
            SDictionary byId1 = this.getById(byId.getPid());
            String fullPath = byId1.getFullPath();
            String NewPath;
            if (StringUtils.isBlank(fullPath)){
                NewPath=dictionary.getDictName();
            }else {
                NewPath=fullPath+"/"+dictionary.getDictName();
            }
            String ProductID=byId.getProductID();
            dictionary.setFullPath(NewPath);
            dictionary.setEditor(ThreadLocalUtils.getUserName());
            dictionary.setEditTime(new Date());
            if (StringUtils.isNotBlank(OldPath)){
                QueryWrapper<SDictionary> wrapper=new QueryWrapper<>();
                wrapper.eq("ProductID",ProductID);
                wrapper.likeRight("FullPath",OldPath);
                List<SDictionary> list = this.list(wrapper);
                for (SDictionary sDictionary : list) {
                    String fullPath1 = sDictionary.getFullPath();
                    fullPath1.replace(OldPath,NewPath+"/");
                    sDictionary.setFullPath(fullPath1);
                    this.updateById(sDictionary);
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
    public List<Map<String, Object>> SystemAllParams_Select_Tree(String pid) {
        List<Map<String,Object>> list=baseMapper.list(pid);
        List<Map<String, Object>> result=new ArrayList<>();

            for (Map<String, Object> map : list) {
                List<Map<String, Object>> id = baseMapper.list((String) map.get("ID"));
                if (id.size()>0){
                    map.put("hasChild",true);
                }else {
                    map.put("hasChild",false);
                }
                result.add(map);
            }
        return result;
    }
}
