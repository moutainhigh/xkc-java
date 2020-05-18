package com.tahoecn.xkc.common.utils;

import com.tahoecn.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class NetUtil {
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StrUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StrUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     *
     * @description: 获取id去重 + 去127.0.0.1
     * @return:
     * @author: 张晓东
     * @time: 2020/5/18 13:12
     */
    public static String getRemoteAddr2(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        if (StringUtils.isNotEmpty(remoteAddr)) {
            return Arrays.stream(remoteAddr.split(","))
                    .map(String::trim)
                    .filter(i -> !i.equals("127.0.0.1"))
                    .collect(Collectors.toSet()).stream()
                    .collect(Collectors.joining(","));
        }
        return "";
    }



}
