package com.tahoecn.xkc.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.tahoecn.xkc.common.constants.GlobalConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class RqCodeUtils {


    private static String appId;
    //private String appId = "wxddcffaa0ba4efe75";

    private static String appSecret;

    public String getAppId() {
        return appId;
    }

    @Value("${APP_ID}")
    public void setAppId(String appId) {
        RqCodeUtils.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    @Value("${APP_SECRET}")
    public void setAppSecret(String appSecret) {
        RqCodeUtils.appSecret = appSecret;
    }

    @Value("${tahoe.application.physicalPath}")
    private static String physicalPath;

    /**
     * 获取 token
     * 普通的 get 可获 token
     */
    public static String getToken() {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("grant_type", "client_credential");
            map.put("appid", appId);
            map.put("secret",appSecret);

            String rt = UrlUtil.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);

            JSONObject json = JSONObject.parseObject(rt);

            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                return json.getString("access_token");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /*
     * 获取 二维码图片
　　 *
     */
    public static String getminiqrQr(String accessToken, int width) {
//        String p = request.getSession().getServletContext().getRealPath("/");
        String codeUrl = physicalPath + "rqcodeImg" + File.separator;
        String codeFile = physicalPath + "rqcodeImg" + File.separator + "twoCode.png";
        String twoCodeUrl = "/twoCode.png";
        try {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", "1234567890");//todo 参数
//            paramJson.put("path", activityId);
            paramJson.put("width", width);
            paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            File logoSaveFile = new File(codeUrl);
            if (!logoSaveFile.exists()){
                logoSaveFile.mkdirs();
            }
            OutputStream os = new FileOutputStream(codeFile);
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return twoCodeUrl;
    }
}
