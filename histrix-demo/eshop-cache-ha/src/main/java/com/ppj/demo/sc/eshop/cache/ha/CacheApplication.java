package com.ppj.demo.sc.eshop.cache.ha;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.ppj.demo.sc.eshop.cache.ha.filter.HystrixRequestContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pipi
 * @since 2021/8/24 19:23
 */
@Configuration
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean hystrixRequestContextFilter() {
        final FilterRegistrationBean hystrixFilterRegistrationBean = new FilterRegistrationBean(new HystrixRequestContextFilter());
        hystrixFilterRegistrationBean.addUrlPatterns("/*");
        return hystrixFilterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean indexServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/hystrix.stream");
        return registrationBean;
    }
}
