package com.ppj.sc.demo.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author pipi
 * @since 2021/8/17 18:29
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaServerA {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerA.class, args);
    }
}


