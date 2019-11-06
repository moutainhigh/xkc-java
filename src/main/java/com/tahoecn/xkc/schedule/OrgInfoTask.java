package com.tahoecn.xkc.schedule;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import com.tahoecn.xkc.service.uc.CsUcOrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tahoecn.core.date.DateUtil;
import com.tahoecn.core.util.JsonUtil;
import com.tahoecn.core.util.NetUtil;
import com.tahoecn.http.HttpUtil;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.model.CsUcOrg;
import com.tahoecn.xkc.model.dto.OrgInfoDto;
import com.tahoecn.xkc.model.dto.ResponseDto;
import com.tahoecn.security.SecureUtil;

/**
 * 同步组织数据 同步时间凌晨2点
 * 
 * @ClassName OrgInfoTask
 * @author wang_sd
 * @date 2019年4月3日
 */
@Component("orgInfoTask")
public class OrgInfoTask {

	private static final Log log = LogFactory.get();
	@Value("${uc_api_url_orglist}")
	private String orgListUrl;
	@Value("${uc_sysId}")
	private String sysId;
	@Value("${uc_priv_key}")
	private String privKey;
	@Value("${bind_host}")
	private String bindHost;

	private String fromTime = "2000-01-01 00:00:00";

	@Autowired
	private CsUcOrgService csUcOrgService;

	@Scheduled(cron = "0 0 2 * * ?")
	public void pullOrgInfo() {
		if (StringUtils.isNotBlank(bindHost)) {
			Boolean flg = false;
			for (String ip : NetUtil.localIpv4s()) {
				log.info("bind host str={} ,localhost str = {}", bindHost, ip);
				flg = flg ? true : bindHost.equals(ip);

			}
			if (!flg) {
				log.info("bind host {} != localhost pullUserInfo scheduled exit! ", bindHost);
				return;
			}
		}
		try {
			Long timestamp = System.currentTimeMillis();
			String token = SecureUtil.md5(timestamp + privKey);
			String toTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
			Integer pageSize = 50;
			Integer pageNo = 1;
			Integer pageTotal = pageNo;
			fromTime = DateUtil.lastWeek().toDateStr();
			while (pageNo < pageTotal + 1) {
				String url = String.format(
						orgListUrl + "?fromTime=%s&toTime=%s&sysId=%s&pageNo=%s&pageSize=%s&timestamp=%s&token=%s",
						URLEncoder.encode(fromTime, "utf-8"), URLEncoder.encode(toTime, "utf-8"), sysId, pageNo,
						pageSize, String.valueOf(timestamp), token);
				log.info("request url = {}", url);
				String result = HttpUtil.httpGet(url);
				log.info("orginfo result = {}", result);
				if (StringUtils.isNotBlank(result)) {
					ResponseDto<List<OrgInfoDto>> responseDto = JsonUtil.convertJsonToBean(result, ResponseDto.class);
					if (responseDto != null && responseDto.getCode() == 0) {
						pageTotal = responseDto.getTotalPages();
						if (responseDto.getResult() != null) {
							for (Object str : responseDto.getResult()) {
								String info = JsonUtil.convertObjectToJson(str);
								OrgInfoDto orgInfoDto = JsonUtil.convertJsonToBean(info, OrgInfoDto.class);
								CsUcOrg orgInfo = convert(orgInfoDto);
								csUcOrgService.synOrgInfo(orgInfo);
							}
						}
					}
				}
				Thread.sleep(1000);
				pageNo++;
			}
		} catch (Exception e) {
			log.error(e, "pull org info fail! {}", e.getMessage());
		}

	}

	private CsUcOrg convert(OrgInfoDto orgInfoDto) {
		CsUcOrg orgInfo = new CsUcOrg();
		BeanUtils.copyProperties(orgInfoDto, orgInfo);
		orgInfo.setCreateDate(new Date());
		orgInfo.setUpdateDate(new Date());
		return orgInfo;
	}
}
