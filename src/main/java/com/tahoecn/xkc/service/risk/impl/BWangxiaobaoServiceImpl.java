package com.tahoecn.xkc.service.risk.impl;

import cn.hutool.core.date.DateUtil;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.uc.sso.SSOHelper;
import com.tahoecn.uc.sso.security.token.SSOToken;
import com.tahoecn.xkc.async.ExecutorsUtils;
import com.tahoecn.xkc.async.FaceRynnable;
import com.tahoecn.xkc.mapper.sys.SysAccessRecordMapper;
import com.tahoecn.xkc.model.risk.BRiskconfig;
import com.tahoecn.xkc.model.risk.BWangxiaobao;
import com.tahoecn.xkc.mapper.risk.BWangxiaobaoMapper;
import com.tahoecn.xkc.model.risk.vo.WangXiaobaoVO;
import com.tahoecn.xkc.model.sys.SysAccessRecord;
import com.tahoecn.xkc.service.risk.IBWangxiaobaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.service.sys.ISysAccessRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2020-05-11
 */
@Service
public class BWangxiaobaoServiceImpl extends ServiceImpl<BWangxiaobaoMapper, BWangxiaobao> implements IBWangxiaobaoService {

    @Autowired
    private ISysAccessRecordService sysAccessRecordService;
    @Resource
    private SysAccessRecordMapper sysAccessRecordMapper;

    @Override
    public JSONResult record(HttpServletRequest request, WangXiaobaoVO vo) {
        SysAccessRecord sysAccessRecord = sysAccessRecordService.getSysAccessRecord(request);
        JSONResult jsonResult = new JSONResult();

        //新增start
        BWangxiaobao bWangxiaobao = new BWangxiaobao();
        BeanUtils.copyProperties(vo, bWangxiaobao);
        bWangxiaobao.setCreateTime(DateUtil.date());
        bWangxiaobao.setId(UUID.randomUUID().toString());
        this.baseMapper.insert(bWangxiaobao);
        //新增end
        ExecutorsUtils.fkExecute(new FaceRynnable(bWangxiaobao));
        sysAccessRecord.setInterfaceState("0");
        sysAccessRecord.setReason("成功");
        sysAccessRecordMapper.insert(sysAccessRecord);
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        return jsonResult;
    }
}
