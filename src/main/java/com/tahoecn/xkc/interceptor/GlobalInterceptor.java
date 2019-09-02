package com.tahoecn.xkc.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tahoecn.xkc.service.sys.ISLogsService;
import com.tahoecn.xkc.service.uc.CsUcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;

/**
 * 全局的拦截器
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	private static final Log log = LogFactory.get();

	@Autowired
	private CsUcUserService csUcUserService;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	private ISLogsService iSLogsService;

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
		log.info("【1】在处理请求之前要做的动作");

		Map<String, String> map = Maps.newHashMap();
		String url = request.getRequestURL().toString();
		log.info("url:" + url);

		// headers
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String parameter = headers.nextElement();
			//map.put(parameter, request.getHeader(parameter));
			log.info(parameter + ":" + request.getHeader(parameter));
		}

		// parameters
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String parameter = enumeration.nextElement();
			map.put(parameter, request.getParameter(parameter));
			log.info("parameters-" + parameter + ":" + request.getParameter(parameter));
		}

		log.info("=====================================================================");

		RequestWrapper requestWrapper = new RequestWrapper(request);
		String body = requestWrapper.getBody();
		try {
			Map<String,Object> logMap = new HashMap<String,Object>();
			logMap.put("BizID", "xkc");
			logMap.put("BizType", "FuncRequestData");
			logMap.put("BizDesc", "方法请求数据");
			logMap.put("Ext1", url.substring(url.lastIndexOf("/"),url.length()));
			logMap.put("Ext2", url);
			if (map.size() == 0)
				logMap.put("Data", body);
			else
				logMap.put("Data", map.toString()+"-"+body);
			iSLogsService.SystemLogsDetail_Insert(logMap, request);
		}catch (Exception e){
			e.printStackTrace();
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
