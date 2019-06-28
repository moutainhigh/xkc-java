package com.tahoecn.xkc.service.channel.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.channel.BChannelorgMapper;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.model.channel.BChannelorg;
import com.tahoecn.xkc.model.dto.ChannelDto;
import com.tahoecn.xkc.model.dto.ChannelInsertDto;
import com.tahoecn.xkc.service.channel.IBChannelorgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BChannelorgServiceImpl extends ServiceImpl<BChannelorgMapper, BChannelorg> implements IBChannelorgService {

    @Autowired
    private BChannelorgMapper channelorgMapper;
    @Autowired
    private BChanneluserMapper channeluserMapper;

    @Override
    public Map getList(String ProjectID,String sqlWhere,int pageSize,int pageNum) {
//        if (page==null){
//            return channelorgMapper.getList(map);
//        }
        List<ChannelDto> list = channelorgMapper.getList(ProjectID, sqlWhere,pageSize,pageNum);
        Integer recordCount=channelorgMapper.getRecordCount(ProjectID, sqlWhere);
        Map map=new HashMap();
        map.put("list",list);
        map.put("recordCount",recordCount);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ChannelDetail_InsertN(ChannelInsertDto channelInsertDto) {
        baseMapper.ChannelDetail_InsertN(channelInsertDto);
        channeluserMapper.ChannelDetail_InsertN(channelInsertDto);
    }

    @Override
    public List<Map<String, String>> ChannelOrgAllList_SelectN(String projectID, String pOrgName) {
        return baseMapper.ChannelOrgAllList_SelectN(projectID,pOrgName);
    }

    @Override
    public void ChannelStatus_UpdateN(String id, String userID, String status) {
        baseMapper.ChannelStatus_UpdateN(id,userID,status);
        channeluserMapper.ChannelStatus_UpdateN(id,userID,status);
    }
}
