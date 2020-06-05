package com.moim.gateway.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * PreFilter.java
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
public class PreFilter extends ZuulFilter {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		// 요청에 인증정보가 있으면 username header를 추출하여 서비스쪽으로 전달
		if(request.getHeader("Authorization") != null) {
			String username = getUsername(request);
			context.addZuulRequestHeader("username", username);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	// access_token에서 username 추출
	private String getUsername(HttpServletRequest request) {
		Jwt jwt = JwtHelper.decode(request.getHeader("Authorization").split(" ")[1]);
		String claims = jwt.getClaims();
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
		
		try {
			Map<String, Object> data = objectMapper.readValue(claims, typeRef);
			return data.get("user_name").toString();
		} catch (Exception e) {
			throw new InvalidTokenException("Cannot convert access token to JSON");
		}
	}
}
