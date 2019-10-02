package com.ntuc.income.edgeservice;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {
  @Bean
  RateLimiter rateLimiter() {
    return RateLimiter.create(1.0D / 10.0D);
  }

  @Bean
  RateLimitingFilter rateLimitingFilter(){
    return new RateLimitingFilter();
  }
}
