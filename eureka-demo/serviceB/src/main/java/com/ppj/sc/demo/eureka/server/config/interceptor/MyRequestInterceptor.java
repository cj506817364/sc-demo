package com.ppj.sc.demo.eureka.server.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author pipi
 * @since 2021/8/20 12:30
 */
public class MyRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println(requestTemplate);
    }
}
