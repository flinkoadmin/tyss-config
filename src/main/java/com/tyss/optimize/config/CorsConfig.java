package com.tyss.optimize.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean customCorsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000","http://localhost","http://localhost:80","http://10.10.10.131:3000","http://10.10.10.131","http://10.10.10.131:80","http://10.10.10.82", "http://10.10.10.82:80","http://10.10.10.89", "http://10.10.10.89:80","http://test-firecrowd.fireflink.com", "http://dev-firecrowd.fireflink.com","https://app.firecrowd.com","http://app.firecrowd.com","http://111.93.145.51","http://10.10.10.237","https://backend1.fireflink.com"));
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        var bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
