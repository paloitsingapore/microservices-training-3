package com.ntuc.income.edgeservice;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

public class RateLimitingFilter extends ZuulFilter {

  private static final HttpStatus tooMany = HttpStatus.TOO_MANY_REQUESTS;
  private static final Logger log = LoggerFactory.getLogger(RateLimitingFilter.class);

  @Autowired RateLimiter rateLimiter;

  @Override public String filterType() {
    return "pre";
  }

  @Override public int filterOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Override public boolean shouldFilter() {
    return true;
  }

  @Override public Object run() throws ZuulException {
    try {
      RequestContext currentContext = RequestContext.getCurrentContext();
      HttpServletResponse response = currentContext.getResponse();
      response.reset();
      if (!rateLimiter.tryAcquire()) {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setStatus(tooMany.value());
        response.getWriter().append(tooMany.getReasonPhrase());
        currentContext.setSendZuulResponse(false);
        throw new ZuulException(tooMany.getReasonPhrase(),
            tooMany.value(), tooMany.getReasonPhrase());
      }
    } catch (Exception e) {
      //  ReflectionUtils.rethrowRuntimeException(e);
      log.error(e.getMessage());
      e.printStackTrace();
      ReflectionUtils.rethrowRuntimeException(e);
    }
    return null;
  }
}
