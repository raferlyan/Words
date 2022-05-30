package com.raferlyan.words.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author raferlyan
 * @date 2022/5/30 13:54
 **/
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean corsFilter() {
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration().applyPermitDefaultValues();
        config.setAllowCredentials(false); // communicate without cookies
        config.setAllowedMethods(List.of("*")); // allow patch/delete/...
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // set to all paths
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
        }

}
