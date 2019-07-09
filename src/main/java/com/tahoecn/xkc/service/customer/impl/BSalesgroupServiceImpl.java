package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BSalesgroupMapper;
import com.tahoecn.xkc.model.customer.BSalesgroup;
import com.tahoecn.xkc.service.customer.IBSalesgroupService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 树形结构：项目--团队--组 服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-09
 */
@Service
public class BSalesgroupServiceImpl extends ServiceImpl<BSalesgroupMapper, BSalesgroup> implements IBSalesgroupService {

}
