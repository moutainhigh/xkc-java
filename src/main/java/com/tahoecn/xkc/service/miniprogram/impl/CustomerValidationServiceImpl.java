package com.tahoecn.xkc.service.miniprogram.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.common.enums.CertificatesIdEnum;
import com.tahoecn.xkc.common.enums.ChannelTypeIdEnum;
import com.tahoecn.xkc.common.enums.SexEnum;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.mapper.channel.BChanneluserMapper;
import com.tahoecn.xkc.mapper.salegroup.BSalesuserMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.channel.BChanneluser;
import com.tahoecn.xkc.model.miniprogram.vo.UserRegisterVO;
import com.tahoecn.xkc.model.salegroup.BSalesuser;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.miniprogram.ICustomerValidationService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 思为小程序客储用户中心验证类接口服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-04-01
 */
@Service
public class CustomerValidationServiceImpl extends ServiceImpl<BChanneluserMapper, BChanneluser> implements ICustomerValidationService {

    @Resource
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Resource
    private BChanneluserMapper bChanneluserMapper;

    @Resource
    private BSalesuserMapper bSalesuserMapper;

    @Override
    @Transactional
    public JSONResult getCustomerMessage(HttpServletRequest request, UserRegisterVO userRegisterVO) throws Exception {
        JSONResult jsonResult = new JSONResult();
        // 记录接口访问信息
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        // ChannelTypeID数值类型校验str  参考ChannelTypeIdEnum枚举类
        String str1 = "0123";
        String str2 = "4567";
        // 当ChannelTypeID = '中介同行' 的时候 ChannelOrgCode非空校验
        if (ChannelTypeIdEnum.MIDDLE.getCode() == Integer.valueOf(userRegisterVO.getChannelTypeID())) {
            if (StringUtils.isEmpty(userRegisterVO.getChannelOrgCode())) {
                sysAccessRecord.setInterfaceState("1");
                sysAccessRecord.setReason("channelOrgCode不能为空");
                sysAccessRecordMapper.insert(sysAccessRecord);
                return new JSONResult(1, "channelOrgCode不能为空");
            }
        }
        // 判断不同类型客户返回不同客户信息
        if (str1.indexOf(userRegisterVO.getChannelTypeID()) >= 0) {
            userRegisterVO.setChannelTypeID(ChannelTypeIdEnum.getEnumByCode(Integer.valueOf(userRegisterVO.getChannelTypeID())).getMessage());
            BChanneluser bChanneluser = bChanneluserMapper.getBChannelUser(userRegisterVO);
            bChanneluser.setGender(String.valueOf(SexEnum.getEnumByMessage(bChanneluser.getGender()).getCode()));
            bChanneluser.setChannelTypeID(String.valueOf(ChannelTypeIdEnum.getEnumByMessage(bChanneluser.getChannelTypeID()).getCode()));
            bChanneluser.setCertificatesType(String.valueOf(CertificatesIdEnum.getEnumByMessage(bChanneluser.getCertificatesType()).getCode()));
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
            jsonResult = ResultUtil.setJsonResult(new JSONResult<>(), bChanneluser, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
        } else if (str2.indexOf(userRegisterVO.getChannelTypeID()) >= 0) {
            userRegisterVO.setChannelTypeID(ChannelTypeIdEnum.getEnumByCode(Integer.valueOf(userRegisterVO.getChannelTypeID())).getMessage());
            BSalesuser bSalesuser = bSalesuserMapper.getBSalesuser(userRegisterVO);
            BChanneluser bChanneluser = new BChanneluser();
            bChanneluser.setId(bSalesuser.getId());
            bChanneluser.setName(bSalesuser.getName());
            bChanneluser.setMobile(bSalesuser.getTelPhone());
            bChanneluser.setUserName(bSalesuser.getUserName());
            bChanneluser.setChannelTypeID(String.valueOf(ChannelTypeIdEnum.getEnumByMessage(userRegisterVO.getChannelTypeID()).getCode()));
            sysAccessRecord.setInterfaceState("0");
            sysAccessRecord.setReason("成功");
            sysAccessRecordMapper.insert(sysAccessRecord);
            jsonResult = ResultUtil.setJsonResult(new JSONResult<>(), bChanneluser, null, TipsEnum.Success.getCode(), TipsEnum.Success.getMsg());
        } else {
            sysAccessRecord.setInterfaceState("1");
            sysAccessRecord.setReason("channelTypeID不正确");
            sysAccessRecordMapper.insert(sysAccessRecord);
            return new JSONResult(1, "channelTypeID不正确");
        }
        return jsonResult;
    }
}
