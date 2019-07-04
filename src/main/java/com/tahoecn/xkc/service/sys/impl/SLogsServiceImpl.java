package com.tahoecn.xkc.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.xkc.common.utils.NetUtil;
import com.tahoecn.xkc.mapper.sys.SLogsMapper;
import com.tahoecn.xkc.model.sys.SLogs;
import com.tahoecn.xkc.service.sys.ISLogsService;

import java.util.Date;
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
		log.setBizID((String) logMap.get("BizID"));
		log.setBizType((String) logMap.get("BizType"));
		log.setBizDesc((String) logMap.get("BizDesc"));
//		log.setIp(NetUtil.localIpv4s().toString());//本地ip
		log.setIp(NetUtil.getRemoteAddr(request));//客户端IP
		log.setExt1((String) logMap.get("Ext1"));
		log.setExt2((String) logMap.get("Ext2"));
		log.setExt3((String) logMap.get("Ext3"));
		log.setExt4((String) logMap.get("Ext4"));
		log.setData((String) logMap.get("Data"));
		log.setOrgID((String) logMap.get("OrgID"));
		log.setAuthCompanyID("17BA4307-D05A-4A57-8729-FC1BD45302B6");
		log.setProductID("10A9328C-2ADF-4797-A666-92E2E4CD92A1");
		log.setCreator((String) logMap.get("UserID"));
		log.setCreateTime(new Date());
		log.setStatus(1);
		log.setIsDel(0);
		sLogsMapper.insert(log);
	}


}
