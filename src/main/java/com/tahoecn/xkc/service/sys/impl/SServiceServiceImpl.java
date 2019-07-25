package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.sys.SServiceMapper;
import com.tahoecn.xkc.model.sys.SService;
import com.tahoecn.xkc.service.sys.ISServiceService;
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
 * @since 2019-07-25
 */
@Service
public class SServiceServiceImpl extends ServiceImpl<SServiceMapper, SService> implements ISServiceService {

    @Override
    public IPage<Map<String, Object>> ServiceList_Select(int pageindex, int pagesize) {
        IPage page=new Page(pageindex,pagesize);
        IPage<Map<String,Object>> iPage=baseMapper.ServiceList_Select(page);
        List<Map<String, Object>> list = iPage.getRecords();
        List<Map<String, Object>> result=new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> obj=baseMapper.ServiceLogList_Select((String)map.get("ID"));
            if (obj.size()==0){
                obj.put("ServiceLogID","");
                obj.put("StartRunTime","");
                obj.put("EndRunTime","");
                obj.put("LogStatus","1");
                obj.put("Count","0");
            }
            obj.put("ID",map.get("ID"));
            obj.put("ServiceCode",map.get("ServiceCode"));
            obj.put("ServiceDesc",map.get("ServiceDesc"));
            obj.put("Status",map.get("Status"));
            obj.put("ServiceFrequency",map.get("ServiceFrequency"));
            result.add(obj);
        }

        return iPage.setRecords(result);
    }

    @Override
    public IPage<Map<String, Object>> ServiceLogListByService_Select(IPage page, String serviceID, int status) {
        return baseMapper.ServiceLogListByService_Select(page,serviceID,status);
    }
}
