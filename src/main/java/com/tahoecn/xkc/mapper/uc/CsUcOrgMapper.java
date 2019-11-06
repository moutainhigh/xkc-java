package com.tahoecn.xkc.mapper.uc;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tahoecn.xkc.model.CsUcOrg;
import com.tahoecn.xkc.model.vo.OrgInfoVo;

import java.util.List;

/**
 * <p>
 * UC组织机构信息 Mapper 接口
 * </p>
 *
 * @author zghw
 * @since 2018-11-15
 */
public interface CsUcOrgMapper extends BaseMapper<CsUcOrg> {

    List<OrgInfoVo> findAllOrgInfo();
}
