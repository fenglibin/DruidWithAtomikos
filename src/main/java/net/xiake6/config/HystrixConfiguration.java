package net.xiake6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

@Configuration
public class HystrixConfiguration {

  @Bean
  public HystrixCommandAspect hystrixCommandAspect() {
    return new HystrixCommandAspect();
  }

}
