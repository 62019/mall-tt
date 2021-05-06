package com.example.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/*
3
验证用户是否有权限
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //请求的资源，从数据源筛选得到的
        if(collection==null)return;

        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()){
            ConfigAttribute next = iterator.next();
            String attribute = next.getAttribute();
            //获得该用户能访问的资源
//            for (GrantedAuthority authority : authentication.getAuthorities()) {
//                if(attribute.equals((String)authority.getAuthority())) System.out.println();
//
//            }

            /*
            authentication.getAuthorities() 这个是用户有的权限
            attribute 需要的权限
             */


            //这个用户拥有的所有权限   和 请求的权限对比
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                //.trim去掉空格
                if (attribute.trim().equals(grantedAuthority.getAuthority())) {
                    System.out.println("e");
                    return;
                }
            }
            throw new AccessDeniedException("抱歉，您没有访问权限");

        }


    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}