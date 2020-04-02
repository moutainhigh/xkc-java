package com.tahoecn.xkc.service.miniprogram.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.ChannelTypeIdEnum;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.miniprogram.CustomerMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.RelationshipVO;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.miniprogram.ICustomerService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 思为小程序客户信息获取服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
public class CustomerServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements ICustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Override
    public JSONResult getCustomerList(HttpServletRequest request, RelationshipVO relationshipVO) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        List<Map> result = new ArrayList<Map>();
        if (ChannelTypeIdEnum.Self_Drains.getCode() == Integer.valueOf(relationshipVO.getRoleId()) ||
                ChannelTypeIdEnum.Self_Commissioner.getCode() == Integer.valueOf(relationshipVO.getRoleId())) {
            result = customerMapper.getCustomerList1(relationshipVO);
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
        } else if (ChannelTypeIdEnum.Sale.getCode() == Integer.valueOf(relationshipVO.getRoleId()) ||
                ChannelTypeIdEnum.Adviser.getCode() == Integer.valueOf(relationshipVO.getRoleId())) {
            result = customerMapper.getCustomerList2(relationshipVO);
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

    @Override
    public JSONResult getCustomerDetail(HttpServletRequest request, String opportunityId) throws Exception {
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map = customerMapper.getCustomerDetail1(opportunityId);
        if (null != map) {
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
        } else {
            map = customerMapper.getCustomerDetail2(opportunityId);
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
        }
        return ResultUtil.setJsonResult(new JSONResult<>(), map, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
    }

    @Override
    public JSONResult getCustomerFollowRecord(HttpServletRequest request, String opportunityId) throws Exception {
        JSONResult jsonResult = new JSONResult();
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        String customerId = customerMapper.getCustomerId1(opportunityId);
        if (StringUtils.isEmpty(customerId)) {
            customerId = customerMapper.getCustomerId2(opportunityId);
        }
        // 判断经过两次获取用户id后是否为空
        if (StringUtils.isEmpty(customerId)) {
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
            jsonResult = ResultUtil.setJsonResult(new JSONResult<>(), null, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
        } else {
            Map map = customerMapper.getCustomerFollowRecord(customerId);
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
            jsonResult = ResultUtil.setJsonResult(new JSONResult<>(), map, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
        }
        return jsonResult;
    }
}
