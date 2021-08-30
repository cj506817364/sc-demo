package com.ppj.demo.sc.eshop.cache.ha.hystrix.command;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.ppj.demo.sc.eshop.cache.ha.http.HttpClientUtils;
import com.ppj.demo.sc.eshop.cache.ha.local.LocationCache;
import com.ppj.demo.sc.eshop.cache.ha.model.ProductInfo;

/**
 * @author pipi
 * @since 2021/8/24 19:47
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    public static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");
    private Long productId;

    public GetProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommandGroup"))
                .andCommandKey(KEY)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetProductInfoCommandThreadPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(15).withQueueSizeRejectionThreshold(10))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(30)
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)
                        .withExecutionTimeoutInMilliseconds(20000)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)
                )

        );

        this.productId = productId;
    }

    public static void flushCache(Long productId) {
        HystrixRequestCache.getInstance(KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(getCacheKey(productId));
    }

    private static String getCacheKey(Long productId) {
        return "product_info_" + productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        System.out.println("查询商品数据 productId: " + productId);
        if (productId == -1L) {
            throw new IllegalArgumentException("productId illegal");
        }
        if (productId == -2L) {
            ThreadUtil.sleep(2000);
        }
        // 拿到一个商品id
        // 调用商品服务的接口，获取商品id对应的商品的最新数据
        // 用HttpClient去调用商品服务的http接口
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        final ProductInfo productInfo = JSONObject.parseObject(response, ProductInfo.class);
        System.out.println("查询商品数据: " + productInfo);
        return productInfo;
    }

    @Override
    protected String getCacheKey() {
        return getCacheKey(productId);
    }

    @Override
    protected ProductInfo getFallback() {
        System.out.println("走降级方法");
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(productId);
        productInfo.setName("降级商品");
        return productInfo;
    }
}
