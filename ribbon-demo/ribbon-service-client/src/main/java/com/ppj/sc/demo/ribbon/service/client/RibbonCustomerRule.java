package com.ppj.sc.demo.ribbon.service.client;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author pipi
 * @since 2021/8/18 10:15
 */
public class RibbonCustomerRule {

    public static void main(String[] args) {
        final BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
        baseLoadBalancer.setRule(new MyRule());

        List<Server> serverList = RibbonHelper.generatorDefaultServerList();
        baseLoadBalancer.addServers(serverList);

        for (int i = 0; i < RandomUtil.randomInt(6, 10); i++) {
            final Server server = baseLoadBalancer.chooseServer(null);
            System.out.println(server.getId());
        }
    }

    static class MyRule implements IRule {

        private ILoadBalancer balancer;

        @Override
        public Server choose(Object key) {
            final List<Server> allServers = balancer.getAllServers();
            if (CollUtil.isEmpty(allServers)) {
                throw new IllegalArgumentException("empty server list");
            }
            return allServers.get(0);
        }

        @Override
        public ILoadBalancer getLoadBalancer() {
            return this.balancer;
        }

        @Override
        public void setLoadBalancer(ILoadBalancer lb) {
            this.balancer = lb;
        }
    }

}
