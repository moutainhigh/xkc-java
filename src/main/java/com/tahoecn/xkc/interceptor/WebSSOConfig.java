package com.tahoecn.xkc.interceptor;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
	 * //使用此方法配置之后再使用自定义拦截器时跨域相关配置就会失效,所以用下面的corsConfig
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/H5/**").allowedOrigins("http://10.31.29.174:8080");
	}

	/*private CorsConfiguration corsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		*//*
    		* 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
    	*//*
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setMaxAge(3600L);
		return corsConfiguration;
	}
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig());
		return new CorsFilter(source);
	}*/
}
