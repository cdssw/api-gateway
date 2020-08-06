package com.moim.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfig.java
 * 
 * @author cdssw
 * @since 2020. 5. 15.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 5. 15.   cdssw            최초 생성
 * </pre>
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String[] WHITE_LIST = {
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/webjars/**",
			"/h2-console/**",
			"/*/v2/api-docs",
			"/actuator/**"
	};
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(WHITE_LIST).permitAll() // white list만 허용
			.antMatchers(HttpMethod.POST, "/user/signup/").permitAll() // 회원가입 허용
			.antMatchers(HttpMethod.GET, "/user/check/**").permitAll() // 회원가입용 체크는 허용
			.antMatchers(HttpMethod.POST, "/file/avatar").permitAll() // 회원가입용 체크는 허용
			.anyRequest().authenticated(); // 그 외 요청은 access_token이 있어야 가능
	}
}
