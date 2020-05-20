package com.moim.gateway;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
/**
 * SwaggerConfig.java
 * 
 * @author cdssw
 * @since May 20, 2020
 * @description Swagger 설정 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 20, 2020   cdssw            최초 생성
 * </pre>
 */
@Profile("!prod") // production은 제외
@Component
@Primary // 우선순위 설정
@AllArgsConstructor
public class SwaggerController implements SwaggerResourcesProvider {

	private final RouteLocator routeLocator;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> swaggerResources = routeLocator.getRoutes().stream().distinct().map(route -> {
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(route.getLocation());
			swaggerResource.setLocation(route.getFullPath().replace("**", "v2/api-docs"));
			swaggerResource.setSwaggerVersion(SWAGGER_2.getVersion());
			return swaggerResource;
		}).collect(Collectors.toList());
		return swaggerResources;
	}

	
}
