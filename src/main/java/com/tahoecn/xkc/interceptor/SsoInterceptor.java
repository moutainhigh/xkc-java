package com.tahoecn.xkc.interceptor;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tahoecn.xkc.service.uc.CsUcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.landray.sso.client.EKPSSOContext;
import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.model.CsUcUser;

/**
 * 全局的拦截器
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class SsoInterceptor implements HandlerInterceptor {

	@Autowired
	private CsUcUserService csUcUserService;

	@Autowired
	RedisTemplate redisTemplate;

	/**
	 * 在处理请求之前要做的动作
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		EKPSSOContext context = EKPSSOContext.getInstance();
		if (context != null) {
			String username = context.getCurrentUsername();
			System.out.println(username);
			CsUcUser csUcUser = (CsUcUser) redisTemplate.opsForValue().get(username);
			if (csUcUser == null) {
				csUcUser = csUcUserService.selectByUsername(username);
				redisTemplate.expire(username, 2, TimeUnit.HOURS); // Redis 用户 有效期2小时
				redisTemplate.opsForValue().set(username, csUcUser);
			}
			ThreadLocalUtils.setUser(csUcUser);
		}

		return true;
	}

	/**
	 * 完成请求处理后要做的动作
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}

	/**
	 * 请求结束后要做的动作
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ThreadLocalUtils.remove();
	}

}
