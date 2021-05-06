package com.example.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
1
 */

public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    //    在哪用的
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;


    //??
    @Autowired
    public void setMyAccessDecisionManager(DynamicAccessDecisionManager dynamicAccessDecisionManager) {
        super.setAccessDecisionManager(dynamicAccessDecisionManager);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        问题所在
        return dynamicSecurityMetadataSource;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);


        //刚开始少了这句很麻烦 一直跨域失败 突然想起
        //OPTIONS请求直接放行
        if(request.getMethod().equals(HttpMethod.OPTIONS.toString())){
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }


        //白名单
        PathMatcher pathMatcher = new AntPathMatcher();
        if (pathMatcher.match("/sys/admin/login", request.getRequestURI())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        if (pathMatcher.match("/sys/admin/logout", request.getRequestURI())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        if (pathMatcher.match("/api/**", request.getRequestURI())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        if (pathMatcher.match("/home/**", request.getRequestURI())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }

        //调用里面的decide   查看用户是否有权限
            InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }





}
