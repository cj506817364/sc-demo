package com.ppj.sc.demo.eureka.server.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author pipi
 * @since 2021/8/27 10:05
 */
@Component
public class ServiceAClinetFallbackFactory implements FallbackFactory<ServiceAClient> {

    //    @Resource
//    private ServiceAClinetFallback serviceAClinetFallback;
    @Override
    public ServiceAClient create(Throwable throwable) {
        return new ServiceAClinetFallback();
//        return new ServiceAClient() {
//
//            @Override
//            public String sayHello(Long id, String name, Integer age) {
//                System.out.println("sayHello 降级方法");
//                return null;
//            }
//
//            @Override
//            public String createUser(User user) {
//                System.out.println("createUser 降级方法");
//                return null;
//            }
//
//            @Override
//            public String updateUser(Long id, User user) {
//                System.out.println("updateUser 降级方法");
//                return null;
//            }
//
//            @Override
//            public String deleteUser(Long id) {
//                System.out.println("deleteUser 降级方法");
//                return null;
//            }
//
//            @Override
//            public User getById(Long id) {
//                System.out.println("getById 降级方法");
//                return null;
//            }
//        };
    }
}
