package com.moim.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorFilter extends ZuulFilter {
 
    private final Logger log = LoggerFactory.getLogger(getClass());
    
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
