package com.ppj.sc.demo.ribbon.service.client;

import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pipi
 * @since 2021/8/18 18:09
 */
public class RibbonHelper {
    public static List<Server> generatorDefaultServerList() {
        List<Server> service = new ArrayList<>();
        service.add(new Server("localhost:8080"));
        service.add(new Server("localhost:8088"));
        return service;
    }
}
