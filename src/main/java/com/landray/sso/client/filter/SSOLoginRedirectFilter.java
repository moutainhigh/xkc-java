/**
 * 
 */
package com.landray.sso.client.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.landray.sso.client.EKPSSOChain;
import com.landray.sso.client.EKPSSOContext;

/**
 * 单点用过过滤重构
 * 
 * @ClassName SSOLoginFilter
 * @author wang_sd
 * @date 2019年4月15日
 */
public class SSOLoginRedirectFilter extends AbstractFilter {

	@Override
	public void doFilter(EKPSSOContext context, EKPSSOChain chain) throws IOException, ServletException {
		HttpServletResponse response = context.getResponse();

		String username = context.getCurrentUsername();
		boolean needRedirect = StringUtils.isEmpty(username);
		if (needRedirect) {
			response.sendError(401, "获取授权失败");
			return;
		}
		chain.doNextFilter();
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(Properties arg0) throws ServletException {
		
	}

}
