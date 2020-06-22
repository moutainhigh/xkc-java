package com.tahoecn.xkc.schedule;

import com.tahoecn.xkc.config.TomcatPort;
import com.tahoecn.xkc.schedule.risk.*;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @description: 风控定时任务
 * @author: 张晓东
 * @time: 2020/5/6 10:50
 */
@Configuration
public class RiskTask {

    @Value("${execute.task.ip}")
    private String executeTaskIp;
    //人脸识别
    @Value("${face.task:0 1 1 * * ?}")
    private String faceCron;
    //联名购房
    @Value("${joint.name.task:0 2 1 * * ?}")
    private String jointNameCron;
    //防截客
    @Value("${protect.customer.task:0 3 1 * * ?}")
    private String protectCustomerCron;
    //搜电未报备
    @Value("${search.mobile.task:0 4 1 * * ?}")
    private String searchMobileCron;
    //短期成交
    @Value("${short.deal.task:0 5 1 * * ?}")
    private String shortDealCron;
    //认筹未刷证
    @Value("${unverified.task:0 6 1 * * ?}")
    private String unverifiedCron;
    //认筹未刷证
    @Value("${updateInfo.task:0 7 1 * * ?}")
    private String updateInfoCron;

    /*@Value("${wxbriskcount.task:0 8 1 * * ?}")
    private String wxbriskcountCron;*/


    // 配置定时任务:
    @Bean(name = "searchMobileDetail")
    public MethodInvokingJobDetailFactoryBean searchMobileDetail(SearchMobileTask searchMobileTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(searchMobileTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:
    @Bean(name = "searchMobileTrigger")
    public CronTriggerFactoryBean searchMobileTrigger(JobDetail searchMobileDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(searchMobileDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(searchMobileCron);
        return trigger;
    }


    // 配置定时任务:
    @Bean(name = "shortDealDetail")
    public MethodInvokingJobDetailFactoryBean shortDealDetail(ShortDealTask shortDealTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(shortDealTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:
    @Bean(name = "shortDealTrigger")
    public CronTriggerFactoryBean shortDealTrigger(JobDetail shortDealDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(shortDealDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(shortDealCron);
        return trigger;
    }


    // 配置定时任务:
    @Bean(name = "protectCustomerDetail")
    public MethodInvokingJobDetailFactoryBean protectCustomerDetail(ProtectCustomerTask protectCustomerTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(protectCustomerTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:
    @Bean(name = "protectCustomerTrigger")
    public CronTriggerFactoryBean protectCustomerTrigger(JobDetail protectCustomerDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(protectCustomerDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(protectCustomerCron);
        return trigger;
    }


    // 配置定时任务:联名购房
    @Bean(name = "jointNameDetail")
    public MethodInvokingJobDetailFactoryBean jointNameDetail(JointNameTask jointNameTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(jointNameTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:联名购房
    @Bean(name = "jointNameTrigger")
    public CronTriggerFactoryBean jointNameTrigger(JobDetail jointNameDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jointNameDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(jointNameCron);
        return trigger;
    }


    // 配置定时任务:人脸识别
    @Bean(name = "faceDetail")
    public MethodInvokingJobDetailFactoryBean faceDetail(FaceTask faceTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(faceTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:人脸识别
    @Bean(name = "faceTrigger")
    public CronTriggerFactoryBean faceTrigger(JobDetail faceDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(faceDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(faceCron);
        return trigger;
    }



    // 配置定时任务:人脸识别
    @Bean(name = "unverifiedDetail")
    public MethodInvokingJobDetailFactoryBean unverifiedDetail(UnverifiedTask unverifiedTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(unverifiedTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:人脸识别
    @Bean(name = "unverifiedTrigger")
    public CronTriggerFactoryBean unverifiedTrigger(JobDetail unverifiedDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(unverifiedDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(unverifiedCron);
        return trigger;
    }

    // 配置定时任务:人脸识别
    @Bean(name = "updateInfoDetail")
    public MethodInvokingJobDetailFactoryBean updateInfoDetail(FkUpdateInfoTask fkUpdateInfoTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(fkUpdateInfoTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:人脸识别
    @Bean(name = "updateInfoTrigger")
    public CronTriggerFactoryBean updateInfoTrigger(JobDetail updateInfoDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(updateInfoDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(updateInfoCron);
        return trigger;
    }


    /*@Bean(name = "wxbriskcountDetail")
    public MethodInvokingJobDetailFactoryBean wxbriskcountDetail(WxbriskcountTask wxbriskcountTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(wxbriskcountTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("task");
        return jobDetail;
    }


    // 配置触发器:人脸识别
    @Bean(name = "wxbriskcountTrigger")
    public CronTriggerFactoryBean wxbriskcountTrigger(JobDetail wxbriskcountDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(wxbriskcountDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date(System.currentTimeMillis()));
        // cron表达式
        trigger.setCronExpression(wxbriskcountCron);
        return trigger;
    }*/

    @Value("${execute.task.port}")
    private String executeTaskPort;

    @Autowired
    private TomcatPort tomcatPort;

    private Logger log = LoggerFactory.getLogger(RiskTask.class);

    // 配置Scheduler
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger faceTrigger, Trigger jointNameTrigger, Trigger protectCustomerTrigger,
                                                 Trigger searchMobileTrigger, Trigger shortDealTrigger, Trigger unverifiedTrigger,
                                                 Trigger updateInfoTrigger/*, Trigger wxbriskcountTrigger*/) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        log.info("zhangxiaodong log ip : {} , port : {} , if : {}", executeTaskIp, tomcatPort.getHttpPort(), ipList().contains(executeTaskIp) && executeTaskPort.equals(tomcatPort.getHttpPort()+""));
        if (ipList().contains(executeTaskIp) && executeTaskPort.equals(tomcatPort.getHttpPort()+"")) {
            // 延时启动，应用启动1秒后
            bean.setStartupDelay(1);
            // 注册触发器
            bean.setTriggers(/*editNameTrigger, */faceTrigger, jointNameTrigger, protectCustomerTrigger, searchMobileTrigger,
                    shortDealTrigger, unverifiedTrigger, updateInfoTrigger/*, wxbriskcountTrigger*/);
        }
        return bean;
    }

    private List<String> ipList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }

}
