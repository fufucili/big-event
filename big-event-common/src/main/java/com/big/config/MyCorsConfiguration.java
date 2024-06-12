package com.big.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域问题
 *
 * 因为使用了拦截器，所以webConfig实现WebMvcConfigurer重写addCorsMappings方法不会起作用
 *
 * 原因：客户端请求经过先后顺序问题，当服务端收到一个请求，先走过滤器，再进拦截器，然后在走Mapping映射中的路径所指资源，
 * 所以跨域配置在mapping并不起作用，返回的头信息并没有配置跨域信息，浏览器就会报跨域异常
 *
 * @author TuYongbin
 * @Date 2024/5/16 16:36
 */
@Configuration
public class MyCorsConfiguration {

    private CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        return new CorsFilter(source);
    }
}
