package com.ppj.sc.demo.eureka.server.service;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author pipi
 * @since 2021/8/20 11:42
 */
@Configuration
public class ServiceA implements IServiceA {


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        System.out.println("call new RestTemplate()");
        return new RestTemplate();
    }

    @Override
    public String sayHello(String name) {
        RestTemplate restTemplate = getRestTemplate();
        // http://192.168.31.107:8080/sayHello/leo
        return restTemplate.getForObject("http://ServiceA/sayHello/" + name, String.class);
    }
}
