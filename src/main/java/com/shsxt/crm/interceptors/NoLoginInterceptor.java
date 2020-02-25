package com.shsxt.crm.interceptors;

import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取cookie  解析用户id
         *    如果用户id存在 并且 数据库存在对应用户记录  放行  否则进行拦截 重定向到登录
         */
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);

        if (userId==0||userService.selectByPrimaryKey(userId)==null){

            response.sendRedirect(request.getContextPath()+"/index");

            return false;
        }

        return true;
    }
}
