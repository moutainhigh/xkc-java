package com.tahoecn.xkc.service.report.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.report.CustomerBookMapper;
import com.tahoecn.xkc.model.customer.CustomerBook;
import com.tahoecn.xkc.service.report.ICustomerBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-08-28
 */
@Service
public class CustomerBookServiceImpl extends ServiceImpl<CustomerBookMapper, CustomerBook> implements ICustomerBookService {
    @Override
    public List<Map<String, Object>> listOpp(String id) {
        return baseMapper.listOpp(id);
    }

    @Override
    public List<Map<String, Object>> listClue(String id) {
        return baseMapper.listClue(id);
    }

    @Override
    public List<Map<String, Object>> customerPayInfo(String id) {
        return baseMapper.customerPayInfo(id);
    }
}
