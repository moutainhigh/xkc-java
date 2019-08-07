package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.converter.Result;
import com.tahoecn.xkc.mapper.sys.BMedialargeMapper;
import com.tahoecn.xkc.model.sys.BMediachild;
import com.tahoecn.xkc.model.sys.BMedialarge;
import com.tahoecn.xkc.service.sys.IBMediachildService;
import com.tahoecn.xkc.service.sys.IBMedialargeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-08-06
 */
@Service
public class BMedialargeServiceImpl extends ServiceImpl<BMedialargeMapper, BMedialarge> implements IBMedialargeService {

    @Autowired
    private IBMediachildService mediachildService;
    @Override
    public List<Map<String, Object>> getMediaList_Select(String projectID) {
        List<Map<String, Object>> mediaLargeList = baseMapper.getMediaLargeList();
        List<Map<String, Object>> childList = baseMapper.getMediaChildList(projectID);
        for (Map<String, Object> map : mediaLargeList) {
            List<Map<String, Object>> child = new ArrayList<>();
            for (Map<String, Object> childmap : childList) {
                if (StringUtils.equals((String) map.get("ID"), (String) childmap.get("MediaLargeID"))) {
                    child.add(childmap);
                }
            }
            //手动设定媒体大类父id为-1
            map.put("MediaLargeID","-1");
            map.put("children", child);
            map.put("haschild", child.size() > 0);
        }
        return mediaLargeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result Media_SaveOrUpdate(String projectID,String mediaLargeID, String name, String shortName, int listIndex, int status) {
        //媒体大类
        try {
            if ("-1".equals(mediaLargeID)){
                BMedialarge bMedialarge=new BMedialarge();
                bMedialarge.setName(name);
                bMedialarge.setShortName(shortName);
                bMedialarge.setListIndex(listIndex);
                bMedialarge.setStatus(status);
                bMedialarge.setIsDel(0);
                bMedialarge.setCreateTime(new Date());
                bMedialarge.setCreator(ThreadLocalUtils.getUserName());
                this.saveOrUpdate(bMedialarge);
            }else {
                BMediachild bMediachild=new BMediachild();
                bMediachild.setName(name);
                bMediachild.setShortName(shortName);
                bMediachild.setListIndex(listIndex);
                bMediachild.setIsDel(0);
                bMediachild.setStatus(status);
                bMediachild.setProjectID(projectID);
                bMediachild.setCreateTime(new Date());
                bMediachild.setCreator(ThreadLocalUtils.getUserName());
                mediachildService.saveOrUpdate(bMediachild);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(99,"新增或编辑失败");
        }
        return Result.okm("成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result Media_Status(String mediaLargeID, String id, int status) {
        try {
            if ("-1".equals(mediaLargeID)){
                BMedialarge bMedialarge=new BMedialarge();
                bMedialarge.setId(id);
                bMedialarge.setStatus(status);
                bMedialarge.setEditTime(new Date());
                bMedialarge.setEditor(ThreadLocalUtils.getUserName());
                this.updateById(bMedialarge);
            }else {
                BMediachild bMediachild=new BMediachild();
                bMediachild.setId(id);
                bMediachild.setStatus(status);
                bMediachild.setEditor(ThreadLocalUtils.getUserName());
                bMediachild.setEditTime(new Date());
                mediachildService.updateById(bMediachild);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(99,"修改状态失败");
        }
        return Result.okm("成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result Media_Delete(String mediaLargeID, String id) {
        try {
            if ("-1".equals(mediaLargeID)){
                QueryWrapper<BMedialarge> wrapper=new QueryWrapper<>();
                wrapper.eq("ID",id);
                this.remove(wrapper);
            }else {
                QueryWrapper<BMediachild> wrapper=new QueryWrapper<>();
                wrapper.eq("ID",id);
                mediachildService.remove(wrapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.errormsg(99,"删除失败");
        }
        return Result.okm("成功");
    }

}
