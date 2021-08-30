package com.ppj.demo.sc.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.ppj.demo.sc.eshop.cache.ha.http.HttpClientUtils;
import com.ppj.demo.sc.eshop.cache.ha.model.ProductInfo;
import rx.Observable;
import rx.Subscriber;

/**
 * @author pipi
 * @since 2021/8/24 19:45
 */
public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {

    private Long[] productIds;

    public GetProductInfosCommand(Long[] productIds) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfosGroup"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<ProductInfo> construct() {
        return Observable.create(new Observable.OnSubscribe<ProductInfo>() {
            @Override
            public void call(Subscriber<? super ProductInfo> observer) {
                for (Long productId : productIds) {
                    String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
                    String response = HttpClientUtils.sendGetRequest(url);
                    final ProductInfo productInfo = JSONObject.parseObject(response, ProductInfo.class);
                    observer.onNext(productInfo);
                }
                observer.onCompleted();
            }
        });
    }
}
