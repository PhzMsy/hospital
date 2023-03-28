package org.msy.hospital.dict.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 11612
 * @date 2023/3/23
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
// 允许任何请求访问
        registry.addMapping("/**")
// 任何原始域放行
                .allowedOriginPatterns("*")
// 是否发送发送Cookie
                .allowCredentials(true)
// 允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
// 任何头信息
                .allowedHeaders("*")
                .maxAge(3600 * 24);
    }
}

