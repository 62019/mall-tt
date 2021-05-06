package com.example.security.component;

import cn.hutool.core.util.URLUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
2
验证需要的权限是否在权限列表中（感觉多余）
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }



    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        if (configAttributeMap == null) this.loadDataSource();

        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();


//直接load出来了
//        Map<String, ConfigAttribute> configAttributeMap = new ConcurrentHashMap<>();
//        for (SysResource sysResource : list) {
//            //第二个？？
//            configAttributeMap.put(sysResource.getUrl(),new org.springframework.security.access.SecurityConfig(sysResource.getId() + ":" + sysResource.getName()));
//        }

        //@获得所有resource 和请求的资源对比 看看在不在里面
        Iterator<String> iterator = configAttributeMap.keySet().iterator();

        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));

            }
        }

//        获取当前访问的路径,需要的权限

        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}