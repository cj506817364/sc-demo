package com.ppj.demo.sc.eshop.cache.ha.controller;

import com.alibaba.fastjson.JSON;
import com.ppj.demo.sc.eshop.cache.ha.hystrix.command.*;
import com.ppj.demo.sc.eshop.cache.ha.model.ProductInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author pipi
 * @since 2021/8/24 19:37
 */
@RestController
public class CacheController {

    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Long productId) {
        final GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(productId);
        final ProductInfo productInfo = getProductInfoCommand.execute();

        //        final Future<ProductInfo> future = getProductInfoCommand.queue();
        //        System.out.println(future.get());

        final GetCityNameCommand getCityNameCommand = new GetCityNameCommand(productInfo.getCityId());
        final String cityName = getCityNameCommand.execute();
        productInfo.setCityName(cityName);

        final GetBrandNameCommand getBrandNameCommand = new GetBrandNameCommand(productInfo.getBrandId());
        final String execute = getBrandNameCommand.execute();
        productInfo.setBrandName(execute);
        return JSON.toJSONString(productInfo);
    }

    @RequestMapping("/change/products")
    @ResponseBody
    public String changeProducts(Long[] productIds) throws ExecutionException, InterruptedException {
//        final GetProductInfosCommand getProductInfosCommand = new GetProductInfosCommand(productIds);
//        final Observable<ProductInfo> observe = getProductInfosCommand.observe();
//        observe.subscribe(new Observer<ProductInfo>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("获取完了所有商品数据");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onNext(ProductInfo productInfo) {
//                System.out.println(productInfo);
//            }
//        });

//        for (Long productId : productIds) {
//            final GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(productId);
//            final ProductInfo execute = getProductInfoCommand.execute();
//            Console.log("cache: {} productInfo: {}", getProductInfoCommand.isResponseFromCache(), execute);
//
//        }

        List<Future<ProductInfo>> futureList = new ArrayList<>();
        for (Long productId : productIds) {
            final GetProductInfosCollapser getProductInfosCollapser = new GetProductInfosCollapser(productId);
            final Future<ProductInfo> queue = getProductInfosCollapser.queue();
            futureList.add(queue);
        }
        for (Future<ProductInfo> productInfoFuture : futureList) {
            final ProductInfo productInfo = productInfoFuture.get();
            System.out.println(productInfo);
        }

        return "success";
    }
}
