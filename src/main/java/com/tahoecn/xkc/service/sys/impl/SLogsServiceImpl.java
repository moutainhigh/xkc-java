package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.mapper.sys.SLogsMapper;
import com.tahoecn.xkc.model.sys.SLogs;
import com.tahoecn.xkc.service.sys.ISLogsService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YYY
 * @since 2019-07-01
 */
@Service
public class SLogsServiceImpl extends ServiceImpl<SLogsMapper, SLogs> implements ISLogsService {

	@Autowired
	private SLogsMapper sLogsMapper;
	/**
	 * 记录日志
	 */
	@Override
	public void SystemLogsDetail_Insert(Map<String, Object> logMap, HttpServletRequest request) {
		SLogs log = new SLogs();
		if (logMap.get("BizID")!=null){
            log.setBizID((String) logMap.get("BizID"));
        }
        if (logMap.get("BizType")!=null){
            log.setBizType((String) logMap.get("BizType"));
        }
        if (logMap.get("BizDesc")!=null){
            log.setBizDesc((String) logMap.get("BizDesc"));
        }
//		log.setIp(NetUtil.localIpv4s().toString());//本地ip
		log.setIp(NetUtil.getRemoteAddr2(request));//客户端IP
        if (logMap.get("Ext1")!=null){
            log.setExt1((String) logMap.get("Ext1"));
        }
        if (logMap.get("Ext2")!=null){
            log.setExt2((String) logMap.get("Ext2"));
        }
        if (logMap.get("Ext3")!=null){
            log.setExt3((String) logMap.get("Ext3"));
        }
        if (logMap.get("Ext4")!=null){
            log.setExt4((String) logMap.get("Ext4"));
        }
        if (logMap.get("Data")!=null){
            log.setData((String) logMap.get("Data"));
        }
        if (logMap.get("OrgID")!=null){
            log.setOrgID((String) logMap.get("OrgID"));
        }
		log.setAuthCompanyID("17BA4307-D05A-4A57-8729-FC1BD45302B6");
		log.setProductID("10A9328C-2ADF-4797-A666-92E2E4CD92A1");
        if (logMap.get("UserID")!=null){
            log.setCreator((String) logMap.get("UserID"));
        }
		log.setCreateTime(new Date());
		log.setStatus(1);
		log.setIsDel(0);
		sLogsMapper.insert(log);
	}
	
	/*
	 * 操作日志
	 */
	@Override
	public List<Map<String, Object>> mBrokerCustomerDetail_Select(Map<String, Object> map) {
		return sLogsMapper.mBrokerCustomerDetail_Select(map);
	}

	@Override
	public int mBrokerCustomerDetail_SelectAll(Map<String, Object> map) {
		return sLogsMapper.mBrokerCustomerDetail_SelectAll(map);
	}

	@Override
	public List<Map<String, Object>> mBrokerCustomerDetail_Select1(Map<String, Object> map) {
		return sLogsMapper.mBrokerCustomerDetail_Select1(map);
	}


}
