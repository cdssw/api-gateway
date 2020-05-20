package com.moim.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig.java
 * 
 * @author cdssw
 * @since Apr 10, 2020
 * @description Swagger 설정 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 10, 2020   cdssw            최초 생성
 * </pre>
 */
@Profile("!prod") // production은 제외
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
				.paths(PathSelectors.any())
				.build();
	}
}
