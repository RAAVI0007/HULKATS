package com.web.hulklogic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebConfig {
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	            	registry.addMapping("/rest/**")
	        		.allowedOrigins("*")
	        		.allowedMethods("*")
	        			.allowedHeaders("*")
	        		.exposedHeaders("*")
	        		.allowCredentials(false).maxAge(3600);
	            }
	        };
	    }
}
