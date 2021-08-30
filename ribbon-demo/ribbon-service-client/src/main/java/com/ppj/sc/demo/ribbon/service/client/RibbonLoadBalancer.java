package com.ppj.sc.demo.ribbon.service.client;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author pipi
 * @since 2021/8/18 10:14
 */
public class RibbonLoadBalancer {

    public static void main(String[] args) {

        // 原理
        final ILoadBalancer loadBalancer = new BaseLoadBalancer();
        List<Server> service = RibbonHelper.generatorDefaultServerList();
        loadBalancer.addServers(service);

        for (int i = 0; i < 10; i++) {
            final Server server = loadBalancer.chooseServer(null);
            System.out.println(server.getId());
        }
    }
}
