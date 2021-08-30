package com.ppj.sc.demo.eureka.server.client;

import com.ppj.sc.demo.eureka.server.config.MyConfiguration;
import com.ppj.sc.demo.eureka.service.api.ServiceAInterface;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author pipi
 * @since 2021/8/20 11:45
 */
@FeignClient(value = "ServiceA", configuration = MyConfiguration.class
        , fallbackFactory = ServiceAClinetFallbackFactory.class
//        , fallback = ServiceAClinetFallback.class
)
public interface ServiceAClient extends ServiceAInterface {

}
