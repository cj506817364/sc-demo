package com.ppj.sc.demo.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author pipi
 * @since 2021/8/17 18:29
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class EurekaServerB {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerB.class, args);
    }

}


