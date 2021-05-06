package com.example.security;


import com.example.security.component.*;
import com.example.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        //不需要保护的资源路径允许访问    两个地方都要写看见没
//        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.antMatchers("/home/**").permitAll();
            registry.antMatchers("/cart/**").permitAll();
            registry.antMatchers("/sys/admin/login").permitAll();
            registry.antMatchers("/api/**").permitAll();

//        }
        //允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        // 任何请求需要身份认证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //有动态权限配置时添加动态权限校验过滤器
        if(dynamicSecurityService!=null){
            registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.cors();
//
////有个bug 权限不放表里面都可以访问
//
//
//        http.csrf()// 由于使用的是JWT，我们这里不需要csrf
////                .and().cors()
//                .disable()
//                .sessionManagement()// 基于token，所以不需要session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//
//                //在这写没用，写到 DynamicSecurityFilter 好像也要用 俩个地方都要写  是的
//
//                .authorizeRequests()
//                    .antMatchers("/sys/admin/login","/sys/admin/logout","/home/**")// 对登录注册要允许访问
//                    .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
//                .permitAll()
////                .antMatchers("/**")//测试时全部运行访问
////                .permitAll()
//                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
//                .authenticated()
//                .and().addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//
////        http.addFilterBefore(corsf2ilter, ChannelProcessingFilter.class);
////        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
//
//
//        http.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler())
//                .authenticationEntryPoint(restAuthenticationEntryPoint());
//
//        if (dynamicSecurityService != null) {
//            http.addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
//        }
//
//    }


    //松哥
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      BCryptPasswordEncoder      a 123
        auth.inMemoryAuthentication()
                .withUser("a")
                .password("$2a$10$Jfdb3OXIx8EvfSdQ.qbB.uZnFkgim5.ZbIlU9YJ.9PvNo0CZm3kJm").roles("cao");
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //?
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    //上面需要的 这个也要在容器里
    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }


    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }


    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    //
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }


}
