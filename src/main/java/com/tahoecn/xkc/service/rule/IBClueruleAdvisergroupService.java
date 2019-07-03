package com.tahoecn.xkc.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tahoecn.xkc.model.rule.BClueruleAdvisergroup;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YYY
 * @since 2019-06-26
 */
public interface IBClueruleAdvisergroupService extends IService<BClueruleAdvisergroup> {

    boolean updateRules(String OrgID,String UserID,String rProjectID,String ClueRuleID,String ProjectIDWhere);
}
