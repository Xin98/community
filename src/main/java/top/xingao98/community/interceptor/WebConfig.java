package top.xingao98.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by xinGao 2020/3/16
 */

@Configuration
//使用该注解会拦截静态文件，这个以后再剖析源码!
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SessionIntercepor sessionIntercepor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionIntercepor).addPathPatterns("/**");
    }

}