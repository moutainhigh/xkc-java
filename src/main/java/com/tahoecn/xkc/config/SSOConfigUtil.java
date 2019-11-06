/**
 * 
 */
package com.tahoecn.xkc.config;

import com.tahoecn.uc.sso.SSOConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version
 * @since 2019年5月27日 下午2:14:40
 */
@Configuration
public class SSOConfigUtil {

    @Value("${sso.loginUrl}")
    private String loginUrl;
    
    @Value("${sso.logoutUrl}")
    private String logoutUrl;
    
    @Value("${sso.ucwebUrl}")
    private String ucwebUrl;
    
    @Value("${sso.cookieDomain}")
    private String cookieDomain;
    
    @Value("${sso.ucssowebUrl}")
    private String ucssowebUrl;
    
    @Bean
    public SSOConfig ssoConfig() {
        SSOConfig ssoConfig = new SSOConfig();
        ssoConfig.setCookieDomain(cookieDomain);
        ssoConfig.setLoginUrl(loginUrl);
        ssoConfig.setLogoutUrl(logoutUrl);
        ssoConfig.setUcwebUrl(ucwebUrl);
        ssoConfig.setUcssowebUrl(ucssowebUrl);
        return SSOConfig.init(ssoConfig);
    }
}
