package com.nchu.software.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //1. 设置哪些原始域
        config.addAllowedOriginPattern("*");
        // 是否允许发送Cookie信息
        config.setAllowCredentials(Boolean.TRUE);
        // 开放哪些Http方法，允许跨域访问
        config.addAllowedMethod("*");
        // 允许HTTP请求中的携带哪些Header信息
        config.addAllowedHeader("*");
        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("*");
        //2. 添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //3. 返回新的CorsFilter
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(corsConfigurationSource));
        // 在有其他拦截器的时候，通过bean.setOrder(0)，设置加载顺序
        // 设置为第一
        bean.setOrder(0);
        return bean;
    }
}
