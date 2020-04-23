package com.tahoecn.xkc.service.sys.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tahoecn.core.json.JSONResult;
import com.tahoecn.xkc.mapper.sys.SysAccessManagementMapper;
import com.tahoecn.xkc.model.sys.SysAccessManagement;
import com.tahoecn.xkc.service.sys.ISysAccessManagementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2020-03-31
 */
@Service
public class SysAccessManagementServiceImpl extends ServiceImpl<SysAccessManagementMapper, SysAccessManagement> implements ISysAccessManagementService {

    @Resource
    SysAccessManagementMapper sysAccessManagementMapper;

    @Override
    public JSONResult handlerIpVerification(HttpServletRequest request) throws Exception {
        boolean ifAllow = false;
        // 获取请求ip
        String remoteAddr = getIpAddress(request);
        // 获取系统允许ip配置信息
        List<SysAccessManagement> sysAccessManagementList = sysAccessManagementMapper.getAll();
        if (null != sysAccessManagementList && sysAccessManagementList.size() > 0) {
            for (SysAccessManagement sysAccessManagement : sysAccessManagementList) {
                if (StringUtils.isNotEmpty(sysAccessManagement.getFdAllowIp())) {
                    String ipStr = sysAccessManagement.getFdAllowIp();
                    if (ipStr.indexOf(",") > 0) {
                        String[] ipArray = ipStr.split(",");
                        for (int i = 0; i < ipArray.length; i++) {
                            if (ipArray[i].equals(remoteAddr)) {
                                ifAllow = true;
                                break;
                            }
                        }
                    } else if (ipStr.indexOf("-") > 0) {
                        ifAllow = ipIsValid(ipStr, remoteAddr);
                    } else if (ipStr.equals(remoteAddr)) {
                        ifAllow = true;
                    }
                }
            }
        } else {
            ifAllow = true;
        }
        // 返回ip验证结果
        if (!ifAllow) {
            return new JSONResult(1, "此" + remoteAddr + "地址不允许访问");
        }
        return null;
    }

    /**
     * 获取请求ip地址
     *
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断IP是否在指定网段
     *
     * @param ipSection
     * @param ip
     * @return
     */
    private static boolean ipIsValid(String ipSection, String ip) {
        ipSection = ipSection.trim();
        ip = ip.trim();
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP)) {
            return false;
        }
        int idx = ipSection.indexOf('-');
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }
}
