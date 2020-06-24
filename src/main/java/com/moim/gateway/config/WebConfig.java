package com.moim.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig.java
 * 
 * @author cdssw
 * @since 6. 24, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 6. 24, 2020    cdssw            최초 생성
 * </pre>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*"); // method 허용
	}
}
