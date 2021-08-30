package com.ppj.demo.sc.eshop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.ppj.demo.sc.eshop.cache.ha.local.LocationCache;

/**
 * @author pipi
 * @since 2021/8/24 20:40
 */
public class GetCityNameCommand extends HystrixCommand<String> {
    private Long cityId;

    public GetCityNameCommand(Long cityId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)
                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetCityNameThreadPool"))
        );
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        final String cityName = LocationCache.getCityName(cityId);
//        System.out.println(cityName);
        return cityName;
    }
}
