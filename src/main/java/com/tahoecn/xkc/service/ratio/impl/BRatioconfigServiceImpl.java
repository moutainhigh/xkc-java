package com.tahoecn.xkc.service.ratio.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.uc.sso.SSOHelper;
import com.tahoecn.uc.sso.security.token.SSOToken;
import com.tahoecn.xkc.common.enums.TipsEnum;
import com.tahoecn.xkc.common.utils.ResultUtil;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.ratio.BRatioconfigMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.ratio.BRatioconfig;
import com.tahoecn.xkc.model.ratio.vo.RatioConfigInfoVO;
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
import java.util.*;
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

    private final static String BBZB = new String("bbzb_");
    private final static String CJZB = new String("cjzb_");

    @Override
    @Transactional
    public JSONResult replace(HttpServletRequest request, RatioconfigVO vo) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);

        Map<String, List<String>> channelMap = this.baseMapper.selectList(new QueryWrapper<BRatioconfig>() {{
            eq("Type", 0);
            eq("IsDel", 0);
            if (StringUtils.isNotEmpty(vo.getId())) {
                notIn("ID", vo.getId());
            }
        }}).stream().flatMap(i -> Arrays.asList(i.getChannels().split(",")).stream())
                .collect(Collectors.groupingBy(Function.identity()));
        AtomicBoolean flag = new AtomicBoolean(false);
        AtomicReference<String> msg = new AtomicReference();
        Arrays.asList(vo.getChannels())
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
            if (null == this.baseMapper.selectById(new BRatioconfig() {{
                setId(vo.getId());
            }})) return ResultUtil.setJsonResult(TipsEnum.Failed.getCode(), "此渠道占比id不存在");
            iUpdate(vo, changeUser, changeTime);
        } else {
            iInsert(vo, changeUser, changeTime);//新增
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
            setChannels(Arrays.stream(vo.getChannels()).collect(Collectors.joining(",")));
            setReportRatio(vo.getReportRatio());
            setReportRatioName(BBZB + UUID.randomUUID().toString());
            setDealRatio(vo.getDealRatio());
            setDealRatioName(CJZB + UUID.randomUUID().toString());
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
            setChannels(Arrays.stream(vo.getChannels()).collect(Collectors.joining(",")));
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
        }}).stream().map(this::changeListConfigData));
    }

    Map<String, Object> changeListConfigData(BRatioconfig bRatioconfig) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", bRatioconfig.getId());
        map.put("name", bRatioconfig.getName());
        map.put("channels", bRatioconfig.getChannels().split(","));
        map.put("reportRatio", bRatioconfig.getReportRatio());
        map.put("dealRatio", bRatioconfig.getDealRatio());
        map.put("creator", bRatioconfig.getCreator());
        map.put("createTime", bRatioconfig.getCreateTime());
        map.put("editeTime", bRatioconfig.getEditeTime());
        map.put("editor", bRatioconfig.getEditor());
        map.put("status", bRatioconfig.getStatus());
        return map;
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
        this.baseMapper.updateById(new BRatioconfig() {{
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
            this.baseMapper.update(bRatioconfig, new QueryWrapper() {{
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
        this.baseMapper.update(new BRatioconfig() {{
            setIsDel(1);
            setEditor(changeUser);
            setEditeTime(changeTime);
        }},new QueryWrapper<BRatioconfig>() {{
            eq("IsDel", 0);
            eq("Type", 1);
        }});
        //重新下发
        this.baseMapper.selectList(new QueryWrapper<BRatioconfig>() {{
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
                            setReportRatioName(i.getReportRatioName());
                            setDealRatio(i.getDealRatio());
                            setDealRatioName(i.getDealRatioName());
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
        this.baseMapper.updateById(new BRatioconfig() {{
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

    @Override
    public JSONResult list(HttpServletRequest request, String regionalId, String cityId, String projectId) {
        //集团所有数据, id->info
        Map<String, BRatioconfig> blocMap = this.baseMapper.selectList(new QueryWrapper<BRatioconfig>() {{
            eq("Type", 0);
            eq("IsDel", 0);
            eq("Status", 1);
        }}).stream().collect(Collectors.toMap(BRatioconfig::getId, i1 -> i1));
        //项目明细
        List<BRatioconfig> infos = this.baseMapper.selectList(new QueryWrapper<BRatioconfig>() {{
            eq("Type", 1);
            eq("IsDel", 0);
            eq("Status", 1);
            if (StringUtils.isNotEmpty(regionalId)) eq("RegionalId", regionalId);
            if (StringUtils.isNotEmpty(cityId)) eq("CityId", cityId);
            if (StringUtils.isNotEmpty(projectId)) eq("ProjectId", projectId);
        }});
        Map<String, List<BRatioconfig>> projectMap = infos.stream().collect(Collectors.groupingBy(BRatioconfig::getProjectId));
//        Map<String, Integer> infoMap = infos.stream().collect(Collectors.toMap(i -> i.getProjectId() + "_" + i.getDealRatioName(), BRatioconfig::getDealRatio));
//        infoMap.putAll(infos.stream().collect(Collectors.toMap(i -> i.getProjectId() + "_" + i.getReportRatioName(), BRatioconfig::getReportRatio)));
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode(), new HashMap<String, Object>() {{
            put("blocInfos", infos.stream().collect(Collectors.groupingBy(BRatioconfig::getMasterId))
                    .keySet().stream().map(i -> new HashMap() {{
                        put("id", blocMap.get(i).getId());
                        put("name", blocMap.get(i).getName());
                        put("reportRatioName", blocMap.get(i).getReportRatioName());
                        put("dealRatioName", blocMap.get(i).getDealRatioName());
                    }}).toArray());//下达集团信息
            put("projectMap", projectMap.keySet().stream().map(i -> {
                HashMap<Object, Object> map = Maps.newHashMap();
                projectMap.get(i).forEach(i1 -> {
                    map.put("projectId", i1.getProjectId());
                    map.put("masterId", i1.getMasterId());
                    map.put(i1.getDealRatioName(), i1.getDealRatio());
                    map.put(i1.getReportRatioName(), i1.getReportRatio());
                    map.put("cityName", i1.getCityName());
                    map.put("projectName", i1.getProjectName());
                    map.put("regionalName", i1.getRegionalName());
                });
                return map;
            }).toArray());//项目明细
//            put("infoMap", infoMap);
        }});
    }

    @Override
    @Transactional
    public JSONResult iUpdate(HttpServletRequest request, List<RatioConfigInfoVO> infos) {
        SysAccessRecord sysAccessRecord = this.sysAccessRecordService.getSysAccessRecord(request);
        if (null != infos && infos.size() > 0) {
            SSOToken st = SSOHelper.getSSOToken(request);
            if (st == null) {
                st = SSOHelper.attrToken(request);
            }
//        String changeUser = st.getIssuer();   //用户名
            String changeUser = "";
            DateTime changeTime = DateUtil.date();
            infos.stream().forEach(i -> {
                BRatioconfig bRatioconfig = new BRatioconfig(){{
                    setEditor(changeUser);
                    setEditeTime(changeTime);
                }};
                QueryWrapper<BRatioconfig> wrapper = new QueryWrapper() {{
                    eq("ProjectId", i.getProjectId());
                    if (i.getRatioKey().startsWith(BBZB)) {
                        eq("ReportRatioName", i.getRatioKey());
                        bRatioconfig.setReportRatio(i.getRatio());
                    } else if (i.getRatioKey().startsWith(CJZB)) {
                        eq("DealRatioName", i.getRatioKey());
                        bRatioconfig.setDealRatio(i.getRatio());
                    }
                }};
                baseMapper.update(bRatioconfig, wrapper);
            });
        }
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        this.sysAccessRecordMapper.insert(sysAccessRecord);
        return ResultUtil.setJsonResult(TipsEnum.Success.getCode());
    }
}
