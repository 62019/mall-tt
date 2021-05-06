package com.example.mall.admin.config;


import com.example.mall.admin.entity.SysResource;
import com.example.mall.admin.service.SysAdminService;
import com.example.mall.admin.service.SysResourceService;
import com.example.security.SecurityConfig;
import com.example.security.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Hutengfei
 * @Description:
 * @Date Create in 2019/8/29 14:36
 */

//不加component报错
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig extends SecurityConfig {
    @Autowired
    SysAdminService adminService;

    @Autowired
    SysResourceService resourceService;


    //进入容器
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserDetail(username);
    }
//    通过这个进容器内的
//    這個先進容器
    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<SysResource> resourceList = resourceService.list();
                for (SysResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
    //这设计有点奇怪 为什么登陆一个用户 要先查权限在登陆 ， 可能是这不只是登陆验证

}
