package com.moim.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * ErrorFilter.java
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
@Slf4j
public class ErrorFilter extends ZuulFilter {
 
    @Override
    public String filterType() {
        return "error";
    }
 
    @Override
    public int filterOrder() {
        return 1;
    }
 
    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }
 
    @Override
    public Object run() {
        Throwable throwable = RequestContext.getCurrentContext().getThrowable();
        log.error("Exception was thrown in filters: ", throwable);
        return null;
    }
}
