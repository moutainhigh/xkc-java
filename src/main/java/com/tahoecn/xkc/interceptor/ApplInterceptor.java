package com.tahoecn.xkc.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import com.tahoecn.xkc.common.constants.AesConstants;
import com.tahoecn.xkc.common.utils.AesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


/**
 * 全局的拦截器
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class ApplInterceptor implements HandlerInterceptor {

	private static final Log log = LogFactory.get();

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
		String headToken = request.getHeader("token");


		JSONObject token = JSON.parseObject(headToken);
		PrintWriter pw;
		try {

			if (headToken == null){
				pw = response.getWriter();
				pw.write("{\"code\":\"403\"}");
				pw.flush();
				pw.close();
				return false;
			}
			String checkStr = AesUtils.encrypt(token.get("openId").toString()+","+token.get("timeStr").toString(), AesConstants.AES_KEY);
			if (token.get("checkStr").toString().equals(checkStr)){
				boolean lock;
				lock = redisTemplate.opsForValue().setIfAbsent(token.get("checkStr").toString(), "1");
				redisTemplate.expire(token.get("checkStr").toString(), 1, TimeUnit.HOURS);
				if (lock){
					return true;
				}else{
					pw = response.getWriter();
					pw.write("{\"code\":\"403\"}");
					pw.flush();
					pw.close();
					return false;
				}
			}else{
				pw = response.getWriter();
				pw.write("{\"code\":\"403\"}");
				pw.flush();
				pw.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
		// log.info("【2】完成请求处理后要做的动作");
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
		// log.info("【3】请求结束后要做的动作");
	}

}
