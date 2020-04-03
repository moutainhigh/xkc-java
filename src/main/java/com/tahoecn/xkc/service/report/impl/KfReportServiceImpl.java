package com.tahoecn.xkc.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.mapper.report.CostomerReportMapper;
import com.tahoecn.xkc.mapper.report.KfReportMapper;
import com.tahoecn.xkc.model.customer.CostomerReport;
import com.tahoecn.xkc.model.customer.CostomerReportVO;
import com.tahoecn.xkc.model.reprot.KfCostomerReportDetailVO;
import com.tahoecn.xkc.service.report.IKfReportService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-31
 */
@Service
public class KfReportServiceImpl extends ServiceImpl<CostomerReportMapper, CostomerReport> implements IKfReportService {

    @Autowired
    private KfReportMapper kfReportMapper;

    @Override
    public Page<KfCostomerReportDetailVO> kfCostomerReportDetail(Page<KfCostomerReportDetailVO> page, CostomerReportVO report) {
        return kfReportMapper.kfCostomerReportDetail(page, report);
    }

	@Override
	public List<Map<String, Object>> getOrgList(String userName, Integer level, String orgID) {
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		switch (level) {
		case 1:
			res = kfReportMapper.getPOrgList(userName);
			break;
		case 2:
			res = kfReportMapper.getCOrgList(userName, orgID);
			break;
		case 3:
			res = kfReportMapper.getOrgList(userName, orgID);
			break;
		}
		return res;
	}
}
