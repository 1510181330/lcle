package com.example.maven.word.Config;

import com.example.maven.word.Handler.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    //添加视图管理器，对于网页首页，也就是登录页面进行配置映射
    //WebMvcConfigurerAdapter 内部含有众多空方法，可以配置资源映射，路径映射，拦截器， 类型转换器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("person/index");
    }

    //将拦截器添加到自动配置类中，并制定拦截什么样的请求，不拦截什么样的请求
    //值得注意的是springboot已经制定好了静态资源的映射规则，不必刻意放行这类请求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/userlogin", "/hello");
    }
}
