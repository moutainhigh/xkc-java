package com.tahoecn.xkc.config;

import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.Query;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: 风控组件创建
 * @author: 张晓东
 * @time: 2020/4/29 17:30
 */
@Component
public class TomcatPort {

    private static final Log log = LogFactory.get();

    public int getHttpPort() {
        try {
            MBeanServer server;
            if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
                server = MBeanServerFactory.findMBeanServer(null).get(0);
            } else {
                log.error("no MBeanServer!");
                return -1;
            }

            Set names = server.queryNames(new ObjectName("Catalina:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));

            Iterator iterator = names.iterator();
            if (iterator.hasNext()) {
                ObjectName name = (ObjectName) iterator.next();
                return Integer.parseInt(server.getAttribute(name, "port").toString());
            }
        } catch (Exception e) {
            log.error("getHttpPort", e);
        }
        return -1;
    }

}
