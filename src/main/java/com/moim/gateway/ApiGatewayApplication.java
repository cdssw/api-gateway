package com.moim.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.moim.gateway.filter.PostFilter;
import com.moim.gateway.filter.PreFilter;
import com.moim.gateway.filter.RouteFilter;
import com.moim.gateway.filter.ErrorFilter;

/**
 * ApiGatewayApplication.java
 * 
 * @author cdssw
 * @since 2020. 6. 5.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 6. 5.    cdssw            최초 생성
 * </pre>
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	// preFilter 등록
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	
	// postFilter 등록
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	
	// routeFilter 등록	
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

	// errorFilter 등록	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

}
