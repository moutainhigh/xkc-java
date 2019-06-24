package com.tahoecn.xkc.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tahoecn.security.SecureUtil;
import com.tahoecn.uc.sso.SSOConfig;
import com.tahoecn.uc.sso.SSOHelper;
import com.tahoecn.uc.sso.annotation.Action;
import com.tahoecn.uc.sso.annotation.Login;
import com.tahoecn.uc.sso.common.CookieHelper;
import com.tahoecn.uc.sso.security.token.SSOToken;
import com.tahoecn.uc.sso.utils.LtpaToken;
import com.tahoecn.uc.sso.web.interceptor.SSOSpringInterceptor;
import com.tahoecn.xkc.model.sys.SAccount;
import com.tahoecn.xkc.service.sys.ISAccountService;
import com.tahoecn.xkc.service.uc.CsUcUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.tahoecn.xkc.common.utils.ThreadLocalUtils;
import com.tahoecn.xkc.model.CsUcUser;

/**
 * 全局的拦截器
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class SsoInterceptor extends SSOSpringInterceptor {

	@Autowired
	private CsUcUserService csUcUserService;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	private ISAccountService accountService;

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

		if ((handler instanceof HandlerMethod)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Login login = (Login) method.getAnnotation(Login.class);
			if ((login != null) && (login.action() == Action.Skip)) {
				return true;
			}
		}
		SSOToken ssoToken = SSOHelper.getSSOToken(request);
		if (ssoToken == null) {
			try {
				if (!request.getRequestURI().contains("/webapi")) {
					if (getHandlerInterceptor().preTokenIsNull(request, response)) {
						SSOHelper.clearRedirectLogin(request, response);
					}
					return false;
				}
				sendError(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		String LtpaTokenCookie = CookieHelper.getCookie(request, SSOConfig.getInstance().getOaCookieName());
		if ((StringUtils.isEmpty(LtpaTokenCookie)) || (LtpaTokenCookie.length() < 10)) {
			String tokenValue = LtpaToken.generateTokenByUserName(ssoToken.getIssuer(),
					String.valueOf(SSOConfig.getInstance().getExpireT()), SSOConfig.getInstance().getOatokenKey());
			String domain = SSOConfig.getInstance().getCookieDomain();

			response.addHeader("Set-Cookie", SSOConfig.getInstance().getOaCookieName() + "=" + tokenValue + ";Domain="
					+ domain + "; Path=" + SSOConfig.getInstance().getCookiePath());
		}
		request.setAttribute("ucssoTokenAttr", ssoToken);

		// 单点登陆用户过滤
		Optional<SSOToken> sso = Optional.ofNullable(SSOHelper.attrToken(request));
		String loginName = sso.map(SSOToken::getIssuer).orElse(null);

		if (StringUtils.isNotBlank(loginName)) {
			SAccount csUcUser = (SAccount) redisTemplate.opsForValue().get("asdasd1z");	//YYY:todo
			if (csUcUser == null) {
				QueryWrapper<SAccount> wrapper = new QueryWrapper<>();
				wrapper.lambda().eq(SAccount::getStatus, 1);
				wrapper.lambda().eq(SAccount::getIsDel, 0);
				wrapper.lambda().eq(SAccount::getUserName, loginName);
				csUcUser = accountService.getOne(wrapper);
				if (csUcUser == null) {
					sendError(response);
					return false;
				}
				redisTemplate.opsForValue().set(loginName, csUcUser, 2, TimeUnit.HOURS);
			}
			ThreadLocalUtils.setUser(csUcUser);
			return true;
		}
		sendError(response);
		return false;
	}

	private void sendError(HttpServletResponse response) {
		try {
			response.sendError(401, "获取授权失败");
		} catch (IOException e) {
			e.printStackTrace();
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
