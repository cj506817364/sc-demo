package com.ppj.test;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

/**
 * hystrix 断路器功能测试
 *
 * @author pipi
 * @since 2021/8/25 16:37
 */
public class HystixCircuitBreakerTest {

    public static void main(String[] args) {
        final String url = "http://localhost:8081/change/product?productId=1";
        final String bad_url = "http://localhost:8081/change/product?productId=-1";
        for (int i = 0; i < 15; i++) {
            System.out.println(HttpUtil.get(url));
        }

        System.err.println("开始提交错误请求");
        for (int i = 0; i < 15; i++) {
            System.out.println(HttpUtil.get(bad_url));
        }

        System.err.println("休眠5秒钟");
        ThreadUtil.sleep(5000);

        System.err.println("开始提交正确请求 这时候应该直接走短路方法");
        for (int i = 0; i < 15; i++) {
            System.out.println(HttpUtil.get(url));
        }

        System.err.println("休眠3秒钟");
        ThreadUtil.sleep(3000);
        System.err.println("开始提交正确请求 这时候应该走成功方法");
        for (int i = 0; i < 15; i++) {
            System.out.println(HttpUtil.get(url));
        }

    }

}
