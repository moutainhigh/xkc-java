package com.tahoecn.xkc.service.customer.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.model.customer.BCustomer;
import com.tahoecn.xkc.service.customer.IBCustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-05
 */
@Service
public class BCustomerServiceImpl extends ServiceImpl<BCustomerMapper, BCustomer> implements IBCustomerService {


    @Override
    public IPage<Map<String, Object>> customerChangePageList_Select(IPage page, String projectID, String sqlWhere) {
        return baseMapper.customerChangePageList_Select(page,projectID,sqlWhere);
    }

    @Override
    public List<Map<String, Object>> setExcelToCustomerChangeList(String projectID, String sqlWhere) {
        return baseMapper.excelToCustomerChangeList(projectID,sqlWhere);
    }

    @Override
    public List<Map<String, Object>> GetDistributionList_Select(String project) {
        return baseMapper.getDistributionList_Select(project);
    }

    @Override
    public IPage<Map<String, Object>> CustomerChange_BakPageList_Select(IPage page, String projectID, String sqlWhere) {
        return baseMapper.CustomerChange_BakPageList_Select(page,projectID,sqlWhere);
    }

    @Override
    public List<Map<String, Object>> SetExcelToCustomerChange_BakList(String projectID, String sqlWhere) {
        return baseMapper.SetExcelToCustomerChange_BakList(projectID,sqlWhere);
    }

    @Override
    public Map<String, Object> CustomerChangeDetailAll_Select(String projectId, String customerID, String clueID) {
        Map<String, Object> opportunity = baseMapper.getB_OpportunityByClueID(clueID);
        Map<String, Object> customerChangeDetail;
        if (opportunity != null){
            customerChangeDetail = baseMapper.CustomerChangeDetailAll_Select(projectId,customerID,clueID,opportunity.get("CustomerID").toString());
        }else{
            customerChangeDetail = baseMapper.CustomerChangeDetailAll_Select2(projectId,customerID,clueID);
        }
        return customerChangeDetail;
    }
}
