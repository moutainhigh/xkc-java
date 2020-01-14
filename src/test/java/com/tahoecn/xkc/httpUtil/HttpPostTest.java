package com.tahoecn.xkc.httpUtil;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.http.HttpUtil;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.xkc.common.utils.HttpsUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description TODO
 * @Author zefuw
 * @DATE 2020/1/8 14:41
 **/
public class HttpPostTest {



    public static void main(String[] args) {
        String base_uri = "https://api-test-e.source3g.com/third-face-detect/";
        String url =base_uri +  "foreign/face/customer/add";
        String projectId = "88889996336";
        System.out.println(url);
        String ak = "08e54469fabf4605aa99d3c9d14860a9";
        String sk = "c9d2500b7515455aa3faf6b1aafb6f74";
        String ts = System.currentTimeMillis()/1000 + "";
        String nonce = UUID.randomUUID().toString().replace("-","");
        StringBuilder stringBuilder = new StringBuilder("ak=").append(ak)
                .append("&sk=").append(sk)
                .append("&timestamp=").append(ts)
                .append("&nonce=").append(nonce);

        System.out.println(stringBuilder.toString());
        String sign = SecureUtil.md5(stringBuilder.toString());
        System.out.println(sign);

        Map<String,Object> params = new HashMap<>();

        params.put("ak",ak);
        params.put("ts",ts);
        params.put("nonce",nonce);
        params.put("sign",sign);
        params.put("projectId",projectId);
        params.put("name","姓名");
        params.put("idNumber","352202198708124321");
        params.put("channelCompany","渠道公司");
        params.put("mobile","18549308775");
        params.put("salerName","置业顾问");
        params.put("agent","经纪人");
        params.put("firstReadTime","2019-12-12 12:00:00");
        params.put("finishTime","2019-12-13 12:00:00");
        params.put("finishNo","房间号");
        params.put("reportTime","2019-12-11 12:00:00");

        Object toJSON = JSONObject.toJSON(params);
        System.out.println(toJSON.toString());
        Object result = HttpsUtil.doPost(url, toJSON);
        System.out.println(result.toString());

    }


}
