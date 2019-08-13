package com.tahoecn.xkc.interceptor;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.landray.sso.client.EKPSSOClient;

/**
 * Created by zhanghw on 2018/10/11.
 */
/**
 * @ClassName WebSSOConfig
 * @author wang_sd
 * @date 2019年4月15日
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebSSOConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	SsoInterceptor ssoInterceptor(){
		return new SsoInterceptor();
	}

	@Bean
	JwtInterceptor applInterceptor(){
		return new JwtInterceptor();
	}


	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(ssoInterceptor()).addPathPatterns("/webapi/**");
		registry.addInterceptor(applInterceptor()).addPathPatterns("/app/**").excludePathPatterns("/app/login/**","/app/system/mSystemAppVersion_Select","/app/system/mVerificationCode_Select","/app/login/mChannelRegistJZ_Insert");

	}

	/**
	 * 跨域拦截
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/app/**").allowedOrigins("*");
		registry.addMapping("/H5/**").allowedOrigins("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").allowedHeaders("*");
	}
}
