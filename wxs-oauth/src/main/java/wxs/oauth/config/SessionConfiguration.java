package wxs.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import wxs.oauth.interceptor.SessionInterceptor;

@Configuration
// web配置文件（web.xml）添加一个拦截器
public class SessionConfiguration implements WebMvcConfigurer   {


    @Autowired
    SessionInterceptor sessionInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry ){
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
        //网站配置生成器：添加一个拦截器，拦截路径为整个项目
    }


}
