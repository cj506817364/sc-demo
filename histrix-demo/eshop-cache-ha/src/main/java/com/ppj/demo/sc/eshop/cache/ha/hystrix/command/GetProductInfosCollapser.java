package com.ppj.demo.sc.eshop.cache.ha.hystrix.command;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.ppj.demo.sc.eshop.cache.ha.http.HttpClientUtils;
import com.ppj.demo.sc.eshop.cache.ha.model.ProductInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author pipi
 * @since 2021/8/25 18:45
 */
public class GetProductInfosCollapser extends HystrixCollapser<List<ProductInfo>, ProductInfo, Long> {

    private Long productId;

    public GetProductInfosCollapser(Long productId) {
        this.productId = productId;
    }

    @Override
    public Long getRequestArgument() {
        return this.productId;
    }


    @Override
    protected HystrixCommand<List<ProductInfo>> createCommand(Collection<CollapsedRequest<ProductInfo, Long>> requests) {
        List<Long> params = new ArrayList<>();
        for (CollapsedRequest<ProductInfo, Long> request : requests) {
            params.add(request.getArgument());
        }
        System.out.println("params: " + params);
        return new BatchCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(List<ProductInfo> batchResponse, Collection<CollapsedRequest<ProductInfo, Long>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<ProductInfo, Long> request : collapsedRequests) {
            request.setResponse(batchResponse.get(count++));
        }
    }
    @Override
    protected String getCacheKey() {
        return "product_info_" + productId;
    }

    private static final class BatchCommand extends HystrixCommand<List<ProductInfo>> {

        public final Collection<CollapsedRequest<ProductInfo, Long>> requests;

        private BatchCommand(Collection<CollapsedRequest<ProductInfo, Long>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BatchCommandGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("BatchCommand"))
            );
            this.requests = requests;
        }

        @Override
        protected List<ProductInfo> run() {
            List<Long> params = new ArrayList<>();
            for (CollapsedRequest<ProductInfo, Long> request : requests) {
                params.add(request.getArgument());
            }
            String url = "http://127.0.0.1:8082/getProductInfos?productIds=" + StrUtil.join(",", params);
            String response = HttpClientUtils.sendGetRequest(url);
            return JSONObject.parseArray(response, ProductInfo.class);
        }
    }
}
