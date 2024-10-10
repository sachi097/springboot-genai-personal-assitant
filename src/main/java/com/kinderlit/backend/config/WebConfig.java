package com.kinderlit.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        long timeout = 60 * 60 * 1000;// for example 60 minutes
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
        configurer.setDefaultTimeout(timeout);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Replace with your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");

    }
}