package com.tahoecn.xkc.schedule;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.core.util.NetUtil;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.HttpsUtil;
import com.tahoecn.xkc.mapper.customer.BCustomerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Scheduled(cron = "0 0 16 * * ?")
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

        if (customerForSingle.size() > 0){
            for (int i = 0; i <  customerForSingle.size(); i++) {

                String ts = System.currentTimeMillis()/1000 + "";
                String nonce = UUID.randomUUID().toString().replace("-","");

                StringBuilder stringBuilder = new StringBuilder("ak=").append(ak)
                        .append("&sk=").append(sk)
                        .append("&timestamp=").append(ts)
                        .append("&nonce=").append(nonce);

                String sign = SecureUtil.md5(stringBuilder.toString());

                Map<String,Object> params = customerForSingle.get(i);
                params.put("ak",ak);
                params.put("ts",ts);
                params.put("nonce",nonce);
                params.put("sign",sign);

                Object toJSON = JSONObject.toJSON(params);
                String url = baseUrl ;
                log.info("旺小宝请求地址："+ url);
                log.info("旺小宝请求参数：" + toJSON.toString());
                Object result = HttpsUtil.doPost(url, toJSON);
                log.info("旺小宝返回参数：" + result);
                JSONObject jsonObject = JSONObject.parseObject(result.toString());
                String  code = String.valueOf(jsonObject.get("code"));
                if (!"0".equals(code)){
                    log.error("pull wxb error,添加失败，错误描述：" + jsonObject.get("msg"));
                }
            }
        }else {
            log.warn("warning:查询不到客户信息" );
        }

    }



}
