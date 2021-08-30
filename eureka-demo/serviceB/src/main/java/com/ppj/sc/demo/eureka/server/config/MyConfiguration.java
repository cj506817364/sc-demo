package com.ppj.sc.demo.eureka.server.config;

import com.ppj.sc.demo.eureka.server.config.interceptor.MyRequestInterceptor;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

/**
 * @author pipi
 * @since 2021/8/20 12:28
 */
public class MyConfiguration {

    @Bean
    public RequestInterceptor myRequestInterceptor() {
        return new MyRequestInterceptor();
    }

//    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }
}
