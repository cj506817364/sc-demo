package com.ppj.sc.demo.ribbon.service.client;


import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pipi
 * @since 2021/8/17 19:21
 */
public class RibbonServiceClientApplication {
    public static void main(String[] args) throws Exception {
        // 基于ribbon的api来开发一个可以负载均衡调用一个服务的代码
        // 首先使用代码的方式对ribbon进行一下配置，配置一下ribbon要调用的那个服务的server list
        ConfigurationManager.getConfigInstance().setProperty(
                "ribbon-service.ribbon.listOfServers", "localhost:8080,localhost:8088");

        // 获取指定服务的RestClient，用于请求某个服务的client
        RestClient restClient = (RestClient) ClientFactory.getNamedClient("ribbon-service");

        // 你要请求哪个接口，构造一个对应的HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri("/ribbon/sayHello/ppj")
                .build();

        // 模拟请求一个接口10次
        for (int i = 0; i < 10; i++) {
            HttpResponse response = restClient.executeWithLoadBalancer(request);
            String result = response.getEntity(String.class);
            System.out.println(result);
        }


    }
}
