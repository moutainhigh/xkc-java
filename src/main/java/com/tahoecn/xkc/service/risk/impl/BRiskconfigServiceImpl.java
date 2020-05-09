package com.tahoecn.xkc.service.risk.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.uc.sso.SSOHelper;
import com.tahoecn.uc.sso.security.token.SSOToken;
import com.tahoecn.xkc.mapper.project.BProjectMapper;
import com.tahoecn.xkc.mapper.risk.BRiskconfigMapper;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.project.BProject;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.vo.RiskConfigVo;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.risk.IBRiskconfigService;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2020-05-06
 */
@Service
public class BRiskconfigServiceImpl extends ServiceImpl<BRiskconfigMapper, BRiskconfig> implements IBRiskconfigService {

    private Logger log = LoggerFactory.getLogger(BRiskconfigServiceImpl.class);

    @Resource
    private ISysAccessRecordService sysAccessRecordService;

    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Resource
    private BProjectMapper bProjectMapper;

    @Override
    public JSONResult search(HttpServletRequest request, Integer type, String projectId) {
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        JSONResult jsonResult = new JSONResult();
        QueryWrapper<BRiskconfig> wrapper = new QueryWrapper() {{
            eq("IsDel", 0);
            eq("Status", 1);
        }};
        if (type == 0) {//集团
            wrapper.eq("Type", 0);
        } else {
            wrapper.eq("Type", 1);
            wrapper.eq("ProjectID", projectId);
        }
        BRiskconfig bRiskconfig = this.baseMapper.selectOne(wrapper);
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        jsonResult.setData(bRiskconfig);
        return jsonResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONResult release(HttpServletRequest request) {
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        JSONResult jsonResult = new JSONResult();

        this.baseMapper.disableAll();

        BRiskconfig bRiskconfig = this.baseMapper.selectOne(new QueryWrapper() {{
            eq("IsDel", 0);
            eq("Status", 1);
            eq("Type", 0);
        }});

        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
        String issuer = st.getIssuer();   //用户名
//        String issuer = "";
        bProjectMapper.selectList(new QueryWrapper<BProject>() {{
            eq("Level", 1);
            eq("IsDel", 0);
            eq("Status", 1);
        }}).stream().forEach(i -> {
            BRiskconfig clone = null;
            try {
                clone = bRiskconfig.clone();
            } catch (CloneNotSupportedException e) {
                log.error("bean clone error : {}", e.getMessage());
            }
            clone.setId(UUID.randomUUID().toString());
            clone.setType(1);
            clone.setProjectID(i.getId());
            clone.setCreator(issuer);
            clone.setCreateTime(new Date());
            this.baseMapper.insert(clone);
        });
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        return jsonResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONResult replace(HttpServletRequest request, RiskConfigVo vo) {

        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        JSONResult jsonResult = new JSONResult();

        //禁用历史数据
        this.baseMapper.disable(vo.getId());

        //新增start
        BRiskconfig bRiskconfig = new BRiskconfig();
        BeanUtils.copyProperties(vo, bRiskconfig);
        bRiskconfig.setId(UUID.randomUUID().toString());
        SSOToken st = SSOHelper.getSSOToken(request);
        if (st == null) {
            st = SSOHelper.attrToken(request);
        }
        String issuer = st.getIssuer();   //用户名
//        String issuer = "";
        bRiskconfig.setCreator(issuer);
        bRiskconfig.setCreateTime(new Date());
        this.baseMapper.insert(bRiskconfig);
        //新增end

        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        return jsonResult;
    }
}
