package com.ppj.demo.sc.eshop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author pipi
 * @since 2021/8/25 14:55
 */
public class UpdateProductInfoCommand extends HystrixCommand<Boolean> {

    private Long productId;

    public UpdateProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("UpdateProductInfoCommandGroup"));
        this.productId = productId;
    }

    @Override
    protected Boolean run() throws Exception {
        GetProductInfoCommand.flushCache(productId);
        return true;
    }

}
