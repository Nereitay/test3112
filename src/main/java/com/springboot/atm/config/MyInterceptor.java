package com.springboot.atm.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginAccount = request.getSession().getAttribute("loginAccount");
        if (loginAccount == null) {
            request.setAttribute("meg", "please login");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        RequestUtil.setRequest(request);
        return true;
    }
}
