package com.tahoecn.xkc.schedule;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import com.tahoecn.core.util.NetUtil;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.enums.TaskInfoType;
import com.tahoecn.xkc.model.sys.SService;
import com.tahoecn.xkc.model.sys.SServicelog;
import com.tahoecn.xkc.service.sys.ISServiceService;
import com.tahoecn.xkc.service.sys.ISServicelogService;
import com.tahoecn.xkc.service.sys.ITaskService;

/**
 * TODO 客储定时调度管理器
 * 
 * @ClassName: SaleInfoTask
 * @author: wq_qycc
 * @date: 2019年8月2日
 */
@Component
public class SaleInfoTask implements SchedulingConfigurer {
	private static final Log log = LogFactory.get();

	@Value("${bind_host}")
	private String bindHost;

	@Autowired
	private ISServiceService service;
	@Autowired
	private ISServicelogService servicelog;
	@Autowired
	private ITaskService saleService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		if (isHost()) {
			Integer corePoolSize = 0;
			List<SService> list = service.list();
			if (list != null && list.size() > 0) {
				for (SService e : list) {
					TaskInfoType task = TaskInfoType.getByCode(e.getServiceCode());
					if (task != null && task.getType() != null && !"".equals(task.getType())) {
						log.info("配置{}（{}）调度 =>>>> {} 模式（{}）", e.getServiceDesc(), e.getServiceCode(), task.getType(),
								task.getValue());
						switch (task.getType()) {
						case "cron":
							scheduledTaskRegistrar.addCronTask(doTask(e), task.getValue());
							corePoolSize++;
							break;
						case "fixedDelay":
							scheduledTaskRegistrar.addFixedDelayTask(doTask(e), Long.valueOf(task.getValue()));
							corePoolSize++;
							break;
						case "fixedRate":
							scheduledTaskRegistrar.addFixedRateTask(doTask(e), Long.valueOf(task.getValue()));
							corePoolSize++;
							break;
						default:
							break;
						}
					}
				}
			}
			if (corePoolSize > 0)
				scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
		}
	}

	/**
	 * TODO 执行定时任务
	 * 
	 * @param service
	 * @return
	 */
	private Runnable doTask(SService service) {
		return new Runnable() {
			@Override
			public void run() {
				Date st = new Date();
				log.info("{}：开始执行{}（{}）调度", st, service.getServiceDesc(), service.getServiceCode());
				try {
					saleService.taskRun(service.getServiceCode());
					log.info("{}：{}（{}）调度执行结束", st, service.getServiceDesc(), service.getServiceCode());
					servicelog.save(SServicelog.isSuccess(service.getId(), st, new Date()));
				} catch (Exception e) {
					Throwable t = e.getCause() == null ? e : e.getCause();
					log.info("{}：{}（{}）调度执行失败：{}", st, service.getServiceDesc(), service.getServiceCode(),
							t.getMessage());
					servicelog.save(SServicelog.isError(service.getId(), st, new Date(), t.getMessage()));
				}
			}
		};
	}

	/**
	 * TODO 判断本地IP
	 * 
	 * @return
	 */
	private Boolean isHost() {
		if (StringUtils.isNotBlank(bindHost)) {
			for (String ip : NetUtil.localIpv4s()) {
				log.info("bind host str={} ,localhost str = {}", bindHost, ip);
				if (bindHost.equals(ip))
					return true;
			}
			log.info("bind host {} != localhost pullUserInfo scheduled exit! ", bindHost);
			return false;
		}
		return true;
	}

}