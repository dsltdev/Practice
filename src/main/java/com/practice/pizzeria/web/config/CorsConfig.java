package com.practice.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    //configuracion de dominions urls
    @Bean
    UrlBasedCorsConfigurationSource configurationSource(){
        CorsConfiguration configurationSource = new CorsConfiguration() ;

        configurationSource.setAllowedOrigins(List.of("http://localhost:4200"));
        configurationSource.setAllowedMethods(Arrays.asList("GET","POST"));
        configurationSource.setAllowedHeaders(List.of("*"));


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configurationSource);


        return source;
    }

}
