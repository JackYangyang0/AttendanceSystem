package com.cjj.attendance.config;

import com.cjj.attendance.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    /*
        首页控制器
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
    }


    public void addInterceptors(InterceptorRegistry registry){
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/student/**","/teacher/**" , "/attendance/**");
        registration.excludePathPatterns(                         //添加不拦截路径
                "/login.html",
                "/layui",
                "/css",
                "/img",
                "/user/**"
        );
    }

}
