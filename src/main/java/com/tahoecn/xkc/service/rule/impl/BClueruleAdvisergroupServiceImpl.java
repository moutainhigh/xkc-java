package com.tahoecn.xkc.service.rule.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.rule.BClueruleAdvisergroupMapper;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import com.tahoecn.xkc.service.rule.IBClueruleAdvisergroupService;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
@Service
public class BClueruleAdvisergroupServiceImpl extends ServiceImpl<BClueruleAdvisergroupMapper, BClueruleAdvisergroup> implements IBClueruleAdvisergroupService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRules(String OrgID, String UserID, String rProjectID, String ClueRuleID, String ProjectIDWhere) {
        /*try {*/
            //先删除掉这个机构删掉的项目对应的规则
            baseMapper.deleteByOrgID(OrgID, UserID, ProjectIDWhere);
            //然后进行修改/新增操作
            int count = baseMapper.selectDeleteCount(OrgID, rProjectID);
            //看看有没有删除的，有删除的直接用，修改下改成未删除的
            if (count > 0) {
                String ID = baseMapper.selectID(OrgID, rProjectID);
                if (StringUtils.isBlank(ClueRuleID)) {
                    //RuleID为空表示删除这个规则
                    baseMapper.deleteByID(UserID, ID);
                }
                //不为空表示修改这个规则
                BClueruleAdvisergroup clueruleAdvisergroup = new BClueruleAdvisergroup();
                clueruleAdvisergroup.setId(ID);
                clueruleAdvisergroup.setEditor(UserID);
                clueruleAdvisergroup.setEditTime(new Date());
                clueruleAdvisergroup.setClueRuleID(ClueRuleID);
                clueruleAdvisergroup.setIsDel(0);
                baseMapper.updateById(clueruleAdvisergroup);
            }
            //没有找到数据的话 并且{ClueRuleID}不为空的话 就直接新增
            else {
                BClueruleAdvisergroup clueruleAdvisergroup = new BClueruleAdvisergroup();
                clueruleAdvisergroup.setEditor(UserID);
                clueruleAdvisergroup.setEditTime(new Date());
                clueruleAdvisergroup.setAdviserGroupID(OrgID);
                clueruleAdvisergroup.setClueRuleID(ClueRuleID);
                clueruleAdvisergroup.setIsDel(0);
                clueruleAdvisergroup.setStatus(1);
                baseMapper.updateById(clueruleAdvisergroup);
            }
            return true;
        /*} catch (Exception e) {
            e.printStackTrace();
            //出错手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }*/
    }
}
