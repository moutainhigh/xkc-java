package com.tahoecn.xkc.service.miniprogram.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.ChannelTypeIdEnum;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.miniprogram.RelationshipMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.miniprogram.IRelationshipService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 思为小程序组织关系服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
@Service
public class RelationshipServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements IRelationshipService {

    @Resource
    private RelationshipMapper relationshipMapper;

    @Resource
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Override
    public JSONResult getOrgRole(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<RelationshipVO> list = relationshipMapper.getOrgRole(relationshipVO);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), list, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getProjectPerson(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<RelationshipVO> list = relationshipMapper.getOrgRole(relationshipVO);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), list, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getOrgPerson(HttpServletRequest request) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<RelationshipVO> result = new ArrayList<RelationshipVO>();
        List<RelationshipVO> dataList1 = relationshipMapper.getOrgPerson1();
        List<RelationshipVO> dataList2 = relationshipMapper.getOrgPerson2();
        result.addAll(dataList1);
        result.addAll(dataList2);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), result, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getProject(HttpServletRequest request) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<RelationshipVO> list = relationshipMapper.getProject();
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), list, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getRole(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<RelationshipVO> list = relationshipMapper.getRole(relationshipVO);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), list, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getPerson(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<RelationshipVO> list = relationshipMapper.getPerson(relationshipVO);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(new JSONResult<>(), list, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getCustomer(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<Map> result = new ArrayList<Map>();
        if (ChannelTypeIdEnum.Self_Drains.getCode() == Integer.valueOf(relationshipVO.getRoleId()) ||
                ChannelTypeIdEnum.Self_Commissioner.getCode() == Integer.valueOf(relationshipVO.getRoleId())) {
            result = relationshipMapper.getCustomer1(relationshipVO);
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
        } else if (ChannelTypeIdEnum.Sale.getCode() == Integer.valueOf(relationshipVO.getRoleId()) ||
                ChannelTypeIdEnum.Adviser.getCode() == Integer.valueOf(relationshipVO.getRoleId())) {
            result = relationshipMapper.getCustomer2(relationshipVO);
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
        } else {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("roleId参数错误");
            sysAccessRecordMapper.insert(sysAccessRecord);
            return new JSONResult(1, "roleId参数错误");
        }
        return ResultUtil.setJsonResult(new JSONResult<>(), result, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }
}
