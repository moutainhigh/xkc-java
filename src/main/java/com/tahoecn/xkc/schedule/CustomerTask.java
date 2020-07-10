package com.tahoecn.xkc.schedule;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.core.util.NetUtil;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.HttpsUtil;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import com.tahoecn.xkc.mapper.wxb.BWxbInfoLogMapper;
import com.tahoecn.xkc.model.wxb.BWxbInfoLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description TODO
 * @Author zefuw
 * @DATE 2020/1/9 10:55
 **/
@PropertySource(value = "classpath:wang_xiao_bao.properties")
@Component
public class CustomerTask {

    private static final Log log = LogFactory.get();
    @Value("${wxb.ak}")
    private String ak;
    @Value("${wxb.sk}")
    private String sk;
    @Value("${wxb.baseUrl}")
    private String baseUrl;
    @Value("${bind_host}")
    private String bindHost;


    @Autowired
    BCustomerMapper customerMapper;

    @Resource
    BWxbInfoLogMapper bWxbInfoLogMapper;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void pushCustomerToWxb(){
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

        List<Map<String, Object>> customerForSingle = customerMapper.getCustomerForSingle();

        Date date = new Date(System.currentTimeMillis());
        log.info("{}：开始执行{}（{}）调度", date, "旺小宝","pushCustomerToWxb");
        if (customerForSingle.size() > 0){
            for (int i = 0; i <  customerForSingle.size(); i++) {
                BWxbInfoLog bWxbInfoLog = new BWxbInfoLog();


                    String ts = System.currentTimeMillis()/1000 + "";
                    String nonce = UUID.randomUUID().toString().replace("-","");

                    StringBuilder stringBuilder = new StringBuilder("ak=").append(ak)
                            .append("&sk=").append(sk)
                            .append("&timestamp=").append(ts)
                            .append("&nonce=").append(nonce);

                    String sign = SecureUtil.md5(stringBuilder.toString());

                    Map<String,Object> params = customerForSingle.get(i);

                    bWxbInfoLog.setId(nonce);
                    bWxbInfoLog.setProjectId(       params.get("projectId")!=null?params.get("projectId").toString():null);
                    bWxbInfoLog.setAgent(           params.get("agent")!=null?params.get("agent").toString():null);
                    bWxbInfoLog.setChannelCompany(  params.get("channelCompany")!=null?params.get("channelCompany").toString():null);
                    bWxbInfoLog.setFinishNo(        params.get("finishNo")!=null?params.get("finishNo").toString():null);
                    bWxbInfoLog.setSubscriptionTime(params.get("subscriptionTime")!=null?params.get("subscriptionTime").toString():null);
                    bWxbInfoLog.setFinishTime(      params.get("finishTime")!=null ? params.get("finishTime").toString():null);
                    bWxbInfoLog.setFirstReadTime(   params.get("firstReadTime")!=null?params.get("firstReadTime").toString():null);
                    bWxbInfoLog.setIdNumber(        params.get("idNumber")!=null?params.get("idNumber").toString():null);
                    bWxbInfoLog.setMobile(          params.get("mobile")!=null?params.get("mobile").toString():null);
                    bWxbInfoLog.setName(            params.get("name")!=null?params.get("name").toString():null);
                    bWxbInfoLog.setReportTime(      params.get("reportTime")!=null?params.get("reportTime").toString():null);
                    bWxbInfoLog.setSalerName(       params.get("salerName")!=null?params.get("salerName").toString():null);
                    bWxbInfoLog.setOrderGUID(       params.get("orderGUID")!=null?params.get("orderGUID").toString():null);
                    bWxbInfoLog.setContractGUID(    params.get("contractGUID")!=null?params.get("contractGUID").toString():null);
                    params.put("ak",ak);
                    params.put("ts",ts);
                    params.put("nonce",nonce);
                    params.put("sign",sign);
                    Object toJSON = JSONObject.toJSON(params);
                    String url = baseUrl ;
                try {
                    log.info(date+"：旺小宝请求地址："+ url);
                    log.info(date+"：旺小宝请求参数：" + toJSON.toString());
                    Object result = HttpsUtil.doPost(url, toJSON);
                    log.info(date+"：旺小宝返回参数：" + result);
                    JSONObject jsonObject = JSONObject.parseObject(result.toString());
                    String  code = String.valueOf(jsonObject.get("code"));

                    bWxbInfoLog.setErrorMsg(jsonObject.get("msg").toString());
                    if (!"0".equals(code)){
                        bWxbInfoLog.setStatus(1);
                        log.error(date+"：pull wxb error,添加失败，错误描述：" + jsonObject.get("msg"));
                    }else{
                        bWxbInfoLog.setStatus(0);
                    }
                    bWxbInfoLogMapper.insert(bWxbInfoLog);
                } catch (Exception e) {
                    bWxbInfoLog.setStatus(2);
                    bWxbInfoLog.setErrorMsg("异常");
                    bWxbInfoLogMapper.insert(bWxbInfoLog);
                    e.printStackTrace();
                }
            }
        }else {
            log.warn(date+"：warning:查询不到客户信息" ) ;
        }
        log.info("{}：{}（{}）调度执行结束", date, "旺小宝","pushCustomerToWxb");
    }



}
