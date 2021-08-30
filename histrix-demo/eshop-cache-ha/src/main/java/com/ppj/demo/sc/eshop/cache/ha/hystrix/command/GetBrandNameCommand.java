package com.ppj.demo.sc.eshop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.ppj.demo.sc.eshop.cache.ha.local.BrandCache;

/**
 *
 * @author pipi
 * @since 2021/8/24 20:40
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetBrandNameCommandGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)
                )
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetBrandNameThreadPool"))
        );
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        throw new Exception("服务调用失败!");
    }

    @Override
    protected String getFallback() {
//        System.out.println("fallback方法, 从本地缓存中获取数据, brandId: " + brandId);
        return BrandCache.getBrandName(brandId);
    }
}
