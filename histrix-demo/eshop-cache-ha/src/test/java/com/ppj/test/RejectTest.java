package com.ppj.test;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author pipi
 * @since 2021/8/25 17:46
 */
@Slf4j
public class RejectTest {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            System.out.print(i + ",");
        }
        final String url = "http://localhost:8081/change/product?productId=1";
        final String bad_url = "http://localhost:8081/change/product?productId=-2";
        for (int i = 0; i < 25; i++) {
            new Thread(() -> log.info(HttpUtil.get(bad_url))).start();
        }


//        new Thread(() -> {
//            ThreadUtil.sleep(1000);
//            log.info(HttpUtil.get(bad_url));
//        }).start();
        System.in.read();
    }
}
