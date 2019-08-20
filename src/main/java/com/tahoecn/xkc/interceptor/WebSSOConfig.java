package com.tahoecn.xkc.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
		registry.addInterceptor(applInterceptor()).addPathPatterns("/app/**").excludePathPatterns("/app/login/**","/app/system/mSystemAppVersion_Select","/app/system/mVerificationCode_Select","/app/login/mChannelRegistJZ_Insert",
				"/app/system/SystemAD_Select","/app/user/mUserForgetPwd_Update","/app/partReceive/mCustomerFJSearch_Select","/H5/mVerificationCode_Select","/H5/mLoginTK_SelectN",
				"/app/potenCust/mCustomerPotentialZQDetail_Insert");

	}

	/**
	 * 跨域拦截
	 * //使用此方法配置之后再使用自定义拦截器时跨域相关配置就会失效,所以用下面的corsConfig

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/H5/**").allowedOrigins("*");
	}*/

	/**/
	private CorsConfiguration corsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
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
		source.registerCorsConfiguration("/H5/**", corsConfig());
		return new CorsFilter(source);
	}
}
