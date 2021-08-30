package com.ppj.sc.demo.eureka.server.client;

import com.ppj.sc.demo.eureka.service.api.ServiceAInterface;
import com.ppj.sc.demo.eureka.service.api.dto.User;
import org.springframework.stereotype.Component;

/**
 * @author pipi
 * @since 2021/8/27 10:05
 */
//@Component
public class ServiceAClinetFallback implements ServiceAClient {

    @Override
    public String sayHello(Long id, String name, Integer age) {
        System.out.println("sayHello 降级方法");
        return null;
    }

    @Override
    public String createUser(User user) {
        System.out.println("createUser 降级方法");
        return null;
    }

    @Override
    public String updateUser(Long id, User user) {
        System.out.println("updateUser 降级方法");
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        System.out.println("deleteUser 降级方法");
        return null;
    }

    @Override
    public User getById(Long id) {
        System.out.println("getById 降级方法");
        return null;
    }
}
