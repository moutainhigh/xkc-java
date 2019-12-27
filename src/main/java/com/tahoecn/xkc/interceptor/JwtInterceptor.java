package com.tahoecn.xkc.interceptor;

import com.tahoecn.xkc.common.enums.AppTokenStatus;
import com.tahoecn.xkc.common.exception.AppUserLoginException;
import com.tahoecn.xkc.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    /** redis 用户token前缀 */
    private static final String LOGIN_USER_ID_KEY="LOGIN_USER_ID_KEY";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 取得token
        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            throw new AppUserLoginException(AppTokenStatus.TOKEN_INEXISTENCE);
        }
        tokenHeader = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        Claims tokenBody = JwtTokenUtil.getTokenBody(tokenHeader);
        // 验证redis的token是否存在
        if (!judgeRedisTokenExpired(tokenBody.getId(), tokenHeader)){
            throw new AppUserLoginException(AppTokenStatus.TOKEN_EXPIRED);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    /**
     * 判断用户的token是否过期
     * @param userId
     *          用户ID
     * @return
     */
    private boolean judgeRedisTokenExpired(String userId, String token) {
        String key = LOGIN_USER_ID_KEY + "_" + userId;
        Object o = redisTemplate.opsForValue().get(key);
        if (o == null || !o.toString().equals(token)) {
            return false;
        }

        return true;
    }
}
