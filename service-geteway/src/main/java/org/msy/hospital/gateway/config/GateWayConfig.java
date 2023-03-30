package org.msy.hospital.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;


/**
 * @author 11612
 * @date 2023/3/29
 */
@Configuration
public class GateWayConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
// 跨域设置
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowCredentials(true);
// 注意引入的包
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}
