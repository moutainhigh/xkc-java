package com.tahoecn.xkc.service.ratio.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.uc.sso.SSOHelper;
import com.tahoecn.uc.sso.security.token.SSOToken;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.ratio.BRatioconfigMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.ratio.BRatioconfig;
import com.tahoecn.xkc.model.ratio.vo.RatioconfigVO;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.ratio.IBRatioconfigService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2020-05-12
 */
@Service
public class BRatioconfigServiceImpl extends ServiceImpl<BRatioconfigMapper, BRatioconfig> implements IBRatioconfigService {

    @Autowired
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Resource
    private BProjectMapper bProjectMapper;

    @Override
    @Transactional
    public JSONResult replace(HttpServletRequest request, RatioconfigVO vo) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);

        Map<String, List<String>> channelMap = this.baseMapper.selectList(new QueryWrapper<BRatioconfig>() {{
            eq("Type", 0);
            eq("IsDel", 0);
            if (StringUtils.isNotEmpty(vo.getId())) {
                notIn("ID",vo.getId());
            }
        }}).stream().flatMap(i -> Arrays.asList(i.getChannels().split(",")).stream())
                .collect(Collectors.groupingBy(Function.identity()));
        AtomicBoolean flag = new AtomicBoolean(false);
        AtomicReference<String> msg = new AtomicReference();
        Arrays.asList(vo.getChannels().split(","))
                .stream()
                .forEach(i -> {
                    if (!flag.get() && channelMap.containsKey(i)) {
                        flag.set(true);
                        msg.set(i);
                    }
                });
        if (flag.get())
            return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), new StringBuilder(msg.get()).append("不能重复选择").toString());

        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
//        String changeUser = st.getIssuer();   //用户名
        String changeUser = "";
        DateTime changeTime = DateUtil.date();

        if (StringUtils.isNotEmpty(vo.getId())) {//修改
            //判断是否存在
                if (null == this.baseMapper.selectById(new BRatioconfig(){{
                setId(vo.getId());
            }}))return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "此渠道占比id不存在");
            iUpdate(vo, changeUser, changeTime);
        } else {
            iInsert(vo, changeUser, changeTime);//删除
        }
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        this.sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
    }

    private void iInsert(RatioconfigVO vo, String changeUser, DateTime changeTime) {
        String blocID = UUID.randomUUID().toString();
        this.baseMapper.insert(new BRatioconfig() {{
            setId(blocID);
            setType(0);
            setName(vo.getName());
            setChannels(vo.getChannels());
            setReportRatio(vo.getReportRatio());
            setDealRatio(vo.getDealRatio());
            setCreator(changeUser);
            setCreateTime(changeTime);
            setIsDel(0);
            setStatus(1);
        }});
    }

    private void iUpdate(RatioconfigVO vo, String changeUser, DateTime changeTime) {
        this.baseMapper.updateById(new BRatioconfig() {{
            setId(vo.getId());
            setName(vo.getName());
            setChannels(vo.getChannels());
            setReportRatio(vo.getReportRatio());
            setDealRatio(vo.getDealRatio());
            setEditor(changeUser);
            setEditeTime(changeTime);
        }});
    }

    @Override
    public JSONResult listConfig() {
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), this.baseMapper.selectList(new QueryWrapper<BRatioconfig>() {{
            eq("IsDel", 0);
            eq("Type", 0);
        }}));
    }

    @Override
    @Transactional
    public JSONResult iRemove(HttpServletRequest request, String id) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);
        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
//        String changeUser = st.getIssuer();   //用户名
        String changeUser = "";
        DateTime changeTime = DateUtil.date();
        this.baseMapper.updateById(new BRatioconfig(){{
            setId(id);
            setIsDel(1);
            setEditor(changeUser);
            setEditeTime(changeTime);
        }});
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        this.sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
    }

    @Override
    @Transactional
    public JSONResult disable(HttpServletRequest request, Boolean all, String id) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);
        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
//        String changeUser = st.getIssuer();   //用户名
        String changeUser = "";
        DateTime changeTime = DateUtil.date();
        BRatioconfig bRatioconfig = new BRatioconfig() {{
            setStatus(0);
            setEditor(changeUser);
            setEditeTime(changeTime);
        }};
        if (all) {//禁用全部
            this.baseMapper.update(bRatioconfig, new QueryWrapper(){{
                eq("Type", 0);
                eq("IsDel", 0);
                eq("Status", 1);
            }});
        } else {//禁用一个
            bRatioconfig.setId(id);
            this.baseMapper.updateById(bRatioconfig);
        }
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        this.sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
    }

    @Override
    @Transactional
    public JSONResult command(HttpServletRequest request) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);

        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
//        String changeUser = st.getIssuer();   //用户名
        String changeUser = "";
        DateTime changeTime = DateUtil.date();
        //删除全部
        this.baseMapper.selectList(new QueryWrapper<BRatioconfig>(){{
            eq("IsDel", 0);
            eq("Type", 1);
        }}).stream().forEach(i -> {
            this.baseMapper.updateById(new BRatioconfig(){{
                setId(i.getId());
                setIsDel(1);
                setEditor(changeUser);
                setEditeTime(changeTime);
            }});
        });

        //重新下发
        this.baseMapper.selectList(new QueryWrapper<BRatioconfig>(){{
            eq("Status", 1);
            eq("IsDel", 0);
            eq("Type", 0);
        }}).stream().forEach(i -> {
            // 分发项目给节点
            this.bProjectMapper.searchProject()
                    .stream()
                    .forEach(i2 -> {
                        this.baseMapper.insert(new BRatioconfig() {{
                            setId(UUID.randomUUID().toString());
                            setType(1);
                            setMasterId(i.getId());
                            setName(i.getName());
                            setChannels(i.getChannels());
                            setReportRatio(i.getReportRatio());
                            setDealRatio(i.getDealRatio());
                            setProjectId((String) i2.get("ProjectId"));
                            setProjectName((String) i2.get("ProjectName"));
                            setRegionalId((String) i2.get("RegionalId"));
                            setRegionalName((String) i2.get("RegionalName"));
                            setCityId((String) i2.get("CityId"));
                            setCityName((String) i2.get("CityName"));
                            setCreator(changeUser);
                            setCreateTime(changeTime);
                            setIsDel(0);
                            setStatus(1);
                        }});
                    });
        });
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        this.sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
    }

    @Override
    @Transactional
    public JSONResult enable(HttpServletRequest request, String id) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);

        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
//        String changeUser = st.getIssuer();   //用户名
        String changeUser = "";
        DateTime changeTime = DateUtil.date();
        this.baseMapper.updateById(new BRatioconfig(){{
            setId(id);
            setStatus(1);
            setEditeTime(changeTime);
            setEditor(changeUser);
        }});
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        this.sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
    }
}
