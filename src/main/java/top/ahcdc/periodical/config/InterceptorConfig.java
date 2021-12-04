package top.ahcdc.periodical.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ahcdc.periodical.interceptors.JWTInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
            "/userlogin",
            "/userlogin/**",
            "/"
    };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
