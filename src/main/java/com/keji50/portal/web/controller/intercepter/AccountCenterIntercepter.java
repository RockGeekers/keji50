package com.keji50.portal.web.controller.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.keji50.portal.common.utils.constants.Constants;

/**
 * 用户中心拦截器， 拦截用户中心相关请求， 判断用户是否登录
 * 
 * @author sophia
 * @version
 * @since Ver 1.1
 * @Date 2016年1月23日 下午4:40:16
 * 
 * @see
 */
public class AccountCenterIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute(Constants.SESSION_LOGIN_USER);
        if (loginUser == null) {
            response.sendRedirect("/account/sign_in");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
